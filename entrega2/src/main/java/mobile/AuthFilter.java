package mobile;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebFilter("/mobile/*") 
public class AuthFilter extends HttpFilter implements Filter {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
      throws IOException, ServletException {

    String ctx = req.getContextPath();
    String path = req.getRequestURI().substring(ctx.length());

    if (path.equals("/mobile/IniciarSesion") || path.equals("/mobile/logOut")) {
      chain.doFilter(req, resp);
      return;
    }

    HttpSession s = req.getSession(false);
    if (s == null || s.getAttribute("usuario") == null || !"Asistente".equals(s.getAttribute("rol"))) {
      String next = req.getRequestURI();
      if (req.getQueryString() != null)
        next += "?" + req.getQueryString();

      String loginUrl = ctx + "/mobile/IniciarSesion?next=" + URLEncoder.encode(next, "UTF-8");
      resp.sendRedirect(loginUrl);
      return;
    }

    chain.doFilter(req, resp);
  }
}
