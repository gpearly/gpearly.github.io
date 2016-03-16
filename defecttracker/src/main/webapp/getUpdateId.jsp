

<form action="DefectTrackerServlet" method="POST">

<input type="hidden" name="menuOption" value="updateDefect2"/>

<table>
    <tr>
        <td>Enter <span class="bugSmall">Bug</span> Id:</td>
        <td><input type="text" name="defectId"></td>
    </tr>
</table>
<%@ include file = "/common/resetNextCancelButtons.html" %>
</form>
