<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${titulo}</title>
    <style>
        /* Reset básico */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f7fa;
            color: #333;
            line-height: 1.6;
            padding: 0 20px;
        }

        header {
            background-color: #007BFF;
            color: white;
            padding: 20px 0;
            text-align: center;
            border-radius: 0 0 10px 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            margin-bottom: 10px;
            font-size: 2.2rem;
        }

        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            gap: 30px;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        nav a:hover {
            background-color: rgba(255, 255, 255, 0.2);
        }

        main {
            max-width: 1000px;
            margin: 40px auto;
        }

        section {
            background-color: white;
            padding: 25px;
            margin-bottom: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #007BFF;
            margin-bottom: 15px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #007BFF;
            color: white;
        }

        td input, td textarea {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        td textarea {
            min-height: 100px;
        }

        td button {
            padding: 8px 12px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        td button:hover {
            background-color: #0056b3;
        }

        td button.eliminar {
            background-color: #dc3545;
        }

        td button.eliminar:hover {
            background-color: #c82333;
        }

        form {
            display: inline;
        }

        form input[type="submit"], form button {
            margin: 5px 0;
        }

        a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }

        .inline-form {
            display: inline;
        }
    </style>
</head>
<body>
    <header>
        <h1>${titulo}</h1>
    </header>

    <!-- Formulario para añadir un nuevo producto -->
    <section>
        <h3>Añadir un Nuevo Producto</h3>
        <form action="/administrador/anadirProducto" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="${nombre?default('')}" required>

            <label for="precio">Precio:</label>
            <input type="number" step="0.01" id="precio" name="precio" value="${precio?default('')}" required>

            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion" required>${descripcion?default('')}</textarea>

            <button type="submit">Añadir Producto</button>
        </form>
    </section>

    <!-- Tabla de productos existentes -->
    <h2>Gestionar Productos</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <#list productos as producto>
                <tr>
                    <form action="/administrador/modificar-producto" method="post" class="inline-form">
                        <td>${producto.id}</td>
                        <td><input type="text" name="nombre" value="${producto.nombre}" required></td>
                        <td><input type="number" step="0.01" name="precio" value="${producto.precio}" required></td>
                        <td><textarea name="descripcion" required>${producto.descripcion}</textarea></td>
                        <td>
                            <input type="hidden" name="id_producto" value="${producto.id}">
                            <button type="submit">Modificar</button>
                        </td>
                    </form>
                    <form action="/administrador/eliminar-producto" method="post" class="inline-form" onsubmit="return confirm('¿Estás seguro de eliminar este producto?');">
                        <input type="hidden" name="id_producto" value="${producto.id}">
                        <button type="submit" class="eliminar">Eliminar</button>
                    </form>
                </tr>
            </#list>
        </tbody>
    </table>

    <a href="/logout">Cerrar sesión</a>
</body>
</html>
