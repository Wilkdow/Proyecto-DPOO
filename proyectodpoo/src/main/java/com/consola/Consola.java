package com.consola;

import com.model.cafeteria.Inventario;
import com.model.cafeteria.Restaurante;
import com.model.usuarios.Cliente;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

public abstract class Consola {
    protected boolean salirAplicacion;

    public Consola() {
        this.salirAplicacion = false;
    }

    public abstract boolean correrConsola();

    public static Consola getTipoConsola(Usuario usuario, Restaurante restaurante) {
        String className = usuario.getClass().getSimpleName();
        if (className.matches(Cliente.class.getSimpleName())) {
            return new ConsolaCliente(usuario, restaurante);
        }

        if (className.matches(Empleado.class.getSimpleName()))
            return new ConsolaEmpleado(usuario, restaurante);

        return new ConsolaAdministrador(usuario, restaurante);
    }
}
