package com.modelo.ventas;

import java.time.LocalDateTime;

import com.modelo.usuarios.Cliente;
import com.modelo.usuarios.Empleado;
import com.modelo.usuarios.Usuario;

public abstract class Venta {
    private int id;
    private LocalDateTime fecha;
    private Usuario comprador;
    protected double valorNeto;
    protected double valorTotal;
    protected double puntosGenerados;
    protected double descuentoPuntos;
    protected double descuentoPorcentaje;

    protected double calcularDescuentoTotal(double valorNeto) {
        return valorNeto * descuentoPorcentaje + descuentoPuntos;
    }

    public Venta(LocalDateTime fecha, Usuario comprador, double descuentoPuntos) {
        this.fecha = fecha;
        this.comprador = comprador;
        this.descuentoPuntos = descuentoPuntos;
        this.descuentoPorcentaje = (comprador.getClass() == Empleado.class) ? 0.2 : 0.0;
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
