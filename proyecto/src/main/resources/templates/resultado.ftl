<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido ${usuario.nombre} - Panel de Usuario</title>
    <link rel="stylesheet" href="/estilos.css"> <!-- si tienes CSS -->
</head>
<body>
    <div class="container">
        <h1>¡Hola, ${usuario.nombre}!</h1>
        <p>Correo electrónico: <strong>${usuario.email}</strong></p>

        <h2>Tus productos</h2>

        <#if productos?size == 0>
            <p>No tienes productos registrados.</p>
        <#else>
            <ul>
                <#list productos as producto>
                    <li>
                        <strong>${producto.nombre}</strong> - ${producto.precio} €
                    </li>
                </#list>
            </ul>
        </#if>

        <a href="/">Cerrar sesión</a>
    </div>
</body>
</html>
