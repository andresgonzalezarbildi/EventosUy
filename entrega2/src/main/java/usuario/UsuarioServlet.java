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
	import java.util.UUID;
	
	import excepciones.EdicionNoExisteException;
	import excepciones.UsuarioNoExisteException;
	import excepciones.UsuarioRepetidoException;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.*;
	import jakarta.servlet.annotation.MultipartConfig;
	import logica.interfaces.IControladorUsuario;
	import logica.interfaces.IControladorEvento;
	import logica.datatypes.DataEdicion;
	import logica.datatypes.DataUsuario;
	import logica.Fabrica;
	
	@WebServlet(name="UsuarioServlet", urlPatterns={"/UsuarioServlet"})
	@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1 MB antes de escribir en disco
	    maxFileSize = 5 * 1024 * 1024,    // 5 MB por archivo
	    maxRequestSize = 20 * 1024 * 1024 // 20 MB total
	)
	public class UsuarioServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    private final Fabrica fabrica = Fabrica.getInstance();
	    private final IControladorUsuario cu = fabrica.getControladorUsuario();
	    private final IControladorEvento ce = fabrica.getControladorEvento();
	
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
	              req.getRequestDispatcher("/eventos").forward(req, res);
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
	            DataUsuario usuario = cu.verInfoUsuario(nick);

	            String nombre = req.getParameter("nombre");
	            if (nombre == null || nombre.isBlank()) nombre = usuario.getNombre();

	            String descripcion = usuario.getDescripcion();
	            String link = usuario.getLink();
	            String apellido = usuario.getApellido();
	            LocalDate fechaNac = usuario.getFechaNacimiento();

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
	                   fechaNac = LocalDate.parse(fechaParam);
	            }}

	            log("Modificando usuario: " + nick + ", nombre=" + nombre + ", imagen=" + imagen);

	            cu.modificarUsuario(nick, nombre, descripcion, imagen, link, apellido, fechaNac);
	         // Actualizar imagen en la sesión para que el header la muestre
	            req.getSession().setAttribute("imagen", imagen);

	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=consultar&nick=" + nick);

	        } catch (UsuarioNoExisteException e) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	        }
	    }



	
	
	
	
	
	    private void listarUsuarios(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        DataUsuario[] usuariosArray = null;
	        try {
	            usuariosArray = cu.getUsuarios();
	        } catch (UsuarioNoExisteException e) {
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
	            DataUsuario usuario = cu.verInfoUsuario(nick);
	            req.setAttribute("usuario", usuario);
	
	            String nickLogueado = (String) req.getSession().getAttribute("usuario");
	
	            if ("Organizador".equalsIgnoreCase(usuario.getTipo())) {
	                DataEdicion[] eds;
	                try {
	                    if (nickLogueado != null && nick.equalsIgnoreCase(nickLogueado)) {
	                        eds = ce.listarEdicionesOrganizador(nick);
	                    } else {
	                        eds = ce.listarEdicionesOrganizadorAceptadas(nick);
	                    }
	                } catch (EdicionNoExisteException e) {
	                    eds = new DataEdicion[0];
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
	
	        } catch (UsuarioNoExisteException e) {
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
	            DataUsuario usuario = cu.verInfoUsuario(nick);
	            req.setAttribute("usuario", usuario);
	            req.getRequestDispatcher("/WEB-INF/pages/modificarUsuario.jsp").forward(req, res);
	        } catch (UsuarioNoExisteException e) {
	            res.sendRedirect(req.getContextPath() + "/UsuarioServlet?op=listar");
	        }
	    }
	}
