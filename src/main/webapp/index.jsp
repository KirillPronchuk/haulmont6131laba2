<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Главное меню</title>
    </head>
    <body>
        <h3>Главное меню</h3>
        <br/>
            <a href="<c:url value="/customers"/>" target="_blank">Список пользователей</a>
        <br/>
        <br/>
            <a href="<c:url value="/orders"/>" target="_blank">Список заказов</a>
        <br/>
        <br/>
            <a href="<c:url value="/tariffs"/>" target="_blank">Список тарифов</a>
        <br/>
    </body>
</html>