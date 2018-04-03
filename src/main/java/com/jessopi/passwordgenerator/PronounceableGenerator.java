package com.jessopi.passwordgenerator;

import java.util.*;

/*
    Class that generates a pronounceable word using common brigrams,common middle trigrams and common end trigrams.
    Altered and translated from javascript from https://www.hackerearth.com/practice/notes/random-pronouncable-text-generator/
*/
public class PronounceableGenerator {

    private Random generator;
    private final String[] initialBigrams = {"TH", "OF", "AN", "IN", "TO", "CO", "BE", "HE", "RE", "HA", "WA", "FO", "WH", "MA", "WI", "ON", "HI", "PR", "ST", "NO", "IS", "IT", "SE", "WE", "AS", "CA", "DE", "SO", "MO", "SH", "DI", "AL", "AR", "LI", "WO", "FR", "PA", "ME", "AT", "SU", "BU", "SA", "FI", "NE", "CH", "PO", "HO", "DO", "OR", "UN", "LO", "EX", "BY", "FA", "LA", "LE", "PE", "MI", "SI", "YO", "TR", "BA", "GO", "BO", "GR", "TE", "EN", "OU", "RA", "AC", "FE", "PL", "CL", "SP", "BR", "EV", "TA", "DA", "AB", "TI", "RO", "MU", "EA", "NA", "SC", "AD", "GE", "YE", "AF", "AG", "UP", "AP", "DR", "US", "PU", "CE", "IF", "RI", "VI", "IM", "AM", "KN", "OP", "CR", "OT", "JU", "QU", "TW", "GA", "VA", "VE", "PI", "GI", "BI", "FL", "BL", "EL", "JO", "FU", "HU", "CU", "RU", "OV", "MY", "OB", "KE", "EF", "PH", "CI", "KI", "NI", "SL", "EM", "SM", "VO", "MR", "WR", "ES", "DU", "TU", "AU", "NU", "GU", "OW", "SY", "JA", "OC", "EC", "ED", "ID", "JE", "AI", "EI", "SK", "OL", "GL", "EQ", "LU", "AV", "SW", "AW", "EY", "TY"};

    private final Map<String,List<Character>> finalTrigram = new HashMap<>();
    private final Map<String,List<Character>> middleTrigram = new HashMap<>();

    //initialize large maps
    public PronounceableGenerator(){
        initMiddleTrigram();
        initFinalTrigram();
    }

    //Returns a random character from the given input
    public String pickRandomItem(String[] input) {
        generator = new Random();
        return input[generator.nextInt(input.length)];
    }
    public String pickRandomItem(List<Character> input){
        generator = new Random();
        return input.get(generator.nextInt(input.size())).toString();
    }



    public String generateWord(int wordLength){
        List<Character> candidates;
        String lookupDigram;
        StringBuilder passwordString = new StringBuilder();
        //loops until password length is achieved
        while((passwordString.length()) < wordLength){
            //grabs initialBigram initially
            if(passwordString.length() < 2){
                passwordString.setLength(0);
                passwordString.append(pickRandomItem(initialBigrams)) ;
                continue;
            }
            //find last 2 characters added
            lookupDigram = passwordString.substring(passwordString.length() - 2);

            //If at end of string -1 grab a char list from finalTrigram else get from middle
            if(passwordString.length() == wordLength - 1){
                candidates = finalTrigram.get(lookupDigram);
            } else {
                candidates = middleTrigram.get(lookupDigram);
            }

            //If candidates are empty or null add to password.
            if(candidates != null && !candidates.isEmpty()){
               // str += pickRandomItem(candidates);
                passwordString.append(pickRandomItem(candidates));
            } else {
                //Goes back 2 or 3 chars if no candidates
                if(passwordString.length() < 3){
                    String temp = passwordString.substring(0,passwordString.length() - 2);
                    passwordString.setLength(0);
                    passwordString.append(temp);
                }
                else{
                    String temp = passwordString.substring(0,passwordString.length() - 3);
                    passwordString.setLength(0);
                    passwordString.append(temp);
                }
                //
            }



        }
        return passwordString.toString();
    }

