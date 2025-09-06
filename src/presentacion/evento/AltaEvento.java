package presentacion.evento;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import logica.clases.Categoria;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;
import presentacion.UIUtils;

@SuppressWarnings("serial")
public class AltaEvento extends JInternalFrame {

    private IControladorEvento controlEvento;
    private JTextField tfNombre;
    private JTextArea tfDescripcion;
    private JTextField tfSigla;
    private JDateChooser dcFecha;

    private DefaultListModel<String> modeloDisponibles;
    private DefaultListModel<String> modeloSeleccionadas;
    private JList<String> listaCategoriasDisponibles;
    private JList<String> listaCategoriasSeleccionadas;

    public AltaEvento(IControladorEvento controlEvento) {
        // super(title, resizable, closable, maximizable, iconifiable)
        super("Alta de Evento", false, true, true, true);
        this.controlEvento = controlEvento;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);
        getContentPane().setLayout(new BorderLayout());

        // Panel principal con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
        getContentPane().add(panel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Nombre (fila 0) ---
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Nombre:"), cloneGbc(gbc));

        tfNombre = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfNombre, cloneGbc(gbc));

        // --- Sigla (fila 1) ---
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Sigla:"), cloneGbc(gbc));

        tfSigla = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfSigla, cloneGbc(gbc));

        // --- Descripción (fila 2) ---
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Descripción:"), cloneGbc(gbc));

        tfDescripcion = new JTextArea(4, 20);
        tfDescripcion.setLineWrap(true);
        tfDescripcion.setWrapStyleWord(true);

        JScrollPane scrollDescripcion = new JScrollPane(tfDescripcion);
        scrollDescripcion.setPreferredSize(new Dimension(300, 100));

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0; gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDescripcion, cloneGbc(gbc));

        // --- Fecha (fila 3) ---
        JLabel lblFecha = new JLabel("Fecha:");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.insets = new Insets(6, 6, 6, 6);
        gbc_lblFecha.gridx = 0; gbc_lblFecha.gridy = 3;
        gbc_lblFecha.anchor = GridBagConstraints.EAST;
        panel.add(lblFecha, gbc_lblFecha);

        dcFecha = new JDateChooser();
        dcFecha.setDateFormatString("yyyy/MM/dd");
        ((JTextField) dcFecha.getDateEditor().getUiComponent()).setEditable(true);

        GridBagConstraints gbc_dcFecha = new GridBagConstraints();
        gbc_dcFecha.gridx = 1; gbc_dcFecha.gridy = 3;
        gbc_dcFecha.gridwidth = 2;
        gbc_dcFecha.weightx = 1.0;
        gbc_dcFecha.insets = new Insets(6, 6, 6, 6);
        gbc_dcFecha.fill = GridBagConstraints.HORIZONTAL;
        panel.add(dcFecha, gbc_dcFecha);

        // --- Encabezados Categorías (fila 4) ---
        gbc.gridx = 0; gbc.gridy = 4; 
        gbc.gridwidth = 1; 
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Categorías disponibles"), cloneGbc(gbc));

        gbc.gridx = 2; gbc.gridy = 4;
        panel.add(new JLabel("Categorías seleccionadas"), cloneGbc(gbc));

        // --- Listas y modelos (fila 5) ---
        modeloDisponibles = new DefaultListModel<>();
        modeloSeleccionadas = new DefaultListModel<>();

     // Pedir categorías y armar lista de Strings
        ManejadorEvento manejador = ManejadorEvento.getInstance();
        List<String> nombres = new ArrayList<>();
        for (Categoria c : manejador.getCategorias()) {
            if (c != null && c.getNombre() != null) {
                nombres.add(c.getNombre());
            }
        }

        // Ordenar primero
        Collections.sort(nombres);
        

        // Cargar ordenado al modelo
        modeloDisponibles = new DefaultListModel<>();
        for (String nombre : nombres) {
            modeloDisponibles.addElement(nombre);
        }

        // Ahora sí crear la lista
        listaCategoriasDisponibles = new JList<>(modeloDisponibles);

