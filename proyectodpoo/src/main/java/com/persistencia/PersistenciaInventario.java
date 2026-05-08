package com.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.model.cafeteria.Inventario;
import com.model.productos.Bebida;
import com.model.productos.JuegoMesa;
import com.model.productos.JuegoMesaPrestamo;
import com.model.productos.JuegoMesaVenta;
import com.model.productos.Pastel;
import com.model.productos.Plato;

public class PersistenciaInventario {

    public void cargarInventario(String rutaArchivo, Inventario inventario) throws IOException {
        String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        cargarJuegosMesaPrestamo(inventario, raiz);
        cargarJuegosMesaVenta(inventario, raiz);
        cargarMenu(inventario, raiz);
        cargarCodigosDescuento(inventario, raiz);
    }

    public void guardarUsuarios(String rutaArchivo, Inventario inventario) throws FileNotFoundException{
        JSONObject jobj = new JSONObject();

        guardarJuegosMesaPrestamo(inventario, jobj);
        guardarJuegosMesaVenta(inventario, jobj);
        guardarMenu(inventario, jobj);
        guardarCodigosDescuento(inventario, jobj);

        PrintWriter writer = new PrintWriter(rutaArchivo);
        jobj.write(writer);
        writer.close();
    }

    // Funciones de carga y guardado por lista
    private void cargarJuegosMesaPrestamo(Inventario inventario, JSONObject raiz) {
        JSONArray jJuegos = raiz.getJSONArray("JuegosMesaPrestamo");
        for (int i = 0; i < jJuegos.length(); i++) {
            JSONObject jJuego = jJuegos.getJSONObject(i);
            String nombre = jJuego.getString("nombre");
            int anio = jJuego.getInt("anio");
            String empresa = jJuego.getString("empresa");
            int minJugadores = jJuego.getInt("minJugadores");
            int maxJugadores = jJuego.getInt("maxJugadores");
            String restriccionEdad = jJuego.getString("restriccionEdad");
            String genero = jJuego.getString("genero");
            String estado = jJuego.getString("estado");
            boolean esDificil = jJuego.getBoolean("esDificil");
            int vecesPrestado = jJuego.getInt("vecesPrestado");
            boolean disponible = jJuego.getBoolean("disponible");

            JuegoMesaPrestamo juego = new JuegoMesaPrestamo(nombre, anio, empresa, minJugadores, maxJugadores, restriccionEdad, genero, estado, esDificil);
            juego.setVecesPrestado(vecesPrestado);
            juego.setDisponible(disponible);
            inventario.agregarJuegoMesaPrestamo(juego);
        }
    }

    private void cargarJuegosMesaVenta(Inventario inventario, JSONObject raiz) {
        JSONArray jJuegos = raiz.getJSONArray("JuegosMesaVenta");
        for (int i = 0; i < jJuegos.length(); i++) {
            JSONObject jJuego = jJuegos.getJSONObject(i);
            String nombre = jJuego.getString("nombre");
            int anio = jJuego.getInt("anio");
            String empresa = jJuego.getString("empresa");
            int minJugadores = jJuego.getInt("minJugadores");
            int maxJugadores = jJuego.getInt("maxJugadores");
            String restriccionEdad = jJuego.getString("restriccionEdad");
            String genero = jJuego.getString("genero");
            String estado = jJuego.getString("estado");
            boolean esDificil = jJuego.getBoolean("esDificil");
            int precio = jJuego.getInt("precio");
            int stock = jJuego.getInt("stock");

            JuegoMesaVenta juego = new JuegoMesaVenta(nombre, anio, empresa, minJugadores, maxJugadores, restriccionEdad, genero, estado, esDificil, precio, stock);
            inventario.agregarJuegoMesaVenta(juego);
        }
    }

    private void cargarMenu(Inventario inventario, JSONObject raiz) {
        JSONArray jMenu = raiz.getJSONArray("menu");
        for (int i = 0; i < jMenu.length(); i++) {
            JSONObject jPlato = jMenu.getJSONObject(i);
            Plato plato = cargarPlato(jPlato);
            inventario.agregarPlato(plato);
        }
    }

    private void cargarCodigosDescuento(Inventario inventario, JSONObject raiz) {
        JSONArray jCodigos = raiz.getJSONArray("codigosDescuento");
        for (int i = 0; i < jCodigos.length(); i++) {
            inventario.agregarCodigoDescuento(jCodigos.getString(i));
        }
    }

