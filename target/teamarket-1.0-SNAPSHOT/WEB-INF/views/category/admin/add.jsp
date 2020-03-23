<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="New category ">
        <title>New category </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">

    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="category">
            <div>
                <div>
                    <div>
                        <b><span>New </span><span>category</span></b>
                    </div>
                </div>
                <div>
                    <form action="<c:url value="/admin/category/save"/>" method="post" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <th>Name:</th>
                                <td>
                                    <input  type="text" name="title" minlength="5" maxlength="50"
                                           placeholder="Add category name" required>
                                </td>
                            </tr>
                            <tr>
                                <th>URL:</th>
                                <td>
                                    <input  type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                           placeholder=" Add URL,  (a-z, 0-9, _)"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Description:</th>
                                <td>
                                    <textarea  name="description" maxlength="500"
                                              placeholder=" Enter a category description"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Image:</th>
                                <td>
                                    <input  type="text" name="photo_title" required
                                           placeholder="Enter a name for the photo" minlength="5" maxlength="100">
                                    <br><input type="file" name="photo" accept="image/*">
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button  type="submit"
                                            title="Add new category">Add category
                                    </button>
                                    <button  type="reset"
                                            title="Reset ">Reset
                                    </button>
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
