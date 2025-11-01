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

        // No interceptar recursos estáticos
        if (path.endsWith("ValidarUsuarioServlet") ||
            path.contains("/img/") ||
            path.contains("/estilos/") ||
            path.contains("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        // 🧩 Detectar si el servidor central fue reiniciado
        try {
            EstadoServidorService serviceEstado = new EstadoServidorService();
            EstadoServidor estadoWs = serviceEstado.getEstadoServidorPort();

            String fechaInicioActual = estadoWs.getFechaInicio();

            if (ultimaFechaInicio == null) {
                ultimaFechaInicio = fechaInicioActual;
            } else if (!ultimaFechaInicio.equals(fechaInicioActual)) {
                // 🔥 Se detectó un reinicio del servidor central
                ultimaFechaInicio = fechaInicioActual;

                // Invalidar sesión
                HttpSession sesion = request.getSession(false);
                if (sesion != null) {
                    sesion.invalidate();
                }

                // Borrar cookie JSESSIONID
                Cookie cookie = new Cookie("JSESSIONID", "");
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);

                System.out.println("[SidebarCategorias] ⚠️ Servidor central reiniciado. Sesión invalidada.");
                response.sendRedirect(request.getContextPath() + "/LoginServlet");
                return;
            }

        } catch (Exception e) {
            System.out.println("[SidebarCategorias] ⚠️ No se pudo conectar al EstadoServidorWS: " + e.getMessage());
            // Si querés, también podés invalidar sesión aquí
        }

        // 🧩 Cargar categorías normalmente
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
