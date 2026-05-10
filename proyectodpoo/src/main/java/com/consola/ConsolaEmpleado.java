package com.consola;

import com.model.cafeteria.Inventario;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

public class ConsolaEmpleado extends Consola {
    private Empleado empleado;
    private Inventario inventario;

    public ConsolaEmpleado(Usuario empleado, Inventario inventario) {
        super();
        this.empleado = (Empleado) empleado;
        this.inventario = inventario; 
    }
    public boolean correrConsola() {
        return true;
    }
}
