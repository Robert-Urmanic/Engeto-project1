package com.engeto.urm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileDeconstruction {

    // This class will deconstruct the whole file and construct 5 ArrayLists with specific values

    private List<String> countryShortName = new ArrayList<>();
    private List<String> countryName = new ArrayList<>();
    private List<Integer> countryDPH = new ArrayList<>();
    private List<Double> countryDPHLowered = new ArrayList<>();
    private List<Boolean> countryUsingDPH = new ArrayList<>();

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
        // TODO: 17.11.2021 stupid, redundant, fix - figure out a loop
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
        }
        return print;
    }

    public String toStringWithRestrictions() {
        String print = "";
        for (int i = 0; i < countryShortName.size(); i++) {
            if ((countryDPH.get(i).compareTo(Integer.valueOf(20)) > 0) && countryUsingDPH.get(i) == false) {
                print = print + countryName.get(i) + " (" + countryShortName.get(i) + "): " + countryDPH.get(i) + "%\n";
            }
        }
        return print;
    }

    public void sortTest(){
        Collections.sort(countryDPH, Collections.reverseOrder());
        for (int i = 0; i < countryDPH.size(); i++) {
            System.out.println(countryDPH.get(i));
        }

    }

}

// TODO: 17.11.2021 while loop which will let user choose what to do, the user can choose to print, print with restrictions, print sorted toString