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
        <meta name="title" content="Новый продукт ">
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


        <title>New product </title>
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="product">
            <div >
                <div>
                    <div  class="centre-span">
                        <b>
                            <span>New</span>
                            <span>Product</span>
                        </b>
                    </div>
                </div>
                <div>
                    <form action="<c:url value="/admin/product/save"/>" enctype="multipart/form-data" method="post">
                        <table class="table">
                            <tr>
                                <th>Name:</th>
                                <td>
                                    <input  type="text" name="title" minlength="5"
                                           maxlength="100" placeholder="Еnter product name" required>
                                </td>
                            </tr>
                            <tr>
                                <th>URL:</th>
                                <td>
                                    <input type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                           placeholder=" Enter URL,  (a-z, 0-9, _)"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Category:</th>
                                <td>
                                    <select  name="category" title="Product Category">
                                        <c:forEach items="${categories}" var="category">
                                            <option value="${category.id}">${category.title}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Parameters:</th>
                                <td>
                                    <textarea  name="parameters" maxlength="500"
                                              placeholder="Enter product parameters" required></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Description:</th>
                                <td>
                                    <textarea  name="description" maxlength="500"
                                              placeholder="Enter product description"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Image:</th>
                                <td>
                                    <input  type="text" name="photo_title" required
                                           placeholder="Enter image name" minlength="5" maxlength="100">
                                    <br>
                                    Small: <input type="file" name="small_photo" accept="image/*">
                                    <br>
                                    Big: <input type="file" name="big_photo" accept="image/*">
                                </td>
                            </tr>
                            <tr>
                                <th>Price:</th>
                                <td>
                                    <input  type="number" name="price" required
                                           placeholder="Enter product price" min="0" max="99999" step="0.01">
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button class="btn btn-success" type="submit"
                                            title="Add new product">
                                        Add new product
                                    </button>
                                    <button
                                            class="btn btn-danger" type="reset"
                                            title="reset info ">
                                        Reset
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