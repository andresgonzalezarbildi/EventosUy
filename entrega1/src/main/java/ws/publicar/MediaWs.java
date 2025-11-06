package ws.publicar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;

@WebService(serviceName = "MediaService", portName = "MediaPort")
public class MediaWs {
  
  private Endpoint endpoint = null;
  
  @WebMethod(exclude = true)
  public void publicar(String url) {
    endpoint = Endpoint.publish(url, this);
  }

  @WebMethod(exclude = true)
  public Endpoint getEndpoint() {
      return endpoint;
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


    Path dir = Paths.get("img");
    Files.createDirectories(dir);


    String ext = "";
    int dot = nombreArchivo.lastIndexOf('.');
    if (dot >= 0 && dot < nombreArchivo.length() - 1) {
      ext = nombreArchivo.substring(dot).toLowerCase();
    }
    String[] ALLOWED_EXT = { ".jpg", ".jpeg", ".png", ".gif", ".webp" };
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
  
  public byte[] obtenerImagen(@WebParam(name = "nombreArchivo") String nombreArchivo) throws IOException {
    if (nombreArchivo == null || nombreArchivo.isBlank()) {
        throw new IOException("Nombre de archivo inválido");
    }

    Path dir = Paths.get("img");
    Path file = dir.resolve(nombreArchivo).normalize();

    if (!file.startsWith(dir)) {
        throw new IOException("Acceso no permitido");
    }
    if (!Files.exists(file)) {
        throw new IOException("Archivo no encontrado: " + nombreArchivo);
    }
    return Files.readAllBytes(file);
  }
}