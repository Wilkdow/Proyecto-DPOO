package com.model.usuarios;

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

    public Empleado(String login, String password, int rolInt) {
        super(login, password);
        this.rol = Roles.fromInt(rolInt);
    }

    public Empleado(String login, String password, String rolString) {
        super(login, password);
        this.rol = Roles.valueOf(rolString);
    }

    public Roles getRol() {
        return rol;
    }

    public boolean esMesero() {
        return rol.equals(Roles.MESERO);
    }

    public boolean esCocinero() {
        return rol.equals(Roles.COCINERO);
    }
}
