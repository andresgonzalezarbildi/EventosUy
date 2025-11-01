package ws;

import ws.publicar.EventosWs;
import ws.publicar.UsuarioWs;
import ws.publicar.EstadoServidor;

import jakarta.xml.ws.Endpoint;
import javax.swing.SwingUtilities;
import presentacion.Principal;

public class Main {
  public static void main(String[] args) {

    System.out.println("Iniciando Web Services...");

    // Tus WS actuales
    EventosWs eventoWs = new EventosWs();
    UsuarioWs usuarioWs = new UsuarioWs();

    eventoWs.publicar();
    usuarioWs.publicar();

    // 🆕 Nuevo WS: Estado del Servidor
    String urlEstado = "http://localhost:9128/Servicios/EstadoServidorWS";
    Endpoint.publish(urlEstado, new EstadoServidor());
    System.out.println("  - EstadoServidorWS: " + urlEstado);

    System.out.println("Web Services publicados correctamente");
    System.out.println("  - EventosWS: http://localhost:9128/Servicios/EventosWS");
    System.out.println("  - UsuariosWS: http://localhost:9128/Servicios/UsuariosWS");

    // (opcional) interfaz Swing
    System.out.println("Abriendo aplicación Swing...");
    SwingUtilities.invokeLater(() -> {
      Principal frame = new Principal();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
