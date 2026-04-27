package com.consola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiFunction;

import com.modelo.cafeteria.Cafeteria;
import com.modelo.usuarios.SesionHandler;

public class Logic {
    private static final String ARCHIVO_INICIO_SESION = "proyectodpoo\\data\\users_info.txt";

    private Cafeteria cafeteria;
    private SesionHandler sesionHandler;

    public Logic() throws FileNotFoundException, IOException{
        cafeteria = new Cafeteria();
        sesionHandler = new SesionHandler();
        sesionHandler.cargarInfo(ARCHIVO_INICIO_SESION);
    }

    public boolean inicioSesionValido(String user, String password) {
        return sesionHandler.contraseniaCorrecta(user, password);
    }

    public boolean userValido(String user) {
        return sesionHandler.usuarioValido(user);
    }

    public void registrarUsuario(String user, String password) throws Exception{
        sesionHandler.registrarUsuario(ARCHIVO_INICIO_SESION, user, password);
    }
}
