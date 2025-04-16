<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario</title>
    <link rel="stylesheet" href="usuarios.css">
</head>
<body>

    <div class="container">

        <header>
            <img src="Imagenes/Image 3 abr 2025, 12_35_27.png" alt="Logo de la tienda" class="logo">
            <h1>Perfil de Usuario</h1>

            <!-- Botones de navegación y botón de cerrar sesión -->
            <div class="header-buttons">
                <button class="button" onclick="window.location.href='menú.html'">Ir al Menú</button>
                <button class="button" onclick="window.location.href='subasta.html'">Ir a Subasta</button>
                <button class="button" onclick="confirmarCerrarSesion()">Cerrar sesión</button>
            </div>
        </header>

        <section class="user-info">
            <img src="Imagenes/" alt="Foto de Perfil" class="user-photo">
            <div class="user-details">
                <h2>Juan Pérez</h2>
                <p><strong>Email:</strong> juanperez@example.com</p>
                <p><strong>Dirección:</strong> Calle Ficticia, 123, Madrid</p>
                <p><strong>Fecha de Registro:</strong> 12/08/2024</p>

                <button class="button" onclick="window.location.href='editarPerfil.html'">Editar Perfil</button>
            </div>
        </section>

        <section class="purchase-history">
            <h3>Historial de Compras</h3>
            <table>
                <thead>
                    <tr>
                        <th>Artículo</th>
                        <th>Precio</th>
                        <th>Fecha</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Chandal</td>
                        <td>50 €</td>
                        <td>15/03/2025</td>
                    </tr>
                    <tr>
                        <td>Pantalones Vaqueros</td>
                        <td>30 €</td>
                        <td>20/02/2025</td>
                    </tr>
                </tbody>
            </table>
        </section>
    </div>

    <script>
        function confirmarCerrarSesion() {
const confirmar = confirm("¿Estás seguro de que quieres cerrar sesión?");
if (confirmar) {
window.location.href = 'login.html';
}
        }
    </script>

</body>
</html>