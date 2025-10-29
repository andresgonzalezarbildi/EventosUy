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
            <% if (error != null) { %>
              <div class="alerta-error"><%= error %></div>
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
                   String detalleUrl = ctx + "/evento?id=" + id;
            %>

              <div class="row contenido-principal-card"
                   onclick="window.location.href='<%= detalleUrl %>'">
                <div class="col-12 col-md-3 contenido-principal-card-imagen">
                  <img src="<%= ctx %>/img/<%= imagen %>" alt="imagen del evento">
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
