package com.model.ventas;

import java.time.LocalDateTime;

import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

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

    public Venta(Usuario comprador, double descuentoPuntos) {
        
        this.comprador = comprador;
        this.descuentoPuntos = descuentoPuntos;
        this.descuentoPorcentaje = (comprador.getClass() == Empleado.class) ? 0.2 : 0.0;
    }

    public void cerrarVenta() {
        this.fecha = LocalDateTime.now();
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

    public abstract String imprimirFactura();
}
