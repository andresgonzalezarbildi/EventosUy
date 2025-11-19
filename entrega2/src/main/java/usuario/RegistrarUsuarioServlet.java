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
import java.util.UUID;

import ws.media.IOException_Exception;
import ws.media.MediaService;
import ws.media.MediaWs;
import ws.usuario.UsuarioRepetidoFault_Exception;
import ws.usuario.UsuarioService;
import ws.usuario.UsuarioWs;

@WebServlet("/usuarios/registrar")
@MultipartConfig
public class RegistrarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UsuarioService serviceUs = new UsuarioService();
	UsuarioWs cu = serviceUs.getUsuarioPort();
	private final MediaService mediaService = new MediaService();
	private final MediaWs mediaPort = mediaService.getMediaPort();
    
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
              res.sendRedirect(req.getContextPath()+"/eventos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String tipoUsuario = req.getParameter("tipo").toLowerCase();
        if ("organizador".equalsIgnoreCase(tipoUsuario) || "asistente".equalsIgnoreCase(tipoUsuario)) {
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
            repoblarFormulario(req, tipo, nombre, nick, correo, 
                    req.getParameter("apellido"),
                    req.getParameter("descripcion"),
                    req.getParameter("link"),
                    req.getParameter("fechaNacimiento"));
            volverAFormTipo(req, res, tipo);
            return;
        }


        // subir imagen
        String imagen = null;
        Part filePart = null;
        try {
            filePart = req.getPart("imagen");
        } catch (Exception ignore) {
        }

        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();

            if (contentType == null || !contentType.startsWith("image/")) {
                req.setAttribute("error",
                        "El archivo seleccionado no es una imagen válida. Solo se permiten JPG, PNG, GIF o WEBP.");
                repoblarFormulario(req, tipo, nombre, nick, correo, req.getParameter("apellido"), req.getParameter("descripcion"), req.getParameter("link"), req.getParameter("fechaNacimiento"));
                volverAFormTipo(req, res, tipo);
                return;
            }

            String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            byte[] bytes;
            try (InputStream in = filePart.getInputStream()) {
                bytes = in.readAllBytes();
            }

            try {
                imagen = mediaPort.subirImagen(submitted, bytes);
            } catch (IOException_Exception ex) {
                req.setAttribute("error", "El archivo seleccionado no es una imagen válida o ocurrió un error al subirla.");
                repoblarFormulario(req, tipo, nombre, nick, correo, req.getParameter("apellido"), req.getParameter("descripcion"), req.getParameter("link"), req.getParameter("fechaNacimiento"));
                volverAFormTipo(req, res, tipo);
                return;
            }
        }

        String descripcion = null;
        String link        = null;
        String apellido    = null;
        String fechaNac    = null;

        if ("Organizador".equalsIgnoreCase(tipo)) {
            descripcion = req.getParameter("descripcion");
            link        = req.getParameter("link");
        } else if ("Asistente".equalsIgnoreCase(tipo)) {
            apellido    = req.getParameter("apellido");
            fechaNac    = req.getParameter("fechaNacimiento");
            if (fechaNac == null) {
                req.setAttribute("error", "La fecha de nacimiento es obligatoria para asistentes.");
                repoblarFormulario(req, tipo, nombre, nick, correo, apellido, descripcion, link, fechaNac);
                volverAFormTipo(req, res, tipo);
                return;
            }
            if (fechaNac != null && !fechaNac.isBlank()) {
                try {
                    LocalDate f = LocalDate.parse(fechaNac); 
                    if (f.isAfter(LocalDate.now())) {
                        req.setAttribute("error", "La fecha de nacimiento no puede ser futura.");
                        repoblarFormulario(req, tipo, nombre, nick, correo, apellido, descripcion, link, fechaNac);
                        volverAFormTipo(req, res, tipo);
                        return;
                    }
                } catch (Exception e) {
                    req.setAttribute("error", "Formato de fecha inválido. Use AAAA-MM-DD.");
                    repoblarFormulario(req, tipo, nombre, nick, correo, apellido, descripcion, link, fechaNac);
                    volverAFormTipo(req, res, tipo);
                    return;
                }
            }


        } else {
            req.setAttribute("error", "Tipo de usuario inválido.");
            repoblarFormulario(req, tipo, nombre, nick, correo, apellido, descripcion, link, fechaNac);
            volverAFormTipo(req, res, tipo);
            return;
        }

        try {
        	if (fechaNac != null && fechaNac.isBlank()) fechaNac = null;
        	if (descripcion != null && descripcion.isBlank()) descripcion = null;
        	if (link != null && link.isBlank()) link = null;
        	if (apellido != null && apellido.isBlank()) apellido = null;
        	
            cu.altaUsuario(
                nick, nombre, correo, imagen, password,
                tipo, descripcion, link, apellido, fechaNac
            );
            req.getSession().setAttribute("usuarioCreado", "Usuario creado");
            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");

        } catch (UsuarioRepetidoFault_Exception e) {
            req.setAttribute("error", e.getMessage());
            repoblarFormulario(
                req,
                tipo,
                nombre,
                nick,
                correo,
                req.getParameter("apellido"),
                req.getParameter("descripcion"),
                req.getParameter("link"),
                req.getParameter("fechaNacimiento")
            );
            volverAFormTipo(req, res, tipo);


        } catch (Exception e) {
            req.setAttribute("error", "No se pudo dar de alta: " + e.getMessage());
            repoblarFormulario(
                req,
                tipo,
                nombre,
                nick,
                correo,
                req.getParameter("apellido"),
                req.getParameter("descripcion"),
                req.getParameter("link"),
                req.getParameter("fechaNacimiento")
            );
            volverAFormTipo(req, res, tipo);

        }
    }

    /** Helpers */
//    private static LocalDate parseFecha(String iso) {
//        try { return (iso == null || iso.isBlank()) ? null : LocalDate.parse(iso); }
//        catch (Exception e) { return null; }
//    }

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

    private void repoblarFormulario(HttpServletRequest req, String tipo, String nombre, String nick,
            String correo, String apellido, String descripcion,
            String link, String fechaNac) {
        req.setAttribute("form_tipo", tipo);
        req.setAttribute("form_nombre", nombre);
        req.setAttribute("form_nick", nick);
        req.setAttribute("form_correo", correo);
        req.setAttribute("form_apellido", apellido);
        req.setAttribute("form_descripcion", descripcion);
        req.setAttribute("form_link", link);
        req.setAttribute("form_fechaNacimiento", fechaNac);
    }

}
