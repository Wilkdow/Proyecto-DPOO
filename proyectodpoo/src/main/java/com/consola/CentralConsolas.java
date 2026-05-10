package com.consola;

import java.io.IOException;

import com.model.cafeteria.Inventario;
import com.model.usuarios.SessionHandler;

public class CentralConsolas {
    private static final String RUTA_DATA = "data/";
    private static final String INFO_USUARIOS = "info_usuarios.json";

    private static SessionHandler sHandler;
    private static Inventario inventario;

    private static void setUp() {
        try {
            sHandler = new SessionHandler();
            sHandler.cargarInfoUsuarios(RUTA_DATA + INFO_USUARIOS);
            inventario = new Inventario();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        setUp();
        Consola consola = new ConsolaSesiones(sHandler);
        boolean salirAplicacion = consola.correrConsola();
        if (salirAplicacion)
            return;
        consola = Consola.getTipoConsola(sHandler.getLoggedUser(), inventario);
        consola.correrConsola();
    }
}
