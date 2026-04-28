package com.modelo.administracion;

import com.modelo.usuarios.Administrador;
import com.modelo.usuarios.Empleado;
import com.modelo.usuarios.Usuario;

public class CambioTurno extends Solicitud{
    private Turno turnoActual;
    private Turno turnoSolicitado;
    private Empleado empleadoIntercambio;

    public CambioTurno(Empleado empleadoSolicita, Turno turnoActual, Turno turnoSolicitado) {
        super(empleadoSolicita);
        this.turnoActual = turnoActual;
        this.turnoSolicitado = turnoSolicitado;
        this.empleadoIntercambio = null;
    }

    public CambioTurno(Empleado empleadoSolicita, Turno turnoActual, Turno turnoSolicitado, Empleado empleadoIntercambio) {
        super(empleadoSolicita);
        this.turnoActual = turnoActual;
        this.turnoSolicitado = turnoSolicitado;
        this.empleadoIntercambio = empleadoIntercambio;
    }

    @Override
    public void aprobarSolicitud(Usuario usuario) {
        super.aprobarSolicitud(usuario);
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

    public Turno getTurnoActual() {
        return this.turnoActual;
    }

    public Turno getTurnoSolicitado() {
        return this.turnoSolicitado;
    }

    public Empleado getEmpleadoIntercambio() {
        return this.empleadoIntercambio;
    }    
}
