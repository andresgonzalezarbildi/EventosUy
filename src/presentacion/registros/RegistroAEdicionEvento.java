package presentacion.registros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import logica.Fabrica;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;
import excepciones.EventoNoExisteException;

public class RegistroAEdicionEvento extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private final IControladorEvento ctrlEvento;
    private final IControladorUsuario ctrlUsuario;

    private JComboBox<String> cbListaEvento;
    private JComboBox<String> cbListaEdiciones;
    private JComboBox<String> cbTipoRegistro;
    private JComboBox<String> cbAsistentes;
    private JDateChooser dcFecha; 

    public RegistroAEdicionEvento() {
        this(Fabrica.getInstance().getControladorEvento(),
             Fabrica.getInstance().getControladorUsuario());
    }

    public RegistroAEdicionEvento(IControladorEvento ctrlEvento,
                                  IControladorUsuario ctrlUsuario) {
        super("Registro a Edición de Evento", false, true, true, true); // título, resizable, closable, maximizable, iconifiable
        this.ctrlEvento = ctrlEvento;
        this.ctrlUsuario = ctrlUsuario;

        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 500, 350);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        // Evento
        JLabel lblEventos = new JLabel("Evento: ");
        GridBagConstraints gbc_lblEventos = new GridBagConstraints();
        gbc_lblEventos.insets = new Insets(6, 12, 6, 6);
        gbc_lblEventos.anchor = GridBagConstraints.EAST;
        gbc_lblEventos.gridx = 0; gbc_lblEventos.gridy = 0;
        panel.add(lblEventos, gbc_lblEventos);

        cbListaEvento = new JComboBox<>();
        GridBagConstraints gbc_cbEvento = new GridBagConstraints();
        gbc_cbEvento.weightx = 1.0;
        gbc_cbEvento.insets = new Insets(6, 6, 6, 12);
        gbc_cbEvento.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbEvento.gridx = 1; gbc_cbEvento.gridy = 0;
        panel.add(cbListaEvento, gbc_cbEvento);

        // Edicion
        JLabel lblEdiciones = new JLabel("Edicion: ");
        GridBagConstraints gbc_lblEdiciones = new GridBagConstraints();
        gbc_lblEdiciones.anchor = GridBagConstraints.EAST;
        gbc_lblEdiciones.insets = new Insets(6, 12, 6, 6);
        gbc_lblEdiciones.gridx = 0; gbc_lblEdiciones.gridy = 1;
        panel.add(lblEdiciones, gbc_lblEdiciones);

        cbListaEdiciones = new JComboBox<>();
        cbListaEdiciones.setModel(new DefaultComboBoxModel<>());
        cbListaEdiciones.setSelectedIndex(-1);
        GridBagConstraints gbc_cbEdicion = new GridBagConstraints();
        gbc_cbEdicion.weightx = 1.0;
        gbc_cbEdicion.insets = new Insets(6, 6, 6, 12);
        gbc_cbEdicion.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbEdicion.gridx = 1; gbc_cbEdicion.gridy = 1;
        panel.add(cbListaEdiciones, gbc_cbEdicion);

        // Tipo de registro
        JLabel lblTipoRegistro = new JLabel("Tipo De Registro: ");
        GridBagConstraints gbc_lblTipo = new GridBagConstraints();
        gbc_lblTipo.anchor = GridBagConstraints.EAST;
        gbc_lblTipo.insets = new Insets(6, 12, 6, 6);
        gbc_lblTipo.gridx = 0; gbc_lblTipo.gridy = 2;
        panel.add(lblTipoRegistro, gbc_lblTipo);

        cbTipoRegistro = new JComboBox<>();
        cbTipoRegistro.setModel(new DefaultComboBoxModel<>());
        cbTipoRegistro.setSelectedIndex(-1);
        GridBagConstraints gbc_cbTipo = new GridBagConstraints();
        gbc_cbTipo.weightx = 1.0;
        gbc_cbTipo.insets = new Insets(6, 6, 6, 12);
        gbc_cbTipo.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbTipo.gridx = 1; gbc_cbTipo.gridy = 2;
        panel.add(cbTipoRegistro, gbc_cbTipo);

        // Asistente
        JLabel lblAsistentes = new JLabel("Asistente: ");
        GridBagConstraints gbc_lblAsis = new GridBagConstraints();
        gbc_lblAsis.anchor = GridBagConstraints.EAST;
        gbc_lblAsis.insets = new Insets(6, 12, 6, 6);
        gbc_lblAsis.gridx = 0; gbc_lblAsis.gridy = 3;
        panel.add(lblAsistentes, gbc_lblAsis);

        cbAsistentes = new JComboBox<>();
        cbAsistentes.setModel(new DefaultComboBoxModel<>());
        cbAsistentes.setSelectedIndex(-1);
        GridBagConstraints gbc_cbAsis = new GridBagConstraints();
        gbc_cbAsis.weightx = 1.0;
        gbc_cbAsis.insets = new Insets(6, 6, 6, 12);
        gbc_cbAsis.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbAsis.gridx = 1; gbc_cbAsis.gridy = 3;
        panel.add(cbAsistentes, gbc_cbAsis);

        // Fecha
        JLabel lblFecha = new JLabel("Fecha: ");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.insets = new Insets(6, 12, 6, 6);
        gbc_lblFecha.gridx = 0; gbc_lblFecha.gridy = 4;
        panel.add(lblFecha, gbc_lblFecha);

        dcFecha = new JDateChooser();
        dcFecha.setDateFormatString("dd/MM/yyyy");
        dcFecha.setToolTipText("Seleccioná la fecha del registro");
        ((javax.swing.JTextField) dcFecha.getDateEditor().getUiComponent()).setEditable(false);
        GridBagConstraints gbc_dcFecha = new GridBagConstraints();
        gbc_dcFecha.weightx = 1.0;
        gbc_dcFecha.insets = new Insets(6, 6, 6, 12);
        gbc_dcFecha.fill = GridBagConstraints.HORIZONTAL;
        gbc_dcFecha.gridx = 1; gbc_dcFecha.gridy = 4;
        panel.add(dcFecha, gbc_dcFecha);

        // Botonera
        JPanel south = new JPanel();
        getContentPane().add(south, BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        south.add(btnAceptar);
        south.add(btnCancelar);

        // Carga inicial y listeners
        cargarAsistentesEnCombo();
        cargarEventosEnCombo();

        cbListaEvento.addActionListener(e -> {
            String evento = (String) cbListaEvento.getSelectedItem();
            cargarEdicionesParaEvento(evento);
        });

        cbListaEdiciones.addActionListener(e -> {
            String evento = (String) cbListaEvento.getSelectedItem();
            String edicion = (String) cbListaEdiciones.getSelectedItem();
            cargarTiposRegistro(evento, edicion);
        });

        btnAceptar.addActionListener(e -> onAceptar());
        btnCancelar.addActionListener(e -> dispose());
    }

    // ================== Acciones ==================

    private void onAceptar() {
        String evento = (String) cbListaEvento.getSelectedItem();
        String edicion = (String) cbListaEdiciones.getSelectedItem();
        String tipo = (String) cbTipoRegistro.getSelectedItem();
        String asistenteDisplay = (String) cbAsistentes.getSelectedItem();

        if (evento == null || edicion == null || tipo == null || asistenteDisplay == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar evento, edición, tipo y asistente.",
                    "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date utilDate = dcFecha.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar la fecha del registro.",
                    "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate fecha = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String nombreAsistente = extractNicknameOrAll(asistenteDisplay);

        try {
            ctrlEvento.altaRegistro(evento, edicion, tipo, nombreAsistente, fecha);
            JOptionPane.showMessageDialog(this, "Registro creado con éxito.",
                    "Alta de Registro", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error en alta de registro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String extractNicknameOrAll(String display) {
        if (display == null) return null;
        int open = display.lastIndexOf('(');
        int close = display.lastIndexOf(')');
        if (open != -1 && close != -1 && close > open + 1) {
            return display.substring(open + 1, close).trim();
        }
        return display.trim();
    }

    // ================== Carga de combos ==================

    private void cargarEventosEnCombo() {
        try {
            DataEvento[] eventos = ctrlEvento.getEventosDTO();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (DataEvento de : eventos) model.addElement(de.getNombre());
            cbListaEvento.setModel(model);
            cbListaEvento.setSelectedIndex(-1);
        } catch (Exception ex) {
            cbListaEvento.setModel(new DefaultComboBoxModel<>());
            cbListaEvento.setSelectedIndex(-1);
        }
        limpiarEdiciones();
        limpiarTiposRegistro();
    }

    private void cargarEdicionesParaEvento(String nombreEvento) {
        if (nombreEvento == null || nombreEvento.isBlank()) {
            limpiarEdiciones();
            limpiarTiposRegistro();
            return;
        }
        try {
            DataEdicion[] ediciones = ctrlEvento.listarEdiciones(nombreEvento);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            if (ediciones != null) for (DataEdicion de : ediciones) model.addElement(de.getNombre());
            cbListaEdiciones.setModel(model);
            cbListaEdiciones.setSelectedIndex(-1);
        } catch (EventoNoExisteException ex) {
            limpiarEdiciones();
        } catch (Exception ex) {
            limpiarEdiciones();
        }
        limpiarTiposRegistro();
    }

    private void limpiarEdiciones() {
        if (cbListaEdiciones == null) return;
        cbListaEdiciones.setModel(new DefaultComboBoxModel<>());
        cbListaEdiciones.setSelectedIndex(-1);
    }

    private void cargarTiposRegistro(String nombreEvento, String nombreEdicion) {
        if (nombreEvento == null || nombreEvento.isBlank()
                || nombreEdicion == null || nombreEdicion.isBlank()) {
            limpiarTiposRegistro();
            return;
        }
        try {
            DataTipoRegistro[] tipos = ctrlEvento.listarTiposRegistro(nombreEvento, nombreEdicion);
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            if (tipos != null) for (DataTipoRegistro t : tipos) model.addElement(t.getNombre());
            cbTipoRegistro.setModel(model);
            cbTipoRegistro.setSelectedIndex(-1);
        } catch (IllegalArgumentException ex) {
            limpiarTiposRegistro();
        } catch (Exception ex) {
            limpiarTiposRegistro();
        }
    }

    private void limpiarTiposRegistro() {
        if (cbTipoRegistro == null) return;
        cbTipoRegistro.setModel(new DefaultComboBoxModel<>());
        cbTipoRegistro.setSelectedIndex(-1);
    }

    private void cargarAsistentesEnCombo() {
        try {
            DataUsuario[] asistentes = ctrlUsuario.getAsistentes();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            if (asistentes != null) {
                for (DataUsuario du : asistentes) {
                    String nick = du.getNickname() != null ? du.getNickname().trim() : "";
                    if (!nick.isBlank()) {
                        model.addElement(nick);
                    }
                }
            }
            cbAsistentes.setModel(model);
            cbAsistentes.setSelectedIndex(-1);
        } catch (Exception ex) {
            cbAsistentes.setModel(new DefaultComboBoxModel<>());
            cbAsistentes.setSelectedIndex(-1);
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            cargarAsistentesEnCombo();
            cargarEventosEnCombo();
        }
        super.setVisible(aFlag);
    }
}
