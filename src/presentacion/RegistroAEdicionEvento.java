package presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;

public class RegistroAEdicionEvento extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroAEdicionEvento dialog = new RegistroAEdicionEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistroAEdicionEvento() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setTitle("Registro A Edicion Evento");
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
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				JLabel lblTipoRegistro = new JLabel("Tipo De Registro: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 2;
				
				panel.add(lblTipoRegistro, gbc_lblNewLabel);
			}
			{
				JComboBox<String> cbTipoRegistro = new JComboBox<String>();
				cbTipoRegistro.setModel(new DefaultComboBoxModel<>(new String[]{
					    "Seleccione...", "Tipo Registro A", "Tipo Registro B", "Tipo Registro C"
					}));
				cbTipoRegistro.setSelectedIndex(0); /* placeholder solution... */
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.weightx = 1.0;
				gbc_comboBox.insets = new Insets(6, 6, 6, 12);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 2;
				panel.add(cbTipoRegistro, gbc_comboBox);
			}
			{
				JLabel lblAsistentes = new JLabel("Asistente: ");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.insets = new Insets(6, 12, 6, 6);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 3;
				panel.add(lblAsistentes, gbc_lblNewLabel);
			}
			{
				JComboBox<String> cbAsistentes = new JComboBox<String>();
				cbAsistentes.setModel(new DefaultComboBoxModel<>(new String[]{
					    "Seleccione...", "Asistentes A", "Asistentes B", "Asistentes C"
					}));
				cbAsistentes.setSelectedIndex(0); /* placeholder solution... */
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.weightx = 1.0;
				gbc_comboBox.insets = new Insets(6, 6, 6, 12);
				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBox.gridx = 1;
				gbc_comboBox.gridy = 3;
				panel.add(cbAsistentes, gbc_comboBox);
			}
			{
				JLabel lblFecha = new JLabel("Fecha: ");
				GridBagConstraints gbc_lblEventos = new GridBagConstraints();
				gbc_lblEventos.insets = new Insets(6, 12, 6, 6);
				gbc_lblEventos.gridx = 0;
				gbc_lblEventos.gridy = 4;
				panel.add(lblFecha, gbc_lblEventos);
			}
			{
				JSpinner spFecha = new JSpinner();
				spFecha.setModel(new javax.swing.SpinnerDateModel());
				spFecha.setEditor(new javax.swing.JSpinner.DateEditor(spFecha, "dd/MM/yyyy"));
				spFecha.setToolTipText("Seleccioná la fecha del registro");
				GridBagConstraints gbc_spFecha = new GridBagConstraints();
				gbc_spFecha.weightx = 1.0;
				gbc_spFecha.insets = new Insets(6, 6, 6, 12);
				gbc_spFecha.fill = GridBagConstraints.HORIZONTAL;
				gbc_spFecha.gridx = 1;
				gbc_spFecha.gridy = 4;
				panel.add(spFecha, gbc_spFecha);
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
