package com.model.administracion;

import java.util.ArrayList;

import com.model.cafeteria.Prestamo;
import com.model.ventas.Venta;

public class Historial {
    private ArrayList<Venta> historialVentas;
    private ArrayList<Prestamo> historialPrestamos;
    private ArrayList<Solicitud> historialSolicitudes;

    public Historial() {
        this.historialVentas = new ArrayList<>();
        this.historialPrestamos = new ArrayList<>();
        this.historialSolicitudes = new ArrayList<>();
    }

    public void agregarVenta(Venta venta) {
        this.historialVentas.add(venta);
    }

    public void agregarPrestamo(Prestamo prestamo) {
        this.historialPrestamos.add(prestamo);
    }

    public void agregarSolicitud(Solicitud solicitud) {
        this.historialSolicitudes.add(solicitud);
    }

    public ArrayList<Venta> getHistorialVentas() {
        return historialVentas;
    }

    public ArrayList<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public ArrayList<Solicitud> getHistorialSolicitudes() {
        return historialSolicitudes;
    }
}
