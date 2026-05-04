package com.consola;

import java.io.IOException;

import com.model.cafeteria.Inventario;
import com.model.usuarios.SessionHandler;

public class ConsolaSesiones {
    private static final String RUTA_ARCHIVO = "data/info_usuarios.json";

    private SessionHandler sHandler;
    private Inventario inventario;

    private void correrAplicacion() {
        try {
            inventario = new Inventario();
            sHandler = new SessionHandler(inventario);
            sHandler.cargarInfoUsuarios(RUTA_ARCHIVO);
            ejecutarOpcionesRegistro();
            ejecutarOpcionesSesionIniciada();
            sHandler.guardarInfoUsuarios(RUTA_ARCHIVO);
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    private void ejecutarOpcionesRegistro() {
        int opcion = ConsolaBasica.mostrarMenu("¿Desea iniciar Sesión o Registrarse?", new String[]{"Iniciar Sesión", "Registrarse", "Salir"});

        switch (opcion) {
            case 1:
                ejecutarIniciarSesion();
                break;
            case 2:
                ejecutarRegistro();
            default:
                break;
        }
    }

    private void ejecutarOpcionesSesionIniciada() {
        int opcion = ConsolaBasica.mostrarMenu("¿Qué desea hacer?", new String[] {"Entrar al sistema", "Cambiar contraseña", "Salir"});

        switch (opcion) {
            case 1:
                
                break;
            case 2:
                cambiarContrasenia();
                ejecutarOpcionesSesionIniciada();
                break;
            default:
                break;
        }
    }

    private void ejecutarIniciarSesion() {
        System.out.println("Iniciando sesión...\n");
        String user = ConsolaBasica.pedirCadenaAlUsuario("Ingrese usuario");
        String password = ConsolaBasica.pedirCadenaAlUsuario("Ingrese su contraseña");
        try {
            sHandler.iniciarSesion(user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ejecutarOpcionesRegistro();
        }
    }

    private void ejecutarRegistro() {
        System.out.println("Iniciando registro...\n");
        int opcion = ConsolaBasica.mostrarMenu("¿Cómo se desea registrar?", new String[] {"Cliente", "Empleado"});
        String user = ConsolaBasica.pedirCadenaAlUsuario("Ingrese usuario");
        String password = ConsolaBasica.pedirCadenaAlUsuario("Ingrese su contraseña");
        switch (opcion) {
            case 1:
                try {
                    sHandler.registrarCliente(user, password);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    ejecutarOpcionesRegistro();
                }
                break;
            case 2:
                int rolNumero = ConsolaBasica.pedirEnteroAlUsuario("Ingrese 1 para Mesero o 2 para empleado");
                try {
                    sHandler.registrarEmpleado(user, password, rolNumero);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    ejecutarOpcionesRegistro();
                }
            default:
                break;
        }
    }

    public void cambiarContrasenia() {
        String newPassword = ConsolaBasica.pedirCadenaAlUsuario("Ingrese su nueva contraseña");
        sHandler.cambiarContrasenia(newPassword);
        System.out.println("Contraseña cambiada");
    }

    public static void main(String[] args) {
        ConsolaSesiones consola = new ConsolaSesiones();
        consola.correrAplicacion();
    }
}
