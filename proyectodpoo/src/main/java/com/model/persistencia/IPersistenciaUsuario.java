package com.model.persistencia;

public interface IPersistenciaUsuario {
    public void cargarUsuario(String rutaArchivo);

    public void guardarUsuario(String rutaArchivo);
}
