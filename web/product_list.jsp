<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		
			<jsp:include page="include/header.jsp"></jsp:include>


		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
				</ol>
			</div>
			
			<c:forEach var="p" items="${page.list}">
			<div class="col-md-2">
				<a href="product.do?method=getProductInfo&pid=${p.pid}">
					<img src="${p.pimage}" width="168" height="170" style="display: inline-block;">
				</a>
				<p ><a href="product.do?method=getProductInfo&pid=${p.pid}" style='color:green'><span style="width:168px">${fn:substring(p.pname, 0, 14)}</span></a></p>
				<p><font color="#FF0000">商城价：&yen;${p.shop_price}</font></p>
			</div>
			</c:forEach>
			

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<li class="disabled"><a href="product.do?method=getProductListByCid&cid=${param.cid}&currPage=${page.prePage}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<c:forEach var="pageNo" begin="1" end="${page.totalPage}">
					<li <c:if test="${page.currPage==pageNo}"> class="active" </c:if> ><a href="product.do?method=getProductListByCid&cid=${param.cid}&currPage=${pageNo}">${pageNo}</a></li>
				</c:forEach>
				
				<li>
					<a href="product.do?method=getProductListByCid&cid=${param.cid}&currPage=${page.nextPage}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;"><img src="products/1/cs10001.jpg" width="130px" height="130px" /></li>
				</ul>

			</div>
		</div>
		
		<jsp:include page="include/footer.jsp"></jsp:include>

	</body>

</html>