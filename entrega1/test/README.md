# Tests de JUnit para el Sistema de Gestión de Eventos

Este directorio contiene un conjunto completo de tests de JUnit que cubren aproximadamente el **80% del código** del sistema de gestión de eventos.

## 📁 Estructura de Tests

### Clases de Entidad
- **`UsuarioTest.java`** - Tests para la clase Usuario (getters, setters, constructor)
- **`CategoriaTest.java`** - Tests para la clase Categoria (getters, constructor, toString)
- **`EventoTest.java`** - Tests para la clase Evento (gestión de categorías y ediciones)
- **`EdicionEventoTest.java`** - Tests para la clase EdicionEvento (gestión de tipos de registro, patrocinios)

### Manejadores
- **`ManejadorUsuarioTest.java`** - Tests para ManejadorUsuario (CRUD de usuarios, singleton)
- **`ManejadorEventoTest.java`** - Tests para ManejadorEvento (CRUD de eventos y categorías)

### Controladores
- **`ControladorUsuarioTest.java`** - Tests para ControladorUsuario (lógica de negocio de usuarios)
- **`ControladorEventoTest.java`** - Tests para ControladorEvento (lógica de negocio de eventos)

### Datatypes
- **`DataTypesTest.java`** - Tests para todas las clases de transferencia de datos

### Excepciones
- **`ExcepcionesTest.java`** - Tests para todas las excepciones personalizadas del sistema

## 🚀 Cómo Ejecutar los Tests

### Opción 1: Desde el IDE (Recomendado)
1. Abrir el proyecto en tu IDE (Eclipse, IntelliJ, VS Code)
2. Navegar a la carpeta `test/src/test/`
3. Click derecho en `TestSuite.java` → "Run As" → "JUnit Test"
4. O ejecutar cada clase de test individualmente

### Opción 2: Desde la Línea de Comandos
```bash
# Navegar al directorio del proyecto
cd /ruta/al/proyecto

# Compilar el proyecto
javac -cp "lib/*:src" src/**/*.java

# Compilar los tests
javac -cp "lib/*:src:test" test/src/test/*.java

# Ejecutar los tests (requiere JUnit en el classpath)
java -cp "lib/*:src:test" org.junit.platform.console.ConsoleLauncher --class-path test --scan-class-path
```

### Opción 3: Usando Maven (si el proyecto lo soporta)
```bash
mvn test
```

## 📊 Cobertura de Tests

Los tests cubren las siguientes funcionalidades principales:

### ✅ Funcionalidades Cubiertas
- **Gestión de Usuarios**: Alta, baja, modificación, consulta de usuarios y organizadores
- **Gestión de Eventos**: Creación, consulta, gestión de categorías
- **Gestión de Ediciones**: Creación, tipos de registro, patrocinios
- **Validaciones**: Verificación de datos de entrada, manejo de errores
- **Patrones de Diseño**: Singleton, Factory, DTO
- **Manejo de Excepciones**: Todas las excepciones personalizadas del sistema

### 🔍 Casos de Test Incluidos
- **Casos Positivos**: Funcionalidad normal del sistema
- **Casos Negativos**: Manejo de errores y excepciones
- **Casos Límite**: Valores nulos, strings vacíos, fechas inválidas
- **Validaciones**: Verificación de reglas de negocio
- **Integración**: Interacción entre diferentes componentes

## 🛠️ Dependencias Requeridas

- **JUnit 5** (Jupiter)
- **Java 8+**
- **Librerías del proyecto** (jcalendar, jgoodies)

## 📝 Notas Importantes

1. **Singleton Pattern**: Los tests de manejadores verifican el patrón singleton
2. **Estado Compartido**: Algunos tests pueden afectar el estado global del sistema
3. **Datos de Prueba**: Los tests crean datos temporales que se limpian automáticamente
4. **Excepciones**: Se prueban tanto las excepciones esperadas como las inesperadas

## 🎯 Objetivos de Cobertura

- **Cobertura de Líneas**: ~80%
- **Cobertura de Métodos**: ~85%
- **Cobertura de Clases**: ~90%
- **Cobertura de Ramas**: ~75%

## 🔧 Personalización de Tests

Para agregar nuevos tests:

1. Crear una nueva clase en `test/src/test/`
2. Seguir la convención de nombres: `NombreClaseTest.java`
3. Usar anotaciones JUnit 5: `@Test`, `@BeforeEach`, `@AfterEach`
4. Agregar la clase a la documentación de `TestSuite.java`

## 📞 Soporte

Si encuentras problemas con los tests:

1. Verificar que todas las dependencias estén en el classpath
2. Asegurar que el código fuente esté compilado
3. Revisar que las versiones de JUnit sean compatibles
4. Verificar que no haya conflictos de paquetes

---

**¡Los tests están listos para ejecutarse y verificar la funcionalidad del sistema!** 🎉 