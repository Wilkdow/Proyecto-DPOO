package com.consola;

import com.model.cafeteria.Inventario;
import com.model.usuarios.Administrador;
import com.model.usuarios.Cliente;
import com.model.usuarios.Usuario;

public class ConsolaAdministrador extends Consola {
    private Administrador administrador;
    private Inventario inventario;

    public ConsolaAdministrador(Usuario administrador, Inventario inventario) {
        super();
        this.administrador = (Administrador) administrador;
        this.inventario = inventario; 
    }
    public boolean correrConsola() {
        return true;
    }
}
