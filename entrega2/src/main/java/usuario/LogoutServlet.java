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

      
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.invalidate();
        }

        
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath()); 
        response.addCookie(cookie);

      
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
