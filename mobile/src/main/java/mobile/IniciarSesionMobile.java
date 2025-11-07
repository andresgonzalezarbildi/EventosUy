package mobile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.ws.WebServiceException;
import ws.usuario.DataUsuario;
import ws.usuario.PasswordIncorrectaFault_Exception;
import ws.usuario.UsuarioNoExisteFault_Exception;
import ws.usuario.UsuarioService;
import ws.usuario.UsuarioWs;

import java.io.IOException;

/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/iniciarSesion")
public class IniciarSesionMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioService service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionMobile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  req.getRequestDispatcher("/WEB-INF/pages" + "/mobile" +"/iniciarSesionMobile.jsp").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  req.setCharacterEncoding("UTF-8");
	  String ident = req.getParameter("usuario");
    String password = req.getParameter("password");
    if (ident == null || password == null || ident.isBlank() || password.isBlank()) {
      req.setAttribute("error", "Usuario y contraseña son obligatorios.");
      req.getRequestDispatcher("/WEB-INF/pages" + "/mobile" +"/iniciarSesionMobile.jsp").forward(req, res);
      return;
  }
	  try {
	    if(service == null) {
	      service = new UsuarioService();
	    }
	    UsuarioWs controladorUsuario = service.getUsuarioPort();
	    
	    DataUsuario usuario = controladorUsuario.loginMovil(ident, password);

      HttpSession sesion = req.getSession(true);
      sesion.setAttribute("usuario", usuario.getNickname());
      sesion.setAttribute("correo", usuario.getCorreo());
      sesion.setAttribute("imagen", usuario.getImagen());
      sesion.setAttribute("rol", usuario.getTipo());
      
      res.sendRedirect(req.getContextPath() + "/listarEventos");
      return;
	    
	  }catch(PasswordIncorrectaFault_Exception e) {
	    req.setAttribute("error", e.getMessage());
	  }catch( UsuarioNoExisteFault_Exception e) {
	    req.setAttribute("error", e.getMessage());
	  }catch(WebServiceException e) {
      req.setAttribute("error", "No se pudo conectar con el servidor.");
	  }
	  req.getRequestDispatcher("/WEB-INF/pages" + "/mobile" +"/iniciarSesionMobile.jsp").forward(req, res);
	}
}
