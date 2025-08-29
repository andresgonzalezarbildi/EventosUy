package presentacion.evento;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import logica.manejadores.ManejadorEvento;
import logica.clases.Categoria;
import logica.clases.Evento;
import logica.interfaces.IControladorEvento;

public class AltaEvento extends JInternalFrame {

    private IControladorEvento controlEvento;
    private JTextField tfNombre;
    private JTextArea tfDescripcion;
    private JTextField tfSigla;
    private DefaultListModel<String> modeloDisponibles;
    private DefaultListModel<String> modeloSeleccionadas;
    private JList<String> listaCategoriasDisponibles;
    private JList<String> listaCategoriasSeleccionadas;

    public AltaEvento(IControladorEvento controlEvento) {
       
    	// super(title, resizable, closable, maximizable, iconifiable); SON LAS BANDERAS RESPECTIVAS
    		super("Alta de Evento", false, true, true, true); 
   

        this.controlEvento = controlEvento;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5); // mismo margen en todos lados
        panel.add(new JLabel("Nombre:"), cloneGbc(gbc));
        	
        tfNombre = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfNombre, cloneGbc(gbc));

       
        // --- Sigla ---
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Sigla:"), cloneGbc(gbc));

        tfSigla = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfSigla, cloneGbc(gbc));
        
        
     // Descripcion
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1; gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(new JLabel("Descripción:"), cloneGbc(gbc));

        // Campo multilínea
        tfDescripcion = new JTextArea(4, 20);
        tfDescripcion.setLineWrap(true);
        tfDescripcion.setWrapStyleWord(true);

        JScrollPane scrollDescripcion = new JScrollPane(tfDescripcion);
        scrollDescripcion.setPreferredSize(new Dimension(300, 80));

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 1;              // <--- acá el cambio
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDescripcion, cloneGbc(gbc));


        




        // --- Categorías labels ---
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("Categorías Disponibles:"), cloneGbc(gbc));

        gbc.gridx = 2; gbc.gridy = 3;
        panel.add(new JLabel("Categorías Seleccionadas:"), cloneGbc(gbc));

        // --- Listas ---
        // Modelo y lista de disponibles
        DefaultListModel<String> modeloDisponibles = new DefaultListModel<>();
        ManejadorEvento manejador = ManejadorEvento.getInstance();
        for (Categoria c : manejador.getCategorias()) {
        		String cname  = c.getNombre();
            modeloDisponibles.addElement(cname);
        }
        listaCategoriasDisponibles = new JList<String>(modeloDisponibles);
        listaCategoriasDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollDisponibles = new JScrollPane(listaCategoriasDisponibles);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 1; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDisponibles, gbc);

        // Modelo y lista de seleccionadas
        modeloSeleccionadas = new DefaultListModel<>();   // 👈 ahora se asigna al atributo
        listaCategoriasSeleccionadas = new JList<String>(modeloSeleccionadas);
        listaCategoriasSeleccionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollSeleccionadas = new JScrollPane(listaCategoriasSeleccionadas);

        gbc.gridx = 2; gbc.gridy = 4;
        panel.add(scrollSeleccionadas, cloneGbc(gbc));

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
        panel.add(btnAgregar, cloneGbc(gbc));

        // --- Panel inferior de botones ---
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> guardarEvento());
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> setVisible(false));
        botones.add(btnAceptar);
        botones.add(btnCancelar);
        
     // --- Botón para quitar categorías ---
        JButton btnQuitar = new JButton("<<");
        btnQuitar.addActionListener(e -> {
            for (String cat : listaCategoriasSeleccionadas.getSelectedValuesList()) {
                if (!modeloDisponibles.contains(cat)) {
                    modeloDisponibles.addElement(cat);
                }
                modeloSeleccionadas.removeElement(cat);
            }
        });
        gbc.insets = new Insets(-80, 0, 0, 0); // mueve el botón hacia arriba
        
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnQuitar, cloneGbc(gbc));

        getContentPane().add(botones, BorderLayout.SOUTH);
    }

    public void limpiarFormulario() {
        tfNombre.setText("");
        tfDescripcion.setText("");
        tfSigla.setText("");

        // Limpiar listas
        modeloSeleccionadas.clear();

        // También podés deseleccionar elementos seleccionados por si quedaron seleccionados
        listaCategoriasDisponibles.clearSelection();
        listaCategoriasSeleccionadas.clearSelection();

        // Si querés dejar la ventana en su estado inicial visualmente
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    
    private void guardarEvento() {
        String nombre = tfNombre.getText();
        String descripcion = tfDescripcion.getText();
        String sigla = tfSigla.getText();

        // Convertir las categorías seleccionadas en lista (pero sin crear objetos nuevos)
        List<String> categoriasEvento = new ArrayList<>();
        for (int i = 0; i < modeloSeleccionadas.size(); i++) {
            categoriasEvento.add(modeloSeleccionadas.get(i));  // 👈 ya es un Categoria
        }

        // Llamada al controlador
        controlEvento.altaEvento(nombre, descripcion, sigla,categoriasEvento);

        // Mensaje de éxito
        JOptionPane.showMessageDialog(this, "Evento guardado correctamente");

        // Cerrar ventana
        setVisible(false); 
    }


private GridBagConstraints cloneGbc(GridBagConstraints gbc) {
    GridBagConstraints copy = (GridBagConstraints) gbc.clone();
    return copy;
}}