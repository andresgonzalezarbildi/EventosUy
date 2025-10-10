<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Consulta Usuario</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/consultaUsuario.css">
</head>
<body>
  <jsp:include page="header.jsp" />

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="sidebar.jsp" />
        <main class="col-12 col-md-9 d-flex justify-content-center align-items-start">
          <%
            String rol = (String) session.getAttribute("rol");
            if ("Organizador".equalsIgnoreCase(rol)) {
          %>
              <%
			request.getRequestDispatcher("/WEB-INF/pages/consultaUsuarioOrganizador.jsp").include(request, response);
			%>

          <%
            } else if ("Asistente".equalsIgnoreCase(rol)) {
          %>
              <%
			request.getRequestDispatcher("/WEB-INF/pages/consultaUsuarioAsistente.jsp").include(request, response);
			%>

          <%
            } else {
          %>
              <p>Error: rol inválido.</p>
          <%
            }
          %>
        </main>
      </div>
    </div>
  </section>

  <jsp:include page="footer.jsp" />
</body>
</html>
