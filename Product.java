package defecttracker;

public class Product {

	private String Name;
	private ProductStatus Status;
	
	/**
	 * @param name
	 * @param status
	 */
	public Product(String name, ProductStatus status) {
		super();
		Name = name;
		Status = status;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return the status
	 */
	public ProductStatus getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ProductStatus status) {
		Status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [Name=" + Name + ", Status=" + Status + "]";
	}

}
