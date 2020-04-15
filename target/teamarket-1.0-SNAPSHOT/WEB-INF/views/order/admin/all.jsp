<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="noindex,nofollow">
        <title>Orders</title>

        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="orderEntities">
            <div >
                <c:set var="orders_length" value="${fn:length(orders)}"/>
                <div >
                    <div class="centre-span">
                        <b>
                            <span  >Orders</span>
                            <c:if test="${orders_length eq 0}">
                                <span> - is empty!</span>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${orders_length gt 0}">
                    <div>
                        <table >
                            <tr>
                                <th>Number</th>
                                <th>Status</th>
                                <th >Date</th>
                                <th>
                                    Action
                                    <a href="<c:url value="/admin/order/delete_all"/>" title="Delete all orders">
                                        <button  class="btn btn-danger" type="submit">Delete ALL</button>
                                    </a>
                                </th>
                            </tr>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <td>${order.number}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.status eq status_new}">
                                                <span >${order.status.description}</span>
                                            </c:when>
                                            <c:otherwise>${order.status.description}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td >${order.date}</td>
                                    <td>
                                        <a href="<c:url value="/admin/order/view/${order.id}"/>"
                                           title="Watch order ${order.number}">
                                            <button class="btn btn-info" type="submit">Watch</button>
                                        </a>
                                        <a href="<c:url value="/admin/order/edit/${order.id}"/>"
                                           title="Edit order ${order.number}">
                                            <button   class="btn btn-success" type="submit">Edit</button>
                                        </a>
                                        <a href="<c:url value="/admin/order/delete/${order.id}"/>"
                                           title="Delete order ${order.number}">
                                            <button   class="btn btn-danger" type="submit">Delete</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>