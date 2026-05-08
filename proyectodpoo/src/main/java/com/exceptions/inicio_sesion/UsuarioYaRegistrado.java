package com.exceptions.inicio_sesion;

public class UsuarioYaRegistrado extends ErrorIniciandoSesion{
    private String usuario;

    public UsuarioYaRegistrado(String usuario) {
        super();
        this.usuario = usuario;
    }

    public String getMessage() {
        return "El usuario " + this.usuario + " ya está registrado.";
    }
}
