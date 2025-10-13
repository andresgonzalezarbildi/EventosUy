package evento;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import excepciones.EventoNoExisteException;
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
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;

@WebServlet("/edicion")
@MultipartConfig
public class EdicionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Fabrica fabrica = Fabrica.getInstance();
    private IControladorEvento controladorEventos = fabrica.getControladorEvento();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String op = (req.getParameter("op") != null) ? req.getParameter("op").toLowerCase() : "";

        if ("alta".equals(op)) {
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
        } else {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación GET no válida.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String opt = req.getParameter("opt");
        if ("alta".equals(opt)) {
            altaEdicion(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación POST no válida.");
        }
    }

    private void altaEdicion(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession(false);
        String nickname = (sesion != null) ? (String) sesion.getAttribute("usuario") : null;

        final String nombreEdicion = req.getParameter("nombre");
        final String sigla = req.getParameter("sigla");
        final String ciudad = req.getParameter("ciudad");
        final String pais = req.getParameter("pais");
        final String nombreEvento = req.getParameter("id");
        final String fechaInicioStr = req.getParameter("fechaInicio");
        final String fechaFinStr = req.getParameter("fechaFin");

        // Validación campos obligatorios
        if (nombreEdicion == null || sigla == null || nombreEvento == null ||
            nombreEdicion.isEmpty() || sigla.isEmpty() || nombreEvento.isEmpty()) {
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "Faltan datos obligatorios para crear la edición.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        LocalDate fechaInicio;
        LocalDate fechaFin;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr);
            fechaFin = LocalDate.parse(fechaFinStr);
        } catch (Exception e) {
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "Formato de fecha inválido.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        // Validación: inicio <= fin
        if (fechaInicio.isAfter(fechaFin)) {
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "La fecha de inicio no puede ser posterior a la fecha de fin.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        // Validación: inicio > hoy
        if (!fechaInicio.isAfter(LocalDate.now())) {
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "La fecha de inicio debe ser posterior a la fecha actual.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        // Subir imagen
        String nombreImagenGuardada = null;
        try {
            Part filePart = req.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0) {
                String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ext = "";
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
        } catch (Exception e) {
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "Error al subir la imagen.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

        try {
            // Dar de alta la edición directamente
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

            // Redirigir al evento con UTF-8
            String redirectId = java.net.URLEncoder.encode(nombreEvento, "UTF-8");
            res.sendRedirect(req.getContextPath() + "/evento?op=consultar&id=" + redirectId);

        } catch (UsuarioNoExisteException e) {
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "El organizador no existe o no tiene permisos.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("nomEv", nombreEvento);
            req.setAttribute("error", "Ya existe la edición.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
        }
    }
}
