package com.nintendo.items.enums;

/**
 * Enumeración que representa los diferentes universos de juegos de Nintendo.
 * Cada universo tiene un nombre completo descriptivo.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public enum UniversoJuego {
    MARIO("Super Mario Bros"),
    ZELDA("The Legend of Zelda");

    private final String nombreCompleto;

    /**
     * Constructor de la enumeración UniversoJuego.
     *
     * @param nombreCompleto Nombre completo del universo de juego
     */
    UniversoJuego(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el nombre completo del universo de juego.
     *
     * @return El nombre completo del universo
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
}