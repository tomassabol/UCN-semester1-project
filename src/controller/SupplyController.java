package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.Contractor;
import model.PrimaryKey;
import model.Product;
import model.Shelf;
import model.StockBatch;
import model.SupplyOffer;
import model.SupplyOfferContainer;
import model.SupplyOrder;
import model.SupplyOrderContainer;
import model.TrackableItem;

public class SupplyController {

	private ProductController productCtrl;
	private ContractorController contractorCtrl;

	public SupplyController() {
		productCtrl = new ProductController();
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
	 * @param id of a product
	 * @return The product with the id
	 */
	public Product getProduct(int id){
		return productCtrl.getProduct(id);
	}

	/**
	 * @param id of a contractor
	 * @return The contractort with the id
	 */
	public Contractor getContractor(int id){
		return contractorCtrl.findContractorByID(id);
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

	public SupplyOffer findSupplyOfferByID(int id) {
		return SupplyOfferContainer.getInstance().findSupplyOfferById(id);
	}

	public SupplyOrder findSupplyOrderById(int supplyOrderId) {
		return SupplyOrderContainer.getInstance().findSupplyOrderByID(supplyOrderId);
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

	public ArrayList<SupplyOrder> getSupplyOrders() {
		return SupplyOrderContainer.getInstance().getSupplyOrders();
	}

	public ArrayList<SupplyOrder> getUndeliveredSupplyOrders() {
		return SupplyOrderContainer.getInstance().getUndeliveredSupplyOrders();
	}

	public ArrayList<SupplyOrder> getDeliveredSupplyOrders() {
		return SupplyOrderContainer.getInstance().getDeliveredSupplyOrders();
	}
	
	/*
	private void removeSupplyOffer() {
		// This is left empty for a reason!
		// Supply offers should not be removed, only deactivated!
	}
	*/
	
	/**
	 * Deactivate a supply offer
	 *
	 * @param supplyOffer The supply offer
	 */
	public void setStatus(SupplyOffer supplyOffer) {
		//supplyOffer.setActive(false);
		if (supplyOffer.isActive() == true) {
			supplyOffer.setActive(false);
		}
		else {
			supplyOffer.setActive(true);
		}
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
	
	/**
	 * Creates a supply order.
	 *
	 * @param supplyOffer the supply offer
	 * @param quantity the quantity
	 * @return the supply order
	 */
	// Create supply order
	public SupplyOrder createSupplyOrder(SupplyOffer supplyOffer, int quantity) {
		SupplyOrder supplyOrder = new SupplyOrder(PrimaryKey.getNextSupplyOrderID(), LocalDateTime.now(), supplyOffer, quantity);
		SupplyOrderContainer.getInstance().addSupplyOrder(supplyOrder);
		return supplyOrder;
	}
	
	/**
	 * Mark a supply order as delivered and put the items in stock
	 * Note: serial numbers for trackable items are auto-generated
	 *
	 * @param supplyOrder the supply order
	 * @param shelf the shelf to put the items in
	 * @param trackable whether to add 'trackable' items or non
	 */
	public void StockAndMarkDelivered(SupplyOrder supplyOrder, Shelf shelf, boolean trackable) {
		// For trackable items - auto generate serial number
		if (trackable) {
			// identify product
			Product product = SupplyOfferContainer.getInstance().getProduct(supplyOrder.getSupplyOffer());
			// Generate trackable items
			Set<TrackableItem> trackableItems = new HashSet<>();
			for (int i = 0; i < supplyOrder.getQuantity(); i++) {
				trackableItems.add(new TrackableItem(PrimaryKey.getNextItemID(), 
						TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE,
						product));
			}
			// insert trackable items in a stockBatch
			StockBatch stockBatch = new StockBatch(trackableItems);
			// insert stockBatch into shelf
			shelf.addStockBatch(product, stockBatch);
			
			// For untrackable items
		} else {
			Product product = SupplyOfferContainer.getInstance().getProduct(supplyOrder.getSupplyOffer());
			StockBatch stockBatch = new StockBatch(product, supplyOrder.getQuantity());
			shelf.addStockBatch(product, stockBatch);
		}
		// Mark as delivered and stocked
		supplyOrder.setDelivered(true);
	}
	
	/**
	* Work in progress xd
	* Remember to check that the serial number is unique
	*/
	// custom serial numbers
	public void StockAndMarkDelivered(SupplyOrder supplyOrder, Shelf shelf, Set<Integer> serialNumbers) {
		// TODO: work on it- low priority
	}

	/**
	 * Prints all supplyOffers
	 */
	public void printAllSupplyOffers(){
		SupplyOfferContainer sContainer = SupplyOfferContainer.getInstance();
		sContainer.printAllSupplyOfferInfo();
	}
	
}
