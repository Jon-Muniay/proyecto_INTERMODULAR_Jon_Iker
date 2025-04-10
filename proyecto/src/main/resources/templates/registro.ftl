<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registrarse - Tienda de Ropa</title>
  <link rel="stylesheet" href="/estilos.css">
  <style>
    * {
margin: 0;
padding: 0;
box-sizing: border-box;
font-family: 'Arial', sans-serif;
}

    body {
background-color: #ecf3f9;
display: flex;
justify-content: center;
align-items: center;
height: 100vh;
}

.container {
display: flex;
justify-content: center;
align-items: center;
width: 100%;
}

.register-box {
background-color: #ffffff;
padding: 40px;
border-radius: 10px;
box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
width: 100%;
max-width: 400px;
text-align: center;
}

.header {
margin-bottom: 30px;
}

.header h1 {
font-size: 28px;
color: #2c3e50;
margin: 0;
}

.input-group {
margin-bottom: 20px;
text-align: left;
}

.input-group label {
display: block;
margin-bottom: 5px;
color: #34495e;
font-size: 14px;
}

.input-group input {
width: 100%;
padding: 10px;
border: 1px solid #ccc;
border-radius: 5px;
font-size: 16px;
}

.input-group input:focus {
border-color: #3498db;
outline: none;
}

.error-message {
color: #e74c3c;
font-size: 13px;
margin-top: 5px;
}

.btn-register {
width: 100%;
padding: 12px;
background-color: #3498db;
color: white;
font-size: 16px;
border: none;
border-radius: 5px;
cursor: pointer;
transition: background-color 0.3s ease;
}

.btn-register:hover {
background-color: #2980b9;
}

.login-link {
margin-top: 20px;
font-size: 14px;
color: #7f8c8d;
}

.login-link a {
color: #3498db;
text-decoration: none;
font-weight: bold;
}

.login-link a:hover {
text-decoration: underline;
}
</style>
</head>
<body>
<div class="container">
    <div class="register-box">
      <div class="header">
        <h1>Crear cuenta</h1>
      </div>

     <form id="formRegistro" action="/registro" method="POST" novalidate>
    <div class="input-group">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" placeholder="Ingresa tu nombre" required>
    </div>
    <div class="input-group">
        <label for="email">Correo Electrónico</label>
        <input type="email" id="email" name="email" placeholder="Ingresa tu correo" required>
    </div>
    <div class="input-group">
        <label for="password">Contraseña</label>
        <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
    </div>
    <button type="submit" class="btn-register">Registrarse</button>
</form>

      <div class="login-link">
        ¿Ya tienes cuenta? <a href="/">Inicia sesión aquí</a>
      </div>
    </div>
  </div>

  <script>
    const form = document.getElementById('formRegistro');

    form.addEventListener('submit', function (e) {
let valido = true;

document.getElementById('error-nombre').textContent = '';
document.getElementById('error-email').textContent = '';
document.getElementById('error-password').textContent = '';

const nombre = document.getElementById('nombre').value.trim();
const email = document.getElementById('email').value.trim();
const password = document.getElementById('password').value.trim();

if (nombre.length < 3) {
document.getElementById('error-nombre').textContent = 'El nombre debe tener al menos 3 caracteres.';
valido = false;
}

      const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
      if (!emailRegex.test(email)) {
document.getElementById('error-email').textContent = 'Ingresa un correo válido.';
valido = false;
}

      if (password.length < 6) {
document.getElementById('error-password').textContent = 'La contraseña debe tener al menos 6 caracteres.';
valido = false;
}

      if (!valido) {
e.preventDefault();
}
    });
  </script>
</body>
</html>
