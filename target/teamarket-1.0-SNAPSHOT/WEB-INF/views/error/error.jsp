<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="Error ">
        <title>Error </title>
    </head>
    <body>
    <%@include file="/WEB-INF/views/home/navbar.jsp" %>
    <div >
        <section id="error">
            <div>
                <div>
                    <c:out value="${text_error}"/>
                    <br><br>
                    <span  style="font-size: 15px">
                        <c:out value="${message_error}"/>
                    </span>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="/WEB-INF/views/home/end.jsp"/>
    </body>
    </html>
</compress:html>