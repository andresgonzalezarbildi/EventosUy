package mobile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import ws.eventos.DataEdicion;
import ws.eventos.DataRegistro;
import ws.eventos.DataTipoRegistro;
import ws.eventos.EdicionNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;

@WebServlet("/consultaRegistro")
public class ConsultaRegistroMobile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private transient EventosService serviceEv;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            res.sendRedirect(req.getContextPath() + "/mobile/IniciarSesion");
            return;
        }
        String nickname = (String) sesion.getAttribute("usuario");

       
        Object flashError = sesion.getAttribute("error");
        if (flashError != null) {
            req.setAttribute("error", flashError);
            sesion.removeAttribute("error");
        }

        String idEdicion = req.getParameter("edicion");
        if (idEdicion == null || idEdicion.isBlank()) {
            req.setAttribute("error", "Falta el identificador de la edición.");
            req.getRequestDispatcher("/WEB-INF/pages/mobile/consultaTipoRegistroMobile.jsp")
               .forward(req, res);
            return;
        }

        try {
            if (serviceEv == null) serviceEv = new EventosService();
            EventosWs ctrlEv = serviceEv.getEventosPort();

            System.out.println("[GET consultaRegistro] edicion=" + idEdicion + " usuario=" + nickname);

            DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, nickname);
            req.setAttribute("registro", registro);

            DataTipoRegistro tipoRegistro = ctrlEv.getTipoRegistro(
                    registro.getEvento(), idEdicion, registro.getTipoRegistro());
            req.setAttribute("tipoRegistro", tipoRegistro);

            try {
                DataEdicion edicion = ctrlEv.getInfoEdicion(idEdicion);
                req.setAttribute("edicion", edicion);
            } catch (EdicionNoExisteFault_Exception e) {
                req.setAttribute("error", "La edición no existe: " + e.getMessage());
            }

        } catch (Exception ex) {
            req.setAttribute("error", "Ocurrió un error al obtener los datos del registro.");
        }

        req.getRequestDispatcher("/WEB-INF/pages/mobile/consultaTipoRegistroMobile.jsp")
           .forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
