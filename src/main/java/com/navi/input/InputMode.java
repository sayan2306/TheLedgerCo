package com.navi.input;

import com.navi.LedgerService;
import com.navi.command.executors.CommandExecutor;
import com.navi.models.Command;

public abstract class InputMode {
    private final LedgerService ledgerService;

    protected InputMode(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    protected void execute(String s) {
        Command command = new Command(s);
        CommandExecutor executor = ledgerService.getSupportedCommands().getCommandExecutor(command);
        executor.execute(command, ledgerService.getLedger());
    }

    public abstract void process();
}
