	package usuario;
	
	import java.io.IOException;
	import java.io.InputStream;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.nio.file.StandardCopyOption;
	import java.util.ArrayList;	
	import java.util.Collections;
	import java.util.List;
	import java.util.UUID;
	import ws.eventos.EdicionNoExisteFault_Exception;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.*;
	import jakarta.servlet.annotation.MultipartConfig;
	import ws.eventos.EventosService;
	import ws.eventos.EventosWs;
import ws.media.IOException_Exception;
import ws.media.MediaService;
import ws.media.MediaWs;
import ws.usuario.UsuarioWs;
	import ws.usuario.UsuarioNoExisteFault_Exception;
	import ws.usuario.UsuarioService;
	import ws.usuario.DataUsuario;
	
	@WebServlet(name="UsuarioServlet", urlPatterns={"/UsuarioServlet"})
	@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1 MB antes de escribir en disco
	    maxFileSize = 5 * 1024 * 1024,    // 5 MB por archivo
	    maxRequestSize = 20 * 1024 * 1024 // 20 MB total
	)
	public class UsuarioServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    private EventosService serviceEv = new EventosService();
	    private UsuarioService serviceUs = new UsuarioService();
		EventosWs ce = serviceEv.getEventosPort();
		UsuarioWs cu = serviceUs.getUsuarioPort();
		private final MediaService mediaService = new MediaService();
		private final MediaWs mediaPort = mediaService.getMediaPort();
		
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	
	        String ok = (String) req.getSession().getAttribute("usuarioCreado");
	        if (ok != null) {
	            req.setAttribute("usuarioCreado", ok);
	            req.getSession().removeAttribute("usuarioCreado");
	        }
	
	        String op = req.getParameter("op").toLowerCase();
	        switch (op) {
	            case "listar": 
	                listarUsuarios(req, res);
	                break;
	            case "consultar":
	                consultaUsuario(req, res);
	                break;
	            case "alta":
	                req.getRequestDispatcher("/WEB-INF/pages/altaUsuario.jsp").forward(req, res);
	                break;
	            case "modificar":
	                modificarUsuario(req, res);
	                break;
	            default:
	              req.setAttribute("mensajeError", "Le erraste de camino");
                req.setAttribute("javax.servlet.error.status_code", 404);
                req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
	        }
	    }
	
	    
	    
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {

	        log("Entró a doPost con op=" + req.getParameter("op"));
	        String op = req.getParameter("op");
	        if (!"modificar".equalsIgnoreCase(op)) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	            return;
	        }

	        String nick = req.getParameter("nick");
	        if (nick == null || nick.isBlank()) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	            return;
	        }

	        try {
	            ws.usuario.DataUsuario usuario = cu.verInfoUsuario(nick);

	            String nombre = req.getParameter("nombre");
	            if (nombre == null || nombre.isBlank()) nombre = usuario.getNombre();

	            String descripcion = usuario.getDescripcion();
	            String link = usuario.getLink();
	            String apellido = usuario.getApellido();
	            String fechaNac = usuario.getFechaNacimiento();
	            String correo = usuario.getCorreo();

	            // Imagen
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
	                    repoblarFormularioUsuario(req, res, nick, nombre, correo, descripcion, link, apellido, fechaNac);
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
	                    req.setAttribute("error",
	                            "El archivo seleccionado no es una imagen válida o ocurrió un error al subirla.");
	                	repoblarFormularioUsuario(req, res, nick, nombre, correo, descripcion, link, apellido, fechaNac);
	                	return;
	                }
	            }
	            
	            String newPassword = req.getParameter("newPassword");
	            String confirmPassword = req.getParameter("confirmPassword");
	         	if (newPassword != null && !newPassword.isBlank() && newPassword.equals(confirmPassword)) {
		              cu.cambiarContrasenia(nick, newPassword);
	          }

	            if ("Organizador".equalsIgnoreCase(usuario.getTipo())) {
	                String descParam = req.getParameter("descripcion");
	                if (descParam != null && !descParam.isBlank()) descripcion = descParam;

	                String linkParam = req.getParameter("link");
	                if (linkParam != null && !linkParam.isBlank()) link = linkParam;

	            } else { // Asistente
	                String apellidoParam = req.getParameter("apellido");
	                if (apellidoParam != null && !apellidoParam.isBlank()) apellido = apellidoParam;

	                String fechaParam = req.getParameter("fechaNac");
	                if (fechaParam != null && !fechaParam.isBlank()) {
	                   fechaNac = fechaParam;
	                   
	            }}

	            log("Modificando usuario: " + nick + ", nombre=" + nombre + ", imagen=" + imagen);
	            cu.modificarUsuario(nick, nombre, descripcion, imagen, link, apellido, fechaNac);	
	         // Actualizar imagen en la sesión para que el header la muestre
	            req.getSession().setAttribute("imagen", imagen);

	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=consultar&nick=" + nick);

	        } catch (UsuarioNoExisteFault_Exception e) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	        }
	    }



	
	
	
	
	    private void listarUsuarios(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        List<DataUsuario> usuariosArray = null;
	        try {
	            usuariosArray = cu.getUsuarios();
	            System.out.println("Usuarios obtenidos: " + usuariosArray);
	        } catch (UsuarioNoExisteFault_Exception e) {
	            System.out.println("No hay usuarios cargados: " + e.getMessage());
	        }
	        req.setAttribute("usuarios", usuariosArray);
	        req.getRequestDispatcher("/WEB-INF/pages/usuarios.jsp").forward(req, res);
	    }

	
	    private void consultaUsuario(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	    
	        String nick = req.getParameter("nick");
	        if (nick == null || nick.isEmpty()) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	            return;
	        }
	
	        try {
	            ws.usuario.DataUsuario usuario = cu.verInfoUsuario(nick);
	            req.setAttribute("usuario", usuario);
	            String nickLogueado = (String) req.getSession().getAttribute("usuario");
	            if ("Organizador".equalsIgnoreCase(usuario.getTipo())) {
	            	List<ws.eventos.DataEdicion> eds;
	                try {
	                    if (nickLogueado != null && nick.equalsIgnoreCase(nickLogueado)) {
	                        eds = ce.listarEdicionesOrganizador(nick);
	                    } else {
	                        eds = ce.listarEdicionesOrganizadorAceptadas(nick);
	                    }
	                } catch (EdicionNoExisteFault_Exception e) {
	                    eds = new ArrayList<>();
	                }
	                req.setAttribute("ediciones", eds);
	
	            } else if ("Asistente".equalsIgnoreCase(usuario.getTipo())
	                       && nick.equalsIgnoreCase(nickLogueado)) {
	                try {
	                    req.setAttribute("registros", ce.listarRegistrosDeUsuario(nick));
	                } catch (Exception e) {
	                    req.setAttribute("registros", Collections.emptyList());
	                }
	            }
	
	            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
	
	        } catch (UsuarioNoExisteFault_Exception e) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	        }
	    }
	
	    private void modificarUsuario(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	
	        String nick = req.getParameter("nick");
	        if (nick == null) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	            return;
	        }
	
	        try {
	            ws.usuario.DataUsuario usuario = cu.verInfoUsuario(nick);
	            req.setAttribute("usuario", usuario);
	            req.getRequestDispatcher("/WEB-INF/pages/modificarUsuario.jsp").forward(req, res);
	        } catch (UsuarioNoExisteFault_Exception e) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	        }
	    }
	    
	    private void repoblarFormularioUsuario(HttpServletRequest req, HttpServletResponse res,
	            String nick, String nombre, String correo,
	            String descripcion, String link, String apellido,
	            String fechaNac) throws ServletException, IOException {
	        req.setAttribute("form_nick", nick);
	        req.setAttribute("form_nombre", nombre);
	        req.setAttribute("form_correo", correo);
	        req.setAttribute("form_descripcion", descripcion);
	        req.setAttribute("form_link", link);
	        req.setAttribute("form_apellido", apellido);
	        req.setAttribute("form_fechaNacimiento", fechaNac);
	        req.getRequestDispatcher("/WEB-INF/pages/modificarUsuario.jsp").forward(req, res);
	    }

	}

	