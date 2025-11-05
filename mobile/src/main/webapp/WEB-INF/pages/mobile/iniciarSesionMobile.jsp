<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="ws.eventos.DataEvento" %>

<%
  String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Eventos.uy</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
   <link rel="stylesheet" href="<%= ctx %>/estilos/indexMobile.css">
</head>

<body>
  <!-- Header -->
  <jsp:include page="headerMobile.jsp" />

  <!-- Main Content -->
  <section class="content">
    <div class="container-fluid">
      <div class="row d-md-flex justify-content-md-center">
        <!-- Contenido principal -->
        <main class="col-12 col-md-9">
          <div class="contenido-principal">
          	<h1 id="titulo">Inicio de sesión</h1>
			      <%
			        String registro = request.getParameter("registro");
			        String error = (String) request.getAttribute("error");
			        String usuarioIngresado = (String) request.getAttribute("usuarioIngresado");
			      %>
			
			      <div >
			        <% if ("ok".equals(registro)) { %>
			          <p class="alert alert-success">Registro exitoso. Ahora podés iniciar sesión.</p>
			        <% } %>
			
			        <% if (error != null) { %>
			          <p class="alert alert-danger"><%= error %></p>
			        <% } %>
			      </div>
						<form action="<%=request.getContextPath()%>/iniciarSesion" method="post">
						  <div class="form-group">
						    <label for="usuario">Usuario</label>
						    <input 
				    			type="text" 
						    	class="form-control"
				    	 		id="usuario" 
					    		name="usuario"
					    	 	aria-describedby="emailHelp"
					    	 	value="<%= usuarioIngresado != null ? usuarioIngresado : "" %>"
					    	 	required
						    >
						  </div>
						  <div class="form-group">
						    <label for="password">Contraseña</label>
						    <input 
			    	   		type="password"
          				class="form-control"
          				id="password"
          				name="password"
          				required>
						  </div>
						  <button type="submit" class="btn">
						  	Iniciar sesión
						  </button>
						</form>
          </div>
        </main>
      </div>
    </div>
  </section>

  <!-- Footer -->
  <jsp:include page="footerMobile.jsp" />
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>
