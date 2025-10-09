@echo off
echo ========================================
echo    REBUILD LIBRERIA EVENTOS UY
echo ========================================

echo.
echo [1/4] Cambiando al directorio de la libreria...
cd /d "%~dp0libEventosUy"

echo [2/4] Recompilando codigo Java...
javac -d bin -cp "src" src/logica/CargaDatos/CargaDatos.java src/logica/clases/*.java src/logica/controladores/*.java src/logica/datatypes/*.java src/logica/interfaces/*.java src/logica/manejadores/*.java src/excepciones/*.java

if %errorlevel% neq 0 (
    echo ERROR: Fallo la compilacion
    pause
    exit /b 1
)

echo [3/4] Creando nuevo JAR...
jar -cf logicaEventosUy.jar -C bin .

echo [4/4] Copiando JAR al proyecto web...
copy logicaEventosUy.jar "..\entrega2\src\main\webapp\WEB-INF\lib\logicaEventosUy.jar"

if %errorlevel% neq 0 (
    echo ERROR: Fallo la copia del JAR
    pause
    exit /b 1
)

echo.
echo ========================================
echo    REBUILD COMPLETADO EXITOSAMENTE
echo ========================================
echo.
echo Ahora debes:
echo 1. Reiniciar tu servidor web (Tomcat)
echo 2. Ejecutar "Cargar Datos" en la aplicacion
echo.
pause
