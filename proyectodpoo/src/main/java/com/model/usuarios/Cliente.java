package com.model.usuarios;

public class Cliente extends UsuarioActivo{
    public static final String TIPO_USUARIO = "CLIENTE";
    
    public Cliente(String login, String password) {
        super(login, password);
    }
}
