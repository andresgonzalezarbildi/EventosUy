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
  <jsp:include page="/includes/header.jsp" />

  <main style="max-width:600px;margin:2rem auto;padding:1rem;background:#fff;border:1px solid var(--color-border);border-radius:var(--radius);">
    <h2 style="margin-bottom:1rem;color:var(--color-primary);">Alta de Organizador</h2>

<form action="<%=request.getContextPath()%>/UsuarioServlet"
      method="POST"
      enctype="multipart/form-data">

  <input type="hidden" name="op"   value="alta">
  <input type="hidden" name="tipo" value="Organizador">

  <!-- Campos comunes -->
  <label for="nombre">Nombre:</label>
  <input type="text" id="nombre" name="nombre" required>
  
  <label for="nick">Nick:</label>
  <input type="text" id="nick" name="nick" required>

  <label for="correo">Correo electrónico:</label>
  <input type="email" id="correo" name="correo" required>

  <label for="password">Contraseña:</label>
  <input type="password" id="password" name="password" required>

  <label for="confirmPassword">Confirmar contraseña:</label>
  <input type="password" id="confirmPassword" name="confirmPassword" required>

  <!-- Específicos Organizador -->
  <label for="descripcion">Descripción:</label>
  <textarea id="descripcion" name="descripcion"></textarea>

  <label for="link">Link (opcional):</label>
  <input type="url" id="link" name="link">

  <label for="imagen">Imagen (opcional):</label>
  <input type="file" id="imagen" name="imagen" accept="image/*">

  <button type="submit" class="btn btn-primary">¡Registrar Organizador!</button>
</form>
  </main>

  <jsp:include page="/includes/footer.jsp" />
</body>
</html>
