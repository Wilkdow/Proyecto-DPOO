package com.consola;

import com.model.cafeteria.Inventario;
import com.model.cafeteria.Restaurante;
import com.model.usuarios.Administrador;
import com.model.usuarios.Usuario;

public class ConsolaAdministrador extends Consola {
    private Administrador administrador;
    private Restaurante restaurante;
    private Inventario inventario;

    public ConsolaAdministrador(Usuario administrador, Restaurante restaurante) {
        super();
        this.administrador = (Administrador) administrador;
        this.restaurante = restaurante; 
        this.inventario = restaurante.getInventario();
    }
    public boolean correrConsola() {
        return true;
    }
}
