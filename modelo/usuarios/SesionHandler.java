package com.modelo.usuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


import com.modelo.exceptions.UsuarioYaRegistrado;



public class SesionHandler {
    public Map<String, String> users_info;

    public SesionHandler() {
        users_info = new HashMap<>();
    }

    public void cargarInfo(String rutaArchivo) throws FileNotFoundException, IOException{
        File archivo = new File(rutaArchivo);
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        
        try {
            String linea = reader.readLine();
            while (linea != null) {
                if (linea.isEmpty()) {
                    linea = reader.readLine();
                    continue;
                }
                String[] info = linea.split(";");
                String user = info[0];
                String password = info[1];
                users_info.put(user, password);
                linea = reader.readLine();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            reader.close();
        }
    }

    public void guardarInfo(String rutaArchivo, String[] info) throws FileNotFoundException, IOException{
        File archivo = new File(rutaArchivo);
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));
        writer.newLine();
        writer.write(String.join(";", info));
        writer.close();
    }

    public void registrarUsuario(String rutaArchivo, String user, String password) throws Exception{
        if (usuarioValido(user)) {
            throw new UsuarioYaRegistrado(user);
        }
        guardarInfo(rutaArchivo, new String[] {user, password});
    }

    public boolean usuarioValido(String user) {
        return users_info.keySet().contains(user);
    }

    public boolean contraseniaCorrecta(String user, String password) {
        if (!this.usuarioValido(user))
            return false;
        return users_info.get(user).matches(password);
    }
}