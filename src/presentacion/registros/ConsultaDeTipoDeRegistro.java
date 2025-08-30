package presentacion.registros;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logica.interfaces.IControladorEvento;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;

public class ConsultaDeTipoDeRegistro extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cbListaEvento;
	private JComboBox<String> cbListaEdicion;
	private JComboBox<String> cbListaTipoRegistro;
	private JTextArea infoTipoRegistro;

	private IControladorEvento IEV;

	
	public ConsultaDeTipoDeRegistro(IControladorEvento IEV) {
			this.IEV=IEV;

		    setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		    setResizable(true);
		    setTitle("Consulta de Tipo de Registro");
		    setBounds(100, 100, 600, 400);
		    getContentPane().setLayout(new BorderLayout(0, 0));

		    // Panel izquierdo
		    JPanel panelIzq = new JPanel(new GridBagLayout());
		    panelIzq.setBorder(new EmptyBorder(12, 12, 12, 12));
		    getContentPane().add(panelIzq, BorderLayout.WEST);

		    GridBagConstraints gbc = new GridBagConstraints();
		    gbc.insets = new Insets(6, 6, 6, 6);
		    gbc.fill = GridBagConstraints.HORIZONTAL;
		    gbc.gridx = 0;

		    // Evento
		    gbc.gridy = 0;
		    panelIzq.add(new JLabel("Evento:"), gbc);
		    cbListaEvento = new JComboBox<>();
		    gbc.gridy = 1;
		    panelIzq.add(cbListaEvento, gbc);

		    // Edición
		    gbc.gridy = 2;
		    panelIzq.add(new JLabel("Edición:"), gbc);
		    cbListaEdicion = new JComboBox<>();
		    gbc.gridy = 3;
		    panelIzq.add(cbListaEdicion, gbc);

		    // Tipo de registro
		    gbc.gridy = 4;
		    panelIzq.add(new JLabel("Tipo de Registro:"), gbc);
		    cbListaTipoRegistro = new JComboBox<>();
		    gbc.gridy = 5;
		    panelIzq.add(cbListaTipoRegistro, gbc);

		    // Panel derecho: información
		    infoTipoRegistro = new JTextArea();
		    infoTipoRegistro.setEditable(false);
		    infoTipoRegistro.setLineWrap(true);
		    infoTipoRegistro.setWrapStyleWord(true);
		    getContentPane().add(new JScrollPane(infoTipoRegistro), BorderLayout.CENTER);

		    // Listeners en cascada
		    cbListaEvento.addActionListener(e -> cargarEdiciones());
		    cbListaEdicion.addActionListener(e -> cargarTiposRegistro());
		    cbListaTipoRegistro.addActionListener(e -> mostrarInfoTipoRegistro());

		    // Al abrir, cargar eventos
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



