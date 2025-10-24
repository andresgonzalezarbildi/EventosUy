<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.datatypes.DataTipoRegistro" %>
<%@ page import="logica.datatypes.DataEdicion" %>
<%@ page import="logica.datatypes.DataRegistro" %>
<%
	String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Detalle Tipo de Registro</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
</head>
<body>
	<jsp:include page="headerMobile.jsp"/>
	
	  <section class="content">
    	<div class="container-fluid">
      	<div class="row">      
       		<main class="col-12">
   		      <div class="contenido-principal">
          		<h2 style="margin-bottom: 1rem; color: var(--color-primary); text-align:center;">Detalle del Tipo De Registro
          </h2>

		  <%
		    DataTipoRegistro tipo = (DataTipoRegistro) request.getAttribute("tipoRegistro");
		    String evento = (String) request.getAttribute("evento");
		    String nombreEdicion = (String) request.getAttribute("nombreEdicion");
		    String rol = (String) request.getAttribute("rol");
		    String nickname = (String) request.getAttribute("nickname");
		    DataRegistro registroAsistente = (DataRegistro) request.getAttribute("registroAsistente");
		  %>
		
		  <% if (tipo != null) { %>
  <dl>
    <dt><strong>Nombre:</strong> </dt>
    <dd><%=tipo.getNombre()%></dd>
    <dt><strong>Evento:</strong> </dt>
    <dd><%=evento%></dd>
    <dt><strong>Edición:</strong></dt> 
    <dd><%=nombreEdicion%></dd>
    <dt><strong>Descripción:</strong> </dt>
    <dd><%=tipo.getDescripcion()%></dd>
    <dt><strong>Costo:</strong> </dt>
    <dd><%=tipo.getCosto()%></dd>
    <dt><strong>Cupos Totales:</strong> </dt>
    <dd><%=tipo.getCupo()%></dd>
  </dl>

	<%
	  DataEdicion edicion = (DataEdicion) request.getAttribute("edicion");
	  boolean edicionActiva = false;
	  if (edicion != null && edicion.getFechaFin() != null) {
	      edicionActiva = edicion.getFechaFin().isAfter(java.time.LocalDate.now());
	  }
	%>
	
	<% if ("asistente".equalsIgnoreCase(rol) && registroAsistente == null && edicionActiva) { %>
	    <a style="padding: 0.4rem;" href="<%=request.getContextPath()%>/registroEd?op=alta&id=<%=tipo.getNombre()%>&idEdicion=<%=nombreEdicion%>" class="btn">
	      Registrarse
	    </a>
	<% } else if (!edicionActiva) { %>
	    <p class="text-danger mt-2">La edición ya finalizó. No se pueden registrar nuevos asistentes.</p>
	<% } %>
	
	<% } %>


		</dl> 
		</div>	
		</main>
		
<!-- 			<div style="display:flex; flex-direction:column; align-items:center;"> -->
<%-- 				<% --%>
<%--  			   	 DataEdicion edicion = (DataEdicion) request.getAttribute("edicion");
// 				    if (edicion != null) {
// 				        String imagenEdicion = (edicion.getImagen() != null && !edicion.getImagen().isEmpty())
// 				                              ? edicion.getImagen()
// 				                              : "EdicionSinFoto.png";
<%-- 				%> --%>
<!-- 				    <div style="margin-bottom:12px; text-align:center;"> -->
<%-- 				        <a href="<%= request.getContextPath() %>/edicion?op=consultar&id=<%= nombreEdicion %>"> --%>
<%-- 				            <img src="<%= request.getContextPath() %>/img/<%= imagenEdicion %>"  --%>
<!-- 				                 alt="Consultar Evento" -->
<!-- 				                 style="max-width:150px; width:100%; cursor:pointer; border-radius:8px;  -->
<!-- 				                        box-shadow:0 2px 6px rgba(0,0,0,0.2);"> -->
<!-- 				        </a> -->
<!-- 				        <div style="margin-top:4px; font-size:0.9rem; color:#555;"> -->
<%-- 				            <%= nombreEdicion %> --%>
<!-- 				        </div> -->
<!-- 				    </div> -->
<%-- 				<% --%>
<%--  				    } else {
<%-- 				%> --%>
<!-- 				    <p style="text-align:center; color:#777;">No hay edicion disponible.</p> -->
<%-- 				<% --%>
<%--  				    }
<%-- 				%> --%>
<!-- 			</div> -->
</div>
</div>
</div>
 </section>

	<jsp:include page="footerMobile.jsp"/>
	
	 <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>
