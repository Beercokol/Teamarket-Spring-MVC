<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>
</head>
<body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<jsp:include page="/WEB-INF/views/home/navbar.jsp"/>
<div>
    <section>
        <div class="bg-im no-margin">
            <div id="pr-one">
                <div >
                    <img src="<c:url value="/resources/images/${product.photo.smallUrl}"/>"
                         width="185px" height="185px" alt="<c:out value="${product.title}"/>">
                </div>
                <div>
                    <h3 >
                        <b><c:out value="${product.title}"/></b>
                    </h3>
                    <h5>
                        Articul: <c:out value="${product.article}"/>
                    </h5>
                    <h3>
                            <span>
                                <fmt:formatNumber type="number" value="${product.price}"/> rub
                            </span>
                    </h3>
                    <form action="<c:url value="/cart/add"/>" method=post>
                        <input type=hidden name="id" value="<c:out value="${product.id}"/>">
                        <p class="text" title="Add <c:out value="${product.title}"/> in cart">
                            <button class="btn btn-success" type="submit">Add in cart</button>
                        </p>
                    </form>
                </div>
                <div >
                    <p><b>Product characteristics :</b></p>
                    <p>${product.parameters}</p>
                    <c:if test="${not empty product.description}">
                        <br>
                        <p><b>Product description:</b></p>
                        <p><c:out value="${product.description}"/></p>
                    </c:if>
                    <c:if test="${not empty product.photo.longUrl}">
                        <p>
                            <img src="<c:url value="/resources/images/${product.photo.longUrl}"/>"
                                 alt="<c:out value="${product.title}"/>">
                        </p>
                    </c:if>
                    <br>
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/home/end.jsp"/>

</div>
</body>