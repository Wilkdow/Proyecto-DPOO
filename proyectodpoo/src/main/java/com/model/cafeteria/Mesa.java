package com.model.cafeteria;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.exceptions.orden_fallida.JuegoOcupado;
import com.exceptions.orden_fallida.JuegosPrestadosExcededidos;
import com.exceptions.orden_fallida.MesaOcupada;
import com.model.productos.JuegoMesaPrestamo;
import com.model.usuarios.Usuario;
import com.model.ventas.Prestamo;

public class Mesa {
    private int numeroMesa;
    private int numeroPersonas;
    private boolean hayNinios;
    private boolean tieneBebidaCaliente;
    private Usuario clienteAsignado;
    private Prestamo prestamo;

    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.numeroPersonas = 0;
        this.hayNinios = false;
        this.clienteAsignado = null;
        this.tieneBebidaCaliente = false;
        this.prestamo = null;
    }

    public void reservarMesa(Usuario cliente, int numeroPersonas, boolean hayNinios) throws MesaOcupada {
        if (estaOcupada()) {
            throw new MesaOcupada(String.valueOf(numeroMesa));
        }

        this.clienteAsignado = cliente;
        this.numeroPersonas = numeroPersonas;
        this.hayNinios = hayNinios;
    }

    public void liberarMesa() {
        cerrarPrestamo();
        this.numeroPersonas = 0;
        this.hayNinios = false;
        this.tieneBebidaCaliente = false;
        this.clienteAsignado = null;
    }

    public void iniciarPrestamo() {
        this.prestamo = new Prestamo(this);
    }

    public void cerrarPrestamo() {
        if (this.prestamo == null) {
            throw new IllegalStateException("No hay un préstamo activo para esta mesa.");
        }
        this.prestamo.cerrarPrestamo();
        this.prestamo = null;
    }

    public void prestarJuego(JuegoMesaPrestamo juego) throws JuegosPrestadosExcededidos, JuegoOcupado {
        if (!hayPrestamo())
            iniciarPrestamo();
        this.prestamo.prestarJuego(juego);
    }

    public void devolverJuego(int juegoIndice) {
        if (this.prestamo == null) {
            throw new IllegalStateException("No hay un préstamo activo para esta mesa.");
        }
        this.prestamo.devolverJuego(juegoIndice);
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public boolean hayNinios() {
        return hayNinios;
    }

    public boolean estaOcupada() {
        return clienteAsignado != null;
    }

    public Usuario getClienteAsignado() {
        return clienteAsignado;
    }

    public boolean tieneBebidaCaliente() {
        return this.tieneBebidaCaliente;
    }

    public void tieneBebidaCaliente(boolean b) {
        this.tieneBebidaCaliente = b;
    }

    public boolean tieneJuegoAccion() {
        if (prestamo == null)
            return false;
        return prestamo.hayJuegoMesaAccion();
    }

    public boolean hayPrestamo() {
        return prestamo != null;
    }

    public ArrayList<JuegoMesaPrestamo> getJuegosPrestado() {
        if (prestamo == null)
            return null;
        return prestamo.getJuegosEnPrestamo();
    }
}

