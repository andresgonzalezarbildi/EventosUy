package usuario;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ws.usuario.DataUsuario;
import ws.usuario.PasswordIncorrectaFault_Exception;
import ws.usuario.UsuarioNoExisteFault_Exception;
import ws.usuario.UsuarioService;
import ws.usuario.UsuarioWs;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
  private static final long serialVersionUID = 1L;

  @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    request.getRequestDispatcher("/WEB-INF/pages/iniciarSesion.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String ident = request.getParameter("usuario");
	    String password = request.getParameter("password");

	    UsuarioService serviceUs = new UsuarioService();
	    UsuarioWs cu = serviceUs.getUsuarioPort();

	    try {
	        DataUsuario DataUsu = cu.login(ident, password);

	        HttpSession sesion = request.getSession(true);
	        sesion.setAttribute("usuario", DataUsu.getNickname());
	        sesion.setAttribute("correo", DataUsu.getCorreo());
	        sesion.setAttribute("imagen", DataUsu.getImagen());

	        String rol = (DataUsu.getApellido() != null) ? "asistente" : "organizador";
	        sesion.setAttribute("rol", rol);

	        response.sendRedirect(request.getContextPath() + "/eventos");

	    } catch (UsuarioNoExisteFault_Exception | PasswordIncorrectaFault_Exception e) {

	        request.setAttribute("error", "Usuario o contraseña incorrectos");
	        request.setAttribute("usuarioIngresado", ident); // para rellenar el input
	        request.getRequestDispatcher("/WEB-INF/pages/iniciarSesion.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Ocurrió un error inesperado");
	        request.getRequestDispatcher("/WEB-INF/pages/iniciarSesion.jsp").forward(request, response);
	    }
	}
}
