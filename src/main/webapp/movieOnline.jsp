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
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.min.css" media="screen" />
<script src="js/jquery-1.12.4.min.js"></script>
<script src="js2/jquery.datetimepicker.full.min.js"></script>
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
<!--  <script>
	$(document).ready(function(){
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-TW" ] );
		$( "#ptime" ).datepicker({
			changeMonth: true,
			changeYear: true,
			changeTime: true,
			yearRange: "-100:+0",
			dateFormat: "yy-mm-dd"
		});
	});
</script>-->
<script>
	$(document).ready(function(){
		$.datetimepicker.setLocale('zh-TW');
		$("#ptime").datetimepicker({
			format:'Y-m-d',   
			inline:false,
			closeOnDateSelect:true,
			onShow:function(ct,$i){
				$i.prop("readonly",false);
			},
			onClose:function(ct,$i){
				$i.prop("readonly",true);
			},
			timepicker:false
		});
		
		$('#ptime').on('click', function () {
		    $('#ptime').datetimepicker('show');
		});
		
		init();
		
	});
	
	function init(){
		timeShow();
		$('#ptime').change(function(){
			timeShow();
			getSchedule();
		});
		$('#roomid').change(function(){
			getSchedule();
		});
	};
	
	function timeShow(){
		if($('#ptime').val()!=''){
			$('#tr_time_show').show();
		}else{
			$('#tr_time_show').hide();
		}
	};
	
	function getSchedule(){
		loadSchedule();
	}
	
	function loadSchedule(){
		searchMovieSchedule($('#roomid').val(),$('#ptime').val());
	}
	
	function createSchedule(movieSchedule){
		//var movieSchedule = searchMovieSchedule($('#roomid').val(),$('#ptime').val());
		scheduleReset();
		for(var i=0;i<movieSchedule.length;i++){
			$tr = $('<tr><td></td><td></td><td></td></tr>');
			$tr.css("background-color","lightblue");
			$('td:eq(0)',$tr).html(movieSchedule[i].movie_name);
			$('td:eq(1)',$tr).html(movieSchedule[i].stime);
			$('td:eq(2)',$tr).html(movieSchedule[i].etime);
			$('#schedule').append($tr);
		}
		
	};
	
	function scheduleReset(){
		$('#schedule').css("text-align","center");
		$('#schedule').html("");
		$titleTr = $('<tr><td>電影名稱</td><td>開始時間</td><td>結束時間</td></tr>');
		$titleTr.css("background-color","lightyellow");
		$('#schedule').append($titleTr);
	};
	
	function searchMovieSchedule(room,date){
//  		var movieSchedule = [{"movie_name":"測試1","stime":"16:59","etime":"19:48"},{"movie_name":"測試2","stime":"21:30","etime":"00:48"}];
 		var movieSchedule;
		$.ajax({
			url: "movieSchedule.search",
			dataType : "json",
			method : "POST",
			async:true,
			data:{"room":room,"date":date},
			success:function(returnData){
				//movieSchedule = returnData;
				createSchedule(returnData);
			},
			beforeSend:function(){
                $('#loading').show();
                $('#schedule').hide();
            },
            complete:function(){
                $('#loading').hide();
                $('#schedule').show();
            }
		});
		
		//return movieSchedule;
	};
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
<li><a href="OrderTicket.do">OrderTicket</a></li>
</ul>
</div>

<div id="content">
<div class="right"> 

<h2><a href="#">License and terms of use</a></h2>
<form id="form" action="movieOnline.do" method="post">
<table>
	<tr>
		<td>播放廳院:</td>
		<td align="left">
			<select name="roomid" id="roomid">
				<c:forEach items="${roomList}" var="room">
					<option value="${room.roomid}">
						<c:out value="${room.roomid}"/>
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td id="test">
			播放時間:
		</td>
		<td>
			<input type="text" name="ptime" id="ptime" value="${param.ptime}" size="16" />
<!-- 			<input type="text" name="ptime" id="ptime" size="16"/> -->
		</td>
	</tr>
	<tr id="tr_time_show">
		<td colspan="2">
			<table id="schedule" width="100%">
			</table>
			<img id="loading" src="images/loading.gif" />
		</td>
	</tr>
	<tr>
		<td style="width:60px;">電影名稱:</td>
		<td align="left">
			<select name="movie">
				<c:forEach items="${movieList}" var="movie">
					<option value="${movie.movie_id}">
						<c:out value="${movie.movie_name}"/>
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">

			<input type="button" class="" id="btn_a" value="addMovieSchedule"/>
			<input type="button" class="" id="btn_o" value="movieOnline"/>
		</td>
	</tr>
	
	<tr>
		<td>電影代號:</td>
		<td align="left">
			<select name="playid">
				<c:forEach items="${playList}" var="playList">
					<option value="${playList.playid}">
						<c:out value="${playList.playid}.${playList.movieName}_${playList.ptime}"/>
					</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="hidden" id="action" name="action"/>
			
			<input type="button" class="" id="btn_c" value="createSaleSeat"/>
		</td>
	</tr>
</table>
</form>
<br></br>
<br></br>
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