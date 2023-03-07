<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차트</title>


<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
</head>
<body>
	
<script>
var sock = new SockJS("/socket");

sock.onopen = function() {
    sock.send('test');
};

sock.onmessage = function(e) {
    console.log('message', e.data);
    sock.close();
};

sock.onclose = function() {
    console.log('close');
};
</script>






</body>
</html>