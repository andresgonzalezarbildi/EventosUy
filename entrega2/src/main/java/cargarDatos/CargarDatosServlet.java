package cargarDatos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import logica.interfaces.ICargaDatos;            // en el JAR
import logica.CargaDatos.CargaDatos;            // implementación del JAR
import logica.controladores.ControladorEvento;  // del JAR
import logica.controladores.ControladorUsuario; // del JAR

@WebServlet(name = "SvCargarDatos", urlPatterns = {"/cargarDatos"})
public class CargarDatosServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICargaDatos carga;

    @Override
    public void init() throws ServletException {
    	System.out.println("[CargarDatosServlet] init()");
        // Crear dependencias que pide CargaDatos (ajustá si usás interfaces/fábrica)
        var cu = new ControladorUsuario();
        var ce = new ControladorEvento();
        this.carga = new CargaDatos(cu, ce);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensaje = "";
        String tipoMensaje = "";
        
        try {
            System.out.println("[CargarDatosServlet] Iniciando carga de datos...");
            carga.CargarDatosIniciales(); // método de la interfaz del JAR
            mensaje = "✅ Datos cargados correctamente. El sistema está listo para usar.";
            tipoMensaje = "success";
            System.out.println("[CargarDatosServlet] Carga de datos completada exitosamente");
            
        } catch (Exception e) {
            mensaje = "❌ Error al cargar datos: " + e.getMessage();
            tipoMensaje = "error";
            System.err.println("[CargarDatosServlet] Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Pasar mensaje a la JSP
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("tipoMensaje", tipoMensaje);
        
        // Reenviar a la página principal
        response.sendRedirect(request.getContextPath() + "/eventos");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
