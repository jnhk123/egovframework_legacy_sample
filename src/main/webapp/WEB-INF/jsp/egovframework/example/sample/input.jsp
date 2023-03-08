<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>값 추가</title>
<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script>
	function submit() {
		const numberElement = document.querySelector("#number");
		const value = numberElement.value;
		
		const data = {
			value : Number(value)
		}
		
		$.ajax({
            type : "POST",        
            url : "/socket/chart",
            contentType : "application/json",
            data : JSON.stringify(data),        
            success : function(res){
            	console.log(res);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown){ // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
                alert("통신 실패.")
            }
        });
		
	}
	
	function checkInputValue() {
		const numberElement = document.querySelector("#number");
		const value = numberElement.value;
		
		if(value > 10) {
			numberElement.value = 10;
		}
		
		if(value < 0) {
			numberElement.value = 0;
		}
	}
	
</script>
</head>
<body>

<input type="number" id="number" max="10" min="0" onkeyup="checkInputValue()"> 추가 할 값
<button onclick="submit()">추가</button>

</body>

<script>
	
</script>
</html>