<!-- formulario-producto.html -->
<!-- formulario-producto.html -->
<form method="post" action="/api/productos" style="max-width: 500px; margin: 20px auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
    <h2 style="text-align: center; color: #333;">Agregar Producto a la Puja</h2>
    <input type="text" name="nombre" placeholder="Nombre del producto" required style="width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px;">
    <input type="number" name="precio" placeholder="Precio inicial" min="0" step="0.01" required style="width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px;">
    <textarea name="descripcion" placeholder="Descripción del producto" required style="width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px;"></textarea>
    <button type="submit" style="width: 100%; padding: 10px; background-color: #007bff; color: #fff; border: none; border-radius: 4px; cursor: pointer;">Agregar producto</button>
</form>
<script>
document.getElementById('formProducto').addEventListener('submit', async (e) => {
    e.preventDefault();

    const producto = {
        nombre: document.getElementById('nombre').value,
        precio: parseFloat(document.getElementById('precio').value),
        descripcion: document.getElementById('descripcion').value
    };

    const response = await fetch('/api/productos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(producto)
    });

    if (response.ok) {
        alert('Producto agregado correctamente');
        // Limpiar formulario o actualizar lista
    } else {
        alert('Error al agregar producto');
    }
});
</script>