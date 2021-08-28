package com.navi.command.executors;

import com.navi.models.*;
import com.navi.utils.validators.IntegerValidator;

import java.util.List;

public class LoanCommandExecutor extends CommandExecutor {

    public LoanCommandExecutor() {
        super("LOAN");
    }

    @Override
    protected boolean validate(Command command) {
        if(command.getParams().size() != 5) { return false; }
        if(!IntegerValidator.isValidPositiveInteger(command.getParams().get(2))) { return false; }
        if(!IntegerValidator.isValidPositiveInteger(command.getParams().get(3))) { return false; }
        if(!IntegerValidator.isValidPositiveInteger(command.getParams().get(4))) { return false; }
        return true;
    }

    @Override
    protected void run(Command command, Ledger ledger) {
        List<String> params = command.getParams();
        Loan loan = new Loan(params.get(0),
                            params.get(1),
                            Double.parseDouble(params.get(2)),
                            Integer.parseInt(params.get(3)),
                            Double.parseDouble(params.get(4)));
        LoanDetails loanDetails = new LoanDetails(loan);
        ledger.getStore().addToStore(loanDetails);
    }
}


