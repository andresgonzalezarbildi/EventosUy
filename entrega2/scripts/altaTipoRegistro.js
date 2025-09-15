// altaTipoRegistro.js

const formTipoRegistro = document.getElementById("formTipoRegistro");
const selectEdicion = document.getElementById("edicion");

// cargar ediciones de mock.js (simulamos solo las que organiza el usuario)
ediciones.forEach(ed => {
  const option = document.createElement("option");
  option.value = ed.id;
  option.textContent = `${ed.nombre} - ${ed.ciudad} (${ed.fechaInicio})`;
  selectEdicion.appendChild(option);
});

formTipoRegistro.addEventListener("submit", (e) => {
  e.preventDefault();

  const nuevaEntrada = {
    edicionId: parseInt(selectEdicion.value),
    nombre: document.getElementById("nombre").value,
    descripcion: document.getElementById("descripcion").value,
    costo: parseFloat(document.getElementById("costo").value),
    cupo: parseInt(document.getElementById("cupo").value)
  };

  // Guardamos en mock
  tiposRegistro.push(nuevaEntrada);

  alert("Tipo de registro agregado:\n" + JSON.stringify(nuevaEntrada, null, 2));

  formTipoRegistro.reset();
});
