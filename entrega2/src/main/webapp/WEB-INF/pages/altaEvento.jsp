<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String ctx = request.getContextPath();
  String msg = (String) request.getAttribute("mensaje");
  String err = (String) request.getAttribute("error");

  String formNombre = (String) request.getAttribute("form_nombre");
  String formDescripcion = (String) request.getAttribute("form_descripcion");
  String formSigla = (String) request.getAttribute("form_sigla");
  java.util.List<String> formCatsSel = (java.util.List<String>) request.getAttribute("form_categorias_sel");
  if (formCatsSel == null) formCatsSel = java.util.Collections.emptyList();

  java.util.List<String> categorias = (java.util.List<String>) request.getAttribute("categorias");
  if (categorias == null) categorias = java.util.Collections.emptyList();
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Alta Usuario</title>
  <link rel="stylesheet" href="<%=ctx%>/estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="<%=ctx%>/estilos/main.css">
  <link rel="stylesheet" href="<%=ctx%>/estilos/base.css">
</head>
<body>
  <jsp:include page="header.jsp" />

  <section class="content">
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="sidebar.jsp" />

        <main style="max-width:600px;margin:2rem auto;padding:1rem;background:#fff;border:1px solid var(--color-border);border-radius:var(--radius);">
          <h2 style="margin-bottom:1rem;color:var(--color-primary);">Alta de Evento</h2>

          <% if ((err != null && !err.isEmpty()) || (msg != null && !msg.isEmpty())) { %>
		  <div style="color:red;margin-bottom:1rem;">
		    <%= err != null ? err : "" %>
		    <%= msg != null ? msg : "" %>
		  </div>
		<% } %>

          <form action="<%= ctx %>/evento/alta" method="POST" enctype="multipart/form-data" style="display:flex; flex-direction:column; gap:1rem;">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required value="<%= formNombre != null ? formNombre : "" %>">

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required><%= formDescripcion != null ? formDescripcion : "" %></textarea>

            <label for="sigla">Sigla:</label>
            <input type="text" id="sigla" name="sigla" required value="<%= formSigla != null ? formSigla : "" %>">

            <label for="categorias">Categorías (podés seleccionar varias):</label>
            <select id="categorias" name="categorias" multiple required size="<%= Math.max(4, categorias.size()) %>">
              <%
                if (categorias.isEmpty()) {
              %>
                <option disabled>(No hay categorías disponibles)</option>
              <%
                } else {
                  for (String cat : categorias) {
                    boolean selected = formCatsSel.contains(cat);
              %>
                <option value="<%= cat %>" <%= selected ? "selected" : "" %>><%= cat %></option>
              <%
                  }
                }
              %>
            </select>

            <label for="imagen">Imagen (opcional):</label>
            <input type="file" id="imagen" name="imagen" accept="image/*">

            <div style="margin-top:1rem;display:flex;gap:1rem;">
              <button type="submit" class="btn btn-primary">Aceptar</button>
              <button type="reset" class="btn btn-secondary">Cancelar</button>
            </div>
          </form>
        </main>
      </div>
    </div>
  </section>

  <jsp:include page="footer.jsp" />
</body>
</html>
