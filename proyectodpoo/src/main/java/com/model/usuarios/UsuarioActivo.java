package com.model.usuarios;

import com.exceptions.NoSuficientesPuntos;

import java.util.HashSet;
import java.util.Set;

public abstract class UsuarioActivo extends Usuario{
    private int puntosFidelidad;
    private Set<String> juegosFavoritos;

    public UsuarioActivo(String login, String password) {
        super(login, password);
        this.puntosFidelidad = 0;
        this.juegosFavoritos = new HashSet<>();
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public Set<String> getJuegosFavoritos() {
        return juegosFavoritos;
    }

    public void agregarPuntosFidelidad(int puntosFidelidad) {
        this.puntosFidelidad += puntosFidelidad;
    }

    public void eliminarPuntosFidelidad(int puntosEliminar) throws NoSuficientesPuntos{
        if (puntosEliminar > this.puntosFidelidad)
            throw new NoSuficientesPuntos(getLogin());
        this.puntosFidelidad -= puntosEliminar;
    }

    public void agregarJuegoFavorito(String nombreJuego) {
        juegosFavoritos.add(nombreJuego);
    }

    public void eliminarJuegoFavorito(String nombreJuego) {
        juegosFavoritos.remove(nombreJuego);
    }
}
