package com.modelo.cafeteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.modelo.productos.*;

public class Inventario {
    private Map<String, JuegoMesaPrestamo> juegosMesaPrestamo;
    private Map<String, JuegoMesaVenta> juegosMesaVenta;
    private Map<String, Plato> menu;
    private ArrayList<String> codigosDescuento;

    public Inventario() {
        this.juegosMesaPrestamo = new HashMap<>();
        this.juegosMesaVenta = new HashMap<>();
        this.menu = new HashMap<>();
        this.codigosDescuento = new ArrayList<>();
    }

    public JuegoMesaPrestamo getJuegoMesaPrestamo(String nombre) {
        if (!juegosMesaPrestamo.containsKey(nombre)) {
            throw new IllegalArgumentException("No se encontró el juego de mesa para préstamo: " + nombre);
        }
        return juegosMesaPrestamo.get(nombre);
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
        juegosMesaPrestamo.put(juego.getNombre(), juego);
    }

    public void eliminarJuegoMesaPrestamo(String nombreJuego) {
        juegosMesaPrestamo.remove(nombreJuego);
    }

    public void agregarJuegoMesaVenta(JuegoMesaVenta juego) {
        juegosMesaVenta.put(juego.getNombre(), juego);
    }

    public void eliminarJuegoMesaVenta(String nombreJuego) {
        juegosMesaVenta.remove(nombreJuego);
    }

    public void agregarPlato(Plato plato) {
        menu.put(plato.getNombre(), plato);
    }

    public boolean esCodigoDescuentoValido(String codigoDescuento) {
        return this.codigosDescuento.contains(codigoDescuento);
    }
}
