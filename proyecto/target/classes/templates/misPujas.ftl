<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis Pujas</title>
    <link rel="stylesheet" href="/static/subasta.css">
</head>
<body>

<header>
    <img src="/static/Imagenes/Image_3_abr_2025,_12_35_27.png" alt="Logo" style="width: 120px;">
    <h1>Mis Pujas Actuales</h1>
    <button class="button" onclick="window.location.href='/subasta'">Volver a Subastas</button>
</header>

<div class="container">
    <h2>Productos que has pujado</h2>

    <#if productos?size == 0>
        <p>No tienes pujas activas en este momento.</p>
    <#else>
        <div class="product-list">
            <#list productos as producto>
                <div class="auction-item">
                    <h3>${producto.nombre}</h3>
                    <p><strong>Descripción:</strong> ${producto.descripcion}</p>
                    <p><strong>Tu puja:</strong> ${producto.precio?string["0.00"]} €</p>
                </div>
            </#list>
        </div>
    </#if>
</div>

</body>
</html>