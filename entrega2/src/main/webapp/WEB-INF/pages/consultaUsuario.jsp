<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ page import="ws.usuario.DataUsuario" %>
<%@ page import="ws.eventos.DataEdicion" %>
<%@ page import="java.util.List" %>
<%@ page import="ws.eventos.DataRegistro" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Consulta Usuario</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/consultaUsuario.css">
</head>
<body>
  <jsp:include page="header.jsp" />

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="sidebar.jsp" />

        <main class="col-12 col-md-9 d-flex justify-content-center">

          <%
            DataUsuario usuario = (DataUsuario) request.getAttribute("usuario");
            if (usuario == null) {
          %>
              <p>Usuario no encontrado.</p>
          <%
            } else {
                String tipo = usuario.getTipo();
                String rolLogueado = (String) session.getAttribute("rol");
                String nickLogueado = (String) session.getAttribute("usuario");
          %>

          <div style="max-width:800px; background:#fff; padding:2rem; border-radius:8px; box-shadow:0 2px 6px rgba(0,0,0,0.1); width:100%;">

            <!-- Foto y nickname arriba, centrados -->
            <div style="text-align:center; margin-bottom:1.5rem;">
                <img src="<%= request.getContextPath() + "/img/" + (usuario.getImagen() != null ? usuario.getImagen() : "PerfilSinFoto.jpg") %>" 
                     alt="Foto Usuario" style="width:150px; height:150px; border-radius:8px; object-fit:cover; margin-bottom:0.5rem;">
                <div style="font-weight:bold; font-size:1.2rem;"><%= usuario.getNickname() %></div>
            </div>

            <!-- Información usuario -->
            <div>
              <p><strong>Tipo:</strong> <%= tipo %></p>
              <p><strong>Nombre:</strong> <%= usuario.getNombre() != null ? usuario.getNombre() : "-" %></p>
              <p><strong>Correo:</strong> <%= usuario.getCorreo() != null ? usuario.getCorreo() : "-" %></p>

              <% if ("Organizador".equalsIgnoreCase(tipo)) { %>
                  <p><strong>Descripción:</strong> <%= usuario.getDescripcion() != null ? usuario.getDescripcion() : "-" %></p>
                  <p><strong>Sitio Web:</strong> <%= usuario.getLink() != null ? usuario.getLink() : "-" %></p>
              <% } %>

              <% if ("Asistente".equalsIgnoreCase(tipo)) { %>
                  <p><strong>Apellido:</strong> <%= usuario.getApellido() != null ? usuario.getApellido() : "-" %></p>
                  <p><strong>Fecha Nac.:</strong> <%= usuario.getFechaNacimiento() != null ? usuario.getFechaNacimiento() : "-" %></p>
              <% } %>

              <% if (nickLogueado != null && nickLogueado.equalsIgnoreCase(usuario.getNickname())) { %>
                  <div style="margin-top:1rem;">
                      <button onclick="window.location.href='<%= request.getContextPath() %>/UsuarioServlet?op=modificar&nick=<%= usuario.getNickname() %>'" class="btn btn-primary">
                          Modificar Perfil
                      </button>
                  </div>
              <% } %>

              <% if ("Organizador".equalsIgnoreCase(tipo)) { %>
                  <h3 class="mt-3">Ediciones del Organizador</h3>
                  <ul>
                  <%
                      List<DataEdicion> ediciones = (List<DataEdicion>) request.getAttribute("ediciones");
                      if (ediciones != null && !ediciones.isEmpty()) {
                          for (DataEdicion e : ediciones) {
                              boolean esMiPerfil = nickLogueado != null && nickLogueado.equalsIgnoreCase(usuario.getNickname());
                  %>
                      <li>
                          <a href="<%= request.getContextPath() %>/edicion?op=consultar&id=<%= e.getNombre() %>">
                              <%= e.getNombre() %>
                          </a>
                          <% if (esMiPerfil) { %> - (<%= e.getEstado() %>) <% } %>
                      </li>
                  <%
                          }
                      } else { %>
                          <li>No hay ediciones para mostrar.</li>
                  <% } %>
                  </ul>
              <% } %>

              <% if ("Asistente".equalsIgnoreCase(tipo)
                     && nickLogueado != null
                     && nickLogueado.equalsIgnoreCase(usuario.getNickname())) { %>
                  <h3 class="mt-3">Mis registros</h3>
                  <ul>
                  <%
					java.util.List<ws.eventos.DataRegistro> registros =
					    (java.util.List<ws.eventos.DataRegistro>) request.getAttribute("registros");
					
					if (registros != null && !registros.isEmpty()) {
					    for (ws.eventos.DataRegistro r : registros) {
					%>
					                      <li>
                          <a href="<%= request.getContextPath() %>/registroEd?op=consultar&evento=<%= r.getEvento() %>&edicion=<%= r.getEdicion() %>">
                        Registro en <%= r.getEdicion() %> (<%= r.getEvento() %>) — Tipo: <%= r.getTipoRegistro() %>, Costo: $<%= r.getCosto() %>
                    </a>
                      </li>
                  <%
                          }
                      } else { %>
                          <li>No hay registros.</li>
                  <% } %>
                  </ul>
              <% } %>

            </div>

          </div>

          <%
            } // fin else usuario != null
          %>

        </main>
      </div>
    </div>
  </section>

  <jsp:include page="footer.jsp" />
</body>
</html>
