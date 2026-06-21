package br.com.zenon;

import br.com.zenon.fraud.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String csvFile = "../data/PS_20174392719_1491204439457_log.csv";

        try {

            TransactionReport transactionReport = new TransactionReport();
            TransactionReport.ReportMain reportMain = transactionReport.generateReport(csvFile);

            IO.println("""
                        Total de linhas: %d
                        Total de fraudes: %d
                        Valor total transacionado: %.2f
                    """.formatted(reportMain.totalLines(), reportMain.totalFrauds(), reportMain.totalValue()));

        } catch (Exception e) {
            System.out.println(
                    "Error reading file '" + csvFile + "': " + e.getMessage()
            );
        }
    }
}
