package defecttracker;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefectTrackerServlet extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product> products;
		List<Employee> employees;
		List<Defect> defects = DbUtils.findAllDefects();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  // MM is month; mm is minutes

		RequestDispatcher dispatcher;
			
		Defect defect = new Defect();
		
		int defectId = -1;
		String menuOption = null;

/*		DefectTrackerServlet handles the following menuOptions:
		1.  insertDefect1   - user chose to insert defect; gather DB info and send to insertDefect.jsp
		2.  insertDefect2   - data is retrieved from insertDefect.jsp and a new defect is inserted into the DB.  Confirmation sent to user.
		3.  updateDefect1   - user chose to update defect;  send request to getDefectId.jsp to get defectId
		4.  updateDefect2   - defectId is retrieved from getDefectId.jsp; get current defect info from DB and send to updateDefect.jsp
		5.  updateDefect3   - defect info is retrieved from updateDefect.jsp and the defect is updated in the DB.  Confirmation sent to user.
		6.  viewDefect1     - user chose to view a defect; send request to getDefectId.jsp to get defectId
		7.  viewDefect2     - defectId is retrieved from getDefectId.jsp; get defect info from DB and send to viewDefects.jsp
		8.  viewAllDefects1 - user chose to view all defects;  get info for all defects and send to viewDefects.jsp
*/				
		menuOption = request.getParameter("menuOption");						
		
