package com.navi.strategies;

import com.navi.models.Loan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleInterestCalculatorTest {
    InterestCalculator interestCalculator;

    @Before
    public void before() {
        interestCalculator = new SimpleInterestCalculator();
    }

    @Test
    public void testSimpleInterestCalculator() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        double interest = interestCalculator.calculateInterestAmount(loan);
        assertEquals(200.0, interest, 0);
    }

    @Test
    public void testSimpleInterestCalculatorWithZeroInterestRate() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 0.0);
        double interest = interestCalculator.calculateInterestAmount(loan);
        assertEquals(0.0, interest, 0);
    }
}