    //Initialize map with common middle characters
    private void initMiddleTrigram(){
        middleTrigram.put("TH",Arrays.asList('E','A','I','O','R'));
        middleTrigram.put("AN",Arrays.asList('D','T','Y','C','S','G','N','I','O','E','A','K'));
        middleTrigram.put("IN",Arrays.asList('G','T','E','D','S','C','A','I','K','V','U','N','F'));
        middleTrigram.put("IO",Arrays.asList('N','U','R'));
        middleTrigram.put("EN",Arrays.asList('T','C','D','S','E','I','G','O','N','A'));
        middleTrigram.put("TI",Arrays.asList('O','N','C','V','M','L','E','T','S','A','R','F'));
        middleTrigram.put("FO",Arrays.asList('R','U','O','L'));
        middleTrigram.put("HE",Arrays.asList('R','N','Y','S','M','I','A','L','D','T'));
        middleTrigram.put("HA",Arrays.asList('T','D','V','N','S','R','P','L'));
        middleTrigram.put("HI",Arrays.asList('S','N','C','M','L','P','G','T','R','E'));
        middleTrigram.put("TE",Arrays.asList('R','D','N','S','M','L','E','C','A'));
        middleTrigram.put("AT",Arrays.asList('I','E','T','H','U','O','C'));
        middleTrigram.put("ER",Arrays.asList('E','S','I','A','N','Y','T','V','M','R','O','L','G','F','C'));
        middleTrigram.put("AL",Arrays.asList('L','S','I','T','E','U','O','M','K','F','A'));
        middleTrigram.put("WA",Arrays.asList('S','Y','R','T','N','L'));
        middleTrigram.put("VE",Arrays.asList('R','N','L','S','D'));
        middleTrigram.put("CO",Arrays.asList('N','M','U','R','L','V','S','O'));
        middleTrigram.put("RE",Arrays.asList('S','A','D','N','E','C','L','T','P','M','V','G','F','Q'));
        middleTrigram.put("IT",Arrays.asList('H','I','Y','E','S','T','A','U'));
        middleTrigram.put("WI",Arrays.asList('T','L','N','S'));
        middleTrigram.put("ME",Arrays.asList('N','R','D','T','S','M','A'));
        middleTrigram.put("NC",Arrays.asList('E','I','H','T','R','O','L'));
        middleTrigram.put("ON",Arrays.asList('S','E','T','G','A','D','L','C','V','O','I','F'));
        middleTrigram.put("PR",Arrays.asList('O','E','I','A'));
        middleTrigram.put("AR",Arrays.asList('E','T','D','Y','S','I','R','L','M','K','G','A','O','N','C'));
        middleTrigram.put("ES",Arrays.asList('S','T','E','I','P','U','C'));
        middleTrigram.put("EV",Arrays.asList('E','I'));
        middleTrigram.put("ST",Arrays.asList('A','R','I','E','O','U','S'));
        middleTrigram.put("EA",Arrays.asList('R','S','T','D','L','C','N','V','M','K'));
        middleTrigram.put("IV",Arrays.asList('E','I','A'));
        middleTrigram.put("EC",Arrays.asList('T','O','I','E','A','U','R','H'));
        middleTrigram.put("NO",Arrays.asList('T','W','R','U','N','M'));
        middleTrigram.put("OU",Arrays.asList('T','L','R','N','S','G','P','B'));
        middleTrigram.put("PE",Arrays.asList('R','N','C','A','D','T','O'));
        middleTrigram.put("IL",Arrays.asList('L','E','I','Y','D','A'));
        middleTrigram.put("IS",Arrays.asList('T','H','S','I','E','C','M'));
        middleTrigram.put("MA",Arrays.asList('N','T','L','K','D','S','I','G'));
        middleTrigram.put("AV",Arrays.asList('E','I','A'));
        middleTrigram.put("OM",Arrays.asList('E','P','M','I','A'));
        middleTrigram.put("IC",Arrays.asList('A','H','E','I','T','K','U','S'));
        middleTrigram.put("GH",Arrays.asList('T'));
        middleTrigram.put("DE",Arrays.asList('R','N','S','D','A','V','P','T','M','L','F'));
        middleTrigram.put("AI",Arrays.asList('N','D','R','L','T'));
        middleTrigram.put("CT",Arrays.asList('I','E','U','S','O'));
        middleTrigram.put("IG",Arrays.asList('H','N','I'));
        middleTrigram.put("ID",Arrays.asList('E'));
        middleTrigram.put("OR",Arrays.asList('E','T','M','D','S','K','I','Y','L','G','A','R','N','C'));
        middleTrigram.put("OV",Arrays.asList('E','I'));
        middleTrigram.put("UL",Arrays.asList('D','T','A','L'));
        middleTrigram.put("YO",Arrays.asList('U'));
        middleTrigram.put("BU",Arrays.asList('T','S','R','I'));
        middleTrigram.put("RA",Arrays.asList('T','N','L','C','I','M','D','S','R','P','G','B'));
        middleTrigram.put("FR",Arrays.asList('O','E','A'));
        middleTrigram.put("RO",Arrays.asList('M','U','V','P','N','W','S','O','L','D','C','B','A','T','G'));
        middleTrigram.put("WH",Arrays.asList('I','E','O','A'));
        middleTrigram.put("OT",Arrays.asList('H','E','T','I'));
        middleTrigram.put("BL",Arrays.asList('E','I','Y','O','A'));
        middleTrigram.put("NT",Arrays.asList('E','I','S','R','O','A','L','Y','U','H'));
        middleTrigram.put("UN",Arrays.asList('D','T','I','C','G'));
        middleTrigram.put("TR",Arrays.asList('A','I','O','E','U','Y'));
        middleTrigram.put("HO",Arrays.asList('U','W','S','R','L','O','M','T','P','N','D'));
        middleTrigram.put("AC",Arrays.asList('T','E','K','H','C','R','I'));
        middleTrigram.put("TU",Arrays.asList('R','D','A','T'));
        middleTrigram.put("WE",Arrays.asList('R','L','E','V','S','N','A'));
        middleTrigram.put("CA",Arrays.asList('L','N','T','R','U','S','M','P'));
        middleTrigram.put("SH",Arrays.asList('E','O','I','A'));
        middleTrigram.put("UR",Arrays.asList('E','N','T','S','I','A','Y','R','P','C'));
        middleTrigram.put("IE",Arrays.asList('S','N','D','T','W','V','R','L','F'));
        middleTrigram.put("PA",Arrays.asList('R','T','S','N','L','I','C'));
        middleTrigram.put("TO",Arrays.asList('R','O','N','W','P','M','L'));
        middleTrigram.put("EE",Arrays.asList('N','D','T','M','S','R','P','L','K'));
        middleTrigram.put("LI",Arrays.asList('N','T','S','C','K','G','E','F','Z','V','O','M','A'));
        middleTrigram.put("RI",Arrays.asList('N','E','C','T','S','G','A','V','O','P','M','L','D','B'));
        middleTrigram.put("UG",Arrays.asList('H','G'));
        middleTrigram.put("AM",Arrays.asList('E','P','I','O','A'));
        middleTrigram.put("ND",Arrays.asList('E','I','S','A','U','O'));
        middleTrigram.put("US",Arrays.asList('E','T','I','S','L','H'));
        middleTrigram.put("LL",Arrays.asList('Y','E','O','I','S','A'));
        middleTrigram.put("AS",Arrays.asList('T','S','E','I','U','O','K','H'));
        middleTrigram.put("TA",Arrays.asList('T','N','L','I','R','K','B','G','C'));
        middleTrigram.put("LE",Arrays.asList('S','D','A','T','C','R','N','M','G','V','F'));
        middleTrigram.put("MO",Arrays.asList('R','S','V','T','U','D'));
        middleTrigram.put("WO",Arrays.asList('R','U'));
        middleTrigram.put("MI",Arrays.asList('N','L','S','T','C','G'));
        middleTrigram.put("AB",Arrays.asList('L','O','I'));
        middleTrigram.put("EL",Arrays.asList('L','Y','I','E','F','O','A','T','S','P','D'));
        middleTrigram.put("IA",Arrays.asList('L','N','T'));
        middleTrigram.put("NA",Arrays.asList('L','T','R','N','M'));
        middleTrigram.put("SS",Arrays.asList('I','E','U','O','A'));
        middleTrigram.put("AG",Arrays.asList('E','A','O'));
        middleTrigram.put("TT",Arrays.asList('E','L','I'));
        middleTrigram.put("NE",Arrays.asList('D','S','W','R','E','Y','V','T','L','C','A'));
        middleTrigram.put("PL",Arrays.asList('A','E','I','Y','O'));
        middleTrigram.put("LA",Arrays.asList('T','N','R','S','C','Y','W','I','B'));
        middleTrigram.put("OS",Arrays.asList('T','E','S','I'));
        middleTrigram.put("CE",Arrays.asList('S','N','R','D','P','L','I'));
        middleTrigram.put("DI",Arrays.asList('S','N','T','D','F','E','C','A','V','R'));
        middleTrigram.put("BE",Arrays.asList('R','E','C','T','L','F','S','I','G','D','A'));
        middleTrigram.put("AP",Arrays.asList('P','E','A'));
        middleTrigram.put("SI",Arrays.asList('O','N','D','T','S','G','C','B','V','M','A'));
        middleTrigram.put("NI",Arrays.asList('N','T','S','C','Z','O','G','F'));
        middleTrigram.put("OW",Arrays.asList('N','E','S','I','A'));
        middleTrigram.put("SO",Arrays.asList('N','M','U','L','C','R'));
        middleTrigram.put("AK",Arrays.asList('E','I'));
        middleTrigram.put("CH",Arrays.asList('E','A','I','O','U','R'));
        middleTrigram.put("EM",Arrays.asList('E','S','P','O','B','A','I'));
        middleTrigram.put("IM",Arrays.asList('E','P','I','A','S','M'));
        middleTrigram.put("SE",Arrays.asList('D','N','L','S','R','E','C','T','V','A'));
        middleTrigram.put("NS",Arrays.asList('T','I','E'));
        middleTrigram.put("PO",Arrays.asList('S','R','N','L','W','T','I'));
        middleTrigram.put("EI",Arrays.asList('R','N','G','T'));
        middleTrigram.put("EX",Arrays.asList('P','T','I','C','A'));
        middleTrigram.put("KT",Arrays.asList('N'));
        middleTrigram.put("UC",Arrays.asList('H','T','K','E'));
        middleTrigram.put("AD",Arrays.asList('E','I','Y','V','M','D'));
        middleTrigram.put("GR",Arrays.asList('E','A','O'));
        middleTrigram.put("IR",Arrays.asList('E','S','T','L','I'));
        middleTrigram.put("NG",Arrays.asList('E','S','L','T','R','I'));
        middleTrigram.put("OP",Arrays.asList('E','P','L'));
        middleTrigram.put("SP",Arrays.asList('E','O','I','A'));
        middleTrigram.put("OL",Arrays.asList('D','L','I','O','E','U'));
        middleTrigram.put("DA",Arrays.asList('Y','T','R','N'));
        middleTrigram.put("NL",Arrays.asList('Y'));
        middleTrigram.put("TL",Arrays.asList('Y','E'));
        middleTrigram.put("LO",Arrays.asList('W','N','O','S','C','V','U','T','R','P','G'));
        middleTrigram.put("BO",Arrays.asList('U','T','R','O','D','A'));
        middleTrigram.put("RS",Arrays.asList('T','E','O','I'));
        middleTrigram.put("FE",Arrays.asList('R','E','W','L','C','A'));
        middleTrigram.put("FI",Arrays.asList('R','N','C','E','L','G'));
        middleTrigram.put("SU",Arrays.asList('R','C','P','B','M','L','A'));
        middleTrigram.put("GE",Arrays.asList('N','T','S','R','D'));
        middleTrigram.put("MP",Arrays.asList('L','O','A','T','R','E'));
        middleTrigram.put("UA",Arrays.asList('L','T','R'));
        middleTrigram.put("OO",Arrays.asList('K','D','L','T','R','N','M'));
        middleTrigram.put("RT",Arrays.asList('I','H','A','E','Y','U','S'));
        middleTrigram.put("SA",Arrays.asList('I','M','Y','N','L'));
        middleTrigram.put("CR",Arrays.asList('E','I','O','A'));
        middleTrigram.put("FF",Arrays.asList('E','I'));
        middleTrigram.put("IK",Arrays.asList('E'));
        middleTrigram.put("MB",Arrays.asList('E'));
        middleTrigram.put("KE",Arrays.asList('D','N','T','S','R','E'));
        middleTrigram.put("FA",Arrays.asList('C','R','M','I'));
        middleTrigram.put("CI",Arrays.asList('A','T','E','S','P','N'));
        middleTrigram.put("EQ",Arrays.asList('U'));
        middleTrigram.put("AF",Arrays.asList('T','F'));
        middleTrigram.put("ET",Arrays.asList('T','I','H','E','Y','W','S','A'));
        middleTrigram.put("AY",Arrays.asList('S','E'));
        middleTrigram.put("MU",Arrays.asList('S','N','L','C'));
        middleTrigram.put("UE",Arrays.asList('S','N'));
        middleTrigram.put("HR",Arrays.asList('O','E','I'));
        middleTrigram.put("TW",Arrays.asList('O','E'));
        middleTrigram.put("GI",Arrays.asList('N','V','O','C'));
        middleTrigram.put("OI",Arrays.asList('N'));
        middleTrigram.put("VI",Arrays.asList('N','D','S','C','T','O','L','E'));
        middleTrigram.put("CU",Arrays.asList('L','R','T','S'));
        middleTrigram.put("FU",Arrays.asList('L','R','N'));
        middleTrigram.put("ED",Arrays.asList('I','U','E'));
        middleTrigram.put("QU",Arrays.asList('I','E','A'));
        middleTrigram.put("UT",Arrays.asList('I','H','E'));
        middleTrigram.put("RC",Arrays.asList('H','E'));
        middleTrigram.put("OF",Arrays.asList('F','T'));
        middleTrigram.put("CL",Arrays.asList('E','A','U','O'));
        middleTrigram.put("FT",Arrays.asList('E'));
        middleTrigram.put("IZ",Arrays.asList('E','A'));
        middleTrigram.put("PP",Arrays.asList('E','O','R','L'));
        middleTrigram.put("RG",Arrays.asList('E','A'));
        middleTrigram.put("DU",Arrays.asList('C','S','R','A'));
        middleTrigram.put("RM",Arrays.asList('A','S','I','E'));
        middleTrigram.put("YE",Arrays.asList('A','S','D'));
        middleTrigram.put("RL",Arrays.asList('Y','D'));
        middleTrigram.put("DO",Arrays.asList('W','N','M','E'));
        middleTrigram.put("AU",Arrays.asList('T','S'));
        middleTrigram.put("EP",Arrays.asList('T','O','E','A'));
        middleTrigram.put("BA",Arrays.asList('S','C','R','N','L'));
        middleTrigram.put("JU",Arrays.asList('S'));
        middleTrigram.put("RD",Arrays.asList('S','E','I'));
        middleTrigram.put("RU",Arrays.asList('S','N','C'));
        middleTrigram.put("OG",Arrays.asList('R','I'));
        middleTrigram.put("BR",Arrays.asList('O','I','E','A'));
        middleTrigram.put("EF",Arrays.asList('O','F','U','T','E'));
        middleTrigram.put("KN",Arrays.asList('O','E'));
        middleTrigram.put("LS",Arrays.asList('O'));
        middleTrigram.put("GA",Arrays.asList('N','I','T','R'));
        middleTrigram.put("PI",Arrays.asList('N','T','R','E','C'));
        middleTrigram.put("YI",Arrays.asList('N'));
        middleTrigram.put("BI",Arrays.asList('L','T','N'));
        middleTrigram.put("IB",Arrays.asList('L','I','E'));
        middleTrigram.put("UB",Arrays.asList('L'));
        middleTrigram.put("VA",Arrays.asList('L','T','R','N'));
        middleTrigram.put("OC",Arrays.asList('K','I','E','C','A'));
        middleTrigram.put("IF",Arrays.asList('I','F','E','T'));
        middleTrigram.put("RN",Arrays.asList('I','E','M','A'));
        middleTrigram.put("RR",Arrays.asList('I','E','Y','O'));
        middleTrigram.put("SC",Arrays.asList('H','R','O','I','A'));
        middleTrigram.put("TC",Arrays.asList('H'));
        middleTrigram.put("CK",Arrays.asList('E'));
        middleTrigram.put("DG",Arrays.asList('E'));
        middleTrigram.put("DR",Arrays.asList('E','O','I','A'));
        middleTrigram.put("MM",Arrays.asList('E','U','I'));
        middleTrigram.put("NN",Arrays.asList('E','O','I'));
        middleTrigram.put("OD",Arrays.asList('E','Y','U'));
        middleTrigram.put("RV",Arrays.asList('E','I'));
        middleTrigram.put("UD",Arrays.asList('E','I'));
        middleTrigram.put("XP",Arrays.asList('E'));
        middleTrigram.put("JE",Arrays.asList('C'));
        middleTrigram.put("UM",Arrays.asList('B','E'));
        middleTrigram.put("EG",Arrays.asList('A','R','I','E'));
        middleTrigram.put("DL",Arrays.asList('Y','E'));
        middleTrigram.put("PH",Arrays.asList('Y','O','I','E'));
        middleTrigram.put("SL",Arrays.asList('Y','A'));
        middleTrigram.put("GO",Arrays.asList('V','T','O'));
        middleTrigram.put("CC",Arrays.asList('U','O','E'));
        middleTrigram.put("LU",Arrays.asList('T','S','M','E','D'));
        middleTrigram.put("OA",Arrays.asList('T','R','D'));
        middleTrigram.put("PU",Arrays.asList('T','R','L','B'));
        middleTrigram.put("UI",Arrays.asList('T','R','L'));
        middleTrigram.put("YS",Arrays.asList('T'));
        middleTrigram.put("ZA",Arrays.asList('T'));
        middleTrigram.put("HU",Arrays.asList('S','R','N','M'));
        middleTrigram.put("MR",Arrays.asList('S'));
        middleTrigram.put("OE",Arrays.asList('S'));
        middleTrigram.put("SY",Arrays.asList('S'));
        middleTrigram.put("EO",Arrays.asList('R','P'));
        middleTrigram.put("TY",Arrays.asList('P'));
        middleTrigram.put("UP",Arrays.asList('P','O'));
        middleTrigram.put("FL",Arrays.asList('O','E'));
        middleTrigram.put("LM",Arrays.asList('O'));
        middleTrigram.put("NF",Arrays.asList('O'));
        middleTrigram.put("RP",Arrays.asList('O'));
        middleTrigram.put("OH",Arrays.asList('N'));
        middleTrigram.put("NU",Arrays.asList('M'));
        middleTrigram.put("XA",Arrays.asList('M'));
        middleTrigram.put("OB",Arrays.asList('L'));
        middleTrigram.put("VO",Arrays.asList('L'));
        middleTrigram.put("DM",Arrays.asList('I'));
        middleTrigram.put("GN",Arrays.asList('I'));
        middleTrigram.put("LD",Arrays.asList('I','E'));
        middleTrigram.put("PT",Arrays.asList('I'));
        middleTrigram.put("SK",Arrays.asList('I','E'));
        middleTrigram.put("WR",Arrays.asList('I'));
        middleTrigram.put("JO",Arrays.asList('H'));
        middleTrigram.put("LT",Arrays.asList('H','E'));
        middleTrigram.put("YT",Arrays.asList('H'));
        middleTrigram.put("UF",Arrays.asList('F'));
        middleTrigram.put("BJ",Arrays.asList('E'));
        middleTrigram.put("DD",Arrays.asList('E'));
        middleTrigram.put("EY",Arrays.asList('E'));
        middleTrigram.put("GG",Arrays.asList('E'));
        middleTrigram.put("GL",Arrays.asList('E','A'));
        middleTrigram.put("GU",Arrays.asList('E'));
        middleTrigram.put("HT",Arrays.asList('E'));
        middleTrigram.put("LV",Arrays.asList('E'));
        middleTrigram.put("MS",Arrays.asList('E'));
        middleTrigram.put("NM",Arrays.asList('E'));
        middleTrigram.put("NV",Arrays.asList('E'));
        middleTrigram.put("OK",Arrays.asList('E'));
        middleTrigram.put("PM",Arrays.asList('E'));
        middleTrigram.put("RK",Arrays.asList('E'));
        middleTrigram.put("SW",Arrays.asList('E'));
        middleTrigram.put("TM",Arrays.asList('E'));
        middleTrigram.put("XC",Arrays.asList('E'));
        middleTrigram.put("ZE",Arrays.asList('D'));
        middleTrigram.put("AW",Arrays.asList('A'));
        middleTrigram.put("SM",Arrays.asList('A'));
    }

