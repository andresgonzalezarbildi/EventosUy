package presentacion.evento;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.datatypes.DataEvento;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;


public class AltaEdicionEvento extends JInternalFrame {
	private JComboBox<String> cbListaEventos;
	private JComboBox<String> cbOrganizadores;
	private IControladorEvento controlEvento;
	private IControladorUsuario IUS;
    private JTextField textFieldNombre;
    private JTextField textFieldSigla;
    private JTextField Ciudad;
    private JTextField textFieldPais;
    private JTextField textFieldFechaI;
    private JTextField textFieldFechaF;
    private JTextField textFieldFecha;

	
	public AltaEdicionEvento(IControladorEvento controlEvento,IControladorUsuario IUS) {
		
		super("Alta Edicion Evento", false, true, true, true); 
		this.IUS = IUS;
		this.controlEvento = controlEvento;
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 400);
        getContentPane().setLayout(new BorderLayout());
		
		setTitle("Alta Edicion Evento");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblListaEventos = new JLabel("Evento:");
		GridBagConstraints gbc_lblListaEventos = new GridBagConstraints();
		gbc_lblListaEventos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaEventos.anchor = GridBagConstraints.EAST;
		gbc_lblListaEventos.gridx = 1;
		gbc_lblListaEventos.gridy = 0;
		getContentPane().add(lblListaEventos, gbc_lblListaEventos);
		
		cbListaEventos = new JComboBox<>();
		GridBagConstraints gbc_cbListaEventos = new GridBagConstraints();
		gbc_cbListaEventos.insets = new Insets(0, 0, 5, 0);
		gbc_cbListaEventos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbListaEventos.gridx = 2;
		gbc_cbListaEventos.gridy = 0;
		getContentPane().add(cbListaEventos, gbc_cbListaEventos);
		
		JLabel lblOganizadores = new JLabel("Organizador:");
		GridBagConstraints gbc_lblOganizadores = new GridBagConstraints();
		gbc_lblOganizadores.anchor = GridBagConstraints.EAST;
		gbc_lblOganizadores.insets = new Insets(0, 0, 5, 5);
		gbc_lblOganizadores.gridx = 1;
		gbc_lblOganizadores.gridy = 1;
		getContentPane().add(lblOganizadores, gbc_lblOganizadores);
		
		cbOrganizadores = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		getContentPane().add(cbOrganizadores, gbc_comboBox);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblSigla = new JLabel("Sigla:");
		GridBagConstraints gbc_lblSigla = new GridBagConstraints();
		gbc_lblSigla.anchor = GridBagConstraints.EAST;
		gbc_lblSigla.insets = new Insets(0, 0, 5, 5);
		gbc_lblSigla.gridx = 1;
		gbc_lblSigla.gridy = 3;
		getContentPane().add(lblSigla, gbc_lblSigla);
		
		textFieldSigla = new JTextField();
		GridBagConstraints gbc_textFieldSigla = new GridBagConstraints();
		gbc_textFieldSigla.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSigla.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSigla.gridx = 2;
		gbc_textFieldSigla.gridy = 3;
		getContentPane().add(textFieldSigla, gbc_textFieldSigla);
		textFieldSigla.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 4;
		getContentPane().add(lblCiudad, gbc_lblCiudad);
		
		Ciudad = new JTextField();
		GridBagConstraints gbc_ciudad = new GridBagConstraints();
		gbc_ciudad.insets = new Insets(0, 0, 5, 0);
		gbc_ciudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_ciudad.gridx = 2;
		gbc_ciudad.gridy = 4;
		getContentPane().add(Ciudad, gbc_ciudad);
		Ciudad.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais:");
		GridBagConstraints gbc_lblPais = new GridBagConstraints();
		gbc_lblPais.anchor = GridBagConstraints.EAST;
		gbc_lblPais.insets = new Insets(0, 0, 5, 5);
		gbc_lblPais.gridx = 1;
		gbc_lblPais.gridy = 5;
		getContentPane().add(lblPais, gbc_lblPais);
		
		textFieldPais = new JTextField();
		GridBagConstraints gbc_textFieldPais = new GridBagConstraints();
		gbc_textFieldPais.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPais.gridx = 2;
		gbc_textFieldPais.gridy = 5;
		getContentPane().add(textFieldPais, gbc_textFieldPais);
		textFieldPais.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio:");
		GridBagConstraints gbc_lblFechaInicio = new GridBagConstraints();
		gbc_lblFechaInicio.anchor = GridBagConstraints.EAST;
		gbc_lblFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInicio.gridx = 1;
		gbc_lblFechaInicio.gridy = 6;
		getContentPane().add(lblFechaInicio, gbc_lblFechaInicio);
		
