package com.exceptions.orden_fallida;

public class NoSuficientesPuntos extends Exception{
    private String usuario;

    public NoSuficientesPuntos(String usuario) {
        super("");
        this.usuario = usuario;
    }    

    public String getMessage() {
        return "El usuario " + usuario + " no tiene suficientes puntos para hacer la transacción";
    }
}