    //Initialize map with common word endings.
    private void initFinalTrigram(){
        finalTrigram.put("TH",Arrays.asList('E','O'));
        finalTrigram.put("AN",Arrays.asList('D','T','Y','S','G','O','E','A','K'));
        finalTrigram.put("IN",Arrays.asList('G','T','E','D','S','C','A','I','K','V','U','N','F'));
        finalTrigram.put("IO",Arrays.asList('N','U','R'));
        finalTrigram.put("EN",Arrays.asList('T','D','S','E','G','O','A'));
        finalTrigram.put("TI",Arrays.asList('N','C','M','L','E','T','S','A','R','F'));
        finalTrigram.put("FO",Arrays.asList('R','U','O','L'));
        finalTrigram.put("HE",Arrays.asList('R','N','Y','S','M','A','L','D','T'));
        finalTrigram.put("HA",Arrays.asList('T','D','N','S','R','L'));
        finalTrigram.put("HI",Arrays.asList('S','N','C','M','L','P','G','T','R','E'));
        finalTrigram.put("TE",Arrays.asList('R','D','N','S','M','L','E','A'));
        finalTrigram.put("AT",Arrays.asList('E','H','O'));
        finalTrigram.put("ER",Arrays.asList('E','S','A','N','Y','T','M'));
        finalTrigram.put("AL",Arrays.asList('L','S','T','E','F'));
        finalTrigram.put("WA",Arrays.asList('S','Y','R','T','N','L'));
        finalTrigram.put("VE",Arrays.asList('R','N','L','S','D'));
        finalTrigram.put("CO",Arrays.asList('N','M','U','R','L','O'));
        finalTrigram.put("RE",Arrays.asList('S','A','D','N','E','L','T','P','M'));
        finalTrigram.put("IT",Arrays.asList('H','Y','E','S','A'));
        finalTrigram.put("WI",Arrays.asList('T','L','N','S'));
        finalTrigram.put("ME",Arrays.asList('N','R','D','T','S','M','A'));
        finalTrigram.put("NC",Arrays.asList('E','H','T'));
        finalTrigram.put("ON",Arrays.asList('S','E','T','G','A','D','O'));
        finalTrigram.put("PR",Arrays.asList('E','A'));
        finalTrigram.put("AR",Arrays.asList('E','T','D','Y','S','M','K','A','N'));
        finalTrigram.put("ES",Arrays.asList('S','T','E'));
        finalTrigram.put("EV",Arrays.asList('E'));
        finalTrigram.put("ST",Arrays.asList('A','E','O','S'));
        finalTrigram.put("EA",Arrays.asList('R','S','T','D','L','N','M'));
        finalTrigram.put("IV",Arrays.asList('A'));
        finalTrigram.put("EC",Arrays.asList('T','E','H'));
        finalTrigram.put("NO",Arrays.asList('T','W','R','U','N','M'));
        finalTrigram.put("OU",Arrays.asList('T','L','R','N','S','P'));
        finalTrigram.put("PE",Arrays.asList('R','N','A','D','T'));
        finalTrigram.put("IL",Arrays.asList('L','E','Y','D'));
        finalTrigram.put("IS",Arrays.asList('T','H','S','E','M'));
        finalTrigram.put("MA",Arrays.asList('N','T','L','D','S'));
        finalTrigram.put("AV",Arrays.asList('E'));
        finalTrigram.put("OM",Arrays.asList('E'));
        finalTrigram.put("IC",Arrays.asList('H','E','T','K','S'));
        finalTrigram.put("GH",Arrays.asList('T'));
        finalTrigram.put("DE",Arrays.asList('R','N','S','D','A','P','T','M','L'));
        finalTrigram.put("AI",Arrays.asList('N','D','R','L','T'));
        finalTrigram.put("CT",Arrays.asList('E','S','O'));
        finalTrigram.put("IG",Arrays.asList('H','N'));
        finalTrigram.put("ID",Arrays.asList('E'));
        finalTrigram.put("OR",Arrays.asList('E','T','M','D','S','K','Y','A','N'));
        finalTrigram.put("OV",Arrays.asList('E'));
        finalTrigram.put("UL",Arrays.asList('D','T','L'));
        finalTrigram.put("YO",Arrays.asList('U'));
        finalTrigram.put("BU",Arrays.asList('T','S','R'));
        finalTrigram.put("RA",Arrays.asList('T','N','L','M','D','S','R'));
        finalTrigram.put("FR",Arrays.asList('E','A'));
        finalTrigram.put("RO",Arrays.asList('M','U','P','N','W','O','L','D','T'));
        finalTrigram.put("WH",Arrays.asList('E','O'));
        finalTrigram.put("OT",Arrays.asList('H','E'));
        finalTrigram.put("BL",Arrays.asList('E','Y'));
        finalTrigram.put("NT",Arrays.asList('E','S','O','A','Y','H'));
        finalTrigram.put("UN",Arrays.asList('D','T','G'));
        finalTrigram.put("TR",Arrays.asList('A','E','Y'));
        finalTrigram.put("HO",Arrays.asList('U','W','R','L','O','M','T','P','N','D'));
        finalTrigram.put("AC",Arrays.asList('T','E','K','H'));
        finalTrigram.put("TU",Arrays.asList('R','T'));
        finalTrigram.put("WE",Arrays.asList('R','L','E','S','N','A'));
        finalTrigram.put("CA",Arrays.asList('L','N','T','R','S','M'));
        finalTrigram.put("SH",Arrays.asList('E','O'));
        finalTrigram.put("UR",Arrays.asList('E','N','T','S','A','Y'));
        finalTrigram.put("IE",Arrays.asList('S','N','D','T','W','R','L'));
        finalTrigram.put("PA",Arrays.asList('R','T','S','N','L'));
        finalTrigram.put("TO",Arrays.asList('R','O','N','W','P','M','L'));
        finalTrigram.put("EE",Arrays.asList('N','D','T','M','S','R','P','L','K'));
        finalTrigram.put("LI",Arrays.asList('N','T','S','C','G','E','F','M','A'));
        finalTrigram.put("RI",Arrays.asList('N','E','C','T','S','G','A','P','M','L','D'));
        finalTrigram.put("UG",Arrays.asList('H'));
        finalTrigram.put("AM",Arrays.asList('E'));
        finalTrigram.put("ND",Arrays.asList('E','S','O'));
        finalTrigram.put("US",Arrays.asList('E','T','S','H'));
        finalTrigram.put("LL",Arrays.asList('Y','E','S'));
        finalTrigram.put("AS",Arrays.asList('T','S','E','O','H'));
        finalTrigram.put("TA",Arrays.asList('T','N','L','R'));
        finalTrigram.put("LE",Arrays.asList('S','D','A','T','R','N','M'));
        finalTrigram.put("MO",Arrays.asList('R','T','U','D'));
        finalTrigram.put("WO",Arrays.asList('R','U'));
        finalTrigram.put("MI",Arrays.asList('N','L','S','T','C','G'));
        finalTrigram.put("AB",Arrays.asList());
        finalTrigram.put("EL",Arrays.asList('L','Y','E','F','T','S','D'));
        finalTrigram.put("IA",Arrays.asList('L','N','T'));
        finalTrigram.put("NA",Arrays.asList('L','T','R','N','M'));
        finalTrigram.put("SS",Arrays.asList('E','O'));
        finalTrigram.put("AG",Arrays.asList('E','O'));
        finalTrigram.put("TT",Arrays.asList('E'));
        finalTrigram.put("NE",Arrays.asList('D','S','W','R','E','Y','T','L','A'));
        finalTrigram.put("PL",Arrays.asList('E','Y'));
        finalTrigram.put("LA",Arrays.asList('T','N','R','S','Y','W'));
        finalTrigram.put("OS",Arrays.asList('T','E','S'));
        finalTrigram.put("CE",Arrays.asList('S','N','R','D','P','L'));
        finalTrigram.put("DI",Arrays.asList('S','N','T','D','F','E','C','A','R'));
        finalTrigram.put("BE",Arrays.asList('R','E','T','L','S','D','A'));
        finalTrigram.put("AP",Arrays.asList('E'));
        finalTrigram.put("SI",Arrays.asList('N','D','T','S','G','C','M','A'));
        finalTrigram.put("NI",Arrays.asList('N','T','S','C','G','F'));
        finalTrigram.put("OW",Arrays.asList('N','E','S'));
        finalTrigram.put("SO",Arrays.asList('N','M','U','L','R'));
        finalTrigram.put("AK",Arrays.asList('E','E'));
        finalTrigram.put("CH",Arrays.asList('E','O'));
        finalTrigram.put("EM",Arrays.asList('E','S'));
        finalTrigram.put("IM",Arrays.asList('E','S'));
        finalTrigram.put("SE",Arrays.asList('D','N','L','S','R','E','T','A'));
        finalTrigram.put("NS",Arrays.asList('T','E'));
        finalTrigram.put("PO",Arrays.asList('R','N','L','W','T'));
        finalTrigram.put("EI",Arrays.asList('R','N','G','T'));
        finalTrigram.put("EX",Arrays.asList('T'));
        finalTrigram.put("KT",Arrays.asList('N'));
        finalTrigram.put("UC",Arrays.asList('H','T','K','E'));
        finalTrigram.put("AD",Arrays.asList('E','Y'));
        finalTrigram.put("GR",Arrays.asList('E','A'));
        finalTrigram.put("IR",Arrays.asList('E','S','T'));
        finalTrigram.put("NG",Arrays.asList('E','S'));
        finalTrigram.put("OP",Arrays.asList('E'));
        finalTrigram.put("SP",Arrays.asList('E'));
        finalTrigram.put("OL",Arrays.asList('D','L','E'));
        finalTrigram.put("DA",Arrays.asList('Y','T','R','N'));
        finalTrigram.put("NL",Arrays.asList('Y'));
        finalTrigram.put("TL",Arrays.asList('Y','E'));
        finalTrigram.put("LO",Arrays.asList('W','N','O','U','T','R','P'));
        finalTrigram.put("BO",Arrays.asList('U','T','R','O','D'));
        finalTrigram.put("RS",Arrays.asList('T','E','O'));
        finalTrigram.put("FE",Arrays.asList('R','E','W','L','A'));
        finalTrigram.put("FI",Arrays.asList('R','N','C','E','L','G'));
        finalTrigram.put("SU",Arrays.asList('R','P','M','L'));
        finalTrigram.put("GE",Arrays.asList('N','T','S','R','D'));
        finalTrigram.put("MP",Arrays.asList('T','E'));
        finalTrigram.put("UA",Arrays.asList('L','T','R'));
        finalTrigram.put("OO",Arrays.asList('K','D','L','T','R','N','M'));
        finalTrigram.put("RT",Arrays.asList('H','A','E','Y','S'));
        finalTrigram.put("SA",Arrays.asList('M','Y','N','L'));
        finalTrigram.put("CR",Arrays.asList('E','A'));
        finalTrigram.put("FF",Arrays.asList('E'));
        finalTrigram.put("IK",Arrays.asList('E'));
        finalTrigram.put("MB",Arrays.asList('E'));
        finalTrigram.put("KE",Arrays.asList('D','N','T','S','R','E'));
        finalTrigram.put("FA",Arrays.asList('R','M'));
        finalTrigram.put("CI",Arrays.asList('A','T','E','S','P','N'));
        finalTrigram.put("EQ",Arrays.asList());
        finalTrigram.put("AF",Arrays.asList('T','F'));
        finalTrigram.put("ET",Arrays.asList('H','E','Y','S','A'));
        finalTrigram.put("AY",Arrays.asList('S'));
        finalTrigram.put("MU",Arrays.asList('S','N','L'));
        finalTrigram.put("UE",Arrays.asList('S','N'));
        finalTrigram.put("HR",Arrays.asList('E'));
        finalTrigram.put("TW",Arrays.asList('O','E'));
        finalTrigram.put("GI",Arrays.asList('N','C'));
        finalTrigram.put("OI",Arrays.asList('N'));
        finalTrigram.put("VI",Arrays.asList('N','D','S','C','T','L','E'));
        finalTrigram.put("CU",Arrays.asList('L','R','T','S'));
        finalTrigram.put("FU",Arrays.asList('L','R','N'));
        finalTrigram.put("ED",Arrays.asList('E'));
        finalTrigram.put("QU",Arrays.asList('E'));
        finalTrigram.put("UT",Arrays.asList('H','E'));
        finalTrigram.put("RC",Arrays.asList('H','E'));
        finalTrigram.put("OF",Arrays.asList('F','T'));
        finalTrigram.put("CL",Arrays.asList('E'));
        finalTrigram.put("FT",Arrays.asList('E'));
        finalTrigram.put("IZ",Arrays.asList('E'));
        finalTrigram.put("PP",Arrays.asList('E'));
        finalTrigram.put("RG",Arrays.asList('E'));
        finalTrigram.put("DU",Arrays.asList('S','R'));
        finalTrigram.put("RM",Arrays.asList('S','E'));
        finalTrigram.put("YE",Arrays.asList('A','S','D'));
        finalTrigram.put("RL",Arrays.asList('Y','D'));
        finalTrigram.put("DO",Arrays.asList('W','N','M'));
        finalTrigram.put("AU",Arrays.asList('T','S'));
        finalTrigram.put("EP",Arrays.asList('T','E'));
        finalTrigram.put("BA",Arrays.asList('S','R','N','L'));
        finalTrigram.put("JU",Arrays.asList('S'));
        finalTrigram.put("RD",Arrays.asList('S','E'));
        finalTrigram.put("RU",Arrays.asList('S','N'));
        finalTrigram.put("OG",Arrays.asList());
        finalTrigram.put("BR",Arrays.asList('E','A'));
        finalTrigram.put("EF",Arrays.asList('F','T','E'));
        finalTrigram.put("KN",Arrays.asList('O','E'));
        finalTrigram.put("LS",Arrays.asList('O'));
        finalTrigram.put("GA",Arrays.asList('N','T','R'));
        finalTrigram.put("PI",Arrays.asList('N','T','R','E','C'));
        finalTrigram.put("YI",Arrays.asList('N'));
        finalTrigram.put("BI",Arrays.asList('L','T','N'));
        finalTrigram.put("IB",Arrays.asList('E'));
        finalTrigram.put("UB",Arrays.asList());
        finalTrigram.put("VA",Arrays.asList('L','T','R','N'));
        finalTrigram.put("OC",Arrays.asList('K','E'));
        finalTrigram.put("IF",Arrays.asList('F','E','T'));
        finalTrigram.put("RN",Arrays.asList('E','A'));
        finalTrigram.put("RR",Arrays.asList('E','Y'));
        finalTrigram.put("SC",Arrays.asList('H'));
        finalTrigram.put("TC",Arrays.asList('H'));
        finalTrigram.put("CK",Arrays.asList('E'));
        finalTrigram.put("DG",Arrays.asList('E'));
        finalTrigram.put("DR",Arrays.asList('E','A'));
        finalTrigram.put("MM",Arrays.asList('E'));
        finalTrigram.put("NN",Arrays.asList('E','O'));
        finalTrigram.put("OD",Arrays.asList('E','Y'));
        finalTrigram.put("RV",Arrays.asList('E'));
        finalTrigram.put("UD",Arrays.asList('E'));
        finalTrigram.put("XP",Arrays.asList('E'));
        finalTrigram.put("JE",Arrays.asList());
        finalTrigram.put("UM",Arrays.asList('E'));
        finalTrigram.put("EG",Arrays.asList('E'));
        finalTrigram.put("DL",Arrays.asList('Y','E'));
        finalTrigram.put("PH",Arrays.asList('Y','O','E'));
        finalTrigram.put("SL",Arrays.asList('Y'));
        finalTrigram.put("GO",Arrays.asList('T','O'));
        finalTrigram.put("CC",Arrays.asList('E'));
        finalTrigram.put("LU",Arrays.asList('T','S','M','E'));
        finalTrigram.put("OA",Arrays.asList('T','R','D'));
        finalTrigram.put("PU",Arrays.asList('T','R','L'));
        finalTrigram.put("UI",Arrays.asList('T','R','L'));
        finalTrigram.put("YS",Arrays.asList('T'));
        finalTrigram.put("ZA",Arrays.asList('T'));
        finalTrigram.put("HU",Arrays.asList('S','R','N','M'));
        finalTrigram.put("MR",Arrays.asList('S'));
        finalTrigram.put("OE",Arrays.asList('S'));
        finalTrigram.put("SY",Arrays.asList('S'));
        finalTrigram.put("EO",Arrays.asList('R','P'));
        finalTrigram.put("TY",Arrays.asList());
        finalTrigram.put("UP",Arrays.asList());
        finalTrigram.put("FL",Arrays.asList('E'));
        finalTrigram.put("LM",Arrays.asList());
        finalTrigram.put("NF",Arrays.asList());
        finalTrigram.put("RP",Arrays.asList());
        finalTrigram.put("OH",Arrays.asList());
        finalTrigram.put("NU",Arrays.asList('M'));
        finalTrigram.put("XA",Arrays.asList('M'));
        finalTrigram.put("OB",Arrays.asList());
        finalTrigram.put("VO",Arrays.asList('L'));
        finalTrigram.put("DM",Arrays.asList());
        finalTrigram.put("GN",Arrays.asList());
        finalTrigram.put("LD",Arrays.asList('E'));
        finalTrigram.put("PT",Arrays.asList());
        finalTrigram.put("SK",Arrays.asList('E'));
        finalTrigram.put("WR",Arrays.asList());
        finalTrigram.put("JO",Arrays.asList());
        finalTrigram.put("LT",Arrays.asList('H','E'));
        finalTrigram.put("YT",Arrays.asList('H'));
        finalTrigram.put("UF",Arrays.asList('F'));
        finalTrigram.put("BJ",Arrays.asList());
        finalTrigram.put("DD",Arrays.asList('E'));
        finalTrigram.put("EY",Arrays.asList());
        finalTrigram.put("GG",Arrays.asList('E'));
        finalTrigram.put("GL",Arrays.asList('E'));
        finalTrigram.put("GU",Arrays.asList('E'));
        finalTrigram.put("HT",Arrays.asList('E'));
        finalTrigram.put("LV",Arrays.asList('E'));
        finalTrigram.put("MS",Arrays.asList('E'));
        finalTrigram.put("NM",Arrays.asList('E'));
        finalTrigram.put("NV",Arrays.asList('E'));
        finalTrigram.put("OK",Arrays.asList('E'));
        finalTrigram.put("PM",Arrays.asList('E'));
        finalTrigram.put("RK",Arrays.asList('E'));
        finalTrigram.put("SW",Arrays.asList('E'));
        finalTrigram.put("TM",Arrays.asList('E'));
        finalTrigram.put("XC",Arrays.asList('E'));
        finalTrigram.put("ZE",Arrays.asList('D'));
        finalTrigram.put("AW",Arrays.asList());
        finalTrigram.put("SM",Arrays.asList());
    }

    //Alters string based on what case is selected
    public String generatePassword(int passwordLength, boolean uppercaseAllowed, boolean lowercaseAllowed){

        String password = generateWord(passwordLength);
        Random random = new Random();
        char[] temp = password.toCharArray();

        if(uppercaseAllowed && lowercaseAllowed){
            for(int i = 0; i < password.length();i++){
                if(random.nextDouble() > .5){
                    temp[i] = Character.toLowerCase(temp[i]);
                }
            }
            password = new String(temp);
        } else if (lowercaseAllowed){
            return password.toLowerCase();
        }
        return password;
    }
}
