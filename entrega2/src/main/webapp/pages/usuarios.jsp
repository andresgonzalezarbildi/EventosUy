<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="logica.datatypes.DataUsuario" %>
<%
  String ctx = request.getContextPath();

	DataUsuario[] usuarios = null;
  try {
      Object usrs = request.getAttribute("usuarios");
      if (usrs != null) {
    	  usuarios = (DataUsuario[]) usrs;
      }
  } catch (Exception ignore) { }
%>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>Usuarios</title>
  <link rel="stylesheet" href="<%= ctx %>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%= ctx %>/estilos/base.css">
</head>

<body>
  <!-- Header -->
  <jsp:include page="../includes/header.jsp"/>

  <section class="container-fluid content">
    <div class="row content-row">

      <!-- Sidebar -->
		<jsp:include page="../includes/sidebar.jsp"/>

      <!-- Contenedor principal -->
      <%= "usuarios: " + (usuarios==null ? "null" : usuarios.length) %>
      <main style="max-width: 600px; margin: 2rem auto; padding: 1rem; background: #fff; border: 1px solid var(--color-border); border-radius: var(--radius);">
        <%
              if (usuarios == null || usuarios.length == 0) {
            %>
              <!-- Sin eventos: placeholder -->
              <h1>No hay Usuarios registrados, quieres ser mi primer amigo? jeje</h1>
            <%
              } else {
           	%>
           	<h2 style="margin-bottom: 1rem; color: var(--color-primary);">Consulta Usuario</h2>
           	<div class="usuarios">
		    	<ul style="list-style: none; width: 500px;" >
		    <%
                for (DataUsuario e : usuarios) {
                  String nickname = "";
                  String imagen = "PerfilSinFoto.jpg";

                  try { if (e.getNickname() != null) nickname = e.getNickname(); } catch (Exception ignore) {}
                  try { if (e.getImagen() != null) imagen = e.getImagen(); } catch (Exception ignore) {}

                  String detalleUrl = ctx + "/pages/consultaUsuario.jsp"
                                    + ( true ? ("?id=" + e.getNombre()) : "");
            %>
				
              <li><a href="<%= detalleUrl %>"><img src="<%= ctx %>/img/<%=imagen%>" alt="foto usuario" style="width: 30px; height: 30px;"><%=nickname%></a></li>

            <%
                } // fin for
            %>
 		        </ul>
	          </div>
	        <%
              } // fin else
            %>
      </main>
    </div>
  </section>
	<jsp:include page="../includes/footer.jsp"/>
</body>

</html>