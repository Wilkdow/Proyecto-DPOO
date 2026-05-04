package com.model.administracion;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.model.usuarios.Empleado;

public class Turno {
    private DayOfWeek dia;
    private ArrayList<Empleado> empleadosAsignados;
    private int horaInicio;
    private int horaFin;

    public Turno(int dia, int horaInicio, int horaFin) {
        this.dia = DayOfWeek.of(dia);
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.empleadosAsignados = new ArrayList<>();
    }

    public Turno(DayOfWeek dia, int horaInicio, int horaFin) {
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

    public ArrayList<Empleado> getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    public String getDia() {
        return this.dia.toString();
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public boolean estaEmpleadoEnTurno(Empleado empleado) {
        return empleadosAsignados.contains(empleado);
    }

    public boolean esTurnoValido() {
        LocalDateTime localTime = LocalDateTime.now();
        DayOfWeek day = localTime.getDayOfWeek();
        int hour = localTime.getHour();
        if (day != dia)
            return false;
        if (horaInicio > hour || horaFin < hour)
            return false;
        return true;
    }

    public boolean esTurnoValido(DayOfWeek day, int hour) {
        if (day != dia)
            return false;
        if (horaInicio > hour || horaFin < hour)
            return false;
        return true;
    }
}