<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

 <!-- 제이쿼리 -->
 <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>

</head>

<body>
<div id="root">
	<header>
		<h1>게시판 글읽기</h1>
	</header>

	<hr/>

	<nav>
		처음화면-글쓰기-로그인
	</nav>
	
	<hr/>
	
	<section id="container">
		<form role="form" method="post" autocomplete="off">
			<p>
				<label for="bno">글 번호</label>
				<input type="text" id="bno" name="bno" value="${delete}" readonly="readonly"/>
			</p>
		
			<p>정말로 삭제하시겠습니까?</p>
			<p>
				<button type="submit">예</button>
				<button type="button" id="cancel_btn">아니오</button>
			</p>
		</form>
	</section>

	<footer>
		<p>제작 : ash</p>
	</footer>

</div>

<script>
	//폼을 변수에 저장
	var formObj = $("form[role='form']");
	
	//취소버튼클릭
	$("#cancel_btn").click(function(){
		alert("취소");
		self.location = "/board/read?bno=${delete}"
			   + "&page=${scri.page}"
			   + "&perPageNum=${scri.perPageNum}"
			   + "&searchType=${scri.searchType}"
			   + "&keyword=${scri.keyword}";
	});

</script>


</body>
</html>