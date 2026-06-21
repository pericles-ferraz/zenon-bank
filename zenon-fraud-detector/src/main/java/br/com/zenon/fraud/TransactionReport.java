package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionReport {

    private static String FRAUD = "1";

    public record ReportMain (long totalLines, long totalFrauds, BigDecimal totalValue){

        private final static ReportMain ZERO = new ReportMain(0, 0, BigDecimal.ZERO);
        private ReportMain add (ReportLine line){
            return new ReportMain(
                    totalLines + 1,
                    totalFrauds() + (line.isFraude()? 1 : 0),
                              totalValue().add(line.amount()));
        }

        private ReportMain addForParallel(ReportMain other){
            return  new ReportMain(totalLines + other.totalLines, totalFrauds + other.totalFrauds, totalValue.add(other.totalValue));
        }
    }

    public record ReportLine(boolean isFraude, BigDecimal amount) {

    }

    public ReportMain generateReport(String file){

        try {
            try (Stream<String> lines = Files.lines(Path.of(file))){

                ReportMain report = lines.skip(1)
                        .map(line -> parseReportLine(line))
                        .filter(line -> line.isPresent())
                        .map(Optional::get)
                        .reduce(
                                ReportMain.ZERO, ReportMain::add
                                ,ReportMain::addForParallel
                        );

                return report;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<ReportLine> parseReportLine(String line){
        try{
            String[] fields = line.split(",");

            boolean isFraude = FRAUD.equals(fields[9]);
            BigDecimal amount = new BigDecimal(fields[2]);

            return Optional.of(new ReportLine(isFraude, amount));

        }catch (Exception ex){
            System.err.println("Erro ao fazer parse: " + line + " | " + ex);
            return Optional.empty();
        }
    }
}
