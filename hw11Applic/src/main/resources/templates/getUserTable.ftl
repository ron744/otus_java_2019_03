<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserInfo</title>
</head>
<body>
<p>UsersTable</p>
<br>
<div>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <#list users as user>
        <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
        </tr>
        </#list>
    </table>
    <a href='javascript:history.back();'>Back</a>
</div>
</body>
</html>