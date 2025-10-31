package com.nintendo.items.enums;

/**
 * Enumeración que representa los diferentes niveles de rareza de los ítems.
 * Cada rareza tiene un nombre descriptivo y un nivel numérico asociado.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */

public enum Rareza {
    COMUN ("Comun", 1),
    RARO ("Raro", 2),
    EPICO ("Epico", 3),
    LEGENDARIO ("Legendario", 4);

    private final String nombre;
    private final int nivel;

    /**
     * Constructor de la enumeración Rareza.
     *
     * @param nombre Nombre descriptivo de la rareza
     * @param nivel Nivel numérico asociado a la rareza (1-4)
     */
    Rareza(String nombre, int nivel){
        this.nombre = nombre;
        this.nivel = nivel;
    }

    /**
     * Obtiene el nombre descriptivo de la rareza.
     *
     * @return El nombre de la rareza
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nivel numérico de la rareza.
     *
     * @return El nivel de la rareza (1 = Común, 4 = Legendario)
     */
    public int getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return nombre + " (Nivel " + nivel + ")";
    }
}
