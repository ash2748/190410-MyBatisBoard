<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>

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
	<form id='form' action="modify">
		<input type="hidden" name="num" value="${view.num}">
		<table class="table table-bordered text-center">
			<thead>
				<tr>
					<th>이름</th>
					<td><input type='text' name="name" value="${view.name}" style="width:100%; border: 0;" placeholder="이름을 입력해주세요" autofocus="autofocus"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type='text' name="title" value="${view.title}" style="width:100%; border: 0;" placeholder="제목을 입력해주세요"></td> 
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2" style="height:200px;"><textarea name="content" rows="10" id="textarea" placeholder="내용을 입력해주세요"  form='form' style="width:100%; border: 0;">${view.content}</textarea></td>
				</tr>
			</tbody>
		</table>
	</form>
	<a href="intoBoard" class="btn btn-primary btn-xl" style="padding:10px">목록</a>
	<a href="#" id='modify' class="btn btn-primary btn-xl" style="padding:10px">수정완료</a>
</div>

<script>

var modify=document.getElementById("modify");

modify.onclick=function(){
	var name=document.getElementById("name");
	var title=document.getElementById("title");
	var content=document.getElementById("content");
	
/* 	if(name.value==""){
		alert("이름을 입력해주세요.");
		name.focus();
	}
	else if(title.value==""){
		alert("제목을 입력해주세요.");
		title.focus();
	}
	else if(content.value==""){
		alert("내용을 입력해주세요");
		content.focus();
	}
*/	
	$("#form").submit();
}

</script>

</body>
</html>