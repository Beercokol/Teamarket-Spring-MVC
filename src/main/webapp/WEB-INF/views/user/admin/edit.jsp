<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="${user.name}">
        <title>${user.name} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="user">
            <div >
                <div >
                    <div>
                        <b><span >${user.name}</span></b>
                    </div>
                </div>
                <div>
                    <form action="<c:url value="/admin/user/update"/>" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <table class="table">
                            <tr>
                                <th>Имя:</th>
                                <td>
                                    <input type="text" name="name" minlength="2" maxlength="50"
                                           placeholder=" Add name" value="${user.name}" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Role:</th>
                                <td>
                                    <select  name="role" title="User role">
                                        <option value="${user.role.description}">${user.role.description}</option>
                                        <c:forEach items="${roles}" var="role">
                                            <c:if test="${role ne user.role}">
                                                <option value="${role.description}">${role.description}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Login:</th>
                                <td>
                                    <input type="text" name="username" pattern="[A-Za-z0-9_]{5,50}"
                                           placeholder=" Add login (A-Z, a-z, 0-9, _)"
                                           value="${user.username}" minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Password:</th>
                                <td>
                                    <input type="text" name="password" pattern="[A-Za-z0-9]{6,50}"
                                           placeholder=" Add password (A-Z, a-z, 0-9)"
                                           value="${user.password}" minlength="6" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Email:</th>
                                <td>
                                    <input  type="email" name="email" pattern="[A-Za-z0-9_.@]{5,50}"
                                           placeholder=" Add email (A-Z, a-z, 0-9, _, ., @)"
                                           value="${user.email}" minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Phone:</th>
                                <td>
                                    <input  type="text" name="phone"
                                           placeholder=" Add phone" value="${user.phone}" required>
                                </td>
                            </tr>
                            <tr>
                                <th>VK:</th>
                                <td>
                                    <input  type="text" name="vkontakte" pattern="[a-z0-9_/.]{5,50}"
                                           placeholder=" Add VK address (a-z, 0-9, _, /, .)"
                                           value="${user.vkontakte}" minlength="5" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <th>Facebook:</th>
                                <td>
                                    <input  type="text" name="facebook" pattern="[a-z0-9_/.]{5,50}"
                                           placeholder=" Add Facebook,  (a-z, 0-9, _, /, .)"
                                           value="${user.facebook}" minlength="5" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <th>Skype:</th>
                                <td>
                                    <input  type="text" name="skype" pattern="[A-Za-z0-9_.]{5,50}"
                                           placeholder=" Add login Skype,  (A-Z, a-z, 0-9, _, .)"
                                           value="${user.skype}" minlength="5" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <th>Description:</th>
                                <td>
                                    <textarea  placeholder=" Enter employee description"
                                              name="description" maxlength="250">${user.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button  class="btn btn-success" type="submit"
                                            title="Update user information">Save</button>
                                    <button class="btn btn-danger"  type="reset"
                                            title="Reset Entered Data">Reset</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </section>
    </div>
    </body>
    </html>
</compress:html>