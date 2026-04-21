package com.modelo.cafeteria;

import java.time.LocalDate;
import java.util.ArrayList;

import com.modelo.exceptions.JuegosPrestadosExcededidos;
import com.modelo.productos.JuegoMesaPrestamo;
import com.modelo.usuarios.Usuario;

public class Prestamo {
    
    private int idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean devuelto;
    private ArrayList<JuegoMesaPrestamo> juegosPrestados;
    private Usuario prestatario;
    private Mesa mesa;

    public Prestamo(int idPrestamo, Usuario prestatario, Mesa mesa) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
        this.devuelto = false;
        this.juegosPrestados = new ArrayList<>();
        this.prestatario = prestatario;
        this.mesa = mesa;
    }

    public void prestarJuego(JuegoMesaPrestamo juego) throws Exception {
        if (juegosPrestados.size() >= 2) {
            throw new JuegosPrestadosExcededidos(juegosPrestados.get(0).getNombre(), juegosPrestados.get(1).getNombre());
        }
        juego.prestar();
        juegosPrestados.add(juego);
    }

    public void devolverJuego(JuegoMesaPrestamo juego) {
        juego.devolver();
        juegosPrestados.remove(juego);
    }

    public void cerrarPrestamo() {
        for (JuegoMesaPrestamo juego : juegosPrestados) {
            devolverJuego(juego);
        }
        this.devuelto = true;
        this.fechaDevolucion = LocalDate.now();
    }
}
