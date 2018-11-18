<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>퀴즈</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/menu.jsp" %>

<script>
	function btn_toggle() {
		//alert("버튼1을 누르셨습니다.");
		if($("#commentary").css("display") == "none")
			$("#commentary").show();
		else
			$("#commentary").hide();
	}
	
	function checkAnswer(num) {
		$.ajax({
			url: "checkAnswer",
			type: "GET",
			async : false,
			data: {answer:num},
			error : function(){
				alert('error');
			},
			success : function(msg){
								
				if (msg == 'success')
				{
					alert("정답");
					$(location).attr('href', 'quiz');
				}
				else
					alert("오답");
				
			}
		}
	)};
	



</script>

<p>${dto.domain1} > ${dto.domain2}<p>

    <div> 
    	다음 중 <b>${dto.content}</b>의 결함사례에 해당하는 것은 무엇인가?    
    </div>
    <div>    
    <ul class="example">
    	<li onclick="checkAnswer('1')">
	    	<span class="circled">① </span>${dto.example1}
	    </li>
	    <br>
	    <li onclick="checkAnswer('2')">
	    	<span class="circled">② </span>${dto.example2}
	    </li>
	    <br>
	    <li onclick="checkAnswer('3')">
	    	<span class="circled">③ </span>${dto.example3}
	    </li>
	    <br>
	    <li onclick="checkAnswer('4')">
	    	<span class="circled">④ </span>${dto.example4}
	    </li>
	    <br>
	</ul>
	</div>
	
	<input type="button" id = "btn" onclick="btn_toggle();" value="설명">
	<br>
	
	<div id = "commentary" style="display:none">
		${dto.commentary}
	</div>
        
	
	
    

<script>
	//alert("${msg}");

</script>
</body>
</html>