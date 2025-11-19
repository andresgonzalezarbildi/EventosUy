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
  <style>
    body {
        background-color: #f5f5f5;
    }
    .login-wrapper {
        background: white;
        max-width: 400px;
        margin: 4rem auto;
        padding: 2rem;
        border-radius: 8px;
        box-shadow: 0 0 15px rgba(0,0,0,0.1);
    }
    .login-wrapper h2 {
        text-align: center;
        margin-bottom: 1.5rem;
    }
    .login-wrapper label {
        font-weight: bold;
        margin-top: 0.5rem;
    }
    .login-wrapper input {
        width: 100%;
        margin-bottom: 0.75rem;
        padding: 0.5rem;
        border-radius: 4px;
        border: 1px solid #ccc;
    }
    .login-wrapper .btn {
        width: 48%;
        margin-right: 2%;
    }
    .login-wrapper .btn:last-child {
        margin-right: 0;
    }
    .messages {
        text-align: center;
        margin-bottom: 1rem;
    }
  </style>
</head>
<body>
  <jsp:include page="header.jsp"/>
  <div class="login-wrapper">
    <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
      <h2>Inicio de sesión</h2>

      <%
        String registro = request.getParameter("registro");
        String error = (String) request.getAttribute("error");
        String usuarioIngresado = (String) request.getAttribute("usuarioIngresado");
      %>

<% String msg = (String) session.getAttribute("logout_ok");
   if (msg != null) { %>
   <div class="alert alert-success"><%= msg %></div>
   <% session.removeAttribute("logout_ok"); } %>
      <div class="messages">
        <% if ("ok".equals(registro)) { %>
          <p style="color:green;">Registro exitoso. Ahora podés iniciar sesión.</p>
        <% } %>

        <% if (error != null) { %>
          <p style="color:red;"><%= error %></p>
        <% } %>
      </div>

      <label for="usuario">Usuario o Correo:</label>
      <input type="text" id="usuario" name="usuario" required
             value="<%= usuarioIngresado != null ? usuarioIngresado : "" %>">

      <label for="password">Contraseña:</label>
      <input type="password" id="password" name="password" required>

      <div class="d-flex justify-content-between mt-3">
        <button type="submit" class="btn btn-primary">Iniciar sesión</button>
        <button type="reset" class="btn btn-secondary" onclick="window.location.href='../entrega2'">Cancelar</button>
      </div>
    </form>
  </div>

  <jsp:include page="footer.jsp" />	
</body>
</html>
