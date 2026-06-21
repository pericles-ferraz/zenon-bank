package br.com.zenon;

import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String csvFile = "../data/paysim_with_bad_data.csv";

        TransactionIngestor ingestor = new TransactionIngestor();

        try {

            List<Transaction> transactions = ingestor.ingest(csvFile);

            System.out.printf(
                    "Imported %,d transactions%n%n",
                    transactions.size()
            );

            transactions.stream()
                    .limit(10)
                    .forEach(System.out::println);

        } catch (Exception e) {
            System.err.println(
                    "Error reading file '" + csvFile + "': " + e.getMessage()
            );
        }
    }
}
