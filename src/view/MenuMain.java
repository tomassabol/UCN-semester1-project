package view;


import controller.*;

public class MenuMain extends GenericMenuInterface {
  
  private GenerateDataController generateCtrl;

  /**
   * Constructor for MainMenuUI.
   */
  public MenuMain() {
	  super();
    super.setTitle("Main Menu");
    super.addMenuOption("1", new GenericMenuOption("Generate test data",
    		    () -> generateTestData()));
    super.addMenuOption("2", new GenericMenuOption("Quotes",
    		    () -> showOrdersMenu()));
    super.addMenuOption("3", new GenericMenuOption("Advanced Quote Menu",
    		    () -> showAdvancedQuotes()));
    super.addMenuOption("4", new GenericMenuOption("Products",
            () -> showProductsMenu()));
    super.addMenuOption("5", new GenericMenuOption("Employees",
            () -> showEmployeesMenu()));
    super.addMenuOption("6", new GenericMenuOption("Contractors",
            () -> showContractorMenu()));
    super.addMenuOption("7", new GenericMenuOption("Supplies",
            () -> showSupplyMenu()));
    super.addMenuOption("8", new GenericMenuOption("Stock",
            () -> showStockMenu()));
    super.addMenuOption("9", new GenericMenuOption("Customers", () -> showCustomerMenu()));
    super.addMenuOption("0", new GenericMenuOption("Quit the program",
    		    () -> Terminal.quit()));

    generateCtrl = new GenerateDataController();
  }
  
  /*
   * Generate test data
   * TODO: MOVE THE LOGIC TO CONTROLLER
   */
  public void generateTestData() {
          generateCtrl.generateData();
          super.show("Data Generated");
  }
  
  /**
   * Show orders menu.
   */
  private void showOrdersMenu() {
	  new MenuQuotes(this).show();
  }

  private void showAdvancedQuotes() {
        new MenuQuotesAdvanced(this).show();
}

  /**
   * Show products menu.
   */
  private void showProductsMenu() {
	  new MenuProduct(this).show();
  }
  
  /**
   * Show employees menu.
   */
  private void showEmployeesMenu() {
	  new MenuEmployee(this).show();
  }
  /**
   * Show contractor menu.
   */
  private void showContractorMenu() {
	  new MenuContractor(this).show();
  }
  /**
   * Show supply menu.
   */
  private void showSupplyMenu(){
	  new MenuSupply(this).show();
  }
  /**
   * Show stock menu.
   */
  private void showStockMenu() {
	  new MenuStock(this).show();
  }
  /**
   * Show customer menu.
   */
  private void showCustomerMenu(){
	  new MenuCustomer(this).show();
  }

}
