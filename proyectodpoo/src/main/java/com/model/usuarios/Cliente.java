package com.model.usuarios;

import com.model.cafeteria.Inventario;

public class Cliente extends UsuarioActivo{
    public static final String TIPO_USUARIO = "CLIENTE";
    
    public Cliente(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }
}
