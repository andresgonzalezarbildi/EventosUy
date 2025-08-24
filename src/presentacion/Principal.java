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
        JMenuItem miAltaUsuario = new JMenuItem("Alta Usuario");
        miAltaUsuario.addActionListener(e -> {
            ensureSize(altaUsuarioInternalFrame, 600, 400);
            showInternal(altaUsuarioInternalFrame);
        });
        JMenuItem miConsultaUsuario = new JMenuItem("Consulta de Usuario");
        JMenuItem miModificarUsuario = new JMenuItem("Modificar Datos de Usuario");

        menuUsuario.add(miAltaUsuario);
        menuUsuario.add(miConsultaUsuario);
        menuUsuario.add(miModificarUsuario);
        
        menuBar.add(menuUsuario);

        // Menú Evento
        JMenu menuEvento = new JMenu("Eventos");
        JMenuItem miAltaEvento = new JMenuItem("Alta de Evento");
        JMenuItem miConsultaEvento = new JMenuItem("Consulta de Evento");
        JMenuItem miAltaEdicion = new JMenuItem("Alta de Edición de Evento");
        // Los ActionListener se dejan vacíos por ahora
        menuEvento.add(miAltaEvento);
        menuEvento.add(miConsultaEvento);
        menuEvento.add(miAltaEdicion);
        menuBar.add(menuEvento);

     // Menú Institución
        JMenu menuInstitucion = new JMenu("Instituciónes");

        // Items existentes
        JMenuItem miAltaInstitucion = new JMenuItem("Alta de Institución");
        menuInstitucion.add(miAltaInstitucion);

        // Nuevos items de Patrocinio
        JMenuItem miAltaPatrocinio = new JMenuItem("Alta de Patrocinio");
        miAltaPatrocinio.addActionListener(e -> {
            // abrir internal frame correspondiente
            // ejemplo: showInternal(altaPatrocinioInternalFrame);
        });
        menuInstitucion.add(miAltaPatrocinio);

        JMenuItem miConsultaPatrocinio = new JMenuItem("Consulta de Patrocinio");
        miConsultaPatrocinio.addActionListener(e -> {
            // abrir internal frame correspondiente
            // ejemplo: showInternal(consultaPatrocinioInternalFrame);
        });
        menuInstitucion.add(miConsultaPatrocinio);

        menuBar.add(menuInstitucion);

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
