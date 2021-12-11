<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js">
</script>   
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="#" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
		<c:if test="${empty user }">
			<li><a href="login.jsp">登录</a></li>
			<li><a href="register.jsp">注册</a></li>
		</c:if>
		<c:if test="${!empty user }">
			<li><a href="javascript:;">欢迎您！ ${user.username }</a></li>
			<li><a href="${pageContext.request.contextPath }/user?method=logout">退出</a></li>
		</c:if>
			
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/order?method=userOrder">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath }/">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul id="categoryShow" class="nav navbar-nav">
					<!-- <li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>
					<li><a href="#">电脑办公</a></li> -->
					
				</ul>
				<form class="navbar-form navbar-right" action="${pageContext.request.contextPath }/product?method=search" method="post" role="search">
					<div class="form-group">
						<input id="search" name="search" type="text" class="form-control" placeholder="Search" onkeyup="searchWord(this)">
						<div id="showDiv" style="display:none; position:absolute;z-index:1000;background:#fff; width:179px;border:1px solid #ccc;">
							
						</div>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<!-- 异步加载分类 -->
				<script type="text/javascript">
				
				//加载分类
					$.post(
						"${pageContext.request.contextPath }/product?method=category",
						function(data){
						var content="";
						/* <li><a href="#">电脑办公</a></li> */
						for(var i=0;i<data.length;i++){
							content+=" <li><a href='${pageContext.request.contextPath }/product?method=productListCid&cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
						}
							$("#categoryShow").html(content);
						},
						"json"
					
					);
					
					
					//加载搜索框
					function searchWord(obj){
						var word = $(obj).val();
						
						var content="";
						$.post(
							"${pageContext.request.contextPath }/product?method=searchProduct",
							{"word":word},
							function(data){
								for(var i =0;i<data.length;i++){
									content+="<div style='padding:5px;cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"+data[i]+"</div>";
								}
									$("#showDiv").html(content);
									$("#showDiv").css("display","block");
							},
							"json"
						);
					}
					function overFn(obj){
						$(obj).css("background","#DBEAF9");
					}
					function outFn(obj){
						$(obj).css("background","#fff");
					}
					
					function clickFn(obj){
						$("#search").val($(obj).html());
						$("#showDiv").css("display","none");
					}					
					
				</script>
			</div>
		</div>
	</nav>
</div>