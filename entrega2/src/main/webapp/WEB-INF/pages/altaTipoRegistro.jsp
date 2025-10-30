<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ws.eventos.DataEdicion" %>

<%
    String path = request.getContextPath();
    DataEdicion edicion = (DataEdicion) request.getAttribute("edicion");
    String eventoNombre = (String) request.getAttribute("evento");
    String edicionNombre = (String) request.getAttribute("nombreEdicion");
    String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta de Tipo Registro</title>
    <link rel="stylesheet" href="<%= path %>/estilos/normalize.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%= path %>/estilos/base.css">
    <link rel="stylesheet" href="<%= path %>/estilos/consultaEvento.css">
    <style>
        .formulario-wrapper {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .content-evento img {
            max-width: 100%;
            height: auto;
            display: block;
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
    <div class="row mb-4">
        <jsp:include page="sidebar.jsp"/>
        <div class="col-12 col-md-9">
            <div class="row">

                <!-- FORMULARIO IZQUIERDA -->
                <section class="col-12 col-md-6 formulario-wrapper">
                    <h2>Alta de Tipo de Registro</h2>

                    <% if (error != null) { %>
                        <p class="alerta-error"><%= error %></p>
                    <% } %>

                    <form action="<%= path %>/TipoRegistroServlet" method="post">
                        <input type="hidden" name="op" value="alta">
                        <input type="hidden" name="evento" value="<%= eventoNombre != null ? eventoNombre : "" %>">
                        <input type="hidden" name="edicion" value="<%= edicionNombre != null ? edicionNombre : "" %>">

                        <p class="form-control-plaintext"><%= eventoNombre != null ? eventoNombre : "" %></p>
                        <p class="form-control-plaintext"><%= edicionNombre != null ? edicionNombre : "" %></p>

                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="form-control mb-2"
                               value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>

                        <label for="descripcion">Descripción:</label>
                        <input type="text" id="descripcion" name="descripcion" class="form-control mb-2"
                               value="<%= request.getAttribute("descripcion") != null ? request.getAttribute("descripcion") : "" %>" required>

                        <label for="costo">Costo:</label>
                        <input type="number" id="costo" name="costo" class="form-control mb-2" min="0"
                               value="<%= request.getAttribute("costo") != null ? request.getAttribute("costo") : 0 %>" required>

                        <label for="cupo">Cupo:</label>
                        <input type="number" id="cupo" name="cupo" class="form-control mb-2" min="0"
                               value="<%= request.getAttribute("cupo") != null ? request.getAttribute("cupo") : 0 %>" required>

                        <div class="mt-3">
                            <button type="submit" class="btn btn-primary">Aceptar</button>
                            <button type="reset" class="btn btn-secondary">Cancelar</button>
                        </div>
                    </form>
                </section>

                <!-- EDICIÓN DERECHA -->
                <section class="col-12 col-md-6 content-evento">
                    <% if (edicion != null) { %>
                        <div style="text-align:center; margin-top:1rem;">
                            <a href="<%= path %>/edicion?op=consultar&id=<%= edicion.getNombre() %>">
                                <img src="<%= path %>/img/<%= (edicion.getImagen() != null && !edicion.getImagen().isEmpty()) ? edicion.getImagen() : "EventoSinFoto.png" %>"
                                     alt="Consultar Edición"
                                     style="max-width:150px; width:100%; cursor:pointer; border-radius:8px;
                                            box-shadow:0 2px 6px rgba(0,0,0,0.2);">
                            </a>
                            <div style="margin-top:4px; font-size:0.9rem; color:#555;">
                                <%= edicion.getNombre() %>
                            </div>
                        </div>
                    <% } else { %>
                        <p style="text-align:center; color:#777;">No hay edición disponible.</p>
                    <% } %>
                </section>

            </div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>
