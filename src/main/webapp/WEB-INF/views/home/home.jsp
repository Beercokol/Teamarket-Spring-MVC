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
    <section  class="sec-home" id="navbar-section">
    <div id="home-page">
    <div id="navbar">
    <ul name="navbar-ul">
        <li id="logo-name" Golden Bug></li>
        <li><img src="<c:url value="/resources/images/goldenBug.jpg"/>" height="95" width="95" id="shop-logo" ></li>
        <li id="nav-main"><a href="<c:url value="/home"/>">Home</a></li>
        <li id="nav-categories"><a href="<c:url value="/home#categories"/>">Categories</a></li>
        <li id="nav-products"><a href="<c:url value="/product/all"/>">Products</a></li>
        <li id="nav-delivery"><a href="<c:url value="/home#delivery-section"/>">Delivery</a></li>
        <li id="nav-payments"><a href="<c:url value="/home#payment-section"/>">Payment</a></li>
        <li id="nav-contacts"><a href="<c:url value="/home#contacts-section"/>">Contacts</a></li>
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
    <div class="sec-home" id="logo">
    <h1 id="GB-name">Golgen Bug</h1><br>
    <h2 id="logo-GB">Good tea - good day</h2>
    </div>
    <div id="cat-full">
        <section id="categories" class="sec-home bg-im"  >
            <div>
                <c:set var="categories_length" value="${fn:length(categories)}"/>
                <div >
                    <h3  class="name-logo1">
                        <span >Tea</span>
                        <span> Categories</span>
                        <c:if test="${categories_length eq 0}">
                            <span> - Empty!</span>
                        </c:if>
                    </h3>
                </div>
                <c:if test="${categories_length gt 0}">
                    <div id="cat1-flex">
                        <c:forEach items="${categories}" var="category">
                            <div id="cat-flex" >
                                <div>
                                    <a  href="<c:url value="/category/${category.url}"/>"
                                       title="Go to category <c:out value="${category.title}"/>">
                                        <img src="<c:url value="/resources/images/${category.photo.smallUrl}"/>"
                                             width="150px" height="150px"
                                             alt="<c:out value="${category.title}"/>">
                                        <div class="pr-name">
                                            <c:out value="${category.title}"/>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </c:forEach>
                        <div>

                        </div>
                    </div>
                    <h4 class="name-logo1" id="all-tea-name">
                        <a href="<c:url value="/product/all"/>" title="go to all products">
                            All Tea
                        </a>
                    </h4>
                </c:if>
            </div>
        </section>
    </div>
       <jsp:include page="/WEB-INF/views/product/some_list.jsp"/>

   </section>
    </section>
    <section id="delivery-section">
    <div id="delivery-name">
    <h1 class="sec-home">Delivery Service:</h1>
    </div>
    <div id="delivery-cases">
    <div id="delivery-case-1">
    <img src="<c:url value="/resources/images/taxi1.png"/>" height="200" width="200">
    <div id="dev-text-1">
    <h4>Courier delivery</h4>
    <h4>in Voronezh</h4>
    </div>
    </div>
    <div id="delivery-case-2">
    <img src="<c:url value="/resources/images/delivery-truck1.png"/>" height="200" width="200">
    <div id="dev-text-2">
    <h4>Mail delivery</h4>
    </div>
    </div>
    <div id="delivery-case-3">
    <img src="<c:url value="/resources/images/supermarket-cart-silhouette1.png"/>" height="200" width="200">
    <div id="dev-text-3">
    <h4>Pickup delivery</h4>
    <h4>in Voronezh</h4>
    </div>
    </div>
    </div>
    <h5 class="sec-home" id="h5-d">Cost of courier delivery in Voronezh - 150 rubles</h5>
    </section>
    <section id="payment-section">

    <h1 id="p-h1" class="sec-home">Payment:</h1>
    <div id="payment-div">
    <div id="p-img">
    <img src="<c:url value="/resources/images/tag.png"/>"  height="300" width="270">
    </div>
    <div id=" p-info ">
    <h3> 1) Pay courier.</h3>
    <h3 id=" h2-2 ">2) Pay by mail.</h3>
    <h3 id=" h2-3 ">3) Pay by credit card.</h3>
    </div>
    </div>
    </section>
    <section class="sec-home" id="contacts-section">
    <div id="sect-cont ">

    <h1  class="no-margin" id="contacts-logo">Contacts:</h1>
    <div id="contacts-inf">
    <div id="contacts-text">
    <h4 >Voronezh: university square 1st<br>
    phone number: 8-908-134-05-99<br>
    email: beercokol2@gmail.com</h4>
    </div>
    <div id="contacts-map">
    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d8325.643677777933!2d39.20036833565558!3d51.655649462336505!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x413b2e59b0d8daff%3A0x4e005405b690605a!2sVoronezh%20State%20University!5e0!3m2!1sen!2sru!4v1582979899059!5m2!1sen!2sru "
    width="600 " height="450 " frameborder="0 " style="border:0; " allowfullscreen=" "></iframe>
    </div>
    </div>
    </div>
    </section>
    <section class="sec-home" id="end-page">
        <div id="end-page-div">
            <h2 class="no-margin">Golden Bug</h2><br>
            <h3 id="h3-end-page">Good tea - good  day</h3>
        </div>
    </section>
    </body>


    </html>
</compress:html>