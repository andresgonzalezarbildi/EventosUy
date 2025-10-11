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

        DataEvento[] eventosArray = null;
        List<DataEvento> eventosList = Collections.emptyList();

        try {
            eventosArray = ce.listarEventoExistentes();
            if (eventosArray != null) {
                eventosList = Arrays.asList(eventosArray);
            }
        } catch (EventoNoExisteException e) {}

        req.setAttribute("eventos", eventosList);

        req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);

    }
}

