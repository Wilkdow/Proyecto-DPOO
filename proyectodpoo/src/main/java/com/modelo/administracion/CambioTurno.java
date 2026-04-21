package com.modelo.administracion;

import com.modelo.usuarios.Administrador;
import com.modelo.usuarios.Empleados;
import com.modelo.usuarios.Usuarios;

public class CambioTurno extends Solicitudes{
    private Turno turnoActual;
    private Turno turnoSolicitado;
    private Empleados empleadoIntercambio;

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

    public void aprobarSolicitud(Usuarios usuario) {
        if (!usuario.getClass().equals(Administrador.class)) {
            throw new IllegalArgumentException("Solo un administrador puede aprobar esta solicitud.");
        }

        super.revisarSolicitud();
        if (empleadoIntercambio != null) {
            turnoActual.removerEmpleado(this.empleadoSolicita);
            turnoSolicitado.asignarEmpleado(this.empleadoSolicita);

            turnoActual.asignarEmpleado(empleadoIntercambio);
            turnoSolicitado.removerEmpleado(empleadoIntercambio);
        } else {
            turnoSolicitado.asignarEmpleado(this.empleadoSolicita);
            turnoActual.removerEmpleado(this.empleadoSolicita);
        }
    }

    public void rechazarSolicitud() {
        super.revisarSolicitud();
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