    private void guardarJuegosMesaPrestamo(Inventario inventario, JSONObject raiz) {
        JSONArray jJuegosP = new JSONArray();
        for (JuegoMesaPrestamo juego: inventario.getJuegosMesaPrestamo()) {
            JSONObject jJuegoP = guardarJuego(juego);
            jJuegoP.put("vecesPrestado", juego.getVecesPrestado());
            jJuegoP.put("disponible", juego.estaDisponible());
            jJuegosP.put(jJuegoP);
        }
        raiz.put("JuegosMesaPrestamo", jJuegosP);
    }

    private void guardarJuegosMesaVenta(Inventario inventario, JSONObject raiz) {
        JSONArray jJuegosV = new JSONArray();
        for (JuegoMesaVenta juego: inventario.getJuegosMesaVenta()) {
            JSONObject jJuegoV = guardarJuego(juego);
            jJuegoV.put("precio", juego.getPrecio());
            jJuegoV.put("stock", juego.getStock());
            jJuegosV.put(jJuegoV);
        }
        raiz.put("JuegosMesaVenta", jJuegosV);
    }

    private void guardarMenu(Inventario inventario, JSONObject raiz) {
        JSONArray jMenu = new JSONArray();
        for (Plato plato: inventario.getMenu()) {
            jMenu.put(guardarPlato(plato));
        }
        raiz.put("menu", jMenu);
    }

    private void guardarCodigosDescuento(Inventario inventario, JSONObject raiz) {
        JSONArray jCodigos = new JSONArray();
        for (String codigo: inventario.getCodigosDescuento()) {
            jCodigos.put(codigo);
        }
        raiz.put("codigosDescuento", jCodigos);
    }

    private JSONObject guardarJuego(JuegoMesa juegoMesa) {
        JSONObject jObj = new JSONObject();
        jObj.put("nombre", juegoMesa.getNombre());
        jObj.put("anio", juegoMesa.getAnioPublicacion());
        jObj.put("empresa", juegoMesa.getEmpresaMatriz());
        jObj.put("minJugadores", juegoMesa.getMinJugadores());
        jObj.put("maxJugadores", juegoMesa.getMaxJugadores());
        jObj.put("restriccionEdad", juegoMesa.getRestriccionEdad());
        jObj.put("genero", juegoMesa.getGenero());
        jObj.put("estado", juegoMesa.getEstado());
        jObj.put("esDificil", juegoMesa.esDificil());
        return jObj;
    }

    private JSONObject guardarPlato(Plato plato) {
        JSONObject jPlato = new JSONObject();
        jPlato.put("nombre", plato.getNombre());
        jPlato.put("descripcion", plato.getDescripcion());
        jPlato.put("precio", plato.getPrecio());

        if (plato instanceof Bebida) {
            Bebida bebida = (Bebida) plato;
            jPlato.put("tipo", "Bebida");
            jPlato.put("esAlcoholica", bebida.esEsAlcoholica());
            jPlato.put("esCaliente", bebida.esCaliente());
        } else if (plato instanceof Pastel) {
            Pastel pastel = (Pastel) plato;
            jPlato.put("tipo", "Pastel");
            JSONArray alergenos = new JSONArray(pastel.getAlergenos());
            jPlato.put("alergenos", alergenos);
        } else {
            throw new IllegalArgumentException("Tipo de plato no soportado: " + plato.getClass().getSimpleName());
        }

        return jPlato;
    }

    private Plato cargarPlato(JSONObject jPlato) {
        String tipo = jPlato.getString("tipo");
        String nombre = jPlato.getString("nombre");
        String descripcion = jPlato.getString("descripcion");
        double precio = jPlato.getDouble("precio");

        if ("Bebida".equals(tipo)) {
            boolean esAlcoholica = jPlato.getBoolean("esAlcoholica");
            boolean esCaliente = jPlato.getBoolean("esCaliente");
            return new Bebida(nombre, descripcion, precio, esAlcoholica, esCaliente);
        } else if ("Pastel".equals(tipo)) {
            JSONArray alergenosJson = jPlato.getJSONArray("alergenos");
            ArrayList<String> alergenos = new ArrayList<>();
            for (int i = 0; i < alergenosJson.length(); i++) {
                alergenos.add(alergenosJson.getString(i));
            }
            return new Pastel(nombre, descripcion, precio, alergenos);
        }

        throw new IllegalArgumentException("Tipo de plato desconocido: " + tipo);
    }
}
