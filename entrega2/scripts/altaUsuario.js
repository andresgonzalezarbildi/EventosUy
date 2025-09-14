// altaUsuario.js
console.log("altaUsuario.js cargado correctamente");
const tipoSelect = document.getElementById('tipo');
const extraCampos = document.getElementById('extraCampos');
const formUsuario = document.getElementById('formUsuario');

// Mostrar campos dinámicos
tipoSelect.addEventListener('change', () => {
  const tipo = tipoSelect.value;
  extraCampos.innerHTML = '';

  if (tipo === 'organizador') {
    extraCampos.innerHTML = `
      <label for="descripcion">Descripción general:</label>
      <textarea id="descripcion" name="descripcion"></textarea>

      <label for="link">Link (opcional):</label>
      <input type="url" id="link" name="link">
    `;
  } else if (tipo === 'asistente') {
    // Campos que van en extraCampos
    extraCampos.innerHTML = `
      <label for="fechaNac">Fecha de Nacimiento:</label>
      <input type="date" id="fechaNac" name="fechaNac" required>

      <label for="institucion">Institución:</label>
      <select id="institucion" name="institucion" required>
        <option value="">Seleccione...</option>
        <option value="Universidad de la República">Universidad de la República</option>
        <option value="ORT">ORT</option>
        <option value="UTEC">UTEC</option>
        <option value="IPA">IPA</option>
        <option value="Otra">Otra</option>
      </select>
    `;

    // Insertar Apellido justo después de Nombre
    const campoNombre = document.getElementById('nombre');
    campoNombre.insertAdjacentHTML("afterend", `
      <label for="apellido">Apellido:</label>
      <input type="text" id="apellido" name="apellido" required>
    `);
  }
});

// Validar y capturar envío
formUsuario.addEventListener('submit', (e) => {
  e.preventDefault();

  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirmPassword').value;

  if (password !== confirmPassword) {
    alert("Las contraseñas no coinciden");
    return;
  }

  const nuevoUsuario = {
    nick: document.getElementById('nick').value,
    nombre: document.getElementById('nombre').value,
    correo: document.getElementById('correo').value,
    tipo: tipoSelect.value,
    password: password
  };

  // Guardar en mock
  usuarios.push(nuevoUsuario);

  alert("Usuario registrado con éxito:\n" + JSON.stringify(nuevoUsuario, null, 2));

  formUsuario.reset();
  extraCampos.innerHTML = '';
});
