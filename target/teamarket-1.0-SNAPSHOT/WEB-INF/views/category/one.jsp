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
        <meta name="description" content="<c:out value="${category.title}"/> - ${category.description}"/>
        <meta name="keywords" content="<c:out value="${category.title}"/>"/>
        <meta name="robots" content="index,follow">
        <meta name="title" content="<c:out value="${category.title}"/> ">
        <title><c:out value="${category.title}"/> </title>

    </head>
    <body>
    <jsp:include page="/WEB-INF/views/home/navbar.jsp"/>
    <div class="bg-im">
        <section  id="category_${category.url}">
            <div >
                <div class="sec-home">
                    <h3>
                        <img id="label-category" width="150px" height="150px" alt="<c:out value="${category.title}"/>"
                             src="<c:url value="/resources/images/${category.photo.smallUrl}"/>">
                        <div>
                            <span><c:out value="${category.title}"/></span>
                            <c:if test="${fn:length(products) eq 0}">
                                <span> - is empty!</span>
                            </c:if>
                        </div>
                    </h3>
                </div>
                <div class="cat-list">
                <jsp:include page="/WEB-INF/views/product/list.jsp"/>
                </div>
                <div>
                    <h4 class="sec-home title-pr">
                        <a href="<c:url value="/product/all"/>" title="Go to all products">
                            All tea:
                        </a>
                    </h4>
                </div>
            </div>
        </section>
    </div>
    <c:if test="${not empty category.description}">

    </c:if>
    <jsp:include page="/WEB-INF/views/home/end.jsp"/>

    </body>
    </html>
</compress:html>