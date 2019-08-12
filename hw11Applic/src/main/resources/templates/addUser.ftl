<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>addUserForm</title>
</head>
<body>
<p>AddUser</p>
<br>
<div>
    <fieldset>
        <form method="post" action="userInfo">
            Name<input type="text" name="name">
            Age<input type="number" name="age">
            <input type="submit" value="add user to dataBase">
        </form>
        <br>
        <form method="get" action="userInfo">

            <input type="submit" value="GetUserList">
        </form>
    </fieldset>
</div>
<a href='javascript:history.back();'>Back</a>

</body>
</html>