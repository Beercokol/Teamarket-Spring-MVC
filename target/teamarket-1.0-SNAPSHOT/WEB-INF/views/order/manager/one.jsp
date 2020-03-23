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
        <meta name="title" content="Заказ ${order.number} ">
        <title>Заказ ${order.number} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">

    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/manager_navbar.jsp"/>
    <div class="container-fluid">
        <section id="order">
            <div class="row admin-page">
                <div>
                    <div>
                        <b>
                            <span>Order </span>
                            <span>${order.number}</span>
                        </b>
                    </div>
                </div>

                <div>
                    <table >
                        <tr>
                            <th>Number:</th>
                            <td>${order.number}</td>
                        </tr>
                        <tr>
                            <th>Status:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${order.status eq status_new}">
                                        <span><b>${order.status.description}</b></span>
                                    </c:when>
                                    <c:otherwise>${order.status.description}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Date:</th>
                            <td>${order.date}</td>
                        </tr>
                        <tr>
                            <th>Order processed:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${order.manager ne null}">
                                        <c:choose>
                                            <c:when test="${order.manager.role eq admin_role}">
                                                <b><span>${order.manager.role.description}</span></b>
                                            </c:when>
                                            <c:when test="${order.manager.role eq manager_role}">
                                                <span>${order.manager.role.description}</span>
                                            </c:when>
                                            <c:otherwise>${order.manager.role.description}</c:otherwise>
                                        </c:choose>
                                        <a href="<c:url value="/managers/user/view/${order.manager.id}"/>">
                                                ${order.manager.name}
                                        </a>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Costumer:</th>
                            <td>
                                Name: <b>${order.client.name}</b>
                                <br>Email: <b>${order.client.email}</b>
                                <br>phone: <b>${order.client.phone}</b>
                            </td>
                        </tr>
                        <tr>
                            <th>Delivery address:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty order.shippingAddress}">
                                        ${order.shippingAddress}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Delivery details:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty order.shippingDetails}">
                                        ${order.shippingDetails}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Comments:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty order.description}">
                                        ${order.description}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Products:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:length(sale_positions) gt 0}">
                                        <c:forEach items="${sale_positions}" var="position">
                                            <a href="<c:url value="/product/${position.product.url}"/>"
                                               title="Go to product ${position.product.title}">
                                                    ${position.product.title}
                                            </a>, № ${position.product.id},
                                            <br>${position.number} x ${position.product.price} rub
                                            <br>--------------<br>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>List of products is empty!</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>total amount:</th>
                            <td><b>${order_price}</b> rub</td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <c:if test="${(order.status eq status_new) or (order.manager eq auth_user)}">
                                    <a href="<c:url value="/managers/order/edit/${order.id}"/>"
                                       title="Edit order ${order.number}">
                                        <button  type="submit">Edit</button>
                                    </a>
                                </c:if>
                                <a href="<c:url value="/managers/order/all"/>" title="Return to the list of orders ">
                                    <button  type="submit">Back</button>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>
