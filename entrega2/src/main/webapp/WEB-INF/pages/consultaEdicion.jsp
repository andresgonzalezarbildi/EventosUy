<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.datatypes.DataEdicion" %>
<%@ page import="logica.datatypes.DataTipoRegistro" %>
<%@ page import="logica.datatypes.DataRegistro" %>
<%@ page import="java.util.List" %>
<%@ page import="logica.datatypes.DataEvento" %>


<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Detalle de Edición</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">

  <link rel="stylesheet" href="<%=request.getContextPath()%>/estilos/base.css">
</head>


<%
    DataEdicion ed = (DataEdicion) request.getAttribute("edicion");
	DataEvento evento = (DataEvento) request.getAttribute("evento");
	DataRegistro registroAsistente = (DataRegistro) request.getAttribute("registroAsistente");
    String rol = (String) request.getAttribute("rol");
    String nickname = (String) request.getAttribute("nickname");
%>


<body>
 <jsp:include page="header.jsp" />

  <!-- Main + Sidebar -->
  <section class="content">
    <div class="container-fluid">
      <div class="row">
      
      <jsp:include page="sidebar.jsp" />

        
        
         <div class="col-12 col-md-8 d-flex">
      <!-- Columna izquierda: imagen y datos -->
      <div class="flex-grow-1 d-flex flex-column align-items-center">
        <main
          style="max-width: 700px; margin: 2rem auto; padding: 1.5rem; background: #fff; border: 1px solid var(--color-border); border-radius: var(--radius); box-shadow: 0 2px 6px rgba(0,0,0,0.1);">
          <h2 style="margin-bottom: 1rem; color: var(--color-primary); text-align:center;">Detalle de Edición
          </h2>
		
		
		<% if (ed != null) { %>
			<% if (ed.getImagen() != null && !ed.getImagen().isEmpty()) { %>
			  <img src="<%= request.getContextPath() %>/img/<%= ed.getImagen() %>" 
			       alt="Imagen de la edición" style="max-width:300px;border-radius:8px;">
			<% } %>
			<% } else { %>
		  <p style="text-align:center; color:red;">No se encontró la edición solicitada.</p>
			<% } %>
          <dl
            style="display:grid; grid-template-columns: 150px 1fr; row-gap:0.5rem; column-gap:1rem; margin-bottom:2rem;">
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
          
         
         
          <section style="margin-bottom:2rem;">
		    <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Tipos de Registro</h3>
		    <div style="display:flex; gap:0.5rem; flex-wrap:wrap;">
		        <%
 		            List<DataTipoRegistro> tipos = ed.getTiposRegistro();
		            if (tipos != null && !tipos.isEmpty()) {
		                for (DataTipoRegistro tipo : tipos) {
		        %>
		                    <a href="<%= request.getContextPath() %>/TipoRegistroServlet?op=consulta&id=<%= tipo.getNombre() %>&idEdicion=<%= ed.getNombre() %>"
		                       style="display:inline-block; background:#f0f0f0; padding:0.4rem 0.8rem;
		                              border-radius:12px; text-decoration:none; color:inherit;">
		                        <%= tipo.getNombre() %>
		                    </a>
		        <%
		                }
		            } else {
		        %>
		                <p>No hay tipos de registro disponibles para esta edición.</p>
		        <%
		            }
		        %>
		        		  
		    </div>
		     <% if ("organizador".equalsIgnoreCase(rol) 
		       && nickname != null 
		       && ed.getOrganizador() != null 
		       && ed.getOrganizador().equalsIgnoreCase(nickname)) { %>
		    <button onclick="window.location.href='<%= request.getContextPath() %>/TipoRegistroServlet?op=alta&idEdicion=<%= ed.getNombre() %>'">
		        Agregar Tipo de Registro
		    </button>
			<% } %>
		</section>

          <!-- Organizador -->
          <section style="margin-bottom:2rem;">
            <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Organizador</h3>
            <p><%= ed.getOrganizador() %></p>
          </section>

          <!-- Patrocinios -->
          <section style="margin-bottom:2rem;">
            <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Patrocinios</h3>
            <div style="display:flex; gap:0.5rem; flex-wrap:wrap;">
              <span style="background:#e3f2fd; padding:0.4rem 0.8rem; border-radius:12px;"><%= ed.getPatrocinios() %></span> <!-- PATROCINIOS VAN HARCODEADOS! -->
            </div>
         
         
			
			
			
			<!-- ORGANIZADOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR -->
			<%
			if ("organizador".equalsIgnoreCase(rol) && nickname != null && ed.getOrganizador() != null
		    && ed.getOrganizador().equalsIgnoreCase(nickname)) {
			    boolean organiza = ed.getOrganizador().equalsIgnoreCase(nickname);
			    if (organiza) {
			        DataRegistro[] registros = (DataRegistro[]) request.getAttribute("registrosEd");
			%>
		
			<section style="margin-bottom:2rem;">
		  <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Registros de la Edición</h3>
		  <div style="display:flex; flex-direction:column; gap:0.5rem; flex-wrap:wrap;">
		
		    <%
		    if (registros != null && registros.length > 0) {
		        for (DataRegistro reg : registros) {
		    %>
		        <div style="background:#e3f2fd; padding:0.6rem 1rem; border-radius:12px;">
		            <p><b>Asistente:</b> <%= reg.getAsistente() %></p>
<%-- 		            <p><b>Tipo de Registro:</b> <%= reg.getTipoRegistro() %></p> --%>
<%-- 		            <p><b>Fecha:</b> <%= reg.getFechaRegistro() %></p> --%>
		        </div>    

		    <%
		        }
		    } else {
		    %>
		        <p>No hay registros para esta edición.</p>
		    <%
		    }
		    %>


				    
		  </div>
			</section>
				<%
				    } // TERMINA ORGANIZADOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR
			    //EMPIEZA ASISTENTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
			    
				} else if ("asistente".equalsIgnoreCase(rol) && (registroAsistente != null)) {
					
				%>
				        <section style="margin-bottom:2rem;">
				           <h3 style="margin-bottom:0.5rem; color: var(--color-primary);">Tu Registro:</h3>
			              <fieldset style="margin-top: 2rem; border: 1px solid #ccc; padding: 1rem; border-radius: 8px;">
			                <dl style="display:grid; grid-template-columns: 150px 1fr; row-gap:0.5rem; column-gap:1rem;">
<!-- 			               <dt><strong>Nombre:</strong></dt> -->
<%-- 			               <dd><%= reg.getAsistente() %></dd> --%>
			                  <dt><strong>Tipo de Registro:</strong></dt>
			                  <dd><%= registroAsistente.getTipoRegistro()%></dd>
			                  <dt><strong>Costo:</strong></dt>
			                  <dd><%= registroAsistente.getCosto() %></dd>
			                  <dt><strong>Fecha de Registro:</strong></dt>
			                  <dd><%= registroAsistente.getFecha() %></dd>
			                </dl>
			              </fieldset>
				        </section>
				<%
				} // TERMINA ASISTENTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
				%>
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
				        <a href="<%= request.getContextPath() %>/evento?op=consultar&id=<%= nombreEvento %>">
				            <img src="<%= request.getContextPath() %>/img/<%= imagenEvento %>" 
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
				    <p style="text-align:center; color:#777;">No hay evento disponible.</p>
				<%
				    }
				%>
				</div>


				

  

</div>
</div>
</div>
 </section>
  <jsp:include page="footer.jsp" />
</body>


</html>