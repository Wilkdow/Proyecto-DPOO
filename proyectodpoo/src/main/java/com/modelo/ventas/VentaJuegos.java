package com.modelo.ventas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.modelo.productos.JuegoMesaVenta;
import com.modelo.usuarios.Empleado;
import com.modelo.usuarios.Usuario;

public class VentaJuegos extends Venta{
    private static final double IVA = 0.19;
    private ArrayList<JuegoMesaVenta> juegosMesaVendidos;

    private double calcularValorNeto(ArrayList<JuegoMesaVenta> juegosMesaVendidos) {
        double valorNeto = 0.0;
        for (JuegoMesaVenta juego : juegosMesaVendidos) {
            valorNeto += juego.getPrecio();
        }
        return valorNeto;
    }

    private double calcularValorTotal(double valorNeto) {
        return calcularDescuentoTotal(valorNeto) * (1 + IVA);
    }

    public VentaJuegos(LocalDateTime fecha, Usuario comprador, ArrayList<JuegoMesaVenta> juegosMesaVendidos, double descuento, boolean tieneReferido) {
        super(fecha, comprador, descuento);
        this.juegosMesaVendidos = juegosMesaVendidos;

        this.valorNeto = calcularValorNeto(juegosMesaVendidos);
        this.valorTotal = calcularValorTotal(this.valorNeto);
        this.descuentoPorcentaje = (tieneReferido) ? 0.1 : 0.0;
        this.puntosGenerados = this.valorTotal * 0.01;
    }

    public ArrayList<JuegoMesaVenta> getJuegosMesaVendidos() {
        return this.juegosMesaVendidos;
    }

    public String imprimirFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura de Venta de Juegos\n");
        factura.append(String.format("Fecha: %t\n", this.getFecha()));
        factura.append(String.format("Comprador: %s\n", this.getComprador().getLogin()));
        factura.append("Juegos Vendidos:\n");
        for (JuegoMesaVenta juego : this.juegosMesaVendidos) {
            factura.append(String.format("- %s: $%,.2f\n", juego.getNombre(), juego.getPrecio()));
        }
        factura.append(String.format("Valor Neto: $%,.2f\n", this.getValorNeto()));
        factura.append(String.format("Descuento Total: $%,.2f\n", calcularDescuentoTotal(this.valorNeto)));
        factura.append(String.format("Valor Total: $%,.2f\n", this.getValorTotal()));
        return factura.toString();
    }
}
