package com.exceptions.orden_fallida;

public class JuegoOcupado extends Exception {
    private String nombreJuego;
    
    public JuegoOcupado(String nombreJuego) {
        super("");
        this.nombreJuego = nombreJuego;
    }
    
    public String getMensaje() {
        return "El juego " + nombreJuego + " ya está ocupado y no se puede prestar en este momento.";
    }
}
