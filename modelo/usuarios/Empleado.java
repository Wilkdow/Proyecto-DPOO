package com.modelo.usuarios;

import com.modelo.cafeteria.Inventario;

public abstract class Empleado extends UsuarioActivo{
    public Empleado(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }
}
