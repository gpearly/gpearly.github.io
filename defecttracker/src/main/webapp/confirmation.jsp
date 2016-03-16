<%@page import="defecttracker.Defect" %>


<h3><span class="bugText2">Bug</span>Tracker Confirmation</h3>

<%
int defectId = (Integer) request.getAttribute("defectId");
String userAction = (String) request.getAttribute("userAction");

if (defectId == -1) { %>
	<h3>Unable to <%=userAction%> <span class="bugSmall">bug</span>.</h3>
<% } else { %>
	<h3>Successfully <%=userAction%>ed <span class="bugText2">bug</span> number <span class="bugSmall"><%=defectId%></span>.</h3>
<% } %>

<form action="index.html">
<table>
    <tr>
        <td><input type="submit" value="Ok" /></td>
    </tr>
</table>
</form>
