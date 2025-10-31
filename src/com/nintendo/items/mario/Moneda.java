package com.nintendo.items.mario;

import com.nintendo.items.enums.Rareza;

/**
 * Clase concreta que representa las monedas del universo de Super Mario Bros.
 * Las monedas son coleccionables que tienen un valor numérico.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Moneda extends ItemMario {

    private int cantidad;

    /**
     * Constructor de la clase Moneda.
     * Crea una moneda con valores predeterminados.
     * La duración siempre es 0 ya que es un ítem coleccionable instantáneo.
     *
     * @param id Identificador único de la moneda
     * @param cantidad Cantidad de monedas (valor numérico)
     */
    public Moneda(int id, int cantidad) {
        super(id, "Moneda", "Moneda dorada coleccionable", Rareza.COMUN, cantidad, 0);
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la cantidad de monedas.
     *
     * @return La cantidad de monedas
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Usa la moneda, simulando su recolección.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        System.out.println("¡Recogiste " + cantidad + " moneda(s)! 🪙");
    }

    /**
     * Devuelve una representación en forma de cadena de la moneda.
     *
     * @return String con la información de la moneda
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Cantidad: %d", cantidad);
    }
}