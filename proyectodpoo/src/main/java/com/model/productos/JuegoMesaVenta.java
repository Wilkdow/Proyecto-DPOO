package com.model.productos;

public class JuegoMesaVenta extends JuegoMesa{
    private int precio;
    private int stock;

    public JuegoMesaVenta(String nombre, int anioPublicacion, String empresaMatriz, int minJugadores, int maxJugadores, Edades restriccionEdad, Generos genero, String estado, boolean esDificil, int precio, int stock) {
        super(nombre, anioPublicacion, empresaMatriz, minJugadores, maxJugadores, restriccionEdad, genero, estado, esDificil);
        this.precio = precio;
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void vender(int cantidad) {
        if (cantidad > stock) {
            throw new IllegalArgumentException("No hay suficiente stock para vender " + cantidad + " unidades de " + this.getNombre());
        }
        stock -= cantidad;
    }

    public void reabastecer(int cantidad) {
        stock += cantidad;
    }
}
