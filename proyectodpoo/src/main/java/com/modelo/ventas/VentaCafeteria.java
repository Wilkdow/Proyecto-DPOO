package com.modelo.ventas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.modelo.productos.JuegoMesaVenta;
import com.modelo.usuarios.Usuario;

public class VentaCafeteria extends Venta{
    private static final double IVA = 0.19;
    private ArrayList<JuegoMesaVenta> juegosMesaVendidos;
    private double descuento;

    private double calcularValorNeto(ArrayList<JuegoMesaVenta> juegosMesaVendidos) {
        double valorNeto = 0.0;
        for (JuegoMesaVenta juego : juegosMesaVendidos) {
            valorNeto += juego.getPrecio();
        }
        return valorNeto;
    }

    private double calcularValorTotal(double valorNeto) {
        return valorNeto * (1 + IVA) * (1 - descuento);
    }

    public VentaCafeteria(LocalDateTime fecha, Usuario comprador, ArrayList<JuegoMesaVenta> juegosMesaVendidos, double descuento) {
        super(fecha, comprador);
        this.juegosMesaVendidos = juegosMesaVendidos;
        this.descuento = descuento;

        this.valorNeto = calcularValorNeto(juegosMesaVendidos);
        this.valorTotal = calcularValorTotal(this.valorNeto);
    }

    public ArrayList<JuegoMesaVenta> getJuegosMesaVendidos() {
        return this.juegosMesaVendidos;
    }
}
