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
 		<h3>Book List</h3>
 		
 		<c:if test="${param.act eq 'sv'}">
 			<p class="success"> Book is succesfully saved</p>
 		</c:if>
 		
 		<c:if test="${param.act eq 'del'}">
			<p class="success"> Book deleted Successfully! </p>
		</c:if>
 		
 		<c:if test="${param.act eq 'ed'}">
			<p class="success"> Book edited Successfully! </p>
		</c:if>
		
	
		
		<form action="<s:url value="/librarian/book_search"/>">
			<input type="text" name="freeText" value=""${param.freeText} placeholder="Enter text to search">
			<button>Search</button>
		</form>
		<br/>
		
	<form action="<s:url value="/librarian/bulk_book_delete"/>">
 		<button>Delete Selected Records</button><br/><br/>
 		<table border="1" align="center" cellpadding="3">
 			
 			<tr>
 			
 			  	<th>Select</th>
 				<th>Id</th>
 				<th>Name</th>
 				<th>Author</th>
 				<th>Isbn</th>
 				<th>Type</th>
 				<th>Total</th>
 				<th>AvaibleTotal</th>
 				<th>Action</th>
 				
 			</tr>
 			
 			<c:if test="${empty bookList}">
	 			<tr>
	 				<td colspan="8" class="error">No Records Present</td>
	 			</tr>
 			</c:if>
 			
 			<c:forEach var="book" items="${bookList}" varStatus="st">
 				<tr>
	 				<td align=center><input type="checkbox" name="book_id" value="${book.bookId}"/></td>
	 				<td>${book.bookId}</td>
	 				<td>${book.name}</td>
	 				<td>${book.author}</td>
	 				<td>${book.isbn}</td>
	 				<td>${book.type}</td>
	 				<td>${book.total }</td>
	 				<td>${book.availableCount}</td>
	 				<s:url var="url_del" value="/librarian/del_book" >
	 					<s:param name="book_id" value="${book.bookId}"/>
	 				</s:url>
	 				
	 				<s:url var="url_edit" value="/librarian/edit_book" >
	 					<s:param name="book_id" value="${book.bookId}"/>
	 				</s:url>
	 				
	 				<td><a href="${url_edit}">Edit</a> |<a href="${url_del}">Delete</a></td>
 				</tr>
 			</c:forEach>
 			
 		</table>
 		
 		</form>
 		
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
