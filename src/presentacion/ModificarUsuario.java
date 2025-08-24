package presentacion;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.border.TitledBorder;

import logica.IControladorUsuario;
import logica.DataUsuario;
import excepciones.UsuarioNoExisteException;

@SuppressWarnings("serial")
public class ModificarUsuario extends JInternalFrame {

    private IControladorUsuario controlUsr;

    private JComboBox<String> comboTipoUsuario;
    private JTextField textFieldNickname, textFieldNombre, textFieldCorreo;
    private JLabel lblDescripcion, lblLink, lblApellido, lblFechaNacimiento;
    private JTextField textFieldDescripcion, textFieldLink, textFieldApellido, textFieldFechaNacimiento;
    private JButton btnAceptar, btnCancelar;

    private JList<DataUsuario> listaUsuarios;
    private DefaultListModel<DataUsuario> listaModel;
    private JTextField buscador;
    private DataUsuario[] usuarios;

    public ModificarUsuario(IControladorUsuario icu) {
        controlUsr = icu;

        setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setTitle("Modificar Usuario");
        setBounds(10, 40, 600, 400);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        // Panel izquierdo: buscador + lista
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(200, 0));
        panelIzq.setMinimumSize(new Dimension(200, 0));

        JLabel lblUsuarios = new JLabel("Usuarios", SwingConstants.CENTER);
        panelIzq.add(lblUsuarios, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        listaUsuarios = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        panelIzq.add(scrollLista, BorderLayout.CENTER);

        // Buscador
        buscador = new JTextField("Buscar por nickname...");
        buscador.setForeground(Color.GRAY);
        buscador.setToolTipText("Buscar por nickname...");
        buscador.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (buscador.getText().equals("Buscar por nickname...")) {
                    buscador.setText("");
                    buscador.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (buscador.getText().isEmpty()) {
                    buscador.setText("Buscar por nickname...");
                    buscador.setForeground(Color.GRAY);
                }
            }
        });

        buscador.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrar() {
                String filtro = buscador.getText().trim();
                if (filtro.equals("Buscar por nickname...")) filtro = "";

                listaModel.clear();
                try {
                    for (DataUsuario u : controlUsr.getUsuarios()) {
                        if (u.getNickname().toLowerCase().contains(filtro.toLowerCase())) {
                            listaModel.addElement(u);
                        }
                    }
                } catch (UsuarioNoExisteException e) {
                    // no hay usuarios
                }
            }
            @Override public void insertUpdate(DocumentEvent e) { filtrar(); }
            @Override public void removeUpdate(DocumentEvent e) { filtrar(); }
            @Override public void changedUpdate(DocumentEvent e) { filtrar(); }
        });

        panelIzq.add(buscador, BorderLayout.SOUTH);
        getContentPane().add(panelIzq, BorderLayout.WEST);

     // Panel derecho: formulario
        JPanel panelDer = new JPanel(new BorderLayout());

        // Panel interno solo para los campos y botones con borde
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Datos de Usuario"
        ));

        // Componentes
        comboTipoUsuario = new JComboBox<>(new String[]{"", "Asistente", "Organizador"});
        comboTipoUsuario.setEnabled(false);
        textFieldNickname = new JTextField();
        textFieldNickname.setEditable(false);
        textFieldNombre = new JTextField();
        textFieldCorreo = new JTextField();
        textFieldCorreo.setEditable(false);

        lblDescripcion = new JLabel("Descripción:");
        textFieldDescripcion = new JTextField();
        lblLink = new JLabel("Link:");
        textFieldLink = new JTextField();
        lblApellido = new JLabel("Apellido:");
        textFieldApellido = new JTextField();
        lblFechaNacimiento = new JLabel("Fecha Nac.:");
        textFieldFechaNacimiento = new JTextField();

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2,2,2,2);
        gbc.gridwidth = 1;
        int y = 0;

        // Tipo
        gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(comboTipoUsuario, gbc);

        // Nickname
        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(new JLabel("Nickname:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldNickname, gbc);

        // Nombre
        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldNombre, gbc);

        // Correo
        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldCorreo, gbc);

        // Campos extra
        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(lblDescripcion, gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldDescripcion, gbc);

        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(lblLink, gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldLink, gbc);

        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(lblApellido, gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldApellido, gbc);

        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(lblFechaNacimiento, gbc);
        gbc.gridx = 1;
        panelCampos.add(textFieldFechaNacimiento, gbc);

        // Botones
        y++; gbc.gridx = 0; gbc.gridy = y;
        panelCampos.add(btnAceptar, gbc);
        gbc.gridx = 1;
        panelCampos.add(btnCancelar, gbc);

        // Ocultar campos extra inicialmente
        lblDescripcion.setVisible(false); textFieldDescripcion.setVisible(false);
        lblLink.setVisible(false); textFieldLink.setVisible(false);
        lblApellido.setVisible(false); textFieldApellido.setVisible(false);
        lblFechaNacimiento.setVisible(false); textFieldFechaNacimiento.setVisible(false);

        // Agregar solo el panel interno al panelDer
        panelDer.add(panelCampos, BorderLayout.CENTER);
        add(panelDer, BorderLayout.CENTER);


        // Acción botones
        btnAceptar.addActionListener(e -> cmdModificarUsuario());
        btnCancelar.addActionListener(e -> setVisible(false));

        // Selección de usuario carga datos automáticamente
        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DataUsuario u = listaUsuarios.getSelectedValue();
                if (u != null) cargarUsuario(u);
            }
        });

        // Cargar lista al abrir con corrección para no abrir frame si no hay usuarios
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent e) {
                boolean hayUsuarios = cargarUsuarios();
                if (!hayUsuarios) {
                    JOptionPane.showMessageDialog(ModificarUsuario.this,
                            "No hay usuarios registrados",
                            "Modificar Usuario",
                            JOptionPane.INFORMATION_MESSAGE);
                    SwingUtilities.invokeLater(() -> doDefaultCloseAction());
                }
            }
        });
    }

    public boolean cargarUsuarios() {
        listaModel.clear();
        try {
            usuarios = controlUsr.getUsuarios();
            if (usuarios.length == 0) return false; 
            for (DataUsuario u : usuarios) {
                listaModel.addElement(u);
            }
            return true;
        } catch (UsuarioNoExisteException e) {
            return false; 
        }
    }

    public void cargarUsuario(DataUsuario usuario) {
        if (usuario == null) return;
        comboTipoUsuario.setSelectedItem(usuario.getTipo());
        textFieldNickname.setText(usuario.getNickname());
        textFieldNombre.setText(usuario.getNombre());
        textFieldCorreo.setText(usuario.getCorreo());

        lblDescripcion.setVisible("Organizador".equals(usuario.getTipo()));
        textFieldDescripcion.setVisible("Organizador".equals(usuario.getTipo()));
        textFieldDescripcion.setText(usuario.getDescripcion());

        lblLink.setVisible("Organizador".equals(usuario.getTipo()));
        textFieldLink.setVisible("Organizador".equals(usuario.getTipo()));
        textFieldLink.setText(usuario.getLink());

        lblApellido.setVisible("Asistente".equals(usuario.getTipo()));
        textFieldApellido.setVisible("Asistente".equals(usuario.getTipo()));
        textFieldApellido.setText(usuario.getApellido());

        lblFechaNacimiento.setVisible("Asistente".equals(usuario.getTipo()));
        textFieldFechaNacimiento.setVisible("Asistente".equals(usuario.getTipo()));
        textFieldFechaNacimiento.setText(usuario.getFechaNacimiento());

        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void cmdModificarUsuario() {
        String nombreU = textFieldNombre.getText();
        String tipoU = (String) comboTipoUsuario.getSelectedItem();
        String descripcionU = textFieldDescripcion.getText();
        String linkU = textFieldLink.getText();
        String apellidoU = textFieldApellido.getText();
        String fechaNacU = textFieldFechaNacimiento.getText();

        if (!checkFormulario()) return;

        try {
            controlUsr.modificarUsuario(
                    textFieldNickname.getText(), nombreU, tipoU,
                    descripcionU, linkU, apellidoU, fechaNacU
            );
            JOptionPane.showMessageDialog(this, "El usuario se ha modificado con éxito",
                    "Modificar Usuario", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkFormulario() {
        String tipo = (String) comboTipoUsuario.getSelectedItem();
        String nombreU = textFieldNombre.getText();

        if (nombreU.isEmpty() || tipo == null || tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos obligatorios no pueden estar vacíos",
                    "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("Organizador".equals(tipo) && textFieldDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La descripción es obligatoria para un Organizador",
                    "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("Asistente".equals(tipo) &&
                (textFieldApellido.getText().isEmpty() || textFieldFechaNacimiento.getText().isEmpty())) {
            JOptionPane.showMessageDialog(this, "Apellido y fecha de nacimiento son obligatorios para un Asistente",
                    "Modificar Usuario", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
