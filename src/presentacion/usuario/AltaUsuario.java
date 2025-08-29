package presentacion.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListCellRenderer;

import com.toedter.calendar.JDateChooser;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;   // <-- AGREGAR



import excepciones.UsuarioRepetidoException;
import logica.interfaces.IControladorUsuario;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {

    private IControladorUsuario controlUsr;
    
    private JComboBox<String> comboTipoUsuario;
    private JTextField textFieldNickname, textFieldNombre, textFieldCorreo;
    private JLabel lblIngreseNickname, lblIngreseNombre, lblIngreseCorreo;
    private JButton btnAceptar, btnCancelar;

    private JLabel lblDescripcion;
    private JTextField textFieldDescripcion;
    private JLabel lblLink;
    private JTextField textFieldLink;
    private JLabel lblApellido;
    private JTextField textFieldApellido;
    private JLabel lblFechaNacimiento;
    private JDateChooser dateChooserFechaNac;

    public AltaUsuario(IControladorUsuario icu) {
        controlUsr = icu;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Usuario");
        setBounds(10, 40, 360, 190);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 30, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);

        // Tipo de Usuario
        JLabel lblTipo = new JLabel("Tipo:");
        GridBagConstraints gbc_lblTipo = new GridBagConstraints();
        gbc_lblTipo.gridx = 0;
        gbc_lblTipo.gridy = 0;
        gbc_lblTipo.anchor = GridBagConstraints.EAST;
        getContentPane().add(lblTipo, gbc_lblTipo);

        comboTipoUsuario = new JComboBox<>(new String[] {"", "Asistente", "Organizador"});
        comboTipoUsuario.setSelectedIndex(0);
        comboTipoUsuario.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null || value.equals("")) setText("");
                return this;
            }
        });

        GridBagConstraints gbc_combo = new GridBagConstraints();
        gbc_combo.gridx = 1;
        gbc_combo.gridy = 0;
        gbc_combo.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboTipoUsuario, gbc_combo);

        comboTipoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) comboTipoUsuario.getSelectedItem();
                boolean isOrganizador = "Organizador".equals(seleccionado);
                boolean isAsistente = "Asistente".equals(seleccionado);

                lblDescripcion.setVisible(isOrganizador);
                textFieldDescripcion.setVisible(isOrganizador);
                lblLink.setVisible(isOrganizador);
                textFieldLink.setVisible(isOrganizador);

                lblApellido.setVisible(isAsistente);
                textFieldApellido.setVisible(isAsistente);
                lblFechaNacimiento.setVisible(isAsistente);
             dateChooserFechaNac.setVisible(isAsistente);

                getContentPane().revalidate();
                getContentPane().repaint();
                pack();
            }
        });

        // Campos comunes
        lblIngreseNickname = new JLabel("Nickname:");
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNickname = new GridBagConstraints();
        gbc_lblIngreseNickname.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNickname.gridx = 0;
        gbc_lblIngreseNickname.gridy = 1;
        getContentPane().add(lblIngreseNickname, gbc_lblIngreseNickname);

        textFieldNickname = new JTextField();
        GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
        gbc_textFieldNickname.gridwidth = 2;
        gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
        gbc_textFieldNickname.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNickname.gridx = 1;
        gbc_textFieldNickname.gridy = 1;
        getContentPane().add(textFieldNickname, gbc_textFieldNickname);
        textFieldNickname.setColumns(10);

        lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 2;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 2;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        lblIngreseCorreo = new JLabel("Correo:");
        lblIngreseCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseCorreo = new GridBagConstraints();
        gbc_lblIngreseCorreo.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseCorreo.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseCorreo.gridx = 0;
        gbc_lblIngreseCorreo.gridy = 3;
        getContentPane().add(lblIngreseCorreo, gbc_lblIngreseCorreo);

        textFieldCorreo = new JTextField();
        textFieldCorreo.setToolTipText("Ingrese un correo válido");
        textFieldCorreo.setColumns(10);
        GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
        gbc_textFieldCorreo.gridwidth = 2;
        gbc_textFieldCorreo.fill = GridBagConstraints.BOTH;
        gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCorreo.gridx = 1;
        gbc_textFieldCorreo.gridy = 3;
        getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);

        // Campos adicionales
        lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldDescripcion = new JTextField();
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.fill = GridBagConstraints.BOTH;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 4;
        getContentPane().add(lblDescripcion, gbc_lblDescripcion);
        lblDescripcion.setVisible(false);

        GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
        gbc_textFieldDescripcion.gridwidth = 2;
        gbc_textFieldDescripcion.fill = GridBagConstraints.BOTH;
        gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldDescripcion.gridx = 1;
        gbc_textFieldDescripcion.gridy = 4;
        getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
        textFieldDescripcion.setVisible(false);

        lblLink = new JLabel("Link:");
        lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldLink = new JTextField();
        GridBagConstraints gbc_lblLink = new GridBagConstraints();
        gbc_lblLink.fill = GridBagConstraints.BOTH;
        gbc_lblLink.insets = new Insets(0, 0, 5, 5);
        gbc_lblLink.gridx = 0;
        gbc_lblLink.gridy = 5;
        getContentPane().add(lblLink, gbc_lblLink);
        lblLink.setVisible(false);

        GridBagConstraints gbc_textFieldLink = new GridBagConstraints();
        gbc_textFieldLink.gridwidth = 2;
        gbc_textFieldLink.fill = GridBagConstraints.BOTH;
        gbc_textFieldLink.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldLink.gridx = 1;
        gbc_textFieldLink.gridy = 5;
        getContentPane().add(textFieldLink, gbc_textFieldLink);
        textFieldLink.setVisible(false);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldApellido = new JTextField();
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.fill = GridBagConstraints.BOTH;
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 0;
        gbc_lblApellido.gridy = 4;
        getContentPane().add(lblApellido, gbc_lblApellido);
        lblApellido.setVisible(false);

        GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
        gbc_textFieldApellido.gridwidth = 2;
        gbc_textFieldApellido.fill = GridBagConstraints.BOTH;
        gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldApellido.gridx = 1;
        gbc_textFieldApellido.gridy = 4;
        getContentPane().add(textFieldApellido, gbc_textFieldApellido);
        textFieldApellido.setVisible(false);

        lblFechaNacimiento = new JLabel("Fecha Nac.:");
        lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
        gbc_lblFechaNacimiento.fill = GridBagConstraints.BOTH;
        gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaNacimiento.gridx = 0;
        gbc_lblFechaNacimiento.gridy = 5;
        getContentPane().add(lblFechaNacimiento, gbc_lblFechaNacimiento);
        lblFechaNacimiento.setVisible(false);

        // JDateChooser en vez de JTextField
        dateChooserFechaNac = new JDateChooser();
        dateChooserFechaNac.setDateFormatString("yyyy-MM-dd"); // solo para la vista
        GridBagConstraints gbc_dateChooserFechaNac = new GridBagConstraints();
        gbc_dateChooserFechaNac.gridwidth = 2;
        gbc_dateChooserFechaNac.fill = GridBagConstraints.BOTH;
        gbc_dateChooserFechaNac.insets = new Insets(0, 0, 5, 0);
        gbc_dateChooserFechaNac.gridx = 1;
        gbc_dateChooserFechaNac.gridy = 5;
        getContentPane().add(dateChooserFechaNac, gbc_dateChooserFechaNac);
        dateChooserFechaNac.setVisible(false);

        // Botones
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
            }
        });
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 8;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 8;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
    }

    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
    String nicknameU = textFieldNickname.getText().trim();
    String nombreU = textFieldNombre.getText().trim();
    String correoU = textFieldCorreo.getText().trim();
    String tipoU = (String) comboTipoUsuario.getSelectedItem();

    String descripcionU = textFieldDescripcion.getText().trim();
    String linkU = textFieldLink.getText().trim();
    String apellidoU = textFieldApellido.getText().trim();

    // obtener LocalDate (si aplica)
    LocalDate fechaNacLD = null;
    if ("Asistente".equals(tipoU) && dateChooserFechaNac.getDate() != null) {
        fechaNacLD = dateChooserFechaNac.getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    if (checkFormulario()) {
        try {
            if ("Organizador".equals(tipoU)) {
                controlUsr.altaUsuario(nicknameU, nombreU, correoU, "Organizador",
                        descripcionU, linkU, null, null);
            } else if ("Asistente".equals(tipoU)) {
                controlUsr.altaUsuario(nicknameU, nombreU, correoU, "Asistente",
                        null, null, apellidoU, fechaNacLD);
            }

            JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
                    "Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);

        } catch (UsuarioRepetidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
        }
        limpiarFormulario();
        setVisible(false);
    }
}

	private boolean checkFormulario() {
	    String tipo = (String) comboTipoUsuario.getSelectedItem();
	    String nicknameU = textFieldNickname.getText().trim();
	    String nombreU = textFieldNombre.getText().trim();
	    String correoU = textFieldCorreo.getText().trim();
	    String descripcionU = textFieldDescripcion.getText().trim();
	    String apellidoU = textFieldApellido.getText().trim();
	    Date utilDate = dateChooserFechaNac.getDate(); // <-- ahora viene del JDateChooser
	
	    if (nicknameU.isEmpty() || nombreU.isEmpty() || correoU.isEmpty() || tipo == null || tipo.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Los campos comunes no pueden estar vacíos",
	                "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	
	    if ("Organizador".equals(tipo) && descripcionU.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "La descripción es obligatoria para un Organizador",
	                "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	
	    if ("Asistente".equals(tipo)) {
	        if (apellidoU.isEmpty() || utilDate == null) {
	            JOptionPane.showMessageDialog(this, "Apellido y fecha de nacimiento son obligatorios para un Asistente",
	                    "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	        // (opcional) validar que no sea fecha futura
	        LocalDate fnac = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        if (fnac.isAfter(LocalDate.now())) {
	            JOptionPane.showMessageDialog(this, "La fecha de nacimiento no puede ser futura",
	                    "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }
	
	    return true;
	}


    private void limpiarFormulario() {
    textFieldNickname.setText("");
    textFieldNombre.setText("");
    textFieldCorreo.setText("");

    comboTipoUsuario.setSelectedIndex(0);

    lblDescripcion.setVisible(false);
    textFieldDescripcion.setVisible(false);
    textFieldDescripcion.setText("");

    lblLink.setVisible(false);
    textFieldLink.setVisible(false);
    textFieldLink.setText("");

    lblApellido.setVisible(false);
    textFieldApellido.setVisible(false);
    textFieldApellido.setText("");

    lblFechaNacimiento.setVisible(false);
    // textFieldFechaNacimiento.setVisible(false);
    // textFieldFechaNacimiento.setText("");
    dateChooserFechaNac.setVisible(false);
    dateChooserFechaNac.setDate(null);

    getContentPane().revalidate();
    getContentPane().repaint();
}

}
