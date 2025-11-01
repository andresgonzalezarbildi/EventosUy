<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<header class="navbar">
  <div class="container-fluid">
    <div class="row navbar-row text-center text-md-start">
    
      <div class="col-12 col-md-3 mobile-col-center">
        <h1>
          <a class="title" href="<%= request.getContextPath() %>/eventos">
            Eventos.uy
          </a>
        </h1>
      </div>

   
      <div class="col-12 col-md-6">
        <div class="navbar_search mx-auto">
            <input type="text" name="q" placeholder="Eventos, ediciones..." class="navbar_search-input">
            <button type="submit" class="navbar_search-btn">Buscar</button>
        </div>
      </div>

   
      <div class="col-12 col-md-3 mobile-col-center">
        <div class="navbar_sesion">
        
      <%
  
    HttpSession s = request.getSession(false);

    String nickname = null;
    String imagen = null;
    boolean logueado = false;

    if (s != null) {
        nickname = (String) s.getAttribute("usuario");
        imagen = (String) s.getAttribute("imagen");
        logueado = (nickname != null);
    }

 
    if (imagen == null || imagen.trim().isEmpty()) {
        imagen = "PerfilSinFoto.jpg";
    }

 
    imagen = java.net.URLEncoder.encode(imagen, "UTF-8");
%>

		
		
<% if (logueado) { %>
 
  <a href="<%= request.getContextPath() %>/LogoutServlet" class="navbar_sesion_link">
    Cerrar Sesión
  </a>
  <div class="navbar_sesion_perfil">
    <a href="<%= request.getContextPath() %>/UsuarioServlet?op=consultar&nick=<%= nickname %>">
      <p class="navbar_username"><%= nickname %></p>
      <img class="perfil_image"
           src="<%= request.getContextPath() %>/img/<%= imagen %>"
           alt="foto perfil"
           onerror="this.onerror=null; this.src='<%= request.getContextPath() %>/img/PerfilSinFoto.jpg';" />
    </a>
  </div>
<% } else { %>

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
