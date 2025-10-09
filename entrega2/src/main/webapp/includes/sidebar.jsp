<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Sidebar -->
<aside class="col-12 col-md-3">
  <!-- Categorías -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo">
        <h3>Categorías</h3>
      </div>
      <div class="content-bar-seccion-list">
        <div class="content-bar-seccion-list-options" 
             onclick="window.location.href='<%= request.getContextPath() %>'">
          <span>Tecnología</span>
        </div>
        <div class="content-bar-seccion-list-options" 
             onclick="window.location.href='<%= request.getContextPath() %>'">
          <span>Innovación</span>
        </div>
        <div class="content-bar-seccion-list-options" 
             onclick="window.location.href='<%= request.getContextPath() %>'">
          <span>Deporte</span>
        </div>
        <div class="content-bar-seccion-list-options" 
             onclick="window.location.href='<%= request.getContextPath() %>'">
          <span>Salud</span>
        </div>
      </div>
    </div>
  </div>

  <!-- Acciones -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo">
        <h3>Acciones</h3>
      </div>
      <div class="content-bar-seccion-list">
        <div class="content-bar-seccion-list-options" 
             onclick="window.location.href='<%= request.getContextPath() %>/usuarios'">
          <span>Usuario</span>
        </div>
        <div class="content-bar-seccion-list-options" 
             onclick="window.location.href='<%= request.getContextPath() %>/instituciones'">
          <span>Institución</span>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Botón para cargar datos -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo">
        <h3>Administración</h3>
      </div>
      <div class="content-bar-seccion-list">
        <form action="<%= request.getContextPath() %>/cargarDatos" method="post" class="content-bar-seccion-list-options">
          <button style="background: none; border: none; color: inherit; padding: 0; cursor: pointer;" type="submit">
            Cargar Datos
          </button>
        </form>
      </div>
    </div>
  </div>
  
  

  <!-- Menú específico según tipo de usuario -->
  <%
    Object usuarioObj = session.getAttribute("usuario");
    String tipoUsuario = null;
    
    if (usuarioObj != null) {
      try {
        // Acceso por reflexión al método getTipo() del objeto usuario
        Class<?> usuarioClass = usuarioObj.getClass();
        java.lang.reflect.Method getTipo = usuarioClass.getMethod("getTipo");
        Object tipoObj = getTipo.invoke(usuarioObj);
        tipoUsuario = (tipoObj != null) ? String.valueOf(tipoObj) : null;
      } catch (Exception e) {
        // Si falla la reflexión, usar valor por defecto
        tipoUsuario = null;
      }
    }
    
    if (usuarioObj != null && "ASISTENTE".equals(tipoUsuario)) {
  %>
        <!-- Menú para Asistente -->
        <div class="content-bar">
          <div class="content-bar-seccion">
            <div class="seccion-titulo">
              <h3>Mi Perfil</h3>
            </div>
            <div class="content-bar-seccion-list">
              <div class="content-bar-seccion-list-options" 
                   onclick="window.location.href='<%= request.getContextPath() %>/mis-registros'">
                <span>Mis Registros</span>
              </div>
              <div class="content-bar-seccion-list-options" 
                   onclick="window.location.href='<%= request.getContextPath() %>/eventos-asistente'">
                <span>Eventos</span>
              </div>
            </div>
          </div>
        </div>
  <%
    } else if (usuarioObj != null && "ORGANIZADOR".equals(tipoUsuario)) {
  %>
        <!-- Menú para Organizador -->
        <div class="content-bar">
          <div class="content-bar-seccion">
            <div class="seccion-titulo">
              <h3>Mi Perfil</h3>
            </div>
            <div class="content-bar-seccion-list">
              <div class="content-bar-seccion-list-options" 
                   onclick="window.location.href='<%= request.getContextPath() %>/alta-evento'">
                <span>Alta Evento</span>
              </div>
              <div class="content-bar-seccion-list-options" 
                   onclick="window.location.href='<%= request.getContextPath() %>/eventos-organizador'">
                <span>Mis Eventos</span>
              </div>
              <div class="content-bar-seccion-list-options" 
                   onclick="window.location.href='<%= request.getContextPath() %>/alta-institucion'">
                <span>Alta Institución</span>
              </div>
            </div>
          </div>
        </div>
  <%
    }
  %>
</aside>
