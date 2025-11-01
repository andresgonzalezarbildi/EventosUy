<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta Usuario Organizador</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/main.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
</head>
<body>
  <jsp:include page="header.jsp" />

  <main style="max-width:600px;margin:2rem auto;padding:1rem;background:#fff;border:1px solid var(--color-border);border-radius:var(--radius);">
    <h2 style="margin-bottom:1rem;color:var(--color-primary);">Alta de Organizador</h2>

    <%
      String error = (String) request.getAttribute("error");

      String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
      String nick   = request.getParameter("nick")   == null ? "" : request.getParameter("nick");
      String correo = request.getParameter("correo") == null ? "" : request.getParameter("correo");
      String desc   = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
      String link   = request.getParameter("link") == null ? "" : request.getParameter("link");
    %>
    <% if (error != null) { %>
      <div class="alerta-error" role="alert" style="margin-bottom:1rem;"><%= error %></div>
    <% } %>

    <form action="<%=request.getContextPath()%>/usuarios/registrar" method="POST" enctype="multipart/form-data">
     <input type="hidden" name="tipo" value="Organizador">

	 <label for="nick">Nick:</label>
	<input type="text" id="nick" name="nick" required>
	<span id="nick-status"></span>
      
      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" required value="<%= nombre %>">

     
		<label for="correo">Correo electrónico:</label>
		<input type="email" id="correo" name="correo" required>
		<span id="correo-status"></span>

      <label for="password">Contraseña:</label>
      <input type="password" id="password" name="password" required>

      <label for="confirmPassword">Confirmar contraseña:</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required>

      <label for="descripcion">Descripción:</label>
      <textarea id="descripcion" name="descripcion" required><%= desc %></textarea>

      <label for="link">Link (opcional):</label>
      <input type="url" id="link" name="link" value="<%= link %>">

      <label for="imagen">Imagen (opcional):</label>
      <input type="file" id="imagen" name="imagen" accept="image/*">

      <button type="submit" class="btn" style="margin-top:1rem;">Registrarse</button>
    </form>
  </main>

  <jsp:include page="footer.jsp" />
  
   <script>
		function verificarDisponibilidad(campo, valor) { //se fija si el campo esta vacio para no devolver ningun mensaje
		  if (!valor || valor.trim() === "") {
		    document.getElementById(campo + "-status").innerText = "";
		    return;
		  }
			//el fetch llama al servlet Validarusuarioservlet para mandar un GET
		  fetch("<%= request.getContextPath() %>/ValidarUsuarioServlet?" + campo + "=" + encodeURIComponent(valor)) 
		    .then(res => res.json())
		    .then(data => {
		      const span = document.getElementById(campo + "-status"); //aca se busca el campo donde se escribira el mensaje de disponibilidad  o no en pantalla
		      if (data.error) {
		        span.style.color = "red";
		        span.innerText = "Error de conexión";
		      } else if (data.disponible) {
		        span.style.color = "green";
		        span.innerText = campo === "nick" ? "✔ Nick disponible" : "✔ Correo disponible";
		      } else {
		        span.style.color = "red";
		        span.innerText = campo === "nick" ? "✖ Nick en uso" : "✖ Correo en uso";
		      }
		    })
		    .catch(() => {
		      const span = document.getElementById(campo + "-status");
		      span.style.color = "red";
		      span.innerText = "Error de conexión con el servidor";
		    });
		}
		
		document.addEventListener("DOMContentLoaded", () => {
		  const nickInput = document.getElementById("nick");
		  const correoInput = document.getElementById("correo");
		
		  nickInput.addEventListener("input", () => verificarDisponibilidad("nick", nickInput.value));
		  correoInput.addEventListener("input", () => verificarDisponibilidad("correo", correoInput.value));
		});
</script>
</body>
</html>
