package com.model.cafeteria;

import com.exceptions.MesaOcupada;
import com.model.productos.JuegoMesaPrestamo;
import com.model.usuarios.Usuario;

public class Mesa {
    private int numeroMesa;
    private int numeroPersonas;
    private boolean hayNinios;
    private boolean estaOcupada;
    private boolean tieneBebidaCaliente;
    private Usuario clienteAsignado;
    private Prestamo prestamo;

    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.numeroPersonas = 0;
        this.hayNinios = false;
        this.estaOcupada = false;
        this.clienteAsignado = null;
        this.tieneBebidaCaliente = false;
        this.prestamo = null;
    }

    public void reservarMesa(Usuario cliente, int numeroPersonas, boolean hayNinios) throws MesaOcupada {
        if (estaOcupada) {
            throw new MesaOcupada(String.valueOf(numeroMesa));
        }

        this.clienteAsignado = cliente;
        this.numeroPersonas = numeroPersonas;
        this.hayNinios = hayNinios;
    }

    public void liberarMesa() {
        this.clienteAsignado = null;
        this.numeroPersonas = 0;
        this.hayNinios = false;
        this.estaOcupada = false;
    }

    public void iniciarPrestamo() {
        if (!estaOcupada) {
            throw new IllegalStateException("La mesa no está ocupada.");
        }
        this.prestamo = new Prestamo(this);
    }

    public void cerrarPrestamo() {
        if (this.prestamo == null) {
            throw new IllegalStateException("No hay un préstamo activo para esta mesa.");
        }
        this.prestamo.cerrarPrestamo();
        this.prestamo = null;
    }

    public void prestarJuego(JuegoMesaPrestamo juego) throws Exception {
        if (this.prestamo == null) {
            throw new IllegalStateException("No hay un préstamo activo para esta mesa.");
        }
        this.prestamo.prestarJuego(juego);
    }

    public void devolverJuego(JuegoMesaPrestamo juego) {
        if (this.prestamo == null) {
            throw new IllegalStateException("No hay un préstamo activo para esta mesa.");
        }
        this.prestamo.devolverJuego(juego);
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
        return estaOcupada;
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
}

