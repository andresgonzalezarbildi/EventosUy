package mobile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.eventos.EdicionSinComenzarFault_Exception;

@WebServlet("/confirmarAsistencia")
public class ConfirmarAsistenciaMobile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private transient EventosService serviceEv;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath() + "/mobile/IniciarSesion");
            return;
        }

        String nickname = (String) sesion.getAttribute("usuario");
        String idEdicion = req.getParameter("edicion");
        if (idEdicion == null || idEdicion.isBlank()) {
            sesion.setAttribute("error", "Falta el identificador de la edición.");
            res.sendRedirect(req.getContextPath() + "/mobile/listarEventos");
            return;
        }

        try {
            if (serviceEv == null) serviceEv = new EventosService();
            EventosWs ctrlEv = serviceEv.getEventosPort();

            System.out.println("[POST confirmarAsistencia] edicion=" + idEdicion + " usuario=" + nickname);
            ctrlEv.confirmarAsistencia(idEdicion, nickname); // <-- método WS

            // sin mensaje de éxito: la vista se actualiza y el botón desaparece
        } catch (EdicionSinComenzarFault_Exception e) {
            sesion.setAttribute("error", e.getMessage());
        } catch (Exception e) {
            sesion.setAttribute("error", "Ocurrió un error al confirmar la asistencia.");
        }

        String url = req.getContextPath() + "/mobile/consultaRegistro?edicion=" +
                     URLEncoder.encode(idEdicion, "UTF-8");
        res.sendRedirect(url); // PRG
    }
}
