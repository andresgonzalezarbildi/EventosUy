<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="ws.eventos.DataEvento" %>

<%
  String ctx = request.getContextPath();
	
	Boolean errorWs = (Boolean) request.getAttribute("errorWs");
	String error = (String) request.getAttribute("error");
	String exito = (String) request.getAttribute("exito");
	
	int largo = (int) request.getAttribute("eventosCount");
  List<DataEvento> eventos = null;
  try {
      Object ev = request.getAttribute("eventos");
      if (ev != null) {
          eventos = (List<DataEvento>) ev;
      }
  } catch (Exception ignore) { }
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Eventos.uy</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/index.css">
</head>

<body>
  <!-- Header -->
  <jsp:include page="headerMobile.jsp" />

  <!-- Main Content -->
  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <!-- Contenido principal -->
        <main class="col-12">
          <div class="contenido-principal">
						<% if (error != null && errorWs) { %>
              <div class="alerta-error">
	              <svg xmlns="http://www.w3.org/2000/svg" height="20" viewBox="0 0 24 24"><path fill="#842029" d="M4 1h16a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8.82l-2-2H7V3H5v.18L3.21 1.39C3.39 1.15 3.68 1 4 1m18 21.72L20.73 24l-1-1H4a1 1 0 0 1-1-1v-4a1 1 0 0 1 1-1h9.73l-2-2H4a1 1 0 0 1-1-1v-4a1 1 0 0 1 1-1h1.73L3.68 6.95c-.3-.1-.53-.33-.63-.63L1 4.27L2.28 3zM20 9a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1h-3.18l-6-6zm0 8a1 1 0 0 1 1 1v1.18L18.82 17zM9 5h1V3H9zm0 8h.73L9 12.27zm0 8h1v-2H9zM5 11v2h2v-2zm0 8v2h2v-2z"/></svg>
	              <%= error %>
              </div>
            <% }  else if (error != null){ %>
              <div class="alerta-error">
              	<%= error %>
            	</div>
            <% 
            } else if(exito != null ) {
             %>
             <div class="alerta-exito"><%= exito %></div>
            <% 
            }
              if (eventos == null || largo < 1) {
            %>
              <!-- Sin eventos -->
              <div class="no-eventos">No hay eventos</div>
            <%
              } else {
                for (DataEvento e : eventos) {
                  String nombre = "";
                  String descripcion = "";
                  String imagen = "EventoSinFoto.png";

                  try { if (e.getNombre() != null) nombre = e.getNombre(); } catch (Exception ignore) {}
                  try { if (e.getDescripcion() != null) descripcion = e.getDescripcion(); } catch (Exception ignore) {}
                  try { if (e.getImagen() != null) imagen = e.getImagen(); } catch (Exception ignore) {}

                  String detalleUrl = ctx + "/consultaEvento"
                                    + "?id=" + e.getNombre();
            %>

              <div class="row contenido-principal-card"
                   onclick="window.location.href='<%= detalleUrl %>'">
                <div class="col-12 col-md-3 contenido-principal-card-imagen">
                  <img src="<%= request.getContextPath() %>/MediaServlet?name=<%=imagen %>" alt="imagen del evento">
                </div>
                <div class="col-12 col-md-9 contenido-principal-card-informacion col-12">
                  <p class="contenido-principal-card-informacion-titulo"><%= nombre %></p>
                  <p class="contenido-principal-card-informacion-descripcion"><%= descripcion %></p>
                </div>
              </div>

            <%
                } // fin for
              } // fin else
            %>

          </div>
        </main>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <jsp:include page="footerMobile.jsp" />
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>
