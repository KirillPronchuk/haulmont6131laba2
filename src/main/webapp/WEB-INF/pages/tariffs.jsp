<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница тарифов</title>
    <style type="text/css">
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }
        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }
        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }
        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<a href="/Laba2/">Назад в главное меню</a>

<br/>
<br/>

<h1>Список тарифов</h1>

<c:if test="${!empty allTariffs}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Название</th>
            <th width="120">Скорость</th>
            <th width="120">Цена</th>
            <th width="60">Редактировать</th>
            <th width="60">Удалить</th>
        </tr>
        <c:forEach items="${allTariffs}" var="tariff">
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.name}</td>
                <td>${tariff.speed}</td>
                <td>${tariff.cost}</td>
                <td><a href="<c:url value='/tariffs/edit/${tariff.id}'/>">Редактировать</a></td>
                <td><a href="<c:url value='/tariffs/remove/${tariff.id}'/>">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Добавить тариф</h1>

<c:url var="addAction" value="/tariffs/add"/>

<form:form action="${addAction}" commandName="tariff">
    <table>
        <c:if test="${!empty tariff.id}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Название"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" type="text"/>
            </td>
            <td>
                <form:errors path="name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="speed">
                    <spring:message text="Скорость"/>
                </form:label>
            </td>
            <td>
                <form:input path="speed"/>
            </td>
            <td>
                <form:errors path="speed" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="cost">
                    <spring:message text="Цена"/>
                </form:label>
            </td>
            <td>
                <form:input path="cost"/>
            </td>
            <td>
                <form:errors path="cost" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty tariff.id}">
                    <input type="submit"
                           value="<spring:message text="Редактировать проект"/>"/>
                </c:if>
                <c:if test="${empty tariff.id}">
                    <input type="submit"
                           value="<spring:message text="Добавить проект"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>