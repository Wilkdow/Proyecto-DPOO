package com.modelo.administracion;

import java.util.ArrayList;

import com.modelo.enumerations.DiasSemana;
import com.modelo.usuarios.Empleados;

public class Turno {
    private DiasSemana dia;
    private ArrayList<Empleados> empleadosAsignados;
    private String horaInicio;
    private String horaFin;

    public Turno(DiasSemana dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleadosAsignados = new ArrayList<>();
    }

    public void asignarEmpleado(Empleados empleado) {
        empleadosAsignados.add(empleado);
    }

    public void removerEmpleado(Empleados empleado) {
        empleadosAsignados.remove(empleado);
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
}