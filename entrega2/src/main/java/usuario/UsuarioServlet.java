package usuario;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.UUID;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import logica.interfaces.IControladorUsuario;
import logica.datatypes.DataUsuario;
import logica.Fabrica;

//import logica.clases.Asistente;
//import logica.clases.Organizador;


@WebServlet(name="UsuarioServlet", urlPatterns={"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Fabrica fabrica = Fabrica.getInstance();
    private final IControladorUsuario cu = fabrica.getControladorUsuario();

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

        // Redirigimos al index.jsp para mostrar los usuarios
        req.getRequestDispatcher("/WEB-INF/pages/usuarios.jsp").forward(req, res);
    }  
    
    private void consultaUsuario(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String nick = req.getParameter("nick");
        if (nick.isEmpty()) {
            req.setAttribute("error", "Debe ingresar un usuario a consultar.");
            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
            return;
        }

//        try {
//            DataUsuario usuario = null;
//
//            // Probamos primero con asistente
//            try {
//                usuario = null;
//            } catch (UsuarioNoExisteException ignored) {}
//
//            // Si no existe, probamos con organizador
//            if (usuario == null) {
//                try {
//                    usuario = null;
//                } catch (UsuarioNoExisteException ignored) {}
//            }
//
//            if (usuario == null) {
//                throw new UsuarioNoExisteException("Usuario no encontrado.");
//            }
//
//            req.setAttribute("usuario", usuario);
//            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
//
//        } catch (UsuarioNoExisteException e) {
//            req.setAttribute("error", e.getMessage());
//            req.getRequestDispatcher("/WEB-INF/pages/consultaUsuario.jsp").forward(req, res);
//        }
    }

}
