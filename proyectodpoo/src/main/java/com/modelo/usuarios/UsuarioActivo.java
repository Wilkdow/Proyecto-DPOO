package com.modelo.usuarios;

import java.util.HashSet;
import java.util.Set;

import com.modelo.productos.JuegoMesa;

public class UsuarioActivo extends Usuario{
    private String nombre;
    private int puntosAcumulados;
    private Set<JuegoMesa> juegosFavoritos;

    public UsuarioActivo(String login, String password) {
        super(login, password);
        this.puntosAcumulados = 0;
        this.juegosFavoritos = new HashSet<JuegoMesa>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntosAcumulados() {
        return this.puntosAcumulados;
    }

    public Set<JuegoMesa> getJuegosFavoritos() {
        return this.juegosFavoritos;
    }

    public void acumularPuntos(double puntos) {
        this.puntosAcumulados += puntos;
    }

    public void canjearPuntos(int puntos) {
        if (puntos > this.puntosAcumulados)
            throw new IllegalArgumentException("No tienes suficientes puntos para canjear.");
        this.puntosAcumulados -= puntos;
    }

    public void agregarJuegoFavorito(JuegoMesa juego) {
        this.juegosFavoritos.add(juego);
    }

    public void eliminarJuegoFavorito(JuegoMesa juego) {
        if (!this.juegosFavoritos.contains(juego)) 
            throw new IllegalArgumentException("El juego no está en tus favoritos.");
        this.juegosFavoritos.remove(juego);
    }
}