		textFieldFechaI = new JTextField();
		GridBagConstraints gbc_textFieldFechaI = new GridBagConstraints();
		gbc_textFieldFechaI.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaI.gridx = 2;
		gbc_textFieldFechaI.gridy = 6;
		getContentPane().add(textFieldFechaI, gbc_textFieldFechaI);
		textFieldFechaI.setColumns(10);
		
		JLabel lblFechaFin = new JLabel("Fecha fin:");
		GridBagConstraints gbc_lblFechaFin = new GridBagConstraints();
		gbc_lblFechaFin.anchor = GridBagConstraints.EAST;
		gbc_lblFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaFin.gridx = 1;
		gbc_lblFechaFin.gridy = 7;
		getContentPane().add(lblFechaFin, gbc_lblFechaFin);
		
		textFieldFechaF = new JTextField();
		GridBagConstraints gbc_textFieldFechaF = new GridBagConstraints();
		gbc_textFieldFechaF.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaF.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaF.gridx = 2;
		gbc_textFieldFechaF.gridy = 7;
		getContentPane().add(textFieldFechaF, gbc_textFieldFechaF);
		textFieldFechaF.setColumns(10);
		
		JLabel lblFechaAlta = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 8;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);
		
		textFieldFecha = new JTextField();
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha.gridx = 2;
		gbc_textFieldFecha.gridy = 8;
		getContentPane().add(textFieldFecha, gbc_textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 9;
		getContentPane().add(panel, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton  btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 5;
		gbc_btnAceptar.gridy = 0;
		panel.add(btnAceptar, gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 0;
		panel.add(btnCancelar, gbc_btnCancelar);
	
	
		// estos for cargan los combobox de evento y organizador, con los eventos y organizadores existentes hasta el momento.
		for (DataEvento ev : controlEvento.getEventosDTO()) {
		    cbListaEventos.addItem(ev.getNombre());
		}
		for (DataUsuario org : IUS.getOrganizadores()) {
	        cbOrganizadores.addItem(org.getNickname());
	    }

	    // ---- Listeners de botones ----
	    
	    
	    btnAceptar.addActionListener(e -> cmdRegistroEdicionEventoActionPerformed());

	    btnCancelar.addActionListener(e -> dispose());
	    cargarEventos();

	}
	    
	protected void cmdRegistroEdicionEventoActionPerformed() {
	    String nombre = textFieldNombre.getText().trim();
	    String sigla = textFieldSigla.getText().trim();
	    String ciudad = Ciudad.getText().trim();
	    String pais = textFieldPais.getText().trim();
//	    String fechaInicio = textFieldFechaI.getText().trim();
//	    String fechaFin = textFieldFechaF.getText().trim();
//	    String fechaAlta = textFieldFecha.getText().trim();
	    String eventoSel = (String) cbListaEventos.getSelectedItem();
	    String organizadorSel = (String) cbOrganizadores.getSelectedItem(); 
	    LocalDate hoy = LocalDate.now();
	        try {
	            // acá llamás al método del controlador de eventos
	            controlEvento.altaEdicionEvento(
	            		eventoSel,
	                nombre,
	                sigla,
	                ciudad,
	                pais,
	                hoy,
	                hoy,
	                hoy,
	                organizadorSel
	            );

	            javax.swing.JOptionPane.showMessageDialog(this,
	                "La edición del evento se ha creado con éxito",
	                "Alta Edición de Evento", javax.swing.JOptionPane.INFORMATION_MESSAGE);

	            //TODO: limpiarFormulario();
	            setVisible(false);

	        } catch (Exception e) {
	            javax.swing.JOptionPane.showMessageDialog(this,
	                e.getMessage(),
	                "Alta Edición de Evento", javax.swing.JOptionPane.ERROR_MESSAGE);
	        }
	    
	}

	
	private void cargarEventos() {
	    cbListaEventos.removeAllItems();
	    DataEvento[] eventos = controlEvento.getEventosDTO();
	    if (eventos != null) {
	        for (DataEvento ev : eventos) {
	            if (ev != null) {
	                cbListaEventos.addItem(ev.getNombre());
	            }
	        }
	    }
	}

	private void cargarOrganizadores() {
	    cbOrganizadores.removeAllItems();
	    for (DataUsuario org : IUS.getOrganizadores()) {
	        if (org != null) {
	            cbOrganizadores.addItem(org.getNombre());
	        }
	    }
	}


	
	
	@Override
	public void setVisible(boolean aFlag) {
	    if (aFlag) {
	        cargarEventos(); // recargar siempre que se muestra
	        cargarOrganizadores();
	    }
	    super.setVisible(aFlag);
	}
}
