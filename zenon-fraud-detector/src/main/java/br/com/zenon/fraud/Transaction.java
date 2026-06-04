package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        long step,
        TransactionType type,
        BigDecimal amount,
        TransactionCustomer origin,
        TransactionCustomer recipient,
        boolean isFraud,
        boolean isFlaggedFraud
) {
}