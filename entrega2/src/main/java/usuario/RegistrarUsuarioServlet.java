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
import java.nio.file.StandardCopyOption;
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
    
    private final Fabrica fabrica = Fabrica.getInstance();
    private final IControladorUsuario cu = fabrica.getControladorUsuario();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getParameter("tipo").toLowerCase();
        switch (op) {
            case "organizador":
            	req.getRequestDispatcher("/WEB-INF/pages/altaUsuarioOrganizador.jsp").forward(req, res);
            	break;
        	case "asistente":
            	req.getRequestDispatcher("/WEB-INF/pages/altaUsuarioAsistente.jsp").forward(req, res);
            	break;
            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "El tipo Elegido no es valido.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String tipoUsuario = req.getParameter("tipo").toLowerCase();
    	if("organizador".equalsIgnoreCase(tipoUsuario) || "asistente".equalsIgnoreCase(tipoUsuario)) {
    		altaUsuario(req, res);
    	} else {
    		res.sendError(HttpServletResponse.SC_NOT_FOUND, "El tipo Elegido no es valido.");
    	}
    }
    
    private void altaUsuario(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        final String tipo     = req.getParameter("tipo");      
        final String nombre   = req.getParameter("nombre");
        final String nick     = req.getParameter("nick");
        final String correo   = req.getParameter("correo");
        final String password = req.getParameter("password");
        final String pass2    = req.getParameter("confirmPassword");

        if (nick.isBlank() || nombre.isBlank() || correo.isBlank()
                || password.isBlank() || !password.equals(pass2)) {
            req.setAttribute("error", "Datos inválidos o contraseñas no coinciden.");
            volverAFormTipo(req, res, tipo);
            return;
        }

        // subir imagen
        String nombreImagenGuardada = null;
        Part filePart = null;
        try {
            filePart = req.getPart("imagen"); // nombre de l aimagen
        } catch (IllegalStateException ex) {
            req.setAttribute("error", "error al subir la imagen");
            volverAFormTipo(req, res, tipo);
            return;
        } catch (ServletException | IOException ex) {
            filePart = null;
        }

        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ext = "";
                int dot = submitted.lastIndexOf('.');
                if (dot >= 0 && dot < submitted.length() - 1) ext = submitted.substring(dot).toLowerCase();
                String nuevoNombre = UUID.randomUUID().toString().replace("-", "") + ext;

                String imgDirPath = getServletContext().getRealPath("/img"); // <- carpeta pública
                if (imgDirPath != null) {
                    Path imgDir = Paths.get(imgDirPath);
                    Files.createDirectories(imgDir);
                    Path destino = imgDir.resolve(nuevoNombre);
                    try (InputStream in = filePart.getInputStream()) {
                        Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
                    }
                    nombreImagenGuardada = nuevoNombre;
                } 
            }
        }

        String descripcion = null;
        String link        = null;
        String apellido    = null;
        LocalDate fechaNac = null;

        if ("Organizador".equalsIgnoreCase(tipo)) {
            descripcion = req.getParameter("descripcion");
            link        = req.getParameter("link");
        } else if ("Asistente".equalsIgnoreCase(tipo)) {
            apellido    = req.getParameter("apellido");
            fechaNac    = parseFecha(req.getParameter("fechaNacimiento"));
            if ("Asistente".equalsIgnoreCase(tipo) && fechaNac == null) {
                req.setAttribute("error", "La fecha de nacimiento es obligatoria para asistentes.");
                volverAFormTipo(req, res, tipo);
                return;
            }

        } else {
            req.setAttribute("error", "Tipo de usuario inválido.");
            volverAFormTipo(req, res, tipo);
            return;
        }

        try {
            cu.altaUsuario(
                nick, nombre, correo, nombreImagenGuardada, password,
                tipo, descripcion, link, apellido, fechaNac
            );

            // Éxito → ir a listar (o donde vos quieras)
            req.getSession().setAttribute("usuarioCreado", "Usuario creado");
            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");


        } catch (UsuarioRepetidoException e) {
            req.setAttribute("error", e.getMessage());
            volverAFormTipo(req, res, tipo);

        } catch (Exception e) {
            req.setAttribute("error", "No se pudo dar de alta: " + e.getMessage());
            volverAFormTipo(req, res, tipo);
        }
    }

    /** Helpers */
    private static LocalDate parseFecha(String iso) {
        try { return (iso == null || iso.isBlank()) ? null : LocalDate.parse(iso); }
        catch (Exception e) { return null; }
    }

    private void volverAFormTipo(HttpServletRequest req, HttpServletResponse res, String tipo)
            throws ServletException, IOException {
        if ("Organizador".equalsIgnoreCase(tipo)) {
            req.getRequestDispatcher("/WEB-INF/pages/altaUsuarioOrganizador.jsp").forward(req, res);
        } else if ("Asistente".equalsIgnoreCase(tipo)) {
            req.getRequestDispatcher("/WEB-INF/pages/altaUsuarioAsistente.jsp").forward(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/WEB-INF/pages/altausuario.jsp");
        }
    }
}


