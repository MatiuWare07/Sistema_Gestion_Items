package com.nintendo.items.zelda;

import com.nintendo.items.enums.Rareza;

/**
 * Clase concreta que representa las armas del universo de The Legend of Zelda.
 * Las armas tienen daño y durabilidad que se desgasta con el uso.
 * Pueden ser reparadas para restaurar su durabilidad máxima.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Arma extends ItemZelda {

    private int danio;
    private int durabilidad;
    private int durabilidadMaxima;

    /**
     * Constructor de la clase Arma.
     * La durabilidad inicial será igual a la durabilidad máxima.
     *
     * @param id Identificador único del arma
     * @param nombre Nombre del arma (ej: "Master Sword")
     * @param descripcion Descripción del arma
     * @param rareza Nivel de rareza del arma
     * @param valor Valor económico del arma
     * @param nivelRequerido Nivel mínimo para usar el arma
     * @param danio Puntos de daño que inflige el arma
     * @param durabilidad Durabilidad inicial y máxima del arma
     */
    public Arma(int id, String nombre, String descripcion, Rareza rareza, int valor,
                int nivelRequerido, int danio, int durabilidad) {
        super(id, nombre, descripcion, rareza, valor, nivelRequerido);
        this.danio = danio;
        this.durabilidad = durabilidad;
        this.durabilidadMaxima = durabilidad;
    }

    /**
     * Obtiene el daño del arma.
     *
     * @return Puntos de daño
     */
    public int getDanio() {
        return danio;
    }

    /**
     * Obtiene la durabilidad actual del arma.
     *
     * @return Durabilidad actual
     */
    public int getDurabilidad() {
        return durabilidad;
    }

    /**
     * Obtiene la durabilidad máxima del arma.
     *
     * @return Durabilidad máxima
     */
    public int getDurabilidadMaxima() {
        return durabilidadMaxima;
    }

    /**
     * Repara el arma, restaurando su durabilidad al máximo.
     */
    public void reparar() {
        durabilidad = durabilidadMaxima;
        System.out.println("¡" + nombre + " ha sido reparada completamente!");
    }

    /**
     * Usa el arma para atacar, reduciendo su durabilidad.
     * Si la durabilidad llega a 0 o menos, el arma se rompe.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        if (durabilidad > 0) {
            durabilidad -= 10;
            System.out.println("⚔️ ¡Atacando con " + nombre + "! Daño: " + danio);
            System.out.println("Durabilidad restante: " + durabilidad + "/" + durabilidadMaxima);

            if (durabilidad <= 0) {
                System.out.println("💔 ¡" + nombre + " se ha roto!");
            }
        } else {
            System.out.println("❌ " + nombre + " está rota. Necesita reparación.");
        }
    }

    /**
     * Devuelve una representación en forma de cadena del arma.
     * Incluye información sobre daño, durabilidad y nivel requerido.
     *
     * @return String con la información completa del arma
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Daño: %d - Durabilidad: %d/%d - Nivel req: %d",
                danio, durabilidad, durabilidadMaxima, nivelRequerido);
    }
}