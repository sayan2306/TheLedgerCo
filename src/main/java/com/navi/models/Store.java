package com.navi.models;

public interface Store {
    public void addToStore(LoanDetails loanDetails);
    public LoanDetails getLoanDetailsByBankAndCustomer(String bank, String customer);
}
