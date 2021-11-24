package view;


public class MenuNested extends GenericMenuInterface {
  private static MenuNested instance;

  /**
   * Constructor for MainMenuUI.
   */
  private MenuNested() {
    super();

    super.setTitle("Nested Menu");
    super.addMenuOption("0", new GenericMenuOption("Go back to main menu",
    		() -> MenuMain.getInstance().show()));
  }

  /**
   * @return the instance of MainMenuUI
   */
  public static MenuNested getInstance() {
    if (instance == null) {
      instance = new MenuNested();
    }
    return instance;
  }
  
}
