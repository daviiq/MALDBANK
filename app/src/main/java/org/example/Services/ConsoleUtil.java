package org.example.Services;

public class ConsoleUtil {
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
