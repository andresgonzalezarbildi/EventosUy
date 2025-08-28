package presentacion.usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorUsuario;
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

    // Panel inferior dinámico
    private JLabel lblExtra;
    private JTextArea extraInfo;

    public ConsultaUsuario(IControladorUsuario ICU) {
        super("Consulta de Usuario", true, true, true, true);
        this.ICU = ICU;
        setSize(600, 400);
        getContentPane().setLayout(new BorderLayout());

        // Panel izquierdo (buscador + lista)
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setPreferredSize(new Dimension(200, 0));
        panelIzq.setMinimumSize(new Dimension(200, 0));

        JLabel lblLista = new JLabel("Usuarios", SwingConstants.CENTER);
        panelIzq.add(lblLista, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        listaUsuarios = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        panelIzq.add(scrollLista, BorderLayout.CENTER);

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

        panelIzq.add(buscador, BorderLayout.SOUTH);
        getContentPane().add(panelIzq, BorderLayout.WEST);

        // -------- PANEL DERECHO CON SPLIT --------
        infoUsuario = new JTextArea();
        infoUsuario.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(infoUsuario);

        JPanel panelInferior = new JPanel(new BorderLayout());
        lblExtra = new JLabel("Información adicional", SwingConstants.CENTER);
        panelInferior.add(lblExtra, BorderLayout.NORTH);

        extraInfo = new JTextArea("Aquí va información de ediciones o registros...");
        extraInfo.setEditable(false);
        panelInferior.add(new JScrollPane(extraInfo), BorderLayout.CENTER);

        JSplitPane splitDer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollInfo, panelInferior);
        splitDer.setDividerLocation(200);
        splitDer.setResizeWeight(0.5);

        getContentPane().add(splitDer, BorderLayout.CENTER);

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
                    filtro = "";
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
                return false;
            }
            for (DataUsuario u : usuarios) {
                listaModel.addElement(u);
            }
            return true;
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

            lblExtra.setText("Ediciones asociadas");
            extraInfo.setText("Aquí se mostrarán las ediciones asociadas...");
        } else if (usuario.getTipo().equals("Asistente")) {
            sb.append("Apellido: ").append(usuario.getApellido()).append("\n");
            sb.append("Fecha Nac.: ").append(usuario.getFechaNacimiento()).append("\n");

            lblExtra.setText("Registros a ediciones");
            extraInfo.setText("Aquí se mostrarán los registros a ediciones...");
        } else {
            lblExtra.setText("Información adicional");
            extraInfo.setText("");
        }

        infoUsuario.setText(sb.toString());
    }

    public DataUsuario getUsuarioSeleccionado() {
        return listaUsuarios.getSelectedValue();
    }
}
