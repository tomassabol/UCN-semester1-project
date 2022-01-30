package models.container;

import java.util.ArrayList;
import java.util.List;

import models.Product;
import models.SupplyOrder;

public class SupplyOrderContainer {
	
	private static SupplyOrderContainer instance;
	private ArrayList<SupplyOrder> supplyOrders;

	/*
	 * Private constructor: singleton
	 */
	private SupplyOrderContainer() {
		this.supplyOrders = new ArrayList<>();
	}
	
    /**
     * @return instance of SuppplyOrderContainer
     */
    public static SupplyOrderContainer getInstance(){
		if (instance == null) {
            instance = new SupplyOrderContainer();
        }
        return instance;
    }
    
    
    /*
     * @param supplyOrder The supply order to add to the container
     * @return true if successful, else false
     */
    public void addSupplyOrder(SupplyOrder supplyOrder) {
    	//return supplyOrders.add(supplyOrder);
        supplyOrders.add(supplyOrder);
    }
    
    public void remove(SupplyOrder supplyOrder)  {
    	this.supplyOrders.remove(supplyOrder);
    }
    
    /**
     * @return All undelivered supply orders
     */
    public ArrayList<SupplyOrder> getUndeliveredSupplyOrders() {
    	ArrayList<SupplyOrder> undeliveredOrders = new ArrayList<>();
    	for (SupplyOrder supplyOrder: this.supplyOrders) {
    		// if not delivered
    		if (!supplyOrder.isStocked()) {
    			undeliveredOrders.add(supplyOrder);
    		}
    	}
    	return undeliveredOrders;
    }
    
    /**
     * @return All delivered supply orders
     */
    public ArrayList<SupplyOrder> getDeliveredSupplyOrders() {
    	ArrayList<SupplyOrder> deliveredOrders = new ArrayList<>();
    	for (SupplyOrder supplyOrder: this.supplyOrders) {
    		// if delivered
    		if (supplyOrder.isStocked()) {
    			deliveredOrders.add(supplyOrder);
    		}
    	}
    	return deliveredOrders;
    }
    
    /**
     * @return All delivered supply orders (delivered and not)
     */
    public List<SupplyOrder> getSupplyOrders() {
    	return this.supplyOrders;
    }
    
    public List<SupplyOrder> getSupplyOrders(Product product) {
    	List<SupplyOrder> returnedSupplyOrders = new ArrayList<>();
    	for (SupplyOrder order: this.getSupplyOrders()) {
    		if (order.getProduct() == product) {
    			returnedSupplyOrders.add(order);
    		}
    	}
    	return returnedSupplyOrders;
    }
    
    /**
     * Find SupplyOrder by ID.
     *
     * @param supplyOrderId the supply order ID
     * @return the supply order
     */
    public SupplyOrder findSupplyOrderByID(int supplyOrderId) {
    	for (SupplyOrder supplyOrder: supplyOrders) {
    		if (supplyOrder.ID == supplyOrderId) {
    			return supplyOrder;
    		}
    	}
    	return null;
    }
    
    /**
     * Find undelivered SupplyOrder by ID.
     *
     * @param supplyOrderId the supply order ID
     * @return the supply order
     */
    public SupplyOrder findUndeliveredSupplyOrderByID(int supplyOrderId) {
    	for (SupplyOrder supplyOrder: supplyOrders) {
    		if (supplyOrder.ID == supplyOrderId && !supplyOrder.isStocked()) {
    			return supplyOrder;
    		}
    	}
    	return null;
    }
    
    /**
     * Find delivered SupplyOrder by ID.
     *
     * @param supplyOrderId the supply order ID
     * @return the supply order
     */
    public SupplyOrder finddeliveredSupplyOrderByID(int supplyOrderId) {
    	for (SupplyOrder supplyOrder: supplyOrders) {
    		if (supplyOrder.ID == supplyOrderId && supplyOrder.isStocked()) {
    			return supplyOrder;
    		}
    	}
    	return null;
    }

}
