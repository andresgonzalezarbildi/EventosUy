package wz;

import wz.publicar.CargarDatosWs;
import wz.publicar.EventosWs;
import wz.publicar.UsuarioWs;

import javax.swing.SwingUtilities;

import presentacion.Principal;

public class Main {
  public static void main(String[] args) {
    // Publicar Web Services primero
    System.out.println("Iniciando Web Services...");
    EventosWs eventoWs = new EventosWs();
//    UsuarioWs usuarioWs = new UsuarioWs();
//    CargarDatosWs cargarDatosWs = new CargarDatosWs();

    eventoWs.publicar();
//    usuarioWs.publicar();
//    cargarDatosWs.publicar();
    
    System.out.println("Web Services publicados");
    System.out.println("  - EventosWS: http://localhost:9128/Servicios/EventosWS");
    System.out.println("  - UsuariosWS: http://localhost:9128/Servicios/UsuariosWS");
    System.out.println("  - CargarDatosWS: http://localhost:9128/Servicios/CargarDatosWS");
    System.out.println("app swing");
    
    // Iniciar interfaz gráfica
    // Los datos cargados desde Principal se reflejarán en los Web Services
    // porque comparten la misma instancia de controladores (Singleton)
    SwingUtilities.invokeLater(()-> {
      Principal frame = new Principal();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
