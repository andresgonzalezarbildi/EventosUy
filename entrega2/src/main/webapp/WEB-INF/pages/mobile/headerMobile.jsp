<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
    
<%
	HttpSession sesion = request.getSession(false); 
	String nickname = null;
	String imagen = "PerfilSinFoto.jpg";
	
	if (sesion != null) {
	    nickname = (String) sesion.getAttribute("usuario");
	    if (sesion.getAttribute("imagen") != null) {
	        imagen = (String) sesion.getAttribute("imagen");
	    }
	}

  boolean logueado = (sesion != null && sesion.getAttribute("usuario") != null);
	    
	    List<String> categorias = (List<String>) request.getAttribute("categorias");
	    if (categorias == null) categorias = List.of();
	%>
<header class="navbar navbar-expand-lg navbar-light bg-white sticky-top p-2">
<nav class="container-fluid flex-wrap flex-lg-nowrap">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand d-flex align-items-center p-0 me-0 me-lg-2" href="<%=request.getContextPath()%>/mobile/listarEventos">
  	<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 1024 1024"><path fill="#159574" d="M0 1024V0h1024v1024zM960 64H64v896h896zM832 320H384v128h256v128H384v128h448v128H192V192h640z"/></svg>  
	 </a>
  <% if (logueado ) { %>
  <div class="navbar_sesion p-0">
  	<div class="navbar_sesion_perfil">
    	<img class="perfil_image" 
         src="<%= request.getContextPath() %>/img/<%= imagen %>"
         alt="foto perfil" />
 		</div>
 	</div>
  	<% } %>
  		

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
    	<% if (logueado) { %>
      <li class="nav-item active">
        <a class="nav-link" href="<%= request.getContextPath() %>/mobile/CerrarSesion">Cerrar Sesión <span class="sr-only">(current)</span></a>
      </li>
      <%}else{ %>
      	<li class="nav-item active">
        	<a class="nav-link" href="<%= request.getContextPath() %>/mobile/IniciarSesion">Iniciar Sesión <span class="sr-only">(current)</span></a>
      	</li>
      <%} %>
      <%	if ( !categorias.isEmpty() && logueado) { %>
      <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
            Categorias
          </a>
          <ul class="dropdown-menu">
          <% for (String nombreCat : categorias) { %>
          	<li><a class="dropdown-item" href="<%= request.getContextPath() %>/mobile/ListarEventos"><%= nombreCat %></a></li>
          	<li><hr class="dropdown-divider"></li>
          <%} %>
          </ul>
       </li>
       <%} %>
    </ul>
    <% if(logueado){ %>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <%} %>
  </div>
</nav>
</header>