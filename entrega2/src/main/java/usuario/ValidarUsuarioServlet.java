package usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import ws.usuario.UsuarioService;
import ws.usuario.UsuarioWs;
import ws.usuario.UsuarioNoExisteFault_Exception;

@WebServlet("/ValidarUsuarioServlet")
public class ValidarUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuarioWs cu;

    @Override
    public void init() throws ServletException {
        UsuarioService service = new UsuarioService();
        cu = service.getUsuarioPort();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json;charset=UTF-8");
        String nick = req.getParameter("nick");
        String correo = req.getParameter("correo");

        boolean disponible = true;
        String tipo = "";

        try {
            if (nick != null && !nick.isBlank()) {
                tipo = "nick";
                cu.verInfoUsuario(nick); // lanza excepción si no existe
                disponible = false;
            } 
//            else if (correo != null && !correo.isBlank()) {
//                tipo = "correo";
//                cu.verUsuarioPorCorreo(correo); // suponiendo que tu WS lo tiene
//                disponible = false;
//            }
        } catch (UsuarioNoExisteFault_Exception e) {
            disponible = true; // si lanza, el usuario no existe => disponible
        } catch (Exception e) {
            disponible = false;
        }

        String json = String.format("{\"tipo\":\"%s\", \"disponible\":%s}", tipo, disponible);
        res.getWriter().write(json);
    }
}
