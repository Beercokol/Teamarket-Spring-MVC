<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="title" content="${product.title} ">
        <title>${product.title} </title>
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">


    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div>
        <section id="product">
            <div>
                <div>
                    <div>
                        <b><span >Product </span><span>"${product.title}"</span></b>
                    </div>
                </div>

                <div>
                    <table >
                        <tr>
                            <th>Name:</th>
                            <td>
                                <a href="<c:url value="/product/${product.url}"/>"
                                   title="Go to product  ${product.title}">
                                        ${product.title}
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>Category:</th>
                            <td>
                                <a href="<c:url value="/admin/category/view/${product.id}"/>"
                                   title="Watch category ${product.category.title}">
                                        ${product.category.title}
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>Parametres:</th>
                            <td>${product.parameters}</td>
                        </tr>
                        <tr>
                            <th>Description:</th>
                            <td>${product.description}</td>
                        </tr>
                        <tr>
                            <th>Image:</th>
                            <td>
                                    ${product.photo.title}
                                <br><img src="<c:url value="/resources/img/${product.photo.smallUrl}"/>"
                                         width="75px" height="75px" alt="${product.title}">
                                <c:if test="${product.photo.smallUrl ne null}">
                                    <img src="<c:url value="/resources/img/${product.photo.longUrl}"/>"
                                         width="100px" height="100px" alt="${product.title}">
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>Price:</th>
                            <td>${product.price}</td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/product/edit/${product.id}"/>"
                                   title="Edit product ${product.title}">
                                    <button  type="submit">Edit</button>
                                </a>
                                <a href="<c:url value="/admin/product/delete/${product.id}"/>"
                                   title="Delete product${product.title}">
                                    <button  type="submit">Delete</button>
                                </a>
                                <a href="<c:url value="/admin/product/all"/>" title="Back to the product list">
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
</html>
