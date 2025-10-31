package com.nintendo.items.mario;

import com.nintendo.items.enums.Rareza;

/**
 * Clase concreta que representa las tuberías del universo de Super Mario Bros.
 * Las tuberías son elementos de transporte que llevan a diferentes destinos.
 * Pueden ser normales o secretas.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Tuberia extends ItemMario {

    private String destino;
    private boolean esSecreta;

    /**
     * Constructor de la clase Tuberia.
     * La duración siempre es 0 ya que el transporte es instantáneo.
     * El valor siempre es 0 ya que las tuberías no son coleccionables.
     *
     * @param id Identificador único de la tubería
     * @param nombre Nombre de la tubería (ej: "Tubería Verde", "Tubería Warp")
     * @param destino Lugar al que transporta la tubería
     * @param esSecreta Indica si la tubería es secreta (oculta o especial)
     */
    public Tuberia(int id, String nombre, String destino, boolean esSecreta) {
        super(id, nombre, "Tubería de transporte",
                esSecreta ? Rareza.RARO : Rareza.COMUN, 0, 0);
        this.destino = destino;
        this.esSecreta = esSecreta;
    }

    /**
     * Obtiene el destino de la tubería.
     *
     * @return El destino al que transporta
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Verifica si la tubería es secreta.
     *
     * @return true si es secreta, false en caso contrario
     */
    public boolean isSecreta() {
        return esSecreta;
    }

    /**
     * Usa la tubería, simulando el transporte al destino.
     * Implementación concreta del método abstracto usar().
     */
    @Override
    public void usar() {
        System.out.println("¡Entrando en " + nombre + "!");
        System.out.println("Destino: " + destino);
        if (esSecreta) {
            System.out.println("✨ ¡Es una tubería secreta!");
        }
    }

    /**
     * Devuelve una representación en forma de cadena de la tubería.
     * Incluye información sobre el destino y si es secreta.
     *
     * @return String con la información completa de la tubería
     */
    @Override
    public String toString() {
        return super.toString() + String.format(" | Destino: %s%s",
                destino, esSecreta ? " (Secreta)" : "");
    }
}