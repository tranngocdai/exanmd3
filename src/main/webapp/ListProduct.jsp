<%--
  Created by IntelliJ IDEA.
  User: Dai
  Date: 08/09/2021
  Time: 11:15 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Product</title>
</head>
<body>
<table>
    <a href="/Product?action=create">Create</a>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Desc</th>
        <th>Category</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${productList}" var="product">
        <tr>
            <td>${product.getProductId()}</td>
            <td>${product.getProductName()}</td>
            <td>${product.getProductPrice()}</td>
            <td>${product.getProductQuantity()}</td>
            <td>${product.getProductColor()}</td>
            <td>${product.getProductDesc()}</td>
            <td>${product.getCategory().getCategoryName()}</td>
            <td>
                <a href="/Product?action=edit&id=${product.getProductId()}">Edit</a>
            </td>
            <td>
                <button onclick="confirmDelete()">Delete</button>
            </td>
            <script>
                function confirmDelete() {
                    let isDelete = confirm("Bạn có chắc chắn muốn xóa không?\n Đây là thao tác không thể khôi phục");
                    if (isDelete) {
                        window.location.href = "/Product?action=delete&id=${product.getProductId()}";
                    }
                }
            </script>

        </tr>
    </c:forEach>
</table>
<form method="post" action="/Product?action=search">
    <input type="text" name="searchName">
    <input type="submit" value="Search">
</form>
<a href="/Product">List</a>
</body>
</html>
