<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

    </head>
    <body>
        <h1>${author}</h1>
        <ul type="square">
            <c:forEach var="book" items="${books}">
                <li>${book.name}</li>
            </c:forEach>
        </ul>
    </body>
</html>