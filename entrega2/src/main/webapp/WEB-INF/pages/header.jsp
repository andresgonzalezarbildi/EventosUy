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
            Object usuarioObj = session.getAttribute("usuario");
            boolean logueado = (usuarioObj != null);
            String nickname = "";
            String imagen = "PerfilSinFoto.jpg";
            
            if (usuarioObj != null) {
              try {
                // Acceso por reflexión a los getters del objeto usuario
                Class<?> usuarioClass = usuarioObj.getClass();
                java.lang.reflect.Method getNickname = usuarioClass.getMethod("getNickname");
                java.lang.reflect.Method getImagen = usuarioClass.getMethod("getImagen");
                
                Object nicknameObj = getNickname.invoke(usuarioObj);
                Object imagenObj = getImagen.invoke(usuarioObj);
                
                nickname = (nicknameObj != null) ? String.valueOf(nicknameObj) : "";
                imagen = (imagenObj != null && !String.valueOf(imagenObj).isEmpty()) ? 
                        String.valueOf(imagenObj) : "PerfilSinFoto.jpg";
              } catch (Exception e) {
                // Si falla la reflexión, usar valores por defecto
                nickname = "Usuario";
                imagen = "PerfilSinFoto.jpg";
              }
            }
          %>
          <%
            if (logueado) {
          %>
            <a href="<%= request.getContextPath() %>/logout" class="navbar_sesion_link">
              Cerrar Sesión
            </a>
            <div class="navbar_sesion_perfil">
              <a href="<%= request.getContextPath() %>/mi-perfil">
                <p class="navbar_username"><%= nickname %></p>
                <img class="perfil_image" 
                     src="<%= request.getContextPath() %>/img/<%= imagen %>" 
                     alt="foto perfil" />
              </a>
            </div>
          <%
            } else {
          %>
            <a href="<%= request.getContextPath() %>/iniciarSesion" class="navbar_sesion_link">
              Iniciar Sesión
            </a>
            <p>/</p>
            <a href="<%= request.getContextPath() %>/altaUsuario" class="navbar_sesion_link">
              Registrarse
            </a>
          <%
            }
          %>
        </div>
      </div>
    </div>
  </div>
</header>
