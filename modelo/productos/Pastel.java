package com.modelo.productos;

import java.util.ArrayList;

public class Pastel extends Plato{
    private ArrayList<String> alergenos;

    public Pastel(String nombre, String descripcion, double precio, ArrayList<String> alergenos) {
        super(nombre, descripcion, precio);
        this.alergenos = alergenos;
    }

    public ArrayList<String> getAlergenos() {
        return alergenos;
    }

    public boolean contieneAlergeno(String alergeno) {
        return alergenos.contains(alergeno);
    }
}
