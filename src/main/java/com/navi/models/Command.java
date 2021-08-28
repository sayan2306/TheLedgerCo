package com.navi.models;

import java.util.Arrays;
import java.util.List;

public class Command {
    private final String separator = " ";
    private final List<String> params;
    private final String commandName;

    public Command(String input) {
        List<String> tokens = Arrays.asList(input.split(separator));
        this.commandName = tokens.get(0);
        this.params = tokens.subList(1, tokens.size());
    }

    public String getCommandName() {
        return this.commandName;
    }

    public List<String> getParams() {
        return this.params;
    }

    @Override
    public String toString() {
        return "Command{" +
                "separator='" + separator + '\'' +
                ", params=" + params +
                ", commandName='" + commandName + '\'' +
                '}';
    }
}
