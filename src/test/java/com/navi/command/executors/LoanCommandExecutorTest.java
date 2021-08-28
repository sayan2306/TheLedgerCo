package com.navi.command.executors;

import static org.junit.Assert.assertEquals;

import com.navi.exceptions.InvalidParameterException;
import com.navi.models.*;
import com.navi.output.ConsoleOutputMode;
import com.navi.strategies.SimpleInterestCalculator;
import org.junit.Before;
import org.junit.Test;

public class LoanCommandExecutorTest {

    Ledger ledger;

    @Before
    public void setUpStreams() {
        ledger = new Ledger(
                new InMemoryStore(),
                new SimpleInterestCalculator(),
                new ConsoleOutputMode()
        );
    }

    @Test
    public void testExecute() {
        Command command = new Command("LOAN IDIDI Dale 10000 5 4");

        CommandExecutor commandExecutor = new LoanCommandExecutor();
        commandExecutor.execute(command, ledger);

        LoanDetails loanDetails = ledger.getStore().getLoanDetailsByBankAndCustomer("IDIDI", "Dale");

        assertEquals(10000.0, loanDetails.getLoan().getPrincipalAmount(), 0);
        assertEquals(5, loanDetails.getLoan().getYears(), 0);
        assertEquals(4.0, loanDetails.getLoan().getInterestRate(), 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithInvalidParameters() {
        Command command = new Command("LOAN IDIDI 10000 5 4");

        CommandExecutor commandExecutor = new LoanCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithNegativeAmount() {
        Command command = new Command("LOAN IDIDI Dale -10000 5 4");

        CommandExecutor commandExecutor = new LoanCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithNegativeYears() {
        Command command = new Command("LOAN IDIDI Dale 10000 -5 4");

        CommandExecutor commandExecutor = new LoanCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithNegativeInterestRate() {
        Command command = new Command("LOAN IDIDI Dale 10000 5 -4");

        CommandExecutor commandExecutor = new LoanCommandExecutor();
        commandExecutor.execute(command, ledger);
    }
}

