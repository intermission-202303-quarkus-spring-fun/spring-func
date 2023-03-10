package com.senacor.func.spring.amortisation;

import java.math.BigDecimal;

public record Loan (
        double totalLoan,
        double currentInterestRate,
        double repaymentPerYear
) {
    public record Amortization(
            double totalPaymentAmount,
            double lastRate,

            int years
    ) {
    }
    public Amortization amortization() {
        var years = 0;
        var remainingDebt = totalLoan();
        var totalPaymentAmount = 0.0d;
        var lastPayment = 0.0d;
        while (remainingDebt > 0) {
            years++;
            var interest = remainingDebt * currentInterestRate();
            if (interest >= repaymentPerYear()) {
                throw new RuntimeException("would run forever");
            }
            remainingDebt += interest;
            if (remainingDebt < repaymentPerYear()) {
                totalPaymentAmount += remainingDebt;
                lastPayment = remainingDebt;
                remainingDebt = 0;
            } else {
                remainingDebt -= repaymentPerYear();
                totalPaymentAmount += repaymentPerYear();
                lastPayment = repaymentPerYear();
            }
        }
        return new Amortization(totalPaymentAmount, lastPayment, years);
    }
}