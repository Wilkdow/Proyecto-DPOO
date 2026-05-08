package com.model.administracion;

import com.model.usuarios.Administrador;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

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
        if (!(usuario instanceof Administrador)) {
            throw new IllegalArgumentException("Solo un administrador puede aprobar esta solicitud.");
        }

        this.revisada = true;
        this.aprobada = false;
    }

    public void aprobarSolicitud(Usuario usuario) {
        if (!(usuario instanceof Administrador)) {
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

    public String imprimirResumen() {
        StringBuilder resumen = new StringBuilder();
        resumen.append("Resumen solicitud");
        resumen.append(String.format("Empleado que solicita: ",this.empleadoSolicita));
        resumen.append(this.aprobada ? "Aprobada": this.revisada ? "En revisión": "Sin revisar");
        resumen.append(String.format("Tipo de solicitud: ", (this instanceof SugerenciaPlato) ? "Sugerencia de Plato": "Cambio de Turno"));
        return resumen.toString();
    }
}
