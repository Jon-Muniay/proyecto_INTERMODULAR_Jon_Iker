<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario</title>
    <link rel="stylesheet" href="/static/usuarios.css">
</head>
<style>body {
           font-family: Arial, sans-serif;
           margin: 0;
           padding: 0;
           background-color: #f4f4f4;
       }

       .container {
           width: 80%;
           margin: 0 auto;
           padding: 20px;
           background-color: #ffffff;
           border-radius: 8px;
           box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
       }

       header {
           text-align: center;
           background-color: #3498db;
           padding: 20px 0;
           border-radius: 8px;
           color: white;
       }

       header .logo {
           width: 100px;
           display: block;
           margin: 0 auto;
           max-width: 100%;
           height: auto;
       }

       header h1 {
           margin-top: 10px;
       }

       .user-info {
           display: flex;
           justify-content: space-between;
           margin-top: 20px;
           flex-wrap: wrap; /* Para hacerlo más responsive */
       }

       .user-info .user-photo {
           width: 120px;
           height: 120px;
           border-radius: 50%;
           border: 3px solid #3498db;
           object-fit: cover; /* Mantiene la proporción de la imagen */
       }

       .user-info .user-details {
           flex: 1;
           margin-left: 20px;
           margin-top: 10px;
       }

       .user-info h2 {
           font-size: 24px;
           color: #333;
       }

       .user-info p {
           font-size: 16px;
           color: #555;
       }

       .user-info .button {
           background-color: #3498db;
           color: white;
           border: none;
           padding: 10px 20px;
           border-radius: 5px;
           cursor: pointer;
           margin-right: 10px;
       }

       .user-info .button:hover {
           background-color: #2980b9;
       }

       /* Historial de compras */
       .purchase-history {
           margin-top: 30px;
       }

       .purchase-history h3 {
           font-size: 20px;
           color: #333;
       }

       .purchase-history table {
           width: 100%;
           border-collapse: collapse;
       }

       .purchase-history table th, .purchase-history table td {
           padding: 12px;
           text-align: left;
           border-bottom: 1px solid #ddd;
       }

       .purchase-history table th {
           background-color: #3498db;
           color: white;
       }

       .purchase-history table td {
           background-color: #f9f9f9;
       }

       .purchase-history table tr:hover {
           background-color: #f1f1f1;
       }

       /* Agregar media queries para mejorar la experiencia en móviles */
       @media (max-width: 768px) {
           .user-info {
               flex-direction: column;
               text-align: center;
           }

           .user-info .user-photo {
               width: 100px;
               height: 100px;
           }

           .user-info .user-details {
               margin-left: 0;
               margin-top: 15px;
           }

           .user-info h2 {
               font-size: 22px;
           }

           .purchase-history table th, .purchase-history table td {
               font-size: 14px;
           }

           .purchase-history h3 {
               font-size: 18px;
           }
       }
 </style>
<body>

    <div class="container">

        <header>
            <img src="/static/Imagenes/logo.png" alt="Logo de la tienda" class="logo">
            <h1>Perfil de Usuario</h1>

            <!-- Botones de navegación y botón de cerrar sesión -->
            <div class="header-buttons">
        <a href="/listaPujas">Ir a las Pujas</a>

            <!-- Enlace al menú principal -->
            <a href="/subasta">Ir a Subasta</a>
                <button class="button" onclick="confirmarCerrarSesion()">Cerrar sesión</button>
            </div>
        </header>

        <section class="user-info">
            <img src="/static/Imagenes/user-photo.jpg" alt="Foto de Perfil" class="user-photo">
            <div class="user-details">
                <h1><strong>nombre:</strong> ${usuario.nombre!"Nombre no disponible"}</h1>

                <p><strong>Email:</strong> ${usuario.email!"Email no disponible"}</p>


                <button class="button" onclick="window.location.href='/editar-perfil'">Editar Perfil</button>
            </div>
        </section>

        <section class="purchase-history">
            <h3>Historial de Compras</h3>
            <#if productos?? && productos?size gt 0>
                <table>
                    <thead>
                        <tr>
                            <th>Artículo</th>
                            <th>Precio</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list productos as producto>
                        <tr>
                            <td>${producto.nombre!"Desconocido"}</td>
                            <td>${producto.precio!"0.00"} €</td>
                            <td>${producto.fechaCompra?string("dd/MM/yyyy")!"-"}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                <p>No hay historial de compras disponible.</p>
            </#if>
        </section>

    </div>

    <script>
        function confirmarCerrarSesion() {
            const confirmar = confirm("¿Estás seguro de que quieres cerrar sesión?");
            if (confirmar) {
                window.location.href = '/logout';
            }
        }
    </script>

</body>
</html>
