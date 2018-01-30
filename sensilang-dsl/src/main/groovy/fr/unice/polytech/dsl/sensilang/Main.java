package fr.unice.polytech.dsl.sensilang;

import fr.unice.polytech.dsl.sensilang.language.SensilangShell;

import java.io.File;

public final class Main {
    public static void main(String[] args) {
        SensilangShell shell = new SensilangShell();
        if (args.length > 0) {
            shell.eval(new File(args[0]));
        } else {
            System.out.println("Un argument peut être ? ça peut aider pour faire des choses...");
        }
    }
}
