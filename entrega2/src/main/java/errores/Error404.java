package errores;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Error404")
public class Error404 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Integer status = (Integer) req.getAttribute("javax.servlet.error.status_code");
    String uri = (String) req.getAttribute("javax.servlet.error.request_uri");
    if (status == null) status = 404;
    if (uri == null) uri = req.getRequestURI();

    req.setAttribute("mensajeError", "La página que buscas no existe.");

    resp.setStatus(404);

    req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
  }
}