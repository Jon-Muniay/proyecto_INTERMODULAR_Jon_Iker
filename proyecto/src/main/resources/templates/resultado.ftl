<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil de ${usuario.nombre}</title>
</head>
<body>
    <h1>Bienvenido, ${usuario.nombre}!</h1>
    <p>Tu correo electrónico es: ${usuario.email}</p>

    <h2>Tus Productos</h2>
    <ul>
    <#list productos as producto>
        <li>${producto.nombre} - ${producto.precio}€</li>
    </#list>
    </ul>

    <a href="/logout">Cerrar sesión</a>
</body>
</html>
