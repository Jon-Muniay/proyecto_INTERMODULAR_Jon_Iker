<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subastas</title>
    <link rel="stylesheet" href="subasta.css">
</head>
<body>

    <header>
        <img src="Imagenes/Image 3 abr 2025, 12_35_27.png" alt="Logo de la tienda" style="width: 120px; display: block; margin: 20px auto;">
        <h1 style="text-align:center; color:white; text-shadow: 2px 2px #2c3e50;">Bienvenido al Sistema de Subastas</h1>


        <div style="position: absolute; right: 20px; top: 20px;">
            <button class="button" onclick="window.location.href='usuarios.html'">MI USUARIO</button>
        </div>
    </header>

    <div class="container">
        <h1>Bienvenido al Sistema de Subastas</h1>

        <h2>Artículos Disponibles para Pujar</h2>

        <div class="auction-item">
            <img src="Imagenes/chandal.png" alt="Chandal">
            <div>
                <h3>Artículo 1: Chandal</h3>
                <p><strong>Descripción:</strong> Chandal cómodo para hacer ejercicio</p>
                <p><strong>Precio actual de la puja:</strong> 50 €</p>
                <button class="button" onclick="pujar(1, 50)">Pujar</button>
            </div>
        </div>

        <div class="auction-item">
            <img src="Imagenes/vaqueros.png" alt="Pantalones Vaqueros">
            <div>
                <h3>Artículo 2: Pantalones vaqueros</h3>
                <p><strong>Descripción:</strong> Pantalones muy abrigados, ideales para invierno</p>
                <p><strong>Precio actual de la puja:</strong> 15 €</p>
                <button class="button" onclick="pujar(2, 15)">Pujar</button>
            </div>
        </div>

        <div class="auction-item">
            <img src="Imagenes/camiseta.png" alt="Camiseta">
            <div>
                <h3>Artículo 3: Camiseta</h3>
                <p><strong>Descripción:</strong> Camiseta barata y de buena calidad</p>
                <p><strong>Precio actual de la puja:</strong> 7 €</p>
                <button class="button" onclick="pujar(3, 7)">Pujar</button>
            </div>
        </div>

        <div class="history">
            <h3>Historial de Pujas</h3>
            <ul id="history-list">

            </ul>
        </div>
    </div>

    <script>
        let historialPujas = [];
        let offeredItems = [];

        function pujar(idArticulo, precio) {
let nuevaPuja = prompt(`Introduce el dinero que deseas pujar por el artículo ${idArticulo}. El precio actual es ${precio} €`);
            if (nuevaPuja && !isNaN(nuevaPuja) && parseFloat(nuevaPuja) > precio) {
let historialItem = {
articulo: idArticulo,
monto: nuevaPuja,
fecha: new Date().toLocaleString()
};
                historialPujas.push(historialItem);
                actualizarHistorial();
                alert(`¡Puja realizada con éxito! Tu oferta es ${nuevaPuja} €.`);
            } else {
alert('El dinero ofrecido en la puja debe ser mayor al precio actual.');
}
        }

        function actualizarHistorial() {
let historyList = document.getElementById('history-list');
historyList.innerHTML = '';
historialPujas.forEach(puja => {
let listItem = document.createElement('li');
listItem.textContent = `Pujaste ${puja.monto} € por el artículo ${puja.articulo} el ${puja.fecha}.`;
                historyList.appendChild(listItem);
            });
        }
    </script>

    <script>
        function ofrecerArticulo() {
let itemName = document.getElementById('item-name').value;
let itemDescription = document.getElementById('item-description').value;
let itemPrice = document.getElementById('item-price').value;
let itemImage = document.getElementById('item-image').value;

if (itemName && itemDescription && itemPrice && itemImage) {
let itemDiv = document.createElement('div');
itemDiv.classList.add('auction-item');

itemDiv.innerHTML = `
<img src="${itemImage}" alt="${itemName}">
<div>
<h3>${itemName}</h3>
                        <p><strong>Descripción:</strong> ${itemDescription}</p>
                        <p><strong>Precio inicial:</strong> ${itemPrice} €</p>
                    </div>
                `;

                document.getElementById('offered-items-list').appendChild(itemDiv);

                document.getElementById('item-name').value = '';
                document.getElementById('item-description').value = '';
                document.getElementById('item-price').value = '';
                document.getElementById('item-image').value = '';

                alert('¡Artículo ofrecido con éxito!');
            } else {
alert('Por favor, completa todos los campos.');
}
        }
    </script>
</body>
</html>