package com.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.model.administracion.Historial;
import com.model.administracion.Solicitud;
import com.model.administracion.SugerenciaPlato;
import com.model.cafeteria.Inventario;
import com.model.cafeteria.Mesa;
import com.model.cafeteria.Prestamo;
import com.model.productos.JuegoMesaPrestamo;
import com.model.productos.JuegoMesaVenta;
import com.model.productos.Plato;
import com.model.usuarios.Cliente;
import com.model.usuarios.Empleado;
import com.model.usuarios.SessionHandler;
import com.model.usuarios.Usuario;
import com.model.usuarios.UsuarioActivo;
import com.model.ventas.Venta;
import com.model.ventas.VentaCafeteria;
import com.model.ventas.VentaJuegos;

public class PersistenciaHistorial {

    public void cargarHistorial(String rutaArchivo, Historial historial, SessionHandler sHandler, Inventario inventario) throws IOException {
        String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        cargarVentas(historial, raiz, sHandler, inventario);
        cargarPrestamos(historial, raiz, sHandler, inventario);
        cargarSolicitudes(historial, raiz, sHandler, inventario);
    }

    public void guardarHistorial(String rutaArchivo, Historial historial) throws FileNotFoundException {
        JSONObject raiz = new JSONObject();

        guardarVentas(historial, raiz);
        guardarPrestamos(historial, raiz);
        guardarSolicitudes(historial, raiz);

        PrintWriter writer = new PrintWriter(rutaArchivo);
        raiz.write(writer);
        writer.close();
    }

    private void cargarVentas(Historial historial, JSONObject raiz, SessionHandler sHandler, Inventario inventario) {
        if (!raiz.has("ventas")) {
            return;
        }
        JSONArray jVentas = raiz.getJSONArray("ventas");
        for (int i = 0; i < jVentas.length(); i++) {
            JSONObject jVenta = jVentas.getJSONObject(i);
            Venta venta = cargarVenta(jVenta, sHandler, inventario);
            historial.agregarVenta(venta);
        }
    }

    private void cargarPrestamos(Historial historial, JSONObject raiz, SessionHandler sHandler, Inventario inventario) {
        if (!raiz.has("prestamos")) {
            return;
        }
        JSONArray jPrestamos = raiz.getJSONArray("prestamos");
        for (int i = 0; i < jPrestamos.length(); i++) {
            JSONObject jPrestamo = jPrestamos.getJSONObject(i);
            Prestamo prestamo = cargarPrestamo(jPrestamo, sHandler, inventario);
            historial.agregarPrestamo(prestamo);
        }
    }

    private void cargarSolicitudes(Historial historial, JSONObject raiz, SessionHandler sHandler, Inventario inventario) {
        if (!raiz.has("solicitudes")) {
            return;
        }
        JSONArray jSolicitudes = raiz.getJSONArray("solicitudes");
        for (int i = 0; i < jSolicitudes.length(); i++) {
            JSONObject jSolicitud = jSolicitudes.getJSONObject(i);
            Solicitud solicitud = cargarSolicitud(jSolicitud, sHandler, inventario);
            historial.agregarSolicitud(solicitud);
        }
    }

    private void guardarVentas(Historial historial, JSONObject raiz) {
        JSONArray jVentas = new JSONArray();
        for (Venta venta : historial.getHistorialVentas()) {
            jVentas.put(guardarVenta(venta));
        }
        raiz.put("ventas", jVentas);
    }

    private void guardarPrestamos(Historial historial, JSONObject raiz) {
        JSONArray jPrestamos = new JSONArray();
        for (Prestamo prestamo : historial.getHistorialPrestamos()) {
            jPrestamos.put(guardarPrestamo(prestamo));
        }
        raiz.put("prestamos", jPrestamos);
    }

    private void guardarSolicitudes(Historial historial, JSONObject raiz) {
        JSONArray jSolicitudes = new JSONArray();
        for (Solicitud solicitud : historial.getHistorialSolicitudes()) {
            jSolicitudes.put(guardarSolicitud(solicitud));
        }
        raiz.put("solicitudes", jSolicitudes);
    }

    private Venta cargarVenta(JSONObject jVenta, SessionHandler sHandler, Inventario inventario) {
        String tipo = jVenta.getString("tipo");
        String compradorLogin = jVenta.getString("comprador");
        UsuarioActivo comprador = buscarUsuarioActivo(sHandler, compradorLogin);

        if ("VentaJuegos".equals(tipo)) {
            return cargarVentaJuegos(jVenta, comprador, inventario);
        }
        if ("VentaCafeteria".equals(tipo)) {
            return cargarVentaCafeteria(jVenta, comprador, inventario);
        }
        throw new IllegalArgumentException("Tipo de venta desconocido: " + tipo);
    }

