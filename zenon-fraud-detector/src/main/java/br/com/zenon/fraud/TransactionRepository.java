package br.com.zenon.fraud;

import java.util.Optional;

public interface TransactionRepository {

    public Optional<Transaction> getTransactionByName(String name);
}
