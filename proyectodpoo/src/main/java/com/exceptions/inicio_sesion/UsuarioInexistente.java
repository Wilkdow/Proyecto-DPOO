package com.exceptions.inicio_sesion;

public class UsuarioInexistente extends Exception{
    private String usuario;

    public UsuarioInexistente(String usuario) {
        super(" ");
        this.usuario = usuario;
    }

    public String getMessage() {
        return "El usuario " + this.usuario + " no está registrado en el sistema. Intente de vuelta o registrese";
    }
}
