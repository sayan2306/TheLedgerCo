package com.navi.command.factory;

import com.navi.command.executors.BalanceCommandExecutor;
import com.navi.command.executors.CommandExecutor;
import com.navi.command.executors.LoanCommandExecutor;
import com.navi.command.executors.PaymentCommandExecutor;
import com.navi.exceptions.InvalidCommandException;
import com.navi.models.Command;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ExecutorFactoryTest {

    ExecutorFactory executorFactoryTest;

    @Before
    public void before() {
        executorFactoryTest = ExecutorFactory.getInstance();
    }

    @Test
    public void testLoanCommand() {
        Command command = new Command("LOAN IDIDI Dale 5000 1 6");
        CommandExecutor executor = executorFactoryTest.getCommandExecutor(command);
        assertEquals(true, executor instanceof LoanCommandExecutor);
    }

    @Test
    public void testPaymentCommand() {
        Command command = new Command("PAYMENT IDIDI Dale 1000 5");
        CommandExecutor executor = executorFactoryTest.getCommandExecutor(command);
        assertEquals(true, executor instanceof PaymentCommandExecutor);
    }

    @Test
    public void testBalanceCommand() {
        Command command = new Command("BALANCE IDIDI Dale 3");
        CommandExecutor executor = executorFactoryTest.getCommandExecutor(command);
        assertEquals(true, executor instanceof BalanceCommandExecutor);
    }

    @Test(expected = InvalidCommandException.class)
    public void testInvalidCommand() {
        Command command = new Command("COMMAND1 PARAM1 PARAM2");
        CommandExecutor executor = executorFactoryTest.getCommandExecutor(command);
    }
}