//        UIUtils.ordenarJList(modeloDisponibles);
//        listaCategoriasDisponibles.updateUI(); 
        listaCategoriasDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollDisponibles = new JScrollPane(listaCategoriasDisponibles);

        gbc.gridx = 0; gbc.gridy = 5; 
        gbc.gridwidth = 1;
        gbc.weightx = 0.5; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDisponibles, cloneGbc(gbc));

        listaCategoriasSeleccionadas = new JList<>(modeloSeleccionadas);
        listaCategoriasSeleccionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollSeleccionadas = new JScrollPane(listaCategoriasSeleccionadas);

        gbc.gridx = 2; gbc.gridy = 5; 
        gbc.gridwidth = 1;
        gbc.weightx = 0.5; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollSeleccionadas, cloneGbc(gbc));

        // Botón >> (mismo renglón 5, columna 1)
        JButton btnAgregar = new JButton(">>");
        btnAgregar.addActionListener(e -> {
            for (String cat : listaCategoriasDisponibles.getSelectedValuesList()) {
                if (!modeloSeleccionadas.contains(cat)) {
                    modeloSeleccionadas.addElement(cat);
                }
                // opcional: sacarla de disponibles
                modeloDisponibles.removeElement(cat);
            }
            UIUtils.ordenarJList(modeloDisponibles);
            UIUtils.ordenarJList(modeloSeleccionadas);
        });
        gbc.gridx = 1; gbc.gridy = 5; 
        gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(btnAgregar, cloneGbc(gbc));

        // Botón << (fila 6, columna 1)
        JButton btnQuitar = new JButton("<<");
        btnQuitar.addActionListener(e -> {
            for (String cat : listaCategoriasSeleccionadas.getSelectedValuesList()) {
                if (!modeloDisponibles.contains(cat)) {
                    modeloDisponibles.addElement(cat);
                }
                modeloSeleccionadas.removeElement(cat);
            }
            UIUtils.ordenarJList(modeloSeleccionadas);
            UIUtils.ordenarJList(modeloDisponibles);
        });
        gbc.gridx = 1; gbc.gridy = 6; 
        gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(btnQuitar, cloneGbc(gbc));

        // Panel inferior de botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> guardarEvento());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> setVisible(false));
        botones.add(btnAceptar);
        botones.add(btnCancelar);
        getContentPane().add(botones, BorderLayout.SOUTH);
    }

    public void limpiarFormulario() {
        tfNombre.setText("");
        tfDescripcion.setText("");
        tfSigla.setText("");
        dcFecha.setDate(null);

        modeloSeleccionadas.clear();
        modeloDisponibles.clear();

        // Volver a cargar categorías en una lista temporal
        List<String> nombres = new ArrayList<>();
        for (Categoria c : ManejadorEvento.getInstance().getCategorias()) {
            if (c != null && c.getNombre() != null && !modeloSeleccionadas.contains(c.getNombre())) {
                nombres.add(c.getNombre());
            }
        }

        // Ordenar antes de cargar al modelo
        Collections.sort(nombres);

        for (String nombre : nombres) {
            modeloDisponibles.addElement(nombre);
        }

        // Limpiar selección de las listas
        listaCategoriasDisponibles.clearSelection();
        listaCategoriasSeleccionadas.clearSelection();

        getContentPane().revalidate();
        getContentPane().repaint();
    }


    private void guardarEvento() {
        // 1) Validaciones de UI
        if (!checkFormulario()) return;

        // 2) Recolección de datos
        String nombre = tfNombre.getText().trim();
        String descripcion = tfDescripcion.getText().trim();
        String sigla = tfSigla.getText().trim();

        Date utilDate = dcFecha.getDate();
        if (utilDate == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar la fecha del registro.",
                    "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate fecha = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<String> categoriasEvento = new ArrayList<>();
        for (int i = 0; i < modeloSeleccionadas.size(); i++) {
            categoriasEvento.add(modeloSeleccionadas.get(i));
        }

        // 3) Llamada al controlador
        try {
            controlEvento.altaEvento(nombre, descripcion, sigla, categoriasEvento, fecha);
            JOptionPane.showMessageDialog(this, "Evento guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            setVisible(false);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkFormulario() {
        String nombre = tfNombre.getText() != null ? tfNombre.getText().trim() : "";
        String sigla  = tfSigla.getText()  != null ? tfSigla.getText().trim()  : "";
        String desc   = tfDescripcion.getText() != null ? tfDescripcion.getText().trim() : "";

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            tfNombre.requestFocusInWindow();
            return false;
        }
        if (sigla.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La sigla es obligatoria.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            tfSigla.requestFocusInWindow();
            return false;
        }
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción es obligatoria.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            tfDescripcion.requestFocusInWindow();
            return false;
        }
        if (modeloSeleccionadas == null || modeloSeleccionadas.getSize() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una categoría.", "Faltan datos", JOptionPane.WARNING_MESSAGE);
            listaCategoriasDisponibles.requestFocusInWindow();
            return false;
        }
        return true;
    }

    private GridBagConstraints cloneGbc(GridBagConstraints gbc) {
        return (GridBagConstraints) gbc.clone();
    }
}
