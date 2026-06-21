package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private List<Transaction> transactions;
    public FraudAnalyzer(List<Transaction> transactions){

        Objects.nonNull(transactions);

        this.transactions = transactions.stream().filter(t -> t.isFraud()).collect(Collectors.toList());
    }

    public long countFrauds(){
        return transactions.size();
    }

    public List<Transaction> getHighestValueFrauds(int limit){
        return transactions.stream()
                            .sorted(Comparator.comparing(Transaction::amount).reversed())
                            .limit(limit)
                            .collect(Collectors.toList());
    }

    public List<String> getFraudsters(int limit){

        return transactions.stream().sorted(Comparator.comparing(Transaction::amount).reversed())
                            .map(t -> t.origin().name())
                            .distinct()
                            .limit(limit)
                            .toList();
    }

    public BigDecimal getTotalLoss(){
        return transactions.stream().map(Transaction::amount)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> fraudByType(){
        return transactions.stream().collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }

}
