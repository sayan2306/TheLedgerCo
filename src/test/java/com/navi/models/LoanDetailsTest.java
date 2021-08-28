package com.navi.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

public class LoanDetailsTest {

    @Test
    public void testLoanDetails() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        assertNotNull(loan);
        assertEquals("IDIDI", loan.getLoaner());
        assertEquals("Harry", loan.getLoanee());
        assertEquals(5000.0, loan.getPrincipalAmount(), 0);
        assertEquals(2, loan.getYears(), 0);
        assertEquals(2.0, loan.getInterestRate(), 0);


        LoanDetails loanDetails = new LoanDetails(loan);

        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);


        loanDetails.makePayment(payment);

        assertEquals(1, loanDetails.getPayments().size());
    }
}
