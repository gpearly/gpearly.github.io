<%@page import="defecttracker.Defect" %>
<%@page import="defecttracker.Product" %>
<%@page import="defecttracker.Employee" %>
<%@page import="defecttracker.Priority" %>
<%@page import="defecttracker.State" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<!doctype html>
<html>
<head>
	<title>Update Bug</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif' rel='stylesheet' type='text/css'>
</head>
<body>

<h2>    
    <img src="/images/bugTrackerLeft.png" alt="Bug Tracker" style="width:80;height:80px;">
    Update <span class="bugLarge">Bug</span>
    <img src="/images/bugTrackerRight.png" alt="Bug Tracker" style="width:80;height:80px;">
</h2>

<form action="DefectTrackerServlet" method="POST">

<input type="hidden" name="menuOption" value="updateDefect3"/>

<%
// retrieve the defect to update
List<Defect> updateList = new ArrayList<Defect>();
updateList = (ArrayList<Defect>)request.getAttribute("updateList");
Defect uDefect = (Defect)updateList.get(0);

//retrieve the list of open products
List<Product> products = new ArrayList<Product>();
products = (ArrayList<Product>)request.getAttribute("openProducts");

//retrieve the list of employees
List<Employee> employees = new ArrayList<Employee>();
employees = (ArrayList<Employee>)request.getAttribute("employees");
%>
<table>
	<tr>
		<td>Bug Id:</td>
		<td><%= uDefect.getDefectId() %></td>
		<td>
			<input name="defectId" type="hidden" value="<%= uDefect.getDefectId() %>"/>
		</td>
	</tr>
    <tr>
        <td>Product:</td>
        <td>
			<select name="product">
				<%for (Product product : products) { 
					if ( product.getName().equals(uDefect.getProduct()) ) { %>
						<option value=<%=product.getName()%> selected><%=product.getName()%></option>
				 	<% } else { %>
						<option value=<%=product.getName()%>><%=product.getName()%></option>
				 	<% } %>
				<% } %>
			</select><br/>
        </td>
    </tr>
    <tr>
        <td>Submitter:</td>
        <td>
			<select name="submitLastName">
				<%for (Employee employee : employees) { 
					if ( employee.getLastName().equals(uDefect.getSubmitter()) ) { %>
						<option value=<%=employee.getLastName()%> selected><%=employee.getLastName()%></option>
				 	<% } else { %>
						<option value=<%=employee.getLastName()%>><%=employee.getLastName()%></option>
					<% } %>
				<% } %>
			</select><br/>
        </td>
    </tr>
    <tr>
        <td>Title:</td>
        <td>
            <textarea name="title" cols="40" rows="1" required>
            <%= uDefect.getTitle() %>
            </textarea>
        </td>
    </tr>
    <tr>
        <td>Description:</td>
        <td>
            <textarea name="description" cols="40" rows="5" required>
            <%= uDefect.getDescription() %>
            </textarea>
        </td>
    </tr>
    <tr>
        <td>Submit Date:</td>
        <td>
            <input type="date" name="submitDate" required value='<%= uDefect.getSubmitDate() %>'>
        </td>
    </tr>
    <tr>
        <td>Due Date:</td>
        <td>
            <input type="date" name="dueDate" required value='<%= uDefect.getDueDate() %>'>
        </td>
    </tr>
    <tr>
        <td>Priority:</td>
        <td>
 			<select name="priority">
				<%for (Priority priority : Priority.values()) { 
					if (priority == uDefect.getPriority()) { %>
						<option value=<%=priority%> selected><%=priority%></option>
					<% } else { %>
						<option value=<%=priority%>><%=priority%></option>
					<% } %>
				<% } %>
			</select><br/>
        </td>
    </tr>
    <tr>
        <td>State:</td>
        <td>
            <select name="state">
	       		<%for (State state : State.values()) { 
	       			if (state == uDefect.getState()) { %>
	       				<option value=<%=state%> selected><%=state%></option>
	       			<% } else { %>
	       				<option value=<%=state%>><%=state%></option>
	       			<% } %>
	       		<% } %>
	       	</select>
        </td>
    </tr>
    <tr>
        <td>Assignee:</td>
        <td>
			<select name="assigneeLastName">
				<%for (Employee employee : employees) { 
	       			if ( employee.getLastName().equals(uDefect.getAssignee()) ) { %>
	       				<option value=<%=employee.getLastName()%> selected><%=employee.getLastName()%></option>
	       			<% } else { %>
	       				<option value=<%=employee.getLastName()%>><%=employee.getLastName()%></option>
	       			<% } %>
	       		<% } %>
			</select>
        </td>
    </tr>
    <tr>
        <td>Solution:</td>
        <td>
            <textarea name="solution" cols="40" rows="5">
            <% if (uDefect.getSolution() != null) { %>
            	<%= uDefect.getSolution() %>
            <% } %>
            </textarea>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td><input type="submit" value="Submit" /></td>
    </tr>
</table>
</form>
</body>
</html>