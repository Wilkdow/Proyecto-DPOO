package com.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.model.administracion.Historial;

public class PersistenciaHistorial {

    public void cargarHistorial(String rutaArchivo, Historial historial) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo));
        String line;
        String currentSection = null;

        while ((line = reader.readLine()) != null) {
            if (line.equals("VENTAS")) {
                currentSection = "VENTAS";
            } else if (line.equals("PRESTAMOS")) {
                currentSection = "PRESTAMOS";
            } else if (line.equals("SOLICITUDES")) {
                currentSection = "SOLICITUDES";
            } else if (currentSection != null && !line.trim().isEmpty()) {
                if ("VENTAS".equals(currentSection)) {
                    historial.agregarVenta(line);
                } else if ("PRESTAMOS".equals(currentSection)) {
                    historial.agregarPrestamo(line);
                } else if ("SOLICITUDES".equals(currentSection)) {
                    historial.agregarSolicitud(line);
                }
            }
        }
        reader.close();
    }

    public void guardarHistorial(String rutaArchivo, Historial historial) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo));

        writer.write("VENTAS\n");
        for (String venta : historial.getHistorialVentas()) {
            writer.write(venta + "\n");
        }

        writer.write("PRESTAMOS\n");
        for (String prestamo : historial.getHistorialPrestamos()) {
            writer.write(prestamo + "\n");
        }

        writer.write("SOLICITUDES\n");
        for (String solicitud : historial.getHistorialSolicitudes()) {
            writer.write(solicitud + "\n");
        }

        writer.close();
    }
}
