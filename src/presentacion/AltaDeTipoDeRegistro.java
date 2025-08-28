package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class AltaDeTipoDeRegistro extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaDeTipoDeRegistro dialog = new AltaDeTipoDeRegistro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaDeTipoDeRegistro() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Alta De Tipo De Registro");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(12, 12, 12, 12));
			getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			panel.setLayout(gbl_panel);
			{
				JLabel lblEventos = new JLabel("Evento: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblEventos, gbc_lblNewLabel);
			}
			{
				JComboBox<String> cbListaEvento = new JComboBox<String>();
				GridBagConstraints gbc_ListaEvento = new GridBagConstraints();
				gbc_ListaEvento.weightx = 1.0;
				gbc_ListaEvento.insets = new Insets(6, 6, 6, 12);
				gbc_ListaEvento.fill = GridBagConstraints.HORIZONTAL;
				gbc_ListaEvento.gridx = 1;
				gbc_ListaEvento.gridy = 0;
				cbListaEvento.setModel(new DefaultComboBoxModel<>(new String[]{
					    "Seleccione...", "Evento A", "Evento B", "Evento C"
					}));
					cbListaEvento.setSelectedIndex(0); // placeholder solution
				panel.add(cbListaEvento, gbc_ListaEvento);
			}
			{
				JLabel lblEdiciones = new JLabel("Edicion: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 1;
				panel.add(lblEdiciones, gbc_lblNewLabel);
			}
			{
				JComboBox<String> cbListaEdiciones = new JComboBox<String>();
				cbListaEdiciones.setModel(new DefaultComboBoxModel<>(new String[]{
					    "Seleccione...", "Edicion A", "Edicion B", "Edicion C"
					}));
				cbListaEdiciones.setSelectedIndex(0); /* placeholder solution... */
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.weightx = 1.0;
				gbc_comboBox.insets = new Insets(6, 6, 6, 12);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 1;
				panel.add(cbListaEdiciones, gbc_comboBox);
			}
			{
				JLabel lblNombre = new JLabel("Nombre: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 2;
				panel.add(lblNombre, gbc_lblNewLabel);
			}
			{
				textField = new JTextField();
				textField.setToolTipText("Ingrese un nombre válido");
				textField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(6, 6, 6, 12);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 2;
				panel.add(textField, gbc_textField);
			}
			{
				JLabel lblDescripcion = new JLabel("Descripción: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 3;
				panel.add(lblDescripcion, gbc_lblNewLabel);
			}
			{
				textField = new JTextField();
				textField.setToolTipText("Ingrese una descripción");
				textField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(6, 6, 6, 12);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 3;
				panel.add(textField, gbc_textField);
			}
			{
				JLabel lblCosto = new JLabel("Costo: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 4;
				panel.add(lblCosto, gbc_lblNewLabel);
			}
			{
				textField = new JTextField();
				textField.setToolTipText("Ingrese su costo");
				textField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(6, 6, 6, 12);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 4;
				panel.add(textField, gbc_textField);
			}
			{
				JLabel lblCupo = new JLabel("Cupo: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 5;
				panel.add(lblCupo, gbc_lblNewLabel);
			}
			{
				textField = new JTextField();
				textField.setToolTipText("Ingrese su cupo");
				textField.setColumns(10);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.insets = new Insets(6, 6, 6, 12);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 5;
				panel.add(textField, gbc_textField);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JButton btnAceptar = new JButton("Aceptar");
				panel.add(btnAceptar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				panel.add(btnCancelar);
			}
		}
	}

}
