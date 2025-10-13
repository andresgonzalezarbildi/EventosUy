<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String nickname = (String) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");
    boolean logueado = (nickname != null);
%>

<aside class="col-12 col-md-3">

  <!-- Categorías -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo"><h3>Categorías</h3></div>
      <div class="content-bar-seccion-list">
        <div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>'"><span>Tecnología</span></div>
        <div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>'"><span>Innovación</span></div>
        <div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>'"><span>Deporte</span></div>
        <div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>'"><span>Salud</span></div>
      </div>
    </div>
  </div>

  <!-- Acciones -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo"><h3>Acciones</h3></div>
      <div class="content-bar-seccion-list">
        <div class="content-bar-seccion-list-options" onclick="location.href='<%= request.getContextPath() %>/UsuarioServlet?op=listar'">
          <span>Usuario</span>
        </div>
        <div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>/instituciones'">
          <span>Institución</span>
        </div>
      </div>
    </div>
  </div>

  <!-- Administración -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo"><h3>Administración</h3></div>
      <div class="content-bar-seccion-list">
        <form action="<%= request.getContextPath() %>/cargarDatos" method="post" class="content-bar-seccion-list-options">
          <button style="background:none; border:none; color:inherit; padding:0; cursor:pointer;" type="submit">
            Cargar Datos
          </button>
        </form>
      </div>
    </div>
  </div>


<% if ("organizador".equalsIgnoreCase(rol)) { %>
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo"><h3>Mi Perfil</h3></div>
      <div class="content-bar-seccion-list">
        <div class="content-bar-seccion-list-options"
             onclick="window.location.href='<%= request.getContextPath() %>/evento/alta'">
          <span>Alta Evento</span>
        </div>
        <div class="content-bar-seccion-list-options"
             onclick="window.location.href='<%= request.getContextPath() %>/evento/mis-eventos'">
          <span>Mis Eventos</span>
        </div>
        <div class="content-bar-seccion-list-options"
             onclick="window.location.href='<%= request.getContextPath() %>/alta-institucion'">
          <span>Alta Institución</span>
        </div>
      </div>
    </div>
  </div>
<% } %>


</aside>
