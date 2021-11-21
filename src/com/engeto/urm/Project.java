package com.engeto.urm;

import javax.sound.midi.Soundbank;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Project {
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
            inputChecker(f, s);
        } else System.out.println("VAT stays at default 20%...");
        System.out.println("Choose what you wish to do from the following: ");
        System.out.println("1. Write out entire file\n\t\t\t(number 2 requirement in project description)");
        System.out.println("2. Write out file which has been filtered by VAT and special prices\n\t\t\t(number 3 requirement in project description)");
        System.out.println("3. Write out sorted and filtered states.\n\t\t\t(number 4 requirement in project description)");
        System.out.println("4. Write out sorted and filtered states and include unfiltered ones as well. \n\t\t\t(number 5 requirement in project description)");
        System.out.println("5. Create a new text file with filtered info\n\t\t\t(number 6 requirement in project description)");
        System.out.println("6. Change the VAT rate\n\t\t\t(number 7 requirement in project description)");
        System.out.println("7. Exit");

        boolean b = true;

        while (b) {
            Scanner s2 = new Scanner(System.in);
            int temp = 10;
            try{
                temp = s2.nextInt();
            }catch (InputMismatchException ex){
                System.err.println("You can only type in numbers");
            }
            // had to create new Scanner, program mě zde nenechal jít dál, když jsem naschvál po odsouhlašení změny DPH napsal charakter...
            switch (temp) {
                case 1:
                    System.out.println(f.toStringFullList());
                    System.out.println();
                    System.out.println("Choose again, or type any character if you need the key: ");
                    break;
                case 2:
                    System.out.println(f.toStringWithRestrictions());
                    System.out.println();
                    System.out.println("Choose again, or type any character if you need the key: ");
                    break;
                case 3:
                    System.out.println(f.toStringSortedDescendingDPH());
                    System.out.println();
                    System.out.println("Choose again, or type any character if you need the key: ");
                    break;
                case 4:
                    System.out.println(f.toStringSortedWithExceptionsWithUnusedStates());
                    System.out.println();
                    System.out.println("Choose again, or type any character if you need the key: ");
                    break;
                case 5:
                    FileCreation.setFile(f);
                    System.out.println();
                    System.out.println("Choose again, or type any character if you need the key: ");
                    break;
                case 6:
                    System.out.println("Enter desired VAT limit: ");
                    inputChecker(f,s2);
                    System.out.println("VAT limit set to: " + f.getFilterDPH());
                    System.out.println();
                    System.out.println("Choose again, or type any character if you need the key: ");
                    break;
                case 7:
                    b = false;
                    break;
                default:
                    System.out.println("It seems that you have mistyped a number.\nPlease choose a number from 1 to 7 accordingly: ");
                    System.out.println("1. Write out entire file\n\t\t\t(number 2 requirement in project description)");
                    System.out.println("2. Write out file which has been filtered by VAT and special prices\n\t\t\t(number 3 requirement in project description)");
                    System.out.println("3. Write out sorted and filtered states.\n\t\t\t(number 4 requirement in project description)");
                    System.out.println("4. Write out sorted and filtered states and include unfiltered ones as well. \n\t\t\t(number 5 requirement in project description)");
                    System.out.println("5. Create a new text file with filtered info\n\t\t\t(number 6 requirement in project description)");
                    System.out.println("6. Change the VAT rate\n\t\t\t(number 7 requirement in project description)");
                    System.out.println("7. Exit");
            }
        }

        System.out.println("======================================================================");
        System.out.println("Goodbye and thank you for the first 6 lessons of Java engeto academy");
        System.out.println("======================================================================");

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

    private static void inputChecker(FileDeconstruction f, Scanner s) {
        try {
            int tempInt = 20;
            try{
                tempInt = s.nextInt();
            }catch(InputMismatchException ex){
                System.err.println("Scanner can only accept int, value of VAC was set to 20%");
            }
            f.setFilterDPH(tempInt);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
            //e.printStackTrace();
        }
    }
}
