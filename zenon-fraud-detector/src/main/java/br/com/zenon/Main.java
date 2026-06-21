package br.com.zenon;

import br.com.zenon.fraud.FraudAnalyzer;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

import java.math.RoundingMode;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String csvFile = "../data/PS_20174392719_1491204439457_log.csv";

        TransactionIngestor ingestor = new TransactionIngestor();

        try {

            List<Transaction> transactions = ingestor.ingest(csvFile);

            FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactions);

            IO.println("1. Total de Fraudes: " + fraudAnalyzer.countFrauds());

            IO.println("2. Top 3 Fraudes de Maior Valor:");
            fraudAnalyzer.getHighestValueFrauds(3).stream().forEach( t ->  IO.println(t.amount().setScale(2, RoundingMode.HALF_UP)));

            IO.println("3. Clientes Suspeitos:");
            fraudAnalyzer.getFraudsters(5).forEach(f -> IO.println(f));

            IO.println("4. Prejuízo total: " + fraudAnalyzer.getTotalLoss());

            IO.println("5. Fraudes por tipo:");
            fraudAnalyzer.fraudByType().forEach((type, count) -> IO.println("- %s: %d ".formatted(type, count)));

        } catch (Exception e) {
            System.err.println(
                    "Error reading file '" + csvFile + "': " + e.getMessage()
            );
        }
    }
}
