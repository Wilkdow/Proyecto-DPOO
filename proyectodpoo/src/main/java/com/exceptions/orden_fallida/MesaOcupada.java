package com.exceptions.orden_fallida;

public class MesaOcupada extends Exception{
    private String numeroMesa;

    public MesaOcupada(String numeroMesa){
        super("");
        this.numeroMesa = numeroMesa;
    }
    
    public String getMessage() {
        return "La mesa " + numeroMesa + " ya está ocupada.";
    }
}
