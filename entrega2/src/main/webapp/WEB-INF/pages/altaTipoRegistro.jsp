<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta de Tipo Registro</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/main.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container mt-4">
  <h2>Alta de Tipo de Registro</h2>

  <%
    String error = (String) request.getAttribute("error");
    Map<String, List<String>> ediciones = (Map<String, List<String>>) request.getAttribute("ediciones");
  %>

  <% if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
  <% } %>

  <form action="<%=request.getContextPath()%>/TipoRegistroServlet" method="post">
    <input type="hidden" name="op" value="alta">

    <label for="evento">Evento:</label>
    <select name="evento" id="evento" class="form-select mb-2" required>
      <%
        if (ediciones != null) {
            for (String evento : ediciones.keySet()) {
      %>
        <option value="<%=evento%>" <%= evento.equals(request.getAttribute("evento")) ? "selected" : "" %>><%=evento%></option>
      <%
            }
        }
      %>
    </select>

    <label for="edicion">Edición:</label>
    <select name="edicion" id="edicion" class="form-select mb-2" required>
      <%
        String selectedEvento = (String) request.getAttribute("evento");
        if (selectedEvento != null && ediciones != null && ediciones.get(selectedEvento) != null) {
            for (String ed : ediciones.get(selectedEvento)) {
      %>
        <option value="<%=ed%>" <%= ed.equals(request.getAttribute("edicion")) ? "selected" : "" %>><%=ed%></option>
      <%
            }
        }
      %>
    </select>

    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" class="form-control mb-2" 
           value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>" required>

    <label for="descripcion">Descripción:</label>
    <input type="text" id="descripcion" name="descripcion" class="form-control mb-2"
           value="<%=request.getAttribute("descripcion") != null ? request.getAttribute("descripcion") : ""%>" required>

    <label for="costo">Costo:</label>
    <input type="number" id="costo" name="costo" class="form-control mb-2" min="0"
           value="<%=request.getAttribute("costo") != null ? request.getAttribute("costo") : 0%>" required>

    <label for="cupo">Cupo:</label>
    <input type="number" id="cupo" name="cupo" class="form-control mb-2" min="0"
           value="<%=request.getAttribute("cupo") != null ? request.getAttribute("cupo") : 0%>" required>

    <div class="mt-3">
      <button type="submit" class="btn btn-primary">Aceptar</button>
      <button type="reset" class="btn btn-secondary">Cancelar</button>
    </div>
  </form>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>
