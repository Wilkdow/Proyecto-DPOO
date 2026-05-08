package com.model.usuarios;

import java.util.ArrayList;
import java.util.List;

import com.exceptions.orden_fallida.JuegoOcupado;
import com.exceptions.rol_incumplido.ClientesAtender;
import com.exceptions.rol_incumplido.EmpleadoEnTurno;
import com.exceptions.rol_incumplido.RolIncumplidoException;
import com.model.cafeteria.Restaurante;
import com.model.productos.JuegoMesaPrestamo;

public class Empleado extends UsuarioActivo{
    public enum Roles {
        MESERO, COCINERO;

        private static final Roles[] ENUM = Roles.values();

        public static Roles fromInt(int value) {
            if (value < 1 || value > ENUM.length)
                throw new IllegalArgumentException("Value inválido para Roles: " + value);
            return ENUM[value - 1];
        }
    }

    private Roles rol;
    private List<JuegoMesaPrestamo> juegosPrestados;

    public Empleado(String login, String password, int rolInt) {
        super(login, password);
        this.rol = Roles.fromInt(rolInt);
        this.juegosPrestados = new ArrayList<>();
    }

    public Empleado(String login, String password, String rolString) {
        super(login, password);
        this.rol = Roles.valueOf(rolString);
    }

    public void pedirJuegoPrestado(Restaurante restaurante, JuegoMesaPrestamo juegoMesa) throws RolIncumplidoException, JuegoOcupado{
        if (restaurante.estaEmpleadoEnTurno(this))
            throw new EmpleadoEnTurno(getLogin());
        if (restaurante.hayClientes())
            throw new ClientesAtender(getLogin());
        juegoMesa.prestar();
        juegosPrestados.add(juegoMesa);
    }

    public void devolverJuegoPrestado(int juegoIndice) {
        JuegoMesaPrestamo juego = juegosPrestados.get(juegoIndice);
        juego.devolver();
        juegosPrestados.remove(juego);
    }

    public String getRol() {
        return rol.toString();
    }

    public boolean esMesero() {
        return rol.equals(Roles.MESERO);
    }

    public boolean esCocinero() {
        return rol.equals(Roles.COCINERO);
    }
}
