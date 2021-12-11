<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js">
</script>   
<!DOCTYPE html>
<!--商品浏览记录-->
<script type="text/javascript">
	$.post(
	
		"${pageContext.request.contextPath }/product?method=browse",
		function(data){
			var content ="";
			for(var i=0;i<data.length;i++){
				content+="<li style='width: 150px; height: 216; float: left; margin: 0 8px 0 0; padding: 0 18px 15px; text-align: center;'>"+
				"<img src='"+data[i].pimage+"' width='130px' height='130px' /> </li>"; 
				//content+="<li>"+"<img src=MyShop/"+data[i].pimage+""+"</li>"
			}
			$("#showImage").html(content);
		},
		"json"
	);
</script>

	<div
		style="width: 1210px; margin: 0 auto; padding: 0 9px; border: 1px solid #ddd; border-top: 2px solid #999; height: 246px;">

		<h4 style="width: 50%; float: left; font: 14px/30px 微软雅黑">浏览记录</h4>
		<div style="width: 50%; float: right; text-align: right;">
			<a href="">more</a>
		</div>
		<div style="clear: both;"></div>

		<div style="overflow: hidden;">

			<ul id="showImage" style="list-style: none;">
				
			</ul>

		</div>
	</div>