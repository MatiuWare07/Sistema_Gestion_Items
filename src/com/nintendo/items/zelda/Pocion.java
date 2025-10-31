package com.nintendo.items.zelda;

import com.nintendo.items.enums.Rareza;
import com.nintendo.items.enums.TipoEfecto;

/**
 * Clase concreta que representa las pociones del universo de The Legend of Zelda.
 * Las pociones tienen efectos específicos (curación, velocidad, etc.) y una potencia.
 * Son consumibles de un solo uso.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Pocion extends ItemZelda {

    private TipoEfecto tipoEfecto;
    private int potencia;

    /**
     * Constructor de la clase Pocion.
     *
     * @param id Identificador único de la poción
     * @param nombre Nombre de la poción (ej: "Poción Roja")
     * @param descripcion Descripción de la poción
     * @param rareza Nivel de rareza de la poción
     * @param valor Valor económico de la poción
     * @param nivelRequerido Nivel mínimo para usar la poción
     * @param tipoEfecto Tipo de efecto que produce (CURACION, VELOCIDAD, etc.)
     * @param potencia Potencia del efecto (cantidad de vida, puntos de velocidad, etc.)
     */
    public Pocion(int id, String nombre, String descripcion, Rareza rareza, int valor,
                  int nivelRequerido, TipoEfecto tipoEfecto, int potencia) {
        super(id, nombre, descripcion, rareza, valor, nivelRequerido);
        this.tipoEfecto = tipoEfecto;
        this.potencia = potencia;
    }

    /**
     * Obtiene el tipo de efecto de la poción.
     *
     * @return El tipo de efecto
     */
    public TipoEfecto getTipoEfecto() {
        return tipoEfecto;
    }

    /**
     * Obtiene la potencia de la poción.
     *
     * @return La potencia del efecto
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Usa la poción, aplicando su efecto.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        System.out.println("🧪 ¡Bebiendo " + nombre + "!");
        System.out.println("Efecto: " + tipoEfecto.getDescripcion() + " +" + potencia);
    }

    /**
     * Devuelve una representación en forma de cadena de la poción.
     * Incluye información sobre el efecto, potencia y nivel requerido.
     *
     * @return String con la información completa de la poción
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Efecto: %s +%d - Nivel req: %d",
                tipoEfecto.getDescripcion(), potencia, nivelRequerido);
    }
}