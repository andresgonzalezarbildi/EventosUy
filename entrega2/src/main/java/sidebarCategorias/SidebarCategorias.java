package sidebarCategorias;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.WebServiceException;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;

import java.io.IOException;
import java.util.List;

/**
 * Servlet Filter implementation class sidebarCategorias
 */
@WebFilter("/*")
public class SidebarCategorias extends HttpFilter implements Filter {
  

  private static final long serialVersionUID = 1L;
  private EventosService service = null;
 
  public SidebarCategorias() {
      super();
      // TODO Auto-generated constructor stub
  }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
	    List<String> categorias = List.of();
      try {
          if(service == null) {
            service = new EventosService();
          }
          EventosWs controladorEvento = service.getEventosPort();
          List<String> remotas = controladorEvento.listarCategorias();
          if (remotas != null) categorias = remotas;
      }catch (WebServiceException e) {
        // manejo la lista vacia al inicio
      }
      
      request.setAttribute("categorias", categorias);
      chain.doFilter(request, response);
}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
