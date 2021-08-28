package com.navi.models;

public class Payment {
    private final Integer emiNo;
    private final Double amount;

    public Payment(Integer emiNo, Double amount) {
        this.emiNo = emiNo;
        this.amount = amount;
    }

    public Integer getEmiNo() {
        return emiNo;
    }

    public Double getAmount() {
        return amount;
    }
}
