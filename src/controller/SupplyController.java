package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Contractor;
import model.Customer;
import model.CustomerContainer;
import model.PrimaryKey;
import model.Product;
import model.ProductContainer;
import model.SupplyOffer;
import model.SupplyOfferContainer;
import model.SupplyOrder;
import model.SupplyOrderContainer;

public class SupplyController {

	public SupplyController() {
	}
	
	/**
	 * Creates a supply offer and adds it to the container
	 *
	 * @param product the product
	 * @param contractor the contractor
	 * @param Id the id
	 * @param pricePerItem the price per item
	 * @param minQuantity the minimum order quantity
	 * @param active the active
	 * @param dateAdded the date added
	 * @return the supply offer
	 */
	public SupplyOffer createSupplyOffer(Product product,
			Contractor contractor, BigDecimal pricePerItem,
			int minQuantity) {
		
		SupplyOffer supplyOffer = new SupplyOffer(PrimaryKey.getNextSupplyOfferID(),
				pricePerItem, minQuantity, contractor, true, LocalDateTime.now());
		SupplyOfferContainer.getInstance().addSupplyOffer(product, supplyOffer);
		
		return supplyOffer;
	}
	

	/**
	 * Find supply offer by index for a specific product
	 *
	 * @param product the product
	 * @param index the index
	 * @return the supply offer
	 */
	public SupplyOffer findSupplyOfferByID(Product product, int index) {
		return SupplyOfferContainer.getInstance().findSupplyOfferById(product, index);
	}
	
	/**
	 * Gets the supply offers for a product
	 *
	 * @param product the product
	 * @return the supply offers
	 */
	public ArrayList<SupplyOffer> getSupplyOffers(Product product) {
		return SupplyOfferContainer.getInstance().getSupplyOffers(product);
	}
	
	private void removeSupplyOffer() {
		// This is left empty for a reason!
		// Supply offers should not be removed, only deactivated!
	}
	
	/**
	 * Deactivate a supply offer
	 *
	 * @param supplyOffer The supply offer
	 */
	public void setStatus(SupplyOffer supplyOffer) {
		supplyOffer.setActive(false);
	}
	
	/**
	 * 'Update' supply offer.
	 * Creates a new supply offer with new values and deactivates old one
	 *
	 * @param product the product the supply offer belongs to
	 * @param supplyOffer the old supply offer
	 * @param newContractor the new contractor
	 * @param newMinQuantity the new minimum order quantity
	 * @param newPricePerItem the new price per item
	 * @return the supply offer
	 */
	public SupplyOffer updateSupplyOffer(Product product, SupplyOffer supplyOffer, Contractor newContractor,
			int newMinQuantity, BigDecimal newPricePerItem) {
		supplyOffer.setActive(false);
		return createSupplyOffer(product, newContractor, newPricePerItem, newMinQuantity);
		
		
	}
	
	// Create supply order
	public SupplyOrder createSupplyOrder(SupplyOffer supplyOffer, int quantity) {
		SupplyOrder supplyOrder = new SupplyOrder(PrimaryKey.getNextSupplyOrderID(), LocalDateTime.now(), supplyOffer, quantity);
		SupplyOrderContainer.getInstance().addSupplyOrder(supplyOrder);
		return supplyOrder;
	}
	
	// TODO: method for mark supply as delivered - automatically restock
}
