<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		
		<script type="text/javascript">
		
		$(function(){
			//给所有的数量输入框绑定失去焦点事件
			$("[name='quantity']").blur(function(){
				//获取pid，count
				var count = this.value;
				var pid =  $(this).attr("pid");
				//ajax请求
				$.ajax({
					type:"get",
					url:"cart.do",
					data:"method=updateCount&count="+count+"&pid="+pid,
					success:function(data){
						var subTotal = data.split("&&&")[0];//获取小计
						var totalPrice = data.split("&&&")[1];//获取总金额
						//alert(subTotal);
						//修改页面数据
						$("#totalPrice").empty().append("￥"+totalPrice+"元");
						
						//修改当前购物项的小计
						$("#subtotal"+pid).empty().append("￥"+subTotal);
					}
				})
			})
			
		})
		</script>
		
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="css/style.css" type="text/css" />
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
				/* position:relative;
	 float:left; */
			}
			
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>

		
			<jsp:include page="include/header.jsp"></jsp:include>


		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<c:set var="totalPrice" value="0"/>
							<c:forEach var="cart" items="${cart}">
							  <c:set var="totalPrice" value="${totalPrice+cart.value.subTotal}"/>
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${cart.value.pimage }" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank"> ${cart.value.pname }</a>
								</td>
								<td width="20%">
									￥${cart.value.shop_price }
								</td>
								<td width="10%">
									<input type="text" name="quantity" pid="${cart.value.pid}" value="${cart.value.count}" maxlength="4" size="10">
								</td>
								<td width="15%">
									<span class="subtotal" id="subtotal${cart.value.pid}">￥${cart.value.subTotal}</span>
								</td>
								<td>
									<a href="cart.do?method=delete&pid=${cart.value.pid}" class="delete">删除</a>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;">596</em>&nbsp; 商品金额: <strong id="totalPrice" style="color:#ff6600;">￥${totalPrice}元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="cart.do?method=clear" id="clear" class="clear">清空购物车</a>
					<a href="order_info.htm">
						<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
					</a>
				</div>
			</div>

		</div>

		<jsp:include page="include/footer.jsp"></jsp:include>

	</body>

</html>