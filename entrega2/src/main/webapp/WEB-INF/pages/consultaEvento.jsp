<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ws.eventos.DataEvento" %>
<%@ page import="java.util.List, ws.eventos.DataEdicion" %>

<%
	String path = request.getContextPath();

	String nickname = (String) session.getAttribute("usuario");
	String rol = (String) session.getAttribute("rol");
	boolean logueado = (nickname != null);
	
	DataEvento evento = (DataEvento) request.getAttribute("evento");

	List<DataEdicion> ediciones = (List<DataEdicion>) request.getAttribute("ediciones");

%>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Evento</title>
  <link rel="stylesheet" href="<%= path %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%= path %>/estilos/base.css">
  <link rel="stylesheet" href="<%= path %>/estilos/consultaEvento.css">
</head>
<body>
	<jsp:include page="header.jsp"/>
	  <!-- Main -->
  <main class="container-fluid">
    <div class="row" style="margin-bottom: 1rem;">

      <!-- Sidebar -->
      <jsp:include page="sidebar.jsp"/>

      <div class="col-12 col-md-9">
        <!-- Contenido principal -->
        <div class="contenido-main row">
        	<% if (evento != null) {%>
            <section class="col-12 col-md-7 content-evento row">
              <div class="col-12 row content-evento-resumen">
                <div class="col-12 content-evento-titulo">
                  <p><%= evento.getNombre() %></p>
                </div>
                <div class="col-12 content-evento-imagen ">
                  <img src="<%= path %>/MediaServlet?name=<%=evento.getImagen() %>" alt="logo del evento">
                </div>
              </div>
              <div class="col-12 row content-evento-informacion">
                <div>
                  <p class="informacion-label">Descripcion:</p>
                  <p><%= evento.getDescripcion() %></p>
                </div>
                <div>
                  <p class="informacion-label">Sigla:</p>
                  <p><%= evento.getSigla() %></p>
                </div>
                <div>
                  <p class="informacion-label">Categorías</p>
                  <%
					List<String> categorias = evento.getCategorias();
					if (categorias != null && !categorias.isEmpty()) {
					%>
					 <p>
					   <%= String.join(", ", categorias) %>
					 </p>
					<%
					}
				  %>
                </div>
                <div>
                  <p class="informacion-label">Fecha Alta:</p>
                  <p><%= evento.getFechaAlta() %></p>
                </div>
              </div>
             <% if ("organizador".equalsIgnoreCase(rol)) { %>
  <div class="mt-3 text-center position-relative d-flex flex-column align-items-center">
    <button class="btn btn-primary mb-2 custom-btn"
      onclick="window.location.href='<%=path%>/edicion?op=alta&id=<%= evento.getNombre() %>'">
      Crear Edición
    </button>
	  	<%
		String nombreEventoEncoded = java.net.URLEncoder.encode(evento.getNombre(), "UTF-8");
		%>
	<button class="btn btn-danger custom-btn"
	  onclick="window.location.href='<%=path%>/evento?op=baja&id=<%= nombreEventoEncoded %>'">
	  Finalizar Evento
	</button>


    <% 
    String error = request.getParameter("error");
    if ("edicionesPendientes".equals(error)) {
    %>
      <!-- Toast dinámico -->
      <div id="toastError"
           class="toast align-items-center text-bg-danger border-0 position-absolute start-50 translate-middle-x mt-2"
           role="alert" aria-live="assertive" aria-atomic="true"
           data-bs-delay="5000" style="min-width: 300px;">
        <div class="d-flex">
          <div class="toast-body">
            No se puede finalizar el evento porque tiene ediciones pendientes.
          </div>
          <button type="button" class="btn-close btn-close-white me-2 m-auto"
                  data-bs-dismiss="toast" aria-label="Cerrar"></button>
        </div>
      </div>

      <script>
        document.addEventListener("DOMContentLoaded", () => {
          const toast = new bootstrap.Toast(document.getElementById('toastError'));
          toast.show();
        });
      </script>
    <% } %>

  </div>
<% } %>

            </section>
            <aside class="col-12 col-md-5 content-ediciones">
              <div class="seccion-titulo-edicion">
                <h3>Ediciones</h3>
              </div>
              <%
              	if(ediciones != null) { 
               	for(DataEdicion edicion: ediciones){ 
               		String nombre = "";
                    String imagen = "EdicionSinFoto.png";
                    try { if (edicion.getNombre() != null) nombre = edicion.getNombre(); } catch (Exception ignore) {}
                    try { if (edicion.getImagen() != null) imagen = edicion.getImagen(); } catch (Exception ignore) {}
                    String detalleUrl = path + "/edicion"
                            + "?op=consultar&id=" + edicion.getNombre();
               %>
              <div class="ediciones"
                onclick="window.location.href='<%= detalleUrl %>'">
                <div class="content-edicion">
                  <div class="content-edicion-imagen">
                    <img src="<%= path %>/MediaServlet?name=<%=imagen%>" alt="logo de la edicion">
                  </div>
                  <p class="content-edicion-titulo">
                    <%= nombre %>
                  </p>
                </div>
              </div>
              <% 
              		}
              	} 
              %>
              
            </aside>
            <% } else{
             %>
             <h1>El evento No Existe</h1>
            <%
            }%>
          </div>
      </div>

    </div>
  </main>

  <jsp:include page="footer.jsp"/>
  





  
</body>
</html>