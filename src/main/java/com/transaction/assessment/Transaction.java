package com.transaction.assessment;

public record Transaction(int id, double amount, String currency, long timestamp) {
}