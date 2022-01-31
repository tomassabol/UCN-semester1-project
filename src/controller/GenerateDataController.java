package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import exception.DisabledStateException;
import exception.EmailNotUniqueException;
import exception.IllegalModificationException;
import exception.NullPriceException;
import exception.OutOfStockException;
import gui.Common;
import models.*;

public class GenerateDataController {
    OrderController orderCtrl;
    QuoteController quoteCtrl;
    EmployeeController employeeCtrl;
    ContractorController contractorCtrl;
    LoanController loanCtrl;
    ShoppingCartController shoppingCartCtrl;
    StockController stockCtrl;
    SupplyController supplyCtrl;

    public GenerateDataController() {
        orderCtrl = new OrderController();
        quoteCtrl = new QuoteController();
        employeeCtrl = new EmployeeController();
        contractorCtrl = new ContractorController();
        loanCtrl = new LoanController();
        shoppingCartCtrl = new ShoppingCartController();
        stockCtrl = new StockController();
        supplyCtrl = new SupplyController();
    }

    public void generateData() {
        // Create customer type
        CustomerController customerCtrl = new CustomerController();
        CustomerType customerType = customerCtrl.createCustomerType("VIP", 5);
        // Create customers
        CustomerController ctrl = new CustomerController();
        Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", customerType, LocalDate.now());
        Customer customer2 = ctrl.createCustomer("Tomi", "Toth", "Rundvej 8", "+45 154893", customerType, LocalDate.now());
        Customer customer3 = ctrl.createCustomer("Ferenc", "Mant", "Gade 44", "+45 234523", customerType, LocalDate.now());
        Customer customer4 = ctrl.createCustomer("Julie", "Bron", "Hosebro 81", "+45 154893", customerType, LocalDate.now());
        Customer customer5 = ctrl.createCustomer("Daniel", "Kanepe", "Hosebro 33", "+45 956883", customerType, LocalDate.now());
        
        // Create products
        ProductController productCtrl = new ProductController();
        Product product1 = productCtrl.createProduct("Shovel", "A big, steel shovel", 70, 100, true);
        Product product2 = productCtrl.createProduct("Hammer", "A small, steel hammer", 20, 55, true);
        Product product3 = productCtrl.createProduct("Kitchen", "A designed kitchen with all needed forniture", 5, 30, true);
        Product product4 = productCtrl.createProduct("Chair", ", gaming chair", 10, 50, true);
        Product product5 = productCtrl.createProduct("Table", "Adjustable Table", 10, 100, true);
        Product product6 = productCtrl.createProduct("Diswasher", "T2021 with 5 function", 25, 48, true);
        Product product7 = productCtrl.createProduct("Refrigerator", "A big Refrigerator wit freezer", 4, 100, true);
        Product product8 = productCtrl.createProduct("Laundry basket", "for used clothes", 40, 90, true);
        Product product9 = productCtrl.createProduct("Bookcase", "store books", 50, 100, true);
        Product product10 = productCtrl.createProduct("Table top", "Design wood table top", 10, 28, true);
        Product product11 = productCtrl.createProduct("Lawn mower", "Cut the grass", 10, 28, true);
        Product product12 = productCtrl.createProduct("Chain saw", "for wood cut", 10, 28, true);
        
        // Add 'loaning' prices to some products
        LoaningPrice loaningPrice = productCtrl.createLoaningPrice(BigDecimal.valueOf(25), product1);
        LoaningPrice loaningPrice2 = productCtrl.createLoaningPrice(BigDecimal.valueOf(50), product3);
        LoaningPrice loaningPrice3 = productCtrl.createLoaningPrice(BigDecimal.valueOf(30), product12);
        
        // add bulk discount to some products
        BulkDiscount bulkDiscount1 = new BulkDiscount(3, 5);
        BulkDiscount bulkDiscount2 = new BulkDiscount(5, 10);
        BulkDiscount bulkDiscount3 = new BulkDiscount(7, 15);
        product1.addBulkDiscount(bulkDiscount1);
        product1.addBulkDiscount(bulkDiscount2);
        product1.addBulkDiscount(bulkDiscount3);
        
        // Add purchase price to the products
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
        
        // Create contractors
        Contractor contractor1 = contractorCtrl.createContractor("Some supply A/S");
        Contractor contractor2 = contractorCtrl.createContractor("Timber A/S");
        Contractor contractor3 = contractorCtrl.createContractor("Praktiker A/S");
        Contractor contractor4 = contractorCtrl.createContractor("Hikerma A/S");
        Contractor contractor5 = contractorCtrl.createContractor("Trandmas A/S");
        
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
        
        // Create storage locations
        StorageLocation storageLocation1 = stockCtrl.createStorageLocation("DIY", "Rundvej 11A", true);
        StorageLocation storageLocation2 = stockCtrl.createStorageLocation("Timber", "Melvej 4 ", true);
        StorageLocation storageLocation3 = stockCtrl.createStorageLocation("Kitchen", "Melvej 4 ", true);
        StorageLocation storageLocation4 = stockCtrl.createStorageLocation("DIY", "Rundvej 11A", true);
        
        // Create shelves
        Shelf shelf1 = stockCtrl.createShelf("A1", storageLocation1);
        Shelf shelf2 = stockCtrl.createShelf("A5", storageLocation1);
        Shelf shelf3 = stockCtrl.createShelf("D1", storageLocation2);
        Shelf shelf4 = stockCtrl.createShelf("C22", storageLocation2);
        Shelf shelf5 = stockCtrl.createShelf("C26", storageLocation3);
        Shelf shelf6 = stockCtrl.createShelf("B1", storageLocation4);
        
        // Create supply offers
        SupplyOffer supplyOffer1 = supplyCtrl.createSupplyOffer(product1, contractor1, BigDecimal.valueOf(4), 2);
        
        // Create supply orders
        SupplyOrder supplyOrder1 = supplyCtrl.createSupplyOrder(product1, 15, BigDecimal.valueOf(20), contractor1, LocalDateTime.now().minusDays(2));
        SupplyOrder supplyOrder2 = supplyCtrl.createSupplyOrder(product2, 25, BigDecimal.valueOf(25), contractor1, LocalDateTime.now().minusDays(1));
        SupplyOrder supplyOrder3 = supplyCtrl.createSupplyOrder(product3, 15, BigDecimal.valueOf(30), contractor1, LocalDateTime.now().minusDays(3));
        SupplyOrder supplyOrder4 = supplyCtrl.createSupplyOrder(product5, 33, BigDecimal.valueOf(15), contractor1, LocalDateTime.now().minusDays(5));
        SupplyOrder supplyOrder5 = supplyCtrl.createSupplyOrder(product6, 2, BigDecimal.valueOf(40), contractor1, LocalDateTime.now().minusDays(8));
        SupplyOrder supplyOrder6 = supplyCtrl.createSupplyOrder(product1, 15, BigDecimal.valueOf(20), contractor1, LocalDateTime.now().minusDays(15));
        SupplyOrder supplyOrder7 = supplyCtrl.createSupplyOrder(product1, 15, BigDecimal.valueOf(20), contractor1, LocalDateTime.now().minusDays(15));
        
        // Put some supply orders into stock
        try {
        	supplyCtrl.StockAndMarkDelivered(supplyOrder1, shelf1, LocalDateTime.now(), false, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE);
			supplyCtrl.StockAndMarkDelivered(supplyOrder2, shelf2, LocalDateTime.now(), false, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE);
	        supplyCtrl.StockAndMarkDelivered(supplyOrder3, shelf3, LocalDateTime.now(), true, TrackableItem.TRACKABLE_ITEM_TYPE.LOANABLE);
	        supplyCtrl.StockAndMarkDelivered(supplyOrder4, shelf2, LocalDateTime.now(), true, TrackableItem.TRACKABLE_ITEM_TYPE.LOANABLE);
	        supplyCtrl.StockAndMarkDelivered(supplyOrder5, shelf4, LocalDateTime.now(), true, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE);
	        supplyCtrl.StockAndMarkDelivered(supplyOrder6, shelf1, LocalDateTime.now(), false, TrackableItem.TRACKABLE_ITEM_TYPE.BUYABLE);
		} catch (IllegalModificationException e2) {
			e2.printStackTrace();
		}
        
        // ** customer1 **
        
        // Add items to cart
        try {
			shoppingCartCtrl.addProduct(customer1.getShoppingCart(), product2, 3);
	        shoppingCartCtrl.addProduct(customer1.getShoppingCart(), product1, 3);
		} catch (OutOfStockException | NullPriceException | DisabledStateException e1) {
			e1.printStackTrace();
		}
        // Create quote & then order
        try {
			Quote quote1 = quoteCtrl.createQuote(customer1, employee);
            // create order
            orderCtrl.payForQuote(quote1);
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}

        // ** customer2 **
        
        // Add items to cart
        try {
			shoppingCartCtrl.addProduct(customer2.getShoppingCart(), product2, 1);
	        shoppingCartCtrl.addProduct(customer2.getShoppingCart(), product1, 1);
		} catch (OutOfStockException | NullPriceException | DisabledStateException e1) {
			e1.printStackTrace();
		}
        // Create quote & then order
        try {
			Quote quote2 = quoteCtrl.createQuote(customer2, employee);
            // create order
            orderCtrl.payForQuote(quote2, LocalDateTime.now().minusDays(5));
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
        
        // ** customer3 **
        
        // Add items to cart
        try {
			shoppingCartCtrl.addProduct(customer3.getShoppingCart(), product2, 2);
	        shoppingCartCtrl.addProduct(customer3.getShoppingCart(), product1, 1);
		} catch (OutOfStockException | NullPriceException | DisabledStateException e1) {
			e1.printStackTrace();
		}
        // Create quote & then order
        try {
			Quote quote3 = quoteCtrl.createQuote(customer3, employee);
            // create order
            orderCtrl.payForQuote(quote3, LocalDateTime.now().minusDays(3));
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}

        
        // Add items to cart for customer1, again
        try {
			shoppingCartCtrl.addProduct(customer2.getShoppingCart(), product2, 2);
	        shoppingCartCtrl.addProduct(customer1.getShoppingCart(), product1, 1);
		} catch (OutOfStockException | NullPriceException | DisabledStateException e1) {
			e1.printStackTrace();
		}

        try{
            Loan loan1 = loanCtrl.createLoan(customer1, employee, product3, LocalDateTime.now().plusDays(3));
		} catch (OutOfStockException e) {
			e.printStackTrace();
		}
    }
}
