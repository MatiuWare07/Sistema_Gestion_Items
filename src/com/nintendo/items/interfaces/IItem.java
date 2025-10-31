package com.nintendo.items.interfaces;

import com.nintendo.items.enums.Rareza;

/**
 * Interfaz que define el contrato básico que deben cumplir todos los ítems del sistema.
 * Establece los métodos obligatorios para cualquier objeto que represente un ítem.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public interface IItem{
    /**
     * Obtiene el identificador único del ítem.
     *
     * @return El ID del ítem
     */
    int getId();

    /**
     * Obtiene el nombre del ítem.
     *
     * @return El nombre del ítem
     */
    String getNombre();

    /**
     * Obtiene la descripción del ítem.
     *
     * @return La descripción del ítem
     */
    String getDescripcion();

    /**
     * Obtiene la rareza del ítem.
     *
     * @return La rareza del ítem
     */
    Rareza getRareza();

    /**
     * Obtiene el valor económico del ítem.
     *
     * @return El valor del ítem
     */
    int getValor();

    /**
     * Usa el ítem, ejecutando su efecto o acción específica.
     * Cada implementación definirá su propio comportamiento.
     */
    void usar();
}
