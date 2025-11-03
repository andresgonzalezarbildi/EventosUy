<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%
  String ctx = request.getContextPath();
  Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
  String requestUri  = (String)  request.getAttribute("jakarta.servlet.error.request_uri");
  Throwable ex       = (Throwable)request.getAttribute("jakarta.servlet.error.exception");

  String mensaje = "Ocurrió un error inesperado.";
  if (statusCode != null && statusCode == 404) {
      mensaje = "La página que buscas no existe.";
  } else if (statusCode != null && statusCode == 500) {
      mensaje = "Hubo un problema interno en el servidor.";
  }
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error | Eventos.uy</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/indexMobile.css">
  <style>
    .error-page {
      text-align: center;
      padding: 3rem 1rem;
    }
    .error-page h1 {
      font-size: 3rem;
      margin-bottom: 1rem;
      color: #d9534f;
    }
    .error-page p {
      margin-bottom: 1.5rem;
    }
  </style>
</head>
<body>
  <jsp:include page="./mobile/headerMobile.jsp" />
  <section class="content">
    <div class="container contenido-principal">
      <div class="error-page">
        <h1>Error</h1>
        <p><%= mensaje %></p>
        <% if (requestUri != null) { %><p><small>URI: <%= requestUri %></small></p><% } %>
        <a href="<%= ctx %>/listarEventos" class="btn btn-primary">Volver al inicio</a>
      </div>
    </div>
  </section>
  <jsp:include page="./mobile/footerMobile.jsp" />
  
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>
