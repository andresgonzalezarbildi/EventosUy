package sidebarCategorias;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import logica.Fabrica;
import logica.interfaces.IControladorEvento;

import java.io.IOException;
import java.util.List;

/**
 * Servlet Filter implementation class sidebarCategorias
 */
@WebFilter("/*")
public class sidebarCategorias extends HttpFilter implements Filter {
  

  private static final long serialVersionUID = 1L;
  private Fabrica fabrica = Fabrica.getInstance();
  private IControladorEvento controladorEvento = fabrica.getControladorEvento();
 
  public sidebarCategorias() {
      super();
      // TODO Auto-generated constructor stub
  }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    if (request instanceof HttpServletRequest req) {
        // Solo cargamos las categorías si no están ya en el request
        if (req.getAttribute("categorias") == null) {
            List<String> categorias = controladorEvento.listarCategorias();
            req.setAttribute("categorias", categorias);
        }
    }

    // Continuar con la cadena (otros filtros, servlets, JSPs)
    chain.doFilter(request, response);
}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
