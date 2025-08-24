package presentacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import javax.swing.*;

import logica.Fabrica;
import logica.IControladorUsuario;
import logica.DataUsuario;


public class Principal {

    private JFrame frmGestion;
    private JDesktopPane desktop;
    private IControladorUsuario ICU;

    private AltaUsuario altaUsuarioInternalFrame;
    private ConsultaUsuario consultaUsuarioInternalFrame;
    private ModificarUsuario modificarUsuarioInternalFrame;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Principal window = new Principal();
                window.frmGestion.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Principal() {
        initialize();

        Fabrica fabrica = Fabrica.getInstance();
        ICU = fabrica.getControladorUsuario();

        altaUsuarioInternalFrame = new AltaUsuario(ICU);
        altaUsuarioInternalFrame.setLocation(10, 23);
        altaUsuarioInternalFrame.setClosable(true);
        desktop.add(altaUsuarioInternalFrame);
        altaUsuarioInternalFrame.setVisible(false);
        
        consultaUsuarioInternalFrame = new ConsultaUsuario(ICU);
        consultaUsuarioInternalFrame.setLocation(10, 23);
        consultaUsuarioInternalFrame.setClosable(true);
        desktop.add(consultaUsuarioInternalFrame);
        consultaUsuarioInternalFrame.setVisible(false);
        
        modificarUsuarioInternalFrame = new ModificarUsuario(ICU);
        modificarUsuarioInternalFrame.setLocation(10, 23);
        modificarUsuarioInternalFrame.setClosable(true);
        desktop.add(modificarUsuarioInternalFrame);
        modificarUsuarioInternalFrame.setVisible(false);

    }

