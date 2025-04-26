<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Tienda de Ropa</title>
    <link rel="stylesheet" href="registro.css">
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

        .register-box {
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
            border-color: #ff6f61;
            outline: none;
        }

        .btn-register {
            width: 100%;
            padding: 12px;
            background-color: #ff6f61;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .btn-register:hover {
            background-color: #e65c50;
        }

        .login-link {
            margin-top: 20px;
            font-size: 14px;
            color: #666666;
        }

        .login-link a {
            color: #ff6f61;
            text-decoration: none;
            font-weight: bold;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="register-box">

            <div class="header">
                <img src="Imagenes/Image 3 abr 2025, 12_35_27.png" alt="Logo de la Tienda" class="logo">
                <h1>Registro</h1>
            </div>

            <!-- ¡Formulario corregido! -->
            <form action="/registro" method="POST">
                <div class="input-group">
                    <label for="nombre">Nombre Completo</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Ingresa tu nombre" required>
                </div>
                <div class="input-group">
                    <label for="email">Correo Electrónico</label>
                    <input type="email" id="email" name="email" placeholder="Ingresa tu correo" required>
                </div>
                <div class="input-group">
                    <label for="password">Contraseña</label>
                    <input type="password" id="password" name="password" placeholder="Crea una contraseña" required>
                </div>
                <!-- Este campo puedes validarlo con JS si quieres -->
                <div class="input-group">
                    <label for="confirm-password">Confirmar Contraseña</label>
                    <input type="password" id="confirm-password" name="confirm-password" placeholder="Confirma tu contraseña" required>
                </div>
                <button type="submit" class="btn-register">Registrarse</button>
            </form>

            <p class="login-link">¿Ya tienes una cuenta? <a href="/">Inicia sesión aquí</a></p>
        </div>
    </div>
</body>
</html>
