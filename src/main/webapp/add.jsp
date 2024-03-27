<%--
  Created by IntelliJ IDEA.
  User: Jonathan Cubas
  Date: 3/24/2024
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add to List</title>
</head>
<body>
<h1>Add to List:</h1>

<form action="view.jsp" method="post">
    <label for="name">Item Name:</label>
    <input type="text" id="name" name="name" required><br>

    <button type="submit">Submit</button>
</form>

<form action="index.jsp">
    <input type="submit" value="Back" />
</form>
</body>
</html>
