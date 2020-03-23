<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<%@include file="/WEB-INF/views/home/navbar.jsp" %>

<body>
<section id="product-list-section">
    <h1 class="name-logo1 sec-home">Our tea:</h1><br>
    <div id="product-list-div">

        <c:if test="${fn:length(products) eq 0}">
            <h2 > - is empty!</h2>
        </c:if>
        <jsp:include page="/WEB-INF/views/product/list.jsp"/>
    </div>
</section>

</body>
</html>
