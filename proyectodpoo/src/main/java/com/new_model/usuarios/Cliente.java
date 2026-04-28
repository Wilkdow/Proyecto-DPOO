package com.new_model.usuarios;

import com.new_model.cafeteria.Inventario;

public class Cliente extends UsuarioActivo{
    public static final String TIPO_USUARIO = "CLIENTE";
    
    public Cliente(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }

    public Cliente(String login, String password, Inventario inventario, int puntosFidelidad) {
        super(login, password, inventario, puntosFidelidad);
    }
}
