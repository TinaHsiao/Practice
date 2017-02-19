<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>MovieTheater</title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<script>
	window.onload = function(){init();};
	function init(){
		//alert('YA');
		document.getElementById("btn_a").onclick = function(){
			document.getElementById("action").value = "A";
			submit();
		};
		document.getElementById("btn_c").onclick = function(){
			document.getElementById("action").value = "C";
			submit();
		};
		document.getElementById("btn_o").onclick = function(){
			document.getElementById("action").value = "O";
			submit();
		};
	}
	
	function submit(){
		document.getElementById("form").submit();
	}
</script>
</head>
<body>

<div id="wrap">

<div id="header">
<h1><a href="#">M o v i e   T h e a t e r</a></h1>
</div>

<div id="menu">
<ul>
<li><a href="index.jsp">Home</a></li>
<li><a href="#">MovieOnline</a></li>
<li><a href="orderTicket.jsp">OrderTicket</a></li>
</ul>
</div>

<div id="content">
<div class="right"> 

<h2><a href="#">License and terms of use</a></h2>
<form id="form" action="movieOnline.do" method="post">
	播放時間:<input type="text" name="ptime"/><br/>
	電影代號:
	<select name="movie">
		<c:forEach items="${movieList}" var="movie">
			<option value="${movie.movie_id}">
				<c:out value="${movie.movie_name}"/>
			</option>
		</c:forEach>
	</select>
	<br/>
	播放廳院:<select name="roomid">
		<c:forEach items="${roomList}" var="room">
			<option value="${room.roomid}">
				<c:out value="${room.roomid}"/>
			</option>
		</c:forEach>
	</select>
	<br/>
	<input type="hidden" id="action" name="action"></input>
</form>

<input type="button" class="" id="btn_a" value="addMovieSchedule"></input>
<input type="button" class="" id="btn_c" value="createSaleSeat"></input>
<input type="button" class="" id="btn_o" value="movieOnline"></input>
<br>
<br>
<span style="color:red;font-size:20px">
	<c:choose>
		<c:when test='${"A" eq action}'>
			&nbsp;新增電影行程成功!
		</c:when>
		<c:when test='${"C" eq action}'>
			&nbsp;座位產生成功!	
		</c:when>
		<c:when test='${"O" eq action}'>
			&nbsp;電影上線成功!
		</c:when>
	
	</c:choose>

</span>
</div>

<div class="left"> 

<h2>Categories :</h2>
<ul>
<li><a href="#">World Politics</a></li> 
<li><a href="#">Europe Sport</a></li> 
<li><a href="#">Networking</a></li> 
<li><a href="#">Nature - Africa</a></li>
<li><a href="#">SuperCool</a></li> 
<li><a href="#">Last Category</a></li>
</ul>

<h2>Archives</h2>
<ul>
<li><a href="#">January 2007</a></li> 
<li><a href="#">February 2007</a></li> 
<li><a href="#">March 2007</a></li> 
<li><a href="#">April 2007</a></li>
<li><a href="#">May 2007</a></li> 
<li><a href="#">June 2007</a></li> 
<li><a href="#">July 2007</a></li> 
<li><a href="#">August 2007</a></li> 
<li><a href="#">September 2007</a></li>
<li><a href="#">October 2007</a></li>
<li><a href="#">November 2007</a></li>
<li><a href="#">December 2007</a></li>

</ul>

</div>

<div style="clear: both;"> </div>

</div>

</div>

<div id="footer">
Designed by <a href="http://www.free-css-templates.com/">Free CSS Templates</a>, Thanks to <a href="http://www.openwebdesign.org/">Web Design</a>
</div>

</body>
</html>