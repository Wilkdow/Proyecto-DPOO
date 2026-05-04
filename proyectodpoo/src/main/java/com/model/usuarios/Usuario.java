package com.model.usuarios;

import com.model.cafeteria.Inventario;

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(String password) {
        return this.password.matches(password);
    }
}
