package com.navi.command.executors;

import com.navi.exceptions.InvalidCommandException;
import com.navi.exceptions.InvalidParameterException;
import com.navi.models.*;
import com.navi.output.ConsoleOutputMode;
import com.navi.strategies.SimpleInterestCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BalanceCommandExecutorTest {
    Ledger ledger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        ledger = new Ledger(
                new InMemoryStore(),
                new SimpleInterestCalculator(),
                new ConsoleOutputMode()
        );
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        ledger.getStore().addToStore(new LoanDetails(loan));
    }

    @After
    public void after() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testExecute() {
        Command command = new Command("BALANCE IDIDI Harry 5");
        CommandExecutor executor = new BalanceCommandExecutor();
        executor.execute(command, ledger);
        assertEquals("IDIDI Harry 1085 19\n", outContent.toString());
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithNegativeEMINo() {
        Command command = new Command("BALANCE IDIDI Harry -5");
        CommandExecutor executor = new BalanceCommandExecutor();
        executor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithInvalidParametes() {
        Command command = new Command("BALANCE IDIDI  -5");
        CommandExecutor executor = new BalanceCommandExecutor();
        executor.execute(command, ledger);
    }

    @Test(expected = InvalidCommandException.class)
    public void testExecuteWithInvalidCommandName() {
        Command command = new Command("BALANC IDIDI Harry -5");
        CommandExecutor executor = new BalanceCommandExecutor();
        executor.execute(command, ledger);
    }
}
