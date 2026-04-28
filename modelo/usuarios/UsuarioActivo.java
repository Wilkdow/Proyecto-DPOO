package com.modelo.usuarios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.modelo.administracion.Historial;
import com.modelo.cafeteria.Inventario;
import com.modelo.productos.JuegoMesa;
import com.modelo.productos.JuegoMesaVenta;
import com.modelo.ventas.VentaJuegos;

public abstract class UsuarioActivo extends Usuario{
    private int puntosAcumulados;
    private Set<JuegoMesa> juegosFavoritos;

    public UsuarioActivo(String login, String password, Inventario inventario) {
        super(login, password, inventario);
        this.puntosAcumulados = 0;
        this.juegosFavoritos = new HashSet<JuegoMesa>();
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
