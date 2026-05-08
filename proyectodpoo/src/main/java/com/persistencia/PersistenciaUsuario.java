package com.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.model.usuarios.Cliente;
import com.model.usuarios.Empleado;
import com.model.usuarios.SessionHandler;
import com.model.usuarios.Usuario;

public class PersistenciaUsuario {
    public void cargarUsuarios(String rutaArchivo, SessionHandler sHandler) throws IOException {
        String jsonCompleto = new String(Files.readAllBytes(new File(rutaArchivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        cargarAdmin(sHandler, raiz);
        cargarClientes(sHandler, raiz);
        cargarEmpleados(sHandler, raiz);
    }

    public void guardarUsuarios(String rutaArchivo, SessionHandler sHandler) throws FileNotFoundException{
        JSONObject jobj = new JSONObject();

        guardarAdmin(sHandler, jobj);
        guardarClientes(sHandler, jobj);
        guardarEmpleados(sHandler, jobj);

        PrintWriter writer = new PrintWriter(rutaArchivo);
        jobj.write(writer);
        writer.close();
    }

// Funciones auxiliares

    private void cargarAdmin(SessionHandler sHandler, JSONObject raiz) {
        JSONObject jAdmin = raiz.getJSONObject("administrador");
        sHandler.setAdministrador(jAdmin.getString("user"), jAdmin.getString("password"));
    }

    private void cargarClientes(SessionHandler sHandler, JSONObject raiz) {
        JSONArray jClientes = raiz.getJSONArray("clientes");
        for (int i = 0; i < jClientes.length(); i++) {
            JSONObject jCliente = jClientes.getJSONObject(i);
            String user = jCliente.getString("user");
            String password = jCliente.getString("password");
            int puntosFidelidad = jCliente.getInt("puntosFidelidad");
            Set<String> juegosFavoritos = JSONArrayToSet(jCliente.getJSONArray("juegosFavoritos"));
            sHandler.agregarCliente(user, password, puntosFidelidad, juegosFavoritos);
        }
    }

    private void cargarEmpleados(SessionHandler sHandler, JSONObject raiz) {
        JSONArray jEmpleados = raiz.getJSONArray("empleados");
        for (int i = 0; i < jEmpleados.length(); i++) {
            JSONObject jEmpleado = jEmpleados.getJSONObject(i);
            String user = jEmpleado.getString("user");
            String password = jEmpleado.getString("password");
            String rol = jEmpleado.getString("rol");
            int puntosFidelidad = jEmpleado.getInt("puntosFidelidad");
            Set<String> juegosFavoritos = JSONArrayToSet(jEmpleado.getJSONArray("juegosFavoritos"));
            sHandler.agregarEmpleado(user, password, rol, puntosFidelidad, juegosFavoritos);
        }
    }

    private void guardarAdmin(SessionHandler sHandler, JSONObject raiz) {
        raiz.put("administrador", guardarUsuario(sHandler.getAdministrador()));
    }

    private void guardarClientes(SessionHandler sHandler, JSONObject raiz) {
        JSONArray jClientes = new JSONArray();
        for (Cliente cliente: sHandler.getClientes()) {
            JSONObject jCliente = guardarUsuario(cliente);
            jCliente.put("puntosFidelidad", cliente.getPuntosFidelidad());
            jCliente.put("juegosFavoritos", new JSONArray(cliente.getJuegosFavoritos()));
            jClientes.put(jCliente);
        }
        raiz.put("clientes", jClientes);
    }

    private void guardarEmpleados(SessionHandler sHandler, JSONObject raiz) {
        JSONArray jEmpleados = new JSONArray();
        for (Empleado empleado: sHandler.getEmpleados()) {
            JSONObject jEmpleado = guardarUsuario(empleado);
            jEmpleado.put("rol", empleado.getRol());
            jEmpleado.put("puntosFidelidad", empleado.getPuntosFidelidad());
            jEmpleado.put("juegosFavoritos", new JSONArray(empleado.getJuegosFavoritos()));
            jEmpleados.put(jEmpleado);
        }
        raiz.put("empleados", jEmpleados);
    }

    private JSONObject guardarUsuario(Usuario usuario ) {
        JSONObject jobj = new JSONObject();
        jobj.put("user", usuario.getLogin());
        jobj.put("password", usuario.getPassword());
        return jobj;
    }

    public Set<String> JSONArrayToSet(JSONArray jArray) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < jArray.length(); i++) {
            String jString = jArray.getString(i);
            set.add(jString);
        }
        return set;
    }
}
