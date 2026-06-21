package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer(
        String name,
        BigDecimal oldBalance,
        BigDecimal newBalance
) {
    public TransactionCustomer{

        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

        if(name.trim().equals("")){
            throw new IllegalArgumentException("name should not be empty");
        }

        if (oldBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("oldBalance should be positivo: " + oldBalance);
        }

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("newBalance should be positivo: " + newBalance);
        }
    }
}