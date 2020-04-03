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
        <meta name="title" content="Персонал">
        <title>Персонал </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div>
        <section id="persons">
            <div>
                <div>
                    <div>
                        <b>
                            <span>Staff</span>
                            <c:if test="${fn:length(users) eq 0}">
                                <span> -is empty!</span>
                                <br>
                                <a href="<c:url value="/admin/user/add"/>" title="Add new employee">
                                    <button class="btn btn-success" type="submit">Add</button>
                                </a>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${fn:length(users) gt 0}">
                    <div>
                        <table>
                            <tr>
                                <th>Role</th>
                                <th>Name</th>
                                <td><b>Phone</b>
                                <td>
                                    <b>Action</b>
                                    <a href="<c:url value="/admin/user/add"/>" title="Add new employee">
                                        <button class="btn btn-success"  type="submit">Add</button>
                                    </a>
                                    <a href="<c:url value="/admin/user/delete_all"/>" title="Delete ALL managers">
                                        <button class="btn btn-danger" type="submit">Delete ALL</button>
                                    </a>
                                </td>
                            </tr>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.role eq admin_role}">
                                                <b><span >${user.role.description}</span></b>
                                            </c:when>
                                            <c:when test="${user.role eq manager_role}">
                                                <span >${user.role.description}</span>
                                            </c:when>
                                            <c:otherwise>${user.role.description}</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.name}</td>
                                    <td >${user.phone}</td>
                                    <td>
                                        <a href="<c:url value="/admin/user/view/${user.id}"/>"
                                           title="View information ${user.name}">
                                            <button class="btn-success"  type="submit">Watch</button>
                                        </a>
                                        <a href="<c:url value="/admin/user/edit/${user.id}"/>"
                                           title="Edit information about ${user.name}">
                                            <button class="btn-success"  type="submit">Edit</button>
                                        </a>
                                        <a href="<c:url value="/admin/user/delete/${user.id}"/>"
                                           title="Delete information about ${user.name}">
                                            <button  class="btn-danger" type="submit">Delete</button>
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