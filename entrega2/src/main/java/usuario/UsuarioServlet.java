	package usuario;
	
	import java.io.IOException;
	import java.io.InputStream;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.nio.file.StandardCopyOption;
	import java.time.LocalDate;
	import java.util.Arrays;
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
	import ws.usuario.UsuarioWs;
	import ws.usuario.UsuarioNoExisteFault_Exception;
	import ws.usuario.UsuarioService;
	import logica.interfaces.IControladorEvento;
	import ws.eventos.DataEdicion;
	import ws.usuario.DataUsuario;
	import logica.Fabrica;
	
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

	            // Imagen
	            Part filePart = req.getPart("imagen");
	            String imagen = usuario.getImagen();
	            if (filePart != null && filePart.getSize() > 0) {
	                String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	                String ext = "";
	                int dot = submitted.lastIndexOf('.');
	                if (dot >= 0 && dot < submitted.length() - 1) ext = submitted.substring(dot).toLowerCase();
	                String nuevoNombre = UUID.randomUUID().toString().replace("-", "") + ext;

	                String imgDirPath = getServletContext().getRealPath("/img");
	                if (imgDirPath != null) {
	                    Path imgDir = Paths.get(imgDirPath);
	                    Files.createDirectories(imgDir);
	                    Path destino = imgDir.resolve(nuevoNombre);
	                    try (InputStream in = filePart.getInputStream()) {
	                        Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
	                        imagen = nuevoNombre;
	                    }
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
	                    eds = null;
	                }
	                req.setAttribute("ediciones", Arrays.asList(eds));
	
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
	}
