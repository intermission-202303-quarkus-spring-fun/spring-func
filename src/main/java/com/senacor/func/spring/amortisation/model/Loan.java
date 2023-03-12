package com.senacor.func.spring.amortisation.model;

public class Loan {
    final double totalLoan;
    final double currentInterestRate;
    final double repaymentPerYear;

    public Loan(double totalLoan, double currentInterestRate, double repaymentPerYear) {
        this.totalLoan = totalLoan;
        this.currentInterestRate = currentInterestRate;
        this.repaymentPerYear = repaymentPerYear;
    }
    public static class Amortization {
        final double totalPaymentAmount;
        final double lastRate;
        final int years;

        public Amortization(double totalPaymentAmount, double lastRate, int years) {
            this.totalPaymentAmount = totalPaymentAmount;
            this.lastRate = lastRate;
            this.years = years;
        }

        public double getTotalPaymentAmount() {
            return totalPaymentAmount;
        }

        public double getLastRate() {
            return lastRate;
        }

        public int getYears() {
            return years;
        }
    }

    public Amortization amortization() {
        var years = 0;
        var remainingDebt = totalLoan;
        var totalPaymentAmount = 0.0d;
        var lastPayment = 0.0d;
        while (remainingDebt > 0) {
            years++;
            var interest = remainingDebt * currentInterestRate;
            if (interest >= repaymentPerYear) {
                throw new RuntimeException("would run forever");
            }
            remainingDebt += interest;
            if (remainingDebt < repaymentPerYear) {
                totalPaymentAmount += remainingDebt;
                lastPayment = remainingDebt;
                remainingDebt = 0;
            } else {
                remainingDebt -= repaymentPerYear;
                totalPaymentAmount += repaymentPerYear;
                lastPayment = repaymentPerYear;
            }
        }
        return new Amortization(totalPaymentAmount, lastPayment, years);
    }
}