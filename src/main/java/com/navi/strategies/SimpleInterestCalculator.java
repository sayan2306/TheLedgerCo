package com.navi.strategies;

import com.navi.models.Loan;

public class SimpleInterestCalculator implements InterestCalculator {


    @Override
    public double calculateInterestAmount(Loan loan) {
        return ((loan.getPrincipalAmount() * loan.getInterestRate() * loan.getYears()) / 100.0);
    }
}
