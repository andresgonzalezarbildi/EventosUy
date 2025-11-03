package mobile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebServiceException;
import ws.eventos.DataEdicion;
import ws.eventos.DataEvento;
import ws.eventos.EdicionNoExisteFault_Exception;
import ws.eventos.EventoNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class EventoServlet
 */
@WebServlet("/mobile/ConsultaEvento")
public class ConsultaEventoMobile extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private EventosService service = null;
       
    public ConsultaEventoMobile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      res.setCharacterEncoding("UTF-8");
      
      
    String nombreEvento = (req.getParameter("id")!= null) ? req.getParameter("id").trim() : "";
    if(nombreEvento != "") {
      try {        
        if(service == null) {
          service = new EventosService();
        }
        EventosWs controladorEventos = service.getEventosPort();
        DataEvento dataDelEvento = controladorEventos.getUnEventoDTO(nombreEvento);
        req.setAttribute("evento", dataDelEvento);
        if (dataDelEvento != null) {
          try {
            List<DataEdicion> dataEdicionesDelEvento = controladorEventos.listarEdicionesAceptadasEvento(nombreEvento);
            req.setAttribute("ediciones", dataEdicionesDelEvento);
          }catch(EdicionNoExisteFault_Exception error) {
            req.setAttribute("ediciones", null);
          }
        }
      }catch(EventoNoExisteFault_Exception error) {
        req.setAttribute("error", error.getMessage());
      }catch (WebServiceException e) {
        req.setAttribute("error", "No se pudo conectar con el servidor");
        req.setAttribute("ediciones", null);
        req.setAttribute("evento", null);
    }
      
      req.getRequestDispatcher("/WEB-INF/pages/mobile/ConsultaEventoMobile.jsp").forward(req, res);
    }else {
      res.sendRedirect(req.getContextPath()+"/mobile/listarEventos");
    }
  }


  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
