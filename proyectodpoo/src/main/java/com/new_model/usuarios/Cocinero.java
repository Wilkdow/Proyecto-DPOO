package com.new_model.usuarios;

import com.new_model.cafeteria.Inventario;

public class Cocinero extends Empleado{
    public static final String TIPO_USUARIO = "COCINERO";
    
    public Cocinero(String login, String password, Inventario inventario) {
        super(login, password, inventario);
    }
}
