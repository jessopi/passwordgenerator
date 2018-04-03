package com.jessopi.passwordgenerator;

import java.util.Random;
/*
    Generates password according to parameters passed to generatePassword
*/
public class AllCharacterGenerator {
    private Random random;

    //Randomly chooses a character from the available characters each loop and appends to the result string
    public String generatePassword(int length,boolean upperAllowed,boolean lowerAllowed, boolean numbersAllowed, boolean specialCharactersAllowed){
        random = new Random();
       int size = length;
        String passwordCharacters = generatePasswordCharacters(upperAllowed,lowerAllowed,numbersAllowed,specialCharactersAllowed);
        StringBuilder result = new StringBuilder();
        if(passwordCharacters.isEmpty()){
            return "";
        }
        for(int i = 0 ; i < size;i++){
            result.append(passwordCharacters.charAt(random.nextInt(passwordCharacters.length())));
        }
        return result.toString();
    }

    //Builds string to be used to generate passwords
    private String generatePasswordCharacters(boolean upperAllowed,boolean lowerAllowed, boolean numbersAllowed, boolean specialCharactersAllowed){
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvxyz";
        String numbers = "0123456789";
        String specialCharacters = "!$%@#";
        String availableCharacters = "";

        if(upperAllowed){
            availableCharacters+=upperCase;
        }
        if(lowerAllowed){
            availableCharacters+=lowerCase;
        }
        if(numbersAllowed){
            availableCharacters+=numbers;
        }
        if(specialCharactersAllowed){
            availableCharacters+=specialCharacters;
        }
        return availableCharacters;
    }
}
