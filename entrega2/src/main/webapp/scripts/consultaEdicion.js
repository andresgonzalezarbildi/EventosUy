// consultaEdicion.js
// Simular login (descomentar el que quieras probar)
//login("juan23");   // organizador
// login("maria12"); // asistente
// logout();        // visitante

const eventoSelect = document.getElementById("evento");
const edicionSelect = document.getElementById("edicion");

const detalleSection = document.getElementById("detalleEdicion");
const edicionNombre = document.getElementById("edicionNombre");
const edicionCiudad = document.getElementById("edicionCiudad");
const edicionFechas = document.getElementById("edicionFechas");
const edicionEstado = document.getElementById("edicionEstado");
const edicionOrganizador = document.getElementById("edicionOrganizador");
const edicionImagen = document.getElementById("edicionImagen");
const listaRegistros = document.getElementById("listaRegistros");
const listaPatrocinios = document.getElementById("listaPatrocinios");

const bloqueOrganizador = document.getElementById("bloqueOrganizador");
const listaRegistrosOrganizador = document.getElementById("listaRegistrosOrganizador");
const bloqueAsistente = document.getElementById("bloqueAsistente");
const detalleRegistroAsistente = document.getElementById("detalleRegistroAsistente");

// 1. Cargar eventos
eventos.forEach(ev => {
  const option = document.createElement("option");
  option.value = ev.id;
  option.textContent = ev.nombre;
  eventoSelect.appendChild(option);
});

// 2. Mostrar ediciones aprobadas al elegir evento
eventoSelect.addEventListener("change", () => {
  edicionSelect.innerHTML = '<option value="">Seleccione una edición</option>';
  edicionSelect.disabled = true;

  if (eventoSelect.value) {
    const aprobadas = ediciones.filter(ed => ed.eventoId == eventoSelect.value && ed.estado === "Aprobada");
    aprobadas.forEach(ed => {
      const option = document.createElement("option");
      option.value = ed.id;
      option.textContent = ed.nombre;
      edicionSelect.appendChild(option);
    });
    if (aprobadas.length > 0) edicionSelect.disabled = false;
  }
});

// 3. Mostrar detalle al elegir edición
edicionSelect.addEventListener("change", () => {
  const edicion = edicion.find(ed => ed.id == edicionSelect.value);
  if (!edicion) return;

  // Datos generales
  edicionNombre.textContent = edicion.nombre;
  edicionCiudad.textContent = edicion.ciudad;
  edicionFechas.textContent = `${edicion.fechaInicio} al ${edicion.fechaFin}`;
  edicionEstado.textContent = edicion.estado;
  edicionOrganizador.textContent = edicion.organizador;
  edicionImagen.textContent = edicion.imagen ? "Sí" : "No";

  // Tipos de registro
  listaRegistros.innerHTML = "";
  tiposRegistro.filter(tr => tr.edicionId == edicion.id).forEach(tr => {
    const li = document.createElement("li");
    li.textContent = `${tr.nombre} - $${tr.costo}`;
    listaRegistros.appendChild(li);
  });

  // Patrocinios
  listaPatrocinios.innerHTML = "";
  (edicion.patrocinios || []).forEach(pt => {
    const li = document.createElement("li");
    li.textContent = pt.nombre;
    listaPatrocinios.appendChild(li);
  });

  // 🔹 Diferencias según el usuario loguead
  const user = getUsuarioLogueado();
  bloqueOrganizador.style.display = "none";
  bloqueAsistente.style.display = "none";

  if (user) {
    if (user.tipo === "Organizador" && user.nick === edicion.organizador) {
      bloqueOrganizador.style.display = "block";
      listaRegistrosOrganizador.innerHTML = "";
      (edicion.registros || []).forEach(r => {
        const li = document.createElement("li");
        li.textContent = `${r.usuario} - ${r.tipo}`;
        listaRegistrosOrganizador.appendChild(li);
      });
    }
    if (user.tipo === "Asistente") {
      bloqueAsistente.style.display = "block";
      const miRegistro = (edicion.registros || []).find(r => r.usuario === user.nick);
      detalleRegistroAsistente.textContent = miRegistro ? `Estás inscripto en ${miRegistro.tipo}` : "No tenés registro en esta edición";
    }
  }

  detalleSection.style.display = "block";
});
