package ws.publicar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

@WebService(serviceName = "MediaService", portName = "MediaPort")
public class MediaWs {
  
  private Endpoint endpoint = null;
  
  private static final String[] ALLOWED_EXT = { ".jpg", ".jpeg", ".png", ".gif", ".webp" };
  
  @WebMethod(exclude = true)
  public void publicar(String url) {
    endpoint = Endpoint.publish(url, this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
      return endpoint;
  }
  
  private static volatile Path uploadDir;

  private static Path resolveUploadDir() throws IOException {
    if (uploadDir != null) return uploadDir;

    synchronized (MediaWs.class) {
      if (uploadDir != null) return uploadDir;
      
      String propertie = System.getProperty("user.home")
          + File.separator + ".eventosUy"    
          + File.separator + "servidor_central.properties";
      
      Path cfg = Paths.get(propertie);
      if (!Files.exists(cfg)) {
        throw new IOException("No existe el archivo de configuración: " + cfg);
      }

      Properties p = new Properties();
      try (InputStream in = Files.newInputStream(cfg)) {
        p.load(in);
      }

      String raw = p.getProperty("URIFolderImagenes");
      if (raw == null || raw.isBlank()) {
        throw new IOException("URIFolderImagenes no está definido en " + cfg);
      }

      Path dir = Paths.get(raw).toAbsolutePath().normalize();
      Files.createDirectories(dir);
      if (!Files.isDirectory(dir) || !Files.isWritable(dir)) {
        throw new IOException("URIFolderImagenes no es un directorio escribible: " + dir);
      }

      uploadDir = dir;
      return uploadDir;
    }
  }
  
  private static byte[] readBaseFromClasspath(String nombre) throws IOException {
    try (InputStream in = MediaWs.class.getResourceAsStream("/img/" + nombre)) {
      if (in == null) return null;
      return in.readAllBytes();
    }
  }
  
  
  @WebMethod
  public String subirImagen(
      @WebParam(name = "nombreArchivo") String nombreArchivo,
      @WebParam(name = "imagenBytes") byte[] imagenBytes) throws IOException {

    if (imagenBytes == null || imagenBytes.length == 0) {
      throw new IOException("Imagen vacía.");
    }
    if (nombreArchivo == null || nombreArchivo.isBlank()) {
      throw new IOException("Nombre de archivo inválido.");
    }


    Path dir = resolveUploadDir();    


    String ext = "";
    int dot = nombreArchivo.lastIndexOf('.');
    if (dot >= 0 && dot < nombreArchivo.length() - 1) {
      ext = nombreArchivo.substring(dot).toLowerCase();
    }
    boolean permitido = false;
    for (String ok : ALLOWED_EXT) {
      if (ok.equals(ext)) { permitido = true; break; }
    }
    if (!permitido) {
      throw new IOException("Extensión de imagen no permitida: " + ext);
    }


    String fileName = java.util.UUID.randomUUID().toString().replace("-", "") + ext;
    Path destino = dir.resolve(fileName).normalize();

 
    if (!destino.startsWith(dir)) {
      throw new IOException("Ruta de destino inválida.");
    }


    Files.write(destino, imagenBytes);

    return fileName;
  }
  
  @WebMethod
  public byte[] obtenerImagen(@WebParam(name = "nombreArchivo") String nombreArchivo) throws IOException {
    if (nombreArchivo == null || nombreArchivo.isBlank()) {
        throw new IOException("Nombre de archivo inválido");
    }

    Path dir = resolveUploadDir();
    Path file = dir.resolve(nombreArchivo).normalize();
    if (file.startsWith(dir) && Files.exists(file) && Files.isRegularFile(file)) {
      return Files.readAllBytes(file);
    }

    // 2) Fallback a bases dentro del JAR (classpath:/img/...)
    byte[] inJar = readBaseFromClasspath(nombreArchivo);
    if (inJar != null) return inJar;

    throw new IOException("Archivo no encontrado: " + nombreArchivo);
  }
}