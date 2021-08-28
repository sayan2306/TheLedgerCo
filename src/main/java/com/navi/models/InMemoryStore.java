package com.navi.models;

import com.navi.exceptions.InvalidBankOrCustomerNameException;
import com.navi.exceptions.LoanAlreadyExistsException;
import com.navi.exceptions.NoBankFoundException;
import com.navi.exceptions.NoLoanForCustomerInBankException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStore implements Store {
    private final Map<String, Map<String, LoanDetails>> store;

    public InMemoryStore() {
        this.store = new HashMap<>();
    }

    public void addToStore(LoanDetails loanDetails) {
        String bank = loanDetails.getLoan().getLoaner().trim();
        String customer = loanDetails.getLoan().getLoanee().trim();
        if(bank.length() == 0 || customer.length() == 0)
            throw new InvalidBankOrCustomerNameException();
        if(store.containsKey(bank) && store.get(bank).containsKey(customer)) {
            throw new LoanAlreadyExistsException();
        }
        store.putIfAbsent(bank, new HashMap<>());
        store.get(bank).put(customer, loanDetails);
    }


    @Override
    public LoanDetails getLoanDetailsByBankAndCustomer(String bank, String customer) {
        if(!store.containsKey(bank)) {
            throw new NoBankFoundException();
        } else {
            if(!store.get(bank).containsKey(customer)) {
                throw new NoLoanForCustomerInBankException();
            } else {
                return store.get(bank).get(customer);
            }
        }
    }


    @Override
    public String toString() {
        return "InMemoryLedger{" +
                "store=" + store +
                '}';
    }
}
