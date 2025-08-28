package presentacion;

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

public class ConsultaDeRegistro extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
			try {
				ConsultaDeRegistro dialog = new ConsultaDeRegistro();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	}

	public ConsultaDeRegistro() {
		
	
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Consulta De Registro");
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
		
		
		//USUARIO : TAG y COMBOBOX
		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.insets = new Insets(6, 12, 6, 6);
		gbc_lblUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 0;
		panel_izquierda.add(lblUsuario, gbc_lblUsuario);
		
		JComboBox<String> cbListaUsuario = new JComboBox<String>();
		GridBagConstraints gbc_cbListaUsuario = new GridBagConstraints();
		gbc_cbListaUsuario.weightx = 1.0;
		gbc_cbListaUsuario.insets = new Insets(6, 6, 6, 12);
		gbc_cbListaUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbListaUsuario.gridx = 0;
		gbc_cbListaUsuario.gridy = 1;
		cbListaUsuario.setModel(new DefaultComboBoxModel<>(new String[]{ //pre-carga para mostrar algo en el combobox
			    "Seleccione...", "Usuario A", "Usuario B", "Usuario C"
			}));
		cbListaUsuario.setSelectedIndex(0); // seleccionas el que queres mostrar por defecto
		panel_izquierda.add(cbListaUsuario, gbc_cbListaUsuario);
				
				
				
		//REGISTROS: TAG y COMBOBOX
		JLabel lblRegistro = new JLabel("Registro: ");
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.insets = new Insets(6, 12, 6, 6);
		gbc_lblRegistro.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblRegistro.gridx = 0;
		gbc_lblRegistro.gridy = 2;
		panel_izquierda.add(lblRegistro, gbc_lblRegistro);
						
		JComboBox<String> cbListaRegistro = new JComboBox<String>();
		GridBagConstraints gbc_cbListaRegistro = new GridBagConstraints();
		gbc_cbListaRegistro.weightx = 1.0;
		gbc_cbListaRegistro.insets = new Insets(6, 6, 6, 12);
		gbc_cbListaRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbListaRegistro.gridx = 0;
		gbc_cbListaRegistro.gridy = 3;
		cbListaRegistro.setModel(new DefaultComboBoxModel<>(new String[]{ //pre-carga para mostrar algo en el combobox
		    "Seleccione...", "Registro A", "Registro B", "Registro C"
		}));
		panel_izquierda.add(cbListaRegistro, gbc_cbListaRegistro);
						
						
						
		//Panel Información				
		JTextArea infoTipoRegistro = new JTextArea();
		infoTipoRegistro.setText("Fecha de Registro: " + "\n"
				+ "Evento:  " + "\n"
				+ "Edición: " + "\n"
				+ "Costo: " );
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
