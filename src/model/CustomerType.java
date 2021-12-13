package model;

public class CustomerType {
	
	public final int ID;
	private String name;
	private int discountPercentage;

	/**
	 * Instantiates a new customer type.
	 *
	 * @param ID the id
	 * @param type the type
	 * @param discountPercentage the discount percentage
	 * 
	 * @exception IllegalArgumentException if discount % < 0
	 */
	public CustomerType(int ID, String type, int discountPercentage) {
		if (discountPercentage < 0 || discountPercentage > 100) {
			throw new IllegalArgumentException("Customer's discount percentage must be between 0 and 100");
		}
		this.setDiscountPercentage(discountPercentage);
		this.name = type;
		this.discountPercentage = discountPercentage;
		this.ID = ID;
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * Sets the discount percentage.
	 *
	 * @param discountPercentage the new discount percentage
	 * 
	 * @exception IllegalArgumentException if discount % < 0
	 */
	public void setDiscountPercentage(int discountPercentage) {
		if (discountPercentage < 0 || discountPercentage > 100) {
			throw new IllegalArgumentException("Customer's discount percentage must be between 0 and 100");
		}
		this.discountPercentage = discountPercentage;
	}
	
	

}
