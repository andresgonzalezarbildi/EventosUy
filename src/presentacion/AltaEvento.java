package presentacion;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import logica.IControladorEvento;
import logica.Evento;

public class AltaEvento extends JInternalFrame {

    private IControladorEvento controlEvento;
    private JTextField tfNombre;
    private JTextField tfDescripcion;
    private JTextField tfSigla;
    private DefaultListModel<String> modeloDisponibles;
    private DefaultListModel<String> modeloSeleccionadas;
    private JList<String> listaCategoriasDisponibles;
    private JList<String> listaCategoriasSeleccionadas;

    public AltaEvento(IControladorEvento controlEvento) {
        super("Alta de Evento", true, true, true, true); 

        this.controlEvento = controlEvento;

        setBounds(100, 100, 650, 400);
        getContentPane().setLayout(new BorderLayout());

        // Panel principal con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Nombre ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Nombre:"), gbc);

        tfNombre = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panel.add(tfNombre, gbc);

        // --- Descripción ---
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel("Descripción:"), gbc);

        tfDescripcion = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panel.add(tfDescripcion, gbc);

        // --- Sigla ---
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; gbc.weightx = 0;
        panel.add(new JLabel("Sigla:"), gbc);

        tfSigla = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2; gbc.weightx = 1.0;
        panel.add(tfSigla, gbc);

        // --- Categorías labels ---
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("Categorías Disponibles:"), gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        panel.add(new JLabel("Categorías Seleccionadas:"), gbc);

        // --- Listas ---
        modeloDisponibles = new DefaultListModel<>();
        modeloDisponibles.addElement("Categoria 1");
        modeloDisponibles.addElement("Categoria 2");
        modeloDisponibles.addElement("Categoria 3");

        listaCategoriasDisponibles = new JList<>(modeloDisponibles);
        listaCategoriasDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollDisponibles = new JScrollPane(listaCategoriasDisponibles);
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDisponibles, gbc);

        modeloSeleccionadas = new DefaultListModel<>();
        listaCategoriasSeleccionadas = new JList<>(modeloSeleccionadas);
        listaCategoriasSeleccionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollSeleccionadas = new JScrollPane(listaCategoriasSeleccionadas);
        gbc.gridx = 2; gbc.gridy = 4;
        panel.add(scrollSeleccionadas, gbc);

        // --- Botón para pasar categorías ---
        JButton btnAgregar = new JButton(">>");
        btnAgregar.addActionListener(e -> {
            for (String cat : listaCategoriasDisponibles.getSelectedValuesList()) {
                if (!modeloSeleccionadas.contains(cat)) {
                    modeloSeleccionadas.addElement(cat);
                }
            }
        });
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 0; gbc.weighty = 0; 
        gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAgregar, gbc);

        // --- Panel inferior de botones ---
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

        controlEvento.altaEvento(nombre, descripcion, sigla, null);
        JOptionPane.showMessageDialog(this, "Evento guardado correctamente");
        setVisible(false); // lo oculta, no destruye el frame
    }
}