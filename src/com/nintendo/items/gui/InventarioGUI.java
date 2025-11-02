package com.nintendo.items.gui;

import com.nintendo.items.base.Item;
import com.nintendo.items.inventario.Inventario;
import com.nintendo.items.inventario.InventarioLlenoException;
import com.nintendo.items.mario.*;
import com.nintendo.items.zelda.*;
import com.nintendo.items.enums.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.List;

/**
 * Interfaz gráfica moderna del Sistema de Gestión de Ítems con diseño Nintendo.
 *
 * @author Tu nombre
 * @version 2.0
 */
public class InventarioGUI extends JFrame {

    // Colores temáticos de Nintendo
    private static final Color NINTENDO_RED = new Color(230, 0, 18);
    private static final Color MARIO_RED = new Color(228, 0, 43);
    private static final Color MARIO_BLUE = new Color(0, 147, 221);
    private static final Color ZELDA_GREEN = new Color(106, 190, 48);
    private static final Color ZELDA_GOLD = new Color(255, 197, 32);
    private static final Color BACKGROUND_DARK = new Color(40, 44, 52);
    private static final Color PANEL_BG = new Color(50, 54, 62);
    private static final Color CARD_BG = new Color(60, 64, 72);
    private static final Color TEXT_PRIMARY = new Color(240, 240, 245);
    private static final Color TEXT_SECONDARY = new Color(180, 180, 190);

    // Inventarios
    private Inventario inventarioMario;
    private Inventario inventarioLink;

    // Componentes
    private JTabbedPane tabbedPane;
    private DefaultListModel<String> listModelMario;
    private DefaultListModel<String> listModelLink;
    private JList<String> listaMario;
    private JList<String> listaLink;
    private JTextArea areaInfo;
    private JLabel labelCapacidadMario;
    private JLabel labelCapacidadLink;

    // Contadores
    private int contadorIdMario = 1;
    private int contadorIdZelda = 100;

    public InventarioGUI() {
        inicializarInventarios();
        configurarVentana();
        inicializarComponentes();
        cargarItemsIniciales();
    }

    private void inicializarInventarios() {
        inventarioMario = new Inventario(20, "Mario");
        inventarioLink = new Inventario(20, "Link");
    }

