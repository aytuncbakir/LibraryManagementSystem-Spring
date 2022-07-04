<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType ="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html >
<head>
  <meta http-equiv="Content-Type" content ="text/html; charset="UTF-8">
  <s:url var="url_jqlib" value= "/resources/js/jquery-3.6.0.min.js"/>
  <script src="${url_jqlib}"></script>
  <script type="text/javascript">

  	$(document).ready(function(){

			//alert('JQery is ready');
		$("#id_get_time").click(function(){
				//alert('Button Clicked...');
				$.ajax({

						url:'get_time',
						success: function(data){
								$("#id_time").html(data);
							}

					});

			});

  	  	});

  </script>
   <title>Ajax Test Page - Contact Application</title>
  
</head>

<body >
Ajax Test Page
<br/>
<button id="id_get_time">Get Server Time</button><br/>
<p id="id_time"></p>
</body>
</html>











