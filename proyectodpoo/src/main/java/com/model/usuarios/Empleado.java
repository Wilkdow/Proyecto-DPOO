package com.model.usuarios;

import com.model.cafeteria.Inventario;

public class Empleado extends UsuarioActivo{
    public enum Roles {
        MESERO, COCINERO;

        private static final Roles[] ENUM = Roles.values();

        public static Roles fromInt(int value) {
            if (value < 1 || value > ENUM.length)
                throw new IllegalArgumentException("Value inválido para Roles: " + value);
            return ENUM[value - 1];
        }
    }

    private Roles rol;

    public Empleado(String login, String password, Inventario inventario, Roles rol) {
        super(login, password, inventario);
        this.rol = rol;
    }

    public Roles getRol() {
        return rol;
    }
}
