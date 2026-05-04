package com.model.usuarios;

import com.model.cafeteria.Inventario;

public class Empleado extends UsuarioActivo{
    public enum Roles {
        MESERO(1), COCINERO(2);

        private int value;

        private Roles(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Roles fromInt(int value) {
            for (Roles rol: Roles.values()) {
                if (rol.getValue() == value)
                    return rol;
            }
            throw new IllegalArgumentException("Valor inválido: " + value);
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
