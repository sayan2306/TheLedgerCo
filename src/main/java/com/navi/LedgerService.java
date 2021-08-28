package com.navi;

import com.navi.command.factory.ExecutorFactory;
import com.navi.models.Ledger;

public class LedgerService {
    private final Ledger ledger;
    private final ExecutorFactory supportedCommands;

    public LedgerService(Ledger ledger) {
        this.ledger = ledger;

        supportedCommands = ExecutorFactory.getInstance();
    }

    public Ledger getLedger() {
        return this.ledger;
    }

    public ExecutorFactory getSupportedCommands() {
        return supportedCommands;
    }

    @Override
    public String toString() {
        return "LedgerService{" +
                "ledger=" + ledger +
                ", registry=" + supportedCommands +
                '}';
    }
}
