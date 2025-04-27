<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subastas</title>
    <link rel="stylesheet" href="/static/subasta.css">
    <style>
       /* Reset básico */
       * {
         margin: 0;
         padding: 0;
         box-sizing: border-box;
       }

       /* Fondo general azul */
       body {
         font-family: 'Segoe UI', sans-serif;
         background: linear-gradient(to bottom, #3498db, #2980b9);
         color: #fff;
         min-height: 100vh;
       }

       /* Contenedor principal */
       .container {
         max-width: 1200px;
         margin: 40px auto;
         padding: 20px;
         background-color: rgba(255, 255, 255, 0.95);
         color: #2c3e50;
         border-radius: 20px;
         box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
       }

       /* Header con logo */
       header {
         text-align: center;
         margin-bottom: 40px;
       }

       header img {
         width: 120px;
         height: auto;
         margin-bottom: 10px;
       }

       header h1 {
         font-size: 2.8rem;
         color: #fff;
         text-shadow: 2px 2px #2c3e50;
       }

       /* Títulos */
       h2, h3 {
         color: #2c3e50;
       }

       /* Estilo para los elementos de subasta */
       .auction-item {
         background-color: #ecf0f1;
         display: flex;
         gap: 20px;
         align-items: center;
         padding: 20px;
         margin-bottom: 20px;
         border-radius: 15px;
         box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
         transition: transform 0.2s ease;
       }

       .auction-item:hover {
         transform: scale(1.01);
       }

       .auction-item img {
         width: 150px;
         height: 150px;
         object-fit: cover;
         border-radius: 10px;
         border: 2px solid #3498db;
       }

       /* Estilo para los botones */
       .button {
         display: inline-block;
         padding: 10px 20px;
         background-color: #3498db;
         color: white;
         border: none;
         border-radius: 6px;
         cursor: pointer;
         transition: background-color 0.3s ease;
         margin-top: 10px;
       }

       .button:hover {
         background-color: #2ecc71;
       }

       /* Estilo para la historia de las pujas */
       .history {
         margin-top: 50px;
         background: #fff;
         padding: 25px;
         border-radius: 10px;
         color: #2c3e50;
         box-shadow: 0 4px 8px rgba(0, 0, 0, 0.08);
       }

       .history h3 {
         margin-bottom: 15px;
         font-size: 1.3rem;
       }

       #history-list {
         list-style: disc inside;
         padding-left: 20px;
       }

       #history-list li {
         margin-bottom: 10px;
       }

    </style>
</head>
<body>

<header>
    <img src="/static/Imagenes/Image_3_abr_2025,_12_35_27.png" alt="Logo" style="width: 120px; display: block; margin: 20px auto;">
    <h1 style="text-align:center; color:white; text-shadow: 2px 2px #2c3e50;">Sistema de Subastas</h1>
    <div style="position: absolute; right: 20px; top: 20px;">
        <button class="button" onclick="window.location.href='/usuarios'">Mi Usuario</button>
        <button class="button" onclick="window.location.href='/mis-pujas'">Mis Pujas</button>
    </div>
</header>

<div class="container">
    <h2>Productos Disponibles para Pujar</h2>
    <div class="product-list">
        <#if productos?has_content>
            <#list productos as producto>
                <div class="auction-item">
                    <h3>${producto.nombre}</h3>
                    <p><strong>Descripción:</strong> ${producto.descripcion}</p>
                    <p><strong>Precio actual:</strong> ${producto.precio?string["0.##"]} €</p>
                    <form action="/api/pujar" method="post" onsubmit="return confirm('¿Estás seguro de que deseas pujar?')">
                        <input type="hidden" name="idProducto" value="${producto.id}">
                        <input type="number" name="cantidad" min="${producto.precio}" step="any" placeholder="Introduce tu puja" required>
                        <button type="submit" class="button">Pujar</button>
                    </form>
                </div>
            </#list>
        <#else>
            <p>No hay productos disponibles para pujar en este momento.</p>
        </#if>
    </div>
</div>

</body>
</html>
