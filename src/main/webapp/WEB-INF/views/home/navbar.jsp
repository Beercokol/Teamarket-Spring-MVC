<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">

    <title>Document</title>
    </head>


    <body id="home-page-body">
    <section id="navbar-section">
    <div id="home-page">
    <div id="navbar">
    <ul name="navbar-ul">
        <li id="logo-name" Golden Bug></li>
        <li><img src="<c:url value="/resources/images/goldenBug.png"/>" height="95" width="95" id="shop-logo" ></li>
        <li id="nav-main"><a href="<c:url value="/home"/>">Home</a></li>
        <li id="nav-categories"><a href="<c:url value="/home#categories"/>">Categories</a></li>
        <li id="nav-products"><a href="<c:url value="/home#all-products"/>">Products</a></li>
        <li id="nav-delivery"><a href="<c:url value="/home#delivery"/>">Delivery</a></li>
        <li id="nav-contacts"><a href="<c:url value="/home#contacts"/>">Contacts</a></li>
        <li id="nav-cart">
            <a href="<c:url value="/cart"/>">
    <c:choose>
        <c:when test="${cart_size gt 0}">

            Cart (<span class="color-green"><c:out value="${cart_size}"/></span>)
        </c:when>
        <c:otherwise>
            Cart (<c:out value="${cart_size}"/>)
        </c:otherwise>
    </c:choose>
    </a> </li>
    </ul>
    </div>
    </div>
    </section>
    </body>
    </html>
</compress:html>
