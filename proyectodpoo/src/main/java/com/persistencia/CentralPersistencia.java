package com.persistencia;

public class CentralPersistencia {
    public static PersistenciaUsuario getPersistenciaUsuario() {
        return new PersistenciaUsuario(); 
    }

    public static PersistenciaInventario getPersistenciaInventario() {
        return new PersistenciaInventario();
    }

    public static PersistenciaHistorial getPersistenciaHistorial() {
        return new PersistenciaHistorial();
    }
}