    private void configurarVentana() {
        setTitle("Nintendo Items Manager");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar Look and Feel
        try {
            UIManager.put("Button.background", CARD_BG);
            UIManager.put("Button.foreground", TEXT_PRIMARY);
            UIManager.put("Panel.background", BACKGROUND_DARK);
            UIManager.put("TabbedPane.background", PANEL_BG);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(BACKGROUND_DARK);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(BACKGROUND_DARK);
        panelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header
        JPanel header = crearHeader();
        panelPrincipal.add(header, BorderLayout.NORTH);

        // Tabs
        tabbedPane = crearTabbedPane();
        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);

        // Info panel
        JPanel infoPanel = crearPanelInfo();
        panelPrincipal.add(infoPanel, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradiente
                GradientPaint gradient = new GradientPaint(
                        0, 0, NINTENDO_RED,
                        getWidth(), 0, new Color(180, 0, 14)
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };

        header.setPreferredSize(new Dimension(0, 80));
        header.setLayout(new BorderLayout());
        header.setOpaque(false);

        JLabel titulo = new JLabel("NINTENDO ITEMS MANAGER");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitulo = new JLabel("Mario Bros & The Legend of Zelda");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(255, 255, 255, 200));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 5));
        textos.setOpaque(false);
        textos.add(titulo);
        textos.add(subtitulo);

        header.add(textos, BorderLayout.CENTER);

        return header;
    }

    private JTabbedPane crearTabbedPane() {
        JTabbedPane tabs = new JTabbedPane() {
            @Override
            public void setSelectedIndex(int index) {
                super.setSelectedIndex(index);
                repaint();
            }
        };

        tabs.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tabs.setBackground(PANEL_BG);
        tabs.setForeground(Color.BLACK); // Cambiado a negro para mejor contraste

        // Panel Mario
        JPanel panelMario = crearPanelPersonaje("Mario", MARIO_RED, true);
        tabs.addTab("  MARIO  ", panelMario);

        // Panel Link
        JPanel panelLink = crearPanelPersonaje("Link", ZELDA_GREEN, false);
        tabs.addTab("  LINK  ", panelLink);

        // Personalizar tabs
        tabs.setTabPlacement(JTabbedPane.TOP);

        return tabs;
    }

    private JPanel crearPanelPersonaje(String personaje, Color colorTema, boolean esMario) {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(BACKGROUND_DARK);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Panel izquierdo - Creación de items
        JPanel panelCreacion = crearPanelCreacion(colorTema, esMario);
        panel.add(panelCreacion, BorderLayout.WEST);

        // Panel central - Lista
        JPanel panelLista = crearPanelLista(colorTema, esMario);
        panel.add(panelLista, BorderLayout.CENTER);

        // Panel derecho - Acciones
        JPanel panelAcciones = crearPanelAcciones(colorTema, esMario);
        panel.add(panelAcciones, BorderLayout.EAST);

        return panel;
    }

    private JPanel crearPanelCreacion(Color colorTema, boolean esMario) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(colorTema, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(220, 0));

        JLabel titulo = new JLabel("[+] Crear Items");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(colorTema);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        if (esMario) {
            panel.add(crearBotonModerno("Power-Up", colorTema, e -> crearPowerUp()));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(crearBotonModerno("Moneda", ZELDA_GOLD, e -> crearMoneda()));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(crearBotonModerno("Tuberia", MARIO_BLUE, e -> crearTuberia()));
        } else {
            panel.add(crearBotonModerno("Arma", colorTema, e -> crearArma()));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(crearBotonModerno("Escudo", new Color(70, 130, 180), e -> crearEscudo()));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(crearBotonModerno("Pocion", new Color(220, 20, 60), e -> crearPocion()));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(crearBotonModerno("Rupia", ZELDA_GOLD, e -> crearRupia()));
        }

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel crearPanelLista(Color colorTema, boolean esMario) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(colorTema, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Header con capacidad
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(CARD_BG);

        JLabel tituloLista = new JLabel("[Inventario]");
        tituloLista.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tituloLista.setForeground(colorTema);

        if (esMario) {
            labelCapacidadMario = new JLabel("0/20");
            labelCapacidadMario.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelCapacidadMario.setForeground(TEXT_SECONDARY);
            headerPanel.add(tituloLista, BorderLayout.WEST);
            headerPanel.add(labelCapacidadMario, BorderLayout.EAST);

            // Inicializar modelo y lista para Mario
            listModelMario = new DefaultListModel<>();
            listaMario = crearListaEstilizada(listModelMario);
        } else {
            labelCapacidadLink = new JLabel("0/20");
            labelCapacidadLink.setFont(new Font("Segoe UI", Font.BOLD, 14));
            labelCapacidadLink.setForeground(TEXT_SECONDARY);
            headerPanel.add(tituloLista, BorderLayout.WEST);
            headerPanel.add(labelCapacidadLink, BorderLayout.EAST);

            // Inicializar modelo y lista para Link
            listModelLink = new DefaultListModel<>();
            listaLink = crearListaEstilizada(listModelLink);
        }

        panel.add(headerPanel, BorderLayout.NORTH);

        // Lista
        JList<String> lista = esMario ? listaMario : listaLink;
        JScrollPane scroll = new JScrollPane(lista);
        estilizarScrollPane(scroll, colorTema);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JList<String> crearListaEstilizada(DefaultListModel<String> modelo) {
        JList<String> lista = new JList<>(modelo);
        lista.setFont(new Font("Consolas", Font.PLAIN, 13));
        lista.setBackground(new Color(45, 49, 57));
        lista.setForeground(TEXT_PRIMARY);
        lista.setSelectionBackground(NINTENDO_RED);
        lista.setSelectionForeground(Color.WHITE);
        lista.setBorder(new EmptyBorder(5, 10, 5, 10));
        lista.setFixedCellHeight(30);
        return lista;
    }

    private void estilizarScrollPane(JScrollPane scroll, Color colorTema) {
        scroll.setBorder(new LineBorder(colorTema.darker(), 1, true));
        scroll.getVerticalScrollBar().setBackground(PANEL_BG);
        scroll.getHorizontalScrollBar().setBackground(PANEL_BG);
    }

    private JPanel crearPanelAcciones(Color colorTema, boolean esMario) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(colorTema, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(200, 0));

        JLabel titulo = new JLabel("[ Acciones ]");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(colorTema);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(crearBotonModerno("Usar", new Color(46, 204, 113), e -> usarItem(esMario)));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(crearBotonModerno("Info", new Color(52, 152, 219), e -> mostrarInfo(esMario)));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(crearBotonModerno("Eliminar", new Color(231, 76, 60), e -> eliminarItem(esMario)));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(crearBotonModerno("Limpiar Todo", new Color(149, 165, 166), e -> limpiarInventario(esMario)));

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JButton crearBotonModerno(String texto, Color color, ActionListener listener) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2d.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2d.setColor(color.brighter());
                } else {
                    g2d.setColor(color);
                }

                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

                g2d.setColor(getForeground());
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(getText(), x, y);
            }
        };

        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(180, 40));
        boton.setPreferredSize(new Dimension(180, 40));
        boton.addActionListener(listener);

        return boton;
    }

    private JPanel crearPanelInfo() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(NINTENDO_RED, 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(0, 150));

        JLabel titulo = new JLabel("[ Panel de Informacion ]");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titulo.setForeground(NINTENDO_RED);
        panel.add(titulo, BorderLayout.NORTH);

        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Consolas", Font.PLAIN, 12));
        areaInfo.setBackground(new Color(45, 49, 57));
        areaInfo.setForeground(TEXT_PRIMARY);
        areaInfo.setLineWrap(true);
        areaInfo.setWrapStyleWord(true);
        areaInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        areaInfo.setText(">> Bienvenido al Sistema de Gestion de Items de Nintendo\n\n" +
                ">> Selecciona una pestana (Mario o Link)\n" +
                ">> Usa los botones para crear items\n" +
                ">> Haz clic en un item para ver sus acciones");

        JScrollPane scroll = new JScrollPane(areaInfo);
        scroll.setBorder(null);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private boolean esMarioActual() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    // ============================================
    // MÉTODOS PARA CREAR ÍTEMS (sin cambios lógicos, solo los llamo)
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
                areaInfo.setText("[OK] " + seleccion + " creado!\n\n" + powerUp.toString());

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
                areaInfo.setText("[OK] Moneda(s) x" + cantidad + " anadida(s)!\n\n" + moneda.toString());

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
                    areaInfo.setText("[OK] " + nombre + " creada!\n\n" + tuberia.toString());

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
                areaInfo.setText("✅ " + seleccion + " creada!\n\n" + arma.toString());

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
                areaInfo.setText("✅ " + seleccion + " creado!\n\n" + escudo.toString());

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
                areaInfo.setText("✅ " + seleccion + " creada!\n\n" + pocion.toString());

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
                areaInfo.setText("[OK] Rupia anadida!\n\n" + rupia.toString());

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

            areaInfo.setText(">> USANDO: " + item.getNombre() + "\n");
            areaInfo.append("================================\n\n");

            if (item instanceof Arma) {
                Arma arma = (Arma) item;
                arma.usar();
                areaInfo.append("[Arma] Tipo: Arma\n");
                areaInfo.append("Dano: " + arma.getDanio() + "\n");
                areaInfo.append("Durabilidad: " + arma.getDurabilidad() + "/" + arma.getDurabilidadMaxima() + "\n");
            } else if (item instanceof Escudo) {
                Escudo escudo = (Escudo) item;
                escudo.usar();
                areaInfo.append("[Escudo] Tipo: Escudo\n");
                areaInfo.append("Defensa: " + escudo.getDefensa() + "\n");
                areaInfo.append("Durabilidad: " + escudo.getDurabilidad() + "/" + escudo.getDurabilidadMaxima() + "\n");
            } else if (item instanceof PowerUp) {
                PowerUp powerUp = (PowerUp) item;
                powerUp.usar();
                areaInfo.append("[PowerUp] Tipo: Power-Up\n");
                areaInfo.append("Efecto: " + powerUp.getTipoEfecto().getDescripcion() + "\n");
                areaInfo.append("Multiplicador: x" + powerUp.getMultiplicador() + "\n");
            } else if (item instanceof Pocion) {
                Pocion pocion = (Pocion) item;
                pocion.usar();
                areaInfo.append("[Pocion] Tipo: Pocion\n");
                areaInfo.append("Efecto: " + pocion.getTipoEfecto().getDescripcion() + "\n");
                areaInfo.append("Potencia: +" + pocion.getPotencia() + "\n");
            } else {
                item.usar();
                areaInfo.append("\n" + item.toString());
            }

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

            // Diálogo personalizado
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "Estas seguro de eliminar este item?\n\n" + item.getNombre(),
                    "[!] Confirmar Eliminacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                inventario.eliminarItem(item.getId());
                actualizarListas();
                areaInfo.setText("[X] ITEM ELIMINADO\n");
                areaInfo.append("================================\n\n");
                areaInfo.append(item.getNombre() + " ha sido eliminado del inventario.");
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

            areaInfo.setText("[i] INFORMACION DETALLADA\n");
            areaInfo.append("================================\n\n");
            areaInfo.append("Nombre: " + item.getNombre() + "\n");
            areaInfo.append("ID: " + item.getId() + "\n");
            areaInfo.append("Rareza: " + item.getRareza().getNombre() + "\n");
            areaInfo.append("Valor: " + item.getValor() + "\n");
            areaInfo.append("Universo: " + item.getUniverso().getNombreCompleto() + "\n");
            areaInfo.append("Descripcion: " + item.getDescripcion() + "\n\n");

            // Info específica según tipo
            if (item instanceof Arma) {
                Arma arma = (Arma) item;
                areaInfo.append("[ESTADISTICAS DE ARMA]\n");
                areaInfo.append("  - Dano: " + arma.getDanio() + "\n");
                areaInfo.append("  - Durabilidad: " + arma.getDurabilidad() + "/" + arma.getDurabilidadMaxima() + "\n");
                areaInfo.append("  - Nivel requerido: " + arma.getNivelRequerido() + "\n");
            } else if (item instanceof Escudo) {
                Escudo escudo = (Escudo) item;
                areaInfo.append("[ESTADISTICAS DE ESCUDO]\n");
                areaInfo.append("  - Defensa: " + escudo.getDefensa() + "\n");
                areaInfo.append("  - Durabilidad: " + escudo.getDurabilidad() + "/" + escudo.getDurabilidadMaxima() + "\n");
                areaInfo.append("  - Nivel requerido: " + escudo.getNivelRequerido() + "\n");
            } else if (item instanceof PowerUp) {
                PowerUp powerUp = (PowerUp) item;
                areaInfo.append("[ESTADISTICAS DE POWER-UP]\n");
                areaInfo.append("  - Efecto: " + powerUp.getTipoEfecto().getDescripcion() + "\n");
                areaInfo.append("  - Multiplicador: x" + powerUp.getMultiplicador() + "\n");
                areaInfo.append("  - Duracion: " + powerUp.getDuracion() + "s\n");
            } else if (item instanceof Pocion) {
                Pocion pocion = (Pocion) item;
                areaInfo.append("[ESTADISTICAS DE POCION]\n");
                areaInfo.append("  - Efecto: " + pocion.getTipoEfecto().getDescripcion() + "\n");
                areaInfo.append("  - Potencia: +" + pocion.getPotencia() + "\n");
                areaInfo.append("  - Nivel requerido: " + pocion.getNivelRequerido() + "\n");
            }
        }
    }

    private void limpiarInventario(boolean esMario) {
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "[!] Estas seguro de eliminar TODOS los items?\n\nEsta accion no se puede deshacer.",
                "Confirmar Limpieza Total",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            Inventario inventario = esMario ? inventarioMario : inventarioLink;
            String personaje = esMario ? "Mario" : "Link";
            inventario.limpiarInventario();
            actualizarListas();
            areaInfo.setText("[!] INVENTARIO LIMPIADO\n");
            areaInfo.append("================================\n\n");
            areaInfo.append("El inventario de " + personaje + " ha sido vaciado completamente.");
        }
    }

    // ============================================
    // MÉTODOS AUXILIARES
    // ============================================

    private void actualizarListas() {
        // Actualizar Mario
        listModelMario.clear();
        List<Item> itemsMario = inventarioMario.obtenerTodosLosItems();
        for (Item item : itemsMario) {
            String icono = obtenerIcono(item);
            listModelMario.addElement(icono + " " + item.getNombre() + " (" + item.getRareza().getNombre() + ")");
        }
        labelCapacidadMario.setText(inventarioMario.getCapacidadUsada() + "/" + inventarioMario.getCapacidadMaxima());

        // Actualizar Link
        listModelLink.clear();
        List<Item> itemsLink = inventarioLink.obtenerTodosLosItems();
        for (Item item : itemsLink) {
            String icono = obtenerIcono(item);
            listModelLink.addElement(icono + " " + item.getNombre() + " (" + item.getRareza().getNombre() + ")");
        }
        labelCapacidadLink.setText(inventarioLink.getCapacidadUsada() + "/" + inventarioLink.getCapacidadMaxima());
    }

    private String obtenerIcono(Item item) {
        if (item instanceof PowerUp) return "[P]";
        if (item instanceof Moneda) return "[C]";
        if (item instanceof Tuberia) return "[T]";
        if (item instanceof Arma) return "[W]";
        if (item instanceof Escudo) return "[S]";
        if (item instanceof Pocion) return "[H]";
        if (item instanceof Rupia) return "[R]";
        return "[I]";
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "[!] Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

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

            actualizarListas();

        } catch (InventarioLlenoException e) {
            mostrarError(e.getMessage());
        }
    }

    // ============================================
    // MÉTODO MAIN
    // ============================================

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear y mostrar GUI
        SwingUtilities.invokeLater(() -> {
            InventarioGUI gui = new InventarioGUI();
            gui.setVisible(true);
        });
    }
}