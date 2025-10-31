package com.nintendo.items.enums;

/**
 * Enumeración que representa los diferentes tipos de efectos que pueden tener los ítems.
 * Cada tipo de efecto tiene una descripción asociada que explica su función.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */

public enum TipoEfecto {
    CURACION("Curación"),
    ATAQUE("Ataque"),
    DEFENSA("Defensa"),
    VELOCIDAD("Velocidad"),
    INVENCIBILIDAD("Invencibilidad"),
    MONEDA("Moneda"),
    TRANSPORTE("Transporte");

    private final String descripcion;

    /**
     * Constructor de la enumeración TipoEfecto.
     *
     * @param descripcion Descripción del tipo de efecto
     */
    TipoEfecto(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción del tipo de efecto.
     *
     * @return La descripción del efecto
     */
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Efecto: " + descripcion;
    }
}