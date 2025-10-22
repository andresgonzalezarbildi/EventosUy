package evento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.Fabrica;
import logica.datatypes.DataEvento;
import logica.datatypes.DataEdicion;
import logica.interfaces.IControladorEvento;

import excepciones.EdicionNoExisteException;
import excepciones.EventoNoExisteException;

import java.io.IOException;

/**
 * Servlet implementation class EventoServlet
 */
@WebServlet("/evento")
public class EventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Fabrica fabrica = Fabrica.getInstance();
    private IControladorEvento controladorEventos = fabrica.getControladorEvento();
       
    public EventoServlet() {
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
				DataEvento dataDelEvento = controladorEventos.getUnEventoDTO(nombreEvento);
				req.setAttribute("evento", dataDelEvento);
				if (dataDelEvento != null) {
					try {
						DataEdicion[] dataEdicionesDelEvento = controladorEventos.listarEdicionesAceptadasEvento(nombreEvento);
						req.setAttribute("ediciones", dataEdicionesDelEvento);
					}catch(EdicionNoExisteException error) {
					
					}
			}
		}catch(EventoNoExisteException error) {
			
		}
			
			req.getRequestDispatcher("/WEB-INF/pages/consultaEvento.jsp").forward(req, res);
		}else {
		  res.sendRedirect(req.getContextPath()+"/eventos");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
