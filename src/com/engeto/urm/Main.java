package com.engeto.urm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileDeconstruction f = new FileDeconstruction();
        f.fileDeconstruction();
        System.out.println(f.toStringFullList());
        System.out.println("---------------------------");
        System.out.println(f.toStringWithRestrictions());
        System.out.println("---------------------------");
        f.sortTest();
    }
}

// TODO: 17.11.2021 delete pseudocode.txt

