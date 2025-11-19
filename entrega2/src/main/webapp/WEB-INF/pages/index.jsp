<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="ws.eventos.DataEvento" %>

<%
  String ctx = request.getContextPath();
	String exito = (String) request.getAttribute("exito");
	Boolean errorWs = (Boolean) request.getAttribute("errorWs");
  String error = (String) request.getAttribute("error");
  int largo = (int) request.getAttribute("eventosCount");

  List<DataEvento> eventos = null;
  try {
      Object attr = request.getAttribute("eventos");
      if (attr != null) {
          eventos = (List<DataEvento>) attr; 
      }
  } catch (Exception ignore) {}
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Eventos.uy</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/index.css">
</head>
<body>
  <jsp:include page="header.jsp" />

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="sidebar.jsp" />

        <main class="col-12 col-md-9">
          <div class="contenido-principal">
            <% if (error != null && errorWs) { %>
              <div class="alerta-error">
	              <svg xmlns="http://www.w3.org/2000/svg" height="20" viewBox="0 0 24 24"><path fill="#842029" d="M4 1h16a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1H8.82l-2-2H7V3H5v.18L3.21 1.39C3.39 1.15 3.68 1 4 1m18 21.72L20.73 24l-1-1H4a1 1 0 0 1-1-1v-4a1 1 0 0 1 1-1h9.73l-2-2H4a1 1 0 0 1-1-1v-4a1 1 0 0 1 1-1h1.73L3.68 6.95c-.3-.1-.53-.33-.63-.63L1 4.27L2.28 3zM20 9a1 1 0 0 1 1 1v4a1 1 0 0 1-1 1h-3.18l-6-6zm0 8a1 1 0 0 1 1 1v1.18L18.82 17zM9 5h1V3H9zm0 8h.73L9 12.27zm0 8h1v-2H9zM5 11v2h2v-2zm0 8v2h2v-2z"/></svg>
	              <%= error %>
              </div>
            <% } else if (exito != null){ %>
              <div class="alerta-exito"><%= exito %></div>
            <% } 
            		if (largo <= 0 && !errorWs) { 
            %>
              <div class="no-eventos">No hay eventos</div>
            <% } else { 
                 for (DataEvento e : eventos) {
                   String nombre = "";
                   String descripcion = "";
                   String imagen = "EventoSinFoto.png";
                   try { if (e.getNombre() != null) nombre = e.getNombre(); } catch (Exception ignore) {}
                   try { if (e.getDescripcion() != null) descripcion = e.getDescripcion(); } catch (Exception ignore) {}
                   try { if (e.getImagen() != null) imagen = e.getImagen(); } catch (Exception ignore) {}

                   String id = URLEncoder.encode(nombre, "UTF-8");
                   String detalleUrl = ctx + "/evento?op=consultar&id=" + id;
            %>
              <div class="row contenido-principal-card"
                   onclick="window.location.href='<%= detalleUrl %>'">
                <div class="col-12 col-md-3 contenido-principal-card-imagen">
                
                  <img src="<%= request.getContextPath() %>/MediaServlet?name=<%= imagen %>" alt="imagen del evento">
                </div>
                <div class="col-12 col-md-9 contenido-principal-card-informacion">
                  <p class="contenido-principal-card-informacion-titulo"><%= nombre %></p>
                  <p class="contenido-principal-card-informacion-descripcion"><%= descripcion %></p>
                </div>
              </div>

            <%   } // fin for
               } // fin else %>
          </div>
        </main>
      </div>
    </div>
  </section>
  
  <jsp:include page="footer.jsp" />
</body>
</html>
