package com.model.cafeteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.model.productos.JuegoMesa;
import com.model.productos.JuegoMesaPrestamo;
import com.model.productos.JuegoMesaVenta;
import com.model.productos.Plato;

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

    public JuegoMesa getJuegoMesa(String nombre) {
        JuegoMesa juegoMesa;
        if (juegosMesaPrestamo.containsKey(nombre)) {
            juegoMesa = juegosMesaPrestamo.get(nombre);
        }
        else if (juegosMesaVenta.containsKey(nombre)) {
            juegoMesa = juegosMesaVenta.get(nombre);
        }
        else {
            throw new IllegalArgumentException("No se encontró el juego de mesa: " + nombre);
        }
        return juegoMesa;
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
