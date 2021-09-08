<%--
  Created by IntelliJ IDEA.
  User: Dai
  Date: 08/09/2021
  Time: 11:11 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/Product?action=edit" method="post">
    <table>
        <tr>ID: </tr>
        <tr><input type="hidden" name = "id" value="${product.getProductId()}">${product.getProductId()}</tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="productName" value="${product.getProductName()}"></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="number" name="productPrice" value="${product.getProductPrice()}"></td>
        </tr>
        <tr>
            <td>Quantity</td>
            <td><input type="number" name="productQuantity"  value="${product.getProductQuantity()}"></td>
        </tr>
        <tr>
            <td>Color</td>
            <td><input type="text" name="productColor" value ="${product.getProductColor()}"></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="productDesc" value = "${product.getProductDesc()}"></td>
        </tr>
        <tr>
            <td>Category</td>
            <td>
                <select name="categoryId" >
                    <c:forEach items="${categoryList}" var="category">
                        <option value="${category.getCategoryId()}">${category.getCategoryName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit"></td>
        </tr>
    </table>
</form>

</body>
</html>
