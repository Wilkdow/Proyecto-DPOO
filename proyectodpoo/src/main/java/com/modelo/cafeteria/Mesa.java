package com.modelo.cafeteria;

import com.modelo.exceptions.MesaOcupada;
import com.modelo.usuarios.Usuario;

public class Mesa {
    private int numeroMesa;
    private int numeroPersonas;
    private boolean hayNinios;
    private boolean estaOcupada;
    private Usuario clienteAsignado;

    public Mesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.numeroPersonas = 0;
        this.hayNinios = false;
        this.estaOcupada = false;
        this.clienteAsignado = null;
    }

    public void reservarMesa(Usuario cliente, int numeroPersonas, boolean hayNinios) throws MesaOcupada {
        if (estaOcupada) {
            throw new MesaOcupada(String.valueOf(numeroMesa));
        }

        this.clienteAsignado = cliente;
        this.numeroPersonas = numeroPersonas;
        this.hayNinios = hayNinios;
        this.estaOcupada = true;
    }

    public void liberarMesa() {
        this.clienteAsignado = null;
        this.numeroPersonas = 0;
        this.hayNinios = false;
        this.estaOcupada = false;
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
}
