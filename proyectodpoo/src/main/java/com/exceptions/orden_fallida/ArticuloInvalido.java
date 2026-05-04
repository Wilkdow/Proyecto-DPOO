package com.exceptions.orden_fallida;

public class ArticuloInvalido extends Exception{
    private String nombreArticulo;

    public ArticuloInvalido(String nombreArticulo) {
        super("");
        this.nombreArticulo = nombreArticulo;
    }    

    public String getMessage() {
        return "No se pudo ordenar el articulo " + nombreArticulo;
    }
}
