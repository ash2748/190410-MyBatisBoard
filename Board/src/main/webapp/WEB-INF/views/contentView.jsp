<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글읽기</title>

<!-- Bootstrap core CSS -->
<link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template -->
<link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/css/one-page-wonder.min.css" rel="stylesheet">
<!-- Bootstrap core JavaScript -->
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
<style>
.table{
	margin-top:150px;
 }
  
th{
	width:20%;
}

.replyDelete,.replyModify{
	background-color:white;
	color:black;
}

</style>

</head>
<body>
	<!-- Navigation -->
	<jsp:include page="sub.jsp" flush="false"/>

	<div class="container masthead-content">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>이름</th>
					<td><span>${view.name}</span></td>
				</tr>
				<tr>
					<th>타이틀</th> 
					<td><span>${view.title}</span></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td><span>${view.count}</span></td> 
				</tr>
				<tr>
					<th>작성일자</th>
					<td><span><fmt:formatDate value="${view.write_date}" pattern="yyyy.MM.dd"/></span></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2" style="height:200px;">${view.content}</td>
				</tr>
			</tbody>
		</table>
		
		<input type="hidden" id='num' value="${view.num}">
		<input type="hidden" id='page' value="${page.page}">
		
		<a href="delete?num=${view.num}" id="delete" class="btn btn-primary btn-xl" style="padding:10px">삭제</a>
		<a href="modifyPage?num=${view.num}" id="modify" class="btn btn-primary btn-xl" style="padding:10px">수정</a>
		<a href="intoBoard?page=${page.page}" class="btn btn-primary btn-xl" style="padding:10px">목록</a>
		
		<!-- 댓글입력 -->
		<div class="container" style="margin-top:20px;">
			<label for="content">Comments</label>
			<div class="input-group">
				<input type="text" class="" name="replyName" placeholder="작성자명"">
				<input type="text" class="form-control" id="content" style="width:30%; height:50%;" name="replyContent" placeholder="댓글을 달아주세요">
				<span class="input-group-btn"><input type="button" id="replySubmit" class="btn btn-primary btn-xl" style="padding:5px;position: absolute;" value="완료"></span>
			</div>
		</div>
		<!-- 댓글출력 -->
		<c:forEach items="${reply}" var="reply">
			<div style="padding:4px;margin-top:20px;">
				<span id="reply_name"><strong>작성자:</strong> ${reply.reply_name}</span>
				<span><strong>작성일자:</strong> ${reply.reply_date}</span>
					
				<div id="div">
					<span>${reply.reply_content}</span>
				</div>
					
				<div>
					<input type="button" id="replyModify" class="btn btn-primary btn-xl replyModify" style="padding:5px;" value="수정">
					<input type="button" id="replyDelete" class="btn btn-primary btn-xl replyDelete" style="padding:5px;" value="삭제">
				</div>
			</div>
		</c:forEach>
	</div>
<script>

$("#replyDelete").on("click",function(){
	var viewNum = ${view.num}
	self.location="replyDelete?num="+viewNum
});

$("#replySubmit").on("click",function(){

	replyName = $("input[name='replyName']").val();
	replyText = $("input[name='replyContent']").val();
	num = $("#num").val();

	alert(replyName);
	alert(replyText);
	alert(num);
	
	$.ajax({
		type:'post',
		url:'/reply',
		data : JSON.stringify({"reply_content":replyText, "reply_name":replyName, "num":num}),
		dataType:'json',
		contentType:'application/json;charset=UTF-8',
		success:function(data){
			alert("성공");
			location.reload(); //임시방편(삭제,수정 후 html짜기);			
		},
		error:function(data){
			alert("실패");
		}
	});	
});
</script>
</body>
</html>