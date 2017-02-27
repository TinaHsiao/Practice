<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" media="screen" />
<title>Insert title here</title>
</head>
<body>
<center>
<form action="login.do" method="post">
<table>
	<tr>
		<th colspan="2"><h3>會員登入</h3></th>
	</tr>
	<tr>
		<td></td>
		<td><span class="error">${ErrorMsg.memberId} ${ErrorMsg.password}</span></td>
	</tr>
	<tr>
		<th>帳號:</th>	
		<td><input type="text" name="memberId" size="15" value="${param.memberId}"/></td>
	</tr>
	<tr>
		<th>密碼:</th>	
		<td><input type="password" name="password" size="15" /></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="submit" value="登入"/>
			<a href="register.jsp"><input type="button" value="註冊" /></a>
		</td>
	</tr>
</table>
</form>
</center>

</body>
</html>