<%-- page imports --%>
<%@page import="defecttracker.*" %>
<%@page import="java.util.*" %>

<%-- get req attribute pageName --%>
<% String pageName = (String) request.getAttribute("pageName");%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Page Template</title>

    <%@ include file = "/common/bootstrap.html" %>
</head>
<body role="document" data-pinterest-extension-installed="cr1.39.1">
	<%-- include header --%>
	<%@ include file = "/common/header.jsp"%>
	
	<div class="container theme-showcase" role="main">
		<%-- dynamically include desired page --%>
		<jsp:include page="${pageName}" flush="true" />
	</div>

</body>
</html>