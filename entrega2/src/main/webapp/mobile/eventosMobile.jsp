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

            <%
              if (eventos == null || eventos.isEmpty()) {
            %>
              <!-- Sin eventos -->
              <h1>No hay Eventos</h1>
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
  <jsp:include page="footerMobile.jsp" />
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>
