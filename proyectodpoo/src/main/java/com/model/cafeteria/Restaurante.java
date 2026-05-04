package com.model.cafeteria;

import java.util.ArrayList;

import com.model.administracion.Turno;
import com.model.usuarios.Cliente;
import com.model.usuarios.Empleado;
import com.model.usuarios.Usuario;

public class Restaurante {
    private static int capacidadMaxima = 80;
    
    private Usuario usuario;
    private ArrayList<Cliente> clientes;
    private Turno turnoActivo;

    public Restaurante(Usuario usuario) {
        
    }

    public boolean hayClientes() {
        return !clientes.isEmpty();
    }

    public boolean estaAbierto() {
        if (turnoActivo == null)
            return false;
        return checkEmpleados() && turnoActivo.esTurnoValido();
    }

    private boolean checkEmpleados() {
        int meseros = 0;
        int cocineros = 0;
        for (Empleado empleado: turnoActivo.getEmpleadosAsignados()) {
            if (empleado.esMesero())
                meseros++;
            else if (empleado.esCocinero())
                cocineros++;
        }

        return meseros > 1 && cocineros > 0;
    }
}
