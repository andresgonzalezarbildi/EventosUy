<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Eventos</title>
  <link rel="stylesheet" href="estilos/normalize.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap-grid.min.css">

  <link rel="stylesheet" href="estilos/base.css">
  <link rel="stylesheet" href="estilos/index.css">

</head>

<body>
  <!-- Header -->
  <header class="navbar">
    <div class="container-fluid">
      <div class="row navbar-row text-center text-md-start">
        <!-- Logo -->
        <div class="col-12 col-md-3 mobile-col-center">
          <h1><a class="title" href="/entrega2/index.html">Eventos.uy</a></h1>
        </div>

        <!-- Search -->
        <div class="col-12 col-md-6">
          <div class="navbar_search mx-auto">
            <input type="text" placeholder="Eventos, ediciones..." class="navbar_search-input">
            <button class="navbar_search-btn">Buscar</button>
          </div>
        </div>

        <!-- SesiÃ³n -->
        <div class="col-12 col-md-3 mobile-col-center">
          <div class="navbar_sesion">
            <a href="/entrega2/pages/iniciarSesion.html" class="navbar_sesion_link">Iniciar Sesión</a>
            <p>/</p>
            <a href="/entrega2/pages/altaUsuario.html" class="navbar_sesion_link">Registrarse</a>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Main -->
  <section class="content">
    <div class="container-fluid">
      <div class="row">

        <!-- Sidebar -->
       <aside class="col-12 col-md-3">
          <!-- CategorÃ­as -->
          <div class="content-bar">
            <div class="content-bar-seccion">
              <div class="seccion-titulo">
                <h3>Categorías</h3>
              </div>
              <div class="content-bar-seccion-list">
                <div class="content-bar-seccion-list-options" onclick="window.location.href='/entrega2/index.html'">
                  <span>Tecnología</span>
                </div>
                <div class="content-bar-seccion-list-options" onclick="window.location.href='/entrega2/index.html'">
                  <span>Innovación</span>
                </div>
                <div class="content-bar-seccion-list-options" onclick="window.location.href='/entrega2/index.html'">
                  <span>Deporte</span>
                </div>
                <div class="content-bar-seccion-list-options" onclick="window.location.href='/entrega2/index.html'">
                  <span>Salud</span>
                </div>
              </div>
            </div>
          </div>
          <!-- Acciones -->
          <div class="content-bar">
            <div class="content-bar-seccion">
              <div class="seccion-titulo">
                <h3>Acciones</h3>
              </div>
              <div class="content-bar-seccion-list">
                <div class="content-bar-seccion-list-options"
                  onclick="window.location.href='/entrega2/pages/usuariosVisitante.html'">
                  <span>Usuario</span>
                </div>
                <div class="content-bar-seccion-list-options" onclick="window.location.href='/entrega2/index.html'">
                  <span>Institución</span>
                </div>
              </div>
            </div>
        </aside>

        <!-- Contenido principal -->
        <main class="col-12 col-md-9">

          <div class="contenido-principal">

            <div class="row contenido-principal-card"
              onclick="window.location.href='./pages/consultaEventoVisitante.html'">
              <div class="col-12 col-md-3 contenido-principal-card-imagen">
                <img src="img/IMG-EV04.png" alt="logo del evento">
              </div>

              <div class="col-12 col-md-9 contenido-principal-card-informacion col-12">
                <p class="contenido-principal-card-informacion-titulo">
                  Maraton Montevideo -
                </p>
                <p class="contenido-principal-card-informacion-descripcion">
                  Competencia deportiva anual en la capita
                </p>
              </div>

            </div>
            <div class="row contenido-principal-card"
              onclick="window.location.href='./pages/consultaEventoConfetecVisitante.html'">
              <div class="col-12 col-md-9 contenido-principal-card-informacion col-12">
                <p class="contenido-principal-card-informacion-titulo">
                  Conferencia de TecnologÃ­a -
                </p>
                <p class="contenido-principal-card-informacion-descripcion">
                  Evento sobre innovaciÃ³n tecnolÃ³gica
                </p>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  </section>
 


  <footer class="uc-footer">
    <small>Eventos.uy -- Grupo 42.</small>
  </footer>
</body>

</html>