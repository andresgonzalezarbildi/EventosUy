package evento;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ws.eventos.EventoRepetidoFault_Exception;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.media.IOException_Exception;
import ws.media.MediaService;
import ws.media.MediaWs;



@MultipartConfig
@WebServlet("/evento/alta")
public class AltaEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventosService service = new EventosService();
	EventosWs controladorEventos = service.getEventosPort();
	private MediaService mediaService = new MediaService();
	private MediaWs mediaPort = mediaService.getMediaPort();
	 
       
    public AltaEventoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	try {
            List<String> categorias = controladorEventos.listarCategorias();
            req.setAttribute("categorias", categorias);
        } catch (Exception e) {
            req.setAttribute("categorias", java.util.Collections.emptyList());
        }
        req.getRequestDispatcher("/WEB-INF/pages/altaEvento.jsp").forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String sigla = req.getParameter("sigla"); 
        
        //categorias:
        String[] categoriasArray = req.getParameterValues("categorias");
        List<String> nombresCategorias = new ArrayList<>();
        if (categoriasArray != null) {
            for (String c : categoriasArray) {
                if (c != null && !c.isBlank()) nombresCategorias.add(c.trim());
            }
        }
        
    	// subir imagen
        String imagenFileName = null;
        Part filePart = req.getPart("imagen");

        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                String submitted = Paths.get(filePart.getSubmittedFileName())
                                         .getFileName().toString();
                byte[] bytes;
                try (InputStream in = filePart.getInputStream()) {
                    bytes = in.readAllBytes();
                }
                try {
                  imagenFileName = mediaPort.subirImagen(submitted, bytes);
                } catch (IOException_Exception e) {
                }
            }
        }
        
        LocalDate fechaAltaEnPlataforma = LocalDate.now();
        String FechaAltaAstring = fechaAltaEnPlataforma.toString();
        boolean exito = false;
        String mensaje;
        try {
            controladorEventos.altaEvento(nombre, descripcion, sigla, nombresCategorias, FechaAltaAstring,imagenFileName);
            mensaje = "El evento '" + nombre + "' fue creado correctamente.";
            exito = true; // marcar que se creó correctamente
        } catch (EventoRepetidoFault_Exception e) {
            mensaje = "Ya existe un evento con ese nombre.";
            repoblar(req, nombre, descripcion, sigla, nombresCategorias);
        } catch (Exception e) {
            mensaje = "Error al crear el evento: " + e.getMessage();
            repoblar(req, nombre, descripcion, sigla, nombresCategorias);
        }

        if (exito) {
            // redirigir al menú principal
            res.sendRedirect(req.getContextPath() + "/"); // o la ruta que corresponda al menú principal
        } else {
            // seguir mostrando el formulario con el mensaje
            req.setAttribute("mensaje", mensaje);
            try {
                List<String> categorias = controladorEventos.listarCategorias();
                req.setAttribute("categorias", categorias);
            } catch (Exception e) {
                req.setAttribute("categorias", java.util.Collections.emptyList());
            }
            req.getRequestDispatcher("/WEB-INF/pages/altaEvento.jsp").forward(req, res);
        }

    }
    private void repoblar(HttpServletRequest req, String nombre, String descripcion, String sigla, List<String> nombresCategorias) {
		req.setAttribute("form_nombre", nombre);
		req.setAttribute("form_descripcion", descripcion);
		req.setAttribute("form_sigla", sigla);
		req.setAttribute("form_categorias_sel", nombresCategorias);
		}
    private void repoblarYVolver(HttpServletRequest req, HttpServletResponse res, String nombre, String descripcion, String sigla, List<String> nombresCategorias) throws ServletException, IOException {
		repoblar(req, nombre, descripcion, sigla, nombresCategorias);
		try {
		List<String> categorias = controladorEventos.listarCategorias();
		req.setAttribute("categorias", categorias);
		} catch (Exception e) {
		req.setAttribute("categorias", java.util.Collections.emptyList());
		}
		req.getRequestDispatcher("/WEB-INF/pages/altaEvento.jsp").forward(req, res);
		}
}
