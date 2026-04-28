package com.modelo.usuarios;

import com.modelo.cafeteria.Inventario;

public class Cocinero extends Empleado{
    public static final String TIPO_USUARIO = "COCINERO";
    public Cocinero(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }
}
