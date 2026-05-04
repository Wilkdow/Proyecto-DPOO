package com.model.usuarios;
import com.model.cafeteria.Inventario;

public class Administrador extends Usuario{
    public static final String TIPO_USUARIO = "ADMINISTRADOR";
    
    public Administrador(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }
}
