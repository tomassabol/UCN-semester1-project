package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.IllegalModificationException;
import models.Contractor;
import models.PrimaryKey;
import models.Product;
import models.Shelf;
import models.StockBatch;
import models.SupplyOffer;
import models.SupplyOrder;
import models.TrackableItem;
import models.container.SupplyOfferContainer;
import models.container.SupplyOrderContainer;

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
	
	public List<SupplyOrder> getSupplyOrders(Product product) {
		return SupplyOrderContainer.getInstance().getSupplyOrders(product);
	}

	public List<SupplyOrder> getUndeliveredSupplyOrders() {
		return SupplyOrderContainer.getInstance().getUndeliveredSupplyOrders();
	}

	public List<SupplyOrder> getDeliveredSupplyOrders() {
		return SupplyOrderContainer.getInstance().getDeliveredSupplyOrders();
	}
	
	
	public void updateSupplyOfferProduct(SupplyOffer supplyOffer, Product product) {
		supplyOffer.setProduct(product);
	}
	
	public void updateSupplyOfferPricePerItem(SupplyOffer supplyOffer, BigDecimal pricePerItem) {
		supplyOffer.setPricePerItem(pricePerItem);
	}
	
	public void updateSupplyOfferMinQuantity(SupplyOffer supplyOffer, int minQuantity) {
		supplyOffer.setMinQuantity(minQuantity);
	}
	
	public void updateSupplyOfferContractor(SupplyOffer supplyOffer, Contractor contractor) {
		supplyOffer.setContractor(contractor);
	}

	/**
	 * Creates a supply order and adds it to the container
	 * 
	 * @param product
	 * @param quantity
	 * @param pricePerItem
	 * @param contractor
	 * @param dateOrdered
	 * @return The newly created supply order
	 */
	public SupplyOrder createSupplyOrder(Product product,
			int quantity, BigDecimal pricePerItem, Contractor contractor, LocalDateTime dateOrdered) {
		SupplyOrder supplyOrder = new SupplyOrder(PrimaryKey.getID(PrimaryKey.Keys.SUPPLY_ORDER),
				dateOrdered,
				product,
				quantity,
				pricePerItem, contractor);
		SupplyOrderContainer.getInstance().addSupplyOrder(supplyOrder);
		return supplyOrder;
	}
	
	public void updateSupplyOrderProduct(SupplyOrder supplyOrder, Product product) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isStocked()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setProduct(product);
	};
	
	public void updateSupplyOrderQuantity(SupplyOrder supplyOrder, int quantity) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isStocked()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setQuantity(quantity);
	};
	
	public void updateSupplyOrderPricePerItem(SupplyOrder supplyOrder, BigDecimal pricePerItem) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isStocked()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setPricePerItem(pricePerItem);
	};
	
	public void updateSupplyOrderContractor(SupplyOrder supplyOrder, Contractor contractor) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isStocked()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setContractor(contractor);
	};
	
	public void updateSupplyOrderDateOrdered(SupplyOrder supplyOrder, LocalDateTime dateOrdered) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isStocked()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setDateOrdered(dateOrdered);
		
	}

	
	/**
	 * Mark a supply order as delivered and put the items in stock
	 * Note: serial numbers for trackable items are auto-generated
	 *
	 * @param supplyOrder the supply order
	 * @param shelf the shelf to put the items in
	 * @param trackable whether to add 'trackable' items or non
	 * 
	 * @throws IllegalModificationException when already put into stock
	 */
	public void StockAndMarkDelivered(SupplyOrder supplyOrder, Shelf shelf, LocalDateTime deliveredDate, boolean trackable, TrackableItem.TRACKABLE_ITEM_TYPE type) throws IllegalModificationException {
		if (supplyOrder.isStocked()) {
			throw new IllegalModificationException("This supply order has already been put in stock!");
		}
		if (deliveredDate.isBefore(supplyOrder.getDateOrdered())) {
			throw new IllegalArgumentException("Delivery date must be after order date!");
		}
		if (trackable == false && type == TrackableItem.TRACKABLE_ITEM_TYPE.LOANABLE) {
			throw new IllegalArgumentException("Only trackable items (with serial numbers) can be loanable!");
		}
		
		// For trackable items - auto generate serial number
		if (trackable) {
			// identify product
			Product product = supplyOrder.getProduct();
			
			// Generate trackable items
			Set<TrackableItem> trackableItems = new HashSet<>();
			for (int i = 0; i < supplyOrder.getQuantity(); i++) {
				trackableItems.add(new TrackableItem(PrimaryKey.getID(PrimaryKey.Keys.ITEM), 
						type,
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
		supplyOrder.setDelivered(deliveredDate);
	}
	
	/**
	* Work in progress xd
	* Remember to check that the serial number is unique
	*/
	// custom serial numbers
	public void StockAndMarkDelivered(SupplyOrder supplyOrder, Shelf shelf, Set<Integer> serialNumbers) {
		// TODO: work on it- low priority
	}

	public void removeSupplyOffer(SupplyOffer supplyOffer) {
		SupplyOfferContainer.getInstance().remove(supplyOffer);
}


	public void removeSupplyOrder(SupplyOrder supplyOrder) {
		SupplyOrderContainer.getInstance().remove(supplyOrder);
		
	}


	
}