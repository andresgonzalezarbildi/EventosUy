package tiporegistro;

import java.io.IOException;

import excepciones.EdicionNoExisteException;
import excepciones.TipoRegistroRepetidoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.Fabrica;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;

@WebServlet(name = "TipoRegistroServlet", urlPatterns = {"/TipoRegistroServlet"})
public class TipoRegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Fabrica fabrica = Fabrica.getInstance();
    private final IControladorEvento ce = fabrica.getControladorEvento();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getParameter("op");
        if (op == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación no especificada");
            return;
        }

        switch (op.toLowerCase()) {
		    case "alta":
		        mostrarFormularioAlta(req, res);
		        break;
		    case "listar":
		        listarTiposRegistro(req, res);
		        break;
		    case "consulta":
		        mostrarConsulta(req, res);
		        break;
		    default:
		        res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible");
		}
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getParameter("op");
        if (op == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación no especificada");
            return;
        }

        switch (op.toLowerCase()) {
            case "alta":
                procesarAlta(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible");
        }
    }

    private void mostrarFormularioAlta(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String nicknameLogueado = (String) req.getSession().getAttribute("usuario");
        String nombreEvento = req.getParameter("idEvento");
        String nombreEdicion = req.getParameter("idEdicion");

        DataEdicion ed = null;
        if (nombreEdicion != null && !nombreEdicion.isEmpty()) {
            try {
                ed = ce.getInfoEdicion(nombreEdicion);
            } catch (EdicionNoExisteException e) {
            }
        }

        req.setAttribute("evento", nombreEvento);
        req.setAttribute("edicionSeleccionada", ed); 
        req.getRequestDispatcher("/WEB-INF/pages/altaTipoRegistro.jsp").forward(req, res);
    }


    private void procesarAlta(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String evento = req.getParameter("evento");
        String edicion = req.getParameter("edicion");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String costoStr = req.getParameter("costo");
        String cupoStr = req.getParameter("cupo");

        int costo = Integer.parseInt(costoStr);
        int cupo = Integer.parseInt(cupoStr);

        try {
            ce.altaTipoRegistro(evento, edicion, nombre, descripcion, costo, cupo);
            res.sendRedirect(req.getContextPath() + "/TipoRegistroServlet?op=consulta&evento=" 
                + evento + "&edicion=" + edicion + "&nombre=" + nombre);
        } catch (TipoRegistroRepetidoException e) {
            req.setAttribute("error", "Ya existe un tipo de registro con ese nombre para esta edición.");
            req.setAttribute("evento", evento);
            req.setAttribute("edicion", edicion);
            req.setAttribute("nombre", nombre);
            req.setAttribute("descripcion", descripcion);
            req.setAttribute("costo", costo);
            req.setAttribute("cupo", cupo);
            req.getRequestDispatcher("/WEB-INF/pages/altaTipoRegistro.jsp").forward(req, res);
        }
    }

    private void listarTiposRegistro(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String evento = req.getParameter("evento");
        String edicion = req.getParameter("edicion");

        if (evento == null || evento.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Evento no especificado");
            return;
        }
        if (edicion == null || edicion.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Edición no especificada");
            return;
        }

        var tipos = ce.listarTiposRegistro(evento, edicion);
        req.setAttribute("tipos", tipos);
        req.setAttribute("evento", evento);
        req.setAttribute("edicion", edicion);
        req.getRequestDispatcher("/WEB-INF/pages/listarTipoRegistro.jsp").forward(req, res);
    }

    private void mostrarConsulta(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String edicion = req.getParameter("idEdicion");
        String evento =  ce.getEventoDeUnaEdicion(edicion);;
        String nombre = req.getParameter("id");

        if (evento == null || edicion == null || nombre == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros");
            return;
        }

        DataTipoRegistro tipoRegistro = ce.getTipoRegistro(evento, edicion, nombre);
		req.setAttribute("tipoRegistro", tipoRegistro);
		req.setAttribute("evento", evento);
		req.setAttribute("edicion", edicion);
		req.getRequestDispatcher("/WEB-INF/pages/consultaTipoRegistro.jsp").forward(req, res);
    }

}
