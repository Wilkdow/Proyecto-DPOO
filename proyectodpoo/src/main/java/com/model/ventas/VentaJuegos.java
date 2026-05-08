package com.model.ventas;

import java.util.ArrayList;

import com.model.productos.JuegoMesaVenta;
import com.model.usuarios.UsuarioActivo;

public class VentaJuegos extends Venta{
    private static final double IVA = 0.19;
    private ArrayList<JuegoMesaVenta> juegosMesaVendidos;

    private double calcularValorTotal(double valorNeto) {
        return (valorNeto - calcularDescuentoTotal(valorNeto)) * (1 + IVA);
    }
    
    public VentaJuegos(UsuarioActivo comprador) {
        super(comprador);
        this.juegosMesaVendidos = new ArrayList<>();
    }

    public void agregarJuegoMesaVenta(JuegoMesaVenta juego) {
        this.juegosMesaVendidos.add(juego);
        this.valorNeto += juego.getPrecio();
    }

    public void eliminarJuegoMesaVenta(JuegoMesaVenta juego) {
        if (!this.juegosMesaVendidos.contains(juego)) 
            throw new IllegalArgumentException("El juego no está en la venta.");
        this.juegosMesaVendidos.remove(juego);
        this.valorNeto -= juego.getPrecio();
    }

    public void cerrarVenta(int puntosUtilizados, boolean tieneReferido) {
        super.cerrarVenta(puntosUtilizados);
        this.valorTotal = calcularValorTotal(this.valorNeto);
        
        this.descuentoPorcentaje = tieneReferido ? 0.1: 0.0;
        this.puntosGenerados = this.valorTotal * 0.01;

        this.getComprador().agregarPuntosFidelidad((int) this.puntosGenerados);
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
