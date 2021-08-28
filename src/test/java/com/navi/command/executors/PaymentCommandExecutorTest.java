package com.navi.command.executors;

import com.navi.exceptions.InvalidParameterException;
import com.navi.exceptions.NoLoanForCustomerInBankException;
import com.navi.models.*;
import com.navi.output.ConsoleOutputMode;
import com.navi.strategies.SimpleInterestCalculator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PaymentCommandExecutorTest {

    Ledger ledger;

    @Before
    public void setUpStreams() {
        ledger = new Ledger(
                new InMemoryStore(),
                new SimpleInterestCalculator(),
                new ConsoleOutputMode()
        );
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        ledger.getStore().addToStore(new LoanDetails(loan));
    }

    @Test
    public void testExecute() {
        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);

        Command command = new Command("PAYMENT IDIDI Harry 1000 5");
        CommandExecutor commandExecutor = new PaymentCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = NoLoanForCustomerInBankException.class)
    public void testExecuteForCustomerWithNoLoan() {
        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);

        Command command = new Command("PAYMENT IDIDI Dale 1000 5");
        CommandExecutor commandExecutor = new PaymentCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithNegativeAmount() {
        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);

        Command command = new Command("PAYMENT IDIDI Dale -1000 5");
        CommandExecutor commandExecutor = new PaymentCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithNegativeEMINo() {
        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);

        Command command = new Command("PAYMENT IDIDI Dale 1000 -5");
        CommandExecutor commandExecutor = new PaymentCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

    @Test(expected = InvalidParameterException.class)
    public void testExecuteWithWrongParamterCount() {
        Payment payment = new Payment(5, 4000.0);

        assertNotNull(payment);
        assertEquals(5, payment.getEmiNo(), 0);
        assertEquals(4000.0, payment.getAmount(), 0);

        Command command = new Command("PAYMENT IDIDI Dale -5");
        CommandExecutor commandExecutor = new PaymentCommandExecutor();
        commandExecutor.execute(command, ledger);
    }

}
