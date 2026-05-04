package com.modelo.administracion;

import com.modelo.usuarios.Administrador;
import com.modelo.usuarios.Empleado;
import com.modelo.usuarios.Usuario;

public abstract class Solicitud {
    protected Empleado empleadoSolicita;
    private boolean aprobada;
    private boolean revisada;

    public Solicitud(Empleado empleadoSolicita) {
        this.empleadoSolicita = empleadoSolicita;
        this.aprobada = false;
        this.revisada = false;
    }

    public void rechazarSolicitud(Usuario usuario) {
        if (!usuario.getClass().equals(Administrador.class)) {
            throw new IllegalArgumentException("Solo un administrador puede aprobar esta solicitud.");
        }

        this.revisada = true;
        this.aprobada = false;
    }

    public void aprobarSolicitud(Usuario usuario) {
        if (!usuario.getClass().equals(Administrador.class)) {
            throw new IllegalArgumentException("Solo un administrador puede aprobar esta solicitud.");
        }

        this.revisada = true;
        this.aprobada = true;
    }

    public boolean fueRevisada() {
        return this.revisada;
    }

    public boolean fueAprobada() {
        return this.aprobada;
    }
}
