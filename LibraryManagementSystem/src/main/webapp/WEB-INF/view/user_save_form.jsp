<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content=" text/html; charset=UTF-8">
		<title>User SAve Form - Library Management</title>
		<s:url var="url_css" value="/resources/css/style.css" />
		<link href="${url_css}" rel="stylesheet" type="text/css"/>
		
		
	</head>
	<s:url var="url_bg" value="/resources/images/bg.png" />
	<body background="${url_bg}">
		
		<table border="1" width="80%" align="center">
		
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
				<td height="350px" valign="top" align="center">
					<%-- Page Content Area --%>
					<h3> User SAve Form </h3>
					<c:if test="${err!= null}">
						<p class="error"> ${err} </p>
					</c:if>
					
					<c:if test="${param.act eq 'lo'}">
						<p class="success"> Logout Successfully! </p>
					</c:if>
					
					<s:url var="url_csave" value="/admin/save_user"></s:url>
					<f:form action="${url_csave}" modelAttribute="command">
						
						<table border="1">
						
						
							<tr>
								<td>Name</td>
								<td><f:input path="name"/></td>
							</tr>
							
							<tr>
								<td>Surname</td>
								<td><f:input path="surname"/></td>
							</tr>
							
							<tr>
								<td>Username</td>
								<td><f:input path="username"/></td>
							</tr>
						
							<tr>
								<td>Password</td>
								<td><f:input path="password"/></td>
							</tr>
							
							<tr>
								<td>Phone</td>
								<td><f:input path="phone"/></td>
							</tr>
							
							<tr>
								<td>Address</td>
								<td><f:input path="address"/></td>
							</tr> 
						
							<tr>
								<td>Email</td>
								<td><f:input path="email"/></td>
							</tr>
							
							
							<tr>
								<td>Type</td>
								<td><f:input path="type"/></td>
							</tr>
							
							<tr>
								<td>Status</td>
								<td><f:input path="status"/></td>
							</tr>
							
							<tr>
							
								<td colspan="2" align="right" >
									<button>save</button> 
									
								</td>
							</tr>
						
						</table>
					
					</f:form>
					
					
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