package com.model.usuarios;

public abstract class Usuario {
    private String login;
    private String password;

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
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
