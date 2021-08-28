package com.navi.command.executors;

import com.navi.models.*;
import com.navi.utils.validators.IntegerValidator;

import java.util.List;

public class PaymentCommandExecutor extends CommandExecutor {

    public PaymentCommandExecutor() {
        super("PAYMENT");
    }

    @Override
    protected boolean validate(Command command) {
        //PAYMENT IDIDI Dale 1000 5
        if(command.getParams().size() != 4) {  return false; }
        if(!IntegerValidator.isValidPositiveInteger(command.getParams().get(2))) { return false; }
        if(!IntegerValidator.isValidPositiveInteger(command.getParams().get(3))) { return false; }
        return true;
    }

    @Override
    protected void run(Command command, Ledger ledger) {
        List<String> params = command.getParams();
        Double amount = Double.parseDouble(params.get(2));
        Integer emiNo = Integer.parseInt(params.get(3));

        LoanDetails loanDetails = ledger.getStore().getLoanDetailsByBankAndCustomer(params.get(0), params.get(1));

        Payment payment = new Payment(emiNo, amount);
        loanDetails.makePayment(payment);
    }
}