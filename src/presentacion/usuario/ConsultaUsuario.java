package presentacion.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
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
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorUsuario;


public class ConsultaUsuario extends JInternalFrame {


    private static final long serialVersionUID = 1L;
	private JList<DataUsuario> listaUsuarios;
    private DefaultListModel<DataUsuario> listaModel;
    private DataUsuario[] usuarios;

    private JLabel lblNick, lblNombre, lblApellido, lblCorreo, lblFecha, lblDesc, lblLink, lblTipo;


    private JTextField txtNickname, txtNombre, txtApellido, txtCorreo, txtFechaNac, txtLink;
    private JTextArea txtDescripcion;
    private JScrollPane scrollDesc;
    private JTextField txtTipo;

    private final IControladorUsuario ICU;

    public ConsultaUsuario(IControladorUsuario ICU) {
        super("Consulta de Usuario", false, true, true, true);
        this.ICU = ICU;

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

        JPanel panelDer = new JPanel();
        panelDer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                "Detalle", TitledBorder.LEFT, TitledBorder.TOP));
        root.add(panelDer, BorderLayout.CENTER);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths  = new int[]{0, 0, 0};
        gbl.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.9, 0.0, 0.0, Double.MIN_VALUE};
        panelDer.setLayout(gbl);

        Insets IN = new Insets(6, 10, 6, 10);

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


        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInfoUsuario(listaUsuarios.getSelectedValue());
            }
        });
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
        try { if (u.getFechaNacimiento() != null) fecha = u.getFechaNacimiento().toString(); } catch (Exception ignored) {}
        txtFechaNac.setText(fecha);

        String desc = "";
        try { desc = safe(u.getDescripcion()); } catch (Exception ignored) {}
        txtDescripcion.setText(desc);

        String link = "";
        try { link = safe(u.getLink()); } catch (Exception ignored) {}
        txtLink.setText(link);

        String tipo = "";
        try { tipo = safe(u.getTipo()); } catch (Exception ignored) {}
        txtTipo.setText(tipo);

        actualizarVisibilidadPorTipo(tipo);

        actualizarVisibilidadPorTipo(tipo);
    }


    private void actualizarVisibilidadPorTipo(String tipoRaw) {
        String tipo = (tipoRaw == null) ? "" : tipoRaw.trim().toLowerCase();

        boolean esAsistente   = tipo.equals("asistente");
        boolean esOrganizador = tipo.equals("organizador");

        setVis(lblLink, txtLink, true);
        setVis(lblDesc, scrollDesc, true);
        setVis(lblApellido, txtApellido, true);
        setVis(lblFecha, txtFechaNac, true);

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
