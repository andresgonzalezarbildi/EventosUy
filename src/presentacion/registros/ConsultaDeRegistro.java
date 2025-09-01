package presentacion.registros;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import logica.Fabrica;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class ConsultaDeRegistro extends JInternalFrame {

    private final IControladorEvento ctrlEvento;
    private final IControladorUsuario ctrlUsuario;

    private JComboBox<DataUsuario> cbListaAsistente;
    private JComboBox<DataRegistro> cbListaRegistro;

    private JTextField tfFechaReg;
    private JTextField tfEvento;
    private JTextField tfEdicion;
    private JTextField tfCosto;

    private DataRegistro[] registrosActuales;

    public ConsultaDeRegistro() {
        this(Fabrica.getInstance().getControladorEvento(),
             Fabrica.getInstance().getControladorUsuario());
    }

    public ConsultaDeRegistro(IControladorEvento IVE, IControladorUsuario ICU) {
        this.ctrlEvento = IVE;
        this.ctrlUsuario = ICU;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consulta De Registro");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panelIzq = new JPanel();
        panelIzq.setBorder(new EmptyBorder(12, 12, 12, 12));
        panelIzq.setPreferredSize(new Dimension(240, 0));
        getContentPane().add(panelIzq, BorderLayout.WEST);

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] {0, 0};
        gbl.rowHeights  = new int[] {0, 0, 0, 0, 0};
        gbl.columnWeights = new double[] {0.0, 1.0};
        gbl.rowWeights    = new double[] {0.0, 0.0, 0.0, 0.0, 1.0};
        panelIzq.setLayout(gbl);

        JLabel lblAsistente = new JLabel("Asistente: ");
        lblAsistente.setVerticalAlignment(SwingConstants.TOP);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 12, 6, 6); c.anchor = GridBagConstraints.WEST;
        c.gridx = 0; c.gridy = 0;
        panelIzq.add(lblAsistente, c);

        cbListaAsistente = new JComboBox<>();
        cbListaAsistente.setModel(new DefaultComboBoxModel<>());
        
        cbListaAsistente.setSelectedIndex(-1);
        c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 12); c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 1; c.weightx = 1.0;
        panelIzq.add(cbListaAsistente, c);

        JLabel lblRegistro = new JLabel("Registro: ");
        c = new GridBagConstraints();
        c.insets = new Insets(6, 12, 6, 6); c.anchor = GridBagConstraints.SOUTHWEST;
        c.gridx = 0; c.gridy = 2;
        panelIzq.add(lblRegistro, c);

        cbListaRegistro = new JComboBox<>();
        cbListaRegistro.setModel(new DefaultComboBoxModel<>());
        cbListaRegistro.setSelectedIndex(-1);
        c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 12); c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0; c.gridy = 3; c.weightx = 1.0;
        panelIzq.add(cbListaRegistro, c);

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

        // Listeners
        cbListaAsistente.addActionListener(e -> {
            DataUsuario seleccionado = (DataUsuario) cbListaAsistente.getSelectedItem();
            if (seleccionado != null) {
                cargarRegistrosParaAsistente(seleccionado.getNickname());
                limpiarInfo();
            }
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

        cargarAsistentesEnCombo();
    }

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

    private void cargarAsistentesEnCombo() {
        try {
            DataUsuario[] asistentes = ctrlUsuario.getAsistentes();
            DefaultComboBoxModel<DataUsuario> model = new DefaultComboBoxModel<>();
            if (asistentes != null) {
                for (DataUsuario du : asistentes) {
                    if (du.getNickname() != null && !du.getNickname().isBlank()) {
                        model.addElement(du);
                    }
                }
            }
            cbListaAsistente.setModel(model);
            cbListaAsistente.setSelectedIndex(-1); 
        } catch (Exception ex) {
            cbListaAsistente.setModel(new DefaultComboBoxModel<>());
            cbListaAsistente.setSelectedIndex(-1);
        }
        limpiarRegistros();
    }


    private void cargarRegistrosParaAsistente(String nickname) {
        try {
            registrosActuales = ctrlEvento.listarRegistrosDeUsuario(nickname);
            DefaultComboBoxModel<DataRegistro> model = new DefaultComboBoxModel<>();
            if (registrosActuales != null) {
                for (DataRegistro r : registrosActuales) model.addElement(r);
            }
            cbListaRegistro.setModel(model);
            cbListaRegistro.setSelectedIndex(-1);
        } catch (Exception ex) {
            cbListaRegistro.setModel(new DefaultComboBoxModel<>());
            cbListaRegistro.setSelectedIndex(-1);
        }
    }

    public void setContext(DataUsuario usuario, DataRegistro registro) {
        if (usuario == null) return;

        cargarAsistentesEnCombo();

        for (int i = 0; i < cbListaAsistente.getItemCount(); i++) {
            DataUsuario u = cbListaAsistente.getItemAt(i);
            if (u != null && u.getNickname().equals(usuario.getNickname())) {
                cbListaAsistente.setSelectedIndex(i);

                cargarRegistrosParaAsistente(u.getNickname());
                break;
            }
        }

        if (registro != null && registrosActuales != null) {
            for (int i = 0; i < cbListaRegistro.getItemCount(); i++) {
                DataRegistro r = cbListaRegistro.getItemAt(i);
                if (r != null && r.getEdicion().equals(registro.getEdicion())) {
                    cbListaRegistro.setSelectedIndex(i);
                    setFechaRegistro(r.getFecha() != null ? r.getFecha().toString() : "");
                    setEvento(r.getEvento());
                    setEdicion(r.getEdicion());
                    setCosto(r.getCosto() != null ? r.getCosto().toString() : "");
                    break;
                }
            }
        }
    }
    public void recargarDatos() {
        cargarAsistentesEnCombo(); 
        limpiarInfo();
    }
    private void limpiarRegistros() {
        registrosActuales = null;
        cbListaRegistro.setModel(new DefaultComboBoxModel<>());
        cbListaRegistro.setSelectedIndex(-1);
    }



}
