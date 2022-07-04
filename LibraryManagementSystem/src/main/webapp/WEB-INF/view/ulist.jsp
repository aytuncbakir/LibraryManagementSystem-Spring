<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType ="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content ="text/html; charset="UTF-8">
   <title>User List - Contact Application</title>
   <s:url var="url_css" value="/resources/css/style.css"/>
  <link href= "${url_css}" rel="stylesheet" type="text/css"/>
   <s:url var="url_jqlib" value= "/resources/js/jquery-3.6.0.min.js"/>
  <script src="${url_jqlib}"></script>
  <script>
		function changeStatus(uid, sts){

				// alert(userId + status);
				$.ajax({

					url: 'change_status',
					data: {userId: uid, status: sts},
					success: function (data){
							alert(data);
						}
					});

			}

  </script>
  
  
   <script>
		function changeType(uid, typ){
		
				$.ajax({

					url: 'change_type',
					data: {userId: uid, type: typ},
					success: function (data){
							alert(data);
						}
					});
			}

  </script>
  
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
 		<h3>User List</h3>
 		
 		<c:if test="${param.act eq 'sv'}">
 			<p class="success"> User is succesfully saved</p>
 		</c:if>
 		
 		<c:if test="${param.act eq 'del'}">
			<p class="success"> User deleted Successfully! </p>
		</c:if>
 		
 		<c:if test="${param.act eq 'ed'}">
			<p class="success"> User edited Successfully! </p>
		</c:if>
		
		<form action="<s:url value="/admin/user_search"/>">
			<input type="text" name="freeText" value=""${param.freeText} placeholder="Enter text to search">
			<button>Search</button>
		</form>
		<br/>
		
	<form action="<s:url value="/admin/bulk_user_delete"/>">
 		<button>Delete Selected Records</button><br/><br/>
 		<table border="1" align="center" cellpadding="3">
 			
 			<tr>
 			
 			  	<th>Select</th>
 				<th>Id</th>
 				<th>Name</th>
 				<th>Surname</th>
 				<th>Username</th>
 				<th>Password</th>
 				<th>Phone</th>
 				<th>Address</th>
 				<th>Email</th>
 				<th>Type</th>
 				<th>Status</th>
 				<th>Action</th>
 				
 			</tr>
 			
 			<c:if test="${empty userList}">
	 			<tr>
	 				<td colspan="8" class="error">No Records Present</td>
	 			</tr>
 			</c:if>
 			
 			<c:forEach var="user" items="${userList}" varStatus="st">
 				<tr>
	 				<td align=center><input type="checkbox" name="user_id" value="${user.userId}"/></td>
	 				<td>${user.userId}</td>
	 				<td>${user.name}</td>
	 				<td>${user.surname}</td>
	 				<td>${user.username}</td>
	 				<td>${user.password}</td>
	 				<td>${user.phone}</td>
	 				<td>${user.address}</td>
	 				<td>${user.email}</td>
	 				
	 				
	 				<td>
	 					<select id="id_${user.userId}_${user.userId}"  onchange="changeType(${user.userId},$(this).val())">
	 						<option value="1">Admin</option>
	 						<option value="2">Librarian</option>
	 						<option value="3">Member</option>
	 					</select>
	 					<script>
							$('#id_${user.userId}_${user.userId}').val(${user.type});
	 					</script>
	 				
	 				</td>
	 				
	 				
	 				<td>
	 					<select id="id_${user.userId}"  onchange="changeStatus(${user.userId},$(this).val())">
	 						<option value="1">Active</option>
	 						<option value="2">Blocked</option>
	 					</select>
	 					<script>
							$('#id_${user.userId}').val(${user.status});
	 					</script>
	 				
	 				</td>
	 				
	 				
	 				<s:url var="url_del" value="/admin/del_user" >
	 					<s:param name="user_id" value="${user.userId}"/>
	 				</s:url>
	 				
	 				<s:url var="url_edit" value="/admin/edit_user" >
	 					<s:param name="user_id" value="${user.userId}"/>
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
