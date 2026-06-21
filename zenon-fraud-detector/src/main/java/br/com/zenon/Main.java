package br.com.zenon;

import br.com.zenon.fraud.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String csvFile = "../data/PS_20174392719_1491204439457_log.csv";

        TransactionIngestor ingestor = new TransactionIngestor();

        try {

            List<Transaction> transactions = ingestor.ingest(csvFile);

            String noExistCustomer = "C12345";
            String existCustomer = "C1231006815";

            IO.println("Testes usando list: ");
            TransactionRepository repositoryList = new TransactionListRepository(transactions);
            repositoryList.getTransactionByName(noExistCustomer).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + noExistCustomer));
            repositoryList.getTransactionByName(existCustomer).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + existCustomer));

            IO.println();

            IO.println("Testes usando Map: ");
            TransactionRepository repositoryMap = new TransactionMapRepository(transactions);
            repositoryMap.getTransactionByName(noExistCustomer).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + noExistCustomer));
            repositoryMap.getTransactionByName(existCustomer).ifPresentOrElse(IO::println, () -> IO.println("Transação não encontrada para o cliente " + existCustomer));


        } catch (Exception e) {
            System.err.println(
                    "Error reading file '" + csvFile + "': " + e.getMessage()
            );
        }
    }
}
