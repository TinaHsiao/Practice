<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MovieTheater</title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="js/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function(){
		setInterval(function(){
			$("#img1").prop("src","VideoServlet?time="+Math.random());
			$("#img2").css("visibility","visible");
			$("#img1").css("visibility","hidden");
			$("#img2").prop("src","VideoServlet?time="+Math.random());
			$("#img1").css("visibility","visible");
			$("#img2").css("visibility","hidden");
			
		},200);
	});
</script>
</head>
<body>
	<h1>123456798</h1>
	<img id="img1" width="800" class="video-image" alt="video" src="VideoServlet?rnd=753935" style="visibility: hidden;">
	<img id="img2" width="800" class="video-image" style="display: block; z-index: -1; visibility: visible; margin-top: -284px;" alt="video" src="VideoServlet?rnd=462670">
</body>
</html>