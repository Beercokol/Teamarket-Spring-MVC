<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${fn:length(featured_products) gt 0}">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <div>
        <c:forEach items="${featured_products}" var="featured_product">
            <div>
                <div >
                    <a href="<c:url value="/product/${featured_product.url}"/>"
                       title="Go to <c:out value="${featured_product.title}"/>">
                        <img  width="185px" height="185px"
                             alt="<c:out value="${featured_product.title}"/>"
                             src="<c:url value="/resources/images/${featured_product.photo.smallUrl}"/>">
                        <div >
                            <c:out value="${featured_product.title}"/>
                        </div>
                        <p >
                            <fmt:formatNumber type="number" value="${featured_product.price}"/> rub
                        </p>
                    </a>
                    <form action="<c:url value="/cart/add_quickly"/>" method=post>
                        <input type="hidden" name="id" value="<c:out value="${featured_product.id}"/>">
                        <input type="hidden" name="url"
                               value="<c:url value="/product/${product.url}"/>">
                        <p class="text"
                           title="Add <c:out value="${featured_product.title}"/> in Cart">
                            <button  type="submit" class="btn btn-success">Add in Cart</button>
                        </p>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>