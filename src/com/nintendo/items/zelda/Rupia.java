package com.nintendo.items.zelda;

import com.nintendo.items.enums.Rareza;

/**
 * Clase concreta que representa las rupias del universo de The Legend of Zelda.
 * Las rupias son la moneda de Hyrule y tienen diferentes colores según su valor.
 * Cada color está asociado a una cantidad específica y una rareza.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Rupia extends ItemZelda {

    private int cantidad;
    private String color;

    /**
     * Constructor de la clase Rupia.
     * El nivel requerido siempre es 1 ya que cualquiera puede recoger rupias.
     * La rareza se determina automáticamente según la cantidad.
     *
     * @param id Identificador único de la rupia
     * @param cantidad Valor numérico de la rupia
     * @param color Color de la rupia (Verde, Azul, Roja, Púrpura, Plata, Oro)
     */
    public Rupia(int id, int cantidad, String color) {
        super(id, "Rupia " + color, "Moneda de Hyrule", determinarRareza(cantidad), cantidad, 1);
        this.cantidad = cantidad;
        this.color = color;
    }

    /**
     * Determina automáticamente la rareza según la cantidad de rupias.
     *
     * @param cantidad Valor de la rupia
     * @return La rareza correspondiente
     */
    private static Rareza determinarRareza(int cantidad) {
        if (cantidad >= 300) return Rareza.LEGENDARIO;
        if (cantidad >= 100) return Rareza.EPICO;
        if (cantidad >= 20) return Rareza.RARO;
        return Rareza.COMUN;
    }

    /**
     * Obtiene la cantidad de rupias.
     *
     * @return El valor numérico de la rupia
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene el color de la rupia.
     *
     * @return El color de la rupia
     */
    public String getColor() {
        return color;
    }

    /**
     * Usa la rupia, simulando su recolección.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        System.out.println("💎 ¡Recogiste una Rupia " + color + "! Valor: " + cantidad);
    }

    /**
     * Devuelve una representación en forma de cadena de la rupia.
     * Incluye información sobre el color y cantidad.
     *
     * @return String con la información completa de la rupia
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Color: %s - Cantidad: %d",
                color, cantidad);
    }
}