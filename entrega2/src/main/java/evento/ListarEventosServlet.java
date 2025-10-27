package evento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collections;
import java.util.List;

import ws.eventos.DataEvento;
import ws.eventos.EventoNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
//import excepciones.EventoNoExisteException;
//import logica.interfaces.IControladorEvento;
//import logica.Fabrica;
//import logica.datatypes.DataEvento;

@WebServlet("/eventos")
public class ListarEventosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
//    private Fabrica fabrica = Fabrica.getInstance();
//    private IControladorEvento ce = fabrica.getControladorEvento();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
        EventosService service = new EventosService();
        EventosWs port = service.getEventosPort();
        
        try {
          List<DataEvento> eventos = port.listarEventoExistentes();
          if(eventos == null) {
            System.out.println("devolvio null");
          }else {
            System.out.println("cantidad de eventos: " + eventos.size());
            for(DataEvento e: eventos) {
              System.out.println("-"+ e.getNombre() + "|" + e.getDescripcion());
            }
          }
          req.setAttribute("eventos", eventos);
          req.setAttribute("eventosCount", eventos.size());
        }catch(EventoNoExisteFault_Exception error) {
          req.setAttribute("error", error.getMessage());
        }
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);     
    }
}
    
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        DataEvento[] eventosArray = null;
//        List<DataEvento> eventosList = Collections.emptyList();
//
//        try {
//            eventosArray = ce.listarEventoExistentes();
//            if (eventosArray != null) {
//                eventosList = Arrays.asList(eventosArray);
//            }
//        } catch (EventoNoExisteException e) {}
//
//        req.setAttribute("eventos", eventosList);
//
//        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
//
//    }
//}

