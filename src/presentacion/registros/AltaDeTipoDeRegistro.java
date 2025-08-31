package presentacion.registros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;

@SuppressWarnings("serial")
public class AltaDeTipoDeRegistro extends JInternalFrame {
    private IControladorEvento IEV;
    // Campos de texto separados
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtCosto;
    private JTextField txtCupo;

    // Combos
    private JComboBox<String> cbListaEvento;
    private JComboBox<String> cbListaEdiciones;

    private boolean cargandoEventos = false;

    public AltaDeTipoDeRegistro(IControladorEvento IEV) {
        super("Alta De Tipo De Registro", false, true, true, true);
        this.IEV = IEV;
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 500, 350);
        getContentPane().setLayout(new BorderLayout(0, 0));

        // Panel central
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().add(panel, BorderLayout.CENTER);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        panel.setLayout(gbl_panel);

        // Evento
        JLabel lblEventos = new JLabel("Evento:");
        GridBagConstraints gbc_lblEventos = new GridBagConstraints();
        gbc_lblEventos.insets = new Insets(6, 12, 6, 6);
        gbc_lblEventos.anchor = GridBagConstraints.EAST;
        gbc_lblEventos.gridx = 0;
        gbc_lblEventos.gridy = 0;
        panel.add(lblEventos, gbc_lblEventos);

        cbListaEvento = new JComboBox<>();
        cbListaEvento.addItem("");  
        cbListaEvento.setSelectedIndex(0);     
        cbListaEvento.addActionListener(e -> {
            if (cargandoEventos) return; // 
            String eventoSel = (String) cbListaEvento.getSelectedItem();
            if (eventoSel != null && !eventoSel.equals("")) {
                cargarEdiciones(eventoSel, true);
            } else {
                cbListaEdiciones.removeAllItems();
                cbListaEdiciones.addItem("");
                cbListaEdiciones.setSelectedIndex(0);
            }
        });

        GridBagConstraints gbc_ListaEvento = new GridBagConstraints();
        gbc_ListaEvento.weightx = 1.0;
        gbc_ListaEvento.insets = new Insets(6, 6, 6, 12);
        gbc_ListaEvento.fill = GridBagConstraints.HORIZONTAL;
        gbc_ListaEvento.gridx = 1;
        gbc_ListaEvento.gridy = 0;
        panel.add(cbListaEvento, gbc_ListaEvento);

        // Ediciones
        JLabel lblEdiciones = new JLabel("Edición:");
        GridBagConstraints gbc_lblEdiciones = new GridBagConstraints();
        gbc_lblEdiciones.anchor = GridBagConstraints.EAST;
        gbc_lblEdiciones.insets = new Insets(6, 12, 6, 6);
        gbc_lblEdiciones.gridx = 0;
        gbc_lblEdiciones.gridy = 1;
        panel.add(lblEdiciones, gbc_lblEdiciones);

        cbListaEdiciones = new JComboBox<>();
        cbListaEdiciones.addItem("");
        cbListaEdiciones.setSelectedIndex(0);

        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.weightx = 1.0;
        gbc_comboBox.insets = new Insets(6, 6, 6, 12);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 1;
        panel.add(cbListaEdiciones, gbc_comboBox);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(6, 12, 6, 6);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 2;
        panel.add(lblNombre, gbc_lblNombre);

        txtNombre = new JTextField();
        txtNombre.setToolTipText("Ingrese un nombre válido");
        GridBagConstraints gbc_txtNombre = new GridBagConstraints();
        gbc_txtNombre.insets = new Insets(6, 6, 6, 12);
        gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNombre.gridx = 1;
        gbc_txtNombre.gridy = 2;
        panel.add(txtNombre, gbc_txtNombre);

