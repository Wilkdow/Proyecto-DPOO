package com.model.productos;

public abstract class JuegoMesa {
    public enum Generos {
        CARTAS, TABLERO, ACCION
    }

    public enum Edades {
        NINIOS, JOVENES, ADULTOS
    }


    private String nombre;
    private int anioPublicacion;
    private String empresaMatriz;
    private int minJugadores;
    private int maxJugadores;
    private Edades restriccionEdad;
    private Generos genero;
    private String estado;
    private boolean esDificil;

    public JuegoMesa(String nombre, int anioPublicacion, String empresaMatriz, int minJugadores, int maxJugadores, String restriccionEdad, String genero, String estado, boolean esDificil) {
        this.nombre = nombre;
        this.anioPublicacion = anioPublicacion;
        this.empresaMatriz = empresaMatriz;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
        this.restriccionEdad = Edades.valueOf(restriccionEdad);
        this.genero = Generos.valueOf(genero);
        this.estado = estado;
        this.esDificil = esDificil;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioPublicacion() {
        return this.anioPublicacion;
    }

    public String getEmpresaMatriz() {
        return this.empresaMatriz;
    }

    public int getMinJugadores() {
        return this.minJugadores;
    }

    public int getMaxJugadores() {
        return this.maxJugadores;
    }
    public String getRestriccionEdad() {
        return restriccionEdad.toString();
    }

    public String getGenero() {
        return genero.toString();
    }

    public String getEstado() {
        return estado;
    }

    public boolean esDificil() {
        return this.esDificil;
    }

    public boolean esAccion() {
        return genero.equals(Generos.ACCION);
    }

    public String imprimirInformacion() {
        return "Nombre: " + nombre + "\n" +
                "Año de Publicación: " + anioPublicacion + "\n" +
                "Empresa Matriz: " + empresaMatriz + "\n" +
                "Número de Jugadores: " + minJugadores + "-" + maxJugadores + "\n" +
                "Restricción de Edad: " + restriccionEdad + "\n" +
                "Género: " + genero + "\n" +
                "Estado: " + estado + "\n" +
                "Es Difícil: " + (esDificil ? "Sí" : "No");
    }
}
