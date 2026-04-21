package com.modelo.administracion;

import java.util.ArrayList;

import com.modelo.enumerations.DiasSemana;
import com.modelo.usuarios.Empleados;

public class Turno {
    DiasSemana dia;
    ArrayList<Empleados> empleadosAsignados;
    String horaInicio;
    String horaFin;

    public Turno(DiasSemana dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleadosAsignados = new ArrayList<>();
    }

    public void asignarEmpleado(Empleados empleado) {
        empleadosAsignados.add(empleado);
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
}