package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.*;

public class GenerateDataController {
    private QuoteController orderCtrl;
    private EmployeeController employeeCtrl;

    public GenerateDataController() {
        orderCtrl = new QuoteController();
        employeeCtrl = new EmployeeController();
    }

    public void generateData() {
        BulkDiscount bulkDiscount = new BulkDiscount(2, 20);
        // Create customer type
        // TODO: NEED A CUSTOMER TYPE CONTROLLER
        CustomerType customerType = new CustomerType(PrimaryKey.getNextCustomerTypeID(), "Normal", 5);
        // Create customers
        CustomerController ctrl = new CustomerController();
        Customer customer1 = ctrl.createCustomer("Attila", "Bako", "Rundvej 4", "+45 734123", customerType, LocalDate.now());
        System.out.println("Generated customer data!");
        // Create products
        ProductController ctrl2 = new ProductController();
        Product product1 = ctrl2.createProduct("Shovel", "A big, steel shovel", 0, 100);
        product1.addBulkDiscount(bulkDiscount);
        // TODO: need a controller for price creation
        SellingPrice sellingPrice = new SellingPrice(BigDecimal.valueOf(95), LocalDateTime.now());
        product1.addSellingPrice(sellingPrice);
        
        // Create items
        // TODO: Implement itemController, itemLineController...
        ShoppingItemLine itemLine1 = new ShoppingItemLine(product1, 4);
        ShoppingItemLine itemLine2 = new ShoppingItemLine(product1, 1);
        Contractor contractor = new Contractor(0, "TestSupplyCompany");
        ContractorContainer.getInstance().addContractor(contractor);
        
        // TODO: also need to use a controller here
        customer1.getShoppingCart().add(itemLine1);
        customer1.getShoppingCart().add(itemLine2);
        
        // Create employees
        IFEmployee employee = employeeCtrl.createEmployee("080600-1111", "Daniels", "Kanepe", "Rundvej 8", "+45 11114567", LocalDate.now());
        
        // Add orders to customer1
        orderCtrl.createQuote(customer1, employee, customer1.getShoppingCart());
        
        customer1.getShoppingCart().add(itemLine1);
    }
}
