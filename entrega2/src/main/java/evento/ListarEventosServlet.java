package evento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.xml.ws.WebServiceException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import ws.eventos.DataEvento;
import ws.eventos.EventoNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;

@WebServlet("/eventos")
public class ListarEventosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EventosService service = null;


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
            req.setAttribute("error", e.getMessage());
            req.setAttribute("eventos", eventos);
            req.setAttribute("eventosCount", 0);

        } catch (WebServiceException e) {
            req.setAttribute("errorWs", true);
            req.setAttribute("error", "No se pudo conectar con el servidor de eventos.");
            req.setAttribute("eventos", eventos);
            req.setAttribute("eventosCount", 0);
        }

        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }
    

}

