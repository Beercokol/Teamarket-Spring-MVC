<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="Editing category ${category.title} ">
        <title>Editing category ${category.title}</title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="category">
            <div >
                <div>
                    <div>
                        <b>
                            <span>Editing category </span>
                            <span>"${category.title}"</span>
                        </b>
                    </div>
                </div>
                <div>
                    <form action="<c:url value="/admin/category/update"/>" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${category.id}">
                        <table>
                            <tr>
                                <th>Name:</th>
                                <td>
                                    <input type="text" name="title" minlength="5" maxlength="50"
                                           placeholder=" Add category name" value="${category.title}" required>
                                </td>
                            </tr>
                            <tr>
                                <th>URL:</th>
                                <td>
                                    <input type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                           placeholder=" Add URL (a-z, 0-9, _)" value="${category.url}"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Description:</th>
                                <td>
                                <textarea  name="description" maxlength="500"
                                          placeholder=" Add category description">${category.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Image:</th>
                                <td>
                                    <input type="hidden" name="photo_id" value="${category.photo.id}">
                                    <input  type="text" name="photo_title"
                                           placeholder="Add image name" value="${category.photo.title}"
                                           minlength="5" maxlength="100">
                                    <br><input type="file" name="photo" accept="img/*">
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button  class="btn btn-success" type="submit"
                                            title="Update Category Information">Save</button>
                                    <button  class="btn btn-danger" type="reset"
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