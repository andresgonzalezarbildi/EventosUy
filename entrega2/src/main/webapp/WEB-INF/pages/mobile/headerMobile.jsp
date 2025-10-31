<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String nickname = "nickname";
	String imagen = "PerfilSinFoto.jpg";
 %>
<nav class="navbar navbar-expand-lg navbar-light bg-white">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" href="<%=request.getContextPath()%>/indexMobile.jsp">Eventos.uy</a>
  <div class="navbar_sesion">
  	<div class="navbar_sesion_perfil">
  <a href="<%= request.getContextPath() %>/UsuarioServlet?op=consultar&nick=<%= nickname %>">
    <img class="perfil_image" 
         src="<%= request.getContextPath() %>/img/<%= imagen %>"
         alt="foto perfil" />
  </a>
  </div>
  </div>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="#">Eventos <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>