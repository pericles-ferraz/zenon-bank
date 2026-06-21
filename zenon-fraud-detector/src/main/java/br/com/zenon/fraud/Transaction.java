package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Objects;

public record Transaction(
        long step,
        TransactionType type,
        BigDecimal amount,
        TransactionCustomer origin,
        TransactionCustomer recipient,
        boolean isFraud,
        boolean isFlaggedFraud
) {
    public Transaction{

        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(recipient);

        if(step <=0){
            throw new IllegalArgumentException("step should be positive: " + step);
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("amount should be positivo: " + amount);
        }
    }
}