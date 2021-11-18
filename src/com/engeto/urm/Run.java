package com.engeto.urm;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Run {
    public static void run() {
        System.out.println("Welcome to ENGETO PROJECT 1 - VAT RATES");
        System.out.println("=======================================");

        FileDeconstruction f = new FileDeconstruction();
        f.fileDeconstruction();

        System.out.println("The cut line for VAT rate is currently 20%, do you wish to change that? (Y/n)");
        Scanner s = new Scanner(System.in);
        String tempDecision = s.nextLine();
        if ("Y".equals(tempDecision.toUpperCase())) {
            System.out.println("Enter desired VAT limit: ");
            f.setFilterDPH(s.nextInt());
        } else System.out.println("VAT stays at default 20%...");

        System.out.println("Choose what you wish to do from the following: ");
        System.out.println("1. Write out entire file");
        System.out.println("2. Write out file which has been filtered by VAT and special prices");
        System.out.println("3. Create a new text file with filtered info");
        System.out.println("4. Exit");

        boolean b = true;

        while (b) {
            int temp = s.nextInt();
            switch (temp) {
                case 1:
                    System.out.println(f.toStringFullList());
                    System.out.println();
                    System.out.println("Choose again, please: ");
                    break;
                case 2:
                    System.out.println(f.toStringWithRestrictions());
                    System.out.println();
                    System.out.println("Choose again, please: ");
                    break;
                case 3:
                    FileCreation.setFile(f);
                    System.out.println();
                    System.out.println("Choose again, please: ");
                    break;
                case 4:
                    b = false;
            }
        }

        System.out.println("==============================================");
        System.out.println("Goodbye");
        System.out.println("==============================================");

        /*System.out.println(f.toStringFullList());
        System.out.println("---------------------------");
        System.out.println(f.toStringWithRestrictions());
        System.out.println("---------------------------");
        System.out.println(f.toStringSortedDescendingDPH());
        System.out.println("---------------------------");
        System.out.println(f.toStringSortedWithExceptionsWithUnusedStates());
        FileCreation.setFile(f);

         */
    }
}
