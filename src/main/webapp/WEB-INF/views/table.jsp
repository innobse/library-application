<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

    </head>
    <body>
        <h1>Список книг:</h1>
        <table border="1px">
            <c:forEach var="book" items="${books}">
                <tr>
                    <td width="40%">
                            ${book.author.name}
                    </td>
                    <td width="60%">
                            ${book.name}
                    </td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>