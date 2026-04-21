package com.modelo.administracion;

import com.modelo.cafeteria.Inventario;
import com.modelo.productos.Plato;
import com.modelo.usuarios.Empleado;
import com.modelo.usuarios.Usuario;

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
