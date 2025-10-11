@echo off
setlocal

echo ========================================
echo    REBUILD LIBRERIA EVENTOS UY
echo ========================================

REM 1) Ir a la carpeta de la librería
cd /d "%~dp0libEventosUy" || (echo No existe libEventosUy & pause & exit /b 1)

REM 2) Limpiar y recrear bin
if exist bin rd /s /q bin
mkdir bin

REM 3) Listar TODAS las fuentes .java recursivamente
dir /s /b src\*.java > sources.txt

REM 4) Compilar todo a bin (preserva paquetes desde src)
echo Compilando...
javac -encoding UTF-8 -d bin -cp "src" @sources.txt
if %errorlevel% neq 0 (
  echo ERROR: Fallo la compilacion
  pause
  exit /b 1
)

REM 5) Crear el JAR solo con lo compilado
echo Empaquetando JAR...
if exist logicaEventosUy.jar del /q logicaEventosUy.jar
jar -cf logicaEventosUy.jar -C bin .

REM 6) Copiar JAR a entrega2 y entrega1
echo Copiando JAR a entrega2...
copy /y logicaEventosUy.jar "..\entrega2\src\main\webapp\WEB-INF\lib\logicaEventosUy.jar" >nul
echo Copiando JAR a entrega1...
copy /y logicaEventosUy.jar "..\entrega1\lib\logicaEventosUy.jar" >nul

echo.
echo ===== REBUILD OK =====
echo - Refresca en Eclipse (F5)
echo - Project > Clean
echo - Servers: Clean, Publish, Restart
echo.
pause
endlocal

