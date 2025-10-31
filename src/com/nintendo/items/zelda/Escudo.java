package com.nintendo.items.zelda;

import com.nintendo.items.enums.Rareza;

/**
 * Clase concreta que representa los escudos del universo de The Legend of Zelda.
 * Los escudos proporcionan defensa y tienen durabilidad que se desgasta con el uso.
 * Pueden ser reparados para restaurar su durabilidad máxima.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Escudo extends ItemZelda {

    private int defensa;
    private int durabilidad;
    private int durabilidadMaxima;

    /**
     * Constructor de la clase Escudo.
     * La durabilidad inicial será igual a la durabilidad máxima.
     *
     * @param id Identificador único del escudo
     * @param nombre Nombre del escudo (ej: "Hylian Shield")
     * @param descripcion Descripción del escudo
     * @param rareza Nivel de rareza del escudo
     * @param valor Valor económico del escudo
     * @param nivelRequerido Nivel mínimo para usar el escudo
     * @param defensa Puntos de defensa que proporciona el escudo
     * @param durabilidad Durabilidad inicial y máxima del escudo
     */
    public Escudo(int id, String nombre, String descripcion, Rareza rareza, int valor,
                  int nivelRequerido, int defensa, int durabilidad) {
        super(id, nombre, descripcion, rareza, valor, nivelRequerido);
        this.defensa = defensa;
        this.durabilidad = durabilidad;
        this.durabilidadMaxima = durabilidad;
    }

    /**
     * Obtiene la defensa del escudo.
     *
     * @return Puntos de defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Obtiene la durabilidad actual del escudo.
     *
     * @return Durabilidad actual
     */
    public int getDurabilidad() {
        return durabilidad;
    }

    /**
     * Obtiene la durabilidad máxima del escudo.
     *
     * @return Durabilidad máxima
     */
    public int getDurabilidadMaxima() {
        return durabilidadMaxima;
    }

    /**
     * Repara el escudo, restaurando su durabilidad al máximo.
     */
    public void reparar() {
        durabilidad = durabilidadMaxima;
        System.out.println("¡" + nombre + " ha sido reparado completamente!");
    }

    /**
     * Usa el escudo para bloquear, reduciendo su durabilidad.
     * Si la durabilidad llega a 0 o menos, el escudo se rompe.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        if (durabilidad > 0) {
            durabilidad -= 5;
            System.out.println("🛡️ ¡Bloqueando con " + nombre + "! Defensa: " + defensa);
            System.out.println("Durabilidad restante: " + durabilidad + "/" + durabilidadMaxima);

            if (durabilidad <= 0) {
                System.out.println("💔 ¡" + nombre + " se ha roto!");
            }
        } else {
            System.out.println("❌ " + nombre + " está roto. Necesita reparación.");
        }
    }

    /**
     * Devuelve una representación en forma de cadena del escudo.
     * Incluye información sobre defensa, durabilidad y nivel requerido.
     *
     * @return String con la información completa del escudo
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Defensa: %d - Durabilidad: %d/%d - Nivel req: %d",
                defensa, durabilidad, durabilidadMaxima, nivelRequerido);
    }
}