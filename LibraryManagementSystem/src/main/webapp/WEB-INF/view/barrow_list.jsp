<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType ="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content ="text/html; charset="UTF-8">
   <title>User Login - Contact Application</title>
   <s:url var="url_css" value="/resources/css/style.css"/>
  <link href= "${url_css}" rel="stylesheet" type="text/css"/>
  
</head>
<s:url var="url_bg" value="/resources/images/bg.png"/>
<body background = "${url_bg}">
 
 <table border="1"  width="80%" align="center">
 
 	<tr>
 		<td height="80px">
 			<%-- Header --%>
 		<jsp:include page="include/header.jsp"/>
 		</td>
 	</tr>
 	
 	<tr>
 		<td height="25px">
 		<%-- Menu --%>
 		<jsp:include page="include/menu.jsp"/>
 		</td>
 	</tr>
 	
 	<tr>
 		<td height="350px" valign="top">
 		<%-- Page Content Area --%>
 		<h3>Barrowed Book List</h3>
 		
 		<c:if test="${param.act eq 'sv'}">
 			<p class="success"> Book is succesfully saved</p>
 		</c:if>
 		
 		<c:if test="${param.act eq 'del'}">
			<p class="success"> Book deleted Successfully! </p>
		</c:if>
 		
 		<c:if test="${param.act eq 'ed'}">
			<p class="success"> Book edited Successfully! </p>
		</c:if>
		
	
 		
 		<table border="1" align="center" cellpadding="3">
 			
 			<tr>
 			
 			  	
 				<th>BarrowId</th>
 				<th>UserID</th>
 				<th>BookId</th>
 				<th>Date</th>
 				<th>Punishment Days</th>
 			
 				
 			</tr>
 			
 			<c:if test="${empty barrowList}">
	 			<tr>
	 				<td colspan="8" class="error">No Records Present</td>
	 			</tr>
 			</c:if>
 			
 			<c:forEach var="barrow" items="${barrowList}" varStatus="st">
 				<tr>
	 				
	 				<td>${barrow.barrowId}</td>
	 				<td>${barrow.userId}</td>
	 				<td>${barrow.bookId}</td>
	 				<td>${barrow.barrowDate}</td>
	 				<td>${barrow.days}</td>
 				</tr>
 			</c:forEach>
 			
 		</table>
 	
 		
 		</td>
 	</tr>
 	
 	<tr>
 		<td height="25px">
 		<%-- Footer --%>
 		<jsp:include page="include/footer.jsp"/>
 		</td>
 	</tr>
 
 </table>
 
</body>
</html>
