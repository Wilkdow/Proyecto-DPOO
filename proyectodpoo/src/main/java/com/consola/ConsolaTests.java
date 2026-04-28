package com.consola;

import com.new_model.cafeteria.Inventario;
import com.new_model.usuarios.SessionHandler;

public class ConsolaTests {
    private SessionHandler sHandler;
    private Inventario inventario;

    public void correrAplicacion() {
        inventario = new Inventario();
        sHandler = new SessionHandler(inventario);
        int opcion = ConsolaBasica.mostrarMenu("Bienvenido", new String[]{"Iniciar Sesión", "Registrarse"});
    }

    public static void main(String[] args) {
        ConsolaTests consola = new ConsolaTests();
        consola.correrAplicacion();
    }
}
