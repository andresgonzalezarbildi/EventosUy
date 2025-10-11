<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="logica.datatypes.DataEvento" %>

<%
  String ctx = request.getContextPath();

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
  <title>Eventos.uy - Plataforma de Eventos</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/index.css">
</head>

<body>
  <!-- Header -->
  <jsp:include page="header.jsp" />

  <!-- Main Content -->
  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp" />

        <!-- Contenido principal -->
        <main class="col-12 col-md-9">
          <div class="contenido-principal">

            <%
              if (eventos == null || eventos.isEmpty()) {
            %>
              <!-- Sin eventos: placeholder -->
              <h1>No hay Eventos :(</h1>
            <%
              } else {
                for (DataEvento e : eventos) {
                  String nombre = "";
                  String descripcion = "";
                  String imagen = "EventoSinFoto.png";

                  try { if (e.getNombre() != null) nombre = e.getNombre(); } catch (Exception ignore) {}
                  try { if (e.getDescripcion() != null) descripcion = e.getDescripcion(); } catch (Exception ignore) {}
                  try { if (e.getImagen() != null) imagen = e.getImagen(); } catch (Exception ignore) {}

                  String detalleUrl = ctx + "/evento"
                                    + "?id=" + e.getNombre();
            %>

              <div class="row contenido-principal-card"
                   onclick="window.location.href='<%= detalleUrl %>'">
                <div class="col-12 col-md-3 contenido-principal-card-imagen">
                  <img src="<%= ctx %>/img/<%=imagen %>" alt="imagen del evento">
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
  <jsp:include page="footer.jsp" />
</body>
</html>
