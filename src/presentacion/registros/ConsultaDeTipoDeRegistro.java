package presentacion.registros;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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

public class ConsultaDeTipoDeRegistro extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
			try {
				ConsultaDeTipoDeRegistro dialog = new ConsultaDeTipoDeRegistro();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	}

	public ConsultaDeTipoDeRegistro() {
		
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Consulta De Tipo De Registro");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
		
		JPanel panel_izquierda = new JPanel();
		panel_izquierda.setBorder(new EmptyBorder(12, 12, 12, 12));
		getContentPane().add(panel_izquierda, BorderLayout.WEST);
		GridBagLayout gbl_panel_izquierda = new GridBagLayout();
		gbl_panel_izquierda.columnWidths = new int[] {0, 0, 0};
		gbl_panel_izquierda.rowHeights = new int[] {0, 0, 0, 0 ,0, 0 ,0};
		gbl_panel_izquierda.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_izquierda.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_izquierda.setLayout(gbl_panel_izquierda);
		
		
		//EVENTO : TAG y COMBOBOX
		JLabel lblEventos = new JLabel("Evento: ");
		lblEventos.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblEventos = new GridBagConstraints();
		gbc_lblEventos.insets = new Insets(6, 12, 6, 6);
		gbc_lblEventos.anchor = GridBagConstraints.WEST;
		gbc_lblEventos.gridx = 0;
		gbc_lblEventos.gridy = 0;
		panel_izquierda.add(lblEventos, gbc_lblEventos);
		
		JComboBox<String> cbListaEvento = new JComboBox<String>();
		GridBagConstraints gbc_cbListaEvento = new GridBagConstraints();
		gbc_cbListaEvento.weightx = 1.0;
		gbc_cbListaEvento.insets = new Insets(6, 6, 6, 12);
		gbc_cbListaEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbListaEvento.gridx = 0;
		gbc_cbListaEvento.gridy = 1;
		cbListaEvento.setModel(new DefaultComboBoxModel<>(new String[]{ //pre-carga para mostrar algo en el combobox
			    "Seleccione...", "Evento A", "Evento B", "Evento C"
			}));
		cbListaEvento.setSelectedIndex(0); // seleccionas el que queres mostrar por defecto
		panel_izquierda.add(cbListaEvento, gbc_cbListaEvento);
				
				
				
		//EDICION: TAG y COMBOBOX
		JLabel lblEdiciones = new JLabel("Edición: ");
		GridBagConstraints gbc_lblEdiciones = new GridBagConstraints();
		gbc_lblEdiciones.insets = new Insets(6, 12, 6, 6);
		gbc_lblEdiciones.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblEdiciones.gridx = 0;
		gbc_lblEdiciones.gridy = 2;
		panel_izquierda.add(lblEdiciones, gbc_lblEdiciones);
						
		JComboBox<String> cbListaEdicion = new JComboBox<String>();
		GridBagConstraints gbc_cbListaEdicion = new GridBagConstraints();
		gbc_cbListaEdicion.weightx = 1.0;
		gbc_cbListaEdicion.insets = new Insets(6, 6, 6, 12);
		gbc_cbListaEdicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbListaEdicion.gridx = 0;
		gbc_cbListaEdicion.gridy = 3;
		cbListaEdicion.setModel(new DefaultComboBoxModel<>(new String[]{ //pre-carga para mostrar algo en el combobox
		    "Seleccione...", "Edicion A", "Edicion B", "Edicion C"
		}));
		panel_izquierda.add(cbListaEdicion, gbc_cbListaEdicion);
				
				
				
		//TipoRegistro: TAG y COMBOBOX
		JLabel lblTipoRegistro = new JLabel("Tipo de Registro: ");
		GridBagConstraints gbc_lblTipoRegistro = new GridBagConstraints();
		gbc_lblTipoRegistro.insets = new Insets(6, 12, 6, 6);
		gbc_lblTipoRegistro.anchor = GridBagConstraints.WEST;
		gbc_lblTipoRegistro.gridx = 0;
		gbc_lblTipoRegistro.gridy = 4;
		panel_izquierda.add(lblTipoRegistro, gbc_lblTipoRegistro);
						
		JComboBox<String> cbListaTipoRegistro = new JComboBox<String>();
		GridBagConstraints gbc_cbListaTipoRegistro= new GridBagConstraints();
		gbc_cbListaTipoRegistro.weightx = 1.0;
		gbc_cbListaTipoRegistro.insets = new Insets(6, 6, 6, 12);
		gbc_cbListaTipoRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbListaTipoRegistro.gridx = 0;
		gbc_cbListaTipoRegistro.gridy = 5;
		cbListaTipoRegistro.setModel(new DefaultComboBoxModel<>(new String[]{ //pre-carga para mostrar algo en el combobox
		    "Seleccione...", "TipoRegistro A", "TipoRegistro B", "TipoRegistro C"
		}));
		panel_izquierda.add(cbListaTipoRegistro, gbc_cbListaTipoRegistro);
						
						
						
		//Panel Información				
		JTextArea infoTipoRegistro = new JTextArea();
		infoTipoRegistro.setText("Nombre: " + "\n"
				+ "Descripción:  " + "\n"
				+ "Costo: " + "\n"
				+ "Cupo: " );
		infoTipoRegistro.setEditable(false);
		infoTipoRegistro.setLineWrap(true);
		infoTipoRegistro.setWrapStyleWord(true);
		infoTipoRegistro.setRows(5);
		infoTipoRegistro.setColumns(25);
		infoTipoRegistro.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JScrollPane scroll = new JScrollPane(infoTipoRegistro);
		getContentPane().add(scroll, BorderLayout.CENTER);

	}
	}

	

}
