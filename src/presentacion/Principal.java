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
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;
import presentacion.evento.AltaEdicionEvento;
import presentacion.evento.AltaEvento;
import presentacion.evento.ConsultaEdicionEvento;
import presentacion.evento.ConsultaEvento;
import presentacion.registros.AltaDeTipoDeRegistro;
import presentacion.registros.ConsultaDeRegistro;
import presentacion.registros.ConsultaDeTipoDeRegistro;
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
    private AltaEdicionEvento altaEdicionInternalFrame;
    private ConsultaEdicionEvento consultaEdicionInternalFrame;
    private RegistroAEdicionEvento registrarEdicionEvento;
    private AltaDeTipoDeRegistro altaTipoDeRegistroInternalFrame;
    private ConsultaDeTipoDeRegistro consultaDeTipoDeRegistroInternalFrame;
    private ConsultaDeRegistro consultaRegistroInternalFrame;


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
        IEV = fabrica.getControladorEvento();

        

        altaUsuarioInternalFrame = new AltaUsuario(ICU);
        altaUsuarioInternalFrame.setLocation(10, 23);
        altaUsuarioInternalFrame.setClosable(true);
        desktop.add(altaUsuarioInternalFrame);
        altaUsuarioInternalFrame.setVisible(false);
        altaUsuarioInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        consultaUsuarioInternalFrame = new ConsultaUsuario(ICU,IEV);
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
        
        consultaEventoInternalFrame = new ConsultaEvento(IEV, (ev, ed) -> {
            abrirConsultaEdicionCon(ev, ed);
        });
        consultaEventoInternalFrame.setLocation(10, 23);
        consultaEventoInternalFrame.setClosable(true);
        desktop.add(consultaEventoInternalFrame);
        consultaEventoInternalFrame.setVisible(false); 
        consultaEventoInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);

        
        altaEdicionInternalFrame = new AltaEdicionEvento(IEV,ICU);
        altaEdicionInternalFrame.setLocation(10, 23);
        altaEdicionInternalFrame.setClosable(true);
        desktop.add(altaEdicionInternalFrame);
        altaEdicionInternalFrame.setVisible(false);
        altaEdicionInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        consultaEdicionInternalFrame = new ConsultaEdicionEvento(IEV);
        consultaEdicionInternalFrame.setLocation(10, 23);
        consultaEdicionInternalFrame.setClosable(true);
        desktop.add(consultaEdicionInternalFrame);
        consultaEdicionInternalFrame.setVisible(false);
        consultaEdicionInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        
        altaTipoDeRegistroInternalFrame = new AltaDeTipoDeRegistro(IEV);
        altaTipoDeRegistroInternalFrame.setLocation(10, 23);
        altaTipoDeRegistroInternalFrame.setClosable(true);
        desktop.add(altaTipoDeRegistroInternalFrame);
        altaTipoDeRegistroInternalFrame.setVisible(false);
        altaTipoDeRegistroInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        consultaDeTipoDeRegistroInternalFrame = new ConsultaDeTipoDeRegistro(IEV);
        consultaDeTipoDeRegistroInternalFrame.setLocation(10, 23);
        consultaDeTipoDeRegistroInternalFrame.setClosable(true);
        desktop.add(consultaDeTipoDeRegistroInternalFrame);
        consultaDeTipoDeRegistroInternalFrame.setVisible(false);
        consultaDeTipoDeRegistroInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
        consultaRegistroInternalFrame = new ConsultaDeRegistro(IEV,ICU);
        consultaRegistroInternalFrame.setLocation(10, 23);
        consultaRegistroInternalFrame.setClosable(true);
        desktop.add(consultaRegistroInternalFrame);
        consultaRegistroInternalFrame.setVisible(false);
        consultaRegistroInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        
    }

    private void initialize() {

    	Fabrica fabrica = Fabrica.getInstance();
//      fabrica.getCargaDatos().CargarDatosIniciales();
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
        JMenuItem menuCarga = new JMenuItem("Carga de Datos");
        menuCarga.addActionListener(e -> {
        	fabrica.getCargaDatos().CargarDatosIniciales();
        });
        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(e -> {
            frmGestion.setVisible(false);
            frmGestion.dispose();
        });
        menuSistema.add(menuSalir);
        menuBar.add(menuSistema);
        menuSistema.add(menuCarga);
 
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
                consultaUsuarioInternalFrame = new ConsultaUsuario(ICU,IEV);
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
        miAltaEdicion.addActionListener(e -> {
        	if (altaEdicionInternalFrame == null || altaEdicionInternalFrame.isClosed()) {
        		altaEdicionInternalFrame = new AltaEdicionEvento(IEV,ICU);
        	    desktop.add(altaEdicionInternalFrame);
      	} 
        	altaEdicionInternalFrame.setVisible(true);
        	altaEdicionInternalFrame.toFront(); // Traerla al frente
            ensureSize(altaEdicionInternalFrame, 600, 400);
            showInternal(altaEdicionInternalFrame);
        });
        menuEvento.add(miAltaEdicion);
      
        JMenuItem miConsultaEdicionEvento = new JMenuItem("Consulta Edicion de Evento");
        miConsultaEdicionEvento.addActionListener(e -> {
    	if (consultaEdicionInternalFrame == null || consultaEdicionInternalFrame.isClosed()) {
    		consultaEdicionInternalFrame = new ConsultaEdicionEvento(IEV);
    	    desktop.add(consultaEdicionInternalFrame);
  	} 
    	consultaEdicionInternalFrame.setVisible(true);
    	consultaEdicionInternalFrame.toFront(); // Traerla al frente
        ensureSize(consultaEdicionInternalFrame, 600, 400);
        showInternal(consultaEdicionInternalFrame);
    });
        menuEvento.add(miConsultaEdicionEvento);
        menuBar.add(menuEvento);
        
//////////////////////////////////////////////////////////////////////////////////REGISTROS        
        // Menú Registros
        JMenu mnRegistros = new JMenu("Registros");
        menuBar.add(mnRegistros);


        JMenuItem mntmRegistroAEdicion = new JMenuItem("Registro a Edición de Evento");
        mntmRegistroAEdicion.addActionListener(e -> {
            if (registrarEdicionEvento == null || registrarEdicionEvento.isClosed()) {
                registrarEdicionEvento = new RegistroAEdicionEvento();
                desktop.add(registrarEdicionEvento);
            }
            registrarEdicionEvento.setVisible(true);
            registrarEdicionEvento.toFront();
            ensureSize(registrarEdicionEvento, 600, 400);
            showInternal(registrarEdicionEvento);
        });
        mnRegistros.add(mntmRegistroAEdicion);


        JMenuItem mntmConsultaDeRegistro = new JMenuItem("Consulta de Registro");
        mntmConsultaDeRegistro.addActionListener(e -> {
            if (consultaRegistroInternalFrame == null || consultaRegistroInternalFrame.isClosed()) {
                consultaRegistroInternalFrame = new ConsultaDeRegistro(IEV, ICU);
                consultaRegistroInternalFrame.setLocation(10, 23);
                consultaRegistroInternalFrame.setClosable(true);
                consultaRegistroInternalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
                desktop.add(consultaRegistroInternalFrame);
            } else {
            	consultaRegistroInternalFrame.recargarDatos();
            }
            ensureSize(consultaRegistroInternalFrame, 600, 400);
            showInternal(consultaRegistroInternalFrame);
        });
        mnRegistros.add(mntmConsultaDeRegistro);



        JMenu mnTipoDeRegistros = new JMenu("Tipos de Registro");
        mnRegistros.add(mnTipoDeRegistros);

        JMenuItem mntmAltaTipoDeRegistro = new JMenuItem("Alta de Tipo de Registro");
        mntmAltaTipoDeRegistro.addActionListener(e -> {
        	if (altaTipoDeRegistroInternalFrame == null || altaTipoDeRegistroInternalFrame.isClosed()) {
        		altaTipoDeRegistroInternalFrame = new AltaDeTipoDeRegistro(IEV);
        	    desktop.add(altaTipoDeRegistroInternalFrame);
      	} 
        	altaTipoDeRegistroInternalFrame.setVisible(true);
        	altaTipoDeRegistroInternalFrame.toFront(); 
            ensureSize(altaTipoDeRegistroInternalFrame, 600, 400);
            showInternal(altaTipoDeRegistroInternalFrame);
        });
        mnTipoDeRegistros.add(mntmAltaTipoDeRegistro);

        JMenuItem mntmConsultaTipoDeRegistro = new JMenuItem("Consulta de Tipo de Registro");
        mntmConsultaTipoDeRegistro.addActionListener(e -> {
            if (consultaDeTipoDeRegistroInternalFrame == null || consultaDeTipoDeRegistroInternalFrame.isClosed()) {
                consultaDeTipoDeRegistroInternalFrame = new ConsultaDeTipoDeRegistro(IEV);
                
                desktop.add(consultaDeTipoDeRegistroInternalFrame);
            } 
            consultaDeTipoDeRegistroInternalFrame.setVisible(true);
            consultaDeTipoDeRegistroInternalFrame.toFront(); 
            ensureSize(consultaDeTipoDeRegistroInternalFrame, 600, 400);
            showInternal(consultaDeTipoDeRegistroInternalFrame);
        });
        mnTipoDeRegistros.add(mntmConsultaTipoDeRegistro);


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
    ConsultaEvento consulta = new ConsultaEvento(ctrlEvento, (ev, ed) -> {
        abrirConsultaEdicionCon(ev, ed);
    });
    consulta.cargarEventos(ctrlEvento.getEventosDTO()); // cargamos los datos
    desktop.add(consulta);   // agregamos al JDesktopPane
    consulta.setVisible(true);   // hacemos visible la ventana
    try {
        consulta.setSelected(true); // lo trae al frente
    } catch (java.beans.PropertyVetoException e) {
        e.printStackTrace();
    }
}

private void abrirConsultaEdicionCon(DataEvento evento, DataEdicion edicion) {
    if (consultaEdicionInternalFrame == null || consultaEdicionInternalFrame.isClosed()) {
        consultaEdicionInternalFrame = new ConsultaEdicionEvento(IEV);
        desktop.add(consultaEdicionInternalFrame);
    }	
    ensureSize(consultaEdicionInternalFrame, 700, 500);
    showInternal(consultaEdicionInternalFrame);
    consultaEdicionInternalFrame.setContext(evento, edicion);
}
}