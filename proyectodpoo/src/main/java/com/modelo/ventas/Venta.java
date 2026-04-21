package com.modelo.ventas;

import java.time.LocalDateTime;

import com.modelo.usuarios.Usuario;

public abstract class Venta {
    private int id;
    private LocalDateTime fecha;
    private Usuario comprador;
    protected double valorNeto;
    protected double valorTotal;
    protected double puntosGenerados;

    public Venta(LocalDateTime fecha, Usuario comprador) {
        this.fecha = fecha;
        this.comprador = comprador;
    }

    public int getId() {
        return this.id;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public Usuario getComprador() {
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
