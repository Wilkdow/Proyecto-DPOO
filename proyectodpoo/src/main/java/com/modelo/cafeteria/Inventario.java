package com.modelo.cafeteria;

import java.util.HashMap;
import java.util.Map;

import com.modelo.productos.*;

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
        if (!juegosMesaPrestamos.containsKey(nombre)) {
            throw new IllegalArgumentException("No se encontró el juego de mesa para préstamo: " + nombre);
        }
        return juegosMesaPrestamos.get(nombre);
    }

    public JuegoMesaVenta getJuegoMesaVenta(String nombre) {
        if (!juegosMesaVenta.containsKey(nombre)) {
            throw new IllegalArgumentException("No se encontró el juego de mesa para venta: " + nombre);
        }
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
