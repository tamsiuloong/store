<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="col-md-6"
	style="text-align: center; height: 200px; padding: 0px;">
	<a href="product_info.htm"> <img src="${productList[0].pimage}"
		width="516px" height="200px" style="display: inline-block;">
	</a>
</div>
<c:forEach var="p" items="${productList}" begin="1">
<div class="col-md-2"
	style="text-align: center; height: 200px; padding: 10px 0px;">
	<a href="product_info.htm"> <img src="${p.pimage}"
		width="130" height="130" style="display: inline-block;">
	</a>
	<p>
		<a href="product_info.html" style='color: #666'>${p.pname}</a>
	</p>
	<p>
		<font color="#E4393C" style="font-size: 16px">&yen;${p.shop_price}</font>
	</p>
</div>
</c:forEach>
