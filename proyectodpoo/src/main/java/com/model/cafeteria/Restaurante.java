package com.model.cafeteria;

import java.util.ArrayList;

import com.exceptions.RestauranteLleno;
import com.model.administracion.Solicitud;
import com.model.administracion.Turno;
import com.model.usuarios.Cliente;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;


public class Restaurante {
    private static int capacidadMaxima = 80;
    
    private Usuario loggedUser;
    private int genteEnRestaurante;
    private Inventario inventario;
    private ArrayList<Cliente> clientes;
    private ArrayList<Solicitud> solicitudes;
    private Mesa[] mesas;
    private Turno turnoActivo;

    public Restaurante(Usuario loggedUser, Inventario inventario, int cantidadMesas) {
        this.genteEnRestaurante = 0;
        this.clientes = new ArrayList<>();
        this.inventario = inventario;
        this.solicitudes = new ArrayList<>();
        this.mesas = inicializarMesas(cantidadMesas);
        this.turnoActivo = null;
        this.loggedUser = loggedUser;
    }

    public ArrayList<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Mesa getMesa(int numeroMesa) {
        return mesas[numeroMesa];
    }

    public Mesa getMesaVacia() {
        Mesa mesa = null;
        for (int i = 0; i < mesas.length; i++) {
            if (!mesas[i].estaOcupada()) {
                mesa = mesas[i];
                break;
            }
        }
        return mesa;
    }

    public int getCuposDisponibles() {
        return capacidadMaxima - genteEnRestaurante;
    }

    public void agregarGenteAlRestaurante(int numeroPersonas) throws RestauranteLleno {
        if (numeroPersonas > getCuposDisponibles())
            throw new RestauranteLleno(capacidadMaxima, getCuposDisponibles());
        this.genteEnRestaurante += numeroPersonas;
    }

    public void agregarSolicitud(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }

    public void asignarTurno(Turno turno) {
        this.turnoActivo = turno;
    }

    public void cerrarRestaurante() {
        this.turnoActivo = null;
        this.clientes = new ArrayList<>();
    }

    public void setCapacidadMaxima(int newCapacidadMaxima) {
        capacidadMaxima = newCapacidadMaxima;
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    public boolean estaAbierto() {
        if (turnoActivo == null)
            return false;
        return checkEmpleados() && turnoActivo.esTurnoValido();
    }

    private Mesa[] inicializarMesas(int cantidadMesas) {
        Mesa[] mesas = new Mesa[cantidadMesas];
        for (int i = 0; i < cantidadMesas; i++) {
            mesas[i] = new Mesa(i);
        }
        return mesas;
    }

    private boolean checkEmpleados() {
        int meseros = 0;
        int cocineros = 0;
        for (Empleado empleado: turnoActivo.getEmpleadosAsignados()) {
            if (empleado.esMesero())
                meseros++;
            else if (empleado.esCocinero())
                cocineros++;
        }

        return meseros > 1 && cocineros > 0;
    }

    public boolean estaEmpleadoEnTurno(Empleado empleado) {
        return turnoActivo.estaEmpleadoEnTurno(empleado);
    }
}
