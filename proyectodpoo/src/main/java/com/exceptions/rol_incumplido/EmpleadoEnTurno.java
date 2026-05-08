package com.exceptions.rol_incumplido;

public class EmpleadoEnTurno extends RolIncumplidoException {
    private String nombreEmpleado;

    public EmpleadoEnTurno(String nombreEmpleado) {
        super("");
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getMessage() {
        return "El empleado " + nombreEmpleado + " está de turno. Por lo que no puede realizar la acción";
    }
}
