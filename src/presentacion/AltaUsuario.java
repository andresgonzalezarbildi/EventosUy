package presentacion;

import javax.swing.JInternalFrame;

import excepciones.UsuarioRepetidoException;
import logica.IControladorUsuario;

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
import javax.swing.ListCellRenderer;

/**
 * JInternalFrame que permite registrar un nuevo usuario al sistema.
 * 

 *
 */
@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {

    // Controlador de usuarios que se utilizará para las acciones del JFrame
    private IControladorUsuario controlUsr;
    
    // Los componentes gráficos se agregan como atributos de la clase
    // para facilitar su acceso desde diferentes métodos de la misma.
    private JComboBox comboTipoUsuario;
    private JTextField textFieldNickname;
    private JTextField textFieldNombre;
    private JTextField textFieldCorreo;
    private JLabel lblIngreseNickname;
    private JLabel lblIngreseNombre;
    private JLabel lblIngreseCorreo;
    private JButton btnAceptar;
    private JButton btnCancelar;

    private JLabel lblDescripcion;
    private JTextField textFieldDescripcion;
    private JLabel lblLink;
    private JTextField textFieldLink;

    private JLabel lblApellido;
    private JTextField textFieldApellido;
    private JLabel lblFechaNacimiento;
    private JTextField textFieldFechaNacimiento;
    
    /**
     * Create the frame.
     */
    public AltaUsuario(IControladorUsuario icu) {
        // Se inicializa con el controlador de usuarios
        controlUsr = icu;

        // Propiedades del JInternalFrame como dimensión, posición dentro del frame,
        // etc.
        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Usuario");
        setBounds(10, 40, 360, 190);

        // En este caso utilizaremos el GridBagLayout que permite armar una grilla
        // en donde las filas y columnas no son uniformes.
        // Conviene trabajar este componente desde la vista de diseño gráfico y sólo
        // manipular los valores para ajustar alguna cosa.
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
        comboTipoUsuario.setSelectedIndex(0); // Empieza vacío

        // Renderer para mostrar el primer elemento como vacío
        comboTipoUsuario.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null || value.equals("")) {
                    setText(""); // texto vacío
                }
                return this;
            }
        });

        GridBagConstraints gbc_combo = new GridBagConstraints();
        gbc_combo.gridx = 1;
        gbc_combo.gridy = 0;
        gbc_combo.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(comboTipoUsuario, gbc_combo);

        //ActionListner para mostrar nuevos campos dependiendo del tipo de usuario
        comboTipoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object seleccionado = comboTipoUsuario.getSelectedItem();
                if (seleccionado == null || seleccionado.equals("")) {
                    // No mostrar ningún campo aún
                    lblDescripcion.setVisible(false);
                    textFieldDescripcion.setVisible(false);
                    lblLink.setVisible(false);
                    textFieldLink.setVisible(false);

                    lblApellido.setVisible(false);
                    textFieldApellido.setVisible(false);
                    lblFechaNacimiento.setVisible(false);
                    textFieldFechaNacimiento.setVisible(false);
                } else if (seleccionado.equals("Organizador")) {
                    lblDescripcion.setVisible(true);
                    textFieldDescripcion.setVisible(true);
                    lblLink.setVisible(true);
                    textFieldLink.setVisible(true);

                    lblApellido.setVisible(false);
                    textFieldApellido.setVisible(false);
                    lblFechaNacimiento.setVisible(false);
                    textFieldFechaNacimiento.setVisible(false);
                } else { // Asistente
                    lblDescripcion.setVisible(false);
                    textFieldDescripcion.setVisible(false);
                    lblLink.setVisible(false);
                    textFieldLink.setVisible(false);

                    lblApellido.setVisible(true);
                    textFieldApellido.setVisible(true);
                    lblFechaNacimiento.setVisible(true);
                    textFieldFechaNacimiento.setVisible(true);
                }
                getContentPane().revalidate();
                getContentPane().repaint();
                pack();
            }
        });

        
        
        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el nickname del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseNickname = new JLabel("Nickname:");
        lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNickname = new GridBagConstraints();
        gbc_lblIngreseNickname.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNickname.gridx = 0;
        gbc_lblIngreseNickname.gridy = 1;
        getContentPane().add(lblIngreseNickname, gbc_lblIngreseNickname); 

        // Una campo de texto (JTextField) para ingresar el nickanme del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldNickname = new JTextField();
        GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
        gbc_textFieldNickname.gridwidth = 2;
        gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
        gbc_textFieldNickname.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNickname.gridx = 1;
        gbc_textFieldNickname.gridy = 1;
        getContentPane().add(textFieldNickname, gbc_textFieldNickname);
        textFieldNickname.setColumns(10);

        // Una etiqueta (JLabel) indicandp que en el siguiente campo debe ingresarse 
        // el Nombre del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 2;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

        // Una campo de texto (JTextField) para ingresar el Nombre del usuario. 
        // Por defecto es posible ingresar cualquier string.
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.gridwidth = 2;
        gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.gridx = 1;
        gbc_textFieldNombre.gridy = 2;
        getContentPane().add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);

        // Una etiqueta (JLabel) indicando que en el siguiente campo debe ingresarse 
        // el correo del usuario. El texto está alineado horizontalmente a la derecha para
        // que quede casi pegado al campo de texto.
        lblIngreseCorreo = new JLabel("Correo:");
        lblIngreseCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseCorreo = new GridBagConstraints();
        gbc_lblIngreseCorreo.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseCorreo.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseCorreo.gridx = 0;
        gbc_lblIngreseCorreo.gridy = 3;
        getContentPane().add(lblIngreseCorreo, gbc_lblIngreseCorreo);

        // Una campo de texto (JTextField) para ingresar el Correo del usuario. 
        // Por defecto es posible ingresar cualquier string.
        // Al campo se le incluye un Tooltip que, al pasar el mouse por encima, despliega un mensaje.
        textFieldCorreo = new JTextField();
        textFieldCorreo.setToolTipText("Ingrese un número sin puntos ni guiones");
        textFieldCorreo.setColumns(10);
        GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
        gbc_textFieldCorreo.gridwidth = 2;
        gbc_textFieldCorreo.fill = GridBagConstraints.BOTH;
        gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldCorreo.gridx = 1;
        gbc_textFieldCorreo.gridy = 3;
        getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);
        
     // Campos ocultos dependiendo del tipo de usuario que se elija

        lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
        textFieldDescripcion = new JTextField();
        GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
        gbc_lblDescripcion.fill = GridBagConstraints.BOTH;
        gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_lblDescripcion.gridx = 0;
        gbc_lblDescripcion.gridy = 4;  // fila siguiente
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
        gbc_lblApellido.gridy = 4; // misma fila que descripcion
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
        textFieldFechaNacimiento = new JTextField();
        GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
        gbc_lblFechaNacimiento.fill = GridBagConstraints.BOTH;
        gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaNacimiento.gridx = 0;
        gbc_lblFechaNacimiento.gridy = 5; // misma fila que link
        getContentPane().add(lblFechaNacimiento, gbc_lblFechaNacimiento);
        lblFechaNacimiento.setVisible(false);

        GridBagConstraints gbc_textFieldFechaNacimiento = new GridBagConstraints();
        gbc_textFieldFechaNacimiento.gridwidth = 2;
        gbc_textFieldFechaNacimiento.fill = GridBagConstraints.BOTH;
        gbc_textFieldFechaNacimiento.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldFechaNacimiento.gridx = 1;
        gbc_textFieldFechaNacimiento.gridy = 5;
        getContentPane().add(textFieldFechaNacimiento, gbc_textFieldFechaNacimiento);
        textFieldFechaNacimiento.setVisible(false);
        
        

        // Un botón (JButton) con un evento asociado que permite registrar el usuario.
        // Dado que el código de registro tiene cierta complejidad, conviene delegarlo
        // a otro método en lugar de incluirlo directamente de el método actionPerformed 
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

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
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

    // Este método es invocado al querer registrar un usuario, funcionalidad
    // provista por la operación del sistem registrarUsuario().
    // Previamente se hace una verificación de los campos, particularmente que no sean vacíos
    // y que la cédula sea un número. 
    // Tanto en caso de que haya un error (de verificación o de registro) o no, se despliega
    // un mensaje utilizando un panel de mensaje (JOptionPane).
    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        // Obtengo datos de los controles Swing
        String nicknameU = this.textFieldNickname.getText();
        String nombreU = this.textFieldNombre.getText();
        String correoU = this.textFieldCorreo.getText();

        if (checkFormulario()) {
            try {
                controlUsr.altaUsuario(nicknameU, nombreU, correoU);

                // Muestro éxito de la operación
                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito", "Registrar Usuario",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (UsuarioRepetidoException e) {
                // Muestro error de registro
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
        }
    }

    // Permite validar la información introducida en los campos e indicar
    // a través de un mensaje de error (JOptionPane) cuando algo sucede.
    // Este tipo de chequeos se puede realizar de otras formas y con otras librerías de Java, 
    // por ejemplo impidiendo que se escriban caracteres no numéricos al momento de escribir en
    // en el campo de la cédula, o mostrando un mensaje de error apenas el foco pasa a otro campo.
    private boolean checkFormulario() {
        String tipo = (String) comboTipoUsuario.getSelectedItem();
        String nicknameU = textFieldNickname.getText();
        String nombreU = textFieldNombre.getText();
        String correoU = textFieldCorreo.getText();
        String linkU = textFieldLink.getText();
        String descripcionU = textFieldDescripcion.getText();
        String apellidoU = textFieldApellido.getText();
        String fechaNacU = textFieldFechaNacimiento.getText();

        // Validación de campos comunes
        if (nicknameU.isEmpty() || nombreU.isEmpty() || correoU.isEmpty() || tipo == null || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos comunes no pueden estar vacíos", 
                    "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (tipo.equals("Organizador")) {
            if (descripcionU.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La descripción es obligatoria para un Organizador", 
                        "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // Link es opcional, no se valida
        } else if (tipo.equals("Asistente")) {
            if (apellidoU.isEmpty() || fechaNacU.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Apellido y fecha de nacimiento son obligatorios para un Asistente", 
                        "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }


    // Permite borrar el contenido de un formulario antes de cerrarlo.
    // Recordar que las ventanas no se destruyen, sino que simplemente 
    // se ocultan, por lo que conviene borrar la información para que 
    // no aparezca al mostrarlas nuevamente.
    private void limpiarFormulario() {
        textFieldNickname.setText("");
        textFieldNombre.setText("");
        textFieldCorreo.setText("");
        
        // Resetear combobox al estado inicial
        comboTipoUsuario.setSelectedIndex(0); // o null si querés que no haya selección
        
        // Ocultar los campos adicionales
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
        textFieldFechaNacimiento.setVisible(false);
        textFieldFechaNacimiento.setText("");

        getContentPane().revalidate();
        getContentPane().repaint();
    }

}
