package registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import excepciones.EventoNoExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.Fabrica;
import logica.datatypes.DataEdicion;
import logica.datatypes.DataEvento;
import logica.datatypes.DataRegistro;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;
import logica.datatypes.DataTipoRegistro;


@WebServlet("/registroEd")
@MultipartConfig
public class registroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final Fabrica fabrica = Fabrica.getInstance();
    private final IControladorEvento ctrlEv = fabrica.getControladorEvento();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
    	String op = req.getParameter("op");
         String nombreEdicion = (req.getParameter("idEdicion") != null) ? req.getParameter("idEdicion") : "";
         String nickname = null;
         String rol = "visitante";
         
         try {
             switch (op) {
             case "alta":
            	 String idTipoReg = req.getParameter("id");
                 if (idTipoReg == null || idTipoReg.isEmpty()) {
                     res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' del tipo reg.");
                     return;
                 }
                 try {
                	 String nomEvento = ctrlEv.getEventoDeUnaEdicion(nombreEdicion);
                	 DataEdicion edicion = ctrlEv.getInfoEdicion(nomEvento);
                	 LocalDate fechaIni = edicion.getFechaIni();
                	 LocalDate fechaFin = edicion.getFechaFin();
                	 String ciudad = edicion.getCiudad();
                	 String pais = edicion.getPais();
                	 DataTipoRegistro tiporeg = ctrlEv.getTipoRegistro(nomEvento,nombreEdicion,idTipoReg);
                 	  Integer costo = tiporeg.getCosto();
                   	 req.setAttribute("fechaIni", fechaIni);
                   	 req.setAttribute("fechaFin", fechaFin);
                   	 req.setAttribute("ciudad", ciudad);
                   	 req.setAttribute("pais", pais);
                  	 req.setAttribute("nomEvento", nomEvento);
                  	 req.setAttribute("nomTipoReg", idTipoReg);
                  	 req.setAttribute("nomEdicion", nombreEdicion);
                  	 req.setAttribute("costo", costo );
                	 
                 }catch (EventoNoExisteException e) {
                     res.sendError(HttpServletResponse.SC_NOT_FOUND, "El evento no existe.");
                 }
                 break;
                 
//////////////////////////////////////////////////////ACA empieza LA QUE TENEMOS Q COMPARAR//////////////////////////////////////////////////////////////////////

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
     	        	 DataRegistro registroAsistente = ctrlEv.listarUnRegistroDeUsuario(nombreEdicion,nickname);
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
//////////////////////////////////////////////////////ACA EMPIEZA LA QUE TENEMOS Q COMPARAR//////////////////////////////////////////////////////////////////////

case "consultar": {
    HttpSession sesion = req.getSession(false);
    if (sesion != null) {
        rol = (String) sesion.getAttribute("rol");
        nickname = (String) sesion.getAttribute("usuario");
    }

    // 1️⃣ ORGANIZADOR
    if ("organizador".equalsIgnoreCase(rol)) {

        // Si no viene idEdicion → listar sus ediciones
        if (req.getParameter("idEdicion") == null) {
            DataEdicion[] edicionesOrg = ctrlEv.listarEdicionesDeOrganizador(nickname);
            req.setAttribute("ediciones", edicionesOrg);
            req.getRequestDispatcher("/WEB-INF/pages/listarEdicionesOrganizador.jsp").forward(req, res);
            break;
        }

        String idEdicion = req.getParameter("idEdicion");

        // Si no viene idUsuario → listar usuarios registrados en esa edición
        if (req.getParameter("idUsuario") == null) {
            DataRegistro[] registros = ctrlEv.listarRegistrosDeEdicion(idEdicion);
            req.setAttribute("registros", registros);
            req.setAttribute("idEdicion", idEdicion);
            req.getRequestDispatcher("/WEB-INF/pages/listarRegistrosEdicion.jsp").forward(req, res);
            break;
        }

        // Si viene idUsuario → mostrar detalle del registro
        String idUsuario = req.getParameter("idUsuario");
        DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, idUsuario);
        req.setAttribute("registro", registro);
        req.getRequestDispatcher("/WEB-INF/pages/detalleRegistro.jsp").forward(req, res);
        break;
    }

    // 2️⃣ ASISTENTE
    if ("asistente".equalsIgnoreCase(rol)) {

        // Si no viene idEdicion → listar ediciones donde está registrado
        if (req.getParameter("idEdicion") == null) {
            DataEdicion[] ediciones = ctrlEv.listarEdicionesDeAsistente(nickname);
            req.setAttribute("ediciones", ediciones);
            req.getRequestDispatcher("/WEB-INF/pages/listarEdicionesAsistente.jsp").forward(req, res);
            break;
        }

        // Si viene idEdicion → mostrar detalle de su propio registro
        String idEdicion = req.getParameter("idEdicion");
        DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, nickname);
        req.setAttribute("registro", registro);
        req.getRequestDispatcher("/WEB-INF/pages/detalleRegistro.jsp").forward(req, res);
        break;
    }

    // 3️⃣ VISITANTE (sin login)
    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Debe iniciar sesión para consultar registros.");
    break;
}
//////////////////////////////////////////////////////ACA TERMINA LA QUE TENEMOS Q COMPARAR//////////////////////////////////////////////////////////////////////
    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");

    	System.out.println("Valor de opt: " + req.getParameter("opt"));
    	System.out.println("Nombre evento: " + req.getParameter("id"));
        String opt = req.getParameter("opt");
        if (opt != null && opt.equals("alta")) {
            altaRegistro(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Operación POST no válida.");
        }
    }
    
    
    private void altaRegistro(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	
        HttpSession sesion = req.getSession(false);
        String rol = "visitante";
        String nickname = null;

        if (sesion != null) {
            rol = (String) sesion.getAttribute("rol");
            nickname = (String) sesion.getAttribute("usuario");
        }

        req.setAttribute("rol", rol);
        req.setAttribute("nickname", nickname);

        final String nombreEvento = req.getParameter("id");
        final String nombreEdicion = req.getParameter("nombre");
        final String nombreTipoRegistro = req.getParameter("tiporegistro");
        final String nombreAsistente = req.getParameter("nombreAsistente");
       


        if (nombreEdicion == null || nombreEdicion.isEmpty() ||
            nombreEvento == null || nombreEvento.isEmpty()) {

            req.setAttribute("error", "Faltan datos obligatorios para crear el registro.");
            req.getRequestDispatcher("/WEB-INF/pages/registroaEdicion.jsp").forward(req, res);
            return;
        }
        
        try {
            ctrlEv.altaRegistro(
                    nombreEvento,
                    nombreEdicion,
                    nombreTipoRegistro,
                    nombreAsistente,
                    LocalDate.now()
            );
            //altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha)

            // esto si hay exito redirige
            res.sendRedirect(req.getContextPath() + "/registroEd?op=consultar&id=" + nombreEvento);

        } catch (UsuarioNoExisteException e) {
            req.setAttribute("error", "El organizador no existe o no tiene permisos.");
            req.getRequestDispatcher("/WEB-INF/pages/registroaEdicion.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al registrar la edición.");
            req.getRequestDispatcher("/WEB-INF/pages/registroaEdicion.jsp").forward(req, res);
        }
    }

    	
    	
    }
    
    
    
    
    
    
    
    
    
    