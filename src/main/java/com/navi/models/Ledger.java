package com.navi.models;

import com.navi.output.OutputMode;
import com.navi.strategies.InterestCalculator;

public class Ledger {
    private final Store Store;
    private final InterestCalculator interestCalculator;
    private final OutputMode outputMode;


    public Ledger(Store store, InterestCalculator interestCalculator, OutputMode outputMode) {
        Store = store;
        this.interestCalculator = interestCalculator;
        this.outputMode = outputMode;
    }


    public Store getStore() {
        return Store;
    }

    public InterestCalculator getInterestCalculator() {
        return interestCalculator;
    }

    public OutputMode getOutputMode() {
        return outputMode;
    }
}
