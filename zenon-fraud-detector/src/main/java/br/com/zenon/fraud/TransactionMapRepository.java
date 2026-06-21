package br.com.zenon.fraud;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository{

    private Map<String, Transaction> transactionMap;

    public TransactionMapRepository (List<Transaction> transactions){
        Objects.requireNonNull(transactions);

        transactionMap = transactions.stream().collect(Collectors.toMap(t -> t.origin().name(), Function.identity()));
    }

    @Override
    public Optional<Transaction> getTransactionByName(String name) {

        long start = System.nanoTime();

        var result = Optional.ofNullable(transactionMap.get(name));

        long end = System.nanoTime();

        IO.println("Tempo de execução (ms): " + (end - start) / 1_000_000.0);

        return result;
    }
}
