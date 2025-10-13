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

import excepciones.EventoRepetidoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import logica.Fabrica;
import logica.interfaces.IControladorEvento;


@MultipartConfig
@WebServlet("/evento/alta")
public class AltaEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Fabrica fabrica = Fabrica.getInstance();
    private IControladorEvento controladorEventos = fabrica.getControladorEvento();
       
    public AltaEventoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	try {
            List<String> categorias = controladorEventos.ListarCategorias();
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
        String nombreImagenGuardada = null;
        Part filePart = null;
        try {
            filePart = req.getPart("imagen"); // nombre de l aimagen
        } catch (IllegalStateException ex) {
            req.setAttribute("error", "error al subir la imagen");
            repoblarYVolver(req, res, nombre, descripcion, sigla, nombresCategorias);
            return;
        } catch (ServletException | IOException ex) {
            filePart = null;
        }

        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ext = "";
                int dot = submitted.lastIndexOf('.');
                if (dot >= 0 && dot < submitted.length() - 1) ext = submitted.substring(dot).toLowerCase();
                String nuevoNombre = UUID.randomUUID().toString().replace("-", "") + ext;

                String imgDirPath = getServletContext().getRealPath("/img"); // <- carpeta pública
                if (imgDirPath != null) {
                    Path imgDir = Paths.get(imgDirPath);
                    Files.createDirectories(imgDir);
                    Path destino = imgDir.resolve(nuevoNombre);
                    try (InputStream in = filePart.getInputStream()) {
                        Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
                    }
                    nombreImagenGuardada = nuevoNombre;
                } 
            }
        }
        
        LocalDate fechaAltaEnPlataforma = LocalDate.now();
        String mensaje;
        try {
            controladorEventos.altaEvento(nombre,descripcion,sigla,nombresCategorias,fechaAltaEnPlataforma,nombreImagenGuardada);
            mensaje = "El evento '" + nombre + "'fue creado correctamente.";
        } catch (EventoRepetidoException e) {
            mensaje = "Ya existe un evento con ese nombre.";
        } catch (Exception e) {
            mensaje = "Error al crear el evento: " + e.getMessage();
        }
        req.setAttribute("mensaje", mensaje);
        req.getRequestDispatcher("/WEB-INF/pages/altaEvento.jsp").forward(req, res);
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
		List<String> categorias = controladorEventos.ListarCategorias();
		req.setAttribute("categorias", categorias);
		} catch (Exception e) {
		req.setAttribute("categorias", java.util.Collections.emptyList());
		}
		req.getRequestDispatcher("/WEB-INF/pages/altaEvento.jsp").forward(req, res);
		}
}
