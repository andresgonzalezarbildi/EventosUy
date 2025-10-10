package evento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import excepciones.EventoNoExisteException;
import logica.interfaces.IControladorEvento;
import logica.Fabrica;
import logica.datatypes.DataEvento;

@WebServlet("/eventos")
public class ListarEventosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Fabrica fabrica = Fabrica.getInstance();
    private IControladorEvento ce = fabrica.getControladorEvento();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");


        DataEvento[] eventosArray = null;
        List<DataEvento> eventosList = Collections.emptyList();

        try {
            eventosArray = ce.listarEventoExistentes();
            if (eventosArray != null) {
                eventosList = Arrays.asList(eventosArray);
            }
        } catch (EventoNoExisteException e) {
            System.out.println("⚠️ No hay eventos cargados: " + e.getMessage());
        }

        // Mandamos la lista al JSP
        req.setAttribute("eventos", eventosList);

        // Redirigimos al index.jsp para mostrar los eventos
        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
    }
}

