

<%-- define menuOption based on idType (update or view) --%>
<% 
String menuOption = null;
String idType = (String) request.getAttribute("idType");

if (idType.equals("update")) {
	menuOption = "updateDefect2";
} else if (idType.equals("view")) {
	menuOption = "viewDefect2";
} else {
	// do nothing
}
%>

<form action="DefectTrackerServlet" method="POST">

<input type="hidden" name="menuOption" value="${menuOption}"/>

<table>
    <tr>
        <td>Enter <span class="bugSmall">Bug</span> Id:</td>
        <td><input type="text" name="defectId"></td>
    </tr>
</table>
<%@ include file = "/common/resetNextCancelButtons.html" %>
</form>
