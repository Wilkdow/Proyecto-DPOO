package com.modelo.usuarios;

public abstract class Empleado extends UsuarioActivo{
    public Empleado(String login, String password) {
        super(login, password);
    }
}
