<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Bean.UserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lab05_03</title>
</head>
<body> 
<H1>會員 ${ memberBean.name } 的資料新增成功</H1>
編號: ${ memberBean.memberId }<BR>
地址: ${ memberBean.address }<BR>
生日: ${ memberBean.birthday }<BR>
註冊時間: ${ memberBean.registerDate }<BR>
體重: ${ memberBean.weight }<BR>
<hr>
<%--
	MemberBean mb = (MemberBean)request.getAttribute("memberBean");
	out.println("<H1>會員 " + mb.getName() + "的資料新增成功-1</H1>");
	out.println("編號: " + mb.getMemberId() + "<BR>");
	out.println("地址: " + mb.getAddress() + "<BR>");
	out.println("生日: " + mb.getBirthday() + "<BR>");
	out.println("註冊時間: " + mb.getRegisterDate() + "<BR>");
	out.println("體重: " + mb.getWeight() + "<BR>");
--%>
<hr>
<jsp:useBean id="memberBean" class="Bean.UserBean" scope="request" />
	<h1>會員<jsp:getProperty property="name" name="memberBean" />的資料-3</h1>
	編號:<jsp:getProperty property="memberId" name="memberBean" /><br>
	地址:<jsp:getProperty property="address"  name="memberBean" /><br>
	生日:<jsp:getProperty property="birthday"  name="memberBean"/><br>
	註冊時間:<jsp:getProperty property="registerDate"  name="memberBean"/><br>
	體重:<jsp:getProperty property="weight"  name="memberBean"/><br>

</body>
</html>