package sidebarCategorias;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.ws.WebServiceException;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.estado.EstadoServidorService;
import ws.estado.EstadoServidor;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.http.Cookie;

@WebFilter("/*")
public class SidebarCategorias extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L;
    private EventosService service = null;
    private static String ultimaFechaInicio = null; // para detectar reinicios del servidor central

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("usuariosActivos", null);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	

        String path = request.getRequestURI();
        System.out.println("[SidebarCategorias] pasando por: " + path);
       
        if (path.contains("/LoginServlet") ||
                path.contains("/LogoutServlet")
                ||path.endsWith("ValidarUsuarioServlet") ||
            path.contains("/img/") ||
            path.contains("/estilos/") ||
            path.contains("/js/")) {
            chain.doFilter(request, response);
            return;
        }

      
        try {
            EstadoServidorService serviceEstado = new EstadoServidorService();
            EstadoServidor estadoWs = serviceEstado.getEstadoServidorPort();

            String fechaInicioActual = estadoWs.getFechaInicio();

            if (ultimaFechaInicio == null) {
                ultimaFechaInicio = fechaInicioActual;
            } else if (!ultimaFechaInicio.equals(fechaInicioActual)) {
               
                ultimaFechaInicio = fechaInicioActual;

              
                HttpSession sesion = request.getSession(false);
                if (sesion != null) {
                    sesion.invalidate();
                }

             
                Cookie cookie = new Cookie("JSESSIONID", "");
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);

                System.out.println("[SidebarCategorias] ⚠️ Servidor central reiniciado. Sesión invalidada.");
                response.sendRedirect(request.getContextPath() + "/LoginServlet");
                return;
            }

        } catch (Exception e) {
            System.out.println("[SidebarCategorias] ❌ No se pudo conectar al EstadoServidorWS: " + e.getMessage());

           
            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                sesion.invalidate();
            }

           
            Cookie cookie = new Cookie("JSESSIONID", "");
            cookie.setMaxAge(0);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);

            request.setAttribute("javax.servlet.error.status_code", 503);
            request.setAttribute("mensajeError", 
                "El servidor central no se encuentra disponible en este momento. " +
                "Por favor, intente nuevamente más tarde.");
            request.setAttribute("javax.servlet.error.request_uri", request.getRequestURI());

            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
            return;
        }

  
        List<String> categorias = List.of();
        try {
            if (service == null) {
                service = new EventosService();
            }
            EventosWs controladorEvento = service.getEventosPort();
            List<String> remotas = controladorEvento.listarCategorias();
            if (remotas != null) categorias = remotas;
        } catch (WebServiceException e) {
            // manejar fallo sin cortar la carga
        }

        request.setAttribute("categorias", categorias);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {}
    public void destroy() {}
}
