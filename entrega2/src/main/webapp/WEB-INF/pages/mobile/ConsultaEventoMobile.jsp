<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ws.eventos.DataEvento" %>
<%@ page import="java.util.List" %>
<%@ page import="ws.eventos.DataEdicion" %>

<%
	String path = request.getContextPath();

	String error = (String) request.getAttribute("error");

	String nickname = (String) session.getAttribute("usuario");
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
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<%= path %>/estilos/base.css">
  <link rel="stylesheet" href="<%= path %>/estilos/consultaEvento.css">
</head>
<body>
	<jsp:include page="headerMobile.jsp" />
	  <!-- Main -->
  <main class="container-fluid">
    <div class="row" style="margin-bottom: 1rem;">
      <div class="col-12">
        <!-- Contenido principal -->
        <div class="contenido-main row">
        	<% if(error != null) { %>
        		<div class="alerta-error">
              	<%= error %>
            	</div>
            	
        	<% } else if (evento != null) {%>
            <section class="col-12 col-md-7 content-evento row">
              <div class="col-12 row content-evento-resumen">
                <div class="col-12 content-evento-titulo">
                  <p><%= evento.getNombre() %></p>
                </div>
                <div class="col-12 content-evento-imagen ">
                  <img src="<%= request.getContextPath() %>/MediaServlet?name=<%=evento.getImagen() %>" alt="logo del evento">
                </div>
              </div>
              <div class="col-12 content-evento-informacion">
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
                    String detalleUrl = path + "/mobile/consultaEdicion"
                            + "?id=" + edicion.getNombre();
               %>
              <div class="ediciones"
                onclick="window.location.href='<%= detalleUrl %>'">
                <div class="content-edicion">
                  <div class="content-edicion-imagen">
                    <img src="<%= request.getContextPath() %>/MediaServlet?name=<%=imagen%>" alt="logo de la edicion">
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
            <% } %>
          </div>
      </div>

    </div>
  </main>

  <jsp:include page="footerMobile.jsp"/>
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>