<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserInfo</title>
</head>
<body>
<#--<a href="addPerson">Add User</a>-->
<br><br>
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
</div>
</body>
</html>