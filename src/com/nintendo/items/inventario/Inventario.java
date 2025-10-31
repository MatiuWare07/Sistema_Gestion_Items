package com.nintendo.items.inventario;

import com.nintendo.items.base.Item;
import com.nintendo.items.enums.Rareza;
import com.nintendo.items.enums.UniversoJuego;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que gestiona el inventario de ítems de un jugador.
 * Permite agregar, eliminar, buscar y filtrar ítems con una capacidad máxima.
 * Utiliza Streams de Java 8 para operaciones de filtrado y búsqueda.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Inventario {

    private int capacidadMaxima;
    private List<Item> items;
    private String propietario;

    /**
     * Constructor de la clase Inventario.
     *
     * @param capacidadMaxima Número máximo de ítems que puede contener el inventario
     * @param propietario Nombre del propietario del inventario
     */
    public Inventario(int capacidadMaxima, String propietario) {
        this.capacidadMaxima = capacidadMaxima;
        this.propietario = propietario;
        this.items = new ArrayList<>();
    }

    /**
     * Agrega un ítem al inventario si hay espacio disponible.
     *
     * @param item El ítem a agregar
     * @return true si se agregó correctamente
     * @throws InventarioLlenoException si el inventario está lleno
     */
    public boolean agregarItem(Item item) throws InventarioLlenoException {
        if (estaLleno()) {
            throw new InventarioLlenoException("El inventario de " + propietario + " está lleno!");
        }
        items.add(item);
        System.out.println("✅ " + item.getNombre() + " añadido al inventario de " + propietario);
        return true;
    }

    /**
     * Elimina un ítem del inventario por su ID.
     *
     * @param id El ID del ítem a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarItem(int id) {
        Item itemAEliminar = buscarItemPorId(id);
        if (itemAEliminar != null) {
            items.remove(itemAEliminar);
            System.out.println("❌ " + itemAEliminar.getNombre() + " eliminado del inventario");
            return true;
        }
        System.out.println("⚠️ No se encontró el ítem con ID: " + id);
        return false;
    }

    /**
     * Busca un ítem por su ID.
     *
     * @param id El ID del ítem a buscar
     * @return El ítem encontrado o null si no existe
     */
    public Item buscarItemPorId(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca ítems cuyo nombre contenga el texto especificado (case insensitive).
     *
     * @param nombre El texto a buscar en el nombre
     * @return Lista de ítems que coinciden con la búsqueda
     */
    public List<Item> buscarItemsPorNombre(String nombre) {
        return items.stream()
                .filter(item -> item.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Busca ítems por su rareza.
     *
     * @param rareza La rareza a buscar
     * @return Lista de ítems con la rareza especificada
     */
    public List<Item> buscarItemsPorRareza(Rareza rareza) {
        return items.stream()
                .filter(item -> item.getRareza() == rareza)
                .collect(Collectors.toList());
    }

    /**
     * Busca ítems por su universo de juego.
     *
     * @param universo El universo de juego a buscar (MARIO o ZELDA)
     * @return Lista de ítems del universo especificado
     */
    public List<Item> buscarItemsPorUniverso(UniversoJuego universo) {
        return items.stream()
                .filter(item -> item.getUniverso() == universo)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una copia de todos los ítems del inventario.
     *
     * @return Lista con todos los ítems
     */
    public List<Item> obtenerTodosLosItems() {
        return new ArrayList<>(items);
    }

    /**
     * Verifica si el inventario está lleno.
     *
     * @return true si está lleno, false en caso contrario
     */
    public boolean estaLleno() {
        return items.size() >= capacidadMaxima;
    }

    /**
     * Obtiene la cantidad de ítems actualmente en el inventario.
     *
     * @return Número de ítems
     */
    public int getCapacidadUsada() {
        return items.size();
    }

    /**
     * Obtiene la capacidad máxima del inventario.
     *
     * @return Capacidad máxima
     */
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    /**
     * Obtiene el nombre del propietario del inventario.
     *
     * @return Nombre del propietario
     */
    public String getPropietario() {
        return propietario;
    }

    /**
     * Elimina todos los ítems del inventario.
     */
    public void limpiarInventario() {
        items.clear();
        System.out.println("🗑️ Inventario de " + propietario + " limpiado");
    }

    /**
     * Devuelve una representación visual del inventario.
     * Muestra todos los ítems de forma organizada y legible.
     *
     * @return String con el inventario completo
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n═══════════════════════════════════════════════════\n");
        sb.append("📦 INVENTARIO DE ").append(propietario.toUpperCase()).append("\n");
        sb.append("═══════════════════════════════════════════════════\n");
        sb.append(String.format("Capacidad: %d/%d\n", getCapacidadUsada(), capacidadMaxima));
        sb.append("───────────────────────────────────────────────────\n");

        if (items.isEmpty()) {
            sb.append("El inventario está vacío.\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                sb.append(String.format("%d. %s\n", i + 1, items.get(i).toString()));
            }
        }

        sb.append("═══════════════════════════════════════════════════\n");
        return sb.toString();
    }
}