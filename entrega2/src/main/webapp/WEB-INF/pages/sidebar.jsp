<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<%
    String nickname = (String) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");
    boolean logueado = (nickname != null);
    List<String> categorias = (List<String>) request.getAttribute("categorias");
%>

<aside class="col-12 col-md-3">


  <!-- Acciones -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo"><h3>Acciones</h3></div>
      <div class="content-bar-seccion-list">
        <div class="content-bar-seccion-list-options" onclick="location.href='<%= request.getContextPath() %>/UsuarioServlet?op=listar'">
          <span>Usuarios</span>
        </div>
        <div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>/'">
          <span>Instituciónes</span>
        </div>
      </div>
    </div>
  </div>
  
    <!-- Categorías -->
  <div class="content-bar">
    <div class="content-bar-seccion">
      <div class="seccion-titulo"><h3>Categorías</h3></div>
      <div class="content-bar-seccion-list">
<%
      if (categorias != null && !categorias.isEmpty()) {
        for (String nombreCat : categorias) {
    %>
    	<div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>'"><span><%= nombreCat %></span></div>
     <%
        }
      } else {
    %>
      <div class="content-bar-seccion-list-options"><span>No Hay Categorias</span></div>
    <%
      }
    %>

      </div>
    </div>
  </div>

  <%
    Boolean datosCargados = (Boolean) getServletContext().getAttribute("datosCargados");
    if (datosCargados == null) datosCargados = false;
  %>
	<% if (!datosCargados) { %>
	<div class="content-bar">
	  <div class="content-bar-seccion">
	    <div class="seccion-titulo"><h3>Administración</h3></div>
	    <div class="content-bar-seccion-list">
	    	<div class="content-bar-seccion-list-options" onclick="window.location.href='<%= request.getContextPath() %>/cargarDatos'">
	    		<span>Cargar Datos</span>
	    	</div>
	    </div>
	  </div>
	</div>
	<% } %>




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
             onclick="window.location.href='<%= request.getContextPath() %>/'">
          <span>Alta Institución</span>
        </div>
      </div>
    </div>
  </div>
<% } %>

</aside>
