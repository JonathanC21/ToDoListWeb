<%--
  Created by IntelliJ IDEA.
  User: Jonathan Cubas
  Date: 3/24/2024
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove from List</title>
</head>
<body>
<h1>Remove from List:</h1>

<form action="view.jsp" method="post">
    <label for="id">ID to remove:</label>
    <input type="number" id="id" name="id" required><br>

    <button type="submit">Submit</button>
</form>

<form action="index.jsp">
    <input type="submit" value="Back" />
</form>

</body>
</html>
