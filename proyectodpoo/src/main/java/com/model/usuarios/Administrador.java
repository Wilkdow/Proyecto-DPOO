package com.model.usuarios;

public class Administrador extends Usuario{
    public static final String TIPO_USUARIO = Administrador.class.getSimpleName();
    
    public Administrador(String login, String password) {
        super(login, password);
    }


}
