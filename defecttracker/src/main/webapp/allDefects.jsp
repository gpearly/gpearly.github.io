<%-- imports --%>
<%@ page import="java.util.*" %>
<%@ page import="defecttracker.*" %>

<%-- grab defect list from session --%>
<%
  List<Defect> defects = (ArrayList<Defect>)session.getAttribute("defects");
%>

<h4>Overview of All Bugs</h4>

<table style="width:100%">

  <tr>
    <th>Bug Id</th>
    <th>Title</th>
    <th>Product</th>
    <th>Priority</th>
    <th>State</th>
  </tr>
  
  <%-- dynamically-sized overview table of defects --%>
  <% for (Defect defect : defects) { %>
  <tr>
      <td><%= defect.getDefectId() %></td>
      <td><%= defect.getTitle() %></td>
      <td><%= defect.getProduct() %></td>
      <td><%= defect.getPriority() %></td>
      <td><%= defect.getState() %></td>
  </tr>
  <% } %>
</table>
