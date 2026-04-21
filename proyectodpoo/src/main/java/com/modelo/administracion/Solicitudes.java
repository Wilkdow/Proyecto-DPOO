package com.modelo.administracion;

import com.modelo.usuarios.Empleados;

public abstract class Solicitudes {
    Empleados empleadoSolicita;
    boolean aprobada;
    boolean revisada;

    public Solicitudes(Empleados empleadoSolicita) {
        this.empleadoSolicita = empleadoSolicita;
        this.aprobada = false;
        this.revisada = false;
    }

    public void revisarSolicitud() {
        this.revisada = true;
    }

    public void aprobarSolicitud() throws Exception{
        if (this.revisada) {
            this.aprobada = true;
        } else {
            throw new Exception("La solicitud debe ser revisada antes de ser aprobada.");
        }
    }
}
