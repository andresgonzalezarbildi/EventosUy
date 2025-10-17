package cargarDatos;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import logica.Fabrica;
import logica.interfaces.ICargaDatos;           

@WebServlet(name = "SvCargarDatos", urlPatterns = {"/cargarDatos"})
public class CargarDatosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ICargaDatos carga;
	private Fabrica fabrica;

    @Override
    public void init() throws ServletException {
      fabrica = Fabrica.getInstance();
      carga = fabrica.getCargaDatos();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
      ServletContext context = getServletContext();
      Boolean cargado = (Boolean) context.getAttribute("datosCargados");

      if (cargado == null || !cargado) {
          carga.cargarDatosIniciales();
          context.setAttribute("datosCargados", true); 
      }

      res.sendRedirect(req.getContextPath() + "/eventos");
    }
}
