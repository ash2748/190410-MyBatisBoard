<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>테스트 게시판</title>

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
  
  .active{
  	border-radius:10px;
  	background-color:pink;
  }
  </style>
</head>
<body>
	<!-- Navigation -->
	<jsp:include page="sub.jsp" flush="false"/>
	
	<!-- board -->
	<div class="container masthead-content">
		<!-- board 리스트 출력 부분 -->
		<table class="table table-bordered text-center">
			<thead>
				<tr>
					<th>이름</th> <th>타이틀</th> <th>조회수</th> <th>작성일자</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.name}</td> 
					<td><a href="contentView?num=${list.num}&page=${pageMaker.cri.page}">${list.title}</a></td> 
					<td>${list.count}</td> 
					<td><fmt:formatDate value="${list.write_date}" pattern="yyyy.MM.dd" /></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- 글쓰기버튼 -->
		<a class="btn btn-primary btn-xl" href="writePage" style="padding:10px">글쓰기</a>
		
		<!-- 검색,페이징 -->
		<div class="box-footer">
		
			<!-- 1)검색 -->
			<div class="form-group">
				<select class="form-control" name="searchType" style="width:10%;">
					<option value="t" <c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
					<option value="n" <c:out value="${scri.searchType eq 'n' ? 'selected' : ''}"/>>이름</option>
					<option value="c" <c:out value="${scri.searchType eq 'c' ? 'selected' : '' }"/>>내용</option>
				</select>
			</div>
			<div class="form-group">
				<div class="input-group">
					<input type="text" class="control" name="keyword" id="keyword" value="${scri.keyword}">
					<span class="input-group-btn">
						<button type="button" class="btn btn-primary btn-flat" id="searchBtn">
							<i class="fa fa-search"></i>검색
						</button>
					</span>
				</div>
			</div>
			
			<!-- 2)페이징 이전버튼 / 페이지번호 / 다음버튼 -->
			<div class="text-center">
				<ul class="pagination">
					<c:if test="${pageMaker.prev}">
						<li><a href="${pageMaker.startPage-1}" class="btn">이전</a></li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
						<li <c:out value="${pageMaker.cri.page == idx ? 'class=active' : '' }"/>>
							<a href="${idx}" class="btn">${idx}</a>							
						</li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<li><a href="${pageMaker.endPage+1}" class="btn">다음</a></li>
					</c:if>
				</ul>
			</div>
			
			<!-- 3)현재페이지값, 페이지별글개수 출력 -->
			<form id="listPageForm">
				<input type="hidden" name="page" value="${pageMaker.cri.page}">
				<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}">
				<input type="hidden" name="searchType" value="${scri.searchType}">
				<input type="hidden" name="keyword" value="${scri.keyword}">
			</form>
		</div>
	</div>

<script>
	//페이징 관련 처리
	$(".pagination li a").on("click",function(event){
		event.preventDefault();
		
		var targetPage = $(this).attr("href"); //페이지 번호 누르면, 누른 번호를 담는다.
		var listPageForm = $("#listPageForm"); //page,perPageNum을 담아둔 form을 가져온다.
		
		listPageForm.find("[name='page']").val(targetPage); //form에서 page의 값에 누른 페이지번호를 넣는다.
		
		listPageForm.attr("action","intoBoard").attr("method","get"); //intoBoard(게시판첫페이지)로 이동속성을 넣고, method는 get으로 바꾼다.
		listPageForm.submit();//전송한다.(페이지번호 누를때마다 변동된 page값이 같이 전송됨)
	});
	
	//검색관련처리
	$("#searchBtn").click(function(){
		
		self.location="intoBoard?page=1&searchType="
						+$("select option:selected").val()
						+"&keyword="
						+encodeURIComponent($('#keyword').val());
	});
	
</script>
</body>
</html>