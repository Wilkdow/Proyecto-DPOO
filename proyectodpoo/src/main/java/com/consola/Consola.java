package com.consola;

import java.util.List;
import java.util.Scanner;

public class Consola {
    public static int imprimirMenu(Scanner scanner, List<String> mensajes) {
        for (int i = 0; i < mensajes.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + mensajes.get(i));
        }
        System.out.print("Seleccione opción: ");
        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion > 0 && opcion <= mensajes.size())
                return opcion;
        }
        scanner.nextLine();
        System.out.println("\nOpción Incorrecta");
        return imprimirMenu(scanner, mensajes);
    }

    public static String preguntarString(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String respuesta = scanner.nextLine();
        return respuesta;
    }
}
