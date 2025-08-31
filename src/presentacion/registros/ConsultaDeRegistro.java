package presentacion.registros;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Fabrica;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ConsultaDeRegistro extends JInternalFrame {

    // Controladores
    private final IControladorEvento ctrlEvento;
    private final IControladorUsuario ctrlUsuario;

    // Combos
    private JComboBox<String> cbListaAsistente;
    private JComboBox<String> cbListaRegistro;

    // Campos info (no editables)
    private JTextField tfFechaReg;
    private JTextField tfEvento;
    private JTextField tfEdicion;
    private JTextField tfCosto;

    // Registros cargados para el asistente seleccionado
    private DataRegistro[] registrosActuales;

    // ==== Constructores ====
    public ConsultaDeRegistro() {
        this(Fabrica.getInstance().getControladorEvento(),
             Fabrica.getInstance().getControladorUsuario());
    }

    public ConsultaDeRegistro(IControladorEvento IVE, IControladorUsuario ICU) {
        this.ctrlEvento = IVE;
        this.ctrlUsuario = ICU;

        setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta De Registro");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        // -------- Panel Izquierdo (combos) ----------
        JPanel panelIzq = new JPanel();
        panelIzq.setBorder(new EmptyBorder(12, 12, 12, 12));
        panelIzq.setPreferredSize(new java.awt.Dimension(240, 0));
        getContentPane().add(panelIzq, BorderLayout.WEST);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] {0, 0};
        gbl.rowHeights  = new int[] {0, 0, 0, 0, 0};
        gbl.columnWeights = new double[] {0.0, 1.0};
        gbl.rowWeights    = new double[] {0.0, 0.0, 0.0, 0.0, 1.0};
        panelIzq.setLayout(gbl);

        // Asistente
        JLabel lblAsistente = new JLabel("Asistente: ");
        lblAsistente.setVerticalAlignment(SwingConstants.TOP);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 12, 6, 6); c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0;
        panelIzq.add(lblAsistente, c);

        cbListaAsistente = new JComboBox<>();
        cbListaAsistente.setModel(new DefaultComboBoxModel<>());
        cbListaAsistente.setSelectedIndex(-1); // campo vacío
        c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 12); c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 1; c.weightx = 1.0;
        panelIzq.add(cbListaAsistente, c);

        // Registro
        JLabel lblRegistro = new JLabel("Registro: ");
        c = new GridBagConstraints();
        c.insets = new Insets(6, 12, 6, 6); c.anchor = GridBagConstraints.SOUTHWEST;
        c.gridx = 0; c.gridy = 2;
        panelIzq.add(lblRegistro, c);

        cbListaRegistro = new JComboBox<>();
        cbListaRegistro.setModel(new DefaultComboBoxModel<>());
        cbListaRegistro.setSelectedIndex(-1); // campo vacío
        c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 12); c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 3; c.weightx = 1.0;
        panelIzq.add(cbListaRegistro, c);

        // -------- Panel Centro: Información del registro ----------
        JPanel panelInfo = new JPanel(new GridBagLayout());
        panelInfo.setBorder(new TitledBorder(
                BorderFactory.createEtchedBorder(),
                "Información del registro:",
                TitledBorder.CENTER, TitledBorder.TOP));
        getContentPane().add(new JScrollPane(panelInfo), BorderLayout.CENTER);

        GridBagConstraints gi = new GridBagConstraints();
        gi.insets = new Insets(6, 18, 6, 6);
        gi.anchor = GridBagConstraints.EAST;

        int row = 0;
        tfFechaReg = addRow(panelInfo, gi, row++, "Fecha de Registro:");
        tfEvento   = addRow(panelInfo, gi, row++, "Evento:");
        tfEdicion  = addRow(panelInfo, gi, row++, "Edición:");
        tfCosto    = addRow(panelInfo, gi, row++, "Costo:");

        // ---- Carga inicial + listeners ----
        cargarAsistentesEnCombo();

        cbListaAsistente.addActionListener(e -> {
            String seleccionado = (String) cbListaAsistente.getSelectedItem();
            String nickname = extractNicknameOrAll(seleccionado);
            cargarRegistrosParaAsistente(nickname);
            limpiarInfo(); // reset info al cambiar asistente
        });

        cbListaRegistro.addActionListener(e -> {
            int idx = cbListaRegistro.getSelectedIndex();
            if (idx >= 0 && registrosActuales != null && idx < registrosActuales.length) {
                DataRegistro r = registrosActuales[idx];
                setFechaRegistro(r.getFecha() != null ? r.getFecha().toString() : "");
                setEvento(r.getEvento());
                setEdicion(r.getEdicion());
                setCosto(r.getCosto() != null ? r.getCosto().toString() : "");
            } else {
                limpiarInfo();
            }
        });
    }

    /** Llamalo al abrir la ventana para refrescar combos y limpiar info. */
    public void recargarDatos() {
        cargarAsistentesEnCombo(); // ya limpia registros adentro
        limpiarInfo();
    }

    /** Crea fila etiqueta + JTextField no editable; retorna el field. */
    private JTextField addRow(JPanel parent, GridBagConstraints base, int y, String label) {
        GridBagConstraints left = (GridBagConstraints) base.clone();
        left.gridx = 0; left.gridy = y;
        parent.add(new JLabel(label), left);

        JTextField tf = new JTextField();
        tf.setEditable(false);

        GridBagConstraints right = (GridBagConstraints) base.clone();
        right.gridx = 1; right.gridy = y;
        right.insets = new Insets(6, 6, 6, 18);
        right.fill = GridBagConstraints.HORIZONTAL;
        right.weightx = 1.0;
        parent.add(tf, right);

        return tf;
    }

    // ================== Setters para completar info ==================
    public void setFechaRegistro(String v) { tfFechaReg.setText(v != null ? v : ""); }
    public void setEvento(String v)        { tfEvento.setText(v != null ? v : ""); }
    public void setEdicion(String v)       { tfEdicion.setText(v != null ? v : ""); }
    public void setCosto(String v)         { tfCosto.setText(v != null ? v : ""); }

    public void limpiarInfo() {
        setFechaRegistro("");
        setEvento("");
        setEdicion("");
        setCosto("");
    }

    // ================== Carga de combos ==================
    // Ahora muestra SOLO el nickname
    private void cargarAsistentesEnCombo() {
        try {
            DataUsuario[] asistentes = ctrlUsuario.getAsistentes();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            if (asistentes != null) {
                for (DataUsuario du : asistentes) {
                    String nick = du.getNickname() != null ? du.getNickname().trim() : "";
                    if (!nick.isBlank()) {
                        model.addElement(nick);
                    } else {
                        model.addElement("<sin_nickname>");
                    }
                }
            }
            cbListaAsistente.setModel(model);
            cbListaAsistente.setSelectedIndex(-1); // vacío inicial
        } catch (Exception ex) {
            cbListaAsistente.setModel(new DefaultComboBoxModel<>());
            cbListaAsistente.setSelectedIndex(-1);
        }
        limpiarRegistros();
    }

    // Ahora muestra SOLO el nombre de la edición
    private void cargarRegistrosParaAsistente(String nickname) {
        try {
            registrosActuales = ctrlEvento.listarRegistrosDeUsuario(nickname);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            if (registrosActuales != null) {
                for (DataRegistro r : registrosActuales) {
                    String edicion = r.getEdicion() != null ? r.getEdicion().trim() : "";
                    model.addElement(!edicion.isBlank() ? edicion : "<sin edición>");
                }
            }
            cbListaRegistro.setModel(model);
            cbListaRegistro.setSelectedIndex(-1);
        } catch (Exception ex) {
            cbListaRegistro.setModel(new DefaultComboBoxModel<>());
            cbListaRegistro.setSelectedIndex(-1);
        }
    }

    private void limpiarRegistros() {
        registrosActuales = null;
        cbListaRegistro.setModel(new DefaultComboBoxModel<>());
        cbListaRegistro.setSelectedIndex(-1);
    }

    // Si el display es "Nombre (nick)", devuelve "nick"; si no, devuelve el string completo
    private String extractNicknameOrAll(String display) {
        if (display == null) return null;
        int open = display.lastIndexOf('(');
        int close = display.lastIndexOf(')');
        if (open != -1 && close != -1 && close > open + 1) {
            return display.substring(open + 1, close).trim(); // "Nombre (nick)" -> nick
        }
        return display.trim(); // si ya es solo "nick", retorna tal cual
    }
}

