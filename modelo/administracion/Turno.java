package com.modelo.administracion;

import java.util.ArrayList;

import com.modelo.enumerations.DiasSemana;
import com.modelo.usuarios.Empleado;

public class Turno {
    private DiasSemana dia;
    private ArrayList<Empleado> empleadosAsignados;
    private String horaInicio;
    private String horaFin;

    public Turno(DiasSemana dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleadosAsignados = new ArrayList<>();
    }

    public void asignarEmpleado(Empleado empleado) {
        empleadosAsignados.add(empleado);
    }

    public void removerEmpleado(Empleado empleado) {
        empleadosAsignados.remove(empleado);
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
}