package com.model.ventas;

import java.time.LocalDateTime;

import com.model.usuarios.Empleado;
import com.model.usuarios.UsuarioActivo;

public abstract class Venta {
    private LocalDateTime fecha;
    private UsuarioActivo comprador;
    protected double valorNeto;
    protected double valorTotal;
    protected double puntosGenerados;
    protected double descuentoPuntos;
    protected double descuentoPorcentaje;

    protected double calcularDescuentoTotal(double valorNeto) {
        return valorNeto * descuentoPorcentaje + descuentoPuntos;
    }

    public Venta(UsuarioActivo comprador) {
        this.comprador = comprador;
        this.descuentoPuntos = 0;
        this.descuentoPorcentaje = (comprador.getClass() == Empleado.class) ? 0.2 : 0.0;
        this.puntosGenerados = 0;
        this.valorNeto = 0.0;
        this.valorTotal = 0.0;
    }

    protected void cerrarVenta(int puntosUtilizados) {
        this.fecha = LocalDateTime.now();
        this.descuentoPuntos = puntosUtilizados;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public UsuarioActivo getComprador() {
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
