package com.model.administracion;

import java.util.ArrayList;

import com.model.usuarios.Empleado;

public class Turno {
    public enum DiasSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }
        
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