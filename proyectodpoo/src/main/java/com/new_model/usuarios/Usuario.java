package com.new_model.usuarios;

import com.new_model.cafeteria.Inventario;

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

    public boolean checkPassword(String password) {
        return this.password.matches(password);
    }
}
