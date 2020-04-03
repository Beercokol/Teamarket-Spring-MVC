<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">

        <title>Authorization </title>
    </head>
    <body >
    <%@include file="/WEB-INF/views/home/navbar.jsp" %>
    <div  class="container-fluid bg-im" id="login-sec">
        <section id="login" >
            <div  class="row login">
                <div  class="col-xs-6 col-xs-offset-4 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xl-4 col-xl-offset-4 text-center" id="login-row">
                    <div  class="text-shadow">
                        <span >Authorization:</span>
                    </div>
                    <c:if test="${param.error ne null}">
                        <div class="alert alert-danger" role="alert">
                            Authorization error!
                        </div>
                    </c:if>
                    <c:if test="${param.logout ne null}">
                        <div   class="alert alert-info" role="alert">
                            You are logged out
                        </div>
                    </c:if>
                    <form class="form-signin" action="<c:url value="/login"/>" method="post">
                        <input id="username" class="form-control" type="text" name="username" autofocus required
                               pattern="[A-Za-z0-9_]{5,50}" minlength="5" maxlength="50"
                               placeholder="Add login,  (A-Z, a-z, 0-9, _)" style="margin-top: 25px">
                        <input id="password" class="form-control" type="password" name="password" required
                               pattern="[A-Za-z0-9]{6,50}" style="margin-top: 25px" minlength="6" maxlength="50"
                               placeholder="Add password, (A-Z, a-z, 0-9)">
                        <button class="btn btn-success" type="submit" style="margin-top: 25px">Log in</button>
                    </form>
                </div>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/home/end.jsp" %>

    </body>
    </html>
</compress:html>