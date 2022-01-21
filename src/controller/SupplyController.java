package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Contractor;
import model.PrimaryKey;
import model.Product;
import model.Shelf;
import model.StockBatch;
import model.SupplyOffer;
import model.SupplyOrder;
import model.TrackableItem;
import model.containers.SupplyOfferContainer;
import model.containers.SupplyOrderContainer;

public class SupplyController {

	private ProductController productCtrl;
	private ContractorController contractorCtrl;

	public SupplyController() {
		productCtrl = new ProductController();
	}
	

	/**
	 * Creates a new supply offer and adds it to the container
	 * 
	 * @param product
	 * @param contractor
	 * @param pricePerItem
	 * @param minQuantity
	 * @return
	 */
	public SupplyOffer createSupplyOffer(Product product,
			Contractor contractor, BigDecimal pricePerItem,
			int minQuantity) {
		
		SupplyOffer supplyOffer = new SupplyOffer(PrimaryKey.getID(PrimaryKey.Keys.SUPPLY_OFFER), product,
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
	public SupplyOffer findSupplyOfferByIndex(Product product, int index) {
		return SupplyOfferContainer.getInstance().findSupplyOfferByIndex(product, index);
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
	public List<SupplyOffer> getSupplyOffers(Product product) {
		return SupplyOfferContainer.getInstance().getSupplyOffers(product);
	}
	
	public List<SupplyOffer> getSupplyOffers() {
		return SupplyOfferContainer.getInstance().getSupplyOffers();
	}

	public List<SupplyOrder> getSupplyOrders() {
		return SupplyOrderContainer.getInstance().getSupplyOrders();
	}

	public List<SupplyOrder> getUndeliveredSupplyOrders() {
		return SupplyOrderContainer.getInstance().getUndeliveredSupplyOrders();
	}

	public List<SupplyOrder> getDeliveredSupplyOrders() {
		return SupplyOrderContainer.getInstance().getDeliveredSupplyOrders();
	}
	
	
	/**
	 * Deactivate a supply offer
	 *
	 * @param supplyOffer The supply offer
	 */
	public void setStatus(SupplyOffer supplyOffer, boolean status) {
		supplyOffer.setActive(status);
		//supplyOffer.setActive(!supplyOffer.isActive()); || This may be better
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
	 * Creates a supply order and adds it to the container
	 * 
	 * @param product
	 * @param quantity
	 * @param pricePerItem
	 * @param contractor
	 * @return The newly created supply order
	 */
	public SupplyOrder createSupplyOrder(Product product,
			int quantity, BigDecimal pricePerItem, Contractor contractor) {
		SupplyOrder supplyOrder = new SupplyOrder(PrimaryKey.getID(PrimaryKey.Keys.SUPPLY_ORDER),
				LocalDateTime.now(),
				product,
				quantity,
				product.getLatestSellingPrice(), contractor);
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
			Product product = supplyOrder.getProduct();
			
			// Generate trackable items
			Set<TrackableItem> trackableItems = new HashSet<>();
			for (int i = 0; i < supplyOrder.getQuantity(); i++) {
				trackableItems.add(new TrackableItem(PrimaryKey.getID(PrimaryKey.Keys.ITEM), 
						TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE,
						product));
			}
			// insert trackable items in a stockBatch
			StockBatch stockBatch = new StockBatch(trackableItems, LocalDateTime.now());
			// insert stockBatch into shelf
			shelf.addStockBatch(product, stockBatch);
			
			// For untrackable items
		} else {
			Product product = supplyOrder.getProduct();
			StockBatch stockBatch = new StockBatch(product, supplyOrder.getQuantity(), LocalDateTime.now());
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
	
}