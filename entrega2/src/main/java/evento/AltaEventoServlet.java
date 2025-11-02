package evento;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ws.eventos.EventoRepetidoFault_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.nio.file.Paths;

import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.media.IOException_Exception;
import ws.media.MediaService;
import ws.media.MediaWs;

@MultipartConfig
@WebServlet("/evento/alta")
public class AltaEventoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final EventosService service = new EventosService();
    private final EventosWs controladorEventos = service.getEventosPort();

    private final MediaService mediaService = new MediaService();
    private final MediaWs mediaPort = mediaService.getMediaPort(); // o getMediaWsPort()

    public AltaEventoServlet() {
        super();
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

        // Categorías
        String[] categoriasArray = req.getParameterValues("categorias");
        List<String> nombresCategorias = new ArrayList<>();
        if (categoriasArray != null) {
            for (String c : categoriasArray) {
                if (c != null && !c.isBlank()) nombresCategorias.add(c.trim());
            }
        }

        // ---------- Esto es para subir la imagen: vamo mechu ----------
        String imagenFileName = null;
        Part filePart = null;
        try {
            filePart = req.getPart("imagen");
        } catch (Exception ignore) {
        }

        if (filePart != null && filePart.getSize() > 0) {
            String contentType = filePart.getContentType();

            if (contentType == null || !contentType.startsWith("image/")) {
                req.setAttribute("error",
                        "El archivo seleccionado no es una imagen válida. Solo se permiten JPG, PNG, GIF o WEBP.");
                repoblarYVolver(req, res, nombre, descripcion, sigla, nombresCategorias);
                return;
            }

            String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            byte[] bytes;
            try (InputStream in = filePart.getInputStream()) {
                bytes = in.readAllBytes();
            }

            try {
                imagenFileName = mediaPort.subirImagen(submitted, bytes);
            } catch (IOException_Exception ex) {
                req.setAttribute("error",
                        "El archivo seleccionado no es una imagen válida o ocurrió un error al subirla.");
                repoblarYVolver(req, res, nombre, descripcion, sigla, nombresCategorias);
                return;
            }
        }
        // ------------------------------------------------------------------

        String fechaAlta = LocalDate.now().toString();
        boolean exito = false;
        String mensaje;

        try {
            controladorEventos.altaEvento(
                    nombre, descripcion, sigla, nombresCategorias, fechaAlta, imagenFileName
            );
            mensaje = "El evento '" + nombre + "' fue creado correctamente.";
            exito = true;
        } catch (EventoRepetidoFault_Exception e) {
            mensaje = "Ya existe un evento con ese nombre.";
            repoblar(req, nombre, descripcion, sigla, nombresCategorias);
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error al crear el evento: " + e.getMessage();
            repoblar(req, nombre, descripcion, sigla, nombresCategorias);
        }

        if (exito) {
            res.sendRedirect(req.getContextPath() + "/");
        } else {
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

    private void repoblar(HttpServletRequest req, String nombre, String descripcion, String sigla,
                          List<String> nombresCategorias) {
        req.setAttribute("form_nombre", nombre);
        req.setAttribute("form_descripcion", descripcion);
        req.setAttribute("form_sigla", sigla);
        req.setAttribute("form_categorias_sel", nombresCategorias);
    }

    private void repoblarYVolver(HttpServletRequest req, HttpServletResponse res,
                                 String nombre, String descripcion, String sigla,
                                 List<String> nombresCategorias) throws ServletException, IOException {
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
