package com.transaction.assessment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionAnalysis {
	public static void main(String[] args) {
		List<Transaction> transactions = List.of(new Transaction(1, 200.0, "USD", 1651334400L),
				new Transaction(2, 450.0, "EUR", 1651420800L), new Transaction(3, 300.0, "USD", 1651507200L),
				new Transaction(4, 600.0, "USD", 1651593600L));

		Optional<Double> averageAmount = calculateAverageTransactionAmount(transactions, "USD", 1651334400L,
				1651593600L);
		averageAmount.ifPresentOrElse(amount -> System.out.println("Average Amount: $" + amount),
				() -> System.out.println("No transactions match the criteria."));
	}

	public static Optional<Double> calculateAverageTransactionAmount(List<Transaction> transactions, String currency,
			long startTime, long endTime) {
		List<Double> amounts = transactions.parallelStream()
				.filter(transaction -> transaction.currency().equalsIgnoreCase(currency)
						&& transaction.timestamp() >= startTime && transaction.timestamp() <= endTime)
				.map(Transaction::amount).collect(Collectors.toList());

		return amounts.isEmpty() ? Optional.empty()
				: Optional.of(amounts.stream().mapToDouble(Double::doubleValue).average().getAsDouble());
	}
}
