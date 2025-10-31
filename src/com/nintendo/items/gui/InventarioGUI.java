package com.nintendo.items.gui;

import com.nintendo.items.base.Item;
import com.nintendo.items.inventario.Inventario;
import com.nintendo.items.inventario.InventarioLlenoException;
import com.nintendo.items.mario.*;
import com.nintendo.items.zelda.*;
import com.nintendo.items.enums.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Interfaz gráfica principal del Sistema de Gestión de Ítems.
 * Utiliza Java Swing para proporcionar una interfaz visual interactiva.
 *
 * @author Mateo Fitipaldi
 * @version 1.0
 */
public class InventarioGUI extends JFrame {

    // Inventarios
    private Inventario inventarioMario;
    private Inventario inventarioLink;

    // Componentes de la interfaz
    private JTabbedPane tabbedPane;
    private DefaultListModel<String> listModelMario;
    private DefaultListModel<String> listModelLink;
    private JList<String> listaMario;
    private JList<String> listaLink;
    private JTextArea areaInfo;
    private JLabel labelCapacidadMario;
    private JLabel labelCapacidadLink;

    // Contadores de IDs
    private int contadorIdMario = 1;
    private int contadorIdZelda = 100;

    /**
     * Constructor de la interfaz gráfica.
     */
    public InventarioGUI() {
        inicializarInventarios();
        inicializarComponentes();
        cargarItemsIniciales();
    }

    /**
     * Inicializa los inventarios de Mario y Link.
     */
    private void inicializarInventarios() {
        inventarioMario = new Inventario(20, "Mario");
        inventarioLink = new Inventario(20, "Link");
    }

    /**
     * Inicializa todos los componentes de la interfaz.
     */
    private void inicializarComponentes() {
        // Configuración de la ventana principal
        setTitle("🎮 Sistema de Gestión de Ítems - Mario & Zelda");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel superior con título
        JPanel panelTitulo = crearPanelTitulo();
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // TabbedPane para Mario y Zelda
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        // Pestaña de Mario
        JPanel panelMario = crearPanelInventario("Mario", true);
        tabbedPane.addTab("🍄 Mario", panelMario);

        // Pestaña de Link/Zelda
        JPanel panelLink = crearPanelInventario("Link", false);
        tabbedPane.addTab("⚔️ Link", panelLink);

        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);

