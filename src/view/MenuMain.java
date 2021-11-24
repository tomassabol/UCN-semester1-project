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
    		() -> System.exit(0)));
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
	Parser parser = Parser.getInstance();
	parser.getDateInput("Your birth date");
	parser.confirmInput();
    super.show("Test option ran successfully!");
  }
  
  /**
   * Show nested menu
   */
  private void showNestedMenu() {
    MenuNested.getInstance().show();
  }
  
}
