<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>accountExist</title>
</head>
<body>
	${errorMessage.memberId} 
	${loginOK}
	<br>
	您目前所輸入的帳號是:
	${param.memberId}
	${memberId}
</body>
</html>