package com.engeto.urm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileCreation {
    public static void setFile(FileDeconstruction f) {
        try (PrintWriter out = new PrintWriter("vat-over-" + f.getFilterDPH() + ".txt")) {
            out.println(f.toStringSortedWithExceptionsWithUnusedStates());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // for some reason file is created only after the application is closed
    }
}
