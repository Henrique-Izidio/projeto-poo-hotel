package src.br.edu.ufca.interfaces;

import java.util.Scanner;

public class Util {
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void aguardar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void pausa(Scanner scanner) {
        System.out.println("Precione ENTER para continuar....");
        scanner.nextLine();
    }

    public static int scanInt(Scanner scanner){
        int numero;

        while (!scanner.hasNextInt()) {
            System.out.print("\nEntrada invalida. Tente novamente: ");
            scanner.next(); // Consumir a entrada inválida
        }

        numero = scanner.nextInt();

        return numero;
    }
}

