<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"
    import="java.util.List, java.util.Collections" %>
<%
  String path = request.getContextPath();
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
    <title>Error</title>
    <link rel="stylesheet" href="<%= path %>/estilos/normalize.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%= path %>/estilos/base.css">
    <link rel="stylesheet" href="<%= path %>/estilos/error.css">
</head>
<body>
    <!-- Includes absolutos desde el contexto para evitar problemas de ruta -->
    <jsp:include page="/WEB-INF/pages/header.jsp" />

    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar (seguro: categorias nunca es null) -->
                <jsp:include page="/WEB-INF/pages/sidebar.jsp" />

                <main class="col-12 col-md-9">
                    <div class="contenido-principal">
                        <section class="error-page" role="alert" aria-live="polite">
                            <div class="error-box">
                                <img src="https://i.imgur.com/qIufhof.png" alt="Error illustration"/>
                                <h1>
                                    Error
                                </h1>
                                <p><%= mensaje %></p>
                                <p class="info">
                                   <% if (requestUri != null) { %><small>URI: <%= requestUri %></small><br/><% } %>
                                   <a href="<%= path %>/eventos" class="btn btn-primary">Volver al inicio</a>
                                </p>
                            </div>
                        </section>
                    </div>
                </main>
            </div>
        </div>
    </section>

    <jsp:include page="/WEB-INF/pages/footer.jsp" />
</body>
</html>
