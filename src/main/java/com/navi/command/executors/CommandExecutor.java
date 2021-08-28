package com.navi.command.executors;

import com.navi.exceptions.InvalidCommandException;
import com.navi.models.Command;
import com.navi.models.Ledger;
//import com.navi.models.Store;

import com.navi.exceptions.InvalidParameterException;

public abstract class CommandExecutor {
    public String commandName;

    protected CommandExecutor(String command) {
        this.commandName = command;
    }

    public void execute(Command command, Ledger ledger) {
        if(!this.commandName.equalsIgnoreCase(command.getCommandName())) { throw new InvalidCommandException(); }
        if(!validate(command)) { throw new InvalidParameterException(); }
        run(command, ledger);
    }

    public String getCommandName() {
        return this.commandName;
    }

    protected abstract boolean validate(Command command);
    protected abstract void run(Command command, Ledger ledger);

}
