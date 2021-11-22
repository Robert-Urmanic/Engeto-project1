package com.engeto.urm;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class FileDeconstruction {

    // This class will deconstruct the whole file and construct 5 ArrayLists with specific values (country name, shortened name etc...)

    private List<String> countryShortName = new ArrayList<>();
    private List<String> countryName = new ArrayList<>();
    private List<Integer> countryDPH = new ArrayList<>();
    private List<Double> countryDPHLowered = new ArrayList<>();
    private List<Boolean> countryUsingDPH = new ArrayList<>();

    private static int filterDPH = 20;

    public void setFilterDPH(int filterDPH) throws IllegalArgumentException{
        if(!((filterDPH > 0) && (filterDPH < 100))){
            throw new IllegalArgumentException("\nVAT cannot exceed the value of 100 AND cannot go below the value of 0\nThe VAT value will remain at default 20%");
        }
        this.filterDPH = filterDPH;
    }

    public String getFilterDPH(){
        return "" + filterDPH;
    }
    // Getter and setter used in FileCreation.java

    public void fileDeconstruction() {
        try (Scanner scannerVat = new Scanner(new File("vat-eu.csv"))) {
            // opening file in brackets so that it closes automatically
            while (scannerVat.hasNextLine()) {
                // while there is a next line, repeat
                String nextVatLine = scannerVat.nextLine();
                nextVatLine = nextVatLine.replace(",", ".");
                // replacing incorrect format
                String[] countryComplete = nextVatLine.split("\t");
                // feeding the whole split line into a temporary array of Strings
                feedLists(countryComplete);
                // feeding our first 5 ArrayLists
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void feedLists(String[] countryComplete) {
        // adding information to lists from String[] countryComplete
        countryShortName.add(countryComplete[0]);
        countryName.add(countryComplete[1]);
        countryDPH.add(Integer.parseInt(countryComplete[2]));
        // parseInt, converting String to Integer
        countryDPHLowered.add(Double.parseDouble(countryComplete[3]));
        countryUsingDPH.add(Boolean.parseBoolean(countryComplete[4]));
    }

    public String toStringFullList() {
        String print = "";
        for (int i = 0; i < countryShortName.size(); i++) {
            print = print + countryName.get(i) + " (" + countryShortName.get(i) + "): " + countryDPH.get(i) + "%\n";
            // .size() of any country* variable will do
        }
        return print;
        // printing countries and DPH in desired format
    }

    public String toStringWithRestrictions() {
        String print = "";
        for (int i = 0; i < countryShortName.size(); i++) {
            if ((countryDPH.get(i).compareTo(Integer.valueOf(filterDPH)) > 0) && countryUsingDPH.get(i) == false) {
                // specifying what countries we want to have printed out
                print = print + countryName.get(i) + " (" + countryShortName.get(i) + "):\t" + countryDPH.get(i) + " % "
                        + "(" + new DecimalFormat("#.#").format(countryDPHLowered.get(i)) + " %)\n";
            }
        }
        return print;
        // printing only selected countries in desired format
    }

    public String toStringSortedDescendingDPH() {
        String print = "";
        Integer tempLoop = new Integer(countryDPH.size());
        // had to implement my own bubble sorting algorithm
        // the algorithm sorts by countryDPH
        // we can allocate other information which will always fit because the lists are fed in this way:
        // list DPH = 10 20 30 20
        // list name = CZ SK CR GR
        // list fname = Czech republic Slovakia Croatia Greece

        for (int j = 0; j < tempLoop; j++) {
            for (int i = 0; i < (countryDPH.size() - 1); i++) {
                if (countryDPH.get(i).compareTo(countryDPH.get(i + 1)) < 0) {
                    Integer tempValCountryDPH = countryDPH.get(i + 1);
                    countryDPH.set(i + 1, countryDPH.get(i));
                    countryDPH.set(i, tempValCountryDPH);

                    // if DPH.i is smaller than DPH.i+1
                    // switch the 2 values

                    String tempValCountryShortName = countryShortName.get(i + 1);
                    countryShortName.set(i + 1, countryShortName.get(i));
                    countryShortName.set(i, tempValCountryShortName);

                    String tempValCountryName = countryName.get(i + 1);
                    countryName.set(i + 1, countryName.get(i));
                    countryName.set(i, tempValCountryName);

                    Double tempValCountryDPHLowered = countryDPHLowered.get(i + 1);
                    countryDPHLowered.set(i + 1, countryDPHLowered.get(i));
                    countryDPHLowered.set(i, tempValCountryDPHLowered);

                    // names have to follow the same switching mechanism for desired outcome, names have to match the DPH

                }
            }
        }

        return toStringWithRestrictions();
        // reusing a method
    }

    public String toStringSortedWithExceptionsWithUnusedStates() {
        String print = "\nSazba VAT 20 % nebo nižší nebo používají speciální sazbu: ";
        for (int i = 0; i < countryShortName.size(); i++) {
            if ((countryDPH.get(i).compareTo(Integer.valueOf(filterDPH)) <= 0) || countryUsingDPH.get(i) == true) {
                print = print + countryShortName.get(i) + dot(i, countryShortName.size());
                    // dot method for better aesthetics
            }
        }


        return toStringSortedDescendingDPH() + "======================" + print;
        // reusing method and printing out unchosen countries
    }

    public String dot(int finalInt, int finalLoopInt){
        if ((finalInt + 1) == finalLoopInt){
            return ".";
        }
        else return ", ";
        // replaces the last "," with a "." looks better (y)
    }
}




// TODO: 17.11.2021 while loop which will let user choose what to do, the user can choose to print, print with restrictions, print sorted toString
