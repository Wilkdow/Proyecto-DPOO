package com.model.administracion;

import com.model.cafeteria.Inventario;
import com.model.productos.Plato;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

public class SugerenciaPlato extends Solicitud{
    private Plato platoSugerido;
    private Inventario inventario;
    
    public SugerenciaPlato(Empleado empleadoSolicita, Plato platoSugerido, Inventario inventario) {
        super(empleadoSolicita);
        this.platoSugerido = platoSugerido;
    }

    @Override
    public void aprobarSolicitud(Usuario usuario) {
        super.aprobarSolicitud(usuario);

        inventario.agregarPlato(platoSugerido);
    }

    public Plato getPlatoSugerido() {
        return platoSugerido;
    }
}
