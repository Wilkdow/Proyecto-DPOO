package com.model.productos;

import com.exceptions.orden_fallida.JuegoOcupado;

public class JuegoMesaPrestamo extends JuegoMesa{
    private int vecesPrestado;
    private boolean disponible;

    public JuegoMesaPrestamo(String nombre, int anioPublicacion, String empresaMatriz, int minJugadores, int maxJugadores, Edades restriccionEdad, Generos genero, String estado, boolean esDificil) {
        super(nombre, anioPublicacion, empresaMatriz, minJugadores, maxJugadores, restriccionEdad, genero, estado, esDificil);
        this.vecesPrestado = 0;
        this.disponible = true;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void prestar() throws JuegoOcupado{
        if (!disponible) {
            throw new JuegoOcupado(this.getNombre());
        }
        vecesPrestado++;
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }
}
