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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


        <title>Cart</title>
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
    <div >
        <section  class="bg-im" id="cart">
            <div >
                <c:set var="sale_positions_length" value="${fn:length(sale_positions)}"/>
                <div  class="sec-home">
                    <div id="cart-l" >
                        <b>
                            <span  class="sec-home" id="cart-logo1">Cart:</span>
                            <c:if test="${sale_positions_length eq 0}">
                                <span  class="sec-home"  id="cart-logo1"> - Empty!</span>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${sale_positions_length gt 0}">
                    <jsp:include page="/WEB-INF/views/product/in_cart.jsp"/>
                    <div class="inp-flex">
                        <form action="checkout" method="post">
                            <div>
                                <input class="input-1" type="text" name="user_name" placeholder=" Add your name"
                                       minlength="2" maxlength="50" required autofocus>
                            </div>
                            <div>
                                <input class="input-1" type="text" name="user_phone" placeholder="Add telephone number"
                                       required>
                            </div>
                            <div>
                                <input class="input-1" type="email" name="user_email" placeholder=" Add Email"
                                       minlength="5" maxlength="50">
                            </div>
                            <div id="mor">
                                <input class="btn btn-success" type="submit"  value="make order" width="203px">
                            </div>
                        </form>
                    </div>
                    <div id="btn-cart">

                        <div id="btn-flex">
                            <div>
                            <a href="<c:url value="/product/all"/>">
                                <button class="btn btn-success"> Shopping</button>
                            </a>
                            </div>
                            &nbsp;&nbsp;&nbsp;<div>
                            <a href="<c:url value="/cart/clear"/>">
                                <button class="btn btn-success">Empty </button>
                            </a>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>
        <section  class="sec-home"id="end-page">
            <div id="end-page-div">
                <h2 class="no-margin">Golden Bug</h2><br>
                <h3 id="h3-end-page">Good tea - good  day</h3>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>