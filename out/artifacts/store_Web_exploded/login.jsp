<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
		
		//等整个页面加载完成后
		$(function(){
			
			//给checkImg绑定一个单击事件
			$("#checkImg").click(function(){
				//改变当前图片的src属性
				$(this).attr("src","checkImg.jpg?"+new Date());
			})
		})
			
		</script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<body>
	
			
		<jsp:include page="include/header.jsp"></jsp:include>
	
	
<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('images/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN

				<div>&nbsp;</div>
<form class="form-horizontal" action="user.do?method=login" method="post">
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="username" value="${cookie.remeberMe.value}" name="username" placeholder="请输入用户名">
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="inputPassword3" value="test" name="password" placeholder="请输入密码">
    </div>
  </div>
   <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" id="inputPassword3" value="1111" name="yzmCode" placeholder="请输入验证码">
    </div>
    <div class="col-sm-3">
      <img src="checkImg.jpg" id="checkImg"/>
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" name="autoLogin"> 自动登录
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox" name="remeberMe"> 记住用户名
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="submit"  width="100" value="登录" name="submit" border="0"
    style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    <span style="color:red">${msg }</span>
    </div>
  </div>
</form>
</div>			
	</div>
</div>
</div>	

	<jsp:include page="include/footer.jsp"></jsp:include>
</body></html>