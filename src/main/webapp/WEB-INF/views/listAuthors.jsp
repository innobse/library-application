<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

    </head>
    <body>
        <h1>Список авторов:</h1>

        <br />
        <a href="/authors/?view=tableAuthors">Отобразить таблицей</a>
        <br />

        <ul type="square">
            <c:forEach var="obj" items="${objects}">
                <li><a href="/authors/${obj.id}">${obj.name}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>