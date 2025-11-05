package mobile;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/errores")
public class erroresServlets extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Recupera info del error que el contenedor le pasa automáticamente
        Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");

        if (requestUri == null) {
            requestUri = "Desconocido";
        }


        req.setAttribute("codigo", statusCode);
        req.setAttribute("excepcion", throwable);
        req.setAttribute("uri", requestUri);

        req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
    }
}
