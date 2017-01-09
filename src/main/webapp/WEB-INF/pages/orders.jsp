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
        #wrap{
            display: none;
            opacity: 0.8;
            position: fixed;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            padding: 16px;
            background-color: rgba(1, 1, 1, 0.725);
            z-index: 100;
            overflow: auto;
        }

        #window{
            width: 400px;
            height: 400px;
            margin: 50px auto;
            display: none;
            background: #fff;
            z-index: 200;
            position: fixed;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            padding: 16px;
        }

        .close{
            margin-left: 364px;
            margin-top: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>


<a href="/Laba2/">Назад в главное меню</a>

<br/>
<br/>

<h1>Список заказов</h1>

<c:if test="${!empty allOrders}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">ID тарифа</th>
            <th width="120">ID клиена</th>
            <th width="120">Дата заказа</th>
            <th width="60">Редактировать</th>
            <th width="60">Удалить</th>
        </tr>
        <c:forEach items="${allOrders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.tariffnum}</td>
                <td>${order.customernum}</td>
                <td>${order.date}</td>
                <td><a href="<c:url value='/orders/edit/${order.id}'/>">Редактировать</a></td>
                <td><a href="<c:url value='/orders/remove/${order.id}'/>">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<h1>Добавить тариф</h1>

<c:url var="addAction" value="/orders/add"/>

<form:form action="${addAction}" commandName="order">
    <table>
        <c:if test="${!empty order.id}">
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
                <form:label path="tariffnum">
                    <spring:message text="Номер тарифа"/>
                </form:label>
            </td>
            <td>
                <form:select path="tariffnum">
                    <form:options items="${tarnumlist}" type="text"/>
                </form:select>
                <%--<form:input path="tariffnum" type="text"/>--%>
            </td>
            <td>
                <form:errors path="tariffnum" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="customernum">
                    <spring:message text="Номер пользователя"/>
                </form:label>
            </td>
            <td>
                <form:select path="customernum">
                    <form:options items="${custnumlist}" type="text"/>
                </form:select>
                <%--<form:input path="customernum"/>--%>
            </td>
            <td>
                <form:errors path="customernum" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="date">
                    <spring:message text="Дата заказа"/>
                </form:label>
            </td>
            <td>
                <form:input path="date"/>
            </td>
            <td>
                <form:errors path="date" cssClass="error" />
            </td>
        </tr>
        <tr>
            <c:if test="${!empty order.id}">
                <td>
                    <input type="submit"
                           value="<spring:message text="Редактировать заказ"/>"/>
                </td>
                <td>
                    <form action="/orders">
                        <input type="submit" value="<spring:message text="Вернуться назад"/>"
                               name="Submit" id="frm1_submit" />
                    </form>
                </td>
            </c:if>
            <c:if test="${empty order.id}">
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="Добавить заказ"/>"/>
                </td>
            </c:if>
        </tr>
    </table>
</form:form>
</body>
</html>