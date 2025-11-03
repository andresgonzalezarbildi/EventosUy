package usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import ws.usuarios.UsuarioService;
import ws.usuarios.UsuarioWs;
import ws.usuarios.UsuarioNoExisteFault_Exception;

@WebServlet("/ValidarUsuarioServlet")
public class ValidarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UsuarioService serviceUs = new UsuarioService();
    private final UsuarioWs cu = serviceUs.getUsuarioPort();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("application/json; charset=UTF-8");
        String nick = req.getParameter("nick");
        String correo = req.getParameter("correo");

        try {
            if (nick != null && !nick.isBlank()) {
                boolean disponible = verificarNickDisponible(nick);
                res.getWriter().write("{\"campo\":\"nick\",\"disponible\":" + disponible + "}");
            } else if (correo != null && !correo.isBlank()) {
                boolean disponible = verificarCorreoDisponible(correo);
                res.getWriter().write("{\"campo\":\"correo\",\"disponible\":" + disponible + "}");
            } else {
                res.getWriter().write("{\"error\":\"Parámetro faltante\"}");
            }
        } catch (Exception e) {
            res.getWriter().write("{\"error\":\"" + e.getMessage().replace("\"", "\\\"") + "\"}");
        }
    }

    private boolean verificarNickDisponible(String nick) {
        try {
            cu.verInfoUsuario(nick);  // si no lanza excepción, el usuario existe
            return false;              // nick en uso
        } catch (UsuarioNoExisteFault_Exception e) {
            return true;               // nick disponible
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar nick: " + e.getMessage());
        }
    }

    private boolean verificarCorreoDisponible(String correo) {
        try {
            // recorro todos los usuarios y comparo por email
            for (var u : cu.getUsuarios()) {
                if (u.getCorreo().equalsIgnoreCase(correo)) {
                    return false; // correo en uso
                }
            }
            return true; // correo disponible
        } catch (UsuarioNoExisteFault_Exception e) {
            return true; // no hay usuarios cargados, por lo tanto disponible
        } catch (Exception e) {
            throw new RuntimeException("Error al verificar correo: " + e.getMessage());
        }
    }
}
