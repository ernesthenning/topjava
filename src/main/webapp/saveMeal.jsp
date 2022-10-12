<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save meal</title>
</head>
<body>
<h3>Save meal</h3>
<form method="POST" action="saveMeal">
    <table border="0">
        <tr>
            <td>DateTime</td>
            <td><input type="datetime-local" name="dateTime" value="${meal.dateTime}" /></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" value="${meal.description}" /></td>
        </tr>
        <tr>
            <td>Calories</td>
            <td><input type="text" name="calories" value="${meal.calories}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
