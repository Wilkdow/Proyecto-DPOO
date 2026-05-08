package com.model.usuarios;

import com.exceptions.RestauranteLleno;
import com.exceptions.orden_fallida.ArticuloInvalido;
import com.exceptions.orden_fallida.JuegoOcupado;
import com.exceptions.orden_fallida.JuegosPrestadosExcededidos;
import com.exceptions.orden_fallida.MesaOcupada;
import com.model.cafeteria.Mesa;
import com.model.cafeteria.Restaurante;
import com.model.productos.Bebida;
import com.model.productos.JuegoMesa;
import com.model.productos.JuegoMesaPrestamo;
import com.model.productos.Plato;

public class Cliente extends UsuarioActivo{
    public static final String TIPO_USUARIO = "CLIENTE";

    private Mesa mesa;
    
    public Cliente(String login, String password) {
        super(login, password);
        this.mesa = null;
    }

    public void reservarMesa(Restaurante restaurante, int numeroPersonas, boolean hayNinios) throws MesaOcupada, RestauranteLleno {
        restaurante.agregarCapacidad(numeroPersonas);
        this.mesa = restaurante.getMesaVacia();
        this.mesa.reservarMesa(null, numeroPersonas, hayNinios);
    }

    @Override
    public void ordenarPlato(Plato plato) throws ArticuloInvalido {
        if (!checkCompatibilidad(plato, mesa))
            throw new ArticuloInvalido(plato.getNombre());
        if (plato instanceof Bebida) {
            if (((Bebida)plato).esCaliente())
                mesa.tieneBebidaCaliente(true);
        }
        super.ordenarPlato(plato);
    }

    public void pedirJuegoPrestado(JuegoMesaPrestamo juegoMesa) throws ArticuloInvalido, JuegosPrestadosExcededidos, JuegoOcupado {
        if (!checkCompatibilidad(juegoMesa, mesa))
            throw new ArticuloInvalido(juegoMesa.getNombre());
        if (!mesa.hayPrestamo())
            mesa.iniciarPrestamo();
        mesa.prestarJuego(juegoMesa);
    }

    public void devolverJuegoPrestado(int juegoIndice) {
        mesa.devolverJuego(juegoIndice);
    }

    private static boolean checkCompatibilidad(Plato plato, Mesa mesa) {
        if (!(plato instanceof Bebida))
            return true;
        Bebida bebida = (Bebida) plato;

        if (mesa.hayNinios() && bebida.esEsAlcoholica())
            return false;
        if (bebida.esCaliente() && mesa.tieneJuegoAccion()) {
            return false;
        }
        return true;
    }

    private static boolean checkCompatibilidad(JuegoMesa juegoMesa, Mesa mesa) {
        if (mesa.tieneBebidaCaliente() && juegoMesa.esAccion())
            return false;
        return true;
    }
}