/*		
	    ----------------------------------------------------------------------------------------------------------
			                                    	INSERT BUG SEQUENCE

		Caller					Called					Data
		----------------------------------------------------------------------------------------------------------
		index.html				DefectTrackerServlet	insertDefect1
		DefectTrackerServlet	insertDefect.jsp		openProducts, employees
		insertDefect.jsp        DefectTrackerServlet	insertDefect2, product, submitLastName, title, description, dueDate, priority, state, assigneeLastName, solution
		DefectTrackerServlet	confirmation.jsp		userAction, defect
		confirmation.jsp		index.html				none
		----------------------------------------------------------------------------------------------------------		
*/
		if (menuOption.equals("insertDefect1")) {
	
			// create a list of open products since defects cannot be written against closed products
			List<Product> openProducts = new ArrayList<Product>(); 
			products = DbUtils.findAllProducts();
			for (Product product : products) {
				if (product.getStatus() == ProductStatus.OPEN) {
					openProducts.add(product);
				}
			}
			request.setAttribute("openProducts", openProducts);
			
			// send the list of employees to the jsp so that the defect can be assigned if desired
			employees = DbUtils.findAllEmployees();
			request.setAttribute("employees", employees);
			
			// forward to insertDefects.jsp to gather new defect info
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/insertDefect.jsp");
			dispatcher.forward(request, response);
			
		} else if (menuOption.equals("insertDefect2")) {
			
			// build defect object from insertDefect.jsp form data (defectId generated by DbUtils.insertDefect())
			defect.setProduct(request.getParameter("product"));
			defect.setSubmitter(request.getParameter("submitLastName"));
			defect.setSubmitDate(DateUtils.getCurrentDate());
			defect.setTitle(request.getParameter("title"));
			defect.setDescription(request.getParameter("description"));
			
			try {
				java.util.Date date = format.parse(request.getParameter("dueDate"));
				defect.setDueDate(new java.sql.Date(date.getTime()));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			
			defect.setPriority(Priority.valueOf(request.getParameter("priority")));
			defect.setState(State.valueOf(request.getParameter("state")));
			defect.setAssignee(request.getParameter("assigneeLastName"));
			defect.setSolution(request.getParameter("solution"));
			defectId = DbUtils.insertDefect(defect);

			request.setAttribute("userAction", "insert");
			request.setAttribute("defectId", defectId);
			
			// forward to JSP for user confirmation
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/confirmation.jsp");
			dispatcher.forward(request, response);

/*		
		----------------------------------------------------------------------------------------------------------
			                                    	UPDATE BUG SEQUENCE

		Caller					Called					Data
		----------------------------------------------------------------------------------------------------------
		index.html				DefectTrackerServlet	updateDefect1
		DefectTrackerServlet	getUpdateId				none
		getUpdateId				DefectTrackerServlet	updateDefect2, defectId
		DefectTrackerServlet	updateDefect.jsp		openProducts, employees
		updateDefect.jsp        DefectTrackerServlet	updateDefect3, product, subtitle, description, dueDate, priority, state, assigneeLastName, solution
		DefectTrackerServlet	confirmation.jsp		userAction, defect
		confirmation.jsp		index.html				none
		----------------------------------------------------------------------------------------------------------		
*/
		} else if (menuOption.equals("updateDefect1")) {

			// forward to getViewId.jsp to request defectId from user
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/getUpdateId.jsp");
			dispatcher.forward(request, response);
						
		} else if (menuOption.equals("updateDefect2")) {
			
			// find the defect to update in the Db and create a list of one defect
			defectId = Integer.parseInt(request.getParameter("defectId")); 
			defect = DbUtils.findDefect(defectId);
			defects = new ArrayList<Defect>();
			defects.add(defect);
			
			request.setAttribute("updateList", defects);
			request.setAttribute("defectId", defect.getDefectId());
			
			// create a list of open products since not allowed to update closed products
			List<Product> openProducts = new ArrayList<Product>(); 
			products = DbUtils.findAllProducts();
			for (Product product : products) {
				if (product.getStatus() == ProductStatus.OPEN) {
					openProducts.add(product);
				}
			}
			request.setAttribute("openProducts", openProducts);
			
			// send the list of employees to the jsp so that the defect can be reassigned if desired
			employees = DbUtils.findAllEmployees();
			request.setAttribute("employees", employees);
			
			// forward to updateDefects.jsp to update defect info
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/updateDefect.jsp");
			dispatcher.forward(request, response);
			
		} else if (menuOption.equals("updateDefect3")) {

			// build defect object from updateDefect.jsp form data
			// (sans submitDate)
			defect.setDefectId(Integer.parseInt(request.getParameter("defectId")));
			defect.setProduct(request.getParameter("product"));
			defect.setSubmitter(request.getParameter("submitLastName"));			
			defect.setTitle(request.getParameter("title"));
			defect.setDescription(request.getParameter("description"));
			try {
				java.util.Date date = format.parse(request.getParameter("dueDate"));
				defect.setDueDate(new java.sql.Date(date.getTime()));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			defect.setPriority(Priority.valueOf(request.getParameter("priority")));
			defect.setState(State.valueOf(request.getParameter("state")));
			defect.setAssignee(request.getParameter("assigneeLastName"));
			defect.setSolution(request.getParameter("solution"));

			// update the defect in the db
			int updateStatus = DbUtils.updateDefect(defect);
			
			if (updateStatus == 1) {
				defectId = defect.getDefectId();
			} else {
				defectId = -1;
			}
			request.setAttribute("userAction", "update");
			request.setAttribute("defectId", defectId);
						
			// forward to JSP for user confirmation
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/confirmation.jsp");
			dispatcher.forward(request, response);

/*		
	    ----------------------------------------------------------------------------------------------------------
			                                    	VIEW BUG SEQUENCE

		Caller					Called					Data
		----------------------------------------------------------------------------------------------------------
		index.html				DefectTrackerServlet	viewDefect1
		DefectTrackerServlet	getViewId.jsp			none
		getViewId.jsp			DefectTrackerServlet	viewDefect2, defectId
		DefectTrackerServlet	viewDefects.jsp			defectList
		viewDefect.jsp			index.html				none
		----------------------------------------------------------------------------------------------------------		
*/
		} else if (menuOption.equals("viewDefect1")) {
			
			// forward to getViewId.jsp to request defectId from user
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/getViewId.jsp");
			dispatcher.forward(request, response);

		} else if (menuOption.equals("viewDefect2")) {
			
			// find the defect in the Db and create a list of one defect
			defectId = Integer.parseInt(request.getParameter("defectId")); 
			defect = DbUtils.findDefect(defectId);
			defects = new ArrayList<Defect>();
			defects.add(defect);
			
			request.setAttribute("defectList", defects);
			request.setAttribute("isSingleDefect", "true");

			// forward to viewDefects.jsp
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/viewDefects.jsp");
			dispatcher.forward(request, response);

/*		
		----------------------------------------------------------------------------------------------------------
			                                    	VIEW ALL BUG SEQUENCE

		Caller					Called					Data
		----------------------------------------------------------------------------------------------------------
		index.html				DefectTrackerServlet	viewAllDefect1
		DefectTrackerServlet	viewDefects.jsp			defectList
		viewDefect.jsp			index.html				none
		----------------------------------------------------------------------------------------------------------		
*/
		} else if (menuOption.equals("viewAllDefects1")) {

			// find a list of all defects in the DB
			defects = DbUtils.findAllDefects();
			
			request.setAttribute("defectList", defects);
			request.setAttribute("isSingleDefect", "false");

			// forward to viewDefects.jsp
			dispatcher = getServletConfig().getServletContext().getRequestDispatcher("/viewDefects.jsp");
			dispatcher.forward(request, response);			
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

}
