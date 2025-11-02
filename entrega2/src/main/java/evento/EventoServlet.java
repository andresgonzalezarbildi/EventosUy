package evento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ws.eventos.EventoNoExisteFault;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class EventoServlet
 */
@WebServlet("/evento")
public class EventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventosService service = new EventosService();
	 EventosWs controladorEventos = service.getEventosPort();
       
    public EventoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false); 
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/login?error=DebeIniciarSesion");
            return;
        }
        ws.usuario.DataUsuario usuario = (ws.usuario.DataUsuario) session.getAttribute("usuarioLogueado");
        if (usuario == null || !usuario.getTipo().equalsIgnoreCase("organizador")) {
            res.sendRedirect(req.getContextPath() + "/accesoDenegado.jsp");
            return;
        }

        String nombreEvento = (req.getParameter("id") != null) ? req.getParameter("id").trim() : "";

        if (!nombreEvento.isEmpty()) {
            ws.eventos.DataEvento dataDelEventoAntes = null;
            try {
                dataDelEventoAntes = controladorEventos.getUnEventoDTO(nombreEvento);
            } catch (ws.eventos.EventoNoExisteFault_Exception error) {
                res.sendRedirect(req.getContextPath() + "/eventos?error=NoExiste");
                return;
            }

            if (dataDelEventoAntes != null && dataDelEventoAntes.isFinalizado()) {
                res.sendRedirect(req.getContextPath() + "/eventos?mensaje=finalizado");
                return;
            }

            try {
                ws.eventos.DataEvento dataDelEvento = controladorEventos.getUnEventoDTO(nombreEvento);
                req.setAttribute("evento", dataDelEvento);

                if (dataDelEvento != null) {
                    try {
                        List<ws.eventos.DataEdicion> dataEdicionesDelEvento =
                            controladorEventos.listarEdicionesAceptadasEvento(nombreEvento);
                        req.setAttribute("ediciones", dataEdicionesDelEvento);
                    } catch (ws.eventos.EdicionNoExisteFault_Exception e) {
                    
                    }
                }

                req.getRequestDispatcher("/WEB-INF/pages/consultaEvento.jsp").forward(req, res);

            } catch (ws.eventos.EventoNoExisteFault_Exception e) {
                res.sendRedirect(req.getContextPath() + "/eventos?error=NoExiste");
            }

        } else {
            res.sendRedirect(req.getContextPath() + "/eventos");
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
