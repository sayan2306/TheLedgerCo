package com.navi.models;

import java.util.ArrayList;
import java.util.List;

public class LoanDetails {
    private List<Payment> payments;
    private final Loan loan;

    public LoanDetails(Loan loan) {
        this.loan = loan;
        payments = new ArrayList<>();
    }

    public Loan getLoan() {
        return loan;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void makePayment(Payment payment) {
        payments.add(payment);
    }

    @Override
    public String toString() {
        return "LoanDetails{" +
                ", payments=" + payments +
                ", loan=" + loan +
                '}';
    }
}