        // Panel inferior con información
        JPanel panelInferior = crearPanelInformacion();
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    /**
     * Crea el panel del título.
     */
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180));

        JLabel titulo = new JLabel("🎮 SISTEMA DE GESTIÓN DE ÍTEMS - MARIO & ZELDA 🎮");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);

        panel.add(titulo);
        return panel;
    }

    /**
     * Crea un panel de inventario para un personaje.
     *
     * @param personaje Nombre del personaje
     * @param esMario   true si es Mario, false si es Link
     */
    private JPanel crearPanelInventario(String personaje, boolean esMario) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Panel izquierdo - Botones de creación
        JPanel panelBotones = crearPanelBotones(esMario);
        panel.add(panelBotones, BorderLayout.WEST);

        // Panel central - Lista de items
        JPanel panelLista = crearPanelLista(esMario);
        panel.add(panelLista, BorderLayout.CENTER);

        // Panel derecho - Acciones
        JPanel panelAcciones = crearPanelAcciones(esMario);
        panel.add(panelAcciones, BorderLayout.EAST);

        return panel;
    }

    /**
     * Crea el panel de botones para crear ítems.
     */
    private JPanel crearPanelBotones(boolean esMario) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                "➕ Crear Ítems",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));

        if (esMario) {
            panel.add(crearBoton("🍄 Power-Up", e -> crearPowerUp()));
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(crearBoton("🪙 Moneda", e -> crearMoneda()));
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(crearBoton("🟢 Tubería", e -> crearTuberia()));
        } else {
            panel.add(crearBoton("⚔️ Arma", e -> crearArma()));
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(crearBoton("🛡️ Escudo", e -> crearEscudo()));
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(crearBoton("🧪 Poción", e -> crearPocion()));
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(crearBoton("💎 Rupia", e -> crearRupia()));
        }

        return panel;
    }

    /**
     * Crea el panel con la lista de ítems.
     */
    private JPanel crearPanelLista(boolean esMario) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        // Label de capacidad
        JLabel labelCapacidad;
        if (esMario) {
            labelCapacidadMario = new JLabel("Capacidad: 0/20");
            labelCapacidadMario.setFont(new Font("Arial", Font.BOLD, 12));
            labelCapacidad = labelCapacidadMario;
        } else {
            labelCapacidadLink = new JLabel("Capacidad: 0/20");
            labelCapacidadLink.setFont(new Font("Arial", Font.BOLD, 12));
            labelCapacidad = labelCapacidadLink;
        }

        panel.add(labelCapacidad, BorderLayout.NORTH);

        // Lista de items
        if (esMario) {
            listModelMario = new DefaultListModel<>();
            listaMario = new JList<>(listModelMario);
            listaMario.setFont(new Font("Monospaced", Font.PLAIN, 11));
            JScrollPane scroll = new JScrollPane(listaMario);
            panel.add(scroll, BorderLayout.CENTER);
        } else {
            listModelLink = new DefaultListModel<>();
            listaLink = new JList<>(listModelLink);
            listaLink.setFont(new Font("Monospaced", Font.PLAIN, 11));
            JScrollPane scroll = new JScrollPane(listaLink);
            panel.add(scroll, BorderLayout.CENTER);
        }

        return panel;
    }

    /**
     * Crea el panel de acciones sobre los ítems.
     */
    private JPanel crearPanelAcciones(boolean esMario) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                "⚡ Acciones",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
        ));

        panel.add(crearBoton("✅ Usar Item", e -> usarItem(esMario)));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(crearBoton("❌ Eliminar", e -> eliminarItem(esMario)));
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(crearBoton("🔍 Info", e -> mostrarInfo(esMario)));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(crearBoton("🗑️ Limpiar Todo", e -> limpiarInventario(esMario)));

        return panel;
    }

    /**
     * Crea el panel de información en la parte inferior.
     */
    private JPanel crearPanelInformacion() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                "📋 Información",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)
        ));

        areaInfo = new JTextArea(6, 40);
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Monospaced", Font.PLAIN, 11));
        areaInfo.setText("Bienvenido al Sistema de Gestión de Ítems\n" +
                "Usa los botones para crear, usar y gestionar ítems.");

        JScrollPane scroll = new JScrollPane(areaInfo);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Crea un botón estilizado.
     */
    private JButton crearBoton(String texto, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(180, 35));
        boton.addActionListener(listener);
        return boton;
    }

    /**
     * Carga algunos ítems iniciales para demostración.
     */
    private void cargarItemsIniciales() {
        try {
            // Items de Mario
            inventarioMario.agregarItem(new PowerUp(contadorIdMario++, "Super Mushroom",
                    "Te hace crecer", Rareza.COMUN, 10, 0, TipoEfecto.ATAQUE, 2.0));
            inventarioMario.agregarItem(new PowerUp(contadorIdMario++, "Fire Flower",
                    "Lanza fuego", Rareza.RARO, 25, 30, TipoEfecto.ATAQUE, 3.0));
            inventarioMario.agregarItem(new Moneda(contadorIdMario++, 10));

            // Items de Link
            inventarioLink.agregarItem(new Arma(contadorIdZelda++, "Master Sword",
                    "Espada legendaria", Rareza.LEGENDARIO, 10000, 10, 50, 100));
            inventarioLink.agregarItem(new Escudo(contadorIdZelda++, "Hylian Shield",
                    "Escudo de Hyrule", Rareza.EPICO, 500, 5, 30, 80));
            inventarioLink.agregarItem(new Pocion(contadorIdZelda++, "Poción Roja",
                    "Restaura vida", Rareza.RARO, 100, 1, TipoEfecto.CURACION, 100));
            inventarioLink.agregarItem(new Rupia(contadorIdZelda++, 100, "Plata"));

            actualizarListas();

        } catch (InventarioLlenoException e) {
            mostrarError(e.getMessage());
        }
    }

    // ============================================
    // MÉTODOS PARA CREAR ÍTEMS
    // ============================================

    private void crearPowerUp() {
        String[] opciones = {"Super Mushroom", "Fire Flower", "Super Star", "Cape Feather"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Selecciona el Power-Up:", "Crear Power-Up",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            try {
                PowerUp powerUp = null;
                switch (seleccion) {
                    case "Super Mushroom":
                        powerUp = new PowerUp(contadorIdMario++, "Super Mushroom",
                                "Te hace crecer", Rareza.COMUN, 10, 0, TipoEfecto.ATAQUE, 2.0);
                        break;
                    case "Fire Flower":
                        powerUp = new PowerUp(contadorIdMario++, "Fire Flower",
                                "Lanza fuego", Rareza.RARO, 25, 30, TipoEfecto.ATAQUE, 3.0);
                        break;
                    case "Super Star":
                        powerUp = new PowerUp(contadorIdMario++, "Super Star",
                                "Invencibilidad", Rareza.LEGENDARIO, 100, 10, TipoEfecto.INVENCIBILIDAD, 5.0);
                        break;
                    case "Cape Feather":
                        powerUp = new PowerUp(contadorIdMario++, "Cape Feather",
                                "Volar", Rareza.EPICO, 50, 20, TipoEfecto.VELOCIDAD, 2.5);
                        break;
                }

                inventarioMario.agregarItem(powerUp);
                actualizarListas();
                areaInfo.setText("✅ " + seleccion + " creado y añadido al inventario de Mario!");

            } catch (InventarioLlenoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private void crearMoneda() {
        String input = JOptionPane.showInputDialog(this, "Cantidad de monedas (1-100):", "10");
        if (input != null) {
            try {
                int cantidad = Integer.parseInt(input);
                if (cantidad < 1 || cantidad > 100) {
                    mostrarError("La cantidad debe estar entre 1 y 100");
                    return;
                }

                Moneda moneda = new Moneda(contadorIdMario++, cantidad);
                inventarioMario.agregarItem(moneda);
                actualizarListas();
                areaInfo.setText("✅ Moneda(s) x" + cantidad + " añadida(s) al inventario!");

            } catch (NumberFormatException e) {
                mostrarError("Debe ingresar un número válido");
            } catch (InventarioLlenoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private void crearTuberia() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la tubería:", "Tubería Verde");
        if (nombre != null && !nombre.trim().isEmpty()) {
            String destino = JOptionPane.showInputDialog(this, "Destino:", "Mundo Subterráneo");
            if (destino != null) {
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Es una tubería secreta?",
                        "Tubería Secreta", JOptionPane.YES_NO_OPTION);

                try {
                    Tuberia tuberia = new Tuberia(contadorIdMario++, nombre, destino, respuesta == JOptionPane.YES_OPTION);
                    inventarioMario.agregarItem(tuberia);
                    actualizarListas();
                    areaInfo.setText("✅ " + nombre + " creada y añadida al inventario!");

                } catch (InventarioLlenoException e) {
                    mostrarError(e.getMessage());
                }
            }
        }
    }

    private void crearArma() {
        String[] opciones = {"Master Sword", "Kokiri Sword", "Biggoron Sword", "Great Fairy Sword"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Selecciona el arma:", "Crear Arma",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            try {
                Arma arma = null;
                switch (seleccion) {
                    case "Master Sword":
                        arma = new Arma(contadorIdZelda++, "Master Sword",
                                "Espada legendaria", Rareza.LEGENDARIO, 10000, 10, 50, 100);
                        break;
                    case "Kokiri Sword":
                        arma = new Arma(contadorIdZelda++, "Kokiri Sword",
                                "Espada básica", Rareza.COMUN, 50, 1, 10, 50);
                        break;
                    case "Biggoron Sword":
                        arma = new Arma(contadorIdZelda++, "Biggoron Sword",
                                "Espada gigante", Rareza.EPICO, 5000, 7, 40, 80);
                        break;
                    case "Great Fairy Sword":
                        arma = new Arma(contadorIdZelda++, "Great Fairy Sword",
                                "Espada mágica", Rareza.RARO, 3000, 5, 30, 70);
                        break;
                }

                inventarioLink.agregarItem(arma);
                actualizarListas();
                areaInfo.setText("✅ " + seleccion + " creada y añadida al inventario de Link!");

            } catch (InventarioLlenoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private void crearEscudo() {
        String[] opciones = {"Hylian Shield", "Deku Shield", "Mirror Shield"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Selecciona el escudo:", "Crear Escudo",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            try {
                Escudo escudo = null;
                switch (seleccion) {
                    case "Hylian Shield":
                        escudo = new Escudo(contadorIdZelda++, "Hylian Shield",
                                "Escudo de Hyrule", Rareza.EPICO, 500, 5, 30, 80);
                        break;
                    case "Deku Shield":
                        escudo = new Escudo(contadorIdZelda++, "Deku Shield",
                                "Escudo de madera", Rareza.COMUN, 40, 1, 10, 40);
                        break;
                    case "Mirror Shield":
                        escudo = new Escudo(contadorIdZelda++, "Mirror Shield",
                                "Refleja luz", Rareza.RARO, 300, 3, 20, 60);
                        break;
                }

                inventarioLink.agregarItem(escudo);
                actualizarListas();
                areaInfo.setText("✅ " + seleccion + " creado y añadido al inventario!");

            } catch (InventarioLlenoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private void crearPocion() {
        String[] opciones = {"Poción Roja", "Poción Verde", "Poción Azul"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Selecciona la poción:", "Crear Poción",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            try {
                Pocion pocion = null;
                switch (seleccion) {
                    case "Poción Roja":
                        pocion = new Pocion(contadorIdZelda++, "Poción Roja",
                                "Restaura toda la vida", Rareza.RARO, 100, 1, TipoEfecto.CURACION, 100);
                        break;
                    case "Poción Verde":
                        pocion = new Pocion(contadorIdZelda++, "Poción Verde",
                                "Restaura vida", Rareza.COMUN, 30, 1, TipoEfecto.CURACION, 50);
                        break;
                    case "Poción Azul":
                        pocion = new Pocion(contadorIdZelda++, "Poción Azul",
                                "Restaura magia", Rareza.RARO, 80, 1, TipoEfecto.VELOCIDAD, 75);
                        break;
                }

                inventarioLink.agregarItem(pocion);
                actualizarListas();
                areaInfo.setText("✅ " + seleccion + " creada y añadida al inventario!");

            } catch (InventarioLlenoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    private void crearRupia() {
        String[] opciones = {"Verde (1)", "Azul (5)", "Roja (20)", "Púrpura (50)", "Plata (100)", "Oro (300)"};
        String seleccion = (String) JOptionPane.showInputDialog(
                this, "Selecciona la rupia:", "Crear Rupia",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion != null) {
            try {
                Rupia rupia = null;
                switch (seleccion) {
                    case "Verde (1)":
                        rupia = new Rupia(contadorIdZelda++, 1, "Verde");
                        break;
                    case "Azul (5)":
                        rupia = new Rupia(contadorIdZelda++, 5, "Azul");
                        break;
                    case "Roja (20)":
                        rupia = new Rupia(contadorIdZelda++, 20, "Roja");
                        break;
                    case "Púrpura (50)":
                        rupia = new Rupia(contadorIdZelda++, 50, "Púrpura");
                        break;
                    case "Plata (100)":
                        rupia = new Rupia(contadorIdZelda++, 100, "Plata");
                        break;
                    case "Oro (300)":
                        rupia = new Rupia(contadorIdZelda++, 300, "Oro");
                        break;
                }

                inventarioLink.agregarItem(rupia);
                actualizarListas();
                areaInfo.setText("✅ Rupia añadida al inventario!");

            } catch (InventarioLlenoException e) {
                mostrarError(e.getMessage());
            }
        }
    }

    // ============================================
    // MÉTODOS DE ACCIONES
    // ============================================

    private void usarItem(boolean esMario) {
        JList<String> lista = esMario ? listaMario : listaLink;
        int indice = lista.getSelectedIndex();

        if (indice == -1) {
            mostrarError("Debes seleccionar un ítem primero");
            return;
        }

        Inventario inventario = esMario ? inventarioMario : inventarioLink;
        List<Item> items = inventario.obtenerTodosLosItems();

        if (indice < items.size()) {
            Item item = items.get(indice);

            // Capturar la salida del método usar()
            areaInfo.setText("⚡ USANDO: " + item.getNombre() + "\n");
            areaInfo.append("─────────────────────────────\n");

            // Simulamos usar el item y mostramos info
            if (item instanceof Arma) {
                Arma arma = (Arma) item;
                areaInfo.append("⚔️ Atacando con " + arma.getNombre() + "\n");
                areaInfo.append("Daño: " + arma.getDanio() + "\n");
                areaInfo.append("Durabilidad: " + arma.getDurabilidad() + "/" + arma.getDurabilidadMaxima());
            } else if (item instanceof Escudo) {
                Escudo escudo = (Escudo) item;
                areaInfo.append("🛡️ Bloqueando con " + escudo.getNombre() + "\n");
                areaInfo.append("Defensa: " + escudo.getDefensa() + "\n");
                areaInfo.append("Durabilidad: " + escudo.getDurabilidad() + "/" + escudo.getDurabilidadMaxima());
            } else {
                areaInfo.append(item.toString());
            }

            item.usar();
            actualizarListas();
        }
    }

    private void eliminarItem(boolean esMario) {
        JList<String> lista = esMario ? listaMario : listaLink;
        int indice = lista.getSelectedIndex();

        if (indice == -1) {
            mostrarError("Debes seleccionar un ítem primero");
            return;
        }

        Inventario inventario = esMario ? inventarioMario : inventarioLink;
        List<Item> items = inventario.obtenerTodosLosItems();

        if (indice < items.size()) {
            Item item = items.get(indice);
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar " + item.getNombre() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                inventario.eliminarItem(item.getId());
                actualizarListas();
                areaInfo.setText("❌ " + item.getNombre() + " eliminado del inventario");
            }
        }
    }

    private void mostrarInfo(boolean esMario) {
        JList<String> lista = esMario ? listaMario : listaLink;
        int indice = lista.getSelectedIndex();

        if (indice == -1) {
            mostrarError("Debes seleccionar un ítem primero");
            return;
        }

        Inventario inventario = esMario ? inventarioMario : inventarioLink;
        List<Item> items = inventario.obtenerTodosLosItems();

        if (indice < items.size()) {
            Item item = items.get(indice);
            areaInfo.setText("📋 INFORMACIÓN DEL ÍTEM\n");
            areaInfo.append("═══════════════════════════\n");
            areaInfo.append(item.toString() + "\n");
        }
    }

    private void limpiarInventario(boolean esMario) {
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar TODOS los ítems del inventario?",
                "Confirmar limpieza",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            Inventario inventario = esMario ? inventarioMario : inventarioLink;
            inventario.limpiarInventario();
            actualizarListas();
            areaInfo.setText("🗑️ Inventario limpiado completamente");
        }
    }

    // ============================================
    // MÉTODOS AUXILIARES
    // ============================================

    private void actualizarListas() {
        // Actualizar lista de Mario
        listModelMario.clear();
        List<Item> itemsMario = inventarioMario.obtenerTodosLosItems();
        for (Item item : itemsMario) {
            listModelMario.addElement(item.getNombre() + " - " + item.getRareza().getNombre());
        }
        labelCapacidadMario.setText("Capacidad: " + inventarioMario.getCapacidadUsada() +
                "/" + inventarioMario.getCapacidadMaxima());

        // Actualizar lista de Link
        listModelLink.clear();
        List<Item> itemsLink = inventarioLink.obtenerTodosLosItems();
        for (Item item : itemsLink) {
            listModelLink.addElement(item.getNombre() + " - " + item.getRareza().getNombre());
        }
        labelCapacidadLink.setText("Capacidad: " + inventarioLink.getCapacidadUsada() +
                "/" + inventarioLink.getCapacidadMaxima());
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // ============================================
    // MÉTODO MAIN
    // ============================================

    public static void main(String[] args) {
        // Usar el Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear y mostrar la GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            InventarioGUI gui = new InventarioGUI();
            gui.setVisible(true);
        });
    }
}