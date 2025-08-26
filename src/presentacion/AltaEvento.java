package presentacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AltaEvento extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTextField tfNombre;
    private JTextField tfDescripcion;
    private JTextField tfSigla;
    private JList<String> listaCategoriasDisponibles;
    private JList<String> listaCategoriasSeleccionadas;
    private DefaultListModel<String> modeloDisponibles;
    private DefaultListModel<String> modeloSeleccionadas;

    public AltaEvento() {
        setTitle("Alta de Evento");
        setModal(true);
        setResizable(false);
        setBounds(100, 100, 613, 331);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().add(panel, BorderLayout.WEST);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
        gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        // --- Nombre ---
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNombre.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(6, 12, 6, 6);
        gbc_lblNombre.gridx = 0;
        gbc_lblNombre.gridy = 0;
        panel.add(lblNombre, gbc_lblNombre);

        tfNombre = new JTextField();
        GridBagConstraints gbc_tfNombre = new GridBagConstraints();
        gbc_tfNombre.insets = new Insets(6, 6, 6, 12);
        gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfNombre.weightx = 1.0;
        gbc_tfNombre.gridx = 1;
        gbc_tfNombre.gridy = 0;
        gbc_tfNombre.gridwidth = 2;
        panel.add(tfNombre, gbc_tfNombre);

        // --- Descripción ---
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
        gbc_lblDescripcion.insets = new Insets(6, 12, 6, 6);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 1;
        panel.add(lblDescripcion, gbc_lblDescripcion);

        tfDescripcion = new JTextField();
        GridBagConstraints gbc_tfDescripcion = new GridBagConstraints();
        gbc_tfDescripcion.insets = new Insets(6, 6, 6, 12);
        gbc_tfDescripcion.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfDescripcion.weightx = 1.0;
        gbc_tfDescripcion.gridx = 1;
        gbc_tfDescripcion.gridy = 1;
        gbc_tfDescripcion.gridwidth = 2;
        panel.add(tfDescripcion, gbc_tfDescripcion);

        // --- Sigla ---
        JLabel lblSigla = new JLabel("Sigla:");
        lblSigla.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GridBagConstraints gbc_lblSigla = new GridBagConstraints();
        gbc_lblSigla.anchor = GridBagConstraints.EAST;
        gbc_lblSigla.insets = new Insets(6, 12, 6, 6);
        gbc_lblSigla.gridx = 0;
        gbc_lblSigla.gridy = 2;
        panel.add(lblSigla, gbc_lblSigla);

        tfSigla = new JTextField();
        GridBagConstraints gbc_tfSigla = new GridBagConstraints();
        gbc_tfSigla.insets = new Insets(6, 6, 6, 12);
        gbc_tfSigla.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfSigla.weightx = 1.0;
        gbc_tfSigla.gridx = 1;
        gbc_tfSigla.gridy = 2;
        gbc_tfSigla.gridwidth = 2;
        panel.add(tfSigla, gbc_tfSigla);

        // --- Listas de Categorías ---
        JLabel lblDisponibles = new JLabel("Categorías Disponibles:");
        lblDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GridBagConstraints gbc_lblDisponibles = new GridBagConstraints();
        gbc_lblDisponibles.anchor = GridBagConstraints.CENTER;
        gbc_lblDisponibles.insets = new Insets(6, 12, 6, 6);
        gbc_lblDisponibles.gridx = 0;
        gbc_lblDisponibles.gridy = 3;
        panel.add(lblDisponibles, gbc_lblDisponibles);

        JLabel lblSeleccionadas = new JLabel("Categorías Seleccionadas:");
        lblSeleccionadas.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GridBagConstraints gbc_lblSeleccionadas = new GridBagConstraints();
        gbc_lblSeleccionadas.anchor = GridBagConstraints.CENTER;
        gbc_lblSeleccionadas.insets = new Insets(6, 12, 6, 6);
        gbc_lblSeleccionadas.gridx = 2;
        gbc_lblSeleccionadas.gridy = 3;
        panel.add(lblSeleccionadas, gbc_lblSeleccionadas);

        modeloDisponibles = new DefaultListModel<>();
        modeloDisponibles.addElement("Categoria 1");
        modeloDisponibles.addElement("Categoria 2");
        modeloDisponibles.addElement("Categoria 3");
        listaCategoriasDisponibles = new JList<>(modeloDisponibles);
        listaCategoriasDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        GridBagConstraints gbc_listaDisponibles = new GridBagConstraints();
        gbc_listaDisponibles.insets = new Insets(6, 6, 6, 6);
        gbc_listaDisponibles.fill = GridBagConstraints.BOTH;
        gbc_listaDisponibles.weightx = 1.0;
        gbc_listaDisponibles.weighty = 1.0;
        gbc_listaDisponibles.gridx = 0;
        gbc_listaDisponibles.gridy = 4;
        panel.add(new JScrollPane(listaCategoriasDisponibles), gbc_listaDisponibles);

        modeloSeleccionadas = new DefaultListModel<>();
        listaCategoriasSeleccionadas = new JList<>(modeloSeleccionadas);
        listaCategoriasSeleccionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        GridBagConstraints gbc_listaSeleccionadas = new GridBagConstraints();
        gbc_listaSeleccionadas.insets = new Insets(6, 6, 6, 12);
        gbc_listaSeleccionadas.fill = GridBagConstraints.BOTH;
        gbc_listaSeleccionadas.weightx = 1.0;
        gbc_listaSeleccionadas.weighty = 1.0;
        gbc_listaSeleccionadas.gridx = 2;
        gbc_listaSeleccionadas.gridy = 4;
        panel.add(new JScrollPane(listaCategoriasSeleccionadas), gbc_listaSeleccionadas);

        // --- Botón para pasar categorías ---
        JButton btnAgregar = new JButton(">>");
        btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnAgregar.addActionListener(e -> {
            List<String> seleccionadas = listaCategoriasDisponibles.getSelectedValuesList();
            for (String cat : seleccionadas) {
                if (!modeloSeleccionadas.contains(cat)) {
                    modeloSeleccionadas.addElement(cat);
                }
            }
        });
        GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
        gbc_btnAgregar.gridx = 1;
        gbc_btnAgregar.gridy = 4;
        gbc_btnAgregar.anchor = GridBagConstraints.CENTER;
        panel.add(btnAgregar, gbc_btnAgregar);

        // --- Panel de botones ---
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> guardarEvento());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> setVisible(false));
        botones.add(btnAceptar);
        botones.add(btnCancelar);

        getContentPane().add(botones, BorderLayout.SOUTH);
    }

    private void guardarEvento() {
        String nombre = tfNombre.getText();
        String descripcion = tfDescripcion.getText();
        String sigla = tfSigla.getText();

        List<String> categorias = new ArrayList<>();
        for (int i = 0; i < modeloSeleccionadas.size(); i++) {
            categorias.add(modeloSeleccionadas.get(i));
        }

        // Llamada al controlador
        System.out.println("Evento guardado: " + nombre + " - " + descripcion + " - " + sigla);
        System.out.println("Categorias: " + categorias);
        JOptionPane.showMessageDialog(this, "Evento guardado correctamente");
        setVisible(false);
    }
}
