package com.model.productos;

public class Bebida extends Plato{
    private boolean esAlcoholica;
    private boolean esCaliente;

    public Bebida(String nombre, String descripcion, double precio, boolean esAlcoholica, boolean esCaliente) {
        super(nombre, descripcion, precio);
        this.esAlcoholica = esAlcoholica;
        this.esCaliente = esCaliente;
    }

    public boolean esEsAlcoholica() {
        return esAlcoholica;
    }

    public boolean esCaliente() {
        return esCaliente;
    }
}
