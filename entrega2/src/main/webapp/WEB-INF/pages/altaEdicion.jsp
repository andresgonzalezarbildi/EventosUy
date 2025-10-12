<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String nomEv = (String) request.getAttribute("nomEv");
    String path = request.getContextPath();
%>	

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta Edición de Evento</title>
  <link rel="stylesheet" href="<%=path%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=path%>/estilos/main.css">
  <link rel="stylesheet" href="<%=path%>/estilos/base.css">
</head>


<body>
  <jsp:include page="header.jsp"/>

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="sidebar.jsp"/>

        <main style="max-width:600px;margin:2rem auto;padding:1rem;background:#fff;border:1px solid var(--color-border);border-radius:var(--radius);">
          <h2 style="margin-bottom:1rem;color:var(--color-primary);">Alta de Edición de Evento</h2>

          <form id="formEdicionEvento"
                action="<%=path%>/edicion"
                method="post"
                enctype="multipart/form-data">

            <!-- Campo oculto para el tipo de operación -->
            <input type="hidden" name="opt" value="alta">

            <!-- Campo oculto con el ID del evento -->
            <input type="hidden" name="id" value="<%= nomEv %>">

            <label>Evento:</label>
            <input type="text" value="<%= nomEv %>" readonly>

            <label for="nombre">Nombre de la Edición:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="sigla">Sigla:</label>
            <input type="text" id="sigla" name="sigla" required>

            <label for="ciudad">Ciudad:</label>
            <input type="text" id="ciudad" name="ciudad" required>

            <label for="pais">País:</label>
            <input type="text" id="pais" name="pais" required>

            <label for="fechaInicio">Fecha de Inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" required>

            <label for="fechaFin">Fecha de Fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" required>

            <label for="imagen">Imagen (opcional):</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">

            <div style="margin-top:1.5rem;display:flex;gap:1rem;">
              <button type="submit">Aceptar</button>
              <button type="reset">Cancelar</button>
            </div>
          </form>
        </main>
      </div>
    </div>
  </section>

  <jsp:include page="footer.jsp"/>
</body>
</html>

