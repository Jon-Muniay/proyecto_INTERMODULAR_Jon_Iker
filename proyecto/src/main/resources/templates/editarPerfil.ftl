<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Perfil</title>
    <style>
        body {
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
        }

        header h1 {
            margin-top: 10px;
        }

        .edit-profile {
            margin-top: 20px;
        }

        .edit-profile form {
            display: grid;
            gap: 15px;
        }

        .edit-profile .form-group {
            display: flex;
            flex-direction: column;
        }

        .edit-profile label {
            font-size: 16px;
            color: #333;
            margin-bottom: 5px;
        }

        .edit-profile input {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
            box-sizing: border-box;
        }

        .edit-profile .button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .edit-profile .button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <div class="container">

        <!-- Logo y Título -->
        <header>
            <img src="Imagenes/logo.png" alt="Logo de la tienda" class="logo">
            <h1>Editar Perfil</h1>
        </header>

        <!-- Formulario de edición de perfil -->
        <section class="edit-profile">
            <form id="editForm" method="post" action="/editarPerfil">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required>
                </div>

                <div class="form-group">
                    <label for="email">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" value="${usuario.email}" required>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" placeholder="Nueva contraseña" required>
                </div>

                <div class="form-group">
                    <button type="submit" class="button">Guardar Cambios</button>
                </div>
            </form>
        </section>

    </div>
</body>
</html>
