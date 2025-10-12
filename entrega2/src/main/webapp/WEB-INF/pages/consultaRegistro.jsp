<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Consulta de Registro</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/registroAEdicion.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
</head>
<body>
<jsp:include page="header.jsp" />
  <section class="container-fluid content">
    <div class="row content-row">
        <jsp:include page="sidebar.jsp" />
      <main class="col-12 col-md-9 py-4">
        <div class="row justify-content-center">
          <div class="col-12 col-md-11">
            <form class="p-4 p-md-5 rounded-4 shadow-sm bg-white">
           
           
           
           
              <div style="text-align:center; margin-bottom:1.5rem;">
                <img src="../img/IMG-EDEV03.jpeg" alt="Imagen de la edición"
                  style="max-width: 350px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.15);">
              </div>
              <div class="row gy-2 mb-3">
                <p class="col-12 col-md-6"><span class="negrita">Evento:</span> Maratón de Montevideo</p>
                <p class="col-12 col-md-6"><span class="negrita">Edición:</span> Maratón de Montevideo 2024</p>
                <p class="col-12 col-md-6"><span class="negrita">Fecha registro:</span> 30/07/2024</p>
                <p class="col-12 col-md-6"><span class="negrita">Tipo de registro:</span> Corredor21K</p>
                <p class="col-12 col-md-6"><span class="negrita">Organizador:</span> Intendencia de Montevideo </p>
                <p class="col-12 col-md-6"><span class="negrita">Código de patrocinio:</span> No</p>
                <p class="col-12 col-md-6"><span class="negrita">Fecha de inicio:</span> 14/09/2024</p>
                <p class="col-12 col-md-6"><span class="negrita">Fecha de fin:</span> 14/09/2024</p>
                <p class="col-12 col-md-6"><span class="negrita">Ciudad:</span> Montevideo</p>
                <p class="col-12 col-md-6"><span class="negrita">País:</span> Uruguay</p>
                <p class="col-12 col-md-6"><span class="negrita">Costo: </span> $ 500 </p>
                </div>
            </form>
          </div>
        </div>
      </main>
    </div>
  </section>

  <jsp:include page="footer.jsp" />
</body>

</html>