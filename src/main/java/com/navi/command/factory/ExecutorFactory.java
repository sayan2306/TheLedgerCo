package com.navi.command.factory;

import com.navi.command.executors.BalanceCommandExecutor;
import com.navi.command.executors.CommandExecutor;
import com.navi.command.executors.LoanCommandExecutor;
import com.navi.command.executors.PaymentCommandExecutor;
import com.navi.exceptions.InvalidCommandException;
import com.navi.models.Command;

import java.util.HashMap;
import java.util.Map;

public class ExecutorFactory {

    private final Map<String, CommandExecutor> commands = new HashMap();

    private ExecutorFactory() {
        CommandExecutor loanCommandExecutor = new LoanCommandExecutor();
        CommandExecutor balanceCommandExecutor = new BalanceCommandExecutor();
        CommandExecutor paymentCommandExecutor = new PaymentCommandExecutor();

        commands.put(loanCommandExecutor.getCommandName(), loanCommandExecutor);
        commands.put(balanceCommandExecutor.getCommandName(), balanceCommandExecutor);
        commands.put(paymentCommandExecutor.getCommandName(), paymentCommandExecutor);
    }

    public CommandExecutor getCommandExecutor(Command command) {
        if(!commands.containsKey(command.getCommandName().toUpperCase())) {
            throw new InvalidCommandException();
        }
        return commands.get(command.getCommandName());
    }

    public static ExecutorFactory getInstance() {
        return new ExecutorFactory();
    }
}
