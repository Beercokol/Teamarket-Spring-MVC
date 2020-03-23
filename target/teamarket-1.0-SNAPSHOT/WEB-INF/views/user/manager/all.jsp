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
        <meta name="title" content="Employee ">
        <title>Employee </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/manager_navbar.jsp"/>
    <div>
        <section id="persons">
            <div>
                <div>
                    <div >
                        <b>
                            <span>Employee</span>
                            <c:if test="${fn:length(users) eq 0}"><span> - is empty!</span></c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${fn:length(users) gt 0}">
                    <div>
                        <table>
                            <tr>
                                <th>Role</th>
                                <th>Name</th>
                                <th >Phone</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.role eq admin_role}">
                                                <b><span>${user.role.description}</span></b>
                                            </c:when>
                                            <c:when test="${user.role eq manager_role}">
                                                <span>${user.role.description}</span>
                                            </c:when>
                                            <c:otherwise>${user.role.description}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.name}</td>
                                    <td>${user.phone}</td>
                                    <td>
                                        <a href="<c:url value="/managers/user/view/${user.id}"/>"
                                           title="Watch info ${user.username}">
                                            <button  type="submit">Watch</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>