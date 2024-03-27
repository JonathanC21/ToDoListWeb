<%@ page import="cubas.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="cubas.entity.ItemEntity" %>
<%@ page import="cubas.Main" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Jonathan Cubas
  Date: 3/24/2024
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<html>
<head>
    <title>View List</title>
</head>
<%
    if (request.getParameter("name") != null){
        String itemName = request.getParameter("name");
        Main.addItem(itemName);
    }
    if (request.getParameter("id") != null){
        int itemID = Integer.parseInt(request.getParameter("id"));
        Main.removeItemFromDatabase(itemID);
    }

%>


<style>
    table, th, td {
        border:1px solid black;
    }
</style>
<body>

<h1>To Do List:</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <%
        ArrayList<Item> itemList = Main.getToDoList();
        for (Item item : itemList) {
    %>
    <tr>
        <td><%= item.getItemID() %></td>
        <td><%= item.getItemName() %></td>

    </tr>
    <% } %>
    </tbody>
</table>



<form action="index.jsp">
    <input type="submit" value="Back" />
</form>
</body>
</html>
