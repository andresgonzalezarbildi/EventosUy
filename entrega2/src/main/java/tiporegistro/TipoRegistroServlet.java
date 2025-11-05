package tiporegistro;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ws.eventos.EdicionNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.eventos.TipoRegistroRepetidoFault_Exception;

@WebServlet(name = "TipoRegistroServlet", urlPatterns = {"/TipoRegistroServlet"})
public class TipoRegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EventosService service = new EventosService();
	EventosWs ce = service.getEventosPort();

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
                try {
                    mostrarConsulta(req, res);
                } catch (EdicionNoExisteFault_Exception e) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Edición no encontrada");
                }
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
        String nombreEdicion = req.getParameter("idEdicion");
        if (nombreEdicion == null || nombreEdicion.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Edición no especificada");
            return;
        }

        ws.eventos.DataEdicion ed = null;
        try {
            ed = ce.getInfoEdicion(nombreEdicion);
        } catch (EdicionNoExisteFault_Exception e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Edición no encontrada");
            return;
        }

        String nombreEvento = ce.getEventoDeUnaEdicion(nombreEdicion);

        req.setAttribute("edicion", ed);
        req.setAttribute("evento", nombreEvento);
        req.setAttribute("nombreEdicion", nombreEdicion);

        req.getRequestDispatcher("/WEB-INF/pages/altaTipoRegistro.jsp").forward(req, res);
    }

    private void procesarAlta(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String evento = req.getParameter("evento");
        String edicionNombre = req.getParameter("edicion");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String costoStr = req.getParameter("costo");
        String cupoStr = req.getParameter("cupo");

        int costo = 0;
        int cupo = 0;
        String error = null;

        try {
            costo = Integer.parseInt(costoStr);
            cupo = Integer.parseInt(cupoStr);

            if (cupo <= 0) {
                error = "El cupo debe ser mayor a 0.";
            } else {
                ce.altaTipoRegistro(evento, edicionNombre, nombre, descripcion, costo, cupo);
                res.sendRedirect(req.getContextPath() + "/edicion?op=consultar&id="
                        + URLEncoder.encode(edicionNombre, "UTF-8"));
                return;
            }
        } catch (NumberFormatException e) {
            error = "Cupo y costo deben ser números válidos.";
        } catch (TipoRegistroRepetidoFault_Exception e) {
            error = "Ya existe un tipo de registro con ese nombre para esta edición.";
        }

        // Siempre obtener DataEdicion para no romper el JSP
        ws.eventos.DataEdicion edicionObj = null;
        try {
            edicionObj = ce.getInfoEdicion(edicionNombre);
        } catch (EdicionNoExisteFault_Exception e) {
            // opcional: mostrar mensaje de error general
        }

        req.setAttribute("edicion", edicionObj); // <-- DataEdicion siempre
        req.setAttribute("nombreEdicion", edicionNombre);
        req.setAttribute("evento", evento);
        req.setAttribute("nombre", nombre);
        req.setAttribute("descripcion", descripcion);
        req.setAttribute("costo", costo);
        req.setAttribute("cupo", cupo);
        req.setAttribute("error", error);

        req.getRequestDispatcher("/WEB-INF/pages/altaTipoRegistro.jsp").forward(req, res);
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
            throws ServletException, IOException, EdicionNoExisteFault_Exception {
        String nombreEdicion = req.getParameter("idEdicion");
        String rol = null;
        String nickname = null;
        HttpSession sesion = req.getSession(false);
        if (sesion != null) {
            rol = (String) sesion.getAttribute("rol");
            nickname = (String) sesion.getAttribute("usuario");
        } else {
            rol = "visitante";
        }

        ws.eventos.DataRegistro registroAsistente = null;
        if ("asistente".equalsIgnoreCase(rol)) {
            registroAsistente = ce.listarUnRegistroDeUsuario(nombreEdicion, nickname);
        }
        req.setAttribute("rol", rol);
        req.setAttribute("nickname", nickname);
        req.setAttribute("registroAsistente", registroAsistente);

        String evento = ce.getEventoDeUnaEdicion(nombreEdicion);
        String nombre = req.getParameter("id");
        ws.eventos.DataEdicion edicion = ce.getInfoEdicion(nombreEdicion);

        if (evento == null || edicion == null || nombre == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros");
            return;
        }

        ws.eventos.DataTipoRegistro tipoRegistro = ce.getTipoRegistro(evento, nombreEdicion, nombre);
        req.setAttribute("edicion", edicion);
        req.setAttribute("tipoRegistro", tipoRegistro);
        req.setAttribute("evento", evento);
        req.setAttribute("nombreEdicion", nombreEdicion);
        req.getRequestDispatcher("/WEB-INF/pages/consultaTipoRegistro.jsp").forward(req, res);
    }
}
