package com.modelo.administracion;

import java.util.ArrayList;

import com.modelo.cafeteria.Prestamo;
import com.modelo.ventas.Venta;

public class Historial {
    private ArrayList<Venta> historialVentas;
    private ArrayList<Prestamo> historialPrestamos;
    private ArrayList<Solicitudes> historialSolicitudes;

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

    public void agregarSolicitud(Solicitudes solicitud) {
        this.historialSolicitudes.add(solicitud);
    }
}
