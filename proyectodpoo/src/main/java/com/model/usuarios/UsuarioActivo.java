package com.model.usuarios;

import com.exceptions.NoSuficientesPuntos;
import com.model.cafeteria.Inventario;
import com.model.productos.JuegoMesa;

import java.util.HashSet;
import java.util.Set;

public abstract class UsuarioActivo extends Usuario{
    private int puntosFidelidad;
    private Set<JuegoMesa> juegosFavoritos;

    public UsuarioActivo(String login, String password, Inventario inventario) {
        super(login, password, inventario);
        this.puntosFidelidad = 0;
        this.juegosFavoritos = new HashSet<>();
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public Set<String> getJuegosFavoritosString() {
        Set<String> set = new HashSet<>();
        for (JuegoMesa juegoMesa: juegosFavoritos) {
            set.add(juegoMesa.getNombre());
        }
        return set;
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
        JuegoMesa juegoMesa = inventario.getJuegoMesa(nombreJuego);
        juegosFavoritos.add(juegoMesa);
    }

    public void eliminarJuegoFavorito(String nombreJuego) {
        JuegoMesa juegoMesa = inventario.getJuegoMesa(nombreJuego);
        juegosFavoritos.remove(juegoMesa);
    }
}
