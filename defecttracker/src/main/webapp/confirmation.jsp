<%@page import="defecttracker.Defect" %>

<!doctype html>
<html>
<head>
	<title>Bug Confirmation</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif' rel='stylesheet' type='text/css'>
</head>
<body>

<h2>    
    <img src="/images/bugTrackerLeft.png" alt="Bug Tracker" style="width:80;height:80px;">
    <span class="bugLarge">Bug</span> Tracker
    <img src="/images/bugTrackerRight.png" alt="Bug Tracker" style="width:80;height:80px;">
</h2>

<h3><span class="bugText2">Bug</span> Tracker Confirmation</h3>

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
</body>
</html>