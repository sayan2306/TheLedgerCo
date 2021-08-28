package com.navi.command.executors;

import com.navi.models.*;
import com.navi.strategies.InterestCalculator;
import com.navi.utils.validators.IntegerValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceCommandExecutor extends CommandExecutor {
    private InterestCalculator interestCalculator;

    public BalanceCommandExecutor() {
        super("BALANCE");
    }

    @Override
    protected boolean validate(Command command) {
        if(command.getParams().size() != 3) { return false; }
        if(!IntegerValidator.isValidPositiveInteger(command.getParams().get(2))) { return false; }
        return true;
    }

    @Override
    protected void run(Command command, Ledger ledger) {
        interestCalculator = ledger.getInterestCalculator();
        List<String> params = command.getParams();
        int emiNo = Integer.parseInt(params.get(2));
        String bank = params.get(0);
        String customer = params.get(1);
        LoanDetails loanDetails = ledger.getStore().getLoanDetailsByBankAndCustomer(bank, customer);


        int amountPaid = calculateAmountPaidTillInstallmentNo(emiNo, loanDetails);

        int emisLeft = calculateNoOfEMILeft(loanDetails.getLoan(), amountPaid);

        ledger.getOutputMode().print(bank + " " + customer + " " + amountPaid + " " + emisLeft);
    }

    private Integer calculateAmountPaidTillInstallmentNo(Integer emiNo, LoanDetails loanDetails) {
        List<Payment> payments = loanDetails.getPayments();
        double totalAmount = calculateTotalLoanAmount(loanDetails.getLoan());
        Map<Integer, List<Double>> emiNoToPaymentMap = new HashMap<>();
        for(Payment payment : payments) {
            emiNoToPaymentMap.putIfAbsent(payment.getEmiNo(), new ArrayList<>());
            emiNoToPaymentMap.get(payment.getEmiNo()).add(payment.getAmount());
        }

        Loan loan = loanDetails.getLoan();

        int emi = calculateEMI(loan);

        double alreadyPaidAmount = 0.0;

        for(int i = 1; i <= emiNo; i++) {
            double temp = alreadyPaidAmount + emi;
            if(Double.compare(temp, totalAmount) > 0) {
                emi = (int) Math.ceil(totalAmount - alreadyPaidAmount);
            }
            alreadyPaidAmount += emi;

            if(emiNoToPaymentMap.containsKey(i)) {
                double amountAfterPayment = alreadyPaidAmount;
                for(double amount : emiNoToPaymentMap.get(i))
                    amountAfterPayment += amount;
                double diff = 0;
                if(Double.compare(amountAfterPayment, totalAmount) > 0) {
                    diff = totalAmount - alreadyPaidAmount;
                    alreadyPaidAmount += diff;
                } else {
                    alreadyPaidAmount = amountAfterPayment;
                }
            }
        }

        int roundedOffPayment = (int) Math.ceil(alreadyPaidAmount);

        return roundedOffPayment;
    }


    private double calculateTotalLoanAmount(Loan loan) {
        return loan.getPrincipalAmount() + interestCalculator.calculateInterestAmount(loan);
    }

    private int calculateEMI(Loan loan) {
        return (int) Math.ceil(calculateTotalLoanAmount(loan) / (12 * loan.getYears()));
    }

    private int calculateNoOfEMILeft(Loan loan, Integer alreadyPaidAmount) {
        int emi = calculateEMI(loan);
        double totalAmount = calculateTotalLoanAmount(loan);
        double amountToBePaid = totalAmount - alreadyPaidAmount;
        if (amountToBePaid <= 0.0)
            return 0;
        return (int) Math.ceil((double) amountToBePaid / emi);
    }

}