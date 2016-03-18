<html>
<head>
<title>View Defect</title>
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

<h3>Defect Tracker</h3>
<h4>View Defect</h4>

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
  <tr>
    <td><%=request.getAttribute("defectId")%></td>
    <td><%=request.getAttribute("product")%></td>
    <td><%=request.getAttribute("submitter")%></td>
    <td><%=request.getAttribute("submitDate")%></td>
    <td><%=request.getAttribute("title")%></td>
    <td><%=request.getAttribute("description")%></td>
    <td><%=request.getAttribute("dueDate")%></td>
    <td><%=request.getAttribute("priority")%></td>
    <td><%=request.getAttribute("state")%></td>
    <td><%=request.getAttribute("assignee")%></td>
    <td><%=request.getAttribute("solution")%></td>
  </tr>

</table>

<p>DefectId    = <%=request.getAttribute("defectId")%><br></p>
<p>Product     = <%=request.getAttribute("product")%><br></p>
<p>Submitter   = <%=request.getAttribute("submitter")%><br></p>
<p>Submit_Date = <%=request.getAttribute("submitDate")%><br></p>
<p>Title       = <%=request.getAttribute("title")%><br></p>
<p>Description = <%=request.getAttribute("description")%><br></p>
<p>Due_Date    = <%=request.getAttribute("dueDate")%><br></p>
<p>Priority    = <%=request.getAttribute("priority")%><br></p>
<p>State       = <%=request.getAttribute("state")%></p>
<p>Assignee    = <%=request.getAttribute("assignee")%><br></p>
<p>Solution    = <%=request.getAttribute("solution")%><br></p>

<footer>
	<a href="/" title="Back to the Home page"><b>HOME</b></a> | <a href="#" title="To the top of this page"><b>BACK TO TOP</b></a>
</footer>

</body>
</html>