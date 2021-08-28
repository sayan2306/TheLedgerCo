package com.navi.strategies;

import com.navi.models.Loan;

public interface InterestCalculator {
    public double calculateInterestAmount(Loan loan);
}
