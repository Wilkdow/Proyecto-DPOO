package com.modelo.exceptions;

public class UsuarioYaRegistrado extends Exception{
    private String usuario;

    public UsuarioYaRegistrado(String usuario) {
        super(" ");
        this.usuario = usuario;
    }

    public String getMessage() {
        return "El usuario " + this.usuario + " ya está registrado.";
    }
}
