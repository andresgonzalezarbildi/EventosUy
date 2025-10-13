<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Iniciar Sesión</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/main.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
</head>
<body>
 <jsp:include page="header.jsp"/>
  <div class="container">
    <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
      <h2>Inicio de sesión</h2>

      <%
        // Mensajes y valores guardados
        String registro = request.getParameter("registro");
        String error = (String) request.getAttribute("error");
        String usuarioIngresado = (String) request.getAttribute("usuarioIngresado");
      %>

      <% if ("ok".equals(registro)) { %>
        <p style="color:green;">Registro exitoso. Ahora podés iniciar sesión.</p>
      <% } %>

      <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
      <% } %>

      <label for="usuario">Usuario o Correo:</label>
      <input type="text" id="usuario" name="usuario" required
             value="<%= usuarioIngresado != null ? usuarioIngresado : "" %>">

      <label for="password">Contraseña:</label>
      <input type="password" id="password" name="password" required>

      <div class="botones">
        <button type="submit">Iniciar sesión</button>
        <button type="reset" onclick="window.location.href='../index.jsp'">Cancelar</button>
      </div>
    </form>
  </div>

  <footer class="uc-footer">
    <small>Eventos.uy — Grupo 42.</small>
  </footer>
</body>
</html>
