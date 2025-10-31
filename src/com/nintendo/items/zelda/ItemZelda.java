package com.nintendo.items.zelda;

import com.nintendo.items.base.Item;
import com.nintendo.items.enums.Rareza;
import com.nintendo.items.enums.UniversoJuego;

/**
 * Clase abstracta que representa los ítems específicos del universo de The Legend of Zelda.
 * Hereda de Item y añade el atributo de nivel requerido para usar el ítem.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public abstract class ItemZelda extends Item {

    protected int nivelRequerido;

    /**
     * Constructor de la clase ItemZelda.
     * Automáticamente asigna el universo como ZELDA.
     *
     * @param id Identificador único del ítem
     * @param nombre Nombre del ítem
     * @param descripcion Descripción del ítem
     * @param rareza Nivel de rareza del ítem
     * @param valor Valor económico del ítem
     * @param nivelRequerido Nivel mínimo requerido para usar el ítem
     */
    public ItemZelda(int id, String nombre, String descripcion, Rareza rareza,
                     int valor, int nivelRequerido) {
        super(id, nombre, descripcion, rareza, valor, UniversoJuego.ZELDA);
        this.nivelRequerido = nivelRequerido;
    }

    /**
     * Obtiene el nivel requerido para usar el ítem.
     *
     * @return El nivel mínimo requerido
     */
    public int getNivelRequerido() {
        return nivelRequerido;
    }
}