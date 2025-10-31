package com.nintendo.items.base;

import com.nintendo.items.interfaces.IItem;
import com.nintendo.items.enums.Rareza;
import com.nintendo.items.enums.UniversoJuego;

/**
 * Clase abstracta base que implementa la interfaz IItem.
 * Proporciona la implementación común para todos los tipos de ítems del sistema.
 * Contiene los atributos y métodos compartidos por todos los ítems.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public abstract class Item implements IItem {

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected Rareza rareza;
    protected int valor;
    protected UniversoJuego universo;

    /**
     * Constructor de la clase Item.
     *
     * @param id Identificador único del ítem
     * @param nombre Nombre del ítem
     * @param descripcion Descripción del ítem
     * @param rareza Nivel de rareza del ítem
     * @param valor Valor económico del ítem
     * @param universo Universo de juego al que pertenece el ítem
     */
    public Item(int id, String nombre, String descripcion, Rareza rareza, int valor, UniversoJuego universo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rareza = rareza;
        this.valor = valor;
        this.universo = universo;
    }

    /**
     * Obtiene el identificador único del ítem.
     *
     * @return El ID del ítem
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Obtiene el nombre del ítem.
     *
     * @return El nombre del ítem
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la descripción del ítem.
     *
     * @return La descripción del ítem
     */
    @Override
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene la rareza del ítem.
     *
     * @return La rareza del ítem
     */
    @Override
    public Rareza getRareza() {
        return rareza;
    }

    /**
     * Obtiene el valor económico del ítem.
     *
     * @return El valor del ítem
     */
    @Override
    public int getValor() {
        return valor;
    }

    /**
     * Obtiene el universo de juego al que pertenece el ítem.
     *
     * @return El universo del ítem
     */
    public UniversoJuego getUniverso() {
        return universo;
    }

    /**
     * Método abstracto que debe ser implementado por las subclases.
     * Define el comportamiento específico al usar el ítem.
     */
    @Override
    public abstract void usar();

    /**
     * Devuelve una representación en forma de cadena del ítem.
     *
     * @return String con la información básica del ítem
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s - Valor: %d",
                universo, nombre, rareza.getNombre(), descripcion, valor);
    }
}