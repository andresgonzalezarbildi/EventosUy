package usuario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false); // no crear si no existe
        if (sesion != null) {
            sesion.invalidate(); //  destruye la sesión
        }

        // Redirigir al login (podés agregar mensaje opcional)
        response.sendRedirect(request.getContextPath() + "/eventos");
    }
}
