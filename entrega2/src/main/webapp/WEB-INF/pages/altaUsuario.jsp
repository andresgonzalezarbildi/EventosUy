<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta Usuario</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/main.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
</head>
<body>
  <!-- Header -->
  <jsp:include page="header.jsp" />

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp" />

        <!-- Contenido principal -->
        <main class="col-12 col-md-8 d-flex justify-content-center align-items-start">
          <div class="w-50 mt-4">
            <h2 class="mb-4" style="color: var(--color-primary);">Alta de Usuario</h2>

            <!-- Un solo form con dos botones -->
            <form action="<%=request.getContextPath()%>/usuarios/registrar" method="GET" class="d-flex flex-column gap-3">
  				<button type="submit" name="tipo" value="Organizador" class="btn btn-primary">Organizador</button>
  				<button type="submit" name="tipo" value="Asistente"   class="btn btn-secondary">Asistente</button>
			</form>
          </div>
        </main>
      </div>
    </div>
  </section>

  <jsp:include page="footer.jsp" />
</body>
</html>
