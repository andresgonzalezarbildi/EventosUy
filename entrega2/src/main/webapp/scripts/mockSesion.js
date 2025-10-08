// mockSesion.js
console.log("✅ mockSesion cargado");

// Estado actual de sesión
let usuarioLogueado = null;

// Simula login con un nick
function login(nick) {
  const user = usuarios.find(u => u.nick === nick);
  if (user) {
    usuarioLogueado = user;
    console.log("🔑 Usuario logueado:", usuarioLogueado);
    return true;
  } else {
    console.warn("❌ Usuario no encontrado:", nick);
    return false;
  }
}

// Simula logout
function logout() {
  console.log("👋 Cerrando sesión:", usuarioLogueado);
  usuarioLogueado = null;
}

// Devuelve usuario actual
function getUsuarioLogueado() {
  return usuarioLogueado;
}
