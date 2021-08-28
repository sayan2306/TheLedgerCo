package com.navi.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoanTest {

    @Test
    public void testLoan() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        assertNotNull(loan);
        assertEquals("IDIDI", loan.getLoaner());
        assertEquals("Harry", loan.getLoanee());
        assertEquals(5000.0, loan.getPrincipalAmount(), 0);
        assertEquals(2, loan.getYears(), 0);
        assertEquals(2.0, loan.getInterestRate(), 0);
    }
}
