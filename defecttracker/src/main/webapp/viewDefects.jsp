<%-- page imports --%>
<%@page import="defecttracker.Defect" %>
<%@page import="java.util.*" %>

<%-- retrieve the list of defects to be viewed --%>
<% 
String isSingleDefect = (String) request.getAttribute("isSingleDefect");

List<Defect> defectList = new ArrayList<Defect>();
defectList = (ArrayList<Defect>) request.getAttribute("defectList");
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>View Bugs</title>

    <%@ include file = "/common/bootstrap.html" %>
</head>
<body>
	<%-- include header --%>
	<%@ include file = "/common/Header.jsp"%>
	
	<div class="container theme-showcase" role="main">
		<form action="index.html">
		
		<% if (isSingleDefect.equals("true")) { %>
			<%-- view single defect --%>
			<% 
				Defect singleDefect = defectList.get(0);
				session.setAttribute("defect", singleDefect);
			%>
			<%@ include file = "/singleDefect.jsp" %>
		<% } else { %>
			<%-- overview of all defects --%>
			<% session.setAttribute("defects", defectList); %>
			<%@ include file = "/allDefects.jsp" %>
		<% } %>
		
		</form>
	</div>
</body>
</html>