<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Iniciar Sesión - Tienda de Ropa</title>
  <style>
    * {
margin: 0;
padding: 0;
box-sizing: border-box;
font-family: 'Arial', sans-serif;
}

    body {
background-color: #f5f5f5;
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

.login-box {
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
text-align: center;
}

.logo {
width: 150px;
height: auto;
margin-bottom: 10px;
border-radius: 10px;
}

.header h1 {
font-size: 28px;
color: #333333;
margin: 0;
}

.input-group {
margin-bottom: 20px;
text-align: left;
}

.input-group label {
display: block;
margin-bottom: 5px;
color: #666666;
font-size: 14px;
}

.input-group input {
width: 100%;
padding: 10px;
border: 1px solid #cccccc;
border-radius: 5px;
font-size: 16px;
transition: border-color 0.3s ease;
}

.input-group input:focus {
border-color: #007BFF;
outline: none;
}

.btn-login {
width: 100%;
padding: 12px;
background-color: #007BFF;
color: white;
font-size: 16px;
border: none;
border-radius: 5px;
cursor: pointer;
transition: background-color 0.3s ease;
}

.btn-login:hover {
background-color: #0056b3;
}

.register-link {
margin-top: 20px;
font-size: 14px;
color: #666666;
}

.register-link a {
color: #007BFF;
text-decoration: none;
font-weight: bold;
}

.register-link a:hover {
text-decoration: underline;
}
</style>
</head>
<body>
<div class="container">
    <div class="login-box">
      <div class="header">
        <img src="Image 3 abr 2025, 12_35_27.png" alt="Logo de la Tienda" class="logo">
      </div>

      <form id="formLogin" action="/login" method="POST" novalidate>
    <div class="input-group">
        <label for="email">Correo Electrónico</label>
        <input type="email" id="email" name="email" placeholder="Ingresa tu correo" required>
    </div>
    <div class="input-group">
        <label for="password">Contraseña</label>
        <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>
    </div>
    <button type="submit" class="btn-login">Iniciar Sesión</button>
    <#if error??>
        <p class="error-message">${error}</p>
    </#if>
</form>

      <p class="register-link">¿No tienes una cuenta? <a href="registro.html">Regístrate aquí</a></p>
    </div>
  </div>

  <script>
    document.getElementById("loginForm").addEventListener("submit", function(e) {
const email = document.getElementById("email").value.trim();
const password = document.getElementById("password").value.trim();

if (!email || !password) {
e.preventDefault();
alert("Por favor, completa todos los campos antes de continuar.");
}
    });
  </script>
</body>
</html>
