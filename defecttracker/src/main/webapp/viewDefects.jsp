<%-- page imports --%>
<%@page import="defecttracker.Defect" %>
<%@page import="java.util.*" %>

<%-- retrieve the list of defects to be viewed --%>
<% 
String isSingleDefect = (String) request.getAttribute("isSingleDefect");

List<Defect> defectList = new ArrayList<Defect>();
defectList = (ArrayList<Defect>) request.getAttribute("defectList");
%>


<h4>Overview of All Bugs</h4>

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
