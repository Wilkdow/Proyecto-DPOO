package com.model.cafeteria;

import java.time.LocalDate;
import java.util.ArrayList;

import com.exceptions.orden_fallida.ArticuloInvalido;
import com.exceptions.orden_fallida.JuegoOcupado;
import com.exceptions.orden_fallida.JuegosPrestadosExcededidos;
import com.model.productos.JuegoMesaPrestamo;
import com.model.usuarios.Usuario;

public class Prestamo {
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;
    private ArrayList<JuegoMesaPrestamo> juegosPrestados;
    private Usuario prestatario;
    private Mesa mesa;

    public Prestamo(Mesa mesa) {
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.devuelto = false;
        this.juegosPrestados = new ArrayList<>(2);
        this.prestatario = mesa.getClienteAsignado();
        this.mesa = mesa;
    }

    public void prestarJuego(JuegoMesaPrestamo juego) throws JuegosPrestadosExcededidos, JuegoOcupado {
        if (juegosPrestados.size() >= 2) {
            throw new JuegosPrestadosExcededidos(juegosPrestados.get(0).getNombre(), juegosPrestados.get(1).getNombre());
        }
        juego.prestar();
        juegosPrestados.add(juego);
    }

    public void devolverJuego(int juegoIndice) {
        JuegoMesaPrestamo juego = juegosPrestados.get(juegoIndice);
        juego.devolver();
        juegosPrestados.remove(juego);
    }

    public boolean hayJuegoMesaAccion() {
        for (JuegoMesaPrestamo juego: juegosPrestados) {
            if (juego.esAccion())
                return true;
        }
        return false;
    }

    public void cerrarPrestamo() {
        for (int i = 0; i > juegosPrestados.size(); i++) {
            devolverJuego(i);
        }
        this.devuelto = true;
        this.fechaDevolucion = LocalDate.now();
    }

    public ArrayList<JuegoMesaPrestamo> getJuegosPrestados() {
        return juegosPrestados;
    }

    public LocalDate getFechaPrestamo() {
        return this.fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public boolean fueDevuelto() {
        return this.devuelto;
    }

    public Usuario getPrestatario() {
        return this.prestatario;
    }
}
