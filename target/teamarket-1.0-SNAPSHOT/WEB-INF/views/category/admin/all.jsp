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
        <meta name="title" content="Categories">
        <title>Categories</title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div>
        <section id="categories">
            <div>
                <c:set var="categories_length" value="${fn:length(categories)}"/>
                <div>
                    <div  class="centre-span">
                        <b>
                            <span>Categories</span>
                            <c:if test="${categories_length eq 0}">
                                <span> - is empty!</span><br>
                                <a href="<c:url value="/admin/category/add"/>" title="Add new category">
                                    <button class="btn btn-success"  type="submit">Add</button>
                                </a>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${categories_length gt 0}">
                    <div>
                        <table>
                            <tr>
                                <th>Name</th>
                                <td><b>URL</b></td>
                                <th>
                                    Action
                                    <a href="<c:url value="/admin/category/add"/>" title="Add new category">
                                        <button class="btn btn-success"  type="submit">Add</button>
                                    </a>
                                    <a href="<c:url value="/admin/category/delete_all"/>"
                                       title="Delete all categories">
                                        <button class="btn btn-danger"  type="submit">Delete ALL</button>
                                    </a>
                                </th>
                            </tr>
                            <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td>
                                        <a href="<c:url value="/category/${category.url}"/>"
                                           title="Go to category ${category.title}">${category.title}</a>
                                    </td>
                                    <td>${category.url}</td>
                                    <td>
                                        <a href="<c:url value="/admin/category/view/${category.id}"/>"
                                           title="Watch category ${category.title}">
                                            <button class="btn btn-info" type="submit">Watch</button>
                                        </a>
                                        <a href="<c:url value="/admin/category/edit/${category.id}"/>"
                                           title="Edit category ${category.title}">
                                            <button class="btn btn-success" type="submit">Edit</button>
                                        </a>
                                        <a href="<c:url value="/admin/category/delete/${category.id}"/>"
                                           title="Delete category ${category.title}">
                                            <button class="btn btn-danger" type="submit">Delete</button>
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