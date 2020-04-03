<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


        <title>Tea</title>
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div >
        <section>
            <div>
                <c:set var="products_length" value="${fn:length(products)}"/>
                <div>
                    <div>
                        <b>
                            <span >Products</span>
                            <c:if test="${products_length eq 0}">
                                <span> - empty!</span><br>
                                <a href="<c:url value="/admin/product/add"/>" title="Add new product">
                                    <button   class="btn btn-success" type="submit">Add</button>
                                </a>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${products_length gt 0}">
                    <div>
                        <table>
                            <tr>
                                <th width="40%">Name</th>
                                <th  width="15%">Category</th>
                                <th width="35%">
                                    Action
                                    <a href="<c:url value="/admin/product/add"/>" title="Add new product">
                                        <button  class="btn btn-success" type="submit">Add</button>
                                    </a>
                                    <a href="<c:url value="/admin/product/delete_all"/>" title="delete all products">
                                        <button  class="btn btn-danger" type="submit">Delete All</button>
                                    </a>
                                </th>
                            </tr>
                            <c:forEach items="${products}" var="product">
                                <tr>
                                    <td>
                                        <a href="<c:url value="/product/${product.url}"/>"
                                           title="Go to product ${product.title}">${product.title}</a>
                                    </td>
                                    <td>
                                        <a href="<c:url value="/admin/category/view/${product.category.id}"/>"
                                           title="Watch a category ${product.category.title}">
                                                ${product.category.title}</a>
                                    </td>
                                    <td>
                                        <a href="<c:url value="/admin/product/view/${product.id}"/>"
                                           title="Watch a product ${product.title}">
                                            <button сlass="btn btn-success"
                                                    type="submit">Watch</button>
                                        </a>
                                        <a href="<c:url value="/admin/product/edit/${product.id}"/>"
                                           title="Edit product${product.title}">
                                            <button class="btn btn-success" type="submit">Edit</button>
                                        </a>
                                        <a href="<c:url value="/admin/product/delete/${product.id}"/>"
                                           title="Delete product ${product.title}">
                                            <button  class="btn btn-danger" type="submit">Delete</button>
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