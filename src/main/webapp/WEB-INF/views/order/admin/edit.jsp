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
        <meta name="title" content="Order editing ${order.number} ">
        <title>Order editing ${order.number} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div>
        <section id="order">
            <div>
                <div>
                    <div  class="centre-span">
                        <b>
                            <span> Order editing :</span>
                            <span >${order.number}</span>
                        </b>
                    </div>
                </div>
                <div>
                    <form action="<c:url value="/admin/order/update"/>" method="post">
                        <input class="id-input" name="id" value="${order.id}">
                        <input class="id-input"  name="auth_user" value="${auth_user.id}">
                        <table >
                            <tr>
                                <th>Number:</th>
                                <td>
                                    <input  type="text" name="number"
                                           pattern="[A-Za-z0-9]{3,10}"
                                           placeholder=" Add number (A-Z, a-z, 0-9)"
                                           value="${order.number}" minlength="3" maxlength="10" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Status:</th>
                                <td>
                                    <select name="status" title="Order status">
                                        <option value="${order.status.description}">${order.status.description}</option>
                                        <c:forEach items="${statuses}" var="status">
                                            <c:if test="${status ne order.status}">
                                                <option value="${status.description}">${status.description}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Date:</th>
                                <td>${order.date}</td>
                            </tr>
                            <tr>
                                <th>Customer:</th>
                                <td>
                                    <input  type="text" name="user_name" minlength="2"
                                           maxlength="50"
                                           placeholder=" Add customer name" value="${order.client.name}"
                                           required><br>
                                    <input  type="email" name="user_email" minlength="5"
                                           value="${order.client.email}" pattern="[A-Za-z0-9_.@]{5,50}"
                                           maxlength="50"
                                           placeholder=" Add email , (A-Z, a-z, 0-9, _, ., @)"><br>
                                    <input id="phone"  type="text" name="user_phone" required
                                           placeholder=" Add customer phone" value="${order.client.phone}">
                                </td>
                            </tr>
                            <tr>
                                <th>Delivery address :</th>
                                <td>
                                    <input  type="text" name="shipping-address" maxlength="100"
                                           placeholder=" Add delivery address" value="${order.shippingAddress}">
                                </td>
                            </tr>
                            <tr>
                                <th>Delivery Details:</th>
                                <td>
                                    <input  type="text" name="shipping-details" maxlength="100"
                                           placeholder=" Add delivery details"
                                           value="${order.shippingDetails}">
                                </td>
                            </tr>
                            <tr>
                                <th>Comments :</th>
                                <td>
                                    <textarea name="description" maxlength="250"
                                              placeholder=" Comment">${order.description}</textarea>
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
                                <th>Total amount :</th>
                                <td>${order_price} rub</td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button  class="btn btn-success" type="submit"
                                            title="Update Order Information">Save
                                    </button>
                                    <button class="btn btn-danger" type="reset"
                                            title="Reset Entered Data">Reset
                                    </button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>