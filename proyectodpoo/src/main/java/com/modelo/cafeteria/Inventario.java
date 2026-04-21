package com.modelo.cafeteria;

import java.util.HashMap;
import java.util.Map;

import com.modelo.juegosdemesa.*;
import com.modelo.platos.Plato;

public class Inventario {
    private Map<String, JuegoMesaPrestamo> juegosMesaPrestamos;
    private Map<String, JuegoMesaVenta> juegosMesaVenta;
    private Map<String, Plato> menu;

    public Inventario() {
        this.juegosMesaPrestamos = new HashMap<>();
        this.juegosMesaVenta = new HashMap<>();
        this.menu = new HashMap<>();
    }

    public JuegoMesaPrestamo getJuegoMesaPrestamo(String nombre) {
        return juegosMesaPrestamos.get(nombre);
    }

    public JuegoMesaVenta getJuegoMesaVenta(String nombre) {
        return juegosMesaVenta.get(nombre);
    }

    public Plato getPlato(String nombre) {
        return menu.get(nombre);
    }

    public void agregarJuegoMesaPrestamo(JuegoMesaPrestamo juego) {
        juegosMesaPrestamos.put(juego.getNombre(), juego);
    }

    public void agregarJuegoMesaVenta(JuegoMesaVenta juego) {
        juegosMesaVenta.put(juego.getNombre(), juego);
    }

    public void agregarPlato(Plato plato) {
        menu.put(plato.getNombre(), plato);
    }
}
