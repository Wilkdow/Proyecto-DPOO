package com.model.cafeteria;

import java.util.ArrayList;
import java.util.Collection;
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
        return juegosMesaPrestamo.get(nombre);
    }

    public JuegoMesaVenta getJuegoMesaVenta(String nombre) {
        return juegosMesaVenta.get(nombre);
    }

    public JuegoMesa getJuegoMesa(String nombre) {
        JuegoMesa juegoMesa = juegosMesaPrestamo.get(nombre);
        if (juegoMesa == null)
            juegoMesa = juegosMesaVenta.get(nombre);
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

    public void agregarCodigoDescuento(String codigo) {
        codigosDescuento.add(codigo);
    }

    public boolean esCodigoDescuentoValido(String codigoDescuento) {
        return this.codigosDescuento.contains(codigoDescuento);
    }

    public Collection<JuegoMesaPrestamo> getJuegosMesaPrestamo() {
        return juegosMesaPrestamo.values();
    }

    public Collection<JuegoMesaVenta> getJuegosMesaVenta() {
        return juegosMesaVenta.values();
    }

    public Collection<Plato> getMenu() {
        return menu.values();
    }

    public ArrayList<String> getCodigosDescuento() {
        return codigosDescuento;
    }

    public String getCatalogoJuegosPrestamo() {
        StringBuilder catalogo = new StringBuilder();
        catalogo.append("Juegos para prestamo:\n");
        for (JuegoMesa juego: juegosMesaPrestamo.values()) {
            catalogo.append(juego.imprimirInformacion() + "\n");
        }
        return catalogo.toString();
    }

    public String getCatalogoJuegosVenta() {
        StringBuilder catalogo = new StringBuilder();
        catalogo.append("Juegos para venta:\n");
        for (JuegoMesa juego: juegosMesaVenta.values()) {
            catalogo.append(juego.imprimirInformacion() + "\n");
        }
        return catalogo.toString();
    }
}

