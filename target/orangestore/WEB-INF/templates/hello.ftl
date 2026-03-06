<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Orange Store - Привіт</title>
</head>
<body>
    <h2>Результат роботи DAO:</h2>
    <p>${message}</p>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <#list users as user>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
        </#list>
    </table>
</body>
</html>