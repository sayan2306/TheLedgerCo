package com.navi.strategies;

import com.navi.models.Loan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InterestCalculatorTest {
    InterestCalculator interestCalculator;


    @Before
    public void before() {
        interestCalculator = mock(InterestCalculator.class);
    }

    @Test
    public void testInterestCalculator() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        when(interestCalculator.calculateInterestAmount(loan)).thenReturn(200.0);
        assertEquals(200.0, interestCalculator.calculateInterestAmount(loan), 0);
    }

}
