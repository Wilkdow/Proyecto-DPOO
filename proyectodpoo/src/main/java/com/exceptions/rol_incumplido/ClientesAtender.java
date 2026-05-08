package com.exceptions.rol_incumplido;

public class ClientesAtender extends RolIncumplidoException {
    private String nombreEmpleado;

    public ClientesAtender(String nombreEmpleado) {
        super("");
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getMessage() {
        return "Hay clientes por atender en el local. El empleado " + nombreEmpleado + " debería atenderlos";
    }
}
