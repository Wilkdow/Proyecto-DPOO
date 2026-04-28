package com.modelo.ventas;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.modelo.cafeteria.Mesa;
import com.modelo.exceptions.ArticuloInvalido;
import com.modelo.productos.Plato;
import com.modelo.usuarios.Usuario;

public class VentaCafeteria extends Venta{
    private static final double IMPUESTO_CONSUMO = 0.08;
    private double propina;
    private ArrayList<Plato> platosPedidos;

    private double calcularValorTotal(double valorNeto) {
        double valorPropina = valorNeto * propina;
        return calcularDescuentoTotal(valorNeto) * (1 + IMPUESTO_CONSUMO) + valorPropina ;
    }

    public VentaCafeteria(Usuario comprador, double puntosUtilizados) {
        super(comprador, puntosUtilizados);
        this.platosPedidos = new ArrayList<>();

        this.valorNeto = 0.0;
        this.valorTotal = 0.0;
        this.puntosGenerados = 0.0;
    }

    public void ordenarPlato(Mesa mesa, Plato plato) throws ArticuloInvalido{
        if (!mesa.ordenarPlato(plato))
            throw new ArticuloInvalido(plato.getNombre());
        platosPedidos.add(plato);
        valorNeto += plato.getPrecio();
    }

    public ArrayList<Plato> getPlatosPedidos() {
        return this.platosPedidos;
    }

    @Override
    public void cerrarVenta() {
        super.cerrarVenta();
        this.valorTotal = calcularValorTotal(this.valorNeto);
        this.puntosGenerados = this.valorTotal * 0.01;
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
