<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta Usuario Asistente</title>
  <link rel="stylesheet" href="../estilos/normalize.css">
   <link
    rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css"
  >
  <link rel="stylesheet" href="../estilos/main.css">
  <link rel="stylesheet" href="../estilos/base.css">
</head>
<body>
    <!-- Header -->
  	<jsp:include page="../includes/header.jsp" />

  <main style="max-width: 600px; margin: 2rem auto; padding: 1rem; background: #fff; border: 1px solid var(--color-border); border-radius: var(--radius);">
    <h2 style="margin-bottom: 1rem; color: var(--color-primary);">Alta de Asistente</h2>
    
    <form>
      <!-- Datos comunes -->
      <label for="nick">Nick:</label>
      <input type="text" id="nick" name="nick" required>

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" required>

      <label for="correo">Correo electrÃ³nico:</label>
      <input type="email" id="correo" name="correo" required>

      <!-- ContraseÃ±as -->
      <label for="password">ContraseÃ±a:</label>
      <input type="password" id="password" name="password" required>

      <label for="confirmPassword">Confirmar ContraseÃ±a:</label>
      <input type="password" id="confirmPassword" name="confirmPassword" required>

    

      <!-- Campos especÃ­ficos de asistente -->
      <fieldset style="margin-top: 1.5rem; border: 1px solid #ccc; padding: 1rem; border-radius: 8px;">
		
		<label for="apellido">Apellido:</label>
      <input type="apellido" id="apellido" name="apellido" required>
			
        <label for="fechaNac">Fecha de Nacimiento:</label>
        <input type="date" id="fechaNac" name="fechaNac">
		
        <label for="institucion">InstituciÃ³n:</label>
        <select id="institucion" name="institucion">
          <option value="">Seleccione...</option>
          <option value="Universidad de la RepÃºblica">Universidad de la RepÃºblica</option>
          <option value="ORT">ORT</option>
          <option value="UTEC">UTEC</option>
          <option value="IPA">IPA</option>
          <option value="Otra">Otra</option>
        </select>

        
      </fieldset>
	<label for="imagen">Imagen de perfil (Opcional):</label>
        <input type="file" id="imagen" name="imagen" accept="image/*">

      <!-- BotÃ³n -->
      <div style="margin-top: 1.5rem; display: flex; gap: 1rem;">
        <button type="submit">Â¡Registrar Asistente!</button>
        <button type="submit">Cancelar</button>
      </div>
    </form>
  </main>

  <jsp:include page="../includes/footer.jsp">
</body>
</html>
