<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${fn:length(products) gt 0}">
    <c:forEach items="${products}" var="product">
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <div    class="col-xs-6 col-sm-6 col-md-6 col-lg-3 col-xl-3 wow pulse" id="pr-2">
            <div>
                <a href="<c:url value="/product/${product.url}"/>" title="Go to ${product.title}">
                    <img src="<c:url value="/resources/images/${product.photo.smallUrl}"/>"
                         alt="<c:out value="${product.title}"/>"
                         width="185px" height="185px">
                    <div>
                        <c:out value="${product.title}"/>
                    </div>
                    <p>
                        <fmt:formatNumber type="number" value="${product.price}"/> rub
                    </p>
                </a>
                <form action="<c:url value="/cart/add_quickly"/>" method="post">
                    <input type="hidden" name="id" value="<c:out value="${product.id}"/>">
                    <input type="hidden" name="url" value="/product/all">
                    <p class="text" title="Put  <c:out value="${product.title}"/> in cart ">
                        <button class="btn btn-success" type="submit">Add in cart</button>
                    </p>
                </form>
            </div>
        </div>
    </c:forEach>
</c:if>