    private VentaJuegos cargarVentaJuegos(JSONObject jVenta, UsuarioActivo comprador, Inventario inventario) {
        VentaJuegos venta = new VentaJuegos(comprador);
        JSONArray jJuegos = jVenta.getJSONArray("juegosVendidos");
        for (int i = 0; i < jJuegos.length(); i++) {
            String nombreJuego = jJuegos.getString(i);
            JuegoMesaVenta juego = inventario.getJuegoMesaVenta(nombreJuego);
            venta.agregarJuegoMesaVenta(juego);
        }
        ajustarVenta(venta, jVenta);
        return venta;
    }

    private VentaCafeteria cargarVentaCafeteria(JSONObject jVenta, UsuarioActivo comprador, Inventario inventario) {
        VentaCafeteria venta = new VentaCafeteria(comprador);
        JSONArray jPlatos = jVenta.getJSONArray("platosPedidos");
        for (int i = 0; i < jPlatos.length(); i++) {
            String nombrePlato = jPlatos.getString(i);
            Plato plato = inventario.getPlato(nombrePlato);
            venta.ordenarPlato(plato);
        }
        setField(venta, "propina", jVenta.getDouble("propina"));
        ajustarVenta(venta, jVenta);
        return venta;
    }

    private void ajustarVenta(Venta venta, JSONObject jVenta) {
        if (!jVenta.isNull("fecha")) {
            setField(venta, "fecha", LocalDateTime.parse(jVenta.getString("fecha")));
        }
        setField(venta, "valorNeto", jVenta.getDouble("valorNeto"));
        setField(venta, "valorTotal", jVenta.getDouble("valorTotal"));
        setField(venta, "puntosGenerados", jVenta.getDouble("puntosGenerados"));
        setField(venta, "descuentoPuntos", jVenta.getDouble("descuentoPuntos"));
        setField(venta, "descuentoPorcentaje", jVenta.getDouble("descuentoPorcentaje"));
    }

    private Prestamo cargarPrestamo(JSONObject jPrestamo, SessionHandler sHandler, Inventario inventario) {
        int numeroMesa = jPrestamo.getInt("numeroMesa");
        String prestatarioLogin = jPrestamo.getString("prestatario");
        Usuario prestatario = buscarUsuario(sHandler, prestatarioLogin);
        Mesa mesa = new Mesa(numeroMesa);
        mesa.reservarMesa(prestatario, 0, false);
        Prestamo prestamo = new Prestamo(mesa);

        JSONArray jJuegos = jPrestamo.getJSONArray("juegosPrestados");
        for (int i = 0; i < jJuegos.length(); i++) {
            String nombreJuego = jJuegos.getString(i);
            JuegoMesaPrestamo juego = inventario.getJuegoMesaPrestamo(nombreJuego);
            try {
                prestamo.prestarJuego(juego);
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar préstamo: " + nombreJuego, e);
            }
        }
        setField(prestamo, "fechaPrestamo", LocalDate.parse(jPrestamo.getString("fechaPrestamo")));
        setField(prestamo, "devuelto", jPrestamo.getBoolean("devuelto"));
        if (!jPrestamo.isNull("fechaDevolucion")) {
            setField(prestamo, "fechaDevolucion", LocalDate.parse(jPrestamo.getString("fechaDevolucion")));
        }
        return prestamo;
    }

    private Solicitud cargarSolicitud(JSONObject jSolicitud, SessionHandler sHandler, Inventario inventario) {
        String tipo = jSolicitud.getString("tipo");
        String empleadoLogin = jSolicitud.getString("empleado");
        Empleado empleado = buscarEmpleado(sHandler, empleadoLogin);

        if ("SugerenciaPlato".equals(tipo)) {
            String nombrePlato = jSolicitud.getString("plato");
            Plato plato = inventario.getPlato(nombrePlato);
            SugerenciaPlato solicitud = new SugerenciaPlato(empleado, plato, inventario);
            setField(solicitud, "inventario", inventario);
            setSolicitudEstado(solicitud, jSolicitud.getBoolean("revisada"), jSolicitud.getBoolean("aprobada"));
            return solicitud;
        }
        throw new IllegalArgumentException("Tipo de solicitud desconocido: " + tipo);
    }

    private JSONObject guardarVenta(Venta venta) {
        JSONObject jVenta = new JSONObject();
        jVenta.put("tipo", venta.getClass().getSimpleName());
        jVenta.put("comprador", venta.getComprador().getLogin());
        jVenta.put("fecha", venta.getFecha() == null ? JSONObject.NULL : venta.getFecha().toString());
        jVenta.put("valorNeto", venta.getValorNeto());
        jVenta.put("valorTotal", venta.getValorTotal());
        jVenta.put("puntosGenerados", venta.getPuntosGenerados());
        jVenta.put("descuentoPuntos", getDoubleField(venta, "descuentoPuntos"));
        jVenta.put("descuentoPorcentaje", getDoubleField(venta, "descuentoPorcentaje"));

        if (venta instanceof VentaJuegos) {
            VentaJuegos ventaJuegos = (VentaJuegos) venta;
            JSONArray jJuegos = new JSONArray();
            for (JuegoMesaVenta juego : ventaJuegos.getJuegosMesaVendidos()) {
                jJuegos.put(juego.getNombre());
            }
            jVenta.put("juegosVendidos", jJuegos);
            return jVenta;
        }

        if (venta instanceof VentaCafeteria) {
            VentaCafeteria ventaCafeteria = (VentaCafeteria) venta;
            JSONArray jPlatos = new JSONArray();
            for (Plato plato : ventaCafeteria.getPlatosPedidos()) {
                jPlatos.put(plato.getNombre());
            }
            jVenta.put("platosPedidos", jPlatos);
            jVenta.put("propina", getDoubleField(ventaCafeteria, "propina"));
            return jVenta;
        }

        throw new IllegalArgumentException("Tipo de venta no soportado: " + venta.getClass().getName());
    }

