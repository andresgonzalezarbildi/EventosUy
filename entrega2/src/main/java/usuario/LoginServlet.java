package usuario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import logica.Fabrica;
import logica.interfaces.IControladorUsuario;
import logica.datatypes.DataUsuario;
import logica.clases.Asistente;
import logica.clases.Organizador;
import excepciones.UsuarioNoExisteException;
import excepciones.PasswordIncorrectaException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Muestra el formulario de login
	        request.getRequestDispatcher("/WEB-INF/pages/iniciarSesion.jsp").forward(request, response);
	    }

	 @Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {

	     String ident = request.getParameter("usuario");
	     String password = request.getParameter("password");

	     Fabrica fabrica = Fabrica.getInstance();
	     IControladorUsuario ctrlUsuario = fabrica.getControladorUsuario();

	     try {
	         DataUsuario DataUsu = ctrlUsuario.login(ident, password);

	         HttpSession sesion = request.getSession(true);
	         sesion.setAttribute("usuario", DataUsu.getNickname());
	         sesion.setAttribute("correo", DataUsu.getCorreo());
	         sesion.setAttribute("imagen", DataUsu.getImagen());

	         String rol = (DataUsu.getApellido() != null) ? "asistente" : "organizador";
	         sesion.setAttribute("rol", rol);

	         response.sendRedirect(request.getContextPath() + "/eventos");

	     } catch (UsuarioNoExisteException | PasswordIncorrectaException e) {
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
