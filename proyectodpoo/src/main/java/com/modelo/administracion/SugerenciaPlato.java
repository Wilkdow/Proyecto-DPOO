package com.modelo.administracion;

import com.modelo.platos.Plato;
import com.modelo.usuarios.Empleados;

public class SugerenciaPlato extends Solicitudes{
    private Plato platoSugerido;
    
    public SugerenciaPlato(Empleados empleadoSolicita, Plato platoSugerido) {
        super(empleadoSolicita);
        this.platoSugerido = platoSugerido;
    }

    public Plato getPlatoSugerido() {
        return platoSugerido;
    }
}
