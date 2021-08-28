package com.navi.models;

import com.navi.exceptions.InvalidBankOrCustomerNameException;
import com.navi.exceptions.LoanAlreadyExistsException;
import com.navi.exceptions.NoBankFoundException;
import com.navi.exceptions.NoLoanForCustomerInBankException;
import com.navi.models.Loan;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InMemoryStoreTest {
    Store store;

    @Before
    public void before() {
        store = new InMemoryStore();
    }

    @Test
    public void testAddToStore() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(loan));

        LoanDetails loanDetails = store.getLoanDetailsByBankAndCustomer("IDIDI", "Harry");
        assertEquals(5000.0, loanDetails.getLoan().getPrincipalAmount(), 0);
        assertEquals("IDIDI", loanDetails.getLoan().getLoaner());
        assertEquals("Harry", loanDetails.getLoan().getLoanee());
    }


    @Test(expected = LoanAlreadyExistsException.class)
    public void testAddToStoreForExistingUserInSameBank() {
        Loan firstLoan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(firstLoan));

        LoanDetails loanDetails = store.getLoanDetailsByBankAndCustomer("IDIDI", "Harry");
        assertEquals(5000.0, loanDetails.getLoan().getPrincipalAmount(), 0);
        assertEquals("IDIDI", loanDetails.getLoan().getLoaner());
        assertEquals("Harry", loanDetails.getLoan().getLoanee());

        Loan secondLoan = new Loan("IDIDI", "Harry", 2000.0, 2, 2.0);
        store.addToStore(new LoanDetails(secondLoan));
    }

    @Test
    public void testAddToStoreForExistingUserInDifferentBank() {
        Loan firstLoan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(firstLoan));

        LoanDetails firstLoanDetails = store.getLoanDetailsByBankAndCustomer("IDIDI", "Harry");
        assertEquals(5000.0, firstLoanDetails.getLoan().getPrincipalAmount(), 0);
        assertEquals("IDIDI", firstLoanDetails.getLoan().getLoaner());
        assertEquals("Harry", firstLoanDetails.getLoan().getLoanee());

        Loan secondLoan = new Loan("MBI", "Harry", 2000.0, 2, 2.0);
        store.addToStore(new LoanDetails(secondLoan));

        LoanDetails seondLoanDetails = store.getLoanDetailsByBankAndCustomer("IDIDI", "Harry");
        assertEquals(5000.0, seondLoanDetails.getLoan().getPrincipalAmount(), 0);
        assertEquals("IDIDI", seondLoanDetails.getLoan().getLoaner());
        assertEquals("Harry", seondLoanDetails.getLoan().getLoanee());
    }


    @Test(expected = InvalidBankOrCustomerNameException.class)
    public void testAddToStoreForEmptyBankName() {
        Loan firstLoan = new Loan("", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(firstLoan));
    }

    @Test(expected = InvalidBankOrCustomerNameException.class)
    public void testAddToStoreForEmptyCustomerName() {
        Loan firstLoan = new Loan("IDIDI", "", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(firstLoan));
    }


    @Test
    public void testGetFromStore() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(loan));

        LoanDetails loanDetails = store.getLoanDetailsByBankAndCustomer("IDIDI", "Harry");
        assertEquals(5000.0, loanDetails.getLoan().getPrincipalAmount(), 0);
        assertEquals("IDIDI", loanDetails.getLoan().getLoaner());
        assertEquals("Harry", loanDetails.getLoan().getLoanee());
    }

    @Test(expected = NoBankFoundException.class)
    public void testGetFromStoreForNonExistingBank() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(loan));

        LoanDetails loanDetails = store.getLoanDetailsByBankAndCustomer("ICIDI", "Harry");

    }

    @Test(expected = NoLoanForCustomerInBankException.class)
    public void testGetFromStoreForNonExistingCustomerInBank() {
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        store.addToStore(new LoanDetails(loan));

        LoanDetails loanDetails = store.getLoanDetailsByBankAndCustomer("IDIDI", "Harri");

    }

}
