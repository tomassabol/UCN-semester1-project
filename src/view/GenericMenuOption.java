package view;

public class GenericMenuOption {
  private String title;
  private Runnable action;

  /**
   * @param name   The name of the menu item.
   * @param action The action to be performed when the menu item is selected.
   */
  public GenericMenuOption(String title, Runnable action) {
    this.title = title;
    this.action = action;
  }

  /**
   * Run the assigned action to this menu option
   */
  public void run() {
    this.action.run();
  }

  /**
   * @return The name of the menu option.
   */
  public String getTitle() {
    return title;
  }
}
