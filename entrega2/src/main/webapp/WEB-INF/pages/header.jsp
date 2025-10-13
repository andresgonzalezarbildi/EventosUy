<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Header -->
<header class="navbar">
  <div class="container-fluid">
    <div class="row navbar-row text-center text-md-start">
      <!-- Logo -->
      <div class="col-12 col-md-3 mobile-col-center">
        <h1>
          <a class="title" href="<%= request.getContextPath() %>/eventos">
            Eventos.uy
          </a>
        </h1>
      </div>

      <!-- Search -->
      <div class="col-12 col-md-6">
        <div class="navbar_search mx-auto">
            <input type="text" name="q" placeholder="Eventos, ediciones..." class="navbar_search-input">
            <button type="submit" class="navbar_search-btn">Buscar</button>
        </div>
      </div>

      <!-- Sesión -->
      <div class="col-12 col-md-3 mobile-col-center">
        <div class="navbar_sesion">
        
       <%
	  // Obtener datos de sesión
	  String nickname = (String) session.getAttribute("usuario");
	  String imagen = (String) session.getAttribute("imagen");
	
	  boolean logueado = (nickname != null);
	
	  // Si no hay imagen guardada, usar una por defecto
	  if (imagen == null || imagen.isEmpty()) {
	      imagen = "PerfilSinFoto.jpg";
	  }
		%>
		
		
<% if (logueado) { %>
  <!-- Usuario logueado -->
  <a href="<%= request.getContextPath() %>/LogoutServlet" class="navbar_sesion_link">
    Cerrar Sesión
  </a>
  <div class="navbar_sesion_perfil">
  <a href="<%= request.getContextPath() %>/UsuarioServlet?op=consultar&nick=<%= nickname %>">
    <p class="navbar_username"><%= nickname %></p>
    <img class="perfil_image" 
         src="<%= request.getContextPath() %>/img/<%= imagen %>?t=<%= System.currentTimeMillis() %>"
         alt="foto perfil" />
  </a>
  </div>
<% } else { %>
  <!-- Usuario no logueado -->
  <a href="<%= request.getContextPath() %>/LoginServlet" class="navbar_sesion_link">
    Iniciar Sesión
  </a>
  <p>/</p>
  <a href="<%= request.getContextPath() %>/UsuarioServlet?op=alta" class="navbar_sesion_link">
    Registrarse
  </a>
<% } %>

        </div>
      </div>
    </div>
  </div>
</header>
