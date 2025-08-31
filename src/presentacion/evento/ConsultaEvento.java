package presentacion.evento;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import excepciones.EventoNoExisteException;

import java.awt.*;

import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.clases.Evento;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

import java.util.List;

// es para usar callbacks, lo uso para traer consulta edicion
import java.util.function.BiConsumer;


public class ConsultaEvento extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JList<DataEvento> listaEventos;
    private DefaultListModel<DataEvento> listaModel;
    private DataEvento[] eventos;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JList<String> listCategorias; 
    private JComboBox<String> cbEdiciones; 
    private IControladorEvento IEV;
    private BiConsumer<DataEvento, DataEdicion> openEdicionCallback;


    private JTextArea textArea;
    public ConsultaEvento(IControladorEvento ctrlEv, BiConsumer<DataEvento, DataEdicion> openEdicionCallback) {
    	
		IEV = ctrlEv;
		this.openEdicionCallback = openEdicionCallback;
        setSize(600, 400); //establece el tamaño incial de la ventana
        setResizable(false); //permite al user redimenzioanr la ventana manualment
        setIconifiable(true); // permite minimzar la ventana
        setMaximizable(true); // permite maximzar la vent
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // oculta la ventana, no la destruye VER BIEN ESTO
        setClosable(true); // aparece la x para cerrar
        
        
        // Panel izquierdo (buscador + lista)
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBounds(0, 0, 200, 370);
        panelIzq.setPreferredSize(new Dimension(200, 0));
        JLabel lblLista = new JLabel("Eventos", SwingConstants.CENTER);
        panelIzq.add(lblLista, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        getContentPane().setLayout(null);
        
        listaEventos = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaEventos);
        panelIzq.add(scrollLista, BorderLayout.CENTER);

      

        getContentPane().add(panelIzq);
        
        JPanel panel = new JPanel();
        panel.setBounds(198, 0, 386, 370);
        getContentPane().add(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0,0,0,0,0,0,0,0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0,1.0,0.0,0.0,1.0,0.0,0.0,Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JLabel lblNombre = new JLabel("Nombre del evento:");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.anchor = GridBagConstraints.WEST;
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 0;
        panel.add(lblNombre, gbc_lblNombre);
        
        textField = new JTextField();
        textField.setEditable(false);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 0;
        panel.add(textField, gbc_textField);
        textField.setColumns(10);
        
        JLabel lblDescripcion = new JLabel("Descripcion del evento:");
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 1;
        gbc_lblDescripcion.gridy = 1;
        panel.add(lblDescripcion, gbc_lblDescripcion);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.insets = new Insets(0, 0, 5, 0);
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 2;
        gbc_textArea.gridy = 1;
        panel.add(textArea, gbc_textArea);
        
        JLabel lblSigla = new JLabel("Sigla del evento:");
        GridBagConstraints gbc_lblSigla = new GridBagConstraints();
        gbc_lblSigla.anchor = GridBagConstraints.WEST;
        gbc_lblSigla.insets = new Insets(0, 0, 5, 5);
        gbc_lblSigla.gridx = 1;
        gbc_lblSigla.gridy = 2;
        panel.add(lblSigla, gbc_lblSigla);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 5, 0);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 2;
        gbc_textField_1.gridy = 2;
        panel.add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Fecha de alta:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 3;
        panel.add(lblNewLabel, gbc_lblNewLabel);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.insets = new Insets(0, 0, 5, 0);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 2;
        gbc_textField_2.gridy = 3;
        panel.add(textField_2, gbc_textField_2);
        textField_2.setColumns(10);
        
        JLabel lblCategoria = new JLabel("Categorias:");
        GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
        gbc_lblCategoria.anchor = GridBagConstraints.WEST;
        gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
        gbc_lblCategoria.gridx = 1;
        gbc_lblCategoria.gridy = 4;
        panel.add(lblCategoria, gbc_lblCategoria);
        
        listCategorias = new JList<>();
        listCategorias.setEnabled(false); // 👈 solo lectura
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.insets = new Insets(0, 0, 5, 0);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 2;
        gbc_list.gridy = 4;
        panel.add(new JScrollPane(listCategorias), gbc_list);
        
        JLabel lblEdicion = new JLabel("Consultar edicion: ");
        GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
        gbc_lblEdicion.anchor = GridBagConstraints.WEST;
        gbc_lblEdicion.insets = new Insets(0, 0, 0, 5);
        gbc_lblEdicion.gridx = 1;
        gbc_lblEdicion.gridy = 5;
        panel.add(lblEdicion, gbc_lblEdicion);
        
        cbEdiciones = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 2;
        gbc_comboBox.gridy = 5;
        panel.add(cbEdiciones, gbc_comboBox);
        JScrollPane scrollInfo = new JScrollPane();
        
        JButton btnVerEdicion = new JButton("Ver edición");
        GridBagConstraints gbc_btn = new GridBagConstraints();
        gbc_btn.gridx = 2;
        gbc_btn.gridy = 6;
        gbc_btn.insets = new Insets(6, 0, 0, 0);
        gbc_btn.anchor = GridBagConstraints.LINE_START;
        panel.add(btnVerEdicion, gbc_btn);

        btnVerEdicion.addActionListener(e -> abrirConsultaEdicionSeleccionada());

        JPanel panelInferior = new JPanel(new BorderLayout());

        // Acción al seleccionar un evento
        listaEventos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInfoEvento(listaEventos.getSelectedValue());
            }
        });
    }
       
    // Método para cargar eventos desde la lógica
    public void cargarEventos(DataEvento[] lista) {
        this.eventos = lista;
        listaModel.clear();
        for (DataEvento ev : lista) {
            listaModel.addElement(ev);
        }
    }    
       
  

    
    private void mostrarInfoEvento(DataEvento evento) {
        if (evento == null) return;

        // Mostrar cada atributo en su campo
        textField.setText(evento.getNombre());
        textArea.setText(evento.getDescripcion());
        textField_1.setText(evento.getSigla());
        textField_2.setText(evento.getFechaAlta().toString());
        DefaultListModel<String> catModel = new DefaultListModel<>();
        List<String> cats = evento.getCategorias();
        if (cats != null) {
            for (String cat : cats) {
                catModel.addElement(cat);
            }
        }
        listCategorias.setModel(catModel);
        cbEdiciones.removeAllItems();
        try {
            DataEdicion[] ediciones = IEV.listarEdiciones(evento.getNombre());
            if (ediciones != null && ediciones.length > 0) {
                cbEdiciones.addItem("Seleccione...");
                for (DataEdicion ed : ediciones) {
                    cbEdiciones.addItem(ed.getNombre());
                }
                cbEdiciones.setSelectedIndex(0);
            } else {
                cbEdiciones.addItem("No tiene ediciones");
            }
        } catch (Exception ex) {
            cbEdiciones.addItem("Error al cargar ediciones");
        }
    }

        
    
    
    
    
    public DataEvento getEventoSeleccionado() {
        return listaEventos.getSelectedValue();
    }
    
    private void abrirConsultaEdicionSeleccionada() {
        DataEvento eventoSel = getEventoSeleccionado();
        if (eventoSel == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná un evento.", "Falta selección", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Object sel = cbEdiciones.getSelectedItem();
        if (sel == null || "Seleccione...".equals(sel) || String.valueOf(sel).startsWith("No tiene")) {
            JOptionPane.showMessageDialog(this, "Seleccioná una edición válida.", "Falta selección", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nombreEdicion = String.valueOf(sel);

        // Resolver el DataEdicion por nombre
        DataEdicion target = null;
        try {
            DataEdicion[] eds = IEV.listarEdiciones(eventoSel.getNombre());
            if (eds != null) {
                for (DataEdicion de : eds) {
                    if (de != null && nombreEdicion.equals(de.getNombre())) {
                        target = de;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error obteniendo la edición: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (target == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la edición seleccionada.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ¡Listo! Llamamos al callback que abre ConsultaEdicionEvento con contexto.
        if (openEdicionCallback != null) {
            openEdicionCallback.accept(eventoSel, target);
        }
    }
}
