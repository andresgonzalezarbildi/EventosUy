package evento;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import excepciones.EventoNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

@WebServlet("/edicion")
public class EdicionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Fabrica fabrica = Fabrica.getInstance();
    private IControladorEvento controladorEventos = fabrica.getControladorEvento();
    private IControladorUsuario controladorUsuarios = fabrica.getControladorUsuario();
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = (req.getParameter("op") != null) ? req.getParameter("op").toLowerCase() : "";
        String nombreEdicion = (req.getParameter("id") != null) ? req.getParameter("id") : "";
        String nickname = null;
        String rol = "visitante";

        try {
            switch (op) {
                case "listar":
                    listarEdiciones(req, res);
                    break;
                case "consultar":
                	
                	
                	 // Session del usuario
        	        HttpSession sesion = req.getSession(false);
        	        if (sesion != null) {
        	             rol = (String) sesion.getAttribute("rol");
        	            nickname = (String) sesion.getAttribute("usuario");
        	            req.setAttribute("rol", rol);
        	            req.setAttribute("nickname", nickname);
        	        } else {
        	            req.setAttribute("rol", "visitante");
        	        }
        	        if (nombreEdicion == null || nombreEdicion.isEmpty()) {
        	            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' de la edición.");
        	            return;
        	        }
        	            if (nickname != null && "asistente".equalsIgnoreCase(rol)) {        	            
        	        	 DataRegistro registroAsistente = controladorEventos.listarUnRegistroDeUsuario(nombreEdicion,nickname);
        	        	 req.setAttribute("registroAsistente", registroAsistente);
        	        }
        	            
                    
        	            DataEvento[] eventosArray = null;
        	            List<DataEvento> eventosRelacionados = Collections.emptyList();

        	            try {
        	                eventosArray = controladorEventos.listarEventoExistentes();
        	                if (eventosArray != null) {
        	                	eventosRelacionados = Arrays.asList(eventosArray);
        	                }
        	            } catch (EventoNoExisteException e) {}

        	            req.setAttribute("eventosRelacionados", eventosRelacionados);    
        	            
        	            
                    DataEdicion dataEd = controladorEventos.getInfoEdicion(nombreEdicion);
                    DataRegistro[] registrosEd = controladorEventos.listarRegistrosDeEdicion(nombreEdicion);  
                    req.setAttribute("registrosEd", registrosEd);
                    req.setAttribute("edicion", dataEd);
                    req.getRequestDispatcher("/WEB-INF/pages/consultaEdicion.jsp").forward(req, res);
                    break;
                	default:
                    res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible en el GET.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servlet de Edición.");
        }
    }

    private void listarEdiciones(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String nombreEvento = req.getParameter("evento");
        if (nombreEvento == null || nombreEvento.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'evento'.");
            return;
        }

        try {
            DataEdicion[] ediciones = controladorEventos.listarEdicionesAceptadasEvento(nombreEvento);
            req.setAttribute("evento", nombreEvento);
            req.setAttribute("ediciones", ediciones);
            req.getRequestDispatcher("/WEB-INF/pages/listarEdiciones.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar las ediciones.");
        }
    }
}
