package mobile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebServiceException;
import ws.eventos.DataEvento;
import ws.eventos.EventoNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Servlet implementation class listarEventos
 */
@WebServlet("/listarEventosMobile")
public class listarEventosMobile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventosService service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarEventosMobile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	  @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        req.setAttribute("errorWs", false);
        List<DataEvento> eventos = Collections.emptyList();
        
        try {
          if(service == null) {
            service = new EventosService();
          }
          EventosWs controladorEvento = service.getEventosPort();

          eventos = controladorEvento.listarEventoExistentes();

          req.setAttribute("eventos", eventos);
          req.setAttribute("eventosCount", eventos.size());

        } catch (EventoNoExisteFault_Exception e) {
            req.setAttribute("eventos", eventos);
            req.setAttribute("eventosCount", 0);

        } catch (WebServiceException e) {
            req.setAttribute("errorWs", true);
            req.setAttribute("error", "No se pudo conectar con el servidor de eventos.");
            req.setAttribute("eventos", eventos);
            req.setAttribute("eventosCount", 0);
        }

        req.getRequestDispatcher("/WEB-INF/pages" + "/mobile" +"/eventosMobile.jsp").forward(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
