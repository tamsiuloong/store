<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>WEB01</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</head>

	<body>
		<div class="container-fluid">

	
			<jsp:include page="include/header.jsp"></jsp:include>

			<div class="container-fluid">
				<div class="main_con">
					${msg}
				</div>
			</div>

		</div>
		<jsp:include page="include/footer.jsp"></jsp:include>

	</body>

</html>