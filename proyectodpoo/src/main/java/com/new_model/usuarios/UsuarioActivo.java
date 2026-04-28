package com.new_model.usuarios;
import com.new_model.cafeteria.Inventario;

import java.util.HashSet;
import java.util.Set;

public abstract class UsuarioActivo extends Usuario{
    private int puntosFidelidad;
    private Set<String> juegosFavoritos;

    public UsuarioActivo(String login, String password, Inventario inventario) {
        super(login, password, inventario);
        this.puntosFidelidad = 0;
        this.juegosFavoritos = new HashSet<>();
    }

    public UsuarioActivo(String login, String password, Inventario inventario, int puntosFidelidad) {
        super(login, password, inventario);
        this.puntosFidelidad = puntosFidelidad;
        // TODO this.juegosFavoritos = juegosFavoritos;
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }
}
