package controller;

import java.util.List;



import model.PrimaryKey;
import model.ContractorContainer;
import model.Contractor;



/**
 * The Class Contractor.
 */

public class ContractorController {
		/**
		 * Instantiates a new order controller.
		 */
		public ContractorController() {}	
	
	
		
		/**
		 * Creates the contractor
		 *
		 * @param ID the id
		 * @param companyName is the companyName
		 * @return the newly created contractor object
		 */
		public Contractor createContractor(String companyName) {
			Contractor contractor = new Contractor(PrimaryKey.getNextContractorID(), companyName);
			ContractorContainer.getInstance().addContractor(contractor);
			return contractor;
		}
		
		/**
		 * Find a contractor by ID.
		 *
		 * @param id the id
		 * @return the contractor
		 */
		public Contractor findContractorByID(int id) {
			return ContractorContainer.getInstance().findContractorById(id);
		}
		/**
		 * Gets all contractor.
		 *
		 * @return A list of the contractor
		 */
		public List<Contractor> getContractors() {
			return ContractorContainer.getInstance().getContractors();
		}
			
			/**
			 * @param id of the product to be removed
			 */
		public void removeContractor(Contractor contractor) {
				ContractorContainer.getInstance().removeContractor(contractor);
		}

		public void updateCompanyName(Contractor contractor, String name){
			contractor.setCompanyName(name);
		}
			
}	
	
