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
import excepciones.EventoNoExisteException;
import excepciones.TransicionEstadoInvalidaException;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataPatrocinio;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import presentacion.registros.ConsultaDeTipoDeRegistro;

public class AceptarRechazarEdicion extends JInternalFrame {
    private JTextField textOrganizador, textNombre, textFechaIni, textFechaFin, textCiudad, textPais, textSigla, textAlta;
    private IControladorEvento controlEvento;

    private JComboBox<DataEvento> comboBoxEvento;
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
        super("Aceptar / Rechazar Edición", false, true, true, true);
        this.controlEvento = controlEvento;
        setBounds(100, 100, 800, 520);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // Layout general 
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 39, 360, 0};
        gridBagLayout.rowHeights = new int[]{22, 22, 400, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);

        //Evento
        JLabel lblEvento = new JLabel("Evento:");
        GridBagConstraints gbc_lblEvento = new GridBagConstraints();
        gbc_lblEvento.anchor = GridBagConstraints.EAST;
        gbc_lblEvento.insets = new Insets(0, 0, 5, 5);
        gbc_lblEvento.gridx = 1;
        gbc_lblEvento.gridy = 0;
        getContentPane().add(lblEvento, gbc_lblEvento);

        comboBoxEvento = new JComboBox<>();
        GridBagConstraints gbc_comboBoxEvento = new GridBagConstraints();
        gbc_comboBoxEvento.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEvento.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxEvento.gridx = 2;
        gbc_comboBoxEvento.gridy = 0;
        getContentPane().add(comboBoxEvento, gbc_comboBoxEvento);

        //Edicion
        JLabel lblEdicion = new JLabel("Edición:");
        GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
        gbc_lblEdicion.anchor = GridBagConstraints.EAST;
        gbc_lblEdicion.insets = new Insets(0, 0, 5, 5);
        gbc_lblEdicion.gridx = 1;
        gbc_lblEdicion.gridy = 1;
        getContentPane().add(lblEdicion, gbc_lblEdicion);

        comboBoxEdiciones = new JComboBox<>();
        GridBagConstraints gbc_comboBoxEdiciones = new GridBagConstraints();
        gbc_comboBoxEdiciones.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEdiciones.insets = new Insets(0, 0, 5, 0);
        gbc_comboBoxEdiciones.gridx = 2;
        gbc_comboBoxEdiciones.gridy = 1;
        getContentPane().add(comboBoxEdiciones, gbc_comboBoxEdiciones);

        //Panel detalle
        JPanel panelDetalle = new JPanel(new BorderLayout(5, 5));
        GridBagConstraints gbc_panelDetalle = new GridBagConstraints();
        gbc_panelDetalle.fill = GridBagConstraints.BOTH;
        gbc_panelDetalle.gridx = 2;
        gbc_panelDetalle.gridy = 2;
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
                if (value instanceof DataTipoRegistro tr) setText(tr.getNombre());
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

        //Botones 
        JPanel panelBotones = new JPanel(); 
        btnAceptar = new JButton("Aceptar Edición");
        btnRechazar = new JButton("Rechazar Edición");
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
        panelBotones.add(btnAceptar);
        panelBotones.add(btnRechazar);
        panelDetalle.add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        comboBoxEvento.addActionListener(e -> {
            DataEvento seleccionado = (DataEvento) comboBoxEvento.getSelectedItem();
            if (seleccionado != null) cargarEdiciones(seleccionado.getNombre());
        });

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
                    DataEvento ev = (DataEvento) comboBoxEvento.getSelectedItem();
                    if (tr != null && de != null && ev != null) {
                        if (ventanaConsulta != null && ventanaConsulta.isVisible()) ventanaConsulta.dispose();
                        ventanaConsulta = new ConsultaDeTipoDeRegistro(controlEvento);
                        ventanaConsulta.setVisible(true);
                        ventanaConsulta.seleccionarEventoYEdicionYTipo(ev, de, tr);

                        JDesktopPane desktop = AceptarRechazarEdicion.this.getDesktopPane();
                        if (desktop != null) {
                            desktop.add(ventanaConsulta);
                            try { ventanaConsulta.setSelected(true); } catch (java.beans.PropertyVetoException ex) {}
                        }
                    }
                }
            }
        });

        //botones
        btnAceptar.addActionListener(e -> {
            DataEdicion de = (DataEdicion) comboBoxEdiciones.getSelectedItem();
            if (de == null) {
                JOptionPane.showMessageDialog(this, "Seleccioná una edición primero.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                controlEvento.aceptarEdicion(de.getNombre(), true);
                JOptionPane.showMessageDialog(this, "Edición aceptada correctamente.", "OK", JOptionPane.INFORMATION_MESSAGE);
                recargarEdicionesManteniendoEvento();
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
                recargarEdicionesManteniendoEvento();
            } catch (EdicionNoExisteException | TransicionEstadoInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

       
        cargarEventos();
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

    private void cargarEventos() {
        comboBoxEvento.removeAllItems();
        DataEvento[] eventos = controlEvento.getEventosDTO();
        if (eventos != null) {
            java.util.List<DataEvento> lista = new java.util.ArrayList<>();
            for (DataEvento ev : eventos) if (ev != null) lista.add(ev);

            // Ordenar por nombre
            lista.sort(Comparator.comparing(DataEvento::getNombre, String.CASE_INSENSITIVE_ORDER));

            for (DataEvento ev : lista) comboBoxEvento.addItem(ev);
        }
        comboBoxEvento.setSelectedIndex(-1);

        // reset ediciones y detalle
        comboBoxEdiciones.removeAllItems();
        limpiarCamposDetalle();
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
    }

    private void cargarEdiciones(String nombreEvento) {
        comboBoxEdiciones.removeAllItems();
        try {
        	DataEdicion[] ediciones = controlEvento.listarEdicionesIngresadasEvento(nombreEvento);
            if (ediciones != null) {
                java.util.List<DataEdicion> lista = new java.util.ArrayList<>();
                for (DataEdicion de : ediciones) if (de != null) lista.add(de);

                // Ordenar alfabeticamente por nombre
                lista.sort(Comparator.comparing(DataEdicion::getNombre, String.CASE_INSENSITIVE_ORDER));

                for (DataEdicion de : lista) comboBoxEdiciones.addItem(de);
            }
        } catch (EdicionNoExisteException ex) {}

        comboBoxEdiciones.setSelectedIndex(-1); 
        limpiarCamposDetalle();
        btnAceptar.setEnabled(false);
        btnRechazar.setEnabled(false);
    }

    private void recargarEdicionesManteniendoEvento() {
        DataEvento ev = (DataEvento) comboBoxEvento.getSelectedItem();
        if (ev != null) cargarEdiciones(ev.getNombre());
        else {
            comboBoxEdiciones.removeAllItems();
            limpiarCamposDetalle();
            btnAceptar.setEnabled(false);
            btnRechazar.setEnabled(false);
        }
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

   
    public void setContext(DataEvento evento, DataEdicion edicion) {
        cargarEventos();

        if (evento != null) {
            for (int i = 0; i < comboBoxEvento.getItemCount(); i++) {
                DataEvento ev = comboBoxEvento.getItemAt(i);
                if (ev != null && ev.getNombre().equals(evento.getNombre())) {
                    comboBoxEvento.setSelectedIndex(i);
                    break;
                }
            }
            cargarEdiciones(evento.getNombre());
        }

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

        setTitle("Aceptar / Rechazar – " +
                (evento != null ? evento.getNombre() : "Edición") +
                (edicion != null ? " / " + edicion.getNombre() : ""));
    }

    // Reset total
    public void resetearVentana() {
        limpiarCamposDetalle();
        comboBoxEdiciones.removeAllItems();
        cargarEventos();
        setTitle("Aceptar / Rechazar Edición");
    }
}
