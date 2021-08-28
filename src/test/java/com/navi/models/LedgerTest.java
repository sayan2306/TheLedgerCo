package com.navi.models;

import com.navi.output.ConsoleOutputMode;
import com.navi.strategies.SimpleInterestCalculator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LedgerTest {

    @Before
    public void before() {

    }

    @Test
    public void test() {
        Ledger ledger = new Ledger(
                new InMemoryStore(),
                new SimpleInterestCalculator(),
                new ConsoleOutputMode()
        );
        assertNotNull(ledger.getInterestCalculator());
        assertNotNull(ledger.getOutputMode());
        assertNotNull(ledger.getStore());
    }
}
