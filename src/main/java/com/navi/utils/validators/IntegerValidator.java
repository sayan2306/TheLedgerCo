package com.navi.utils.validators;

public class IntegerValidator {

    public static boolean isValidPositiveInteger(String input) {
        for(int i = 0; i < input.length(); i++) {
            if(!Character.isDigit(input.charAt(i))) return false;
        }
        return true;
    }
}
