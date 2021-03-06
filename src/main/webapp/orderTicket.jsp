<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="subTitle" value="orderTicket" scope="session"/>
<%-- <c:if test="${loginOK != true }"> --%>
<%-- 	<c:set var="target" value="/OrderTicket.do" scope="session"/> --%>
<%-- 	<c:redirect url="/user/login.jsp"/> --%>
<%-- </c:if> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${subTitle}</title>
<meta http-equiv="Content-Language" content="English" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
</head>
<body>

<div id="wrap">

<div id="header">
<h1><a href="#">M o v i e   T h e a t e r</a></h1>
</div>

<div id="menu">
<ul>
<li><a href="index.jsp">Home</a></li>
<li><a href="movieOnline.do">MovieOnline</a></li>
<li><a href="#">OrderTicket</a></li>
</ul>
</div>

<div id="content">
<div class="right"> 

<h2><a href="#">電影場次查詢</a></h2>
<br /><br />
<br /><br />
<table>
	<tr>
		<th colspan="2">電影場次查詢</th>
	</tr>
	<c:forEach items="${orderTicketList}" var="o">
		<tr>
			<td>
				<c:out value="${o.movie_name}"/>
			</td>
			<td>
				<c:out value="${o.pdate}"/>
			</td>
			<td>
				<c:out value="${o.stime}"/>
			</td>
			<td>
				<c:out value="${o.etime}"/>
			</td>
			<td>
				<c:out value="${o.roomid}"/>
			</td>
		</tr>
	</c:forEach>	
</table>

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
</div>

</body>
</html>