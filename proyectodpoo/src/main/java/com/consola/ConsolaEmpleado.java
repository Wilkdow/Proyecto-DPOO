package com.consola;

import com.model.cafeteria.Inventario;
import com.model.cafeteria.Restaurante;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

public class ConsolaEmpleado extends Consola {
    private Empleado empleado;
    private Restaurante restaurante;
    private Inventario inventario;

    public ConsolaEmpleado(Usuario empleado, Restaurante restaurante) {
        super();
        this.empleado = (Empleado) empleado;
        this.restaurante = restaurante; 
        this.inventario = restaurante.getInventario();
    }
    public boolean correrConsola() {
        return true;
    }
}
