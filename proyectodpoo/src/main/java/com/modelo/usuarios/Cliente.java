package com.modelo.usuarios;

import com.modelo.cafeteria.Inventario;
import com.modelo.cafeteria.Mesa;
import com.modelo.exceptions.MesaOcupada;

public class Cliente extends UsuarioActivo{
    private Mesa mesa;

    public Cliente(String login, String password, Inventario inventario) {
        super(login, password, inventario);
        this.mesa = null;
    }

    public void reservarMesa(Mesa mesa, int numeroPersonas, boolean hayNinios) throws MesaOcupada{
        this.mesa = mesa;
        mesa.reservarMesa(this, numeroPersonas, hayNinios);
    }

    public void liberarMesa() {
        if (this.mesa != null) {
            this.mesa.liberarMesa();
            this.mesa = null;
        }
    }

    public Mesa getMesa() {
        return this.mesa;
    }

    public void iniciarPrestamo() {
        if (this.mesa == null) {
            throw new IllegalStateException("No tienes una mesa reservada.");
        }
        this.mesa.iniciarPrestamo();
    }

    public void cerrarPrestamo() {
        if (this.mesa == null) {
            throw new IllegalStateException("No tienes una mesa reservada.");
        }
        this.mesa.cerrarPrestamo();
    }

    public void prestarJuego(String nombreJuego) throws Exception {
        if (this.mesa == null) {
            throw new IllegalStateException("No tienes una mesa reservada.");
        }
        this.mesa.prestarJuego(this.inventario.getJuegoMesaPrestamo(nombreJuego));
    }

    public void devolverJuego(String nombreJuego) {
        if (this.mesa == null) {
            throw new IllegalStateException("No tienes una mesa reservada.");
        }
        this.mesa.devolverJuego(this.inventario.getJuegoMesaPrestamo(nombreJuego));
    }
}
