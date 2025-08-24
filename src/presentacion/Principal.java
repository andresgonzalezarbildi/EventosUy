package presentacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import javax.swing.*;

import logica.Fabrica;
import logica.IControladorUsuario;

public class Principal {

    private JFrame frmGestion;
    private JDesktopPane desktop;
    private IControladorUsuario ICU;

    private AltaUsuario altaUsuarioInternalFrame;

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
        ICU = fabrica.getIControladorUsuario();

        altaUsuarioInternalFrame = new AltaUsuario(ICU);
        altaUsuarioInternalFrame.setLocation(10, 23);
        altaUsuarioInternalFrame.setClosable(true);
        desktop.add(altaUsuarioInternalFrame);
        altaUsuarioInternalFrame.setVisible(false);
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
        JMenuItem miModificarUsuario = new JMenuItem("Modificar Usuario");

        menuUsuario.add(miAltaUsuario);
        menuUsuario.add(miConsultaUsuario);
        menuUsuario.add(miModificarUsuario);

        menuBar.add(menuUsuario);

        // Menú Instituciones
        JMenu menuInstitucion = new JMenu("Instituciones");

        // Items existentes
        JMenuItem miAltaInstitucion = new JMenuItem("Alta de Institución");
        menuInstitucion.add(miAltaInstitucion);

        menuBar.add(menuInstitucion);

        JMenu mnPatrocinios = new JMenu("Patrocinios");
        menuInstitucion.add(mnPatrocinios);

        JMenuItem miAltaPatrocinio_1 = new JMenuItem("Alta de Patrocinio");
        mnPatrocinios.add(miAltaPatrocinio_1);

        JMenuItem miConsultaPatrocinio_1 = new JMenuItem("Consulta de Patrocinio");
        mnPatrocinios.add(miConsultaPatrocinio_1);

        // Menú Eventos
        JMenu menuEvento = new JMenu("Eventos");
        JMenuItem miAltaEvento = new JMenuItem("Alta de Evento");
        JMenuItem miConsultaEvento = new JMenuItem("Consulta de Evento");
        JMenuItem miAltaEdicion = new JMenuItem("Alta de Edición de Evento");
        JMenuItem miConsultaEdicionEvento = new JMenuItem("Consulta de Edicion de Evento");
        // Los ActionListener se dejan vacíos por ahora
        menuEvento.add(miAltaEvento);
        menuEvento.add(miConsultaEvento);
        menuEvento.add(miAltaEdicion);
        menuEvento.add(miConsultaEdicionEvento);
        menuBar.add(menuEvento);
       


        // Menú Registros
        JMenu mnRegistros = new JMenu("Registros");
        menuBar.add(mnRegistros);

        JMenuItem mntmConsultaDeRegistro = new JMenuItem("Consulta de Registro");
        mnRegistros.add(mntmConsultaDeRegistro);

        JMenuItem mntmRegistroAEdicion = new JMenuItem("Registro a Edición de Evento");
        mnRegistros.add(mntmRegistroAEdicion);

        // Submenú Tipos de Registro
        JMenu mnTipoDeRegistros = new JMenu("Tipos de Registro");
        mnRegistros.add(mnTipoDeRegistros);

        JMenuItem mntmAltaTipoDe = new JMenuItem("Alta de Tipo de Registro");
        mnTipoDeRegistros.add(mntmAltaTipoDe);

        JMenuItem mntmConsultaTipoDe = new JMenuItem("Consulta de Tipo de Registro");
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
