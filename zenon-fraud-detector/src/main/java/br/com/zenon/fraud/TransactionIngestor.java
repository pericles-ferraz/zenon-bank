package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    private static final int MAX_RECORDS = 1000;

    public List<Transaction> ingest(String fileName) throws IOException {

        List<Transaction> transactions = new ArrayList<>(MAX_RECORDS);

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {

            reader.readLine();
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null && count < MAX_RECORDS) {

                String[] fields = line.split(",");

                TransactionCustomer origin = new TransactionCustomer(
                        fields[3],
                        new BigDecimal(fields[4]),
                        new BigDecimal(fields[5])
                );

                TransactionCustomer recipient = new TransactionCustomer(
                        fields[6],
                        new BigDecimal(fields[7]),
                        new BigDecimal(fields[8])
                );

                Transaction transaction = new Transaction(
                        Integer.parseInt(fields[0]),
                        TransactionType.valueOf(fields[1]),
                        new BigDecimal(fields[2]),
                        origin,
                        recipient,
                        "1".equals(fields[9]),
                        "1".equals(fields[10])
                );

                transactions.add(transaction);
                count++;
            }
        }

        return transactions;
    }
}