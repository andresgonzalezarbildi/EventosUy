// altaEvento.js

const formEvento = document.getElementById("formEvento");
const categoriasSelect = document.getElementById("categorias");

// cargar categorías hardcodeadas desde mock.js
categorias.forEach(cat => {
  const option = document.createElement("option");
  option.value = cat;
  option.textContent = cat;
  categoriasSelect.appendChild(option);
});

formEvento.addEventListener("submit", (e) => {
  e.preventDefault();

  const seleccionadas = Array.from(categoriasSelect.selectedOptions).map(opt => opt.value);

  if (seleccionadas.length === 0) {
    alert("Debe seleccionar al menos una categoría");
    return;
  }

  const nuevoEvento = {
    nombre: document.getElementById("nombre").value,
    descripcion: document.getElementById("descripcion").value,
    sigla: document.getElementById("sigla").value,
    categorias: seleccionadas
  };

  // guardar en mock (solo simulación)
  eventos.push(nuevoEvento);

  alert("Evento registrado:\n" + JSON.stringify(nuevoEvento, null, 2));

  formEvento.reset();
});
