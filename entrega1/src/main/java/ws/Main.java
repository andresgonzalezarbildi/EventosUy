package ws;

import ws.publicar.EventosWs;
import ws.publicar.MediaWs;
import ws.publicar.UsuarioWs;
import ws.publicar.EstadoServidor;
import presentacion.Principal;

import javax.swing.SwingUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    try {

      String configPath = System.getProperty("user.home")
          + File.separator + ".eventosUy"    
          + File.separator + "servidor_central.properties";

      Properties properties = new Properties();
      properties.load(new FileInputStream(new File(configPath)));


      String protocol = properties.getProperty("Protocol", "http");
      String host = properties.getProperty("Host", "0.0.0.0");
      String port = properties.getProperty("Port", "9128");
      String base = properties.getProperty("Base", "/Servicios");

      String baseURL = protocol + "://" + host + ":" + port + base;

      System.out.println("Publicando servicios en: " + baseURL);


      EventosWs eventoWs = new EventosWs();
      UsuarioWs usuarioWs = new UsuarioWs();
      MediaWs mediaWs = new MediaWs();
      EstadoServidor estadoWs = new EstadoServidor();

      eventoWs.publicar(baseURL + "/EventosWS");
      usuarioWs.publicar(baseURL + "/UsuariosWS");
      mediaWs.publicar(baseURL + "/MediaWS");
      estadoWs.publicar(baseURL + "/EstadoServidorWS");

      System.out.println("Web Services publicados correctamente.");

      SwingUtilities.invokeLater(() -> {
        Principal frame = new Principal();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      });

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Error cargando configuración o publicando WS");
    }
  }
}
