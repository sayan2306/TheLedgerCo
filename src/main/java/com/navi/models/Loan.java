package com.navi.models;

public class Loan {
    private final String loaner;
    private final String loanee;
    private final Double principalAmount;
    private final Double interestRate;
    private final Integer years;


    public Loan(String loaner, String loanee, Double principalAmount, Integer years, Double interestRate) {
        this.loaner = loaner;
        this.loanee = loanee;
        this.principalAmount = principalAmount;
        this.years = years;
        this.interestRate = interestRate;

    }

    public String getLoaner() {
        return loaner;
    }

    public String getLoanee() {
        return loanee;
    }

    public Double getPrincipalAmount() {
        return principalAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getYears() {
        return years;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "loaner='" + loaner + '\'' +
                ", loanee='" + loanee + '\'' +
                ", principalAmount=" + principalAmount +
                ", interestRate=" + interestRate +
                ", years=" + years +
                '}';
    }
}
