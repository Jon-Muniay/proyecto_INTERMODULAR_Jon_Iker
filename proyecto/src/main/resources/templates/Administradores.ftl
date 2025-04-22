<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administradores-Subastas</title>
    <link rel="stylesheet" href="Administradores.css">

</head>
<style>/* Reset básico */
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

       td ul {
           padding-left: 18px;
       }

       button {
           padding: 8px 12px;
           margin-right: 5px;
           background-color: #007BFF;
           color: white;
           border: none;
           border-radius: 5px;
           font-weight: bold;
           cursor: pointer;
           transition: background-color 0.3s ease;
       }

       button:hover {
           background-color: #0056b3;
       }

       form p {
           margin-bottom: 15px;
       } </style>
<body>
    <header>
        <h1>Panel de Administración</h1>
        <nav>
            <ul>

                <li><a href="#resetear">Resetear Artículos y Pujas</a></li>
                <li><a href="#historico">Histórico de Ganadores</a></li>
            </ul>
        </nav>
    </header>

    <main>

        <section id="articulos">
            <h2>Artículos Propuestos para Subasta</h2>
            <form action="/validar-articulo" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Estado</th>
                            <th>Pujas Realizadas</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Chandal</td>
                            <td>Chandal cómodo para hacer ejercicio</td>
                            <td>Esperando Aprobación</td>
                            <td>
                                <ul>
                                    <li>100 € - Usuario A</li>
                                    <li>120 € - Usuario B</li>
                                </ul>
                            </td>
                            <td>
                                <input type="hidden" name="articulo_id" value="1">
                                <button type="submit" name="accion" value="aprobar">Aprobar</button>
                                <button type="submit" name="accion" value="eliminar">Eliminar</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Pantalones vaqueros</td>
                            <td>Pantalones muy abrigados, ideales para invierno</td>
                            <td>Esperando Aprobación</td>
                            <td>
                                <ul>
                                    <li>150 € - Usuario C</li>
                                    <li>160 € - Usuario D</li>
                                </ul>
                            </td>
                            <td>
                                <input type="hidden" name="articulo_id" value="2">
                                <button type="submit" name="accion" value="aprobar">Aprobar</button>
                                <button type="submit" name="accion" value="eliminar">Eliminar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </section>


        <section id="resetear">
            <h2>Resetear Artículos y Pujas</h2>
            <form action="/resetear-articulos" method="POST">
                <p>¿Está seguro de que desea resetear todos los artículos y pujas? Esto no eliminará los datos, solo los reiniciará para comenzar de nuevo.</p>
                <button type="submit" name="accion" value="resetear">Resetear</button>
            </form>
        </section>


        <section id="historico">
            <h2>Histórico de Ganadores</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre del Artículo</th>
                        <th>Ganador</th>
                        <th>Precio Final</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Chandal</td>
                        <td>Usuario B</td>
                        <td>120 €</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Pantalones vaqueros</td>
                        <td>Usuario D</td>
                        <td>160 €</td>
                    </tr>
                </tbody>
            </table>
        </section>
    </main>


</body>
</html>