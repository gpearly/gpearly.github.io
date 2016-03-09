package defecttracker;

public class Employee {

	private int employeeID;
	private String lastName;
	private String firstName;
	private String eMail;

	/**
	 * @param employeeID
	 * @param lastName
	 * @param firstName
	 */
	public Employee(int employeeID, String lastName, String firstName, String eMail) {
		super();
		this.employeeID = employeeID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.eMail = eMail;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", lastName=" + lastName + ", firstName=" + firstName + ", eMail="
				+ eMail + "]";
	}
}
