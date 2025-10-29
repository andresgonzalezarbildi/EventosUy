<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"
    import="java.util.List, java.util.Collections" %>
<%
    String path = request.getContextPath();


    Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String message = (String) request.getAttribute("javax.servlet.error.message");
    Throwable excepcion = (Throwable) request.getAttribute("javax.servlet.error.exception");
    String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
    if (uri == null) uri = request.getRequestURI();


    String customMsg = (String) request.getAttribute("mensajeError");


    String titulo = "";
    String descripcion = "";

    if (customMsg != null && !customMsg.isEmpty()) {
        descripcion = customMsg;
        titulo = "Error";
    } else if (status != null) {
        switch (status) {
            case 404:
                titulo = "404 - Página no encontrada";
                descripcion = "La página que buscas no existe o fue movida.";
                break;
            case 400:
                titulo = "400 - Solicitud incorrecta";
                descripcion = "La solicitud enviada es inválida o incompleta.";
                break;
            case 403:
                titulo = "403 - Acceso denegado";
                descripcion = "No tienes permisos para acceder a este recurso.";
                break;
            case 500:
                titulo = "500 - Error interno del servidor";
                descripcion = "Estamos trabajando para solucionar el problema.";
                break;
            default:
                titulo = status + " - Error inesperado";
                descripcion = (message != null && !message.isEmpty())
                              ? message
                              : "Se produjo un error inesperado.";
        }
    } else {
        titulo = "Error desconocido";
        descripcion = "Algo salió mal, pero no pudimos determinar la causa.";
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= titulo %></title>
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
                                    <span><%= (status != null ? status : 500) %></span><br/>
                                    <%= titulo %>
                                </h1>
                                <p><%= descripcion %></p>
                                <p class="info">
                                    <small>URL: <%= uri %></small><br/>
                                    <% if (message != null && !message.isEmpty()) { %>
                                        <small>Detalle: <%= message %></small><br/>
                                    <% } %>
                                    <% if (excepcion != null) { %>
                                        <small>Excepción: <%= excepcion.getClass().getSimpleName() %></small>
                                    <% } %>
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
