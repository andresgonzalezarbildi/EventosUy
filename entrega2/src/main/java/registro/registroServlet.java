package registro;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ws.eventos.EventosService;
import ws.eventos.EventosWs;
import ws.eventos.UsuarioNoExisteFault_Exception;

@WebServlet("/registroEd")
@MultipartConfig
public class registroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private EventosService serviceEv = new EventosService();
	 EventosWs ctrlEv = serviceEv.getEventosPort();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String op = req.getParameter("op");
        String nombreEdicion = (req.getParameter("idEdicion") != null) ? req.getParameter("idEdicion") : "";
        String nickname = null;
        String rol = "visitante";
        
        try {
            switch (op) {
                case "alta": {
                    String idTipoReg = req.getParameter("id");
                    if (idTipoReg == null || idTipoReg.isEmpty()) {
                        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' del tipo reg.");
                        return;
                    }

                    String nomEvento = ctrlEv.getEventoDeUnaEdicion(nombreEdicion);
          					ws.eventos.DataEdicion edicion = ctrlEv.getInfoEdicion(nombreEdicion);
          					String fechaIni = edicion.getFechaIni();
          					String fechaFin = edicion.getFechaFin();
          					String hoy = java.time.LocalDate.now().toString();
          					if(fechaFin.compareTo(hoy) < 0) {
                      req.setAttribute("mensajeError", "La edicion " + edicion.getNombre() + " ya termino, no puede registrarse");
                      req.setAttribute("javax.servlet.error.status_code", 409);
                      req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
                      break;
          					}
          					String ciudad = edicion.getCiudad();
          					String pais = edicion.getPais();
          					ws.eventos.DataTipoRegistro tiporeg = ctrlEv.getTipoRegistro(nomEvento, nombreEdicion, idTipoReg);
          					Integer costo = tiporeg.getCosto();
          
          					req.setAttribute("fechaIni", fechaIni);
          					req.setAttribute("fechaFin", fechaFin);
          					req.setAttribute("ciudad", ciudad);
          					req.setAttribute("pais", pais);
          					req.setAttribute("nomEvento", nomEvento);
          					req.setAttribute("nomTipoReg", idTipoReg);
          					req.setAttribute("nomEdicion", nombreEdicion);
          					req.setAttribute("costo", costo);
          
          					String flashOk  = (String) req.getSession().getAttribute("flash_ok");
          					String flashErr = (String) req.getSession().getAttribute("flash_error");
          					if (flashOk != null) {
          					    req.setAttribute("flash_ok", flashOk);
          					    req.getSession().removeAttribute("flash_ok");
          					}
          					if (flashErr != null) {
          					    req.setAttribute("flash_error", flashErr);
          					    req.getSession().removeAttribute("flash_error");
          					}
                        req.getRequestDispatcher("/WEB-INF/pages/registroaEdicion.jsp").forward(req, res);
                        break;
                    }

                default: {
                  req.setAttribute("mensajeError", "No se encontro la pagina " );
                  req.setAttribute("javax.servlet.error.status_code", 404);
                  req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
                  break;
                }
                
                
                case "consultar": {
            	    HttpSession sesion = req.getSession(false);
            	  if (sesion != null) {
            	        rol = (String) sesion.getAttribute("rol");
            	        nickname = (String) sesion.getAttribute("usuario");
            	    }

            	    if ("organizador".equalsIgnoreCase(rol)) {
            	    	String idEdicion = req.getParameter("idEdicion");
            	    	ws.eventos.DataEdicion edicion = ctrlEv.getInfoEdicion(idEdicion);
            	    	String imagen = edicion.getImagen();
            	    	String asis = req.getParameter("idAsistente");
            	    	ws.eventos.DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, asis);
            	    	String fechaRegistro = registro.getFecha();
            	    	String nomTipoRegistro = registro.getTipoRegistro();
            	    	
        	            req.setAttribute("rol", rol);
        	            req.setAttribute("idEdicion", idEdicion);
        	            req.setAttribute("imagen", imagen);
        	            req.setAttribute("nickname", asis);
        	            req.setAttribute("fechaRegistro", fechaRegistro);
        	            req.setAttribute("nomTipoRegistro", nomTipoRegistro);
            	        req.getRequestDispatcher("/WEB-INF/pages/consultaRegistro.jsp").forward(req, res);
            	        break;
            	       
            	    }
            	    // 2️⃣ ASISTENTE
            	    if ("asistente".equalsIgnoreCase(rol)) {
            	    	
          	        String idEdicion = req.getParameter("edicion");
          	        ws.eventos.DataEdicion edicion = ctrlEv.getInfoEdicion(idEdicion);
          	        String nomEvento = req.getParameter("evento");
            	    	ws.eventos.DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, nickname);
            	    	String fechaRegistro = registro.getFecha();
            	    	String nomTipoRegistro = registro.getTipoRegistro();
            	    	String organizador = edicion.getOrganizador();
            	    	String fechaIni = edicion.getFechaIni();
            	    	String fechaFin = edicion.getFechaFin();
            	    	String imagen = edicion.getImagen();
            	    	String ciudad = edicion.getCiudad();
            	    	String pais = edicion.getPais();
            	    	Integer costo = registro.getCosto();
            	    	
            	    	req.setAttribute("rol", rol);
            	    	req.setAttribute("imagen", imagen);
            	    	req.setAttribute("nickname", nickname);
            	    	req.setAttribute("idEdicion", idEdicion);
            	    	req.setAttribute("nomEvento", nomEvento);
            	    	req.setAttribute("fechaRegistro", fechaRegistro);
            	    	req.setAttribute("nomTipoRegistro", nomTipoRegistro);
            	    	req.setAttribute("organizador", organizador);
            	    	req.setAttribute("fechaIni", fechaIni);
            	    	req.setAttribute("fechaFin", fechaFin);
            	    	req.setAttribute("ciudad", ciudad);
            	    	req.setAttribute("pais", pais);
            	    	req.setAttribute("costo", costo);

            	        req.getRequestDispatcher("/WEB-INF/pages/consultaRegistro.jsp").forward(req, res);
            	        return;
            	    }
            	
            	    // 3️⃣ VISITANTE (sin login)
            	    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Debe iniciar sesión para consultar registros.");
            	    break;
            	    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servlet de REGISTRO.");
        }
    }


             	

	

    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");

    	
        String opt = req.getParameter("opt");
        if (opt != null && opt.equals("alta")) {
            altaRegistro(req, res);
        } else {
          req.setAttribute("mensajeError", "No se encontro la pagina " );
          req.setAttribute("javax.servlet.error.status_code", 404);
          req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
        }
    }
    
    
    private void altaRegistro(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        HttpSession sesion = req.getSession(false);
        String rol = "visitante";
        String nickname = null;

        if (sesion != null) {
            rol = (String) sesion.getAttribute("rol");
            nickname = (String) sesion.getAttribute("usuario");
        }

        req.setAttribute("rol", rol);
        req.setAttribute("nickname", nickname);

        String nombreEdicion = (req.getParameter("idEdicion") != null) ? req.getParameter("idEdicion") : "";
        final String nomEvento = ctrlEv.getEventoDeUnaEdicion(nombreEdicion);
        final String nombreTipoRegistro = req.getParameter("id");

        // Validación básica
        if (nombreEdicion == null || nombreEdicion.isEmpty() ||
            nomEvento == null || nomEvento.isEmpty()) {

            req.setAttribute("mensajeError", "Faltan datos obligatorios para crear el registro.");
            req.setAttribute("javax.servlet.error.status_code", 500);
            req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
            return;
        }

        try {
            String hoy = java.time.LocalDate.now().toString();

            // Llamada al servicio web
            ctrlEv.altaRegistro(nomEvento, nombreEdicion, nombreTipoRegistro, nickname, hoy);

            // Si no lanza excepción, el registro fue exitoso
            req.getSession().setAttribute("flash_ok", "Registro realizado correctamente.");
            res.sendRedirect(req.getContextPath() + "/eventos");
            return;

        } catch (UsuarioNoExisteFault_Exception ex) {
            req.getSession().setAttribute("flash_error", "El asistente no existe o no tiene permisos.");

        } catch (IllegalStateException ex) {
            req.getSession().setAttribute("flash_error", ex.getMessage());

        // ✅ Corregido: solo capturamos SOAPFaultException
        } catch (jakarta.xml.ws.soap.SOAPFaultException ex) {
            String msg = ex.getMessage();

            if (msg != null && msg.contains("No hay cupo disponible")) {
                req.getSession().setAttribute("flash_error", "No quedan cupos disponibles para esta edición.");
            } else if (msg != null && msg.contains("Ya finalizo el evento")) {
                req.getSession().setAttribute("flash_error", "El evento ya finalizó, no puede registrarse.");
            } else {
                req.getSession().setAttribute("flash_error", "Error inesperado: " + msg);
            }

        } catch (Exception ex) {
            req.getSession().setAttribute("flash_error", "Error general: " + ex.getMessage());
        }

        // En todos los casos de error redirige al formulario de registro
        res.sendRedirect(req.getContextPath()
            + "/registroEd?op=alta&idEdicion=" + nombreEdicion + "&id=" + nombreTipoRegistro);
    }

}
    