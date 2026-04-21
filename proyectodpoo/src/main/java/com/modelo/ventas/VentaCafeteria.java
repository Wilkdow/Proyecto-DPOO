package com.modelo.ventas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.modelo.juegosdemesa.JuegosMesaVenta;
import com.modelo.usuarios.Usuarios;

public class VentaCafeteria extends Venta{
    private static final double IVA = 0.19;
    private ArrayList<JuegosMesaVenta> juegosMesaVendidos;
    private double descuento;

    private double calcularValorNeto(ArrayList<JuegosMesaVenta> juegosMesaVendidos) {
        double valorNeto = 0.0;
        for (JuegosMesaVenta juego : juegosMesaVendidos) {
            valorNeto += juego.getPrecio();
        }
        return valorNeto;
    }

    private double calcularValorTotal(double valorNeto) {
        return valorNeto * (1 + IVA) * (1 - descuento);
    }

    public VentaCafeteria(LocalDateTime fecha, Usuarios comprador, ArrayList<JuegosMesaVenta> juegosMesaVendidos, double descuento) {
        super(fecha, comprador);
        this.juegosMesaVendidos = juegosMesaVendidos;
        this.descuento = descuento;

        this.valorNeto = calcularValorNeto(juegosMesaVendidos);
        this.valorTotal = calcularValorTotal(this.valorNeto);
    }

    public ArrayList<JuegosMesaVenta> getJuegosMesaVendidos() {
        return this.juegosMesaVendidos;
    }
}
