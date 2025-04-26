<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario - Tienda de Ropa</title>
</head>
<body>
    <h1>Bienvenido, ${usuario.nombre}!</h1>
    <p><strong>Email:</strong> ${usuario.email}</p>

    <h2>Tus productos:</h2>
    <ul>
        <#if productos?size > 0>
            <#list productos as producto>
                <li>${producto.nombre} - $${producto.precio}</li>
            </#list>
        <#else>
            <p>No tienes productos registrados.</p>
        </#if>
    </ul>

    <a href="/logout">Cerrar sesión</a>
</body>
</html>