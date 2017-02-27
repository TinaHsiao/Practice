<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" media="screen" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.min.css" media="screen" />
<script src="../js/jquery-1.12.4.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/datepicker-zh-TW.js"></script>
<!-- <script src="../js/moment-with-locales.min.js"></script> -->
<script>
	$(document).ready(function(){
		$.datepicker.setDefaults( $.datepicker.regional[ "zh-TW" ] );
		$( "#birthday" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: "-100:+0",
			dateFormat: "yy-mm-dd"
		});
	});
</script>
<title>Insert title here</title>
</head>
<body onload="javascript:document.insertMemberFormA.mId.focus();" >
	<center>  
		<form name="insertMemberFormA" action="user.do" method="POST">
			<table border="1" >
				<thead>
					<tr bgcolor='tan' >
						<th height="60" colspan="2" align="center">註冊會員</th>
					</tr>
				</thead>
				<tbody >
					<tr bgcolor='tan' >
					    <td width="120" height="40">帳號:</td>
					    <td width="600" height="40" align="left" >
					    	<input id='num' style="text-align:left" name="memberId" value="${param.memberId}" type="text" size="14">
					    	<div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.memberId}</div>
					</tr>
					<tr bgcolor='tan' >
					    <td width="120" height="40">密碼:</td>
					    <td width="600" height="40" align="left" >
					         <input id='num' style="text-align:left" name="password" value="${param.password}" type="password" size="14">
					         <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.password}</div>
					    </td>
					</tr>
					<tr bgcolor='tan' >
					    <td width="120" height="40">姓名:</td>
					    <td width="600" height="40" align="left" >
					         <input name="name" value="${param.name}" type="text" size="20">
					         <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.name}</div>
					    </td>
					</tr>
					<tr bgcolor='tan' >
					    <td width="120" height="40">E-mail:</td>
					    <td width="600" height="40" align="left" >
					         <input name="email" value="${param.email}" type="text" size="54">
					         <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.email}</div>
					    </td>
					</tr>
					<tr bgcolor='tan' >
					    <td width="120" height="40">住址:</td>
					    <td width="600" height="40" align="left" >
					         <input name="address" value="${param.address}" type="text" size="54">
					         <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.address}</div>
					    </td>
					</tr>
					<tr bgcolor='tan' >
					    <td width="120" height="40">手機:</td>
					    <td width="600" height="40" align="left" >
					         <input name="phone" value="${param.phone}" type="text" size="20">
					         <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.phone}</div>
					    </td>
					</tr>
					<tr bgcolor='tan' >
					    <td width="120" height="40">生日:</td>
					    <td width="600" height="40" align="left" >
					    	 <input name="birthday" id="birthday" value="${param.birthday}" type="text" size="14" readonly >
					         <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.birthday}</div>
					    </td>
					</tr>
					<tr bgcolor='tan' >
					    <td height="50" colspan="2" align="center">
					       <input type="submit" value="送出" >
					       <div style="color:#FF0000; font-size:60%; display:inline">${ErrorMsg.exception}</div>
					    </td>
					</tr>
				</tbody>
			</table>
		</form>
	</center>
	<div id="datepicker"></div>
</body>
</html>