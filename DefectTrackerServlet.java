package defecttracker;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefectTrackerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int defectId = -1;
		String userChoice = null;
		
		// get user choice
		try {
			// read value from HTML form
//			defectId = Integer.parseInt(request.getParameter("defectId"));						
			userChoice = request.getParameter("userChoice");						
		} catch (Exception e) {
			e.printStackTrace();
		}		

		// handle user choice
		if (userChoice.equals("insert")) {
			
		} else if (userChoice.equals("update")) {
			
		} else if (userChoice.equals("view")) {
			
			// retrieve the defectId input by user
			defectId = Integer.parseInt(request.getParameter("viewdefectId"));
			
			// find the defect in the Db
			Defect defect = DbUtils.findDefect(defectId);
			
			// set attributes to defect info 
			request.setAttribute("defect", defect);
			request.setAttribute("defectId", defect.getDefectId());
			request.setAttribute("product", defect.getProduct());
			request.setAttribute("submitter", defect.getSubmitter());
			request.setAttribute("submitDate", defect.getSubmitDate());
			request.setAttribute("title", defect.getTitle());
			request.setAttribute("description", defect.getDescription());
			request.setAttribute("dueDate", defect.getDueDate());
			request.setAttribute("priority", defect.getPriority());
			request.setAttribute("state", defect.getState());
			request.setAttribute("assignee", defect.getAssignee());
			request.setAttribute("solution", defect.getSolution());

			// Forward to viewDefect.jsp
			RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/viewDefect.jsp");
			dispatcher.forward(request, response);

		} else if (userChoice.equals("viewAll")) {
			
		}
		
		// create new defect
		Defect defect = new Defect();
		defect.setProduct("Product_1");
		defect.setSubmitter("wood");
		defect.setSubmitDate(DateUtils.getCurrentDate());
		defect.setTitle("This is a new defect");
		defect.setDescription("Blah, blah, blah");
		defect.setDueDate(DateUtils.getCurrentDate());
		defect.setPriority(Priority.LOW);
		defect.setState(State.ASSIGNED);
//		defect.setDefectId(DbUtils.insertDefect(defect));
		
		// update defect
		defect.setDescription("Yep, yep, yep");
//		DbUtils.updateDefect(defect);
		
		// build product string for jsp display
		List<Product> plist = DbUtils.findAllProducts();
		StringBuilder productStr = new StringBuilder();
		for (Product product : plist) {
			productStr.append(product.toString() + "<br>");
		}

		// build employee string for jsp display
		List<Employee> elist = DbUtils.findAllEmployees();
		StringBuilder employeeStr = new StringBuilder();
		for (Employee employee : elist) {
			employeeStr.append(employee.toString() + "<br>");
		}

		// build defect string for jsp display
		List<Defect> dlist = DbUtils.findAllDefects();
		StringBuilder defectStr = new StringBuilder();
		for (Defect def : dlist) {
			defectStr.append(def.toString() + "<br>");
		}

//		Defect defect6 = DbUtils.findDefect(6);
//		Defect defect10 = DbUtils.findDefect(10);
		
//		request.setAttribute("message", productStr.toString());
//		request.setAttribute("message", employeeStr.toString());
		request.setAttribute("message", productStr.toString() + "<br>" + employeeStr.toString() + "<br>" + defectStr.toString());
//		request.setAttribute("message", defect6 + "<br>" + defect10);
//		request.setAttribute("message", "New Defect " + defect.getDefectId() + " inserted and updated <br>");
//		request.setAttribute("message", "Find Defect " + defectId + "<br>");
//		request.setAttribute("message", "User choice = " + userChoice + "<br>");
		// request.setAttribute("message", DbUtils.findAllProducts());
		// request.setAttribute("message", defectTracker.submitDefect(defect));

		// Forward to JSP
		RequestDispatcher dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/defectTracker.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

}
