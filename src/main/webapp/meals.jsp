<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11.10.2022
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <td style="font-weight: bold">Date</td>
        <td style="font-weight: bold">Description</td>
        <td style="font-weight: bold">Calories</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${mealToList}" var="mealTo">
        <tr>
            <fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
            <td style="color:${mealTo.excess ? 'red' : 'green'}"><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}" /></td>
            <td style="color:${mealTo.excess ? 'red' : 'green'}">${mealTo.description}</td>
            <td style="color:${mealTo.excess ? 'red' : 'green'}">${mealTo.calories}</td>
            <td>Update</td>
            <td>Update</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
