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
  <jsp:include page="includes/header.jsp" />

  <!-- Main Content -->
  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <jsp:include page="includes/sidebar.jsp" />

        <!-- Contenido principal -->
        <main class="col-12 col-md-9">
          <div class="contenido-principal">

            <!-- Mensajes de feedback -->
            <%
              String mensaje = (String) request.getAttribute("mensaje");
              String tipoMensaje = (String) request.getAttribute("tipoMensaje");
              if (mensaje != null && !mensaje.isEmpty()) {
                boolean ok = "success".equalsIgnoreCase(tipoMensaje);
            %>
              <div class="alert"
                   style="padding:1rem;margin-bottom:1rem;border-radius:4px;
                          background-color:<%= ok ? "#d4edda" : "#f8d7da" %>;
                          color:<%= ok ? "#155724" : "#721c24" %>;
                          border:1px solid <%= ok ? "#c3e6cb" : "#f5c6cb" %>;">
                <%= mensaje %>
              </div>
            <%
              }
            %>

            <%
              if (eventos == null || eventos.isEmpty()) {
            %>
              <!-- Sin eventos: placeholder -->
              <div class="row contenido-principal-card">
                <div class="col-12 col-md-3 contenido-principal-card-imagen">
                  <img src="<%= ctx %>/img/EventoSinFoto.png" alt="imagen por defecto">
                </div>
                <div class="col-12 col-md-9 contenido-principal-card-informacion col-12">
                  <p class="contenido-principal-card-informacion-titulo">
                    No hay eventos cargados
                  </p>
                  <p class="contenido-principal-card-informacion-descripcion">
                    Presioná “Cargar Datos” para insertar datos de prueba.
                  </p>
                </div>
              </div>
            <%
              } else {
                for (DataEvento e : eventos) {
                  String nombre = "";
                  String descripcion = "";
                  String imagen = "EventoSinFoto.png";

                  try { if (e.getNombre() != null) nombre = e.getNombre(); } catch (Exception ignore) {}
                  try { if (e.getDescripcion() != null) descripcion = e.getDescripcion(); } catch (Exception ignore) {}
                  try { if (e.getImagen() != null) imagen = e.getImagen(); } catch (Exception ignore) {}

                  String detalleUrl = ctx + "/pages/consultaEventoVisitante.jsp"
                                    + ( true ? ("?id=" + e.getNombre()) : "");
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
  <jsp:include page="includes/footer.jsp" />
</body>
</html>
