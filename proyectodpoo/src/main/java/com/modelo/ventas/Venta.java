package com.modelo.ventas;

import java.time.LocalDateTime;

import com.modelo.usuarios.Usuarios;

public abstract class Venta {
    private int id;
    private LocalDateTime fecha;
    private Usuarios comprador;
    protected double valorNeto;
    protected double valorTotal;
    protected double puntosGenerados;

    public Venta(LocalDateTime fecha, Usuarios comprador) {
        this.fecha = fecha;
        this.comprador = comprador;
    }

    public int getId() {
        return this.id;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public Usuarios getComprador() {
        return this.comprador;
    }

    public double getValorNeto() {
        return this.valorNeto;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    public double getPuntosGenerados() {
        return this.puntosGenerados;
    }
}
