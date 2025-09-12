// mock.js
console.log("mock.js cargado correctamente");
// Usuarios de prueba
const usuarios = [
  { nick: "juan23", nombre: "Juan Pérez", correo: "juan@example.com", tipo: "Organizador" },
  { nick: "maria12", nombre: "María Gómez", correo: "maria@example.com", tipo: "Asistente" }
];

// mock.js
const categorias = ["Música", "Deportes", "Conferencias", "Cine"];

// Eventos de prueba
const eventos = [
  { id: 1, nombre: "Maratón Montevideo" },
  { id: 2, nombre: "Montevideo Comic" },
  { id: 3, nombre: "Rock Festival" }
];
// Ediciones de prueba
const ediciones = [
  {
    id: 1,
    eventoId: 1,
    nombre: "Maratón 2024",
    ciudad: "Montevideo",
    fechaInicio: "2024-05-01",
    fechaFin: "2024-05-05",
    estado: "Aprobada",
    organizador: "juan23", // nick del organizador
    imagen: true,
    patrocinios: [{ nombre: "Nike", detalle: "Patrocinador oficial de la maratón" }],
    registros: [
      { usuario: "maria12", tipo: "General" },
      { usuario: "lucas88", tipo: "VIP" }
    ]
  },
  {
    id: 2,
    eventoId: 1,
    nombre: "Maratón 2025",
    ciudad: "Montevideo",
    fechaInicio: "2025-05-01",
    fechaFin: "2025-05-05",
    estado: "Ingresada",
    organizador: "juan23",
    imagen: false,
    patrocinios: [],
    registros: []
  },
  {
    id: 3,
    eventoId: 2,
    nombre: "Comic 2025",
    ciudad: "Salto",
    fechaInicio: "2025-01-10",
    fechaFin: "2025-01-12",
    estado: "Aprobada",
    organizador: "juan23",
    imagen: true,
    patrocinios: [{ nombre: "Plan Ceibal", detalle: "Apoya la innovación tecnológica" }],
    registros: [{ usuario: "maria12", tipo: "Estudiante" }]
  },
  {
    id: 4,
    eventoId: 3,
    nombre: "Rock 2024",
    ciudad: "Punta del Este",
    fechaInicio: "2024-11-15",
    fechaFin: "2024-11-17",
    estado: "Rechazada",
    organizador: "juan23",
    imagen: false,
    patrocinios: [],
    registros: []
  }
];

// =====================
// Tipos de registro
// =====================
const tiposRegistro = [
  { id: 1, edicionId: 1, nombre: "General", descripcion: "Participación estándar", costo: 500, cupo: 100 },
  { id: 2, edicionId: 1, nombre: "VIP", descripcion: "Incluye kit y merchandising exclusivo", costo: 1200, cupo: 20 },
  { id: 3, edicionId: 3, nombre: "Estudiante", descripcion: "Descuento para estudiantes con carné", costo: 300, cupo: 50 }
];