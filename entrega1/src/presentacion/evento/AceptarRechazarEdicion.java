package presentacion.evento;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import excepciones.EdicionNoExisteException;
import excepciones.TransicionEstadoInvalidaException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataPatrocinio;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import presentacion.registros.ConsultaDeTipoDeRegistro;

public class AceptarRechazarEdicion extends JInternalFrame {
    private JTextField textOrganizador, textNombre, textFechaIni, textFechaFin, textCiudad, textPais, textSigla, textAlta;
    private IControladorEvento controlEvento;

    private JComboBox<DataEdicion> comboBoxEdiciones;

    private DefaultListModel<DataTipoRegistro> modeloTipos;
    private JList<DataTipoRegistro> listaTipos;

    private DefaultListModel<DataPatrocinio> modeloPatrocinios;
    private JList<DataPatrocinio> listaPatrocinios;

    private ConsultaDeTipoDeRegistro ventanaConsulta = null;

    // Botones
    private JButton btnAceptar;
    private JButton btnRechazar;

    public AceptarRechazarEdicion(IControladorEvento controlEvento) {
        super("Consulta Edicion Evento", false, true, true, true);
        this.controlEvento = controlEvento;
        setBounds(100, 100, 750, 450);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Layout general
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 39, 360, 0};
        gridBagLayout.rowHeights = new int[]{22, 400, 0}; // (0) combo ediciones, (1) detalle
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        // --- Edición ---
        JLabel lblEdicion = new JLabel("Edición:");
        GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
        gbc_lblEdicion.anchor = GridBagConstraints.EAST;
        gbc_lblEdicion.insets = new Insets(0, 0, 5, 5);
        gbc_lblEdicion.gridx = 1;
        gbc_lblEdicion.gridy = 0;
        getContentPane().add(lblEdicion, gbc_lblEdicion);

        comboBoxEdiciones = new JComboBox<>();
        GridBagConstraints gbc_comboBoxEdiciones = new GridBagConstraints();
        gbc_comboBoxEdiciones.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEdiciones.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxEdiciones.gridx = 2;
        gbc_comboBoxEdiciones.gridy = 0;
        getContentPane().add(comboBoxEdiciones, gbc_comboBoxEdiciones);

        // --- Panel detalle ---
        JPanel panelDetalle = new JPanel(new BorderLayout(5, 5));
        GridBagConstraints gbc_panelDetalle = new GridBagConstraints();
        gbc_panelDetalle.fill = GridBagConstraints.BOTH;
        gbc_panelDetalle.gridx = 2;
        gbc_panelDetalle.gridy = 1;
        getContentPane().add(panelDetalle, gbc_panelDetalle);

