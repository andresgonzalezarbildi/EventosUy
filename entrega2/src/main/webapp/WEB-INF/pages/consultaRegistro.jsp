<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<% String imagen = (String) request.getAttribute("imagen"); %>
<% String nickname = (String) request.getAttribute("nickname"); %>
<% String idEdicion = (String) request.getAttribute("idEdicion"); %>
<% String nomEvento = (String) request.getAttribute("nomEvento"); %>
<% String fechaRegistro = (String) request.getAttribute("fechaRegistro"); %>
<% String nomTipoRegistro = (String) request.getAttribute("nomTipoRegistro"); %>
<% String organizador = (String) request.getAttribute("organizador"); %>
<% String fechaIni = (String) request.getAttribute("fechaIni"); %>
<% String fechaFin = (String) request.getAttribute("fechaFin"); %>
<% String ciudad = (String) request.getAttribute("ciudad"); %>
<% String pais = (String) request.getAttribute("pais"); %>
<% Integer costo = (Integer) request.getAttribute("costo"); %>
<% String rol = (String) request.getAttribute("rol"); %>


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
              <h2 style="margin-bottom: 1rem; color: var(--color-primary); text-align:center;">Detalle del Registro
          </h2>
                <img src="<%= request.getContextPath() %>/img/<%= imagen %>" alt="Imagen de la edición"
                  style="max-width: 350px; border-radius: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.15);">
              </div>
              <div class="row gy-2 mb-3">
                <p class="col-12 col-md-6"><span class="negrita">Asistente:</span> <%= nickname %></p>
                <p class="col-12 col-md-6"><span class="negrita">Edición:</span> <%= idEdicion %></p>
                <p class="col-12 col-md-6"><span class="negrita">Fecha registro:</span> <%= fechaRegistro %></p>
                <p class="col-12 col-md-6"><span class="negrita">Tipo de registro:</span> <%= nomTipoRegistro %></p>
                <p class="col-12 col-md-6"><span class="negrita">Tipo de registro:</span> <%= nomTipoRegistro %></p>
                <p class="col-12 col-md-6"><span class="negrita">Código de patrocinio:</span>-</p>
                
                <% if ("asistente".equalsIgnoreCase(rol)) { %>
                    <p class="col-12 col-md-6"><span class="negrita">Evento:</span><%= nomEvento %></p>
	                <p class="col-12 col-md-6"><span class="negrita">Organizador:</span><%= organizador %></p>
	                <p class="col-12 col-md-6"><span class="negrita">Fecha de inicio:</span><%= fechaIni %></p>
	                <p class="col-12 col-md-6"><span class="negrita">Fecha de fin:</span><%= fechaFin %></p>
	                <p class="col-12 col-md-6"><span class="negrita">Ciudad:</span><%= ciudad %></p>
	                <p class="col-12 col-md-6"><span class="negrita">País:</span><%= pais %></p>
	                <p class="col-12 col-md-6"><span class="negrita">Costo: </span><%= costo %></p>
	            <% } %>
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