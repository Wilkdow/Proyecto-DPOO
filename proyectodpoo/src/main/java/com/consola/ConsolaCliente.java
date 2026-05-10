package com.consola;

import com.model.cafeteria.Inventario;
import com.model.cafeteria.Restaurante;
import com.model.productos.JuegoMesa;
import com.model.productos.JuegoMesaPrestamo;
import com.model.productos.JuegoMesaVenta;
import com.model.usuarios.Cliente;
import com.model.usuarios.Usuario;

public class ConsolaCliente extends Consola {
    private Cliente cliente;
    private Restaurante restaurante;
    private Inventario inventario;

    public ConsolaCliente(Usuario cliente, Restaurante restaurante) {
        super();
        this.cliente = (Cliente) cliente;
        this.restaurante = restaurante; 
        this.inventario = restaurante.getInventario();
    }

    public boolean correrConsola() {
        this.salirAplicacion = false;

        ejecutarMenuPrincipal();

        return salirAplicacion;
    }

    private void ejecutarMenuPrincipal() {
        int opcion = ConsolaBasica.mostrarMenu("Menú principal: ", new String[] {"Comprar Juego", "Gestionar Mesa", "Ordenar platos", "Pagar cuenta", "Ver perfil", "Salir"});

        switch (opcion) {
            case 1:
                ejecutarComprarJuego();
                break;

            case 2:
                ejecutarGestionarMesa();
                break;

            case 3:
                
                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;
        
            default:
                salirAplicacion = true;
                return;
        }
        ejecutarMenuPrincipal();
    }

    private JuegoMesa seleccionarJuego(String catalogo) {
        System.out.print(catalogo);
        JuegoMesa juego;
        while (true) {
            String juegoString = ConsolaBasica.pedirCadenaAlUsuario("Ingrese el nombre del juego que desea agregar al carrito");
            juego = inventario.getJuegoMesa(juegoString);
            if (juego != null)
                break;
            System.out.println("Nombre inválido, Intente de vuelta\n");
        }
        return juego;
    }

    private JuegoMesaVenta seleccionarJuegoVenta(String catalogo) {
        System.out.print(catalogo);
        JuegoMesaVenta juego;
        while (true) {
            String juegoString = ConsolaBasica.pedirCadenaAlUsuario("Ingrese el nombre del juego que desea agregar al carrito");
            juego = inventario.getJuegoMesaVenta(juegoString);
            if (juego != null)
                break;
            System.out.println("Nombre inválido, Intente de vuelta\n");
        }
        return juego;
    }

    private JuegoMesaPrestamo seleccionarJuegoPrestamo(String catalogo) {
        System.out.print(catalogo);
        JuegoMesaPrestamo juego;
        while (true) {
            String juegoString = ConsolaBasica.pedirCadenaAlUsuario("Ingrese el nombre del juego que desea agregar al carrito");
            juego = inventario.getJuegoMesaPrestamo(juegoString);
            if (juego != null)
                break;
            System.out.println("Nombre inválido, Intente de vuelta\n");
        }
        return juego;
    }

    private void ejecutarComprarJuego() {
        JuegoMesaVenta juego = seleccionarJuegoVenta(inventario.getCatalogoJuegosVenta());
        if (!ConsolaBasica.pedirConfirmacionAlUsuario("¿Desea agregar el juego " + juego.getNombre() + " a su carrito?"))
            return;
        cliente.comprarJuego(juego);
    }

    private void ejecutarGestionarMesa() {
        if (!cliente.tieneMesa()) {
            if (!ConsolaBasica.pedirConfirmacionAlUsuario("No tiene mesa reservada ¿Desea reservar una?"))
                return;
            int numPersonas = ConsolaBasica.pedirEnteroAlUsuario("Número de personas que van a ir en la mesa");
            boolean hayNinios = ConsolaBasica.pedirConfirmacionAlUsuario("¿Hay niños en la mesa?");
            try {
                cliente.reservarMesa(restaurante, numPersonas, hayNinios);
            } catch (Exception e) {
                System.out.print(e.getMessage());
                return;
            }
        }
        int opcion = ConsolaBasica.mostrarMenu("", new String[] {"Liberar Mesa", "Alquilar Juego", "Devolver Juego Prestado", "Volver"});

        switch (opcion) {
            case 1:
                System.out.println("Liberando la mesa número " + cliente.getNumeroMesa() + "...");
                cliente.liberarMesa();
                break;
            
            case 2:
                ejecutarAlquilarJuego();
                break;

            case 3:
                ejecutarDevolucion();
                break;
                
            default:
                return;
        }
        ejecutarGestionarMesa();
    }

    private void ejecutarDevolucion() {
        int opcion = ConsolaBasica.mostrarMenu("Elija el juego que quiere devolver", cliente.getJuegosPrestadoStrings());
        cliente.devolverJuegoPrestado(opcion);
    }

    private void ejecutarAlquilarJuego() {
        try {
            JuegoMesaPrestamo juego = seleccionarJuegoPrestamo(inventario.getCatalogoJuegosPrestamo());
            cliente.pedirJuegoPrestado(juego);
            System.out.println("El juego " + juego.getNombre() + "fue prestado a la mesa " + cliente.getNumeroMesa());
        } catch (Exception e) {
            System.out.print(e.getMessage());
            ejecutarGestionarMesa();
        }
    }
}
