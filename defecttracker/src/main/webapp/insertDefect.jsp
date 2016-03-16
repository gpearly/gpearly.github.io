<%-- page imports --%>
<%@page import="defecttracker.*" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<h2>Create New Bug</h2>
<form action="DefectTrackerServlet" method="POST">

<input type="hidden" name="menuOption" value="insertDefect2"/>

<%
//retrieve the list of open products
List<Product> products = new ArrayList<Product>();
products = (ArrayList<Product>)request.getAttribute("openProducts");

//retrieve the list of employees
List<Employee> employees = new ArrayList<Employee>();
employees = (ArrayList<Employee>)request.getAttribute("employees");
%>
<table>
    <tr>
        <td>Product:</td>
        <td>
			<select name="product">
				<%for (Product product : products) { %>
					<option value=<%=product.getName()%>><%=product.getName()%></option>
				<% } %>
			</select><br/>
        </td>
    </tr>
    <tr>
        <td>Submitter:</td>
        <td>
			<select name="submitLastName">
				<%for (Employee employee : employees) { %>
					<option value=<%=employee.getLastName()%>><%=employee.getLastName()%></option>
				<% } %>
			</select><br/>
        </td>
    </tr>
    <tr>
        <td>Title:</td>
        <td>
            <textarea name="title" cols="40" rows="1" required></textarea>
        </td>
    </tr>
    <tr>
        <td>Description:</td>
        <td>
            <textarea name="description" cols="40" rows="5" required placeholder="Spill the beans here."></textarea>
        </td>
    </tr>
    <tr>
        <td>Due Date:</td>
        <td>
            <input type="date" name="dueDate"required>
        </td>
    </tr>
    <tr>
        <td>Priority:</td>
        <td>
 			<select name="priority">
				<%for (Priority priority : Priority.values()) { %>
					<option value=<%=priority%>><%=priority%></option>
				<% } %>
			</select><br/>
        </td>
    </tr>
    <tr>
        <td>State:</td>
        <td>
            <select name="state">
	       		<%for (State state : State.values()) { %>
		  			<option value=<%=state%>><%=state%></option>
	       		<% } %>
	       	</select>
        </td>
    </tr>
    <tr>
        <td>Assignee:</td>
        <td>
			<select name="assigneeLastName">
				<%for (Employee employee : employees) { %>
					<option value=<%=employee.getLastName()%>><%=employee.getLastName()%></option>
				<% } %>
			</select>
        </td>
    </tr>
    <tr>
        <td>Solution:</td>
        <td>
            <textarea name="solution" cols="40" rows="5" placeholder="Color me impressed if you already fixed your bug."></textarea>
        </td>
    </tr>
</table>
<%@ include file = "/common/resetSubmitCancelButtons.html" %>
</form>