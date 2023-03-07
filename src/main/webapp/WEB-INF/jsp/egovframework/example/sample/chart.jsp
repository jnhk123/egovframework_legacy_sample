<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차트</title>

<script src="<c:url value='/resources/js/websocket.js'/>"></script>


</head>
<body>

</body>

<script>
	var sock = new SockJS("http://localhost:8080/ws/socket");

	sock.onopen = function() {
		sock.send('1111');
	};

	sock.onmessage = function(e) {
		console.log('message', e.data);
		sock.close();
	};

	sock.onclose = function() {
		console.log('close');
	};
</script>
</html>