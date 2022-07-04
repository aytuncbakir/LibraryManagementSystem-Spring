<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix= "c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>

<s:url var ="url_logout" value="/logout"/>


<c:if test="${sessionScope.userId == null}">

<%-- user not logged ine guest menu --%>
<s:url var ="url_reg_form" value="/reg_form"/>
<s:url var ="url_home" value="/index"/>
<s:url var ="url_login" value="/index"/>
<a href = "${url_home}">Home</a> | <a href = "${url_login}">Login</a> | <a href = "${url_reg_form}">Register</a> | <a href = "#">About</a> 
</c:if>


<c:if test="${sessionScope.userId != null && sessionScope.userType == 1}">

<%-- Admin logged ine guest menu --%>

<s:url var ="url_uhome" value="/admin/dashboard"/>
<s:url var ="url_ulist" value="/admin/ulist"/>
<s:url var ="url_uform" value="/admin/user_save_form"/>


<a href = "${url_uhome}">Home</a> | <a href = "${url_ulist }">User List</a> |  <a href ="${url_uform}">Add User</a> |<a href = "${url_logout}">Logout</a>

</c:if>


<c:if test="${sessionScope.userId != null && sessionScope.userType == 2}">
<s:url var ="url_uhome" value="/librarian/dashboard"/>
<s:url var ="url_bform" value="/librarian/book_form"/>
<s:url var ="url_blist" value="/librarian/blist"/>
<s:url var ="url_check" value="/librarian/check_restitution"/>
<s:url var ="url_given_books" value="/librarian/check_given_books"/>
<%-- User logged ine guest menu --%>
<a href = "${url_uhome}">Home</a> | <a href = "${url_blist}">Book List</a> | <a href = "${url_given_books}">Given Books List</a> | <a href = "${url_check}">Check Restitution</a> |	<a href = "${url_bform}">Add Book</a> | <a href = "${url_logout}">Logout</a> 
</c:if>

<c:if test="${sessionScope.userId != null && sessionScope.userType == 3}">
<s:url var ="url_uhome" value="/user/dashboard"/>
<s:url var ="url_blist" value="/user/ublist"/>
<%-- User logged ine guest menu --%>
<a href = "${url_uhome}">Home</a> | <a href = "${url_blist}">Library Facilities</a> | <a href = "${url_logout}">Logout</a>
</c:if>

	

