package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logica.Fabrica;
import logica.clases.Categoria;
import logica.controladores.ControladorUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorEvento;
import presentacion.evento.AltaEvento;
import presentacion.evento.ConsultaEvento;
import presentacion.registros.RegistroAEdicionEvento;
import presentacion.usuario.AltaUsuario;
import presentacion.usuario.ConsultaUsuario;
import presentacion.usuario.ModificarUsuario;


public class Principal {

    private JFrame frmGestion;
    private JDesktopPane desktop;
    private IControladorUsuario ICU;
    private IControladorEvento IEV;
    private AltaEvento altaEventoInternalFrame;
   
    private ConsultaEvento consultaEventoInternalFrame;
    private AltaUsuario altaUsuarioInternalFrame;
    private ConsultaUsuario consultaUsuarioInternalFrame;
    private ModificarUsuario modificarUsuarioInternalFrame;
    
    private RegistroAEdicionEvento registrarEdicionEvento;


    public static void main(String[] args) {
    	// 1. Obtengo la instancia del manejador
        ManejadorEvento manejador = ManejadorEvento.getInstance();

        // 2. Agrego categorías de ejemplo
        manejador.agregarCategoria(new Categoria("CA01"));
        manejador.agregarCategoria(new Categoria("CA02"));
        manejador.agregarCategoria(new Categoria("CA03"));
        manejador.agregarCategoria(new Categoria("CA04"));
        manejador.agregarCategoria(new Categoria("CA05"));

        
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
        IEV = fabrica.getControladorEvento();

        altaUsuarioInternalFrame = new AltaUsuario(ICU);
        altaUsuarioInternalFrame.setLocation(10, 23);
        altaUsuarioInternalFrame.setClosable(true);
        desktop.add(altaUsuarioInternalFrame);
        altaUsuarioInternalFrame.setVisible(false);
        altaUsuarioInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        consultaUsuarioInternalFrame = new ConsultaUsuario(ICU);
        consultaUsuarioInternalFrame.setLocation(10, 23);
        consultaUsuarioInternalFrame.setClosable(true);
        desktop.add(consultaUsuarioInternalFrame);
        consultaUsuarioInternalFrame.setVisible(false);
        consultaUsuarioInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        modificarUsuarioInternalFrame = new ModificarUsuario(ICU);
        modificarUsuarioInternalFrame.setLocation(10, 23);
        modificarUsuarioInternalFrame.setClosable(true);
        desktop.add(modificarUsuarioInternalFrame);
        modificarUsuarioInternalFrame.setVisible(false);
        modificarUsuarioInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        altaEventoInternalFrame = new AltaEvento(IEV);
        altaEventoInternalFrame.setLocation(10, 23);
        altaEventoInternalFrame.setClosable(true);
        desktop.add(altaEventoInternalFrame);
        altaEventoInternalFrame.setVisible(false);
        altaEventoInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        consultaEventoInternalFrame = new ConsultaEvento(IEV);
        consultaEventoInternalFrame.setLocation(10, 23);
        consultaEventoInternalFrame.setClosable(true);
        desktop.add(consultaEventoInternalFrame);
        consultaEventoInternalFrame.setVisible(false);
        consultaEventoInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
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

////////////////////////////////////////////////////////////////////////////////USUARIO

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

////////////////////////////////////////////////////////////////////////////////INSTITUCIONES
        JMenu menuInstitucion = new JMenu("Instituciones");
        JMenuItem miAltaInstitucion = new JMenuItem("Alta de Institución");
        // miAltaInstitucion.addActionListener(e -> showAltaInstitucion());
        menuInstitucion.add(miAltaInstitucion);
        menuBar.add(menuInstitucion);
        
///////////////////////////////////////////////////////////////////////////////PATROCINIOS

        JMenu mnPatrocinios = new JMenu("Patrocinios");
        menuInstitucion.add(mnPatrocinios);

        JMenuItem miAltaPatrocinio_1 = new JMenuItem("Alta de Patrocinio");
        // miAltaPatrocinio_1.addActionListener(e -> showAltaPatrocinio());
        mnPatrocinios.add(miAltaPatrocinio_1);

        JMenuItem miConsultaPatrocinio_1 = new JMenuItem("Consulta de Patrocinio");
        // miConsultaPatrocinio_1.addActionListener(e -> showConsultaPatrocinio());
        mnPatrocinios.add(miConsultaPatrocinio_1);

////////////////////////////////////////////////////////////////////////////////EVENTOS

        JMenu menuEvento = new JMenu("Eventos");

        JMenuItem miAltaEvento = new JMenuItem("Alta de Evento");
        miAltaEvento.addActionListener(e -> {
        	// Al hacer clic en "Alta Evento":
        	if (altaEventoInternalFrame == null || altaEventoInternalFrame.isClosed()) {
        		altaEventoInternalFrame = new AltaEvento(IEV);
        	    desktop.add(altaEventoInternalFrame);
        	} else {
        	    altaEventoInternalFrame.limpiarFormulario(); //  limpiar para mostrar de nuevo
        	}
        	altaEventoInternalFrame.setVisible(true);
        	altaEventoInternalFrame.toFront(); // Traerla al frente
            ensureSize(altaEventoInternalFrame, 600, 400);
            showInternal(altaEventoInternalFrame);
        });
        menuEvento.add(miAltaEvento);
        JMenuItem miConsultaEvento = new JMenuItem("Consulta de Evento");
        miConsultaEvento.addActionListener(e -> abrirConsultaEvento());  
        menuEvento.add(miConsultaEvento);
        
        
////////////////////////////////////////////////////////////////////////////////EDICION
      
        JMenuItem miAltaEdicion = new JMenuItem("Alta de Edición de Evento");
        // miAltaEdicion.addActionListener(e -> showAltaEdicion());
        menuEvento.add(miAltaEdicion);
      
        JMenuItem miConsultaEdicionEvento = new JMenuItem("Consulta Edicion de Evento");
        // miConsultaEdicionEvento.addActionListener(e -> showConsultaEdicionEvento());
        menuEvento.add(miConsultaEdicionEvento);
        
        menuBar.add(menuEvento);
        
//////////////////////////////////////////////////////////////////////////////////REGISTROS        
        // Menú Registros
        JMenu mnRegistros = new JMenu("Registros");
        menuBar.add(mnRegistros);

        JMenuItem mntmConsultaDeRegistro = new JMenuItem("Consulta de Registro");
        // mntmConsultaDeRegistro.addActionListener(e -> {
        	

        JMenuItem mntmRegistroAEdicion = new JMenuItem("Registro a Edición de Evento");
        mntmRegistroAEdicion.addActionListener(e -> {
        RegistroAEdicionEvento ventana = new RegistroAEdicionEvento();
        ventana.setLocationRelativeTo(null); // null = centrado en pantalla
        ventana.setVisible(true);
        });
        mnRegistros.add(mntmRegistroAEdicion);
        

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
//    private void abrirAltaEvento() {
//        altaEventoInternalFrame.setVisible(true);
//        try {
//            altaEventoInternalFrame.setSelected(true); // lo trae al frente
//        } catch (java.beans.PropertyVetoException e) {
//            e.printStackTrace();
//        }
//    }


private void abrirConsultaEvento() {
    IControladorEvento ctrlEvento = Fabrica.getInstance().getControladorEvento();
    ConsultaEvento consulta = new ConsultaEvento(ctrlEvento);
    consulta.cargarEventos(ctrlEvento.getEventosDTO()); // cargamos los datos
    desktop.add(consulta);   // agregamos al JDesktopPane
    consulta.setVisible(true);   // hacemos visible la ventana
    try {
        consulta.setSelected(true); // lo trae al frente
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }
}}