package com.consola;

import com.model.usuarios.SessionHandler;

public class ConsolaSesiones extends Consola {
    private SessionHandler sHandler;

    public ConsolaSesiones(SessionHandler sHandler) {
        super();
        this.sHandler = sHandler;
    }

    public boolean correrConsola() {

        ejecutarOpcionesRegistro();
        if (!salirAplicacion)
            ejecutarOpcionesSesionIniciada();
        return salirAplicacion;
    }

    private void ejecutarOpcionesRegistro() {
        int opcion = ConsolaBasica.mostrarMenu("¿Desea iniciar Sesión o Registrarse?", new String[]{"Iniciar Sesión", "Registrarse", "Salir"});

        switch (opcion) {
            case 1:
                ejecutarIniciarSesion();
                break;
            case 2:
                ejecutarRegistro();
                break;
            default:
                salirAplicacion = true;
                break;
        }
    }

    private void ejecutarOpcionesSesionIniciada() {
        int opcion = ConsolaBasica.mostrarMenu("¿Qué desea hacer?", new String[] {"Entrar al sistema", "Cambiar contraseña", "Salir"});

        switch (opcion) {
            case 1:
                salirAplicacion = false;
                break;
            case 2:
                cambiarContrasenia();
                ejecutarOpcionesSesionIniciada();
                break;
            default:
                salirAplicacion = true;
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
}