        JLabel lblInfoEdicion = new JLabel("Información de la edición del evento:");
        lblInfoEdicion.setHorizontalAlignment(SwingConstants.CENTER);
        panelDetalle.add(lblInfoEdicion, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel();
        panelDetalle.add(panelCampos, BorderLayout.NORTH);
        GridBagLayout gbl_panelCampos = new GridBagLayout();
        gbl_panelCampos.columnWidths = new int[]{0, 0};
        gbl_panelCampos.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_panelCampos.columnWeights = new double[]{0.0, 1.0};
        gbl_panelCampos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        panelCampos.setLayout(gbl_panelCampos);

        textNombre = addTextField(panelCampos, "Nombre:", 0);
        textFechaIni = addTextField(panelCampos, "Fecha inicio:", 1);
        textFechaFin = addTextField(panelCampos, "Fecha fin:", 2);
        textCiudad = addTextField(panelCampos, "Ciudad:", 3);
        textPais = addTextField(panelCampos, "País:", 4);
        textSigla = addTextField(panelCampos, "Sigla:", 5);
        textAlta = addTextField(panelCampos, "Fecha alta:", 6);
        textOrganizador = addTextField(panelCampos, "Organizador:", 7);

        modeloTipos = new DefaultListModel<>();
        listaTipos = new JList<>(modeloTipos);
        listaTipos.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DataTipoRegistro tr) {
                    setText(tr.getNombre());
                }
                return this;
            }
        });
        JScrollPane scrollTipos = new JScrollPane(listaTipos);
        scrollTipos.setBorder(BorderFactory.createTitledBorder("Tipos de Registro"));

        modeloPatrocinios = new DefaultListModel<>();
        listaPatrocinios = new JList<>(modeloPatrocinios);
        JScrollPane scrollPatrocinios = new JScrollPane(listaPatrocinios);
        scrollPatrocinios.setBorder(BorderFactory.createTitledBorder("Patrocinios"));

        GridBagConstraints gbc_scrollTipos = new GridBagConstraints();
        gbc_scrollTipos.gridx = 0;
        gbc_scrollTipos.gridy = 8;
        gbc_scrollTipos.weightx = 0.5;
        gbc_scrollTipos.weighty = 1.0;
        gbc_scrollTipos.fill = GridBagConstraints.BOTH;
        panelCampos.add(scrollTipos, gbc_scrollTipos);

        GridBagConstraints gbc_scrollPatrocinios = new GridBagConstraints();
        gbc_scrollPatrocinios.gridx = 1;
        gbc_scrollPatrocinios.gridy = 8;
        gbc_scrollPatrocinios.weightx = 0.5;
        gbc_scrollPatrocinios.weighty = 1.0;
        gbc_scrollPatrocinios.fill = GridBagConstraints.BOTH;
        panelCampos.add(scrollPatrocinios, gbc_scrollPatrocinios);

        // --- Botonera inferior ---
        JPanel panelBotones = new JPanel(); // FlowLayout por defecto
        btnAceptar = new JButton("Aceptar Edición");
        btnRechazar = new JButton("Rechazar Edición");
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
        panelBotones.add(btnAceptar);
        panelBotones.add(btnRechazar);
        panelDetalle.add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        comboBoxEdiciones.addActionListener(e -> {
            DataEdicion de = (DataEdicion) comboBoxEdiciones.getSelectedItem();
            boolean haySeleccion = de != null;
            btnAceptar.setEnabled(haySeleccion);
            btnRechazar.setEnabled(haySeleccion);
            if (de != null) mostrarInfoEdicion(de);
            else limpiarCamposDetalle();
        });

        listaTipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    DataTipoRegistro tr = listaTipos.getSelectedValue();
                    DataEdicion de = (DataEdicion) comboBoxEdiciones.getSelectedItem();
                    if (tr != null && de != null) {
                        if (ventanaConsulta != null && ventanaConsulta.isVisible()) ventanaConsulta.dispose();
                        ventanaConsulta = new ConsultaDeTipoDeRegistro(controlEvento);
                        ventanaConsulta.setVisible(true);
                        // Pasamos null como evento; adaptar si tu otra ventana lo requiere.
                        ventanaConsulta.seleccionarEventoYEdicionYTipo(null, de, tr);

                        JDesktopPane desktop = AceptarRechazarEdicion.this.getDesktopPane();
                        if (desktop != null) {
                            desktop.add(ventanaConsulta);
                            try { ventanaConsulta.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        }
                    }
                }
            }
        });

        // Acciones de los botones
        btnAceptar.addActionListener(e -> {
            DataEdicion de = (DataEdicion) comboBoxEdiciones.getSelectedItem();
            if (de == null) {
                JOptionPane.showMessageDialog(this, "Seleccioná una edición primero.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                controlEvento.aceptarEdicion(de.getNombre(), true);
                JOptionPane.showMessageDialog(this, "Edición aceptada correctamente.", "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarEdiciones(); // refresca y limpia
            } catch (EdicionNoExisteException | TransicionEstadoInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRechazar.addActionListener(e -> {
            DataEdicion de = (DataEdicion) comboBoxEdiciones.getSelectedItem();
            if (de == null) {
                JOptionPane.showMessageDialog(this, "Seleccioná una edición primero.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                controlEvento.aceptarEdicion(de.getNombre(), false);
                JOptionPane.showMessageDialog(this, "Edición rechazada correctamente.", "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarEdiciones(); // refresca y limpia
            } catch (EdicionNoExisteException | TransicionEstadoInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Carga directa de ediciones INGRESADAS
        cargarEdiciones();
    }

    private JTextField addTextField(JPanel panel, String label, int row) {
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.anchor = GridBagConstraints.WEST;
        gbcLabel.insets = new Insets(2, 2, 2, 2);
        gbcLabel.gridx = 0;
        gbcLabel.gridy = row;
        panel.add(new JLabel(label), gbcLabel);

        JTextField tf = new JTextField();
        tf.setEditable(false);
        GridBagConstraints gbcTf = new GridBagConstraints();
        gbcTf.fill = GridBagConstraints.HORIZONTAL;
        gbcTf.gridx = 1;
        gbcTf.gridy = row;
        gbcTf.insets = new Insets(2, 2, 2, 2);
        panel.add(tf, gbcTf);
        return tf;
    }

    private void cargarEdiciones() {
        comboBoxEdiciones.removeAllItems();
        try {
            DataEdicion[] ediciones = controlEvento.listarEdicionesIngresadas();
            if (ediciones != null) {
                java.util.List<DataEdicion> lista = new java.util.ArrayList<>();
                for (DataEdicion de : ediciones) if (de != null) lista.add(de);

                // Ordenar alfabéticamente por nombre (case-insensitive)
                lista.sort(Comparator.comparing(DataEdicion::getNombre, String.CASE_INSENSITIVE_ORDER));

                for (DataEdicion de : lista) comboBoxEdiciones.addItem(de);
            }
        } catch (EdicionNoExisteException ex) {
            // Silenciado: no mostramos diálogo al abrir la ventana
        }

        // Dejar combo sin selección
        comboBoxEdiciones.setSelectedIndex(-1);

        // Limpiar detalle y deshabilitar botones
        limpiarCamposDetalle();
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
    }

    private void limpiarCamposDetalle() {
        textNombre.setText("");
        textFechaIni.setText("");
        textFechaFin.setText("");
        textCiudad.setText("");
        textPais.setText("");
        textSigla.setText("");
        textAlta.setText("");
        textOrganizador.setText("");

        modeloTipos.clear();
        modeloPatrocinios.clear();
    }

    private void mostrarInfoEdicion(DataEdicion de) {
        textNombre.setText(de.getNombre());
        textFechaIni.setText(de.getFechaIni().toString());
        textFechaFin.setText(de.getFechaFin().toString());
        textCiudad.setText(de.getCiudad());
        textPais.setText(de.getPais());
        textSigla.setText(de.getSigla());
        textAlta.setText(de.getFechaAltaEnPlataforma().toString());
        textOrganizador.setText(de.getOrganizador());

        modeloTipos.clear();
        if (de.getTiposRegistro() != null)
            for (DataTipoRegistro tr : de.getTiposRegistro()) modeloTipos.addElement(tr);

        modeloPatrocinios.clear();
        if (de.getPatrocinios() != null)
            for (DataPatrocinio p : de.getPatrocinios()) modeloPatrocinios.addElement(p);
    }

    // Mantengo la firma por compatibilidad; se ignora el evento y se usa sólo la edición.
    public void setContext(logica.datatypes.DataEvento eventoIgnorado, DataEdicion edicion) {
        cargarEdiciones();

        if (edicion != null) {
            for (int i = 0; i < comboBoxEdiciones.getItemCount(); i++) {
                DataEdicion de = comboBoxEdiciones.getItemAt(i);
                if (de != null && de.getNombre().equals(edicion.getNombre())) {
                    comboBoxEdiciones.setSelectedIndex(i);
                    mostrarInfoEdicion(de);
                    break;
                }
            }
        }

        setTitle("Consulta Edición" + (edicion != null ? " – " + edicion.getNombre() : ""));
    }

    // Reset total (sin eventos)
    public void resetearVentana() {
        limpiarCamposDetalle();
        comboBoxEdiciones.removeAllItems();
        cargarEdiciones();
        setTitle("Consulta Edicion Evento");
    }
}
