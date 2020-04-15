<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="Category ${category.title}">
        <title>Category ${category.title} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="category">
            <div >
                <div>
                    <div  class="centre-span">
                        <b>
                            <span>Category</span>
                            <span>"${category.title}"</span>
                        </b>
                    </div>
                </div>
                <div>
                    <table>
                        <tr>
                            <th>Name:</th>
                            <td>${category.title}</td>
                        </tr>
                        <tr>
                            <th>URl:</th>
                            <td>
                                <a href="<c:url value="/category/${category.url}"/>"
                                   title="Go to category ${category.title}">${category.url}</a>
                            </td>
                        </tr>
                        <tr>
                            <th>Description:</th>
                            <td>${category.description}</td>
                        </tr>
                        <tr>
                            <th>Image:</th>
                            <td>${category.photo.title}
                                <br><img src="<c:url value="/resources/img/${category.photo.smallUrl}"/>"
                                         width="75px" height="75px" alt="${category.title}">
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/category/edit/${category.id}"/>"
                                   title="Edit category ${category.title}">
                                    <button class="btn btn-success" type="submit">Edit</button>
                                </a>
                                <a href="<c:url value="/admin/category/delete/${category.id}"/>"
                                   title="Delete category ${category.title}">
                                    <button class="btn btn-danger" type="submit">Delete</button>
                                </a>
                                <a href="<c:url value="/admin/category/all"/>" title="Back to category list">
                                    <button  class="btn btn-success" type="submit">Back</button>
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

