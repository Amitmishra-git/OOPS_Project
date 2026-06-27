@ECHO OFF
SETLOCAL
SET "BASE_DIR=%~dp0"
IF EXIST "%BASE_DIR%\.mvn\wrapper\maven-wrapper.properties" (
  powershell -ExecutionPolicy Bypass -File "%BASE_DIR%mvnw.ps1" %*
) ELSE (
  echo Maven wrapper configuration is missing.
  exit /b 1
)