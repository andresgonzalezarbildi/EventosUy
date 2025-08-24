package presentacion;

import javax.swing.*;
import java.awt.event.*;
import logica.IControladorUsuario;
import logica.DataUsuario;
import excepciones.UsuarioNoExisteException;

public class ConsultaUsuario extends JInternalFrame {

    private IControladorUsuario ICU;
    private JList<DataUsuario> listaUsuarios;
    private DefaultListModel<DataUsuario> listaModel;
    private JTextArea infoUsuario;

    public ConsultaUsuario(IControladorUsuario ICU) {
        super("Consulta de Usuario", true, true, true, true);
        this.ICU = ICU;
        setSize(600, 400);
        setLayout(null);

        // Lista de usuarios
        listaModel = new DefaultListModel<>();
        listaUsuarios = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaUsuarios);
        scrollLista.setBounds(10, 10, 200, 340);
        add(scrollLista);

        // Área para mostrar info del usuario
        infoUsuario = new JTextArea();
        infoUsuario.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(infoUsuario);
        scrollInfo.setBounds(220, 10, 360, 340);
        add(scrollInfo);


        // Acción al seleccionar un usuario
        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInfoUsuario(listaUsuarios.getSelectedValue());
            }
        });
    }

    public void cargarUsuarios() {
        try {
            DataUsuario[] usuarios = ICU.getUsuarios();
            listaModel.clear(); // limpiar antes de agregar
            if (usuarios.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
                dispose();
                return;
            }
            for (DataUsuario u : usuarios) {
                listaModel.addElement(u);
            }
        } catch (UsuarioNoExisteException e) {
            JOptionPane.showMessageDialog(this, "No hay usuarios registrados.");
            dispose();
        }
    }


    private void mostrarInfoUsuario(DataUsuario usuario) {
        if (usuario == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Nickname: ").append(usuario.getNickname()).append("\n");
        sb.append("Nombre: ").append(usuario.getNombre()).append("\n");
        sb.append("Correo: ").append(usuario.getCorreo()).append("\n");

        // Aquí iría la lógica para mostrar ediciones o registros según tipo
        // sb.append("Ediciones: ...\n");
        // sb.append("Registros: ...\n");

        infoUsuario.setText(sb.toString());
    }
}
