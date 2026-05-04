package com.modelo.usuarios;

import com.modelo.cafeteria.Inventario;

public class Administrador extends Usuario{
    public static final String TIPO_USUARIO = "ADMINISTRADOR";
    public Administrador(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }

    // TODO
}
