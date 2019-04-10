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
</style>

</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">테스트게시판</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link" href="#">테스트</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">테스트</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

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
	</div>
</body>
</html>