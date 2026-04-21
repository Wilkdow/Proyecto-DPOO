package com.modelo.ventas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.modelo.cafeteria.Mesa;
import com.modelo.productos.JuegoMesaVenta;
import com.modelo.productos.Plato;
import com.modelo.usuarios.Usuario;

public class VentaJuegos extends Venta{
    private static final double IMPUESTO_CONSUMO = 0.08;
    private double propina;
    private ArrayList<Plato> platosPedidos;
    private Mesa mesa;

    private double calcularValorNeto(ArrayList<Plato> platosPedidos) {
        double valorNeto = 0.0;
        for (Plato plato : platosPedidos) {
            valorNeto += plato.getPrecio();
        }
        return valorNeto;
    }

    private double calcularValorTotal(double valorNeto) {
        return valorNeto * (1 + IMPUESTO_CONSUMO) * (1 + propina);
    }

    public VentaJuegos(LocalDateTime fecha, Usuario comprador, ArrayList<Plato> platosPedidos, Mesa mesa, double propina) {
        super(fecha, comprador);
        this.platosPedidos = platosPedidos;
        this.mesa = mesa;
        this.propina = propina;

        this.valorNeto = calcularValorNeto(platosPedidos);
        this.valorTotal = calcularValorTotal(this.valorNeto);
    }

    public ArrayList<Plato> getPlatosPedidos() {
        return this.platosPedidos;
    }
}
