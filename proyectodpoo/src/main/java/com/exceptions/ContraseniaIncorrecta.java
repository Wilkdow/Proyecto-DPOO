package com.exceptions;

public class ContraseniaIncorrecta extends Exception{
    private String user;

    public ContraseniaIncorrecta(String user) {
        super("");
        this.user = user;
    }

    public String getMessage() {
        return "La contraseña para el usuario " + user + " es incorrecta";
    }
}
