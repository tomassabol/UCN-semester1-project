package view;


public class MenuMain extends GenericMenuInterface {
  private static MenuMain instance;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuMain() {
    super();

    super.setTitle("Main Menu");
    super.addMenuOption("1", new GenericMenuOption("Test option",
    		() -> showTestOption()));
    super.addMenuOption("2", new GenericMenuOption("Nested menu",
    		() -> showNestedMenu()));
    super.addMenuOption("0", new GenericMenuOption("Quit the program",
    		() -> Terminal.quit()));
  }

  /**
   * @return the instance of MainMenuUI
   */
  public static MenuMain getInstance() {
    if (instance == null) {
      instance = new MenuMain();
    }
    return instance;
  }

  /**
   * Show test option
   */
  private void showTestOption() {
	Terminal parser = Terminal.getInstance();
	parser.getDateInput("Your birth date");
	parser.getIntegerInput("Your age");
	parser.getStringInput("Your password");
	if (parser.confirmInput()) {
		System.out.println("Doing some processing...");
		super.show("Test option ran successfully!");
	} else {
		super.show("Test Option was cancelled!");
	}
    
  }
  
  /**
   * Show nested menu
   */
  private void showNestedMenu() {
    MenuNested.getInstance().show();
  }
  
}
