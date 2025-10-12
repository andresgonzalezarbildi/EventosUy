package cargarDatos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import logica.Fabrica;
import logica.interfaces.ICargaDatos;            // en el JAR

@WebServlet(name = "SvCargarDatos", urlPatterns = {"/cargarDatos"})
public class CargarDatosServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICargaDatos carga;
	private Fabrica fabrica;

    @Override
    public void init() throws ServletException {
      fabrica = Fabrica.getInstance();
      carga = fabrica.getCargaDatos();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            carga.cargarDatosIniciales(); // método de la interfaz del JAR 
        } catch (Exception e) {
        }
        
        // Reenviar a la página principal
        response.sendRedirect(request.getContextPath() + "/eventos");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
