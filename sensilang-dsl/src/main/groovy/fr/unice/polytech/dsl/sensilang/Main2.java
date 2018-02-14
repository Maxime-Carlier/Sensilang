package fr.unice.polytech.dsl.sensilang;

import fr.unice.polytech.dsl.sensilang.language.SensilangShell;

import java.io.File;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sensilang Shell V0.1");
        while (sc.hasNext()) {
            SensilangShell shell = new SensilangShell();
            try {
                String arg = sc.nextLine();
                shell.eval(new File(arg));
                System.out.println("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
