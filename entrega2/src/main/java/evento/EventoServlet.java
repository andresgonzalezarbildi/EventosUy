package evento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ws.eventos.EventoConEdicionesPendientesFault_Exception;
import ws.eventos.EventoNoExisteFault;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.usuario.UsuarioService;
import ws.usuario.UsuarioWs;

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
	 private UsuarioService serviceUs = new UsuarioService();
	 UsuarioWs cu = serviceUs.getUsuarioPort();
	 
    public EventoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String op = req.getParameter("op") != null ? req.getParameter("op").toLowerCase() : "";
        String nombreEvento = (req.getParameter("id") != null) ? req.getParameter("id").trim() : "";
        switch (op) {

            
            case "consultar":
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
                break;

            
            case "baja":
                HttpSession session = req.getSession(false);
                if (session == null || session.getAttribute("usuario") == null) {
                    res.sendRedirect(req.getContextPath() + "/login?error=DebeIniciarSesion");
                    return;
                }

                String usuario = (String) session.getAttribute("usuario");
                try {
                    ws.usuario.DataUsuario datausu = cu.verInfoUsuario(usuario);
                    if (datausu.getTipo() == null || !datausu.getTipo().equalsIgnoreCase("organizador")) {
                        res.sendRedirect(req.getContextPath() + "/accesoDenegado.jsp");
                        return;
                    }
                    String idEventoa = req.getParameter("id");
                    if (idEventoa != null && !idEventoa.isEmpty()) {
                        try {
                            controladorEventos.finalizarEvento(idEventoa);
                        } catch (EventoConEdicionesPendientesFault_Exception e) {
                            e.printStackTrace();
                            String idUtf8 = java.net.URLEncoder.encode(idEventoa, java.nio.charset.StandardCharsets.UTF_8); //esto arregla el conferencia choto
                            res.sendRedirect(req.getContextPath() + "/evento?op=consultar&id=" + idUtf8 + "&error=edicionesPendientes");


                            return;
                        }
                    }
                } catch (ws.usuario.UsuarioNoExisteFault_Exception e) {
                    e.printStackTrace();
                    res.sendRedirect(req.getContextPath() + "/login?error=UsuarioNoExiste");
                    return;
                }
                res.sendRedirect(req.getContextPath() + "/eventos");
                break;

        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
