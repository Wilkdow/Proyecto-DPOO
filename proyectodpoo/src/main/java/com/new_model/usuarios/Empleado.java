package com.new_model.usuarios;

import com.new_model.cafeteria.Inventario;

public class Empleado extends UsuarioActivo{
    public Empleado(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }

    public Empleado(String login, String password, Inventario inventario, int puntosFidelidad) {
        super(login, password, inventario, puntosFidelidad);
    }
}
