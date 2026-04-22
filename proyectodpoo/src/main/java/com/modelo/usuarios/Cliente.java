package com.modelo.usuarios;

import com.modelo.cafeteria.Mesa;
import com.modelo.exceptions.MesaOcupada;

public class Cliente extends UsuarioActivo{
    private Mesa mesa;

    public Cliente(String login, String password) {
        super(login, password);
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
}
