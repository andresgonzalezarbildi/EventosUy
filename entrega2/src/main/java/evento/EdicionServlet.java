package evento;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import excepciones.EventoNoExisteException;
import excepciones.EdicionNoExisteException;
import excepciones.UsuarioNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import logica.Fabrica;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;

@WebServlet("/edicion")
@MultipartConfig
public class EdicionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Fabrica fabrica = Fabrica.getInstance();
    private final IControladorEvento controladorEventos = fabrica.getControladorEvento();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String op = req.getParameter("op") != null ? req.getParameter("op").toLowerCase() : "";

        try {
            switch (op) {
                case "alta":
                    mostrarAlta(req, res);
                    break;
                case "listar":
                    listarEdiciones(req, res);
                    break;
                case "consultar":
                    consultarEdicion(req, res);
                    break;
                default:
                    res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible en GET.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en EdicionServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String opt = req.getParameter("opt");
        if ("alta".equalsIgnoreCase(opt)) {
            altaEdicion(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación POST no válida.");
        }
    }

    private void mostrarAlta(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String idEvento = req.getParameter("id");
        if (idEvento == null || idEvento.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' del evento.");
            return;
        }
        try {
            DataEvento evento = controladorEventos.getUnEventoDTO(idEvento);
            req.setAttribute("nomEv", evento.getNombre());
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
        } catch (EventoNoExisteException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "El evento no existe.");
        }
    }

    private void altaEdicion(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession(false);
        String nickname = (sesion != null) ? (String) sesion.getAttribute("usuario") : null;

        String nombreEdicion = req.getParameter("nombre");
        String sigla = req.getParameter("sigla");
        String ciudad = req.getParameter("ciudad");
        String pais = req.getParameter("pais");
        String nombreEvento = req.getParameter("id");
        String fechaInicioStr = req.getParameter("fechaInicio");
        String fechaFinStr = req.getParameter("fechaFin");

        if (nombreEdicion == null || nombreEdicion.isEmpty() ||
            sigla == null || sigla.isEmpty() ||
            nombreEvento == null || nombreEvento.isEmpty()) {

            req.setAttribute("error", "Faltan datos obligatorios para crear la edición.");
            req.setAttribute("nomEv", nombreEvento);
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        LocalDate fechaInicio;
        LocalDate fechaFin;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr);
            fechaFin = LocalDate.parse(fechaFinStr);
        } catch (Exception e) {
            req.setAttribute("error", "Formato de fecha inválido.");
            req.setAttribute("nomEv", nombreEvento);
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        // IMAGEN
        String nombreImagenGuardada = null;
        try {
            Part filePart = req.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0 && filePart.getContentType().startsWith("image/")) {
                String ext = "";
                String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                int dot = submitted.lastIndexOf('.');
                if (dot >= 0) ext = submitted.substring(dot).toLowerCase();

                String nuevoNombre = UUID.randomUUID().toString().replace("-", "") + ext;

                Path imgDir = Paths.get(getServletContext().getRealPath("/img"));
                Files.createDirectories(imgDir);

                Path destino = imgDir.resolve(nuevoNombre);
                try (InputStream in = filePart.getInputStream()) {
                    Files.copy(in, destino, StandardCopyOption.REPLACE_EXISTING);
                }

                nombreImagenGuardada = nuevoNombre;
            }
        } catch (IllegalStateException ex) {
            req.setAttribute("error", "Error al subir la imagen.");
            req.setAttribute("nomEv", nombreEvento);
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        // ALTA
        try {
            // Chequeo duplicado
            DataEdicion[] ediciones = controladorEventos.listarEdiciones(nombreEvento);
            for (DataEdicion ed : ediciones) {
                if (ed.getNombre().equalsIgnoreCase(nombreEdicion)) {
                    req.setAttribute("error", "Ya existe una edición con ese nombre para este evento.");
                    req.setAttribute("nomEv", nombreEvento);
                    req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
                    return;
                }
            }

            controladorEventos.altaEdicionEvento(
                    nombreEvento,
                    nombreEdicion,
                    sigla,
                    ciudad,
                    pais,
                    fechaInicio,
                    fechaFin,
                    LocalDate.now(),
                    nickname,
                    nombreImagenGuardada
            );

            res.sendRedirect(req.getContextPath() + "/evento?op=consultar&id=" + URLEncoder.encode(nombreEvento, "UTF-8"));

        } catch (UsuarioNoExisteException e) {
            req.setAttribute("error", "El organizador no existe o no tiene permisos.");
            req.setAttribute("nomEv", nombreEvento);
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al registrar la edición.");
            req.setAttribute("nomEv", nombreEvento);
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
        }
    }

    private void listarEdiciones(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String nombreEvento = req.getParameter("evento");
        if (nombreEvento == null || nombreEvento.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'evento'.");
            return;
        }

        try {
            DataEdicion[] ediciones = controladorEventos.listarEdicionesAceptadasEvento(nombreEvento);
            req.setAttribute("evento", nombreEvento);
            req.setAttribute("ediciones", ediciones);
            req.getRequestDispatcher("/WEB-INF/pages/listarEdiciones.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar las ediciones.");
        }
    }

    private void consultarEdicion(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, EventoNoExisteException, EdicionNoExisteException {
        req.setCharacterEncoding("UTF-8");
        String nombreEdicion = req.getParameter("id");
        if (nombreEdicion == null || nombreEdicion.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id'.");
            return;
        }

        DataEdicion ed = controladorEventos.getInfoEdicion(nombreEdicion);
        String nombreEvento = controladorEventos.getEventoDeUnaEdicion(nombreEdicion);

        req.setAttribute("edicion", ed);
        req.setAttribute("evento", nombreEvento);

        req.getRequestDispatcher("/WEB-INF/pages/consultaEdicion.jsp").forward(req, res);
    }
}
