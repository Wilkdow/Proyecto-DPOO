package com.modelo.administracion;

import com.modelo.usuarios.Empleados;

public class CambioTurno extends Solicitudes{
    Turno turnoActual;
    Turno turnoSolicitado;
    Empleados empleadoIntercambio;

    public CambioTurno(Empleados empleadoSolicita, Turno turnoActual, Turno turnoSolicitado) {
        super(empleadoSolicita);
        this.turnoActual = turnoActual;
        this.turnoSolicitado = turnoSolicitado;
        this.empleadoIntercambio = null;
    }

    public CambioTurno(Empleados empleadoSolicita, Turno turnoActual, Turno turnoSolicitado, Empleados empleadoIntercambio) {
        super(empleadoSolicita);
        this.turnoActual = turnoActual;
        this.turnoSolicitado = turnoSolicitado;
        this.empleadoIntercambio = empleadoIntercambio;
    }

    public Turno getTurnoActual() {
        return this.turnoActual;
    }

    public Turno getTurnoSolicitado() {
        return this.turnoSolicitado;
    }

    public Empleados getEmpleadoIntercambio() {
        return this.empleadoIntercambio;
    }    
}
