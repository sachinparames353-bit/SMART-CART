@echo off
cd /d "%~dp0mobile_web"
echo ========================================================
echo   SUPERMARKET SMART BILLING MOBILE APP
echo ========================================================
echo.
echo Starting mobile web server...
echo.
echo On this PC:          http://localhost:8080
echo On your Mobile Phone: http://172.16.120.223:8080
echo.
echo Press Ctrl+C in this window to stop the server.
echo ========================================================
python -m http.server 8080
pause
