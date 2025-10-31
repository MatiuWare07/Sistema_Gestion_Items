package com.nintendo.items;

import com.nintendo.items.inventario.Inventario;
import com.nintendo.items.inventario.InventarioLlenoException;
import com.nintendo.items.mario.*;
import com.nintendo.items.zelda.*;
import com.nintendo.items.enums.*;
import com.nintendo.items.base.Item;
import java.util.List;

/**
 * Clase principal del Sistema de Gestión de Ítems de Mario y Zelda.
 * Contiene el método main que prueba todas las funcionalidades del sistema.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("🎮 SISTEMA DE GESTIÓN DE ÍTEMS - MARIO & ZELDA 🎮");
        System.out.println("═══════════════════════════════════════════════════════════\n");

        try {
            // ============================================
            // CREAR INVENTARIOS
            // ============================================
            Inventario inventarioMario = new Inventario(15, "Mario");
            Inventario inventarioLink = new Inventario(15, "Link");

            // ============================================
            // CREAR ITEMS DE MARIO
            // ============================================
            System.out.println("\n🍄 === CREANDO ITEMS DE MARIO ===\n");

            PowerUp superMushroom = new PowerUp(1, "Super Mushroom",
                    "Te hace más grande y fuerte", Rareza.COMUN, 10, 0, TipoEfecto.ATAQUE, 2.0);

            PowerUp fireFlower = new PowerUp(2, "Fire Flower",
                    "Lanza bolas de fuego", Rareza.RARO, 25, 30, TipoEfecto.ATAQUE, 3.0);

            PowerUp starMan = new PowerUp(3, "Super Star",
                    "Invencibilidad temporal", Rareza.LEGENDARIO, 100, 10, TipoEfecto.INVENCIBILIDAD, 5.0);

            Moneda moneda1 = new Moneda(4, 1);
            Moneda moneda10 = new Moneda(5, 10);

            Tuberia tuberiaVerde = new Tuberia(6, "Tubería Verde", "Mundo Subterráneo", false);
            Tuberia tuberiaSecreta = new Tuberia(7, "Tubería Warp", "Mundo 8", true);

            // Agregar items de Mario al inventario
            inventarioMario.agregarItem(superMushroom);
            inventarioMario.agregarItem(fireFlower);
            inventarioMario.agregarItem(starMan);
            inventarioMario.agregarItem(moneda1);
            inventarioMario.agregarItem(moneda10);
            inventarioMario.agregarItem(tuberiaVerde);
            inventarioMario.agregarItem(tuberiaSecreta);

            // ============================================
            // CREAR ITEMS DE ZELDA
            // ============================================
            System.out.println("\n⚔️ === CREANDO ITEMS DE ZELDA ===\n");

            Arma masterSword = new Arma(8, "Master Sword",
                    "La espada legendaria que sella la oscuridad", Rareza.LEGENDARIO,
                    10000, 10, 50, 100);

            Arma kokiriSword = new Arma(9, "Kokiri Sword",
                    "Espada básica de los Kokiri", Rareza.COMUN, 50, 1, 10, 50);

            Escudo hylianShield = new Escudo(10, "Hylian Shield",
                    "Escudo resistente de Hyrule", Rareza.EPICO, 500, 5, 30, 80);

            Pocion pocionRoja = new Pocion(11, "Poción Roja",
                    "Restaura toda la vida", Rareza.RARO, 100, 1, TipoEfecto.CURACION, 100);

            Pocion pocionVerde = new Pocion(12, "Poción Verde",
                    "Restaura parte de la vida", Rareza.COMUN, 30, 1, TipoEfecto.CURACION, 50);

            Rupia rupiaVerde = new Rupia(13, 1, "Verde");
            Rupia rupiaAzul = new Rupia(14, 5, "Azul");
            Rupia rupiaRoja = new Rupia(15, 20, "Roja");
            Rupia rupiaPurpura = new Rupia(16, 50, "Púrpura");
            Rupia rupiaPlata = new Rupia(17, 100, "Plata");
            Rupia rupiaOro = new Rupia(18, 300, "Oro");

            // Agregar items de Zelda al inventario
            inventarioLink.agregarItem(masterSword);
            inventarioLink.agregarItem(kokiriSword);
            inventarioLink.agregarItem(hylianShield);
            inventarioLink.agregarItem(pocionRoja);
            inventarioLink.agregarItem(pocionVerde);
            inventarioLink.agregarItem(rupiaVerde);
            inventarioLink.agregarItem(rupiaAzul);
            inventarioLink.agregarItem(rupiaRoja);
            inventarioLink.agregarItem(rupiaPurpura);
            inventarioLink.agregarItem(rupiaPlata);
            inventarioLink.agregarItem(rupiaOro);

            // ============================================
            // MOSTRAR INVENTARIOS
            // ============================================
            System.out.println(inventarioMario);
            System.out.println(inventarioLink);

            // ============================================
            // PROBAR FUNCIONALIDADES
            // ============================================
            System.out.println("\n🎯 === PROBANDO FUNCIONALIDADES ===\n");

            // Usar items de Mario
            System.out.println("┌─────────────────────────────────────┐");
            System.out.println("│  USANDO ITEMS DE MARIO              │");
            System.out.println("└─────────────────────────────────────┘\n");
            superMushroom.usar();
            System.out.println();
            fireFlower.usar();
            System.out.println();
            starMan.usar();
            System.out.println();
            tuberiaSecreta.usar();
            System.out.println();

            // Usar items de Zelda
            System.out.println("┌─────────────────────────────────────┐");
            System.out.println("│  USANDO ITEMS DE ZELDA              │");
            System.out.println("└─────────────────────────────────────┘\n");
            masterSword.usar();
            masterSword.usar();
            System.out.println();
            hylianShield.usar();
            System.out.println();
            pocionRoja.usar();
            System.out.println();
            rupiaOro.usar();
            System.out.println();

            // ============================================
            // BUSCAR ITEMS POR RAREZA
            // ============================================
            System.out.println("\n🔍 === BÚSQUEDA DE ITEMS LEGENDARIOS ===\n");
            List<Item> itemsLegendarios = inventarioMario.buscarItemsPorRareza(Rareza.LEGENDARIO);
            itemsLegendarios.addAll(inventarioLink.buscarItemsPorRareza(Rareza.LEGENDARIO));

            if (!itemsLegendarios.isEmpty()) {
                for (Item item : itemsLegendarios) {
                    System.out.println("⭐ " + item);
                }
            } else {
                System.out.println("No se encontraron items legendarios.");
            }

            // ============================================
            // BUSCAR ITEMS POR UNIVERSO
            // ============================================
            System.out.println("\n🔍 === ITEMS DE ZELDA EN INVENTARIO DE LINK ===\n");
            List<Item> itemsZelda = inventarioLink.buscarItemsPorUniverso(UniversoJuego.ZELDA);
            System.out.println("Total de items de Zelda: " + itemsZelda.size());
            for (Item item : itemsZelda) {
                System.out.println("⚔️ " + item.getNombre());
            }

            // ============================================
            // BUSCAR ITEMS POR NOMBRE
            // ============================================
            System.out.println("\n🔍 === BUSCANDO ITEMS CON 'POCIÓN' ===\n");
            List<Item> pociones = inventarioLink.buscarItemsPorNombre("poción");
            for (Item item : pociones) {
                System.out.println("🧪 " + item.getNombre());
            }

            // ============================================
            // PROBAR DURABILIDAD
            // ============================================
            System.out.println("\n⚔️ === PROBANDO SISTEMA DE DURABILIDAD ===\n");
            System.out.println("Usando Kokiri Sword hasta romperla...\n");
            for (int i = 0; i < 12; i++) {
                kokiriSword.usar();
                if (i < 11) System.out.println();
            }
            System.out.println("\n🔧 Reparando Kokiri Sword...\n");
            kokiriSword.reparar();
            System.out.println();
            kokiriSword.usar();

            // ============================================
            // PROBAR LÍMITE DE INVENTARIO
            // ============================================
            System.out.println("\n\n📦 === PROBANDO LÍMITE DE INVENTARIO ===\n");
            Inventario inventarioPequeno = new Inventario(2, "Toad");
            inventarioPequeno.agregarItem(new Moneda(100, 5));
            inventarioPequeno.agregarItem(new Moneda(101, 10));
            System.out.println(inventarioPequeno);

            // Esto lanzará una excepción
            System.out.println("Intentando agregar un ítem más...\n");
            try {
                inventarioPequeno.agregarItem(new Moneda(102, 1));
            } catch (InventarioLlenoException e) {
                System.out.println("⚠️ EXCEPCIÓN CAPTURADA: " + e.getMessage());
            }

            // ============================================
            // ELIMINAR ITEMS
            // ============================================
            System.out.println("\n\n🗑️ === PROBANDO ELIMINACIÓN DE ITEMS ===\n");
            System.out.println("Eliminando Super Mushroom del inventario de Mario...\n");
            inventarioMario.eliminarItem(1);

            System.out.println("\nIntentando eliminar un ítem inexistente (ID: 999)...\n");
            inventarioMario.eliminarItem(999);

            // ============================================
            // ESTADÍSTICAS FINALES
            // ============================================
            System.out.println("\n\n📊 === ESTADÍSTICAS FINALES ===\n");
            System.out.println("Inventario de Mario:");
            System.out.println("  - Capacidad usada: " + inventarioMario.getCapacidadUsada() + "/" + inventarioMario.getCapacidadMaxima());
            System.out.println("  - Items de Mario: " + inventarioMario.buscarItemsPorUniverso(UniversoJuego.MARIO).size());

            System.out.println("\nInventario de Link:");
            System.out.println("  - Capacidad usada: " + inventarioLink.getCapacidadUsada() + "/" + inventarioLink.getCapacidadMaxima());
            System.out.println("  - Items de Zelda: " + inventarioLink.buscarItemsPorUniverso(UniversoJuego.ZELDA).size());
            System.out.println("  - Items legendarios: " + inventarioLink.buscarItemsPorRareza(Rareza.LEGENDARIO).size());
            System.out.println("  - Items épicos: " + inventarioLink.buscarItemsPorRareza(Rareza.EPICO).size());

            System.out.println("\n═══════════════════════════════════════════════════════════");
            System.out.println("✅ SISTEMA EJECUTADO CORRECTAMENTE");
            System.out.println("═══════════════════════════════════════════════════════════\n");

        } catch (InventarioLlenoException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}