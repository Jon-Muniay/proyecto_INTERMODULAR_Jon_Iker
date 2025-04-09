<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrarse - Tienda de Ropa</title>
    <link rel="stylesheet" href="/estilos.css">
</head>
<body>
    <div class="container">
        <h1>Regístrate</h1>

        <form action="/registrar" method="POST">
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
            <button type="submit">Registrar</button>
        </form>

        <p>¿Ya tienes cuenta? <a href="/">Inicia sesión aquí</a></p>
    </div>
</body>
</html>
