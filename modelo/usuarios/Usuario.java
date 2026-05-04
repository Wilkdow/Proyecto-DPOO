package com.modelo.usuarios;

import com.modelo.cafeteria.Inventario;

public abstract class Usuario {
    private String login;
    private String password;
    protected Inventario inventario;

    public Usuario(String login, String password, Inventario inventario) {
        this.login = login;
        this.password = password;
        this.inventario = inventario;
    }

    public String getLogin() {
        return this.login;
    }
}