        // Descripción
        JLabel lblDescripcion = new JLabel("Descripción:");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.insets = new Insets(6, 12, 6, 6);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 3;
        panel.add(lblDescripcion, gbc_lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setToolTipText("Ingrese una descripción");
        GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
        gbc_txtDescripcion.insets = new Insets(6, 6, 6, 12);
        gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDescripcion.gridx = 1;
        gbc_txtDescripcion.gridy = 3;
        panel.add(txtDescripcion, gbc_txtDescripcion);

        // Costo
        JLabel lblCosto = new JLabel("Costo:");
        GridBagConstraints gbc_lblCosto = new GridBagConstraints();
        gbc_lblCosto.anchor = GridBagConstraints.EAST;
        gbc_lblCosto.insets = new Insets(6, 12, 6, 6);
        gbc_lblCosto.gridx = 0;
        gbc_lblCosto.gridy = 4;
        panel.add(lblCosto, gbc_lblCosto);

        txtCosto = new JTextField();
        txtCosto.setToolTipText("Ingrese el costo");
        GridBagConstraints gbc_txtCosto = new GridBagConstraints();
        gbc_txtCosto.insets = new Insets(6, 6, 6, 12);
        gbc_txtCosto.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCosto.gridx = 1;
        gbc_txtCosto.gridy = 4;
        panel.add(txtCosto, gbc_txtCosto);

        // Cupo
        JLabel lblCupo = new JLabel("Cupo:");
        GridBagConstraints gbc_lblCupo = new GridBagConstraints();
        gbc_lblCupo.anchor = GridBagConstraints.EAST;
        gbc_lblCupo.insets = new Insets(6, 12, 6, 6);
        gbc_lblCupo.gridx = 0;
        gbc_lblCupo.gridy = 5;
        panel.add(lblCupo, gbc_lblCupo);

        txtCupo = new JTextField();
        txtCupo.setToolTipText("Ingrese el cupo");
        GridBagConstraints gbc_txtCupo = new GridBagConstraints();
        gbc_txtCupo.insets = new Insets(6, 6, 6, 12);
        gbc_txtCupo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtCupo.gridx = 1;
        gbc_txtCupo.gridy = 5;
        panel.add(txtCupo, gbc_txtCupo);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> cmdAltaTipoRegistroActionPerformed());
        panelBotones.add(btnAceptar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            limpiarFormulario();
            dispose();
        });
        panelBotones.add(btnCancelar);
    }

    private void cmdAltaTipoRegistroActionPerformed() {
        try {
            String eventoSel = (String) cbListaEvento.getSelectedItem();
            String edicionSel = (String) cbListaEdiciones.getSelectedItem();

            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String costoStr = txtCosto.getText().trim();
            String cupoStr = txtCupo.getText().trim();

            if (eventoSel == null || eventoSel.equals("")) {
                throw new IllegalArgumentException("Debe seleccionar un evento.");
            }
            if (edicionSel == null || edicionSel.equals("")) {
                throw new IllegalArgumentException("Debe seleccionar una edición.");
            }
            if (nombre.isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }
            if (descripcion.isEmpty()) {
                throw new IllegalArgumentException("La descripción no puede estar vacía.");
            }

            int costo, cupo;
            try {
                costo = Integer.parseInt(costoStr);
            } catch (NumberFormatException nfe) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El costo debe ser un número entero.",
                    "Error de entrada",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                cupo = Integer.parseInt(cupoStr);
            } catch (NumberFormatException nfe) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "El cupo debe ser un número entero.",
                    "Error de entrada",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                IEV.altaTipoRegistro(eventoSel, edicionSel, nombre, descripcion, costo, cupo);

                javax.swing.JOptionPane.showMessageDialog(this,
                    "El tipo de registro \"" + nombre + "\" fue agregado correctamente.",
                    "Alta Tipo de Registro",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                dispose();

            } catch (IllegalArgumentException ex) {
                if (ex.getMessage().contains("Ya existe un tipo registro")) {
                    int opcion = javax.swing.JOptionPane.showConfirmDialog(
                            this,
                            "Ya existe un tipo de registro con ese nombre.\n¿Desea cambiar el nombre?",
                            "Nombre duplicado",
                            javax.swing.JOptionPane.YES_NO_OPTION,
                            javax.swing.JOptionPane.WARNING_MESSAGE
                    );

                    if (opcion == javax.swing.JOptionPane.YES_OPTION) {
                        txtNombre.requestFocus();
                    } else {
                        dispose();
                    }
                } else {
                    throw ex;
                }
            }

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Error: " + ex.getMessage(),
                "Alta Tipo de Registro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEventos() {
        cargandoEventos = true;
        cbListaEvento.removeAllItems();
        cbListaEvento.addItem("");
        DataEvento[] eventos = IEV.getEventosDTO();
        if (eventos != null) {
            for (DataEvento ev : eventos) {
                if (ev != null) {
                    cbListaEvento.addItem(ev.getNombre());
                }
            }
        }
        cbListaEvento.setSelectedIndex(0);
        cargandoEventos = false;
    }

    private void cargarEdiciones(String nombreEvento, boolean mostrarMensaje) {
        cbListaEdiciones.removeAllItems();
        cbListaEdiciones.addItem("");
        cbListaEdiciones.setSelectedIndex(0);

        try {
            DataEdicion[] ediciones = IEV.listarEdiciones(nombreEvento);

            if ((ediciones == null || ediciones.length == 0) && mostrarMensaje) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "El evento \"" + nombreEvento + "\" no tiene ediciones asociadas.",
                    "Sin ediciones",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                for (DataEdicion ed : ediciones) {
                    if (ed != null) {
                        cbListaEdiciones.addItem(ed.getNombre());
                    }
                }
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Error al cargar ediciones: " + ex.getMessage(),
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (aFlag) {
            limpiarFormulario();
            cargarEventos();
        }
        super.setVisible(aFlag);
    }

    private void limpiarFormulario() {
        txtDescripcion.setText("");
        txtNombre.setText("");
        txtCosto.setText("");
        txtCupo.setText("");

        cbListaEdiciones.removeAllItems();
        cbListaEdiciones.addItem("");
        cbListaEdiciones.setSelectedIndex(0);

        cbListaEvento.setSelectedIndex(0);

        getContentPane().revalidate();
        getContentPane().repaint();
    }
}