    private void initialize() {
        frmGestion = new JFrame();
        frmGestion.setTitle("Gestión del Sistema");
        frmGestion.setBounds(100, 100, 900, 600);
        frmGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        desktop = new JDesktopPane();
        frmGestion.getContentPane().setLayout(new BorderLayout());
        frmGestion.getContentPane().add(desktop, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frmGestion.setJMenuBar(menuBar);

        // Menú Sistema
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(e -> {
            frmGestion.setVisible(false);
            frmGestion.dispose();
        });
        menuSistema.add(menuSalir);
        menuBar.add(menuSistema);

        // Menú Usuario
        JMenu menuUsuario = new JMenu("Usuarios");
        JMenuItem miAltaUsuario = new JMenuItem("Alta de Usuario");
        miAltaUsuario.addActionListener(e -> {
            ensureSize(altaUsuarioInternalFrame, 600, 400);
            showInternal(altaUsuarioInternalFrame);
        });
        JMenuItem miConsultaUsuario = new JMenuItem("Consulta de Usuario");
        miConsultaUsuario.addActionListener(e -> {
            if (consultaUsuarioInternalFrame == null || consultaUsuarioInternalFrame.isClosed()) {
                consultaUsuarioInternalFrame = new ConsultaUsuario(ICU);
                desktop.add(consultaUsuarioInternalFrame);
            }
            ensureSize(consultaUsuarioInternalFrame, 600, 400);

            if (consultaUsuarioInternalFrame.cargarUsuarios()) {
                showInternal(consultaUsuarioInternalFrame);
            } else {
                consultaUsuarioInternalFrame.setVisible(false);
            }
        });




        JMenuItem miModificarUsuario = new JMenuItem("Modificar Usuario");
        miModificarUsuario.addActionListener(e -> {
            if (!modificarUsuarioInternalFrame.isVisible()) {
                modificarUsuarioInternalFrame.setVisible(true);
                modificarUsuarioInternalFrame.toFront();
                modificarUsuarioInternalFrame.cargarUsuarios();
            } else {
                modificarUsuarioInternalFrame.toFront();
            }
        });





        menuUsuario.add(miAltaUsuario);
        menuUsuario.add(miConsultaUsuario);
        menuUsuario.add(miModificarUsuario);
        menuBar.add(menuUsuario);

     // Menú Instituciones
        JMenu menuInstitucion = new JMenu("Instituciones");

        // Items existentes
        JMenuItem miAltaInstitucion = new JMenuItem("Alta de Institución");
        // miAltaInstitucion.addActionListener(e -> showAltaInstitucion());
        menuInstitucion.add(miAltaInstitucion);

        menuBar.add(menuInstitucion);

        // Submenú Patrocinios
        JMenu mnPatrocinios = new JMenu("Patrocinios");
        menuInstitucion.add(mnPatrocinios);

        JMenuItem miAltaPatrocinio_1 = new JMenuItem("Alta de Patrocinio");
        // miAltaPatrocinio_1.addActionListener(e -> showAltaPatrocinio());
        mnPatrocinios.add(miAltaPatrocinio_1);

        JMenuItem miConsultaPatrocinio_1 = new JMenuItem("Consulta de Patrocinio");
        // miConsultaPatrocinio_1.addActionListener(e -> showConsultaPatrocinio());
        mnPatrocinios.add(miConsultaPatrocinio_1);

        // Menú Eventos
        JMenu menuEvento = new JMenu("Eventos");

        JMenuItem miAltaEvento = new JMenuItem("Alta de Evento");
        // miAltaEvento.addActionListener(e -> showAltaEvento());
        menuEvento.add(miAltaEvento);

        JMenuItem miConsultaEvento = new JMenuItem("Consulta de Evento");
        // miConsultaEvento.addActionListener(e -> showConsultaEvento());
        menuEvento.add(miConsultaEvento);

        JMenuItem miAltaEdicion = new JMenuItem("Alta de Edición de Evento");
        // miAltaEdicion.addActionListener(e -> showAltaEdicion());
        menuEvento.add(miAltaEdicion);

        menuBar.add(menuEvento);

        // Menú Registros
        JMenu mnRegistros = new JMenu("Registros");
        menuBar.add(mnRegistros);

        JMenuItem mntmConsultaDeRegistro = new JMenuItem("Consulta de Registro");
        // mntmConsultaDeRegistro.addActionListener(e -> showConsultaRegistro());
        mnRegistros.add(mntmConsultaDeRegistro);

        JMenuItem mntmRegistroAEdicion = new JMenuItem("Registro a Edición de Evento");
        // mntmRegistroAEdicion.addActionListener(e -> showRegistroAEdicion());
        mnRegistros.add(mntmRegistroAEdicion);

        // Submenú Tipos de Registro
        JMenu mnTipoDeRegistros = new JMenu("Tipos de Registro");
        mnRegistros.add(mnTipoDeRegistros);

        JMenuItem mntmAltaTipoDe = new JMenuItem("Alta de Tipo de Registro");
        // mntmAltaTipoDe.addActionListener(e -> showAltaTipoRegistro());
        mnTipoDeRegistros.add(mntmAltaTipoDe);

        JMenuItem mntmConsultaTipoDe = new JMenuItem("Consulta de Tipo de Registro");
        // mntmConsultaTipoDe.addActionListener(e -> showConsultaTipoRegistro());
        mnTipoDeRegistros.add(mntmConsultaTipoDe);

    }

    private void showInternal(JInternalFrame f) {
        if (!f.isVisible()) f.setVisible(true);
        f.toFront();
        try { f.setSelected(true); } catch (PropertyVetoException ignored) {}
        if (f.getX() == 0 && f.getY() == 0) {
            int x = (desktop.getWidth() - f.getWidth()) / 2;
            int y = (desktop.getHeight() - f.getHeight()) / 2;
            f.setLocation(Math.max(0, x), Math.max(0, y));
        }
    }

    private void ensureSize(JInternalFrame f, int w, int h) {
        if (f.getWidth() == 0 || f.getHeight() == 0) f.setSize(w, h);
    }
}
