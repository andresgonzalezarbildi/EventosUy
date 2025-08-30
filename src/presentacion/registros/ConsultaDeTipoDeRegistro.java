package presentacion.registros;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import javax.swing.SwingConstants;

public class ConsultaDeTipoDeRegistro extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbListaEvento;
	private JComboBox<String> cbListaEdicion;
	private JComboBox<String> cbListaTipoRegistro;
	private JTextArea infoTipoRegistro;

	private IControladorEvento IEV;
	private JLabel lblInformacion;

	
	public ConsultaDeTipoDeRegistro(IControladorEvento IEV) {
			super("Consulta De Tipo De Registro", false, true, true, true);
			this.IEV=IEV;

		    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		    setResizable(true);
		    setTitle("Consulta de Tipo de Registro");
		    setBounds(100, 100, 600, 400);
		    getContentPane().setLayout(new BorderLayout(0, 0));


		    JPanel panelIzq = new JPanel(new GridBagLayout());
		    panelIzq.setBorder(new EmptyBorder(12, 12, 12, 12));
		    getContentPane().add(panelIzq, BorderLayout.WEST);

		    GridBagConstraints gbcEventoLabel = new GridBagConstraints();
		    gbcEventoLabel.insets = new Insets(6, 6, 6, 6);
		    gbcEventoLabel.fill = GridBagConstraints.HORIZONTAL;
		    gbcEventoLabel.gridx = 0;
		    gbcEventoLabel.gridy = 0;
		    panelIzq.add(new JLabel("Evento:"), gbcEventoLabel);

		    GridBagConstraints gbcEventoCombo = new GridBagConstraints();
		    gbcEventoCombo.insets = new Insets(6, 6, 6, 6);
		    gbcEventoCombo.fill = GridBagConstraints.HORIZONTAL;
		    gbcEventoCombo.gridx = 0;
		    gbcEventoCombo.gridy = 1;
		    cbListaEvento = new JComboBox<>();
		    panelIzq.add(cbListaEvento, gbcEventoCombo);

		    GridBagConstraints gbcEdicionLabel = new GridBagConstraints();
		    gbcEdicionLabel.insets = new Insets(6, 6, 6, 6);
		    gbcEdicionLabel.fill = GridBagConstraints.HORIZONTAL;
		    gbcEdicionLabel.gridx = 0;
		    gbcEdicionLabel.gridy = 2;
		    panelIzq.add(new JLabel("Edición:"), gbcEdicionLabel);

		    GridBagConstraints gbcEdicionCombo = new GridBagConstraints();
		    gbcEdicionCombo.insets = new Insets(6, 6, 6, 6);
		    gbcEdicionCombo.fill = GridBagConstraints.HORIZONTAL;
		    gbcEdicionCombo.gridx = 0;
		    gbcEdicionCombo.gridy = 3;
		    cbListaEdicion = new JComboBox<>();
		    panelIzq.add(cbListaEdicion, gbcEdicionCombo);

		    GridBagConstraints gbcTipoLabel = new GridBagConstraints();
		    gbcTipoLabel.insets = new Insets(6, 6, 6, 6);
		    gbcTipoLabel.fill = GridBagConstraints.HORIZONTAL;
		    gbcTipoLabel.gridx = 0;
		    gbcTipoLabel.gridy = 4;
		    panelIzq.add(new JLabel("Tipo de Registro:"), gbcTipoLabel);

		    GridBagConstraints gbcTipoCombo = new GridBagConstraints();
		    gbcTipoCombo.insets = new Insets(6, 6, 6, 6);
		    gbcTipoCombo.fill = GridBagConstraints.HORIZONTAL;
		    gbcTipoCombo.gridx = 0;
		    gbcTipoCombo.gridy = 5;
		    cbListaTipoRegistro = new JComboBox<>();
		    panelIzq.add(cbListaTipoRegistro, gbcTipoCombo);

		    infoTipoRegistro = new JTextArea();
		    infoTipoRegistro.setEditable(false);
		    infoTipoRegistro.setLineWrap(true);
		    infoTipoRegistro.setWrapStyleWord(true);
		    JScrollPane scrollPane = new JScrollPane(infoTipoRegistro);
		    getContentPane().add(scrollPane, BorderLayout.CENTER);
		    
		    lblInformacion = new JLabel("Inofrmacion del tipo de refistro:");
		    lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		    scrollPane.setColumnHeaderView(lblInformacion);

		    cbListaEvento.addActionListener(e -> cargarEdiciones());
		    cbListaEdicion.addActionListener(e -> cargarTiposRegistro());
		    cbListaTipoRegistro.addActionListener(e -> mostrarInfoTipoRegistro());

		    cargarEventos();
		}
	
	

	private void cargarEventos() {
	    cbListaEvento.removeAllItems();
	    DataEvento[] eventos = IEV.getEventosDTO();
	    if (eventos != null && eventos.length > 0) {
	        for (DataEvento ev : eventos) {
	            if (ev != null) cbListaEvento.addItem(ev.getNombre());
	        }
	    } else {
	        cbListaEvento.addItem("No hay eventos");
	    }
	}

	private void cargarEdiciones() {
	    cbListaEdicion.removeAllItems();
	    String eventoSel = (String) cbListaEvento.getSelectedItem();
	    if (eventoSel != null && !eventoSel.equals("No hay eventos")) {
	        try {
	            DataEdicion[] ediciones = IEV.listarEdiciones(eventoSel);
	            if (ediciones.length == 0) {
	                cbListaEdicion.addItem("No tiene ediciones");
	            } else {
	                for (DataEdicion ed : ediciones) {
	                    cbListaEdicion.addItem(ed.getNombre());
	                }
	            }
	        } catch (Exception e) {
	            cbListaEdicion.addItem("Error al cargar ediciones");
	        }
	    }
	}

	private void cargarTiposRegistro() {
	    cbListaTipoRegistro.removeAllItems();
	    String eventoSel = (String) cbListaEvento.getSelectedItem();
	    String edicionSel = (String) cbListaEdicion.getSelectedItem();
	    if (eventoSel != null && edicionSel != null &&
	        !edicionSel.equals("No tiene ediciones")) {
	        try {
	            DataTipoRegistro[] tipos = IEV.listarTiposRegistro(eventoSel, edicionSel);
	            if (tipos.length == 0) {
	                cbListaTipoRegistro.addItem("No tiene tipos de registro");
	            } else {
	                for (DataTipoRegistro tr : tipos) {
	                    cbListaTipoRegistro.addItem(tr.getNombre());
	                }
	            }
	        } catch (Exception e) {
	            cbListaTipoRegistro.addItem("Error al cargar tipos");
	        }
	    }
	}
private void mostrarInfoTipoRegistro() {
    String eventoSel = (String) cbListaEvento.getSelectedItem();
    String edicionSel = (String) cbListaEdicion.getSelectedItem();
    String tipoSel = (String) cbListaTipoRegistro.getSelectedItem();
    if (eventoSel != null && edicionSel != null && tipoSel != null) {
        try {
            DataTipoRegistro tr = IEV.getTipoRegistro(eventoSel, edicionSel, tipoSel);
            infoTipoRegistro.setText(
                "Nombre: " + tr.getNombre() + "\n" +
                "Descripción: " + tr.getDescripcion() + "\n" +
                "Costo: " + tr.getCosto() + "\n" +
                "Cupo: " + tr.getCupo()
            );
        } catch (Exception ex) {
            infoTipoRegistro.setText("Error al obtener el tipo de registro");
        }
    }
}}



