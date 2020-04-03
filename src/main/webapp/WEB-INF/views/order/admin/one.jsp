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
        <meta name="title" content="Order ${order.number}">
        <title>Заказ ${order.number} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div>
        <section id="order">
            <div >
                <div>
                    <div>
                        <b><span>Order </span><span>${order.number}</span></b>
                    </div>
                </div>
                <div>
                    <table>
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
                                                <span>${order.manager.role.description}</span>
                                            </c:when>
                                            <c:when test="${order.manager.role eq manager_role}">
                                                <span>${order.manager.role.description}</span>
                                            </c:when>
                                            <c:otherwise>${order.manager.role.description}</c:otherwise>
                                        </c:choose>
                                        <a href="<c:url value="/admin/user/view/${order.manager.id}"/>">
                                                ${order.manager.name}
                                        </a>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Customer:</th>
                            <td>
                                Name: <b>${order.client.name}</b>
                                <br>Email: <b>${order.client.email}</b>
                                <br>Phone: <b>${order.client.phone}</b>
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
                                    <c:otherwise>The list of products is empty!</c:otherwise>
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
                                <a href="<c:url value="/admin/order/edit/${order.id}"/>"
                                   title="Edit order ${order.number}">
                                    <button class="btn btn-success" type="submit">Edit</button>
                                </a>
                                <a href="<c:url value="/admin/order/delete/${order.id}"/>"
                                   title="Delete order ${order.number}">
                                    <button class="btn btn-danger" type="submit">Delete</button>
                                </a>
                                <a href="<c:url value="/admin/order/all"/>" title="Back to the list of orders">
                                    <button class="btn btn-success" type="submit">Back</button>
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
