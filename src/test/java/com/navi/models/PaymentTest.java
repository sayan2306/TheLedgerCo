package com.navi.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentTest {

    @Test
    public void testPaymentWithValidValues() {
        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);
    }
}
