<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Menú de Pujas</title>
  <link rel="stylesheet" href="menu.css">
</head>
<body>
<style>/* Estilo general */
       body {
         font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
         background-color: #e6f0ff;
         margin: 0;
         padding: 0;
         text-align: center;
       }

       /* Encabezado */
       .header {
         background-color: #004080;
         padding: 15px;
         display: flex;
         justify-content: center;
         gap: 20px;
       }

       /* Botones de navegación */
       .nav-button {
         background-color: #0066cc;
         color: white;
         border: none;
         padding: 10px 20px;
         border-radius: 10px;
         font-size: 16px;
         cursor: pointer;
         transition: background-color 0.3s;
       }

       .nav-button:hover {
         background-color: #0052a3;
       }

       /* Título principal */
       .titulo {
         margin-top: 30px;
         color: #003366;
         font-size: 36px;
       }

       /* Menú principal */
       .menu {
         margin-top: 40px;
       }

       .menu-button {
         background-color: #3399ff;
         color: white;
         border: none;
         padding: 15px 30px;
         margin: 10px;
         border-radius: 12px;
         font-size: 18px;
         cursor: pointer;
         transition: background-color 0.3s;
       }

       .menu-button:hover {
         background-color: #1a8cff;
       }

       .menu-button.salir {
         background-color: #cc3300;
       }

       .menu-button.salir:hover {
         background-color: #b32400;
       }

       /* Secciones */
       .section {
         margin-top: 30px;
         padding: 20px;
       }

       /* Subtítulo */
       .subtitulo {
         font-size: 28px;
         color: #003366;
       }

       /* Campos de entrada */
       .input {
         padding: 10px;
         margin: 10px;
         width: 250px;
         border: 2px solid #66b2ff;
         border-radius: 8px;
         font-size: 16px;
       }

       .action-button {
         background-color: #0047b3;
         color: white;
         border: none;
         padding: 12px 25px;
         margin-top: 15px;
         border-radius: 10px;
         font-size: 18px;
         cursor: pointer;
         transition: background-color 0.3s;
       }

       .action-button:hover {
         background-color: #003399;
       }
 </style>

  <header class="header">
    <button class="nav-button" onclick="window.location.href='usuarios.html'">Ir al Perfil de Usuario</button>
    <button class="nav-button" onclick="window.location.href='subasta.html'">Ir a Subasta</button>
  </header>

  <h1 class="titulo">Menú de Pujas</h1>

  <div class="menu">
    <button class="menu-button" onclick="mostrar('pujar')">Pujar</button>
    <button class="menu-button salir" onclick="salir()">Salir</button>
  </div>

  <section id="pujar" class="section" style="display: none;">
    <h2 class="subtitulo">Hacer una Puja</h2>
    <input class="input" type="text" id="nombrePuja" placeholder="Nombre de la oferta" required><br>
    <input class="input" type="number" id="montoPuja" placeholder="Monto de la puja (€)" min="0" required><br>
    <button class="action-button" onclick="hacerPuja()">Pujar</button>
  </section>

  <script>
    const ofertas = []; // Puedes reemplazar esto con los datos reales desde el backend o dejarlo si aún es local.

    function mostrar(seccionId) {
      const secciones = document.querySelectorAll('.section');
      secciones.forEach(seccion => seccion.style.display = 'none');
      document.getElementById(seccionId).style.display = 'block';
    }

    function hacerPuja() {
      const nombre = document.getElementById('nombrePuja').value.trim();
      const monto = parseFloat(document.getElementById('montoPuja').value);

      if (!nombre || isNaN(monto) || monto <= 0) {
        alert("Por favor, completa todos los campos correctamente.");
        return;
      }

      const oferta = ofertas.find(o => o.nombre === nombre);
      if (!oferta) {
        alert("Oferta no encontrada.");
        return;
      }

      if (monto > oferta.precio) {
        oferta.precio = monto;
        alert("Puja exitosa. Nuevo precio actualizado.");
        document.getElementById('nombrePuja').value = '';
        document.getElementById('montoPuja').value = '';
      } else {
        alert("La puja debe ser mayor al precio actual.");
      }
    }

    function salir() {
      alert("Saliendo del sistema de pujas. ¡Hasta luego!");
      window.location.href = 'index.html';
    }
  </script>

</body>
</html>
