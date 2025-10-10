package usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import excepciones.UsuarioRepetidoException;
import logica.Fabrica;
import logica.interfaces.IControladorUsuario;

@WebServlet("/usuarios/registrar")
@MultipartConfig
public class RegistrarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String nickname = req.getParameter("nick");
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        String password = req.getParameter("password");
        String tipo = req.getParameter("tipo"); // "Organizador" o "Asistente"
        String imagen = req.getParameter("imagen"); // opcional si no se sube archivo

        String descripcion = req.getParameter("descripcion"); // solo organizador
        String link = req.getParameter("link"); // solo organizador

        String apellido = req.getParameter("apellido"); // solo asistente
        String fechaNacStr = req.getParameter("fechaNac"); // solo asistente (yyyy-MM-dd)

        if (nickname == null || nombre == null || correo == null || password == null || tipo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Parámetros obligatorios faltantes\"}");
            return;
        }

        LocalDate fechaNac = null;
        if (fechaNacStr != null && !fechaNacStr.isBlank()) {
            try {
                fechaNac = LocalDate.parse(fechaNacStr);
            } catch (DateTimeParseException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Fecha de nacimiento inválida\"}");
                return;
            }
        }

        try {
            // Manejo de archivo (imagen) si viene en multipart
            try {
                Part filePart = req.getPart("imagen");
                if (filePart != null && filePart.getSize() > 0) {
                    String original = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String extension = "";
                    int dot = original.lastIndexOf('.');
                    if (dot >= 0) extension = original.substring(dot);
                    String uniqueName = UUID.randomUUID().toString().replace("-", "") + extension;

                    String imgDir = "/img/"; 
                    String absoluteTarget = getServletContext().getRealPath(imgDir + uniqueName);
                    Path targetPath = Paths.get(absoluteTarget);
                    Files.createDirectories(targetPath.getParent());
                    try (InputStream in = filePart.getInputStream()) {
                        Files.copy(in, targetPath);
                    }
                    imagen = uniqueName; // guardar solo el nombre para luego resolver ruta pública
                }
            } catch (IllegalStateException | IOException ex) {
                // si falla la subida, continuamos sin imagen personalizada
                System.out.println("Error subiendo imagen: " + ex.getMessage());
            }

            IControladorUsuario cu = Fabrica.getInstance().getControladorUsuario();
            cu.altaUsuario(nickname, nombre, correo, imagen, password, tipo, descripcion, link, apellido, fechaNac);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"ok\":true}");
        } catch (UsuarioRepetidoException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().write("{\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage().replace("\"", "'") + "\"}");
        }
    }
}


