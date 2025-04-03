<!DOCTYPE html>
<html>
<head>
    <title>${titulo}</title>
</head>
<body>
    <h1>Bienvenido, ${usuario}!</h1>
    <p>Lista de productos:</p>
    <ul>
        <#list productos as producto>
            <li>${producto}</li>
        </#list>
    </ul>
</body>
</html>
