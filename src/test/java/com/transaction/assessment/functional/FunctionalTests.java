package com.transaction.assessment.functional;

import static com.streams.assessment.testutils.TestUtils.businessTestFile;
import static com.streams.assessment.testutils.TestUtils.currentTest;
import static com.streams.assessment.testutils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.transaction.assessment.Transaction;
import com.transaction.assessment.TransactionAnalysis;

public class FunctionalTests {

	@Test
	public void testCalculateAverageTransactionAmount() throws IOException {
		List<Transaction> transactions = List.of(new Transaction(1, 100.0, "USD", 1651334400L), // Within time range
				new Transaction(2, 200.0, "USD", 1651334400L), // Within time range
				new Transaction(3, 300.0, "EUR", 1651334400L), // Different currency
				new Transaction(4, 400.0, "USD", 1651000000L) // Outside time range
		);

		Optional<Double> average = TransactionAnalysis.calculateAverageTransactionAmount(transactions, "USD",
				1651334400L, 1651420800L);

//		assertTrue(average.isPresent(), "Expected average to be present");
//		assertEquals(150.0, average.get(), 0.01, "Expected average amount to be correct");
		yakshaAssert(currentTest(), average.isPresent() ? "true" : "false", businessTestFile);
	}
}