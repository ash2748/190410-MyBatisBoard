<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
	<form role="form" method="post" autocomplete="off">
		<p>
			<label for="userId">아이디</label>
			<input type="text" id="userId" name="userId" value="${member.userId}"/>
		
			<button type="button" class="idCheck">아이디 확인</button>
		</p>
		
		<p class="result">
			<span class="msg">아이디를 확인해주세요.</span>
		</p>
		
		<p>
			<label for="userPass">비밀번호</label>
			<input type="password" id="userPass" name="userPass"/>
		</p>
		
		<p>
			<label for="userName">닉네임</label>
			<input type="text" id="userName" name="userName"/>
		</p>
		
		<p>
			<button type="submit" id="submit" disabled="disabled">회원가입</button>
		</p>
		<p>
			<a href="/">처음으로</a>
		</p>
	</form>

<script>
$(".idCheck").click(function(){
	var query = {userId : $("#userId").val()};
	alert(query.userId);
	
	$.ajax({
		url : "/member/idCheck",
		type : "post",
		data : query,
		success : function(data){
			
			if(data == 1){
				$(".result .msg").text("사용 불가");
				$(".result .msg").attr("style", "color:#f00");
				$("#submit").attr("disabled", "disabled");
			}else{
				$(".result .msg").text("사용 가능");
				$(".result .msg").attr("style", "color:#00f");
				$("#submit").removeAttr("disabled");
			}
		}
	}); //ajax end
});

$("#userId").keyup(function(){
	$(".result .msg").text("아이디를 확인해주세요");
	$(".result .msg").attr("style", "color:#000");
	
	$("#submit").attr("disabled","disabled");
});

</script>
</body>
</html>