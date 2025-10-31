package com.nintendo.items.mario;

import com.nintendo.items.base.Item;
import com.nintendo.items.enums.Rareza;
import com.nintendo.items.enums.UniversoJuego;

/**
 * Clase abstracta que representa los ítems específicos del universo de Super Mario Bros.
 * Hereda de Item y añade el atributo de duración característico de los power-ups de Mario.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public abstract class ItemMario extends Item {

    protected int duracion; // duración en segundos

    /**
     * Constructor de la clase ItemMario.
     * Automáticamente asigna el universo como MARIO.
     *
     * @param id Identificador único del ítem
     * @param nombre Nombre del ítem
     * @param descripcion Descripción del ítem
     * @param rareza Nivel de rareza del ítem
     * @param valor Valor económico del ítem
     * @param duracion Duración del efecto del ítem en segundos (0 si es permanente)
     */
    public ItemMario(int id, String nombre, String descripcion, Rareza rareza, int valor, int duracion) {
        super(id, nombre, descripcion, rareza, valor, UniversoJuego.MARIO);
        this.duracion = duracion;
    }

    /**
     * Obtiene la duración del efecto del ítem.
     *
     * @return La duración en segundos (0 si no tiene duración limitada)
     */
    public int getDuracion() {
        return duracion;
    }
}