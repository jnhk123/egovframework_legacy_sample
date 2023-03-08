<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>차트</title>

<script src="<c:url value='/resources/js/websocket.js'/>"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

</head>
<body>

	<figure class="highcharts-figure">
		<div id="container"></div>
		<p class="highcharts-description">인생은 꿀빤다면 좋겠지만 그렇게 쉽지 않았다.</p>
	</figure>

</body>

<script>
	const chart = Highcharts.chart('container', {
		title : {
			text : 'Logarithmic axis demo'
		},
	
		xAxis : {
			tickInterval : 1,
			type : 'logarithmic',
			accessibility : {
				rangeDescription : 'Range: 1 to 10'
			}
		},
	
		yAxis : {
			type : 'logarithmic',
			minorTickInterval : 0.1,
			accessibility : {
				rangeDescription : 'Range: 0.1 to 1000'
			}
		},
	
		tooltip : {
			headerFormat : '<b>{series.name}</b><br />',
			pointFormat : '{point.y}'
		},
	
		series : [ 
			{
				name : '그냥 값',
				data : [1, 2, 4],
				pointStart : 1
			} 
		]
	});

	var sock = new SockJS("http://localhost:8080/ws/socket");

	sock.onopen = function() {
		sock.send('{"code" : 1}');
	};

	sock.onmessage = function(e) {
		
		const array = JSON.parse(e.data);
		const values = array.map(item => item.value);
		chart.series[0].setData(values);
		
	};

	sock.onclose = function() {
		console.log('close');
	};

	
</script>
</html>