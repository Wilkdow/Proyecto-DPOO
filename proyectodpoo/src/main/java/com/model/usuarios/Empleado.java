package com.model.usuarios;

import java.util.ArrayList;
import java.util.List;

import com.exceptions.orden_fallida.JuegoOcupado;
import com.exceptions.rol_incumplido.ClientesAtender;
import com.exceptions.rol_incumplido.EmpleadoEnTurno;
import com.exceptions.rol_incumplido.RolIncumplidoException;
import com.model.administracion.CambioTurno;
import com.model.administracion.SugerenciaPlato;
import com.model.administracion.Turno;
import com.model.cafeteria.Inventario;
import com.model.cafeteria.Restaurante;
import com.model.productos.JuegoMesaPrestamo;
import com.model.productos.Plato;

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

    private static final String TIPO_USUARIO = Empleado.class.getSimpleName();

    private Roles rol;
    private List<JuegoMesaPrestamo> juegosPrestados;
    private List<String> codigosDtoGenerados;

    public Empleado(String login, String password, int rolInt) {
        super(login, password);
        this.rol = Roles.fromInt(rolInt);
        this.juegosPrestados = new ArrayList<>();
        this.codigosDtoGenerados = new ArrayList<>();
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

    public void hacerSugerenciaPlatillo(Plato plato, Restaurante restaurante) {
        Inventario inventario = restaurante.getInventario();
        SugerenciaPlato sugerencia = new SugerenciaPlato(this, plato, inventario);
        restaurante.agregarSolicitud(sugerencia);
    }

    public void solicitarCambioTurno(Turno turnoActual, Turno turnoSolicidato, Restaurante restaurante) {
        CambioTurno cambioTurno = new CambioTurno(this, turnoActual, turnoSolicidato);
        restaurante.agregarSolicitud(cambioTurno);
    }

    public void solicitarCambioTurno(Turno turnoActual, Turno turnoSolicidato, Restaurante restaurante, Empleado empleadoIntercambio) {
        CambioTurno cambioTurno = new CambioTurno(this, turnoActual, turnoSolicidato, empleadoIntercambio);
        restaurante.agregarSolicitud(cambioTurno);
    }

    public List<String> getCodigosDescuento() {
        return this.codigosDtoGenerados;
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

    public boolean codigoGeneradoPorEmpleado(String codigo) {
        return codigosDtoGenerados.contains(codigo);
    }

    public void generarCodigoDescuento(Inventario inventario) {
        String codigo = "";
        for (int i = 0; i < 8; i++) {
            int k = (int )Math.round(Math.random() * 10);
            codigo += k;
        }
        codigosDtoGenerados.add(codigo);
        inventario.agregarCodigoDescuento(codigo);
    }
}
