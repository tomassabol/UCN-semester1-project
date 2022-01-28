package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import exception.EmailNotUniqueException;
import exception.OutOfStockException;
import gui.Common;
import model.*;

public class GenerateDataController {
    private QuoteController orderCtrl;
    private EmployeeController employeeCtrl;
    ContractorController contractorCtrl;
    LoanController loanCtrl;

    public GenerateDataController() {
        orderCtrl = new QuoteController();
        employeeCtrl = new EmployeeController();
        contractorCtrl = new ContractorController();
        loanCtrl = new LoanController();
    }

    public void generateData() {
        // Create customer type
        CustomerController customerCtrl = new CustomerController();
        CustomerType customerType = customerCtrl.createCustomerType("VIP", 5);
        // Create customers
        CustomerController ctrl = new CustomerController();
        Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        Customer customer2 = ctrl.createCustomer("Tomi", "Toth", "Rundvej 8", "+45 154893", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        Customer customer3 = ctrl.createCustomer("Ferenc", "Mant", "Gade 44", "+45 234523", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        Customer customer4 = ctrl.createCustomer("Julie", "Bron", "Hosebro 81", "+45 154893", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        Customer customer5 = ctrl.createCustomer("Daniel", "Kanepe", "Hosebro 33", "+45 956883", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        // Create products
        ProductController productCtrl = new ProductController();
        Product product1 = productCtrl.createProduct("Shovel", "A big, steel shovel", 70, 100, true);
        Product product2 = productCtrl.createProduct("Hammer", "A small, steel hammer", 20, 55, true);
        Product product3 = productCtrl.createProduct("Kitchen", "A designed kitchen with all needed forniture", 0, 30, true);
        Product product4 = productCtrl.createProduct("Chair", ", gaming chair", 10, 50, true);
        Product product5 = productCtrl.createProduct("Table", "Adjustable Table", 10, 100, true);
        Product product6 = productCtrl.createProduct("Diswasher", "T2021 with 5 function", 25, 48, true);
        Product product7 = productCtrl.createProduct("Refrigerator", "A big Refrigerator wit freezer", 0, 100, true);
        Product product8 = productCtrl.createProduct("Laundry basket", "for used clothes", 40, 90, true);
        Product product9 = productCtrl.createProduct("Bookcase", "store books", 50, 100, true);
        Product product10 = productCtrl.createProduct("Table top", "Design wood table top", 10, 28, true);
        Product product11 = productCtrl.createProduct("Lawn mower", "Cut the grass", 10, 28, true);
        Product product12 = productCtrl.createProduct("Chain saw", "for wood cut", 10, 28, true);
        LoaningPrice loaningPrice = productCtrl.createLoaningPrice(BigDecimal.valueOf(25), product1);
        LoaningPrice loaningPrice2 = productCtrl.createLoaningPrice(BigDecimal.valueOf(50), product11);
        LoaningPrice loaningPrice3 = productCtrl.createLoaningPrice(BigDecimal.valueOf(30), product12);
        // add bulk discount to product
        BulkDiscount bulkDiscount = new BulkDiscount(2, 20);
        product1.addBulkDiscount(bulkDiscount);
        // Add purchase price to the product
        productCtrl.createSellingPrice(BigDecimal.valueOf(95), product1);
        productCtrl.createSellingPrice(BigDecimal.valueOf(30), product2);
        productCtrl.createSellingPrice(BigDecimal.valueOf(1400), product3);
        productCtrl.createSellingPrice(BigDecimal.valueOf(50), product4);
        productCtrl.createSellingPrice(BigDecimal.valueOf(80), product5);
        productCtrl.createSellingPrice(BigDecimal.valueOf(800), product6);
        productCtrl.createSellingPrice(BigDecimal.valueOf(500), product7);
        productCtrl.createSellingPrice(BigDecimal.valueOf(20), product8);
        productCtrl.createSellingPrice(BigDecimal.valueOf(120), product9);
        productCtrl.createSellingPrice(BigDecimal.valueOf(100), product10);
        productCtrl.createSellingPrice(BigDecimal.valueOf(1600), product11);
        productCtrl.createSellingPrice(BigDecimal.valueOf(700), product12);
        // Add contractor
        Contractor contractor1 = contractorCtrl.createContractor("Some supply A/S");
        Contractor contractor2 = contractorCtrl.createContractor("Timber A/S");
        Contractor contractor3 = contractorCtrl.createContractor("Praktiker A/S");
        Contractor contractor4 = contractorCtrl.createContractor("Hikerma A/S");
        Contractor contractor5 = contractorCtrl.createContractor("Trandmas A/S");
        
        // NOTE: Not using a controller to skip stock check!
        // Create items
        ShoppingItemLine itemLine1 = new ShoppingItemLine(product1, 4);
        ShoppingItemLine itemLine2 = new ShoppingItemLine(product1, 3);
        // Add itemline to shopping cart
        customer1.getShoppingCart().add(itemLine1);
        
        // Create employees
        IFEmployee employee = null;
		try {
			employee = employeeCtrl.createEmployee("080600-1111", "daniels@abc.com", "1234", "Daniels", "Kanepe", "Rundvej 8", "+45 11114567", LocalDate.now());
			employee = employeeCtrl.createEmployee("035556-3366", "attila@outlook.com", "1234", "Attila", "Kanepe", "Gade 81", "+45 433114567", LocalDate.now());
			employee = employeeCtrl.createEmployee("038986-4433", "tomi@gmail.com", "1234", "Tomi", "Toth", "Handgade 11", "+45 883433167", LocalDate.now());
			employee = employeeCtrl.createEmployee("137656-3854", "dereck@yahoo.com", "1234", "Dereck", "Morr", "Gade 44", "+45 99834567", LocalDate.now());
		} catch (EmailNotUniqueException e1) {
			e1.printStackTrace();
		}
 
        
        customer1.getShoppingCart().add(itemLine2);
        
        StockController stockCtrl = new StockController();
        StorageLocation storageLocation1 = stockCtrl.createStorageLocation("DIY", "Rundvej 11A", true);
        StorageLocation storageLocation2 = stockCtrl.createStorageLocation("Timber", "Melvej 4 ", true);
        StorageLocation storageLocation3 = stockCtrl.createStorageLocation("Kitchen", "Melvej 4 ", true);
        StorageLocation storageLocation4 = stockCtrl.createStorageLocation("DIY", "Rundvej 11A", true);
        
        Shelf shelf1 = stockCtrl.createShelf("A1", storageLocation1);
        stockCtrl.createShelf("A5", storageLocation1);
        stockCtrl.createShelf("D1", storageLocation2);
        stockCtrl.createShelf("C22", storageLocation2);
        stockCtrl.createShelf("C26", storageLocation3);
        stockCtrl.createShelf("B1", storageLocation4);
        
        SupplyController supplyCtrl = new SupplyController();
        SupplyOffer supplyOffer1 = supplyCtrl.createSupplyOffer(product1, contractor1, BigDecimal.valueOf(4), 2);
        
        StockBatch stockBatch = new StockBatch(product1, 10, LocalDateTime.now());
        shelf1.addStockBatch(product1, stockBatch);
        supplyCtrl.createSupplyOrder(supplyOffer1.getProduct(), 
        		5, supplyOffer1.getPricePerItem(), supplyOffer1.getContractor(), LocalDateTime.now());
        supplyCtrl.createSupplyOrder(supplyOffer1.getProduct(), 
        		2, supplyOffer1.getPricePerItem(), supplyOffer1.getContractor(), LocalDateTime.now());
        
        // Add orders to customer1
        try {
			orderCtrl.createQuote(customer1, employee);
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
        
        // Add itemline to shopping cart
        customer1.getShoppingCart().add(itemLine1);

        try{
            Loan loan1 = loanCtrl.createLoan(customer1, employee, product1, LocalDateTime.now().plusDays(3));
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
    }
}
