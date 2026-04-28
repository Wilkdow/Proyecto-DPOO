package com.modelo.usuarios;

import com.modelo.cafeteria.Inventario;

public class Mesero extends Empleado{
    public static final String TIPO_USUARIO = "MESERO";
    public Mesero(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }
}
