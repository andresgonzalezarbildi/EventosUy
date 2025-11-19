<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ws.eventos.DataTipoRegistro" %>
<%@ page import="ws.eventos.DataEdicion" %>
<%@ page import="ws.eventos.DataRegistro" %>
<%@ page import="java.time.LocalDate" %>

<%
	DataRegistro registro = (DataRegistro) request.getAttribute("registro");
	DataTipoRegistro tipoRegistro =  (DataTipoRegistro) request.getAttribute("tipoRegistro");
	DataEdicion edicion = (DataEdicion) request.getAttribute("edicion");
	String error = (String) request.getAttribute("error");
	String exito = (String) request.getAttribute("exito");
	
  boolean edicionIniciada = false;

  try {
      if (edicion != null && edicion.getFechaIni() != null && !edicion.getFechaIni().isEmpty()) {
          java.time.LocalDate fechaInicio =LocalDate.parse(edicion.getFechaIni());
          java.time.LocalDate hoy = LocalDate.now();
          edicionIniciada = !hoy.isBefore(fechaInicio); 
      }
  } catch (Exception e) {
      edicionIniciada = false;
  }
 %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Detalle Tipo de Registro</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/consultaTipoRegistroMobile.css">
  
</head>
<body>
	<jsp:include page="headerMobile.jsp"/>
	
	  <section class="content">
    <div class="container-fluid">
      <div class="row contenedor">
      		<div class="col-12 col-md-8">
	      		<% if (error != null){ %>
	      		<div class="col-12 alerta-error">
	      			<%= error %>
	      		</div>
	      		<%} %>
        		<main>
          	<h2 style="margin-bottom: 1rem; color: var(--color-primary); text-align:center;">
          		Detalle de su Registro
          	</h2>
		
					  <% if (registro  != null && tipoRegistro != null) { %>
		  			<dl>
					    <dt><strong>Evento:</strong> </dt>
					    <dd><%=registro.getEvento() %></dd>
					    <dt><strong>Edición:</strong></dt> 
					    <dd><%=registro.getEdicion()%></dd>
					    <dt><strong>Tipo De Registro:</strong> </dt>
					    <dd><%=tipoRegistro.getNombre() %></dd>
					    <dt><strong>Descripción:</strong> </dt>
					    <dd><%=tipoRegistro.getDescripcion()%></dd>
					    <dt><strong>Costo:</strong> </dt>
					    <dd><%=tipoRegistro.getCosto()%></dd>
					    <dt><strong>Cupos Totales:</strong> </dt>
					    <dd><%=tipoRegistro.getCupo()%></dd>
					    <dt><strong>Usted Pago:</strong> </dt>
					    <dd><%=registro.getCosto()%></dd>
					    <dt><strong>Fecha de Registro:</strong> </dt>
					    <dd><%=registro.getFecha()%></dd>
			  		</dl>
			  
					  <% if(edicionIniciada) { 
					    	if(!registro.isConfirmarAsistencia()){ %>
					  	<form method="post" action="<%= request.getContextPath() %>/confirmarAsistencia" style="display:inline;">
					    <input type="hidden" name="edicion" value="<%= registro.getEdicion() %>">
					    <button type="submit" class="btn ">Confirmar Asistencia</button>
					  </form>
					  <%}else{ %>
					  		<div class="alerta-exito">
					  			Usted Ya confirmo asistencia a esta Edicion
					  		</div>
				  <%	} 
			    	}%>
					  <%} %>
 	
		</main>
		</div>
		
			<div class="col-12 col-md-4" style="display:flex; flex-direction:column; align-items:center;">
				<%
				    if (edicion != null) {
				        String imagenEdicion = (edicion.getImagen() != null && !edicion.getImagen().isEmpty())
				                              ? edicion.getImagen()
				                              : "EdicionSinFoto.png";
				%>
				    <div style="margin-bottom:12px; text-align:center;">
				        <a href="<%= request.getContextPath() %>/consultaEdicion?id=<%= edicion.getNombre() %>">
				            <img src="<%= request.getContextPath() %>/MediaServlet?name=<%= imagenEdicion %>" 
				                 alt="Consultar Evento"
				                 style="max-width:150px; width:100%; cursor:pointer; border-radius:8px; 
				                        box-shadow:0 2px 6px rgba(0,0,0,0.2);">
				        </a>
				        <div style="margin-top:4px; font-size:0.9rem; color:#555;">
				            <%= edicion.getNombre() %>
				        </div>
				    </div>
				<%
				    } else {
				%>
				    <p style="text-align:center; color:#777;">Error al Cargar la Edicion.</p>
				<%
				    }
				%>
			</div>
</div>
</div>
 </section>

	<jsp:include page="footerMobile.jsp" />
	
	 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>
</html>
