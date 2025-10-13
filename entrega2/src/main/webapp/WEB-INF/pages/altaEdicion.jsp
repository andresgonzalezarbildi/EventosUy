<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="logica.datatypes.DataEvento" %>

<%
    String path = request.getContextPath();
    String nombreEvento = (String) request.getAttribute("nomEv");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta de Edición</title>
    <link rel="stylesheet" href="<%= path %>/estilos/normalize.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%= path %>/estilos/base.css">
    <style>
        .formulario-wrapper {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 2rem auto;
        }
        .error-msg {
            color: red;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<main class="container-fluid">
    <div class="row">
        <jsp:include page="sidebar.jsp"/>
        <div class="col-12 col-md-9">
            <section class="formulario-wrapper">
                <h2>Alta de Edición para Evento: <%= nombreEvento != null ? nombreEvento : "" %></h2>

                <% if (error != null) { %>
                    <p class="error-msg"><%= error %></p>
                <% } %>

                <form action="<%= path %>/edicion" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="opt" value="alta">
                    <input type="hidden" name="id" value="<%= nombreEvento != null ? nombreEvento : "" %>">

                    <label for="nombre">Nombre de la Edición:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control mb-2"
                           value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>

                    <label for="sigla">Sigla:</label>
                    <input type="text" id="sigla" name="sigla" class="form-control mb-2"
                           value="<%= request.getAttribute("sigla") != null ? request.getAttribute("sigla") : "" %>" required>

                    <label for="ciudad">Ciudad:</label>
                    <input type="text" id="ciudad" name="ciudad" class="form-control mb-2"
                           value="<%= request.getAttribute("ciudad") != null ? request.getAttribute("ciudad") : "" %>" required>

                    <label for="pais">País:</label>
                    <input type="text" id="pais" name="pais" class="form-control mb-2"
                           value="<%= request.getAttribute("pais") != null ? request.getAttribute("pais") : "" %>" required>

                    <label for="fechaInicio">Fecha de Inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" class="form-control mb-2"
                           value="<%= request.getAttribute("fechaInicio") != null ? request.getAttribute("fechaInicio") : "" %>" required>

                    <label for="fechaFin">Fecha de Fin:</label>
                    <input type="date" id="fechaFin" name="fechaFin" class="form-control mb-2"
                           value="<%= request.getAttribute("fechaFin") != null ? request.getAttribute("fechaFin") : "" %>" required>

                    <label for="imagen">Imagen (opcional):</label>
                    <input type="file" id="imagen" name="imagen" class="form-control mb-3" accept="image/*">

                    <div class="mt-3">
                        <button type="submit" class="btn btn-primary">Aceptar</button>
                        <button type="reset" class="btn btn-secondary">Cancelar</button>
                    </div>
                </form>
            </section>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>
