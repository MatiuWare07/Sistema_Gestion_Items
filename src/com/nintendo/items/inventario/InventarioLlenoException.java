package com.nintendo.items.inventario;

/**
 * Excepción personalizada que se lanza cuando se intenta agregar un ítem
 * a un inventario que ya ha alcanzado su capacidad máxima.
 *
 * Extiende de Exception (checked exception), lo que obliga a manejarla
 * con try-catch o declarándola en el método con throws.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class InventarioLlenoException extends Exception {

    /**
     * Constructor de la excepción InventarioLlenoException.
     *
     * @param mensaje Mensaje descriptivo del error
     */
    public InventarioLlenoException(String mensaje) {
        super(mensaje);
    }
}