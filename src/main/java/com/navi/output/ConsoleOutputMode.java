package com.navi.output;

public class ConsoleOutputMode implements OutputMode {
    @Override
    public void print(String output) {
        System.out.println(output);
    }
}
