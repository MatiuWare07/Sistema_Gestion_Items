package com.nintendo.items.mario;

import com.nintendo.items.enums.Rareza;
import com.nintendo.items.enums.TipoEfecto;

/**
 * Clase concreta que representa los power-ups del universo de Super Mario Bros.
 * Incluye ítems como Super Mushroom, Fire Flower, Super Star, etc.
 * Cada power-up tiene un tipo de efecto y un multiplicador de potencia.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class PowerUp extends ItemMario {

    private TipoEfecto tipoEfecto;
    private double multiplicador;

    /**
     * Constructor de la clase PowerUp.
     *
     * @param id Identificador único del power-up
     * @param nombre Nombre del power-up (ej: "Fire Flower")
     * @param descripcion Descripción del power-up
     * @param rareza Nivel de rareza del power-up
     * @param valor Valor económico del power-up
     * @param duracion Duración del efecto en segundos
     * @param tipoEfecto Tipo de efecto que produce (ATAQUE, VELOCIDAD, etc.)
     * @param multiplicador Multiplicador de potencia del efecto (ej: 2.0 = doble)
     */
    public PowerUp(int id, String nombre, String descripcion, Rareza rareza, int valor,
                   int duracion, TipoEfecto tipoEfecto, double multiplicador) {
        super(id, nombre, descripcion, rareza, valor, duracion);
        this.tipoEfecto = tipoEfecto;
        this.multiplicador = multiplicador;
    }

    /**
     * Obtiene el tipo de efecto del power-up.
     *
     * @return El tipo de efecto
     */
    public TipoEfecto getTipoEfecto() {
        return tipoEfecto;
    }

    /**
     * Obtiene el multiplicador de potencia del power-up.
     *
     * @return El multiplicador
     */
    public double getMultiplicador() {
        return multiplicador;
    }

    /**
     * Usa el power-up, mostrando información sobre su efecto.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        System.out.println("¡Usando " + nombre + "!");
        System.out.println("Efecto: " + tipoEfecto.getDescripcion() +
                " (x" + multiplicador + ") durante " + duracion + " segundos");
    }

    /**
     * Devuelve una representación en forma de cadena del power-up.
     * Incluye información adicional sobre efecto, multiplicador y duración.
     *
     * @return String con la información completa del power-up
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Efecto: %s (x%.1f) - Duración: %ds",
                tipoEfecto.getDescripcion(), multiplicador, duracion);
    }
}