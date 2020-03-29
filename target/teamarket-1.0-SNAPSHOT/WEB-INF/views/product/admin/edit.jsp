<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="Editing product ${product.title}">
        <title>Edit product ${product.title} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section id="product">
            <div >
                <div>
                    <div>
                        <b>
                            <span>Edit product </span>
                            <span>"${product.title}"</span>
                        </b>
                    </div>
                </div>
                <div >
                    <form action="<c:url value="/admin/product/update"/>" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${product.id}">
                        <table>
                            <tr>
                                <th>Name:</th>
                                <td>
                                    <input  type="text" name="title" value="${product.title}"
                                           placeholder="Add product name" minlength="5" maxlength="100" required>
                                </td>
                            </tr>
                            <tr>
                                <th>URL:</th>
                                <td>
                                    <input type="text" name="url" pattern="[a-z0-9_]{5,50}"
                                           placeholder=" Add URL,  (a-z, 0-9, _)" value="${product.url}"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Category:</th>
                                <td>
                                    <select  name="category" title="product category">
                                        <option value="${product.category.id}">${product.category.title}</option>
                                        <c:forEach items="${categories}" var="category">
                                            <c:if test="${category.id ne product.category.id}">
                                                <option value="${category.id}">${category.title}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Parameters:</th>
                                <td>
                                <textarea placeholder="Enter product parameters"
                                          name="parameters" maxlength="500" required>${product.parameters}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Description:</th>
                                <td>
                                <textarea  placeholder="Enter product description"
                                          name="description" maxlength="500">${product.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Image:</th>
                                <td>
                                    <input type="hidden" name="photo_id" value="${product.photo.id}">
                                    <input  type="text" name="photo_title"
                                           placeholder="Enter image name" value="${product.photo.title}"
                                           minlength="5" maxlength="100">
                                    <br>Small: <input type="file" name="small_photo" accept="img/*">
                                    <br>Big: <input type="file" name="big_photo" accept="img/*">
                                </td>
                            </tr>
                            <tr>
                                <th>Price:</th>
                                <td>
                                    <input  type="text" name="price" min="0" max="99999" step="0.01"
                                           placeholder="Enter product price" value="${product.price}" required>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button class="btn btn-success" type="submit"
                                            title="Update product information">Save</button>
                                    <button
                                            class="btn btn-danger" type="reset"
                                            title="Reset Entered Data">Delete</button>
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