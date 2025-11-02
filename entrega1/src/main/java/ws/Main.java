package ws;

import ws.publicar.EventosWs;
import ws.publicar.MediaWs;
import ws.publicar.UsuarioWs;
import ws.publicar.EstadoServidor;

import javax.swing.SwingUtilities;
import presentacion.Principal;

public class Main {
  public static void main(String[] args) {

    System.out.println("Iniciando Web Services...");
    EventosWs eventoWs = new EventosWs();

    UsuarioWs usuarioWs = new UsuarioWs();
    
    MediaWs mediaWs = new MediaWs();
    
    EstadoServidor estadoWs = new EstadoServidor();
    
    
    System.out.println("Web Services publicados");


    eventoWs.publicar();
    System.out.println("  - EventosWS: http://localhost:9128/Servicios/EventosWS");
    usuarioWs.publicar();
    System.out.println("  - UsuariosWS: http://localhost:9128/Servicios/UsuariosWS");
    estadoWs.publicar();
    System.out.println("  - EstadoWs: http://localhost:9128/Servicios/EstadoServidorWS");
    mediaWs.publicar();
    System.out.println("  - MediaWS: http://localhost:9128/Servicios/MediaWs");


    System.out.println("Web Services publicados correctamente");

    System.out.println("Abriendo aplicación Swing...");
    SwingUtilities.invokeLater(() -> {
      Principal frame = new Principal();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
    
    System.out.println("app swing");
  }
}
