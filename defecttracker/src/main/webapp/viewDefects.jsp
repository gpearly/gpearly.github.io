<%@page import="defecttracker.Defect" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<html>
<head>
	<title>Bug List</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif' rel='stylesheet' type='text/css'>
	<style>
	table, th, td {
	    border: 1px solid black;
    	border-collapse: collapse;
	}
	th, td {
    	padding: 5px;
	}
	</style>
</head>
<body>

<h2>    
    <img src="/images/bugTrackerLeft.png" alt="Bug Tracker" style="width:80;height:80px;">
    <span class="bugSmall">Bugs</span> List
    <img src="/images/bugTrackerRight.png" alt="Bug Tracker" style="width:80;height:80px;">
</h2>

<form action="index.html">

<table style="width:100%">
	<tr>
    	<th>DefectID</th>
    	<th>Product</th>		
    	<th>Submitter</th>
    	<th>Submitted</th>
    	<th>Title</th>
    	<th>Description</th>
    	<th>Due</th>
    	<th>Priority</th>
    	<th>State</th>
    	<th>Assignee</th>
		<th>Solution</th>
	</tr>

<%
// retrieve the list of defects to be viewed
List<Defect> defects = new ArrayList<Defect>();
defects = (ArrayList<Defect>)request.getAttribute("defectList");
 
// output defects by table row
for (Defect defect : defects) {
%>
	<tr>
		<td><%=defect.getDefectId()%></td>
		<td><%=defect.getProduct()%></td>
		<td><%=defect.getSubmitter()%></td>
		<td><%=defect.getSubmitDate()%></td>
		<td><%=defect.getTitle()%></td>
		<td><%=defect.getDescription()%></td>
		<td><%=defect.getDueDate()%></td>
		<td><%=defect.getPriority()%></td>
		<td><%=defect.getState()%></td>
		<td><%=defect.getAssignee()%></td>
		<td><%=defect.getSolution()%></td>
	</tr>
<% } %>
</table>
<table>
    <tr>
        <td><input type="submit" value="Ok" /></td>
    </tr>
</table>
</form>
</body>
</html>