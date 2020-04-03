<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<!DOCTYPE HTML>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Checkout</title>

</head>
<body>
<jsp:include page="/WEB-INF/views/home/navbar.jsp"/>
<div class="bg-im">
    <section >
        <div >
            <div>
                <div id="centr-b">
                    <b>${order.client.name}</b>, Thanks for your order!<br><br>
                    A sales manager will contact you within an hour!<br><br>
                    Order number: <b>${order.number}</b><br><br>
                    We will be glad to see you again!<br><br>
                    Phone for contacting us:<br><br>
                    +8-908-134-05-99<br><br>
                    Respectfully,  <b>Golden Bug</b>.<br>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/product/in_cart.jsp"/>
        </div>

    </section>
</div>
<jsp:include page="/WEB-INF/views/home/end.jsp"/>
</body>
</html>
