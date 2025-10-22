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
import logica.clases.EdicionEvento;
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
                case "alta": {
                    String idTipoReg = req.getParameter("id");
                    if (idTipoReg == null || idTipoReg.isEmpty()) {
                        res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta el parámetro 'id' del tipo reg.");
                        return;
                    }

                    String nomEvento = ctrlEv.getEventoDeUnaEdicion(nombreEdicion);
          					DataEdicion edicion = ctrlEv.getInfoEdicion(nombreEdicion);
          					LocalDate fechaIni = edicion.getFechaIni();
          					LocalDate fechaFin = edicion.getFechaFin();
          					if(fechaFin.isBefore(LocalDate.now())) {
                      req.setAttribute("mensajeError", "La edicion " + edicion.getNombre() + " ya termino, no puede registrarse");
                      req.setAttribute("javax.servlet.error.status_code", 409);
                      req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
                      break;
          					}
          					String ciudad = edicion.getCiudad();
          					String pais = edicion.getPais();
          					DataTipoRegistro tiporeg = ctrlEv.getTipoRegistro(nomEvento, nombreEdicion, idTipoReg);
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
            	    	DataEdicion edicion = ctrlEv.getInfoEdicion(idEdicion);
            	    	String imagen = edicion.getImagen();
            	    	String asis = req.getParameter("idAsistente");
            	    	DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, asis);
            	    	LocalDate fechaRegistro = registro.getFecha();
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
          	        DataEdicion edicion = ctrlEv.getInfoEdicion(idEdicion);
          	        String nomEvento = req.getParameter("evento");
            	    	DataRegistro registro = ctrlEv.listarUnRegistroDeUsuario(idEdicion, nickname);
            	    	LocalDate fechaRegistro = registro.getFecha();
            	    	String nomTipoRegistro = registro.getTipoRegistro();
            	    	String organizador = edicion.getOrganizador();
            	    	LocalDate fechaIni = edicion.getFechaIni();
            	    	LocalDate fechaFin = edicion.getFechaFin();
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
        String nombreEdicion = (req.getParameter("idEdicion") != null) ? req.getParameter("idEdicion") : "";
        final String nomEvento = ctrlEv.getEventoDeUnaEdicion(nombreEdicion);
        final String nombreTipoRegistro = req.getParameter("id");
       
        if (nombreEdicion == null || nombreEdicion.isEmpty() ||
        	    nomEvento == null || nomEvento.isEmpty()) {
              req.setAttribute("mensajeError", "Faltan datos obligatorios para crear el registro." );
              req.setAttribute("javax.servlet.error.status_code", 500);
              req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, res);
        	    return;
        	}
        
  
        try {
            ctrlEv.altaRegistro(nomEvento, nombreEdicion, nombreTipoRegistro, nickname, LocalDate.now());
            req.getSession().setAttribute("flash_ok", "Registro realizado correctamente.");
            res.sendRedirect(req.getContextPath() + "/eventos");
            return;
        } catch (IllegalStateException ex) { 
            req.getSession().setAttribute("flash_error", ex.getMessage());
            res.sendRedirect(req.getContextPath()
                + "/registroEd?op=alta&idEdicion=" + nombreEdicion + "&id=" + nombreTipoRegistro);
            return;
        } catch (UsuarioNoExisteException ex) {
            req.getSession().setAttribute("flash_error", "El asistente no existe o no tiene permisos.");
            res.sendRedirect(req.getContextPath()
                + "/registroEd?op=alta&idEdicion=" + nombreEdicion + "&id=" + nombreTipoRegistro);
            return;
        }
    }

    	
    	
    }
    
    