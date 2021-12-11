<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>订单</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
</head>
<body>
<!-- 引入header.jsp -->
		<jsp:include page="/header.jsp"></jsp:include>
		
		<div style="text-align: center;">
			
			<p style="font-size: 90px"> 订单以确认</p>
			<a href="${pageContext.request.contextPath }/order?method=userOrder">查看详细订单</a>&nbsp;&nbsp;&nbsp;
			<span><a href="${pageContext.request.contextPath }/">继续购物</a></span>
		
		</div>
		

<!-- 引入footer.jsp -->
		<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>