    private JSONObject guardarPrestamo(Prestamo prestamo) {
        JSONObject jPrestamo = new JSONObject();
        jPrestamo.put("numeroMesa", getField(prestamo.getClass().getSuperclass(), "mesa").getInt(prestamo));
        jPrestamo.put("prestatario", prestamo.getPrestatario().getLogin());
        jPrestamo.put("fechaPrestamo", prestamo.getFechaPrestamo().toString());
        jPrestamo.put("devuelto", prestamo.fueDevuelto());
        jPrestamo.put("fechaDevolucion", prestamo.getFechaDevolucion() == null ? JSONObject.NULL : prestamo.getFechaDevolucion().toString());

        JSONArray jJuegos = new JSONArray();
        for (JuegoMesaPrestamo juego : prestamo.getJuegosPrestados()) {
            jJuegos.put(juego.getNombre());
        }
        jPrestamo.put("juegosPrestados", jJuegos);
        return jPrestamo;
    }

    private JSONObject guardarSolicitud(Solicitud solicitud) {
        JSONObject jSolicitud = new JSONObject();
        jSolicitud.put("tipo", solicitud.getClass().getSimpleName());
        jSolicitud.put("revisada", solicitud.fueRevisada());
        jSolicitud.put("aprobada", solicitud.fueAprobada());

        if (solicitud instanceof SugerenciaPlato) {
            SugerenciaPlato sugerencia = (SugerenciaPlato) solicitud;
            jSolicitud.put("empleado", getEmpleadoLogin(solicitud));
            jSolicitud.put("plato", sugerencia.getPlatoSugerido().getNombre());
            return jSolicitud;
        }

        throw new IllegalArgumentException("Tipo de solicitud no soportado: " + solicitud.getClass().getName());
    }

    private String getEmpleadoLogin(Solicitud solicitud) {
        try {
            Usuario empleado = (Usuario) getField(solicitud, "empleadoSolicita");
            return empleado.getLogin();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener el empleado de la solicitud", e);
        }
    }

    private UsuarioActivo buscarUsuarioActivo(SessionHandler sHandler, String login) {
        UsuarioActivo usuario = null;
        for (Cliente cliente : sHandler.getClientes()) {
            if (cliente.getLogin().equals(login)) {
                usuario = cliente;
                break;
            }
        }
        if (usuario == null) {
            for (Empleado empleado : sHandler.getEmpleados()) {
                if (empleado.getLogin().equals(login)) {
                    usuario = empleado;
                    break;
                }
            }
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario activo no encontrado: " + login);
        }
        return usuario;
    }

    private Usuario buscarUsuario(SessionHandler sHandler, String login) {
        if (sHandler.getAdministrador() != null && sHandler.getAdministrador().getLogin().equals(login)) {
            return sHandler.getAdministrador();
        }
        for (Cliente cliente : sHandler.getClientes()) {
            if (cliente.getLogin().equals(login)) {
                return cliente;
            }
        }
        for (Empleado empleado : sHandler.getEmpleados()) {
            if (empleado.getLogin().equals(login)) {
                return empleado;
            }
        }
        throw new IllegalArgumentException("Usuario no encontrado: " + login);
    }

    private Empleado buscarEmpleado(SessionHandler sHandler, String login) {
        for (Empleado empleado : sHandler.getEmpleados()) {
            if (empleado.getLogin().equals(login)) {
                return empleado;
            }
        }
        throw new IllegalArgumentException("Empleado no encontrado: " + login);
    }

    private void setSolicitudEstado(Solicitud solicitud, boolean revisada, boolean aprobada) {
        setField(solicitud, "revisada", revisada);
        setField(solicitud, "aprobada", aprobada);
    }

    private Object getField(Object target, String fieldName) {
        try {
            Field field = findField(target.getClass(), fieldName);
            field.setAccessible(true);
            return field.get(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> current = clazz;
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                current = current.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    private void setField(Object target, String fieldName, Object value) {
        try {
            Field field = findField(target.getClass(), fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double getDoubleField(Object target, String fieldName) {
        Object value = getField(target, fieldName);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        throw new IllegalArgumentException("Campo no numérico: " + fieldName);
    }
}
