package com.modelo.administracion;

import com.modelo.usuarios.Empleados;

public abstract class Solicitudes {
    protected Empleados empleadoSolicita;
    private boolean aprobada;
    private boolean revisada;

    public Solicitudes(Empleados empleadoSolicita) {
        this.empleadoSolicita = empleadoSolicita;
        this.aprobada = false;
        this.revisada = false;
    }

    public void revisarSolicitud() {
        this.revisada = true;
    }

    public void aprobarSolicitud() {
        this.aprobada = true;
    }

    public boolean fueRevisada() {
        return this.revisada;
    }

    public boolean fueAprobada() {
        return this.aprobada;
    }
}
