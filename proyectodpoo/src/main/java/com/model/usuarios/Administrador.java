package com.model.usuarios;

public class Administrador extends Usuario{
    public static final String TIPO_USUARIO = "ADMINISTRADOR";
    
    public Administrador(String login, String password) {
        super(login, password);
    }
}
