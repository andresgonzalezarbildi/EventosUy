<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ws.eventos.DataEdicion" %>
<%@ page import="ws.eventos.DataTipoRegistro" %>
<%@ page import="ws.eventos.DataRegistro" %>
<%@ page import="java.util.List" %>
<%@ page import="ws.eventos.DataEvento" %>
<%@ page import="ws.eventos.DataPatrocinio" %>
<%@ page import="java.net.URLEncoder" %>
<%
	String ctx = request.getContextPath();
	String rol = (String) request.getAttribute("rol");
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Detalle de Edición</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/consultaEdicionMobile.css">
</head>


<%
    DataEdicion ed = (DataEdicion) request.getAttribute("edicion");
	DataEvento evento = (DataEvento) request.getAttribute("evento");
	DataRegistro registroAsistente = (DataRegistro) request.getAttribute("registroAsistente");
    String nickname = (String) request.getAttribute("nickname");
%>


<body>
 <jsp:include page="headerMobile.jsp" />

  <!-- Main + Sidebar -->
  <section class="content" style="margin-bottom: 1rem">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12 col-md-8" 
        	style="max-width: 100%; 
        	padding: 1.5rem; 
        	background: #fff; 
        	border: 1px solid var(--color-border); 
        	border-radius: var(--radius); 
        	box-shadow: 0 2px 6px rgba(0,0,0,0.1);">
		      <!-- Columna izquierda: imagen y datos -->
		      <div class="flex-grow-1 d-flex flex-column align-items-center">
        	<main class="d-flex flex-column justify-content-center align-items-center">
	          <h2 style="margin-bottom: 1rem; color: var(--color-primary); text-align:center;">Detalle de Edición
	          </h2>
							<% if (ed != null) { %>
								<% if (ed.getImagen() != null && !ed.getImagen().isEmpty()) { %>
								  <img src="<%= request.getContextPath() %>/MediaServlet?name=<%= ed.getImagen() %>" 
								       alt="Imagen de la edición" style="max-width:300px;border-radius:8px;">
					       <div class="d-flex w-100">
				          <dl>
				            <dt><strong>Nombre:</strong></dt>
				            <dd><%= ed.getNombre() %></dd>
				            <dt><strong>Sigla:</strong></dt>
				            <dd><%= ed.getSigla() %></dd>
				            <dt><strong>Ciudad:</strong></dt>
				            <dd><%= ed.getCiudad() %></dd>
				            <dt><strong>País:</strong></dt>
				            <dd><%= ed.getPais() %></dd>
				            <dt><strong>Fecha Inicio:</strong></dt>
				            <dd><%= ed.getFechaIni() %></dd>
				            <dt><strong>Fecha Fin:</strong></dt>
				            <dd><%= ed.getFechaFin() %></dd>
				            <dt><strong>Alta en Plataforma:</strong></dt>
				            <dd><%= ed.getFechaAltaEnPlataforma() %></dd>
				          </dl>
				          </div>      
         				 	<section class="informacion">
		    						<h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Tipos de Registro</h3>
			   					 	<div style="display:flex; gap:0.5rem; flex-wrap:wrap;">
							        <%
					 		            List<DataTipoRegistro> tipos = ed.getTiposRegistro();
							            if (tipos != null && !tipos.isEmpty()) {
							                for (DataTipoRegistro tipo : tipos) {
							        %>
		                    <span
											   style="display:inline-block; background:#f0f0f0; padding:0.4rem 0.8rem;
											          border-radius:12px; text-decoration:none; color:inherit;">
											    <%= tipo.getNombre() %>
												</span>
							        <%
						                }
						            } else {
							        %>
			                		<p>No hay tipos de registro disponibles para esta edición.</p>
							        <%
							            }
							        %>
			        		  
			    					</div>
									</section>
				          <!-- Organizador -->
			          	<section class="informacion">
				            <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Organizador</h3>
				            <p><%= ed.getOrganizador() %></p>
			          	</section>
				          <!-- Patrocinios -->
				          <section class="informacion">
				            <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Patrocinios</h3>
				            <div style="display:flex; gap:0.5rem; flex-wrap:wrap;">
				              		    <%
		      java.util.List<ws.eventos.DataPatrocinio> patros = ed.getPatrocinios();
		      if (patros != null && !patros.isEmpty()) {
		          for (DataPatrocinio p : patros) {
		    %>
		              <span style="background:#e3f2fd; padding:0.4rem 0.8rem; border-radius:12px;">
		                <%= p.getCodigoDePatrocinio() %> (<%= p.getNivelDePatrocinio() %>)
		              </span>
		    <%
		          }
		      } else {
		    %>
		          <span style="color:#777;">No hay patrocinios registrados para esta edición.</span>
		    <%
		      }
		    %>
				            </div>
				           </section>
										<%  
										if ( registroAsistente != null) {									
										%>
							        <section class="informacion">
					           		<h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Tu Registro:</h3>
				              	<fieldset class="card-registro">
					                <dl>
					                  <dt><strong>Tipo de Registro:</strong></dt>
					                  <dd><%= registroAsistente.getTipoRegistro()%></dd>
					                  <dt><strong>Costo:</strong></dt>
					                  <dd><%= registroAsistente.getCosto() %></dd>
					                  <dt><strong>Fecha de Registro:</strong></dt>
					                  <dd><%= registroAsistente.getFecha() %></dd>
					                </dl>
					              </fieldset>
					              
					              <a href="<%= ctx %>/mobile/consultaRegistro?edicion=<%=ed.getNombre() %>" class="btn">Consultar Registro</a>
							        </section>
										<%
										}
										%>
					</main>
				 </div>			 
				<div style="display:flex; flex-direction:column; align-items:center;">
				<%
				    DataEvento ev = (DataEvento) request.getAttribute("evento");
				    if (ev != null) {
				        String nombreEvento = ev.getNombre();
				        String imagenEvento = (ev.getImagen() != null && !ev.getImagen().isEmpty())
				                              ? ev.getImagen()
				                              : "EventoSinFoto.png";
				%>
				    <div style="margin-bottom:12px; text-align:center;">
				        <a href="<%= request.getContextPath() %>/mobile/ConsultaEvento?id=<%= nombreEvento %>">
				            <img src="<%= request.getContextPath() %>/MediaServlet?name=<%= imagenEvento %>" 
				                 alt="Consultar Evento"
				                 style="max-width:150px; width:100%; cursor:pointer; border-radius:8px; 
				                        box-shadow:0 2px 6px rgba(0,0,0,0.2);">
				        </a>
				        <div style="margin-top:4px; font-size:0.9rem; color:#555;">
				            <%= ev.getNombre() %>
				        </div>
				    </div>
				<%
				    } else {
				%>
				    <p class="alerta-error">No hay evento disponible.</p>
				<%
				    }
				%>
				<% } 
				} else { %>
			  	<p class="alerta-error">No se encontró la edición solicitada.</p>
				<% } %>
				</div>

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