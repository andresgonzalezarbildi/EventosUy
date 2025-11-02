package evento;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.net.URLEncoder;
import ws.eventos.EdicionNoExisteFault_Exception;
import ws.eventos.EventoNoExisteFault;
import ws.eventos.EventoNoExisteFault_Exception;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.usuario.UsuarioNoExisteFault_Exception;
import ws.usuario.UsuarioService;
import ws.usuario.UsuarioWs;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import ws.eventos.DataEdicion;

@WebServlet("/edicion")
@MultipartConfig
public class EdicionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EventosService service = new EventosService();
	 EventosWs controladorEventos = service.getEventosPort();
	 private UsuarioService serviceUs = new UsuarioService();
	 UsuarioWs cu = serviceUs.getUsuarioPort();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String op = req.getParameter("op") != null ? req.getParameter("op").toLowerCase() : "";
        String nombreEdicion = req.getParameter("id") != null ? req.getParameter("id") : "";
        String nickname = null;
        String rol = "visitante";

        try {
            switch (op) {
                case "alta":
                    String idEvento = req.getParameter("id");
                    if (idEvento == null || idEvento.isEmpty()) {
                        req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, res);
                    }
                    try {
                    	ws.eventos.DataEvento evento = controladorEventos.getUnEventoDTO(idEvento);
                        req.setAttribute("nomEv", evento.getNombre());
                        req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
                    } catch (EventoNoExisteFault_Exception e) {
                        req.setAttribute("mensajeError", "El evento no existe.");
                        req.setAttribute("javax.servlet.error.status_code", 500);
                        req.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(req, res);
                    }
                    break;

                case "listar":
                    listarEdiciones(req, res);
                    break;
                    
                case "baja":
                    HttpSession session = req.getSession(false);
                    if (session == null || session.getAttribute("usuario") == null) {
                    	res.sendRedirect(req.getContextPath() + "/eventos");
                        return;
                    }
                    String usuario =
                    		(String) session.getAttribute("usuario");
                    ws.usuario.DataUsuario datausu = cu.verInfoUsuario(usuario);
                    if (datausu.getTipo() == null || !datausu.getTipo().equalsIgnoreCase("organizador")) {
                    	res.sendRedirect(req.getContextPath() + "/eventos");
                        return;
                    }
                    String idEventoa = req.getParameter("id");
                    if (idEventoa != null && !idEventoa.isEmpty()) {
                        controladorEventos.finalizarEvento(idEventoa);
                    }
                    res.sendRedirect(req.getContextPath() + "/eventos");
                    break;

                case "consultar":
                    HttpSession sesion = req.getSession(false);
                    if (sesion != null) {
                        rol = (String) sesion.getAttribute("rol");
                        nickname = (String) sesion.getAttribute("usuario");
                        req.setAttribute("rol", rol);
                        req.setAttribute("nickname", nickname);
                    } else {
                        req.setAttribute("rol", "visitante");
                    }

                    if (nombreEdicion.isEmpty()) {
                      req.getRequestDispatcher("/WEB-INF/pages/consultaEdicion.jsp").forward(req, res);
                        return;
                    }

                    if (nickname != null && "asistente".equalsIgnoreCase(rol)) {
                      try {
                        ws.eventos.DataRegistro registroAsistente = controladorEventos.listarUnRegistroDeUsuario(nombreEdicion, nickname);
                        req.setAttribute("registroAsistente", registroAsistente);
                      } catch (Exception e) {
                        req.getRequestDispatcher("/WEB-INF/pages/consultaEdicion.jsp").forward(req, res);
                      }
                    }
                    
                    try {
                      ws.eventos.DataEdicion dataEd = controladorEventos.getInfoEdicion(nombreEdicion);
                      List<ws.eventos.DataRegistro> registrosEd = controladorEventos.listarRegistrosDeEdicion(nombreEdicion);
                      req.setAttribute("registrosEd", registrosEd);
                      req.setAttribute("edicion", dataEd);
                      String nombreEvento = controladorEventos.getEventoDeUnaEdicion(nombreEdicion);
                      ws.eventos.DataEvento dataEvento = controladorEventos.getUnEventoDTO(nombreEvento);
                      req.setAttribute("evento", dataEvento);
                    }catch (EdicionNoExisteFault_Exception | EventoNoExisteFault_Exception  ignored) {
                      
                    }
                    req.getRequestDispatcher("/WEB-INF/pages/consultaEdicion.jsp").forward(req, res);
                    break;

                default:
                  req.setAttribute("mensajeError", "No se encontro la pagina " );
                  req.setAttribute("javax.servlet.error.status_code", 404);
                  req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
                  break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servlet de Edición.");
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

    private void altaEdicion(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession(false);
        String rol = "visitante";
        String nickname = null;

        if (sesion != null) {
            rol = (String) sesion.getAttribute("rol");
            nickname = (String) sesion.getAttribute("usuario");
        }

        req.setAttribute("rol", rol);
        req.setAttribute("nickname", nickname);

        final String nombreEdicion = req.getParameter("nombre");
        final String sigla = req.getParameter("sigla");
        final String ciudad = req.getParameter("ciudad");
        final String pais = req.getParameter("pais");
        final String nombreEvento = req.getParameter("id");
        final String fechaInicioStr = req.getParameter("fechaInicio");
        final String fechaFinStr = req.getParameter("fechaFin");

        // Validación de datos obligatorios
        if (nombreEdicion == null || nombreEdicion.isEmpty() ||
            sigla == null || sigla.isEmpty() ||
            nombreEvento == null || nombreEvento.isEmpty()) {

            setErrorYReenviar(req, res, "Faltan datos obligatorios para crear la edición.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }

        

        // Validar formato básico (que no estén vacías y sigan el patrón ISO)
        if (fechaInicioStr == null || fechaFinStr == null ||
            fechaInicioStr.isBlank() || fechaFinStr.isBlank()) {
            setErrorYReenviar(req, res, "Debe ingresar ambas fechas.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }

        // Verificar formato correcto (opcional pero más robusto)
        if (!fechaInicioStr.matches("\\d{4}-\\d{2}-\\d{2}") ||
            !fechaFinStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            setErrorYReenviar(req, res, "Formato de fecha inválido. Use YYYY-MM-DD.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }

        // Validar relaciones entre fechas (sin usar LocalDate)
        String hoy = java.time.LocalDate.now().toString();

        // Comparar lexicográficamente porque las fechas ISO mantienen orden temporal
        if (fechaInicioStr.compareTo(hoy) < 0) {
            setErrorYReenviar(req, res, "La fecha de inicio no puede ser anterior a la fecha actual.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }

        if (fechaFinStr.compareTo(fechaInicioStr) < 0) {
            setErrorYReenviar(req, res, "La fecha de fin no puede ser anterior a la fecha de inicio.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }

        // Si llegás hasta acá, las fechas son válidas


        // Validar que la fecha de inicio de la edición no sea anterior a la del evento
        ws.eventos.DataEvento eventoDTO;
        try {
            eventoDTO = controladorEventos.getUnEventoDTO(nombreEvento);
        } catch (EventoNoExisteFault_Exception e) {
            setErrorYReenviar(req, res, "El evento no existe.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }
        String fechaAltaEvento = eventoDTO.getFechaAlta(); // ya viene como String del WS
        if (fechaInicioStr.compareTo(fechaAltaEvento) < 0) {
            setErrorYReenviar(req, res, 
                "La fecha de inicio de la edición no puede ser anterior a la fecha de alta del evento.",
                nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }

        // IMAGEN
        String nombreImagenGuardada = null;
        try {
            Part filePart = req.getPart("imagen");
            if (filePart != null && filePart.getSize() > 0 && filePart.getContentType().startsWith("image/")) {

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
        } catch (IllegalStateException ex) {
            setErrorYReenviar(req, res, "Error al subir la imagen (tamaño excedido o inválido).",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
            return;
        }
        String fechaAltaParam = LocalDate.now().toString();


        try {
            controladorEventos.altaEdicionEvento(
                    nombreEvento,
                    nombreEdicion,
                    sigla,
                    ciudad,
                    pais,
                    fechaInicioStr,
                    fechaFinStr,
                    fechaAltaParam,
                    nickname,
                    nombreImagenGuardada
            );
            res.sendRedirect(req.getContextPath() + "/evento?op=consultar&id=" +
                    URLEncoder.encode(nombreEvento, "UTF-8"));
        } 
//        catch (EventoNoExisteFault_Exception e) {
//            setErrorYReenviar(req, res, "El organizador no existe o no tiene permisos.",
//                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
//        } 
        catch (Exception e) {
            e.printStackTrace();
            setErrorYReenviar(req, res, "Error al registrar la edición.",
                    nombreEdicion, sigla, ciudad, pais, fechaInicioStr, fechaFinStr, nombreEvento);
        }
    }

    private void setErrorYReenviar(HttpServletRequest req, HttpServletResponse res,
                                   String mensajeError, String nombreEdicion, String sigla,
                                   String ciudad, String pais, String fechaInicioStr,
                                   String fechaFinStr, String nombreEvento)
            throws ServletException, IOException {
        req.setAttribute("error", mensajeError);
        req.setAttribute("nombre", nombreEdicion);
        req.setAttribute("sigla", sigla);
        req.setAttribute("ciudad", ciudad);
        req.setAttribute("pais", pais);
        req.setAttribute("fechaInicio", fechaInicioStr);
        req.setAttribute("fechaFin", fechaFinStr);
        req.setAttribute("nomEv", nombreEvento);
        req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
    }

    private void listarEdiciones(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String nombreEvento = req.getParameter("evento");
        if (nombreEvento == null || nombreEvento.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'evento'.");
            return;
        }
        try {
        	List<ws.eventos.DataEdicion> ediciones = controladorEventos.listarEdicionesAceptadasEvento(nombreEvento);
            req.setAttribute("evento", nombreEvento);
            req.setAttribute("ediciones", ediciones);
            req.getRequestDispatcher("/WEB-INF/pages/listarEdiciones.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar las ediciones.");
        }
    }
}
