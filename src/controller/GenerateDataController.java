package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.*;

public class GenerateDataController {
    private QuoteController orderCtrl;
    private EmployeeController employeeCtrl;
    ContractorController contractorCtrl;

    public GenerateDataController() {
        orderCtrl = new QuoteController();
        employeeCtrl = new EmployeeController();
        contractorCtrl = new ContractorController();
    }

    public void generateData() {
        // Create customer type
        CustomerController customerCtrl = new CustomerController();
        CustomerType customerType = customerCtrl.createCustomerType("VIP", 5);
        // Create customers
        CustomerController ctrl = new CustomerController();
        Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        // Create products
        ProductController productCtrl = new ProductController();
        Product product1 = productCtrl.createProduct("Shovel", "A big, steel shovel", 0, 100, true);
        // add bulk discount to product
        BulkDiscount bulkDiscount = new BulkDiscount(2, 20);
        product1.addBulkDiscount(bulkDiscount);
        // Add purchase price to the product
        productCtrl.createSellingPrice(BigDecimal.valueOf(95), product1);
        
        // Add contractor
        Contractor contractor1 = contractorCtrl.createContractor("Some supply company");
        
        // NOTE: Not using a controller to skip stock check!
        // Create items
        ShoppingItemLine itemLine1 = new ShoppingItemLine(product1, 4);
        ShoppingItemLine itemLine2 = new ShoppingItemLine(product1, 3);
        // Add itemline to shopping cart
        customer1.getShoppingCart().add(itemLine1);
        
        // Create employees
        IFEmployee employee = employeeCtrl.createEmployee("080600-1111", "daniels@abc.com", "1234", "Daniels", "Kanepe", "Rundvej 8", "+45 11114567", LocalDate.now());
 
        
        customer1.getShoppingCart().add(itemLine2);
        
        StockController stockCtrl = new StockController();
        StorageLocation storageLocation1 = stockCtrl.createStorageLocation("DIY", "Rundvej 11A", true);
        StorageLocation storageLocation2 = stockCtrl.createStorageLocation("Timber", "Melvej 4 ", true);
        
        Shelf shelf1 = stockCtrl.createShelf("A1", storageLocation1, product1);
        stockCtrl.createShelf("A5", storageLocation1, product1);
        stockCtrl.createShelf("C1", storageLocation2, product1);
        stockCtrl.createShelf("C22", storageLocation2, product1);
        
        SupplyController supplyCtrl = new SupplyController();
        SupplyOffer supplyOffer1 = supplyCtrl.createSupplyOffer(product1, contractor1, BigDecimal.valueOf(4), 2);
        
        StockBatch stockBatch = new StockBatch(product1, 10, LocalDateTime.now());
        shelf1.addStockBatch(product1, stockBatch);
        supplyCtrl.createSupplyOrder(supplyOffer1, 5);
        
        // Add orders to customer1
        try {
			orderCtrl.createQuote(customer1, employee);
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
        
        // Add itemline to shopping cart
        customer1.getShoppingCart().add(itemLine1);
    }
}
