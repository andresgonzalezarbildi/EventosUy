	<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ page import="java.util.*" %>
	<%@ page import="ws.usuario.DataUsuario" %>
	<%
	  String ctx = request.getContextPath();
	
	  String usuarioCreado = (String) request.getAttribute("usuarioCreado");
	  List<DataUsuario> usuarios = null;
	  try {
	      Object usrs = request.getAttribute("usuarios");
	      if (usrs != null) {
	          usuarios = (List<DataUsuario>) usrs;
	      }
	  } catch (Exception ignore) { }
	%>
	<!DOCTYPE html>
	<html lang="es">
	
	<head>
	  <meta charset="UTF-8">
	  <title>Usuarios</title>
	  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
	  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
	  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
	  <link rel="stylesheet" href="<%= ctx %>/estilos/usuarios.css">
	</head>
	
	<body>
	  <!-- Header -->
	  <jsp:include page="header.jsp"/>
	
	  <section class="container-fluid content">
	    <div class="row">
	
	      <!-- Sidebar -->
	      <jsp:include page="sidebar.jsp"/>
	
	      <!-- Contenedor principal -->
	      <main class="col-12 col-md-9">
          	<div class="contenido-principal">
		        <% if (usuarioCreado != null) { %>
		          <div class="alerta-exito" role="alert" style="margin-bottom:1rem;">
		            <%= usuarioCreado %>
		          </div>
		        <% } %>
		
		        <%
		          if (usuarios == null || usuarios.isEmpty()) {
		        %>
		          <h1>No hay Usuarios registrados</h1>
		        <%
		          } else {
		        %>
		          <h1 style="margin-bottom: 1rem; color: var(--color-primary);">Usuarios</h1>
		          <div class="usuarios">
		            <% // <ul style="list-style: none; width: 500px;">%>
		              <%
		                for (DataUsuario e : usuarios) {
		                  String nickname = e.getNickname() != null ? e.getNickname() : "";
		                  String imagen = e.getImagen() != null ? e.getImagen() : "PerfilSinFoto.jpg";
		
		                  // Ahora apuntamos al servlet con el parámetro "nick"
		                  String detalleUrl = ctx + "/UsuarioServlet?op=consultar&nick=" + nickname;
		              %>
		               <div class="row contenido-principal-card" onclick="window.location.href='<%= detalleUrl %>'">
		               	<div class="col-12 col-md-3 contenido-principal-card-imagen">
		                  <img src="<%= ctx %>/MediaServlet?name=<%=imagen %>" alt="foto usuario">
		                </div>
		                <div class="col-12 col-md-9 contenido-principal-card-informacion col-12">
		                  <p class="contenido-principal-card-informacion-titulo"><%= nickname %></p>
		                </div>
		               </div>
		                
		              <%
		                } // fin for
		              %>
		          </div>
		        <%
		          } // fin else
		        %>
	        </div>
	      </main>
	    </div>
	  </section>
	  <jsp:include page="footer.jsp"/>
	</body>
	
	</html>
