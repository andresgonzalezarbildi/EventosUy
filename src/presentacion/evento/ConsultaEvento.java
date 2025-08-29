package presentacion.evento;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import logica.datatypes.DataEvento;
import logica.clases.Evento;
import logica.interfaces.IControladorEvento;
import java.util.List;

public class ConsultaEvento extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JList<DataEvento> listaEventos;
    private DefaultListModel<DataEvento> listaModel;
    private DataEvento[] eventos;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JList<String> listCategorias;  // 👈 atributo de clase

    private JTextArea textArea;
    public ConsultaEvento(IControladorEvento IEV) {
    	
    	
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
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
        
        JList list = new JList();
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.insets = new Insets(0, 0, 5, 0);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 2;
        gbc_list.gridy = 4;
        panel.add(list, gbc_list);
        
        JLabel lblEdicion = new JLabel("Consultar edicion: ");
        GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
        gbc_lblEdicion.anchor = GridBagConstraints.WEST;
        gbc_lblEdicion.insets = new Insets(0, 0, 0, 5);
        gbc_lblEdicion.gridx = 1;
        gbc_lblEdicion.gridy = 5;
        panel.add(lblEdicion, gbc_lblEdicion);
        
        JComboBox comboBox = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 2;
        gbc_comboBox.gridy = 5;
        panel.add(comboBox, gbc_comboBox);
        JScrollPane scrollInfo = new JScrollPane();

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
       
  
//    private void mostrarInfoEvento(DataEvento evento) {
//        if (evento == null) return;
//        StringBuilder sb = new StringBuilder();
//        sb.append("Nombre: ").append(evento.getNombre()).append("\n");
//        sb.append("Sigla: ").append(evento.getSigla()).append("\n");
//        sb.append("Descripción: ").append(evento.getDescripcion()).append("\n");
//        sb.append("Fecha Alta: ").append(evento.getFechaAlta()).append("\n");
//
//        
//        List<String> cats = evento.getCategorias();
//        if (cats != null && !cats.isEmpty()) {
//            sb.append("Categorías: ").append(String.join(", ", cats)).append("\n");
//        } else {
//            sb.append("Categorías: Ninguna\n");
//        }
//    }
    
    private void mostrarInfoEvento(DataEvento evento) {
        if (evento == null) return;

        // Mostrar cada atributo en su campo
        textField.setText(evento.getNombre());
        textArea.setText(evento.getDescripcion());
        textField_1.setText(evento.getSigla());
        textField_2.setText(evento.getFechaAlta().toString());

        // Si querés mostrar categorías en el JList (en vez de solo texto plano)
        // Podés actualizar el JList de categorías aquí
        // Ejemplo: 
//        DefaultListModel<String> catModel = new DefaultListModel<>();
//        for (String cat : evento.getCategorias()) {
//             catModel.addElement(cat);
//         }
//         listEventos.setModel(catModel);
    }
    
    
    
    public DataEvento getEventoSeleccionado() {
        return listaEventos.getSelectedValue();
    }
}
