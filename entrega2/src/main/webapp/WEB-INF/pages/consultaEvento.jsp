<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Eventos</title>
  <link rel="stylesheet" href="<%= path %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">

  <link rel="stylesheet" href="<%= path %>>/estilos/base.css">
  <link rel="stylesheet" href="<%= path %>/estilos/consultaEvento.css">
</head>
<body>
	<jsp:include page="header.jsp"/>
	  <!-- Main -->
  <main class="container-fluid">
    <div class="row" style="margin-bottom: 1rem;">

      <!-- Sidebar -->
      <jsp:include page="sidebar.jsp"/>

      <div class="col-12 col-md-9">
        <!-- Contenido principal -->
        <div class="contenido-main row">
            <section class="col-12 col-md-7 content-evento row">
              <div class="col-12 row content-evento-resumen">
                <div class="col-12 content-evento-titulo">
                  <p>Maraton Montevideo</p>
                </div>
                <div class="col-12 content-evento-imagen ">
                  <img src="../img/IMG-EV04.png" alt="logo del evento">
                </div>
              </div>
              <div class="col-12 row content-evento-informacion">
                <div>
                  <p class="informacion-label">Descripcion:</p>
                  <p>Competencia deportiva anual en la capital</p>
                </div>
                <div>
                  <p class="informacion-label">Sigla:</p>
                  <p>Maraton</p>
                </div>
                <div>
                  <p class="informacion-label">Categorías</p>
                  <p>Deporte, Salud</p>
                </div>
                <div>
                  <p class="informacion-label">Fecha Alta:</p>
                  <p>01/01/2022</p>
                </div>
              </div>
            </section>
            <aside class="col-12 col-md-5 content-ediciones">
              <div class="seccion-titulo-edicion">
                <h3>Ediciones</h3>
              </div>
              <div class="ediciones"
                onclick="window.location.href='./consultasEdiciones/consultaEdicionEDEV03Visitante.html'">
                <div class="content-edicion">
                  <div class="content-edicion-imagen">
                    <img src="../img/IMG-EDEV03.jpeg" alt="logo del evento">
                  </div>
                  <p class="content-edicion-titulo">
                    Maraton Montevideo 2025
                  </p>
                </div>
              </div>
            </aside>
          </div>
      </div>

    </div>
  </main>

  <jsp:include page="footer.jsp"/>
</body>
</html>