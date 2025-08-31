package presentacion.usuario;

import java.awt.BorderLayout;
import presentacion.evento.ConsultaEdicionEvento;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.util.HashMap;


import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


import excepciones.UsuarioNoExisteException;
import logica.clases.EdicionEvento;
import logica.clases.Organizador;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;

import presentacion.registros.ConsultaDeRegistro;
import logica.datatypes.DataRegistro;



public class ConsultaUsuario extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JList<DataUsuario> listaUsuarios;
    private DefaultListModel<DataUsuario> listaModel;
    private DataUsuario[] usuarios;

    private JLabel lblInstitucion, lblRegistros;
    private JTextField txtInstitucion;
    private JList<DataRegistro> listaRegistros;
    private DefaultListModel<DataRegistro> modeloRegistros;
    private JScrollPane scrollRegistros;
    private ConsultaDeRegistro ventanaConsultaRegistro;
    
    private JLabel lblNick, lblNombre, lblApellido, lblCorreo, lblFecha, lblDesc, lblLink, lblTipo;
    private JTextField txtNickname, txtNombre, txtApellido, txtCorreo, txtFechaNac, txtLink;
    private JTextArea txtDescripcion;
    private JScrollPane scrollDesc;
    private JTextField txtTipo;

    private JComboBox<EdicionEvento> comboEdiciones;
    private JLabel lblEdiciones;
    private boolean cargandoEdiciones = false;

    private final IControladorUsuario ICU;
    private final IControladorEvento IEV;

    public ConsultaUsuario(IControladorUsuario ICU, IControladorEvento IEV) {
        super("Consulta de Usuario", false, true, true, true);
        this.ICU = ICU;
        this.IEV = IEV;

        setSize(640, 420);
        setResizable(false);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);

        getContentPane().setLayout(new BorderLayout(10, 10));
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        getContentPane().add(root, BorderLayout.CENTER);

        // Panel izquierdo: lista de usuarios
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(210, 0));
        panelIzq.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                "Usuarios", TitledBorder.LEFT, TitledBorder.TOP));

        listaModel = new DefaultListModel<>();
        listaUsuarios = new JList<>(listaModel);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaUsuarios.setFixedCellHeight(22);
        listaUsuarios.setCellRenderer(new UsuarioRenderer());

        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        panelIzq.add(scrollLista, BorderLayout.CENTER);
        root.add(panelIzq, BorderLayout.WEST);

        // Panel derecho: detalle del usuario
        JPanel panelDer = new JPanel();
        panelDer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                "Detalle", TitledBorder.LEFT, TitledBorder.TOP));
        root.add(panelDer, BorderLayout.CENTER);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths  = new int[]{0, 0, 0};
        gbl.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // +2 filas
        gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.9, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        panelDer.setLayout(gbl);

        Insets IN = new Insets(6, 10, 6, 10);

        lblEdiciones = label("Ediciones organizadas:");
        add(panelDer, lblEdiciones, gbc(0,8,IN, GridBagConstraints.WEST));

        comboEdiciones = new JComboBox<>();
        comboEdiciones.setPreferredSize(new Dimension(200, 24));
        add(panelDer, comboEdiciones, gbcFill(1,8,IN));

        comboEdiciones.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof EdicionEvento ed) {
                    label.setText(ed.getNombre() + " (" + ed.getFechaIni() + ")");
                }
                return label;
            }
        });

        // listener para abrir el otro caso de uso
        comboEdiciones.addItemListener(e -> {
            if (!cargandoEdiciones && e.getStateChange() == ItemEvent.SELECTED) {
                EdicionEvento seleccionada = (EdicionEvento) e.getItem();
                if (seleccionada != null) {
                    ConsultaEdicionEvento ce = new ConsultaEdicionEvento(IEV);
                    getDesktopPane().add(ce);
                    ce.setVisible(true);
                    ce.cargarEdicion(seleccionada.getNombre());
                    comboEdiciones.setSelectedIndex(-1);
                }
            }
        });

        // --- Campos básicos del usuario ---
        lblNick = label("Nickname:");
        add(panelDer, lblNick, gbc(0,0,IN, GridBagConstraints.WEST));
        txtNickname = readonlyField();
        add(panelDer, txtNickname, gbcFill(1,0,IN));

        lblTipo = label("Tipo:");
        add(panelDer, lblTipo, gbc(0,1,IN, GridBagConstraints.WEST));
        txtTipo = readonlyField();
        add(panelDer, txtTipo, gbcFill(1,1,IN));

        lblNombre = label("Nombre:");
        add(panelDer, lblNombre, gbc(0,2,IN, GridBagConstraints.WEST));
        txtNombre = readonlyField();
        add(panelDer, txtNombre, gbcFill(1,2,IN));

        lblApellido = label("Apellido:");
        add(panelDer, lblApellido, gbc(0,3,IN, GridBagConstraints.WEST));
        txtApellido = readonlyField();
        add(panelDer, txtApellido, gbcFill(1,3,IN));

        lblCorreo = label("Correo:");
        add(panelDer, lblCorreo, gbc(0,4,IN, GridBagConstraints.WEST));
        txtCorreo = readonlyField();
        add(panelDer, txtCorreo, gbcFill(1,4,IN));

        lblFecha = label("Fecha nac.:");
        add(panelDer, lblFecha, gbc(0,5,IN, GridBagConstraints.WEST));
        txtFechaNac = readonlyField();
        add(panelDer, txtFechaNac, gbcFill(1,5,IN));

        lblDesc = label("Descripción:");
        add(panelDer, lblDesc, gbc(0,6,IN, GridBagConstraints.NORTHWEST));
        txtDescripcion = new JTextArea(5, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEditable(false);
        txtDescripcion.setFont(new Font(UIManager.getFont("TextField.font").getName(), Font.PLAIN, 12));
        txtDescripcion.setBackground(UIManager.getColor("TextField.inactiveBackground"));
        scrollDesc = new JScrollPane(txtDescripcion);
        add(panelDer, scrollDesc, gbcFillBoth(1,6,IN));

        lblLink = label("Link:");
        add(panelDer, lblLink, gbc(0,7,IN, GridBagConstraints.WEST));
        txtLink = readonlyField();
        add(panelDer, txtLink, gbcFill(1,7,IN));
        
     // --- Institución ---
        lblInstitucion = label("Institución:");
        add(panelDer, lblInstitucion, gbc(0,9,IN, GridBagConstraints.WEST));

        txtInstitucion = readonlyField();          // o new JTextField() si querés editable
        add(panelDer, txtInstitucion, gbcFill(1,9,IN));

        // --- Registros ---
        lblRegistros = label("Registros:");
        add(panelDer, lblRegistros, gbc(0,10,IN, GridBagConstraints.NORTHWEST));

        modeloRegistros = new DefaultListModel<>();
        listaRegistros = new JList<>(modeloRegistros);
        listaRegistros.setVisibleRowCount(6);     

     listaRegistros.setCellRenderer(new DefaultListCellRenderer() {
   
         public Component getListCellRendererComponent(JList<?> list, Object value,
                                                       int index, boolean isSelected, boolean cellHasFocus) {
             JLabel lab = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
             if (value instanceof DataRegistro r) {
                 String ev   = safe(r.getEvento());
                 String ed   = safe(r.getEdicion());
                 String f    = (r.getFecha() != null) ? r.getFecha().toString() : "";
                 lab.setText(ev + " - " + ed + (f.isEmpty() ? "" : " (" + f + ")"));
             }
             return lab;
         }
     });

        scrollRegistros = new JScrollPane(listaRegistros);
        
        add(panelDer, scrollRegistros, gbcFillBoth(1,10,IN));
        
        listaRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
           
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    
                    DataRegistro reg = listaRegistros.getSelectedValue();
                    if (reg == null) return;

                    if (ventanaConsultaRegistro != null && ventanaConsultaRegistro.isVisible()) {
                        ventanaConsultaRegistro.dispose();
                    }

                    ventanaConsultaRegistro = new presentacion.registros.ConsultaDeRegistro(IEV, ICU);
                    ventanaConsultaRegistro.setVisible(true);

                    JDesktopPane desk = ConsultaUsuario.this.getDesktopPane();
                    if (desk != null) {
                        desk.add(ventanaConsultaRegistro);
                        try { ventanaConsultaRegistro.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                    }
                }
            }
        });


        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInfoUsuario(listaUsuarios.getSelectedValue());
            }
            comboEdiciones.setSelectedIndex(-1);
        });
        setVis(lblInstitucion, txtInstitucion, false);
        setVis(lblRegistros,   scrollRegistros, false);
        setVis(lblEdiciones,   comboEdiciones,  false);

    }

    private static JLabel label(String text) {
        JLabel l = new JLabel(text, SwingConstants.LEFT);
        l.setFont(l.getFont().deriveFont(Font.PLAIN));
        return l;
    }

    private static JTextField readonlyField() {
        JTextField f = new JTextField();
        f.setEditable(false);
        f.setBackground(UIManager.getColor("TextField.inactiveBackground"));
        return f;
    }

    private static void add(JPanel p, Component c, GridBagConstraints gbc) {
        p.add(c, gbc);
    }

    private static GridBagConstraints gbc(int x, int y, Insets insets, int anchor) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x; g.gridy = y;
        g.insets = insets;
        g.anchor = anchor;
        return g;
    }
    private static GridBagConstraints gbcFill(int x, int y, Insets insets) {
        GridBagConstraints g = gbc(x,y,insets, GridBagConstraints.CENTER);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;
        return g;
    }
    private static GridBagConstraints gbcFillBoth(int x, int y, Insets insets) {
        GridBagConstraints g = gbc(x,y,insets, GridBagConstraints.CENTER);
        g.fill = GridBagConstraints.BOTH;
        g.weightx = 1.0; g.weighty = 1.0;
        return g;
    }

    private static class UsuarioRenderer extends DefaultListCellRenderer {
        private static final long serialVersionUID = 1L;
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof DataUsuario du) {
                String nick = safe(du.getNickname());
                String nom  = safe(du.getNombre());
                String ape  = safe(du.getApellido());
                c.setText(!nick.isEmpty() ? nick : (nom + (ape.isEmpty() ? "" : " " + ape)));
            }
            c.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
            return c;
        }
    }

    private static String safe(String s) { return s == null ? "" : s; }

    public boolean cargarUsuarios() {
        listaModel.clear();
        try {
            usuarios = ICU.getUsuarios();
            if (usuarios == null || usuarios.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
                return false;
            }
            for (DataUsuario u : usuarios) listaModel.addElement(u);
            listaUsuarios.setSelectedIndex(0);
            return true;
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
            return false;
        }
    }

    private void mostrarInfoUsuario(DataUsuario u) {
        if (u == null) return;

        txtNickname.setText(safe(u.getNickname()));
        txtNombre.setText(safe(u.getNombre()));
        txtApellido.setText(safe(u.getApellido()));
        txtCorreo.setText(safe(u.getCorreo()));

        String fecha = "";
        try {
            if (u.getFechaNacimiento() != null) fecha = u.getFechaNacimiento().toString();
        } catch (Exception ignored) {}
        txtFechaNac.setText(fecha);

        txtDescripcion.setText(safe(u.getDescripcion()));
        txtLink.setText(safe(u.getLink()));
        txtTipo.setText(safe(u.getTipo()));

        String tipo = (u.getTipo() == null) ? "" : u.getTipo().trim().toLowerCase();

        if ("organizador".equals(tipo)) {
            // Carga ediciones del organizador (como ya lo tenías)
            cargandoEdiciones = true;
            comboEdiciones.removeAllItems();
            try {
                Organizador org = ICU.getOrganizador(u.getNickname());
                HashMap<String, EdicionEvento> eds = org.getEdicionesOrganizadas();
                for (EdicionEvento e : eds.values()) comboEdiciones.addItem(e);
                comboEdiciones.setSelectedIndex(-1);
                setVis(lblEdiciones, comboEdiciones, true);
            } catch (Exception ex) {
                setVis(lblEdiciones, comboEdiciones, false);
            } finally {
                cargandoEdiciones = false;
            }

            // Limpio registros por si venías de un asistente
            modeloRegistros.clear();

        } else if ("asistente".equals(tipo)) {
            cargarRegistrosDeAsistente(u.getNickname());

            comboEdiciones.removeAllItems();
            setVis(lblEdiciones, comboEdiciones, false);

        } else {
            // Cualquier otro tipo, limpio todo
            comboEdiciones.removeAllItems();
            setVis(lblEdiciones, comboEdiciones, false);
            modeloRegistros.clear();
        }
        actualizarVisibilidadPorTipo(tipo);
    }

    private void cargarRegistrosDeAsistente(String nickname) {
        // limpiar el modelo
        modeloRegistros.clear();

        if (nickname == null || nickname.isBlank()) {
            // ocultar si no hay nick
            setVis(lblRegistros, scrollRegistros, false);
            return;
        }

        try {
            // pedirle al controlador los registros del asistente
            DataRegistro[] regs = IEV.listarRegistrosDeUsuario(nickname);

            if (regs != null) {
                for (DataRegistro r : regs) {
                    if (r != null) modeloRegistros.addElement(r);
                }
            }

            // mostrar el bloque y refrescar UI
            setVis(lblRegistros, scrollRegistros, true);
            scrollRegistros.revalidate();
            scrollRegistros.repaint();

            // (debug opcional) ver en consola cuántos llegaron
            System.out.println("[ConsultaUsuario] Registros de " + nickname + ": " + (regs == null ? 0 : regs.length));

        } catch (Exception ex) {
            // si hay error, dejamos la lista vacía y la ocultamos
            setVis(lblRegistros, scrollRegistros, false);
            System.out.println("[ConsultaUsuario] Error cargando registros: " + ex.getMessage());
        }
    }

    private void actualizarVisibilidadPorTipo(String tipoRaw) {
        String tipo = (tipoRaw == null) ? "" : tipoRaw.trim().toLowerCase();

        boolean esAsistente   = tipo.equals("asistente");
        boolean esOrganizador = tipo.equals("organizador");

        setVis(lblLink, txtLink, true);
        setVis(lblDesc, scrollDesc, true);
        setVis(lblApellido, txtApellido, true);
        setVis(lblFecha, txtFechaNac, true);
        setVis(lblInstitucion, txtInstitucion, esAsistente);
        setVis(lblRegistros,   scrollRegistros, esAsistente);
        setVis(lblEdiciones, comboEdiciones, esOrganizador);

        if (esAsistente) {
            setVis(lblLink, txtLink, false);
            setVis(lblDesc, scrollDesc, false);
        } else if (esOrganizador) {
            setVis(lblApellido, txtApellido, false);
            setVis(lblFecha, txtFechaNac, false);
        }

        revalidate();
        repaint();
    }


    private static void setVis(Component l, Component c, boolean vis) {
        l.setVisible(vis);
        c.setVisible(vis);
    }

    public DataUsuario getUsuarioSeleccionado() {
        return listaUsuarios.getSelectedValue();
    }
}
