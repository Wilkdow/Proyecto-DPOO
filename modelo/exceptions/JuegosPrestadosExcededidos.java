package com.modelo.exceptions;

public class JuegosPrestadosExcededidos extends Exception{
    private String nombreJuego1;
    private String nombreJuego2;

    public JuegosPrestadosExcededidos(String nombreJuego1, String nombreJuego2) {
        super("");
        this.nombreJuego1 = nombreJuego1;
        this.nombreJuego2 = nombreJuego2;
    }    

    public String getMessage() {
        return "No se pueden prestar más de 2 juegos. La mesa ya tiene los juegos " + nombreJuego1 + " y " + nombreJuego2 + ".";
    }
}
