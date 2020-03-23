<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
    <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>

<body id="body-admin-navbar">
<div id="admin-navbar-div">
    <section id="admin-navbar-section">
        <ul id="navbar-admin-ul">
            <li id="nav-orderEntities"><a href="<c:url value="/admin/order/all"/>">Orders</a></li>
            <li id="nav-main"><a href="<c:url value="/admin/product/all"/>">Products</a></li>
            <li id="nav-categories"><a href="<c:url value="/admin/category/all"/>">Category</a></li>
            <li id="nav-persons"><a href="<c:url value="/admin/user/all"/>">Employees</a></li>
            <li id="nav-manager"><a href="<c:url value="/managers/order/all"/>">For managers</a></li>
            <li id="auth-user">
                <a href="<c:url value="/admin/user/view/${auth_user.id}"/>">${auth_user.name}</a>
            </li>
            <li id="nav-logout">
                <form class="form-signin" action="<c:url value="/logout"/>" method="post">
                    <input type="hidden" id="username" name="username">
                    <button class="btn-logout btn btn-danger" type="submit">Go out</button>
                </form>
            </li>
        </ul>
    </section>
</div>
<section id="registration">
    <div id="reg-div">
        <h2 id="reg-h2">

        </h2>
    </div>
</section>

</body>

</html>