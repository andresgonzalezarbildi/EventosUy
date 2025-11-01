<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta Usuario Asistente</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css"
  >
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/main.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
</head>
<body>
  <!-- Header -->
  <jsp:include page="header.jsp" />
  <main style="max-width: 600px; margin: 2rem auto; padding: 1rem; background: #fff; border: 1px solid var(--color-border); border-radius: var(--radius);">
    <h2 style="margin-bottom: 1rem; color: var(--color-primary);">Alta de Asistente</h2>
	    <%
      	String error = (String) request.getAttribute("error");

      	String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
      	String apellido = request.getParameter("apellido") == null ? "" : request.getParameter("apellido");
      	String nick   = request.getParameter("nick")   == null ? "" : request.getParameter("nick");
      	String correo = request.getParameter("correo") == null ? "" : request.getParameter("correo");
      	String desc   = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
      	String link   = request.getParameter("link") == null ? "" : request.getParameter("link");
    %>
    <% if (error != null) { %>
      <div class="alerta-error" role="alert" style="margin-bottom:1rem;"><%= error %></div>
    <% } %>
	
    <form action="<%=request.getContextPath()%>/usuarios/registrar" method="POST" enctype="multipart/form-data">
      <input type="hidden" name="tipo" value="Asistente">

      <!-- Datos comunes -->
      <label for="nick">Nick:</label>
      <input type="text" id="nick" name="nick" required value="<%= nick %>">
      <span id="nick-status"></span>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" required value="<%= nombre %>">
      
      <label for="apellido">Apellido:</label>
      <input type="text" id="apellido" name="apellido" required value="<%= apellido %>">

      <label for="correo">Correo electrónico:</label>
      <input type="email" id="correo" name="correo" required value="<%= correo %>">
      <span id="correo-status"></span>

      <!-- Contraseñas -->
      <label for="password">Contraseña:</label>
      <input type="password" id="password" name="password" required>

      <label for="confirmPassword">Confirmar Contraseña:</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required>

        <label for="fechaNacimiento">Fecha de Nacimiento:</label>
		<input 
		  type="date" 
		  id="fechaNacimiento" 
		  name="fechaNacimiento" 
		  required
		  value="<%= request.getParameter("fechaNacimiento") == null ? "" : request.getParameter("fechaNacimiento") %>">


        <label for="institucion">Institución:</label>
        <select id="institucion" name="institucion">
          <option value="">Seleccione...</option>
          <option value="Universidad de la República">Universidad de la República</option>
          <option value="ORT">ORT</option>
          <option value="UTEC">UTEC</option>
          <option value="IPA">IPA</option>
          <option value="Otra">Otra</option>
        </select>

      <label for="imagen">Imagen de perfil (Opcional):</label>
      <input type="file" id="imagen" name="imagen" accept="image/*">

		<button type="submit" class="btn" style="margin-top:1rem;">Registrarse</button>
    </form>
  </main>

  <jsp:include page="footer.jsp" />
 
 <script>
		function verificarDisponibilidad(campo, valor) {
		  if (!valor || valor.trim() === "") {
		    document.getElementById(campo + "-status").innerText = "";
		    return;
		  }
		
		  fetch("<%= request.getContextPath() %>/ValidarUsuarioServlet?" + campo + "=" + encodeURIComponent(valor))
		    .then(res => res.json())
		    .then(data => {
		      const span = document.getElementById(campo + "-status");
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

