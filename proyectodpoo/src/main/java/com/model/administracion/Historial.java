package com.model.administracion;

import java.util.ArrayList;

import com.model.ventas.Prestamo;
import com.model.ventas.Venta;

public class Historial {
    private ArrayList<String> historialVentas;
    private ArrayList<String> historialPrestamos;
    private ArrayList<String> historialSolicitudes;

    public Historial() {
        this.historialVentas = new ArrayList<>();
        this.historialPrestamos = new ArrayList<>();
        this.historialSolicitudes = new ArrayList<>();
    }

    public void agregarVenta(Venta venta) {
        this.historialVentas.add(venta.imprimirFactura());
    }

    public void agregarPrestamo(Prestamo prestamo) {
        this.historialPrestamos.add(prestamo.imprimirFactura());
    }

    public void agregarSolicitud(Solicitud solicitud) {
        this.historialSolicitudes.add(solicitud.imprimirResumen());
    }

    public ArrayList<String> getHistorialVentas() {
        return historialVentas;
    }

    public ArrayList<String> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public ArrayList<String> getHistorialSolicitudes() {
        return historialSolicitudes;
    }
}
