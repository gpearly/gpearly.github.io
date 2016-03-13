package defecttracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtils {
	
	private static Logger logger = LoggerUtil.getLogger("DbUtils.txt");

	// column index for Defects database table
	private static final int DEFECT_ID_COL = 1;
	private static final int PRODUCT_COL = 2;
	private static final int SUBMITTER_COL = 3;
	private static final int SUBMIT_DATE_COL = 4;
	private static final int TITLE_COL = 5;
	private static final int DESCRIPTION_COL = 6;
	private static final int DUE_DATE_COL = 7;
	private static final int PRIORITY_COL = 8;
	private static final int STATE_COL = 9;
	private static final int ASSIGNEE_COL = 10;
	private static final int SOLUTION_COL = 11;

	// column index for Products database table
	private static final int NAME_COL = 1;
	private static final int STATUS_COL = 2;

	// column index for Employees database table
	private static final int EMPLOYEE_ID_COL = 1;
	private static final int LAST_NAME_COL = 2;
	private static final int FIRST_NAME_COL = 3;
	private static final int EMAIL_COL = 4;

	// database connection constants
	private static final String CONTEXT = "java:comp/env";
	private static final String DATA_SOURCE = "jdbc/MySQLDS";
		
	/**
	 * Returns the defectId created by the database (auto-incremented).
	 * Returns -1 if unable to insert
	 * @param defect
	 * @return see description
	 */
	public static int insertDefect(Defect defect) {

		Connection conn = null;
		Statement stmt = null;
		int insertStatus = 0;
		int defectId = -1;

		// insert defect into database
		try {
			// prepare connection and statement
			InitialContext ic = new InitialContext();
			Context initialContext = (Context) ic.lookup(CONTEXT);
			DataSource datasource = (DataSource) initialContext.lookup(DATA_SOURCE);
			conn = datasource.getConnection();
			stmt = conn.createStatement();

			// build SQL INSERT string
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO Defects (Product, Submitter, Submit_Date, Title, Description, Due_Date, Priority, State) VALUES (");
			query.append("'" + defect.getProduct() + "',");
			query.append("'" + defect.getSubmitter() + "',");
			query.append("'" + defect.getSubmitDate() + "',");
			query.append("'" + defect.getTitle() + "',");
			query.append("'" + defect.getDescription() + "',");
			query.append("'" + defect.getDueDate() + "',");
			query.append("'" + defect.getPriority() + "',");
			query.append("'" + defect.getState() + "')");
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(defect.toString());
				logger.info(query.toString());
			}

			// perform insert
			insertStatus = stmt.executeUpdate(query.toString(), Statement.RETURN_GENERATED_KEYS);
			
			// retrieve defectId from generated keys
			if (insertStatus == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					defectId = rs.getInt(1);
					if (logger != null && logger.isLoggable(Level.INFO)) {
						logger.info("defectId = " + String.valueOf(defectId));
					}
				}
			}
			
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
			stmt.close();
			conn.close();
		} catch (NamingException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		} catch (SQLException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		}
		return defectId;
	}
	
	/**
	 * Returns 1 if able to update defect; Otherwise, returns 0.
	 * @param defect
	 * @return see description
	 */
	public static int updateDefect(Defect defect) {

		Connection conn = null;
		Statement stmt = null;
		int updateStatus = 0;

		// update defect
		try {
			// prepare connection and statement
			InitialContext ic = new InitialContext();
			Context initialContext = (Context) ic.lookup(CONTEXT);
			DataSource datasource = (DataSource) initialContext.lookup(DATA_SOURCE);
			conn = datasource.getConnection();
			stmt = conn.createStatement();

			// build SQL UPDATE string
			StringBuilder query = new StringBuilder();
			query.append("UPDATE Defects ");
			query.append("SET Product='" + defect.getProduct() + "',");
			query.append("Submitter='" + defect.getSubmitter() + "',");
			query.append("Submit_Date='" + defect.getSubmitDate() + "',");
			query.append("Title='" + defect.getTitle() + "',");
			query.append("Description='" + defect.getDescription() + "',");
			query.append("Due_Date='" + defect.getDueDate() + "',");
			query.append("Priority='" + defect.getPriority() + "',");
			query.append("State='" + defect.getState() + "',");
			query.append("Assignee='" + defect.getAssignee() + "',");
			query.append("Solution='" + defect.getSolution() + "' ");
			query.append("WHERE DefectId='" + defect.getDefectId() + "';");

			// log defect and query
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(defect.toString());
				logger.info(query.toString());
			}

			// perform update
			updateStatus = stmt.executeUpdate(query.toString());
						
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
			stmt.close();
			conn.close();
		} catch (NamingException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		} catch (SQLException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		}
		return updateStatus;
	}
	
    /**
     * Returns defect for the given defect id.  Otherwise, return null.
     * @param defectId defect id
     * @return see description
     */
	public static Defect findDefect(int defectId) {
		
		List<Defect> dlist = findAllDefects();
		
		for (Defect def : dlist) {
			if (defectId == def.getDefectId()) {
				if (logger != null && logger.isLoggable(Level.INFO)) {
					logger.info(def.toString());
				}
				return def;
			}
		}
		return null;		
	}

	/**
	 * Returns a list of all defects.
	 * @return  see description
	 */
	public static List<Defect> findAllDefects() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Defect> defects = new ArrayList<Defect>();

		try {
			// execute query
			InitialContext ic = new InitialContext();
			Context initialContext = (Context) ic.lookup(CONTEXT);
			DataSource datasource = (DataSource) initialContext.lookup(DATA_SOURCE);
			conn = datasource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM `Defects`");
			rs = ps.executeQuery();

			// process query results
			while (rs.next()) {

				Defect defect = new Defect(rs.getInt(DEFECT_ID_COL), rs.getString(PRODUCT_COL),
						rs.getString(SUBMITTER_COL), rs.getDate(SUBMIT_DATE_COL), rs.getString(TITLE_COL),
						rs.getString(DESCRIPTION_COL), Priority.valueOf(rs.getString(PRIORITY_COL)),
						State.valueOf(rs.getString(STATE_COL)));
				defect.setAssignee(rs.getString(ASSIGNEE_COL));
				defect.setSolution(rs.getString(SOLUTION_COL));
				defect.setDueDate(rs.getDate(DUE_DATE_COL));
				defects.add(defect);
				if (logger != null && logger.isLoggable(Level.INFO)) {
					logger.info(defect.toString());
				}
			}
		} catch (NamingException nex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(nex.getMessage());
			}
		} catch (SQLException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
		}
		return defects;
	}
	
	/**
	 * Returns list of all employees
	 * @return see description
	 */
	public static List<Employee> findAllEmployees() {
	 
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Employee> employees = new ArrayList<Employee>();

		try {
			// execute query
			InitialContext ic = new InitialContext();
			Context initialContext = (Context) ic.lookup(CONTEXT);
			DataSource datasource = (DataSource) initialContext.lookup(DATA_SOURCE);
			conn = datasource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM `Employees`");
			rs = ps.executeQuery();

			// process query results
			while (rs.next()) {
				Employee emp = new Employee(rs.getInt(EMPLOYEE_ID_COL), rs.getString(LAST_NAME_COL),
						rs.getString(FIRST_NAME_COL), rs.getString(EMAIL_COL));

				employees.add(emp);
				if (logger != null && logger.isLoggable(Level.INFO)) {
					logger.info(emp.toString());
				}
			}
		} catch (NamingException nex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(nex.getMessage());
			}
		} catch (SQLException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
		}
		return employees;
	}

	
	/**
	 * Returns list of all products
	 * @return see description
	 */
	public static List<Product> findAllProducts() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<Product>();

		try {
			// execute query
			InitialContext ic = new InitialContext();
			Context initialContext = (Context) ic.lookup(CONTEXT);
			DataSource datasource = (DataSource) initialContext.lookup(DATA_SOURCE);
			conn = datasource.getConnection();
			ps = conn.prepareStatement("SELECT * FROM `Products`");
			rs = ps.executeQuery();

			// process query results
			while (rs.next()) {
				Product p = new Product(rs.getString(NAME_COL), ProductStatus.valueOf(rs.getString(STATUS_COL)));
				products.add(p);
				if (logger != null && logger.isLoggable(Level.INFO)) {
					logger.info(p.toString());
				}
			}
		} catch (NamingException nex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(nex.getMessage());
			}
		} catch (SQLException ex) {
			if (logger != null && logger.isLoggable(Level.INFO)) {
				logger.info(ex.getMessage());
			}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					/* ignored */ }
			}
		}
		return products;
	}
}
