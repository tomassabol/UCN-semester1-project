package model;

import java.util.ArrayList;

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
    public boolean addSupplyOrder(SupplyOrder supplyOrder) {
    	return supplyOrders.add(supplyOrder);
    }
    
    // no need for removal. left in for statistics.
    // to 'remove', change status deliveredAndStocked to true for supply order
    /*
    private void removeSupplyOrder() {
    	//
    }
    */
    
    /**
     * @return All undelivered supply orders
     */
    // TODO: implement in view
    public ArrayList<SupplyOrder> getUndeliveredSupplyOrders() {
    	ArrayList<SupplyOrder> undeliveredOrders = new ArrayList<>();
    	for (SupplyOrder supplyOrder: this.supplyOrders) {
    		// if not delivered
    		if (!supplyOrder.isDelivered()) {
    			undeliveredOrders.add(supplyOrder);
    		}
    	}
    	return undeliveredOrders;
    }

    public void printUndeliveredSupplyOrders() {
        for (SupplyOrder supplyOrder : getUndeliveredSupplyOrders()) {
            supplyOrder.printAllSupplyOrderInfo();
        }
    }
    
    /**
     * @return All delivered supply orders
     */
    // TODO: implement in view
    public ArrayList<SupplyOrder> getdeliveredSupplyOrders() {
    	ArrayList<SupplyOrder> deliveredOrders = new ArrayList<>();
    	for (SupplyOrder supplyOrder: this.supplyOrders) {
    		// if delivered
    		if (supplyOrder.isDelivered()) {
    			deliveredOrders.add(supplyOrder);
    		}
    	}
    	return deliveredOrders;
    }

    public void printDeliveredSupplyOrderInfo() {
        for (SupplyOrder supplyOrder : getdeliveredSupplyOrders()) {
            supplyOrder.printAllSupplyOrderInfo();
        }
    }
    
    /**
     * @return All delivered supply orders (delivered and not)
     */
    public ArrayList<SupplyOrder> getSupplyOrders() {
    	return this.supplyOrders;
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
    		if (supplyOrder.ID == supplyOrderId && !supplyOrder.isDelivered()) {
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
    		if (supplyOrder.ID == supplyOrderId && supplyOrder.isDelivered()) {
    			return supplyOrder;
    		}
    	}
    	return null;
    }
    
    public void printAllSupplyOrderInfo() {
        for (SupplyOrder supplyOrder : supplyOrders) {
            supplyOrder.printAllSupplyOrderInfo();
        }
    }

}
