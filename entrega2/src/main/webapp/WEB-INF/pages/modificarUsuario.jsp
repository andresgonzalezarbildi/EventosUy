<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.datatypes.DataUsuario" %>

<%
    DataUsuario usuario = (DataUsuario) request.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/UsuarioServlet?op=listar");
        return;
    }

    String ctx = request.getContextPath();
    String imagen = (usuario.getImagen() != null && !usuario.getImagen().isEmpty()) 
                     ? usuario.getImagen() 
                     : "default.jpg";
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Modificar Usuario</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/consultaUsuario.css">
  <style>
      body { background-color: white; }
      .user-image {
          text-align: center;
          margin-top: 20px;
      }
      .user-image img {
          width: 200px;
          height: 200px;
          object-fit: cover;
          border-radius: 50%;
          border: 2px solid #ccc;
      }
      .user-image h4 {
          margin-top: 10px;
          font-weight: normal;
      }
  </style>
</head>
<body>
  <jsp:include page="header.jsp" />

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <!-- Sidebar -->
        <jsp:include page="sidebar.jsp" />

        <!-- Contenido principal + imagen usuario -->
        <div class="col-12 col-md-8 d-flex">
          <!-- Formulario a la izquierda -->
          <div class="flex-grow-1">
            <h2 class="mb-3">Modificar Usuario</h2>
			<% if (request.getAttribute("errorPassword") != null) { %>
			  <div class="alert alert-danger" role="alert">
			    <%= request.getAttribute("errorPassword") %>
			  </div>
			<% } %>

            <% if ("Organizador".equalsIgnoreCase(usuario.getTipo())) { %>
              <form action="<%= ctx %>/UsuarioServlet?op=modificar" method="post" enctype="multipart/form-data">
              <input type="hidden" name="tipo" value="<%= usuario.getTipo() %>">
              
                <input type="hidden" name="nick" value="<%= usuario.getNickname() %>">
                <label>Nombre:</label>
                <input type="text" name="nombre" class="form-control mb-2" value="<%= usuario.getNombre() %>">
                <label>Descripción:</label>
                <textarea name="descripcion" class="form-control mb-2"><%= usuario.getDescripcion() != null ? usuario.getDescripcion() : "" %></textarea>
                <label>Link:</label>
                <input type="url" name="link" class="form-control mb-2" value="<%= usuario.getLink() != null ? usuario.getLink() : "" %>">
                <label>Imagen:</label>
                <input type="file" name="imagen" accept="image/*" class="form-control mb-2">
                <label>Nueva contraseña:</label>
                <input type="password" name="newPassword" class="form-control mb-2">
                <label>Confirmar contraseña:</label>
                <input type="password" name="confirmPassword" class="form-control mb-2">
                <span class="errorPass" style="color: red; display: none;">
				  Las contraseñas no coinciden
				</span>
                <div class="mt-3 d-flex gap-2">
                  <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  <button type="reset" class="btn btn-secondary">Cancelar</button>
                </div>
              </form>
            <% } else { %>
              <form action="<%= ctx %>/UsuarioServlet?op=modificar" method="post" enctype="multipart/form-data">
              <input type="hidden" name="tipo" value="<%= usuario.getTipo() %>">
              
                <input type="hidden" name="nick" value="<%= usuario.getNickname() %>">
                <label>Nombre:</label>
                <input type="text" name="nombre" class="form-control mb-2" value="<%= usuario.getNombre() %>">
                <label>Apellido:</label>
                <input type="text" name="apellido" class="form-control mb-2" value="<%= usuario.getApellido() %>">
                <label>Fecha de Nacimiento:</label>
                <input type="date" name="fechaNac" class="form-control mb-2" value="<%= usuario.getFechaNacimiento() %>">
                <label>Imagen:</label>
                <input type="file" name="imagen" accept="image/*" class="form-control mb-2">
                <label>Nueva contraseña:</label>
                <input type="password" name="newPassword" class="form-control mb-2">
                <label>Confirmar contraseña:</label>
                <input type="password" name="confirmPassword" class="form-control mb-2">
                <span class="errorPass" style="color: red; display: none;">
				  Las contraseñas no coinciden
				</span>
                <div class="mt-3 d-flex gap-2">
                  <button type="submit" class="btn btn-primary">Guardar cambios</button>
                  <button type="reset" class="btn btn-secondary">Cancelar</button>
                </div>
                
              </form>
            <% } %>
           
			
			<script>
			  const forms = document.querySelectorAll('form');
			  forms.forEach(form => {
			    const pass1 = form.querySelector('input[name="newPassword"]');
			    const pass2 = form.querySelector('input[name="confirmPassword"]');
			    const error = form.querySelector('.errorPass');

			
			    if (pass1 && pass2) {
			      form.addEventListener('submit', (e) => {
			        if (pass1.value !== pass2.value) {
			          e.preventDefault();
			          error.style.display = 'inline';
			          pass2.focus();
			        } else {
			          error.style.display = 'none';
			        }
			      });
			    }
			  });
			</script>
			            
          </div>

          <!-- Imagen a la derecha -->
         <div class="user-image ms-4 text-center">
		  <a href="<%= ctx %>/UsuarioServlet?op=consultar&nick=<%= usuario.getNickname() %>">
		    <img src="<%= request.getContextPath() + "/img/" + (usuario.getImagen() != null ? usuario.getImagen() : "PerfilSinFoto.jpg") %>" 
		         alt="Foto Usuario" style="width:150px; height:150px; border-radius:8px; object-fit:cover; margin-bottom:0.5rem;">
		  </a>
		  <h4><%= usuario.getNickname() %></h4>
		</div>

        </div>
      </div>
    </div>
  </section>


  <jsp:include page="footer.jsp" />
 
</body>
</html>
