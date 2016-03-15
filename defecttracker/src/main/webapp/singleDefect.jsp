<%-- imports --%>
<%@ page import="java.util.*" %>
<%@ page import="defecttracker.Defect" %>

<%-- grab defect object from session --%>
<%
  Defect defect = (Defect) session.getAttribute("defect");
%>

<h3><b>Defect #<%= defect.getDefectId() %> : <%= defect.getTitle() %></b></h3>

<h4>Details</h4>

<table style="widtd:100%">

  <tr>
    <td>Product:</td>
    <td><%= defect.getProduct() %></td>
  </tr>
  <tr>
    <td>Priority:</td>
    <td><%= defect.getPriority() %></td>
    <td>State:</td>
    <td><%= defect.getState() %></td>
  </tr>
  <tr>
    <td>Assignee:</td>
    <td><%= defect.getAssignee() %></td>
    <td>Submitter:</td>
    <td><%= defect.getSubmitter() %></td>
  </tr>
  <tr>
    <td>Due Date:</td>
    <td><%= defect.getDueDate() %></td>
    <td>Date Submitted:</td>
    <td><%= defect.getSubmitDate() %></td>
  </tr>
  
</table>

<h4>Description</h4> 
<p><%= defect.getDescription() %></p>

<h4>Solution</h4>
<p><% if (defect.getSolution() != null) {
          defect.getSolution();
      } else { %>
         <i>Solution TBD</i>
   <% } %>
</p>
