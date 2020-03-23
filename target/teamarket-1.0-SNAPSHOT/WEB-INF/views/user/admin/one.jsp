<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="${user.name} | ${user.role.description} ">
        <title>${user.name} | ${user.role.description} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="product">
            <div >
                <div >
                    <div ><b><span>${user.name}</span></b></div>
                </div>
                <div>
                    <table >
                        <tr>
                            <th>Name:</th>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <th>Role:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${user.role eq admin_role}">
                                        <span >${user.role.description}</span>
                                    </c:when>
                                    <c:when test="${user.role eq manager_role}">
                                        <span >${user.role.description}</span>
                                    </c:when>
                                    <c:otherwise>${user.role.description}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Login:</th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th>Password:</th>
                            <td>${user.password}</td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td>
                                <a href="mailto:${user.email}" title="Email" target="_blank">${user.email}</a>
                            </td>
                        </tr>
                        <tr>
                            <th>Phone:</th>
                            <td>${user.phone}</td>
                        </tr>
                        <c:if test="${not empty user.vkontakte}">
                            <tr>
                                <th>Vk:</th>
                                <td>
                                    <a href="https://${user.vkontakte}" title="VK"
                                       target="_blank">${user.vkontakte}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty user.facebook}">
                            <tr>
                                <th>Facebook:</th>
                                <td>
                                    <a href="https://${user.facebook}" title="Facebook"
                                       target="_blank">${user.facebook}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty user.skype}">
                            <tr>
                                <th>Skype:</th>
                                <td>
                                    <a href="skype:${user.skype}?call" title="Skype"
                                       target="_blank">${user.skype}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty user.description}">
                            <tr>
                                <th>Description:</th>
                                <td>${user.description}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/user/edit/${user.id}"/>"
                                   title="Edit info about ${user.username}">
                                    <button  type="submit">Edit</button>
                                </a>
                                <a href="<c:url value="/admin/user/delete/${user.id}"/>"
                                   title="Delete information about ${user.username}">
                                    <button  type="submit">Delete</button>
                                </a>
                                <a href="<c:url value="/admin/user/all"/>" title="Back to user list">
                                    <button  type="submit">Back</button>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>
