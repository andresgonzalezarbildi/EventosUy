package ws;

import ws.publicar.EventosWs;
import ws.publicar.UsuarioWs;

import javax.swing.SwingUtilities;

import presentacion.Principal;

public class Main {
  public static void main(String[] args) {

    System.out.println("Iniciando Web Services...");
    
    EventosWs eventoWs = new EventosWs();

    UsuarioWs usuarioWs = new UsuarioWs();
    
    System.out.println("Web Services publicados");
    
    eventoWs.publicar();
    System.out.println("  - EventosWS: http://localhost:9128/Servicios/EventosWS");
    
    usuarioWs.publicar();
    System.out.println("  - UsuariosWS: http://localhost:9128/Servicios/UsuariosWS");
    
    SwingUtilities.invokeLater(()-> {
      Principal frame = new Principal();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
    
    System.out.println("app swing");
  }
}
