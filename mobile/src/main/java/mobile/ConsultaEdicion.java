package mobile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.ws.WebServiceException;
import ws.eventos.DataEdicion;
import ws.eventos.DataEvento;
import ws.eventos.EdicionNoExisteFault_Exception;
import ws.eventos.EventoNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;

import java.io.IOException;

/**
 * Servlet implementation class ConsultaEdicion
 */
@WebServlet("/consultaEdicion")
public class ConsultaEdicion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventosService service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaEdicion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  String nombreEdicion = (request.getParameter("id")!= null) ? request.getParameter("id").trim() : "";
	  if(nombreEdicion != "") {
      try {        
        if(service == null) {
          service = new EventosService();
        }
        EventosWs controladorEventos = service.getEventosPort();
        DataEdicion edicion = controladorEventos.getInfoEdicion(nombreEdicion);
        request.setAttribute("edicion", edicion);
        try {
          String nombreEvento = controladorEventos.getEventoDeUnaEdicion(nombreEdicion);
          DataEvento dataEvento = controladorEventos.getUnEventoDTO(nombreEvento);
          request.setAttribute("evento", dataEvento);
        }catch(EventoNoExisteFault_Exception error){
          request.setAttribute("evento", null);
        }
        
        HttpSession sesion = request.getSession(false);
        String nickname = (String) sesion.getAttribute("usuario");
        request.setAttribute("nickname", nickname);
        try {
          ws.eventos.DataRegistro registroAsistente = controladorEventos.listarUnRegistroDeUsuario(nombreEdicion, nickname);
          request.setAttribute("registroAsistente", registroAsistente);
        } catch (Exception e) {
          request.setAttribute("error", "Ocurrio un error al listar los registros");
        }
        
      }catch(EdicionNoExisteFault_Exception error) {
        request.setAttribute("error", error.getMessage());
        request.setAttribute("edicion", null);
      }catch (WebServiceException error) {
        request.setAttribute("error", "No se pudo conectar con el servidor");
        request.setAttribute("edicion", null);
      }
      request.getRequestDispatcher("/WEB-INF/pages/mobile/consultaEdicionMobile.jsp").forward(request, response);
	  }else {
	    request.setAttribute("error", "No se especifico el nombre de la edicion");
	    request.getRequestDispatcher("/WEB-INF/pages/mobile/ConsultaEventoMobile.jsp").forward(request, response);
	    
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
