package com.modelo.cafeteria;

import com.modelo.Exceptions.MesaOcupada;
import com.modelo.usuarios.Cliente;
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

    public void reservarMesa(Cliente cliente, int numeroPersonas, boolean hayNinios) throws MesaOcupada {
        if (!estaOcupada) {
            this.clienteAsignado = cliente;
            this.numeroPersonas = numeroPersonas;
            this.hayNinios = hayNinios;
            this.estaOcupada = true;
        } else {
            throw new MesaOcupada(String.valueOf(numeroMesa));
        }
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
}
