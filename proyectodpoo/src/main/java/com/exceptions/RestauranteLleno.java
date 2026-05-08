package com.exceptions;

public class RestauranteLleno extends Exception {
    int capacidadMaxima;
    int cupos;
    public RestauranteLleno(int capacidadMaxima, int cupos) {
        super("");
        this.capacidadMaxima = capacidadMaxima;
        this.cupos = cupos;
    }

    public String getMessage() {
        return "El restaurante esta lleno, tiene una capacidad de " + capacidadMaxima + "personas. Solo quedan " + cupos + " cupos disponibles";
    }
}
