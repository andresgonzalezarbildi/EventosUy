package presentacion;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import logica.IControladorUsuario;
import logica.DataUsuario;
import excepciones.UsuarioNoExisteException;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;


public class ConsultaUsuario extends JInternalFrame {

    private IControladorUsuario ICU;
    private JList<DataUsuario> listaUsuarios;
    private DefaultListModel<DataUsuario> listaModel;
    private JTextArea infoUsuario;
    private JTextField buscador;
    private DataUsuario[] usuarios;

    public ConsultaUsuario(IControladorUsuario ICU) {
        super("Consulta de Usuario", true, true, true, true);
        this.ICU = ICU;
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        // Panel izquierdo (buscador + lista)
        JPanel panelIzq = new JPanel(new BorderLayout());

        // Label arriba
        JLabel lblLista = new JLabel("Usuarios", SwingConstants.CENTER);
        panelIzq.add(lblLista, BorderLayout.NORTH);

        // Lista de usuarios
        listaModel = new DefaultListModel<>();
        listaUsuarios = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        panelIzq.add(scrollLista, BorderLayout.CENTER);

     // Campo de búsqueda abajo
     // Campo de búsqueda abajo
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

        // Listener para filtrar mientras escribe
        buscador.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrar() {
                String filtro = buscador.getText().trim();
                if (filtro.equals("Buscar por nickname...")) {
                    filtro = ""; // ignoramos el placeholder
                }

                listaModel.clear();
                try {
                    for (DataUsuario u : ICU.getUsuarios()) {
                        if (u.getNickname().toLowerCase().contains(filtro.toLowerCase())) {
                            listaModel.addElement(u);
                        }
                    }
                } catch (UsuarioNoExisteException e) {
                    // si no hay usuarios, no hacemos nada
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { filtrar(); }
            @Override public void removeUpdate(DocumentEvent e) { filtrar(); }
            @Override public void changedUpdate(DocumentEvent e) { filtrar(); }
        });

        panelIzq.add(buscador, BorderLayout.SOUTH);



        getContentPane().add(panelIzq, BorderLayout.WEST);

        // Panel derecho (datos del usuario)
        JPanel panelDer = new JPanel(new BorderLayout());
        JLabel lblDatos = new JLabel("Datos del Usuario", SwingConstants.CENTER);
        panelDer.add(lblDatos, BorderLayout.NORTH);

        infoUsuario = new JTextArea();
        infoUsuario.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(infoUsuario);
        panelDer.add(scrollInfo, BorderLayout.CENTER);

        getContentPane().add(panelDer, BorderLayout.CENTER);

        // Acción al seleccionar un usuario
        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInfoUsuario(listaUsuarios.getSelectedValue());
            }
        });

        // Filtrado dinámico
        buscador.getDocument().addDocumentListener(new DocumentListener() {
        	private void filtrar() {
        	    String filtro = buscador.getText().trim();
        	    if (filtro.equals("Buscar por nickname...")) {
        	        filtro = ""; // ignoramos el placeholder
        	    }

        	    listaModel.clear();
        	    try {
        	        for (DataUsuario u : ICU.getUsuarios()) {
        	            if (u.getNickname().toLowerCase().contains(filtro.toLowerCase())) {
        	                listaModel.addElement(u);
        	            }
        	        }
        	    } catch (UsuarioNoExisteException e) {
        	        // si no hay usuarios, no hacemos nada
        	    }
        	}

            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
        });
    }

    public boolean cargarUsuarios() {
        listaModel.clear();
        try {
            usuarios = ICU.getUsuarios();
            if (usuarios.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
                return false; // indicamos que no hay usuarios
            }
            for (DataUsuario u : usuarios) {
                listaModel.addElement(u);
            }
            return true; // sí hay usuarios
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
            return false;
        }
    }

    private void mostrarInfoUsuario(DataUsuario usuario) {
        if (usuario == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Nickname: ").append(usuario.getNickname()).append("\n");
        sb.append("Nombre: ").append(usuario.getNombre()).append("\n");
        sb.append("Correo: ").append(usuario.getCorreo()).append("\n");
        sb.append("Tipo: ").append(usuario.getTipo()).append("\n");

        if (usuario.getTipo().equals("Organizador")) {
            sb.append("Descripción: ").append(usuario.getDescripcion()).append("\n");
            sb.append("Link: ").append(usuario.getLink()).append("\n");
        } else if (usuario.getTipo().equals("Asistente")) {
            sb.append("Apellido: ").append(usuario.getApellido()).append("\n");
            sb.append("Fecha Nac.: ").append(usuario.getFechaNacimiento()).append("\n");
        }
        infoUsuario.setText(sb.toString());
    }
}
