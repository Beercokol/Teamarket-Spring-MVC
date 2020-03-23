<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">

<div id="table-pr">
    <div>
        <table >
            <tr>
                <th>Name</th>
                <th>Quantity</th>
                <th>Photo</th>
                <th>Category</th>
                <th>Price</th>
            </tr>
            <c:forEach items="${sale_positions}" var="position">
                <tr>
                    <td>
                        <a href="<c:url value="/product/${position.product.url}"/>"
                           title="Go to <c:out value="${position.product.title}"/>">
                            <c:out value="${position.product.title}"/>
                        </a>
                    </td>
                    <td>${position.number} </td>
                    <td>
                        <img src="<c:url value="/resources/images/${position.product.photo.smallUrl}"/>"
                             width="50px" height="50px" alt="<c:out value="${position.product.title}"/>">
                    </td>
                    <td>
                        <a href="<c:url value="/category/${position.product.category.url}"/>"
                           title=" Go to category <c:out value="${position.product.category.title}"/>">
                            <c:out value="${position.product.category.title}"/>
                        </a>
                    </td>
                    <td>
                        <fmt:formatNumber type="number" value="${position.product.price}"/> rub
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td style="text-align: right;">
                    <strong>Total:</strong>
                </td>
                <td>
                    <b><fmt:formatNumber type="number" value="${price_of_cart}"/> rub</b>
                </td>
            </tr>
        </table>
    </div>
</div>