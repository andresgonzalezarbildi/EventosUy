package usuario;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import excepciones.EdicionNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import logica.interfaces.IControladorUsuario;
import logica.interfaces.IControladorEvento;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataUsuario;
import logica.Fabrica;




@WebServlet(name="UsuarioServlet", urlPatterns={"/UsuarioServlet"})
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
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible en el GET.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
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
    	
    	
    	  System.out.println("🚀 Entré a consultaUsuario()");
    	
    	
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
                        // Es el propio organizador
                        eds = ce.listarEdicionesOrganizador(nick);
                    } else {
                        // Visitante o cualquier otro usuario
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

            
            String jspPath = "/WEB-INF/pages/consultaUsuario.jsp";
            if (getServletContext().getResourceAsStream(jspPath) == null) {
                System.out.println("⚠️ El JSP no se encuentra en: " + jspPath);
            } else {
                System.out.println("✅ JSP encontrado correctamente");
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
            throw new RuntimeException("💥 Llegué al forward");

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
