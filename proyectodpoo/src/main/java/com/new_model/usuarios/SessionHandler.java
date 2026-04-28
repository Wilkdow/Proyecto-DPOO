package com.new_model.usuarios;
import com.new_model.cafeteria.Inventario;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.persistencia.CentralPersistencia;
import com.persistencia.PersistenciaUsuario;

public class SessionHandler {
    private Administrador administrador;
    private Map<String, Cliente> clientes;
    private Map<String, Empleado> empleados;
    private Inventario inventario;


    public SessionHandler(Inventario inventario) {
        this.administrador = null;
        this.clientes = new HashMap<>();
        this.empleados = new HashMap<>();
        this.inventario = inventario;
    }

    public void setAdministrador(String user, String password) {
        Administrador administrador = new Administrador(user, password, inventario);
        this.administrador = administrador;
    }

    public void crearCliente(String user, String password) {
        Cliente cliente = new Cliente(user, password, this.inventario);
        clientes.put(user, cliente);
    }
    
    public void agregarCliente(String user, String password, int puntosFidelidad) {
        Cliente cliente = new Cliente(user, password, this.inventario, puntosFidelidad);
        clientes.put(user, cliente);
    }

    public void crearEmpleado(String user, String password) {
        Empleado empleado = new Empleado(user, password, this.inventario);
        empleados.put(user, empleado);
    }

    public void agregarEmpleado(String user, String password, int puntosFidelidad) {
        Empleado empleado = new Empleado(user, password, this.inventario, puntosFidelidad);
        empleados.put(user, empleado);
    }

    public void cargarInfoUsuarios(String rutaArchivo) throws IOException {
        PersistenciaUsuario cargador = CentralPersistencia.getPersistenciaUsuario();
        cargador.cargarUsuarios(rutaArchivo, this);
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public Collection<Cliente> getClientes() {
        return clientes.values();
    }

    public Collection<Empleado> getEmpleados() {
        return empleados.values();
    }

    public boolean iniciarSesion(String user, String password) {
        Usuario usuario = clientes.get(user);
        if (usuario == null) 
            usuario = empleados.get(user);
        if (usuario == null)
            return false;
        return usuario.checkPassword(password); 
    }
}
