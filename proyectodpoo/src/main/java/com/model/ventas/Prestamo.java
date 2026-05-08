package com.model.ventas;

import java.time.LocalDate;
import java.util.ArrayList;

import com.exceptions.orden_fallida.JuegoOcupado;
import com.exceptions.orden_fallida.JuegosPrestadosExcededidos;
import com.model.cafeteria.Mesa;
import com.model.productos.JuegoMesaPrestamo;
import com.model.usuarios.Usuario;

public class Prestamo {
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean cerrado;
    private ArrayList<String> juegosPrestados;
    private ArrayList<JuegoMesaPrestamo> juegosEnPrestamo;
    private Usuario prestatario;

    public Prestamo(Mesa mesa) {
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.cerrado = false;
        this.juegosPrestados = new ArrayList<>();
        this.juegosEnPrestamo = new ArrayList<>(2);
        this.prestatario = mesa.getClienteAsignado();
    }

    public void prestarJuego(JuegoMesaPrestamo juego) throws JuegosPrestadosExcededidos, JuegoOcupado {
        if (juegosEnPrestamo.size() >= 2) {
            throw new JuegosPrestadosExcededidos(juegosEnPrestamo.get(0).getNombre(), juegosEnPrestamo.get(1).getNombre());
        }
        juego.prestar();
        juegosPrestados.add(juego.getNombre());
        juegosEnPrestamo.add(juego);
    }

    public void devolverJuego(int juegoIndice) {
        JuegoMesaPrestamo juego = juegosEnPrestamo.get(juegoIndice);
        juego.devolver();
        juegosEnPrestamo.remove(juego);
    }

    public boolean hayJuegoMesaAccion() {
        for (JuegoMesaPrestamo juego: juegosEnPrestamo) {
            if (juego.esAccion())
                return true;
        }
        return false;
    }

    public void cerrarPrestamo() {
        for (int i = 0; i > juegosEnPrestamo.size(); i++) {
            devolverJuego(i);
        }
        this.cerrado = true;
        this.fechaDevolucion = LocalDate.now();
    }

    public ArrayList<JuegoMesaPrestamo> getJuegosEnPrestamo() {
        return juegosEnPrestamo;
    }

    public LocalDate getFechaPrestamo() {
        return this.fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    public boolean estaCerrado() {
        return this.cerrado;
    }

    public Usuario getPrestatario() {
        return this.prestatario;
    }

    public String imprimirFactura() {
        if (!estaCerrado())
            return null;
        StringBuilder factura = new StringBuilder();
        factura.append("Factura de Préstamo de Juegos\n");
        factura.append(String.format("Fecha de préstamo: %s\n", this.fechaPrestamo));
        factura.append(String.format("Fecha de devolución: %s\n", this.fechaDevolucion));
        factura.append(String.format("Prestatario: %s\n", this.prestatario.getLogin()));
        factura.append("Juegos prestados:\n");
        for (String nombreJuego : this.juegosPrestados) {
            factura.append(String.format("- %s\n", nombreJuego));
        }
        return factura.toString();
    }
}
