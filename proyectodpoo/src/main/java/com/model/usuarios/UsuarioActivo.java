package com.model.usuarios;

import com.exceptions.orden_fallida.ArticuloInvalido;
import com.exceptions.orden_fallida.NoSuficientesPuntos;
import com.model.productos.JuegoMesaVenta;
import com.model.productos.Plato;
import com.model.ventas.VentaCafeteria;
import com.model.ventas.VentaJuegos;

import java.util.HashSet;
import java.util.Set;

public abstract class UsuarioActivo extends Usuario{
    protected int puntosFidelidad;
    protected Set<String> juegosFavoritos;
    private VentaJuegos ventaJuegos;
    private VentaCafeteria ventaCafeteria;

    public UsuarioActivo(String login, String password) {
        super(login, password);
        this.puntosFidelidad = 0;
        this.juegosFavoritos = new HashSet<>();
        this.ventaJuegos = null;
        this.ventaCafeteria = null;
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public Set<String> getJuegosFavoritos() {
        return juegosFavoritos;
    }

    public void ordenarPlato(Plato plato) throws ArticuloInvalido {
        if (ventaCafeteria == null)
            ventaCafeteria = new VentaCafeteria(this);
        ventaCafeteria.ordenarPlato(plato);
    }

    public void cerrarCuentaPlatos(int puntosAUtilizar, double propina) throws NoSuficientesPuntos {
        if (puntosAUtilizar > puntosFidelidad)
            throw new NoSuficientesPuntos(this.getLogin());
        if (ventaCafeteria == null)
            throw new IllegalStateException("No hay venta por cerrar");
        ventaCafeteria.cerrarVenta(puntosAUtilizar, propina);
        ventaCafeteria = null;
    }

    public void abrirCuentaJuegosMesa() {
        ventaJuegos = new VentaJuegos(this);
    }

    public void comprarJuego(JuegoMesaVenta juegoMesa) {
        if (ventaJuegos == null)
            abrirCuentaJuegosMesa();
        ventaJuegos.agregarJuegoMesaVenta(juegoMesa);
    }

    public void cerrarCuentaJuegosMesa(int puntosAUtilizar, boolean tieneReferido) throws NoSuficientesPuntos {
        if (puntosAUtilizar > puntosFidelidad)
            throw new NoSuficientesPuntos(this.getLogin());
        ventaJuegos.cerrarVenta(puntosAUtilizar, tieneReferido);
        ventaJuegos = null;
    }

    public void agregarPuntosFidelidad(int puntosFidelidad) {
        this.puntosFidelidad += puntosFidelidad;
    }

    public void eliminarPuntosFidelidad(int puntosEliminar) throws NoSuficientesPuntos{
        if (puntosEliminar > this.puntosFidelidad)
            throw new NoSuficientesPuntos(getLogin());
        this.puntosFidelidad -= puntosEliminar;
    }

    public void agregarJuegoFavorito(String nombreJuego) {
        juegosFavoritos.add(nombreJuego);
    }

    public void eliminarJuegoFavorito(String nombreJuego) {
        juegosFavoritos.remove(nombreJuego);
    }
}
