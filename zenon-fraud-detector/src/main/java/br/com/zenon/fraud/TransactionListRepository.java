package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public Optional<Transaction> getTransactionByName(String name) {

        long start = System.nanoTime();

        Optional<Transaction> result = transactions.stream()
                                    .filter(t -> t.origin().name().equals(name))
                                    .findFirst();

        long end = System.nanoTime();

        IO.println("Tempo de execução (ms): " + (end - start) / 1_000_000.0);
        return  result;
    }
}
