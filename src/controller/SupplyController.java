package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exceptions.IllegalModificationException;
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
	 * @return The newly created supply order
	 */
	public SupplyOrder createSupplyOrder(Product product,
			int quantity, BigDecimal pricePerItem, Contractor contractor) {
		SupplyOrder supplyOrder = new SupplyOrder(PrimaryKey.getID(PrimaryKey.Keys.SUPPLY_ORDER),
				LocalDateTime.now(),
				product,
				quantity,
				pricePerItem, contractor);
		SupplyOrderContainer.getInstance().addSupplyOrder(supplyOrder);
		return supplyOrder;
	}
	
	public void updateSupplyOrderProduct(SupplyOrder supplyOrder, Product product) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isDelivered()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setProduct(product);
	};
	
	public void updateSupplyOrderQuantity(SupplyOrder supplyOrder, int quantity) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isDelivered()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setQuantity(quantity);
	};
	
	public void updateSupplyOrderPricePerItem(SupplyOrder supplyOrder, BigDecimal pricePerItem) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isDelivered()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setPricePerItem(pricePerItem);
	};
	
	public void updateSupplyOrderContractor(SupplyOrder supplyOrder, Contractor contractor) throws IllegalModificationException {
		// Do not allow modification if already delivered (aka put into stock)
		if (supplyOrder.isDelivered()) {
			throw new IllegalModificationException("You cannot update a supply order thas already been marked as delivered!");
		}
		supplyOrder.setContractor(contractor);
	};

	
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
	public void StockAndMarkDelivered(SupplyOrder supplyOrder, Shelf shelf, boolean trackable) throws IllegalModificationException {
		if (supplyOrder.isDelivered()) {
			throw new IllegalModificationException("This supply order has already been put in stock!");
		}
		
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

	public void removeSupplyOffer(SupplyOffer supplyOffer) {
		SupplyOfferContainer.getInstance().remove(supplyOffer);
}
	
}