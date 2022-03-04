<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

    </head>
    <body>
        <h1>Список авторов:</h1>

        <br />
        <a href="/authors/?view=listAuthors">Отобразить списком</a>
        <br />

        <table border="1px">
            <c:forEach var="obj" items="${objects}">
                <tr>
                    <td><a href="/authors/${obj.id}">${obj.name}</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>