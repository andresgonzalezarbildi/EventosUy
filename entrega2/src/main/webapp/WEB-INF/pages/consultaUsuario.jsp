<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%@ page import="logica.datatypes.DataUsuario" %>
<%@ page import="logica.datatypes.DataEdicion" %>
<%@ page import="java.util.List" %>
<%@ page import="logica.datatypes.DataRegistro" %>



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
        <main class="col-12 col-md-9 d-flex justify-content-center align-items-start">

          <%
            DataUsuario usuario = (DataUsuario) request.getAttribute("usuario");
            if (usuario == null) {
          %>
              <p>Usuario no encontrado.</p>
          <%
            } else {
                // Determinamos el tipo según los datos del usuario
				String tipo = usuario.getTipo();

                // Chequeo de rol logueado para mostrar info extra
                String rolLogueado = (String) session.getAttribute("rol");
                String nickLogueado = (String) session.getAttribute("usuario");
          %>

          <div class="detalle-usuario">
            <h2><%= usuario.getNickname() %></h2>
            <img src="<%= request.getContextPath() + "/img/" + (usuario.getImagen() != null ? usuario.getImagen() : "PerfilSinFoto.jpg") %>" 
                 alt="Foto Usuario" style="width:100px; height:100px;">

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
                 <button onclick="window.location.href='<%= request.getContextPath() %>/UsuarioServlet?op=modificar'">
				    Modificar Perfil
				 </button>

                </div>
            <% } %>
            
            <%-- Ediciones visibles del organizador --%>
			<% if ("Organizador".equalsIgnoreCase(usuario.getTipo())) { %>
		    <h3>Ediciones del Organizador</h3>
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
		            <% if (esMiPerfil) { %>
		                - (<%= e.getEstado() %>)
		            <% } %>
		        </li>
		    <%
		            }
		        } else {
		    %>
		        <li>No hay ediciones para mostrar.</li>
		    <%
		        }
		    %>
		    </ul>
		<% } %>
		
		<%-- Registros visibles del asistente (solo su propio perfil) --%>
		<% if ("Asistente".equalsIgnoreCase(usuario.getTipo())
		       && nickLogueado != null
		       && nickLogueado.equalsIgnoreCase(usuario.getNickname())) { %>
		
		    <h3>Mis registros</h3>
		    <ul>
		    <%
		        DataRegistro[] registros = (DataRegistro[]) request.getAttribute("registros");
		        if (registros != null && registros.length > 0) {
		            for (DataRegistro r : registros) {
		    %>
		        <li>
		            <a href="<%= request.getContextPath() %>/registroEd?op=consultar&evento=<%= r.getEvento() %>&edicion=<%= r.getEdicion() %>">
		                Registro en <%= r.getEdicion() %> (<%= r.getEvento() %>) — Tipo: <%= r.getTipoRegistro() %>, Costo: $<%= r.getCosto() %>
		            </a>
		        </li>
		    <%
		            }
		        } else {
		    %>
		        <li>No hay registros.</li>
		    <%
		        }
		    %>
		    </ul>
		<% } %>

			            

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
