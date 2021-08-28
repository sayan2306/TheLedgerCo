package com.navi.input;

import com.navi.LedgerService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileInputMode extends InputMode {
    private final String fileName;

    public FileInputMode(String fileName, LedgerService ledgerService) {
        super(ledgerService);
        this.fileName = fileName;
    }

    @Override
    public void process() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.fileName));
            String s;
            while((s = br.readLine()) != null) {
                execute(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] " + this.fileName + " does not exist.");
        } catch (Exception e) {
            System.out.println("[ERROR] "  + e);
        }
    }
}
