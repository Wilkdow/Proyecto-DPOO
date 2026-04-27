package com.consola;

import java.io.IOError;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

import com.modelo.exceptions.UsuarioYaRegistrado;

public class Main {
    private static String[] mensajesInicioSesion = {"Iniciar sesión", "Registrarse", "Salir"};

    private static void imprimirInicioSesion(Logic logic, Scanner scanner) {
        System.out.println("Iniciando sesión. Ingrese sus credenciales");
        String usuario = Consola.preguntarString(scanner, "\nUsuario: ");
        String password = Consola.preguntarString(scanner, "\nContraseña: ");
        if (logic.inicioSesionValido(usuario, password))
            return;
        if (!logic.userValido(password)) {
            boolean registrarse = Consola.preguntarString(scanner, "Usuario no encontrado, ¿desea registrarse? (Y/N)").toLowerCase().matches("y");
            if (registrarse)
                imprimirRegistro(logic, scanner);
            else
                imprimirInicioSesion(logic, scanner);
            return;
        }
        System.out.println("Contraseña incorrecta. Intente de vuelta");
        imprimirInicioSesion(logic, scanner);
    }

    private static void imprimirRegistro(Logic logic, Scanner scanner) {
        System.out.println("Para registrar su cuenta, ingrese\n");
        String usuario = Consola.preguntarString(scanner, "\nUsuario: ");
        String password = Consola.preguntarString(scanner, "\nContraseña: ");
        try {
            logic.registrarUsuario(usuario, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (e.getClass() == UsuarioYaRegistrado.class) {
                System.out.println("Usuario ya registrado en el sistema.");
                imprimirRegistro(logic, scanner);
            }
        }
    }

    public static void iniciarSesion(Logic logic, Scanner scanner, int opcion) {
        switch (opcion) {
            case 1:
                imprimirInicioSesion(logic, scanner);
                break;
            case 2:
                imprimirRegistro(logic, scanner);
                break;
            default:
                System.out.println("Saliendo");
                break;
        }
    }

    public static void main(String[] args) {
        ejecutarAplicacion();
    }

    private static void ejecutarAplicacion() {
        Logic logic;
        try {
            logic = new Logic();
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido a BoardGame Cafe!");
        int opcion = Consola.imprimirMenu(scanner, List.of(mensajesInicioSesion));
        iniciarSesion(logic, scanner, opcion);
        
    }
}