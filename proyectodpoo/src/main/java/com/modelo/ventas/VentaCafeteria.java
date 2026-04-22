package com.modelo.ventas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.modelo.cafeteria.Mesa;
import com.modelo.productos.JuegoMesaVenta;
import com.modelo.productos.Plato;
import com.modelo.usuarios.Usuario;

public class VentaCafeteria extends Venta{
    private static final double IMPUESTO_CONSUMO = 0.08;
    private double propina;
    private ArrayList<Plato> platosPedidos;

    private double calcularValorNeto(ArrayList<Plato> platosPedidos) {
        double valorNeto = 0.0;
        for (Plato plato : platosPedidos) {
            valorNeto += plato.getPrecio();
        }
        return valorNeto;
    }

    private double calcularValorTotal(double valorNeto) {
        double valorPropina = valorNeto * propina;
        return calcularDescuentoTotal(valorNeto) * (1 + IMPUESTO_CONSUMO) + valorPropina ;
    }

    public VentaCafeteria(LocalDateTime fecha, Usuario comprador, ArrayList<Plato> platosPedidos, double propina, double descuento) {
        super(fecha, comprador, descuento);
        this.platosPedidos = platosPedidos;
        this.propina = propina;

        this.valorNeto = calcularValorNeto(platosPedidos);
        this.valorTotal = calcularValorTotal(this.valorNeto);
        this.puntosGenerados = this.valorTotal * 0.01;
    }

    public ArrayList<Plato> getPlatosPedidos() {
        return this.platosPedidos;
    }

    public String imprimirFactura() {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura de Venta en Cafetería\n");
        factura.append(String.format("Fecha: %t\n", this.getFecha()));
        factura.append(String.format("Comprador: %s\n", this.getComprador().getLogin()));
        factura.append("Platos Pedidos:\n");
        for (Plato plato : this.platosPedidos) {
            factura.append(String.format("- %s: $%,.2f\n", plato.getNombre(), plato.getPrecio()));
        }
        factura.append(String.format("Valor Neto: $%,.2f\n", this.getValorNeto()));
        factura.append(String.format("Propina: %.2f%%\n", this.propina * 100));
        factura.append(String.format("Descuento Total: $%,.2f\n", calcularDescuentoTotal(this.valorNeto)));
        factura.append(String.format("Valor Total: $%,.2f\n", this.getValorTotal()));
        return factura.toString();
    }
}
