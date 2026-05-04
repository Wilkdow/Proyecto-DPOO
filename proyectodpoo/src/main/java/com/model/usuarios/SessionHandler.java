package com.model.usuarios;
import com.exceptions.inicio_sesion.ContraseniaIncorrecta;
import com.exceptions.inicio_sesion.UsuarioInexistente;
import com.exceptions.inicio_sesion.UsuarioYaRegistrado;
import com.model.cafeteria.Inventario;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.persistencia.CentralPersistencia;
import com.persistencia.PersistenciaUsuario;

public class SessionHandler {
    private Usuario loggedUsuario;
    private Administrador administrador;
    private Map<String, Cliente> clientes;
    private Map<String, Empleado> empleados;


    public SessionHandler() {
        this.loggedUsuario = null;
        this.administrador = null;
        this.clientes = new HashMap<>();
        this.empleados = new HashMap<>();
    }

    public void setAdministrador(String user, String password) {
        Administrador administrador = new Administrador(user, password);
        this.administrador = administrador;
    }

    public void registrarCliente(String user, String password) throws UsuarioYaRegistrado {
        if (findUsuario(user) != null)
            throw new UsuarioYaRegistrado(password);
        Cliente cliente = new Cliente(user, password);
        clientes.put(user, cliente);
    }
    
    public void agregarCliente(String user, String password, int puntosFidelidad, Set<String> juegosFavoritos) {
        Cliente cliente = new Cliente(user, password);
        cliente.agregarPuntosFidelidad(puntosFidelidad);
        for (String nombreJuego: juegosFavoritos) {
            cliente.agregarJuegoFavorito(nombreJuego);
        }
        clientes.put(user, cliente);    
    }

    public void registrarEmpleado(String user, String password, int rolNumero) throws UsuarioYaRegistrado {
        if (findUsuario(user) != null)
            throw new UsuarioYaRegistrado(password);
        Empleado empleado = new Empleado(user, password, rolNumero);
        empleados.put(user, empleado);
    }

    public void agregarEmpleado(String user, String password, String rolString, int puntosFidelidad, Set<String> juegosFavoritos) {
        Empleado empleado = new Empleado(user, password, rolString);
        empleado.agregarPuntosFidelidad(puntosFidelidad);
        for (String nombreJuego: juegosFavoritos) {
            empleado.agregarJuegoFavorito(nombreJuego);
        }
        empleados.put(user, empleado);
    }

    public void cargarInfoUsuarios(String rutaArchivo) throws IOException {
        PersistenciaUsuario cargador = CentralPersistencia.getPersistenciaUsuario();
        cargador.cargarUsuarios(rutaArchivo, this);
    }

    public void guardarInfoUsuarios(String rutaArchivo) throws IOException {
        PersistenciaUsuario cargador = CentralPersistencia.getPersistenciaUsuario();
        cargador.guardarUsuarios(rutaArchivo, this);
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

    public boolean hayUsuarioLoggeado() {
        return this.loggedUsuario != null;
    }

    public void iniciarSesion(String user, String password) throws UsuarioInexistente, ContraseniaIncorrecta{
        Usuario usuario = findUsuario(user);
        if (usuario == null)
            throw new UsuarioInexistente(user);
        if (!usuario.checkPassword(password))
            throw new ContraseniaIncorrecta(user);
        this.loggedUsuario = usuario;
    }

    public void cerrarSesion() {
        this.loggedUsuario = null;
    }

    public void cambiarContrasenia(String newPassword) {
        if (loggedUsuario == null)
            throw new IllegalArgumentException("No ha iniciado sesión. Inicie Sesión");
        loggedUsuario.setPassword(newPassword);
    }

    // Funciones Auxiliares

    private Usuario findUsuario(String user) {
        Usuario usuario = null;
        if (administrador != null && administrador.getLogin().matches(user))
            usuario = administrador;
        else if (clientes.keySet().contains(user))
            usuario = clientes.get(user);
        else if (empleados.keySet().contains(user))
            usuario = empleados.get(user);
        return usuario;
    }
}
