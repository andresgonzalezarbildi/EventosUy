package presentacion.registros;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import excepciones.EventoNoExisteException;

import java.awt.*;

import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;

public class ConsultaDeTipoDeRegistro extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private JList<DataEvento> listaEventos;
    private DefaultListModel<DataEvento> listaModel;

    private JComboBox<DataEdicion> cbListaEdicion;
    private JComboBox<DataTipoRegistro> cbListaTipoRegistro;

    private JTextField tfNombre;
    private JTextArea taDescripcion;
    private JScrollPane scrollDescripcion;
    private JTextField tfCosto;
    private JTextField tfCupo;

    private IControladorEvento IEV;

    public ConsultaDeTipoDeRegistro(IControladorEvento IEV) {
        super("Consulta De Tipo de Registro", false, true, true, true);
        this.IEV = IEV;

        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // Panel izquierdo: lista de eventos
        listaModel = new DefaultListModel<>();
        listaEventos = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaEventos);

        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBounds(0, 0, 200, 370);
        JLabel lblLista = new JLabel("Eventos", SwingConstants.CENTER);
        panelIzq.add(lblLista, BorderLayout.NORTH);
        panelIzq.add(scrollLista, BorderLayout.CENTER);
        getContentPane().add(panelIzq);

        // Panel derecho: formulario de consulta
        JPanel panelDer = new JPanel();
        panelDer.setBounds(198, 0, 386, 370);
        panelDer.setLayout(new GridBagLayout());
        getContentPane().add(panelDer);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ComboBox Ediciones
        gbc.gridx = 0; gbc.gridy = 0;
        panelDer.add(new JLabel("Edición:"), gbc);
        gbc.gridx = 1;
        cbListaEdicion = new JComboBox<>();
        panelDer.add(cbListaEdicion, gbc);

        // ComboBox Tipos de registro
        gbc.gridx = 0; gbc.gridy = 1;
        panelDer.add(new JLabel("Tipo de Registro:"), gbc);
        gbc.gridx = 1;
        cbListaTipoRegistro = new JComboBox<>();
        panelDer.add(cbListaTipoRegistro, gbc);
        cbListaTipoRegistro.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof DataTipoRegistro) {
                    setText(((DataTipoRegistro) value).getNombre());
                }
                return this;
            }
        });
        cbListaEdicion.setPreferredSize(new Dimension(200, 25));
        cbListaTipoRegistro.setPreferredSize(new Dimension(200, 25));

        
        
        // Campos de información del tipo de registro (no editables)
        gbc.gridx = 0; gbc.gridy = 2;
        panelDer.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        tfNombre = new JTextField();
        tfNombre.setEditable(false);
        panelDer.add(tfNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelDer.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        taDescripcion = new JTextArea(4, 20);
        taDescripcion.setLineWrap(true);
        taDescripcion.setWrapStyleWord(true);
        taDescripcion.setEditable(false);
        taDescripcion.setFont(new Font(UIManager.getFont("TextField.font").getName(), Font.PLAIN, 12));
        taDescripcion.setBackground(UIManager.getColor("TextField.inactiveBackground"));

        scrollDescripcion = new JScrollPane(taDescripcion);
        GridBagConstraints gbcScroll = new GridBagConstraints();
        gbcScroll.gridx = 1;
        gbcScroll.gridy = 3;
        gbcScroll.insets = new Insets(6, 6, 6, 6);
        gbcScroll.fill = GridBagConstraints.BOTH;
        gbcScroll.weightx = 1.0;
        gbcScroll.weighty = 1.0;
        panelDer.add(scrollDescripcion, gbcScroll);

        gbc.gridx = 0; gbc.gridy = 4;
        panelDer.add(new JLabel("Costo:"), gbc);
        gbc.gridx = 1;
        tfCosto = new JTextField();
        tfCosto.setEditable(false);
        panelDer.add(tfCosto, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelDer.add(new JLabel("Cupo:"), gbc);
        gbc.gridx = 1;
        tfCupo = new JTextField();
        tfCupo.setEditable(false);
        panelDer.add(tfCupo, gbc);

        // Listeners
        listaEventos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DataEvento ev = listaEventos.getSelectedValue();
                cargarEdiciones(ev);
            }
        });

        cbListaEdicion.addActionListener(e -> {
            DataEvento ev = listaEventos.getSelectedValue();
            DataEdicion ed = (DataEdicion) cbListaEdicion.getSelectedItem();
            if (ev != null && ed != null && ed.getNombre() != null && !ed.getNombre().equals("No tiene ediciones")) {
                cargarTiposRegistro(ev, ed);
            } else {
                cbListaTipoRegistro.removeAllItems();
                limpiarCamposTipoRegistro();
            }
        });

        cbListaTipoRegistro.addActionListener(e -> {
            DataTipoRegistro tr = (DataTipoRegistro) cbListaTipoRegistro.getSelectedItem();
            if (tr != null) mostrarInfoTipoRegistro(tr);
        });
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            cargarEventos();
        }
        super.setVisible(aFlag);
    }

    private void cargarEventos() {
        listaModel.clear();
        try {
            DataEvento[] eventos = IEV.listarEventoExistentes();
            if (eventos != null) {
                java.util.List<DataEvento> lista = new java.util.ArrayList<>();
                for (DataEvento ev : eventos) {
                    if (ev != null) lista.add(ev);
                }

                // Ordenar por nombre de evento (ignorar mayúsculas/minúsculas)
                lista.sort(java.util.Comparator.comparing(DataEvento::getNombre, String.CASE_INSENSITIVE_ORDER));

                for (DataEvento ev : lista) {
                    listaModel.addElement(ev);
                }
            }
        } catch (EventoNoExisteException ex) {
            JOptionPane.showMessageDialog(this, "No hay eventos cargados.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void cargarEdiciones(DataEvento ev) {
        cbListaEdicion.removeAllItems();
        cbListaTipoRegistro.removeAllItems();
        limpiarCamposTipoRegistro();
        if (ev == null) return;

        try {
            DataEdicion[] ediciones = IEV.listarEdiciones(ev.getNombre());
            if (ediciones != null && ediciones.length > 0) {
                for (DataEdicion ed : ediciones) cbListaEdicion.addItem(ed);
            } else {
                cbListaEdicion.addItem(new DataEdicion("No tiene ediciones", null, null, "", "", "", null, "", null, null, null ,null));
            }
        } catch (EventoNoExisteException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se encontraron ediciones para el evento: " + ev.getNombre(),
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarTiposRegistro(DataEvento ev, DataEdicion ed) {
        cbListaTipoRegistro.removeAllItems();
        limpiarCamposTipoRegistro();
        if (ev == null || ed == null) return;

        DataTipoRegistro[] tipos = IEV.listarTiposRegistro(ev.getNombre(), ed.getNombre());
        if (tipos != null && tipos.length > 0) {
            for (DataTipoRegistro tr : tipos) cbListaTipoRegistro.addItem(tr);
        } else {
            cbListaTipoRegistro.addItem(new DataTipoRegistro("No tiene tipos de registro", "", 0, 0));
        }
    }

    private void mostrarInfoTipoRegistro(DataTipoRegistro tr) {
        tfNombre.setText(tr.getNombre());
        taDescripcion.setText(tr.getDescripcion());
        tfCosto.setText(String.valueOf(tr.getCosto()));
        tfCupo.setText(String.valueOf(tr.getCupo()));
    }

    private void limpiarCamposTipoRegistro() {
        tfNombre.setText("");
        taDescripcion.setText("");
        tfCosto.setText("");
        tfCupo.setText("");
    }

    public DataEvento getEventoSeleccionado() {
        return listaEventos.getSelectedValue();
    }
    
    public void seleccionarEventoYEdicionYTipo(DataEvento ev, DataEdicion ed, DataTipoRegistro tr) {
        cargarEventos();

        for (int i = 0; i < listaEventos.getModel().getSize(); i++) {
            DataEvento item = listaEventos.getModel().getElementAt(i);
            if (item.getNombre().equals(ev.getNombre())) {
                listaEventos.setSelectedIndex(i);
                ev = item; 
                break;
            }
        }

        cargarEdiciones(ev);
        for (int i = 0; i < cbListaEdicion.getItemCount(); i++) {
            DataEdicion item = cbListaEdicion.getItemAt(i);
            if (item.getNombre().equals(ed.getNombre())) {
                cbListaEdicion.setSelectedIndex(i);
                ed = item;
                break;
            }
        }

        cargarTiposRegistro(ev, ed);
        for (int i = 0; i < cbListaTipoRegistro.getItemCount(); i++) {
            DataTipoRegistro item = cbListaTipoRegistro.getItemAt(i);
            if (item.getNombre().equals(tr.getNombre())) {
                cbListaTipoRegistro.setSelectedIndex(i);
                break;
            }
        }
    }

    
    public void mostrarTipoRegistro(DataEvento ev, DataEdicion de, DataTipoRegistro tr) {
        // no recargar eventos desde el controlador
        listaModel.clear();
        listaModel.addElement(ev);
        listaEventos.setSelectedValue(ev, true);

        cbListaEdicion.removeAllItems();
        cbListaEdicion.addItem(de);
        cbListaEdicion.setSelectedItem(de);

        cbListaTipoRegistro.removeAllItems();
        cbListaTipoRegistro.addItem(tr);
        cbListaTipoRegistro.setSelectedItem(tr);

        // mostrar info del tipo
        mostrarInfoTipoRegistro(tr);
    }



}
