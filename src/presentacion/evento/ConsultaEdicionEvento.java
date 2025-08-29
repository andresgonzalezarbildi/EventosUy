package presentacion.evento;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import logica.interfaces.IControladorEvento;

public class ConsultaEdicionEvento extends JInternalFrame {
	
	private JTextField textField;
	private IControladorEvento controlEvento;
	
	public ConsultaEdicionEvento(IControladorEvento controlEvento) {
		
		super("Alta Edicion Evento", false, true, true, true);
		this.controlEvento = controlEvento;;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Consulta Edicion Evento");
		setBounds(100, 100, 450, 300);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 39, 360, 0};
		gridBagLayout.rowHeights = new int[]{22, 22, 216, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblEvento = new JLabel("Evento:");
		GridBagConstraints gbc_lblEvento = new GridBagConstraints();
		gbc_lblEvento.anchor = GridBagConstraints.EAST;
		gbc_lblEvento.insets = new Insets(0, 0, 5, 5);
		gbc_lblEvento.gridx = 1;
		gbc_lblEvento.gridy = 0;
		getContentPane().add(lblEvento, gbc_lblEvento);
		
		JComboBox comboBoxEvento = new JComboBox();
		GridBagConstraints gbc_comboBoxEvento = new GridBagConstraints();
		gbc_comboBoxEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEvento.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEvento.gridx = 2;
		gbc_comboBoxEvento.gridy = 0;
		getContentPane().add(comboBoxEvento, gbc_comboBoxEvento);
		
		JLabel lblEdicion = new JLabel("Edicion:");
		GridBagConstraints gbc_lblEdicion = new GridBagConstraints();
		gbc_lblEdicion.anchor = GridBagConstraints.EAST;
		gbc_lblEdicion.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdicion.gridx = 1;
		gbc_lblEdicion.gridy = 1;
		getContentPane().add(lblEdicion, gbc_lblEdicion);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInfoEdicion = new JLabel("Informacion de la edicion del evento:");
		lblInfoEdicion.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblInfoEdicion, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblOrganizador = new JLabel("Organizador:");
		GridBagConstraints gbc_lblOrganizador = new GridBagConstraints();
		gbc_lblOrganizador.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganizador.anchor = GridBagConstraints.WEST;
		gbc_lblOrganizador.gridx = 0;
		gbc_lblOrganizador.gridy = 1;
		panel_1.add(lblOrganizador, gbc_lblOrganizador);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblRegistros = new JLabel("Registros:");
		lblRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblRegistros = new GridBagConstraints();
		gbc_lblRegistros.anchor = GridBagConstraints.WEST;
		gbc_lblRegistros.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistros.gridx = 0;
		gbc_lblRegistros.gridy = 2;
		panel_1.add(lblRegistros, gbc_lblRegistros);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 2;
		panel_1.add(list, gbc_list);
		
		JLabel lblNewLabel_1 = new JLabel("Consultar tipo de registro:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 3;
		panel_1.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblPatrocinio = new JLabel("Consultar Patrocinio");
		GridBagConstraints gbc_lblPatrocinio = new GridBagConstraints();
		gbc_lblPatrocinio.anchor = GridBagConstraints.WEST;
		gbc_lblPatrocinio.insets = new Insets(0, 0, 0, 5);
		gbc_lblPatrocinio.gridx = 0;
		gbc_lblPatrocinio.gridy = 4;
		panel_1.add(lblPatrocinio, gbc_lblPatrocinio);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 1;
		gbc_comboBox_2.gridy = 4;
		panel_1.add(comboBox_2, gbc_comboBox_2);
	}
}
