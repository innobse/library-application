<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

    </head>
    <body>
        <h1>Новый автор:</h1>
        <form method="POST" action="/authors/new" id="newAuthor" name="newAuthor" enctype="multipart/form-data">
            <input type="number" id="id" name="id" value="" hidden />
            <input type="text" id="name" name="name" value="Имя автора" />
            <br />
            <input type="submit" id="btnAddAuthor" name="btnAddAuthor" value="Add New Author" />
        </form>
    </body>
</html>