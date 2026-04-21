package com.modelo.juegosdemesa;

import com.modelo.enumerations.Edades;
import com.modelo.enumerations.Generos;

public abstract class JuegoMesa {
    private String nombre;
    private int anioPublicacion;
    private String empresaMatriz;
    private int minJugadores;
    private int maxJugadores;
    private Edades restriccionEdad;
    private Generos genero;
    private String estado;
    private boolean esDificil;

    public JuegoMesa(String nombre, int anioPublicacion, String empresaMatriz, int minJugadores, int maxJugadores, Edades restriccionEdad, Generos genero, String estado, boolean esDificil) {
        this.nombre = nombre;
        this.anioPublicacion = anioPublicacion;
        this.empresaMatriz = empresaMatriz;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
        this.restriccionEdad = restriccionEdad;
        this.genero = genero;
        this.estado = estado;
        this.esDificil = esDificil;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinJugadores() {
        return minJugadores;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public Edades getRestriccionEdad() {
        return restriccionEdad;
    }

    public Generos getGenero() {
        return genero;
    }

    public String getEstado() {
        return estado;
    }

    public boolean esDificil() {
        return esDificil;
    }

    public String imprimirInformacion() {
        return "Nombre: " + nombre + "\n" +
                "Año de Publicación: " + anioPublicacion + "\n" +
                "Empresa Matriz: " + empresaMatriz + "\n" +
                "Número de Jugadores: " + minJugadores + "-" + maxJugadores + "\n" +
                "Restricción de Edad: " + restriccionEdad + "\n" +
                "Género: " + genero + "\n" +
                "Estado: " + estado + "\n" +
                "Es Difícil: " + (esDificil ? "Sí" : "No");
    }
}
