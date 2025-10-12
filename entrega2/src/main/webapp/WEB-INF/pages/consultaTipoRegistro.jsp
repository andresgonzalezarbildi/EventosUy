<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.DataTipoRegistro" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Detalle Tipo de Registro</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
</head>
<body>
	<jsp:include page="header.jsp"/>

<div class="container mt-4">
  <%
    DataTipoRegistro tipo = (DataTipoRegistro) request.getAttribute("tipoRegistro");
    String evento = (String) request.getAttribute("evento");
    String edicion = (String) request.getAttribute("edicion");
  %>	
  

  <% if (tipo != null) { %>
    <h2>Detalle del Tipo de Registro</h2>

    <p><strong>Evento:</strong> <%=evento%></p>
    <p><strong>Edición:</strong> <%=edicion%></p>
    <p><strong>Nombre:</strong> <%=tipo.getNombre()%></p>
    <p><strong>Descripción:</strong> <%=tipo.getDescripcion()%></p>
    <p><strong>Costo:</strong> $<%=tipo.getCosto()%></p>
    <p><strong>Cupos:</strong> <%=tipo.getCupo()%></p>

    <a href="<%=request.getContextPath()%>/TipoRegistroServlet?op=listar&evento=<%=evento%>&edicion=<%=edicion%>" class="btn btn-secondary mt-3">
      Volver al listado
    </a>
  
  <% } else { %>
    <p>No se encontró el tipo de registro.</p>
  <% } %>
</div>

	<jsp:include page="footer.jsp"/>
</body>
</html>
