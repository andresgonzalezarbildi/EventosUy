<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>



<%
String ctx = request.getContextPath();
LocalDate fechaIni   = (LocalDate) request.getAttribute("fechaIni");
LocalDate fechaFin   = (LocalDate) request.getAttribute("fechaFin");
String ciudad     = (String) request.getAttribute("ciudad");
String pais       = (String) request.getAttribute("pais");
String nomEvento  = (String) request.getAttribute("nomEvento");
String nomTipoReg = (String) request.getAttribute("nomTipoReg");
String nomEdicion = (String) request.getAttribute("nomEdicion");
Integer costo      = (Integer) request.getAttribute("costo");
%>



<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registro a Edición</title>

  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
</head>

<body>
  <jsp:include page="headerMobile.jsp" />
  <section class="container-fluid content">
    <div class="row content-row">
      <main class="col-12 col-md-9 py-4">
        <div class="row justify-content-center">
          <div class="col-12 col-md-11">
		          
		      <form class="p-4 p-md-5 rounded-4 shadow-sm bg-white"
				      action="<%=request.getContextPath()%>/registroEd"
				      method="post">
		      

			<input type="hidden" name="opt" value="alta"> 
			<input type="hidden" name="id" value="<%= nomTipoReg %>"> 
			<input type="hidden" name="idEdicion" value="<%= nomEdicion %>"> 
			<input type="hidden" name="nickname" value="<%= (String)session.getAttribute("usuario") %>"> 

              <div class="row gy-2 mb-3">
              	
				<%
				String ok  = (String) request.getAttribute("flash_ok");
				String err = (String) request.getAttribute("flash_error");
				if (err != null && !err.isEmpty()) {
				%>
				<div class="alert alert-danger" style="margin-bottom: 15px;"><%= err %></div>
				<%
				} else if (ok != null && !ok.isEmpty()) {
				%>
				<div class="alert alert-success" style="margin-bottom: 15px;"><%= ok %></div>
				<%
				}
				%>
                <p class="col-12 col-md-6"><span class="negrita">Nombre Evento:</span><%= nomEvento %></p>
                <p class="col-12 col-md-6"><span class="negrita">Nombre Edición:</span> <%= nomEdicion %>
                </p>
                <p class="col-12 col-md-6"><span class="negrita">Fecha de inicio:</span> <%= fechaIni %></p>
                <p class="col-12 col-md-6"><span class="negrita">Fecha de fin:</span> <%= fechaFin %></p>
                <p class="col-12 col-md-6"><span class="negrita">Ciudad:</span> <%= ciudad %></p>
                <p class="col-12 col-md-6"><span class="negrita">País:</span> <%= pais %></p>
              </div>

              <hr class="my-3">

              <div class="row mb-3 align-items-center gx-2">
                <label for="tipoRegistro" class="col-12 col-md-auto col-form-label pe-md-2"><span class="negrita">Tipo
                    de registro:</span></label>
                <div class="col-12 col-md">
                  <p class="col-12 col-md-6"> <%= nomTipoReg%></p>
                </div>
              </div>

              <div class="row gy-2 mb-3">
                <p class="col-12 col-md-6"><span class="negrita">Costo: </span><%= costo%></p>
              </div>

              <hr class="my-3">

              <div class="row g-2">
                <div class="col-12 col-md-auto">
                  <label for="codigoPatrocinio" class="col-form-label mb-0 text-nowrap"><span class="negrita">Código
                      Patrocinio:</span></label>
                </div>

                <div class="col-12 col-md-5 col-lg-4">
                  <input type="text" id="codigoPatrocinio" class="form-control">
                </div>

                <div class="col-12 col-md-auto d-flex align-items-end">
                  <button type="button" class="btn btn-success px-4 mt-1">Aplicar</button>
                </div>
              </div>


              <div class="row gy-2 mb-3">
                <p class="col-12 col-md-6"><span class="negrita">Costo final:</span> <%= costo%></p>
              </div>

              <!-- Botón -->
              <div class="text-end">
                <button type="submit" class="btn btn-primary">Confirmar Registro</button>
              </div>
            </form>
          </div>
        </div>
      </main>
    </div>
  </section>
  
  
  <jsp:include page="footerMobile.jsp" />

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  

</body>

</html>