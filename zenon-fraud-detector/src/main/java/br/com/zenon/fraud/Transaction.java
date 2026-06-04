package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        long step,
        TransactionType type,
        BigDecimal amount,
        String nameOrig,
        BigDecimal oldBalanceOrg,
        BigDecimal newBalanceOrig,
        String nameDest,
        BigDecimal oldBalanceDest,
        BigDecimal newBalanceDest,
        int isFraud,
        int isFlaggedFraud
) {
}