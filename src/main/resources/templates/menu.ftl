<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Menu</title>
  <link rel="stylesheet" href="menú.css">
</head>
<body>

  <!-- Encabezado con botones de navegación -->
  <header>
    <button onclick="window.location.href='usuarios.html'">Ir al Perfil de Usuario</button>
    <button onclick="window.location.href='subasta.html'">Ir a Subasta</button>
  </header>

  <h1>Menú de Pujas</h1>
  <h2>¡Bienvenido, ${usuario.nombre}!</h2>


  <div class="menu">
    <button onclick="mostrar('subir')">Subir Oferta</button>
    <button onclick="mostrar('ver')">Ver Ofertas</button>
    <button onclick="mostrar('pujar')">Pujar</button>
    <button onclick="salir()">Salir</button>
  </div>

  <div id="subir" class="section">
    <h2>Subir Oferta</h2>
    <input type="text" id="nombreOferta" placeholder="Nombre de la oferta"><br>
    <textarea id="descripcionOferta" placeholder="Descripción de la oferta"></textarea><br>
    <input type="number" id="precioOferta" placeholder="Precio inicial (€)"><br>
    <input type="file" id="imagenOferta" accept="image/*" onchange="previewImagen(event)"><br>
    <img id="preview" style="max-width:150px; margin-top:10px; display:none;"><br>
    <button onclick="subirOferta()">Subir</button>
  </div>

  <div id="ver" class="section">
    <h2>Ofertas Disponibles</h2>
    <div id="listaOfertas" class="ofertas-lista"></div>
  </div>

  <div id="pujar" class="section">
    <h2>Hacer una Puja</h2>
    <input type="text" id="nombrePuja" placeholder="Nombre de la oferta"><br>
    <input type="number" id="montoPuja" placeholder="Monto de la puja (€)"><br>
    <button onclick="hacerPuja()">Pujar</button>
  </div>

  <script>
    const ofertas = [];

    function mostrar(seccionId) {
const secciones = document.querySelectorAll('.section');
secciones.forEach(s => s.style.display = 'none');
document.getElementById(seccionId).style.display = 'block';
if (seccionId === 'ver') actualizarOfertas();
}

    function previewImagen(event) {
const img = document.getElementById('preview');
const file = event.target.files[0];
if (file) {
const reader = new FileReader();
reader.onload = function(e) {
img.src = e.target.result;
img.style.display = 'block';
}
        reader.readAsDataURL(file);
      } else {
img.style.display = 'none';
}
    }

    function subirOferta() {
const nombre = document.getElementById('nombreOferta').value;
const descripcion = document.getElementById('descripcionOferta').value;
const precio = parseFloat(document.getElementById('precioOferta').value);
const imagenInput = document.getElementById('imagenOferta');
const imagen = imagenInput.files[0];

if (nombre && !isNaN(precio)) {
let imagenURL = '';
if (imagen) {
const reader = new FileReader();
reader.onload = function(e) {
imagenURL = e.target.result;
ofertas.push({ nombre, descripcion, precio, imagenURL });
            alert("Oferta subida con éxito.");
            limpiarCampos();
          };
          reader.readAsDataURL(imagen);
        } else {
ofertas.push({ nombre, descripcion, precio, imagenURL });
          alert("Oferta subida con éxito.");
          limpiarCampos();
        }
      } else {
alert("Por favor, completa los campos correctamente.");
}
    }

    function limpiarCampos() {
document.getElementById('nombreOferta').value = '';
document.getElementById('descripcionOferta').value = '';
document.getElementById('precioOferta').value = '';
document.getElementById('imagenOferta').value = '';
document.getElementById('preview').style.display = 'none';
}

    function actualizarOfertas() {
const lista = document.getElementById('listaOfertas');
lista.innerHTML = '';
if (ofertas.length === 0) {
lista.innerHTML = '<p>No hay ofertas aún.</p>';
} else {
ofertas.forEach(oferta => {
const div = document.createElement('div');
div.className = 'oferta';
div.innerHTML = `
<strong>🛍️ ${oferta.nombre}</strong><br>
            ✍️ ${oferta.descripcion}<br>
            <strong>${oferta.precio.toFixed(2)}€</strong><br>
            ${oferta.imagenURL ? `<img src="${oferta.imagenURL}" alt="Imagen de ${oferta.nombre}">` : ''}
          `;
          lista.appendChild(div);
        });
      }
    }

    function hacerPuja() {
const nombre = document.getElementById('nombrePuja').value;
const monto = parseFloat(document.getElementById('montoPuja').value);
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
actualizarOfertas();
} else {
alert("La puja debe ser mayor al precio actual.");
}
    }

    function salir() {
alert("Saliendo del sistema de pujas. ¡Hasta luego!");
window.close();
}
  </script>

</body>
</html>