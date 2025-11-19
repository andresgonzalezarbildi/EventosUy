package mobile;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
      throws IOException, ServletException {

    String ctx = req.getContextPath();
    String path = req.getRequestURI().substring(ctx.length());
    boolean isLogin  = path.equals("/iniciarSesion");
    boolean isLogout = path.equals("/cerrarSesion");

    HttpSession s = req.getSession(false);
    boolean loggedIn = (s != null && s.getAttribute("usuario") != null);
    String rol = (s != null) ? (String) s.getAttribute("rol") : null;


    if (loggedIn && isLogin) {
      resp.sendRedirect(ctx + "/listarEventos"); 
      return;
    }


    if (isLogout ||
        path.startsWith("/estilos") ||
        path.startsWith("/img") ||
        path.startsWith("/js") ||
        path.equals("/") ||
        path.equals("/favicon.ico") ||
        path.startsWith("/webjars") ||
        path.startsWith("/assets")) {
      chain.doFilter(req, resp);
      return;
    }

    if (isLogin && !loggedIn) {
      chain.doFilter(req, resp);
      return;
    }

    if (!loggedIn || !"Asistente".equals(rol)) {
      String next = req.getRequestURI();
      if (req.getQueryString() != null) next += "?" + req.getQueryString();
      String loginUrl = ctx + "/iniciarSesion?next=" + URLEncoder.encode(next, "UTF-8");
      resp.sendRedirect(loginUrl);
      return;
    }

    chain.doFilter(req, resp);
  }
}
