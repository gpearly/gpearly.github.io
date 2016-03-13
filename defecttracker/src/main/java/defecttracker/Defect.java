package defecttracker;

import java.util.Date;

public class Defect {
	
	private int defectId;
	private String product;
	private String submitter;
	private Date submitDate;
	private String title;
	private String description;
	private Date dueDate;
	private Priority priority;
	private State state;
	private String assignee;
	private String solution;	
	
	/**
	 * @param defectId
	 * @param product
	 * @param submitter
	 * @param submitDate
	 * @param title
	 * @param description
	 * @param priority
	 * @param state
	 */
	public Defect(int defectId, String product, String submitter, Date submitDate, String title, String description,
			Priority priority, State state) {
		super();
		this.defectId = defectId;
		this.product = product;
		this.submitter = submitter;
		this.submitDate = submitDate;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.state = state;
	}

	/**
	 * 
	 */
	public Defect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the defectId
	 */
	public int getDefectId() {
		return defectId;
	}

	/**
	 * @param defectId the defectId to set
	 */
	public void setDefectId(int defectId) {
		this.defectId = defectId;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the submitter
	 */
	public String getSubmitter() {
		return submitter;
	}

	/**
	 * @param submitter the submitter to set
	 */
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Defect [defectId=" + defectId + ", product=" + product + ", submitter=" + submitter + ", submitDate="
				+ submitDate + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate
				+ ", priority=" + priority + ", state=" + state + ", assignee=" + assignee + ", solution=" + solution
				+ "]";
	}

}
