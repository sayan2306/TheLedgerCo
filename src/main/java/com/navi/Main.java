package com.navi;

import com.navi.exceptions.ModeNotSupportedException;
import com.navi.input.FileInputMode;
import com.navi.models.InMemoryStore;
import com.navi.models.Ledger;
import com.navi.output.ConsoleOutputMode;
import com.navi.strategies.SimpleInterestCalculator;

public class Main {

    public static void main(String[] args) {

        Ledger ledger = new Ledger(
                new InMemoryStore(),
                new SimpleInterestCalculator(),
                new ConsoleOutputMode()
        );
        LedgerService ledgerService = new LedgerService(ledger);

        if(isFileMode(args)) {
            new FileInputMode(args[0], ledgerService).process();
        } else {
            //Implementation not needed as of now
            throw new ModeNotSupportedException();
        }
    }

    private static boolean isFileMode(String[] args) {
        if (args.length == 1)
            return true;
        return false;
    }
}


