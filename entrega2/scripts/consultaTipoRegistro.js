// consultaTipoRegistro.js

const eventoSelect = document.getElementById("evento");
const edicionSelect = document.getElementById("edicion");
const tipoSelect = document.getElementById("tipo");

const detalleNombre = document.getElementById("detalleNombre");
const detalleDescripcion = document.getElementById("detalleDescripcion");
const detalleCosto = document.getElementById("detalleCosto");
const detalleCupo = document.getElementById("detalleCupo");

// 1. Cargar eventos
eventos.forEach(ev => {
  const option = document.createElement("option");
  option.value = ev.id;
  option.textContent = ev.nombre;
  eventoSelect.appendChild(option);
});

// 2. Cuando elige evento, mostrar ediciones aprobadas
eventoSelect.addEventListener("change", () => {
  edicionSelect.innerHTML = '<option value="">Seleccione una edición</option>';
  tipoSelect.innerHTML = '<option value="">Seleccione un tipo de registro</option>';
  edicionSelect.disabled = true;
  tipoSelect.disabled = true;

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

// 3. Cuando elige edición aprobada, mostrar tipos de registro
edicionSelect.addEventListener("change", () => {
  tipoSelect.innerHTML = '<option value="">Seleccione un tipo de registro</option>';
  tipoSelect.disabled = true;

  if (edicionSelect.value) {
    const registros = tiposRegistro.filter(tr => tr.edicionId == edicionSelect.value);
    registros.forEach(tr => {
      const option = document.createElement("option");
      option.value = tr.id;
      option.textContent = tr.nombre;
      tipoSelect.appendChild(option);
    });
    if (registros.length > 0) tipoSelect.disabled = false;
  }
});

// 4. Cuando elige un tipo de registro, mostrar detalle
tipoSelect.addEventListener("change", () => {
  const tipo = tiposRegistro.find(tr => tr.id == tipoSelect.value);
  if (tipo) {
    detalleNombre.textContent = tipo.nombre;
    detalleDescripcion.textContent = tipo.descripcion;
    detalleCosto.textContent = `$${tipo.costo}`;
    detalleCupo.textContent = tipo.cupo;
  } else {
    detalleNombre.textContent = "";
    detalleDescripcion.textContent = "";
    detalleCosto.textContent = "";
    detalleCupo.textContent = "";
  }
});
