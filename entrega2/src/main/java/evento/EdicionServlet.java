package evento;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.datatypes.DataTipoRegistro;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

@WebServlet("/edicion")
@jakarta.servlet.annotation.MultipartConfig
//@MultipartConfig
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
        String nombreEdicion = (req.getParameter("id") != null) ? req.getParameter("id") : "";
        String nickname = null;
        String rol = "visitante";
        

        try {
            switch (op) {
            case "alta":
                String idEvento = req.getParameter("id");
                if (idEvento == null || idEvento.isEmpty()) {
                    res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' del evento.");
                    return;
                }

                try {
                    DataEvento evento = controladorEventos.getUnEventoDTO(idEvento);
                    String nomEv = evento.getNombre();
                    req.setAttribute("nomEv", nomEv);
                    req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
                } catch (EventoNoExisteException e) {
                    res.sendError(HttpServletResponse.SC_NOT_FOUND, "El evento no existe.");
                }
                break;
                case "listar":
                    listarEdiciones(req, res);
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
        	        if (nombreEdicion == null || nombreEdicion.isEmpty()) {
        	            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' de la edición.");
        	            return;
        	        }
        	            if (nickname != null && "asistente".equalsIgnoreCase(rol)) {        	            
        	        	 DataRegistro registroAsistente = controladorEventos.listarUnRegistroDeUsuario(nombreEdicion,nickname);
        	        	 req.setAttribute("registroAsistente", registroAsistente);
        	        }     	                              
        	            DataEvento[] eventosArray = null;
        	            List<DataEvento> eventosRelacionados = Collections.emptyList();

        	            try {
        	                eventosArray = controladorEventos.listarEventoExistentes();
        	                if (eventosArray != null) {
        	                	eventosRelacionados = Arrays.asList(eventosArray);
        	                }
        	            } catch (EventoNoExisteException e) {}
        	            req.setAttribute("eventosRelacionados", eventosRelacionados);    
                    DataEdicion dataEd = controladorEventos.getInfoEdicion(nombreEdicion);
                    DataRegistro[] registrosEd = controladorEventos.listarRegistrosDeEdicion(nombreEdicion);  
                    req.setAttribute("registrosEd", registrosEd);
                    req.setAttribute("edicion", dataEd);
                    req.getRequestDispatcher("/WEB-INF/pages/consultaEdicion.jsp").forward(req, res);
                    break;
                	default:
                    res.sendError(HttpServletResponse.SC_NOT_FOUND, "Operación no disponible en el GET.");
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

    	System.out.println("Valor de opt: " + req.getParameter("opt"));
    	System.out.println("Nombre evento: " + req.getParameter("id"));
        String opt = req.getParameter("opt");
        if (opt != null && opt.equals("alta")) {
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

       
        if (nombreEdicion == null || nombreEdicion.isEmpty() ||
            sigla == null || sigla.isEmpty() ||
            nombreEvento == null || nombreEvento.isEmpty()) {

            req.setAttribute("error", "Faltan datos obligatorios para crear la edición.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

    
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        try {
            fechaInicio = LocalDate.parse(fechaInicioStr);
            fechaFin = LocalDate.parse(fechaFinStr);
        } catch (Exception e) {
            req.setAttribute("error", "Formato de fecha inválido.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

      //IMAGENNNNNNNNNNNNN
        String nombreImagenGuardada = null;
        try {
            Part filePart = req.getPart("imagen");

            if (filePart != null && filePart.getSize() > 0) {
                String contentType = filePart.getContentType();
                if (contentType != null && contentType.startsWith("image/")) {

                    
                    String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String ext = "";
                    int dot = submitted.lastIndexOf('.');
                    if (dot >= 0 && dot < submitted.length() - 1) {
                        ext = submitted.substring(dot).toLowerCase();
                    }

                    
                    String nuevoNombre = UUID.randomUUID().toString().replace("-", "") + ext;

                    
                    String imgDirPath = getServletContext().getRealPath("/img");
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

        } catch (IllegalStateException ex) {
            req.setAttribute("error", "Error al subir la imagen (tamaño excedido o inválido).");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
            return;
        }

       
        try {
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
            res.sendRedirect(req.getContextPath() + "/evento?op=consultar&id=" + nombreEvento);

        } catch (UsuarioNoExisteException e) {
            req.setAttribute("error", "El organizador no existe o no tiene permisos.");
            req.getRequestDispatcher("/WEB-INF/pages/altaEdicion.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al registrar la edición.");
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
}
