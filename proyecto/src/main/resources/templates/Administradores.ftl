<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administradores-Subastas</title>
    <link rel="stylesheet" href="Administradores.css">

</head>
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