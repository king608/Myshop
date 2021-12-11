<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品列表</title>
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


	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="row" style="width: 1210px; margin: 0 auto;">
		<div class="col-md-12">
			<ol class="breadcrumb">
				<li><a href="#">首页</a></li>
			</ol>
		</div>

		<c:forEach items="${pageBean.list }" var="product">
			<div class="col-md-2" style="height: 250px">
				<a href="${pageContext.request.contextPath}/product?method=productInfo&cid=${cid }&pid=${product.pid}&currentPage=${pageBean.currentPage}"> 
				<img src="${pageContext.request.contextPath}/${product.pimage}" width="170" height="170" style="display: inline-block;">
				</a>
				<p>
					<a href="${pageContext.request.contextPath}/product?method=productInfo&cid=${cid}& }pid=${product.pid}&currentPage=${pageBean.currentPage}" style='color: green'>${product.pname}</a>
				</p>
				<p>
					<font color="#FF0000">商城价：&yen;${product.shop_price}</font>
				</p>
			</div>
		</c:forEach>
		

	</div>

	<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
			
			<!-- 上一页 -->		
			<c:if test="${pageBean.currentPage==1 }">
				<li class="disabled"><a href="javascript:" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
			</c:if>
			<c:if test="${pageBean.currentPage!=1 }">
				<li c><a href="${pageContext.request.contextPath}/product?cid=${cid }&method=productListCid&currentPage=${pageBean.currentPage-1}" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
			</c:if>
			
			
			<c:forEach begin="1" end="${pageBean.totalPage}" var="page">
				<c:if test="${page==pageBean.currentPage }">
					<li class="active"><a href="javascript:;">${page }</a></li>
				</c:if>
				<c:if test="${page!=pageBean.currentPage}">
					<li><a href="${pageContext.request.contextPath}/product?cid=${cid }&method=productListCid&currentPage=${page}">${page }</a></li>
				</c:if>
			
			</c:forEach>
			
			<!-- 下一页 -->
			<c:if test="${pageBean.currentPage==pageBean.totalPage }">
				<li class="disabled"><a href="javascript:;" aria-label="Next">
				 <span aria-hidden="true">&raquo;</span></a></li>
			</c:if>
			<c:if test="${pageBean.currentPage!=pageBean.totalPage }">
				<li>
				<a href="${pageContext.request.contextPath}/product?cid=${cid }&method=productListCid&currentPage=${pageBean.currentPage+1}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span></a></li>
			</c:if>
			
			
		</ul>
	</div>
	<!-- 分页结束 -->

	
	<!-- 引入browse_list.jsp -->
	<jsp:include page="/browse_list.jsp"></jsp:include>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>