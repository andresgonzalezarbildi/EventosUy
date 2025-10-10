package usuario;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import excepciones.EventoNoExisteException;
import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import logica.controladores.ControladorEvento;
import logica.datatypes.DataEvento;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;
import logica.Fabrica;
// import logica.controladores.ControladorUsuario; // o fábrica de controladores

@WebServlet(name="UsuarioServlet", urlPatterns={"/UsuarioServlet"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,        // 1 MB en memoria
    maxFileSize       = 5L * 1024 * 1024,   // 5 MB por archivo
    maxRequestSize    = 25L * 1024 * 1024   // 25 MB total
)
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorUsuario cu = fabrica.getControladorUsuario();

    @Override
    public void init() throws ServletException {
        super.init();
        // TODO: obtené el controlador desde tu fábrica:
        // this.cu = Fabrica.getIControladorUsuario();
        // Temporal (si tenés impl concreta):
        // this.cu = new ControladorUsuario();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html; charset=UTF-8");

    	String op = p(req.getParameter("op")).toLowerCase();
        switch (op) {
            case "listar": 
            	listarUsuarios(req, res);
            	break;
            	
            case "consultar":
            	consultaUsuario(req, res);
            	break;
            default:
            	res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible por GET.");
        }
    }
 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");
    	res.setContentType("text/html; charset=UTF-8");

    	String op = p(req.getParameter("op")).toLowerCase();
        switch (op) {
            case "alta":
                altaUsuario(req, res);
                break;
            case "listar": 
            	listarUsuarios(req, res);
            	break;
            	
            case "consultar": 
            	consultaUsuario(req, res);
            	break;
            default:
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación inválida.");
        }
    }

    /** --- ALTA DE USUARIO (con subida de imagen) --- */
    private void altaUsuario(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("📢 Entró en Alta del UsuarioServlet");

        final String tipo     = p(req.getParameter("tipo"));       // "Organizador" | "Asistente"
        final String nombre   = p(req.getParameter("nombre"));
        final String nick     = p(req.getParameter("nick"));
        final String correo   = p(req.getParameter("correo"));
        final String password = p(req.getParameter("password"));
        final String pass2    = p(req.getParameter("confirmPassword"));

        // Validaciones mínimas
        if (nick.isBlank() || nombre.isBlank() || correo.isBlank() || password.isBlank() || !password.equals(pass2)) {
            req.setAttribute("error", "Datos inválidos o contraseñas no coinciden.");
            volverAFormTipo(req, res, tipo);   // forward al formulario correspondiente
            return;                             // 👈 importante: cortar acá
        }

        // Subida de imagen (opcional)
        String nombreImagenGuardada = null;
        Part filePart = null;
        try { filePart = req.getPart("imagen"); } catch (Exception ignore) {}
        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                req.setAttribute("error", "El archivo debe ser una imagen válida.");
                volverAFormTipo(req, res, tipo);
                return;
            }

            String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String ext = "";
            int dot = submitted.lastIndexOf('.');
            if (dot >= 0 && dot < submitted.length() - 1) {
                ext = submitted.substring(dot); // ".png", ".jpg", etc.
            }

            String nuevoNombre = UUID.randomUUID().toString().replace("-", "") + ext;

            // Preferir guardar dentro del WAR desplegado (exploded)
            String uploadDirPath = getServletContext().getRealPath("/uploads");
            if (uploadDirPath == null) {
                // Si es null, no podemos servir luego esa imagen por /uploads
                req.setAttribute("error", "No se pudo resolver la carpeta /uploads del servidor.");
                volverAFormTipo(req, res, tipo);
                return;
            }

            Path uploadDir = Paths.get(uploadDirPath);
            Files.createDirectories(uploadDir);

            Path destino = uploadDir.resolve(nuevoNombre);
            try (InputStream in = filePart.getInputStream()) {
                Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
            }
            nombreImagenGuardada = nuevoNombre;
        }

        // Campos según tipo
        String descripcion = null;
        String link        = null;
        String apellido    = null;
        LocalDate fechaNac = null;

        if ("Organizador".equalsIgnoreCase(tipo)) {
            descripcion = v(req.getParameter("descripcion"));
            link        = v(req.getParameter("link"));
        } else if ("Asistente".equalsIgnoreCase(tipo)) {
            apellido    = v(req.getParameter("apellido"));
            fechaNac    = parseFecha(req.getParameter("fechaNacimiento")); // yyyy-MM-dd
        } else {
            req.setAttribute("error", "Tipo de usuario inválido.");
            volverAFormTipo(req, res, tipo);
            return;
        }

        try {
            cu.altaUsuario(
                nick,                // nickname
                nombre,              // nombre
                correo,              // correo
                nombreImagenGuardada,// imagen (puede ser null)
                password,            // password
                tipo,                // tipo
                descripcion,         // descripcion (o null)
                link,                // link (o null)
                apellido,            // apellido (o null)
                fechaNac             // fechaNac (o null)
            );

            // Éxito → redirigir a la lista (acción del servlet)
            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");

        } catch (Exception e) {
            req.setAttribute("error", "No se pudo dar de alta: " + e.getMessage());
            volverAFormTipo(req, res, tipo);
            return; // 👈 importante
        }
    }


    /** Helpers */
    private static String p(String s) { return s == null ? "" : s.trim(); }
    private static String v(String s) { s = p(s); return s.isEmpty() ? null : s; }

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
    
    private void listarUsuarios(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	DataUsuario[] usuariosArray = null;
        try {
            usuariosArray = cu.getUsuarios();
        } catch (UsuarioNoExisteException e) {
            System.out.println("No hay usuarios cargados: " + e.getMessage());
        }

        // Mandamos la lista al JSP
        req.setAttribute("usuarios", usuariosArray);

        // Redirigimos al index.jsp para mostrar los usuarios
        req.getRequestDispatcher("/pages/usuarios.jsp").forward(req, res);
    }  
    
    private void consultaUsuario(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String nick = p(req.getParameter("nick"));
        if (nick.isEmpty()) {
            req.setAttribute("error", "Debe ingresar un usuario a consultar.");
            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
            return;
        }

        try {
            DataUsuario usuario = null;

            // Probamos primero con asistente
            try {
                usuario = cu.getAsistente(nick);
            } catch (UsuarioNoExisteException ignored) {}

            // Si no existe, probamos con organizador
            if (usuario == null) {
                try {
                    usuario = cu.getOrganizador(nick);
                } catch (UsuarioNoExisteException ignored) {}
            }

            if (usuario == null) {
                throw new UsuarioNoExisteException("Usuario no encontrado.");
            }

            req.setAttribute("usuario", usuario);
            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);

        } catch (UsuarioNoExisteException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
        }
    }

    
}
