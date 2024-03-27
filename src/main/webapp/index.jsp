<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>To Do List</title>
</head>
<body>
<h1><%= "To Do List Web Application" %>
</h1>
<br/>
<form action="view.jsp">
    <input type="submit" value="View List" />
</form>
<form action="add.jsp">
    <input type="submit" value="Add to List" />
</form>
<form action="remove.jsp">
    <input type="submit" value="Remove from List" />
</form>

</body>
</html>