package view;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class GenericMenuInterface {
	private String title;
	private LinkedHashMap<String, GenericMenuOption> menuOptions;

	/**
	 * @param title The title of the menu.
	 * @param MenuOptions the menu items
	 */
	public GenericMenuInterface() {
		this.title = "";
		this.menuOptions = new LinkedHashMap<>();
	}

	/**
	 * @param title Set the title of the menu
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param MenuOption the menu item
	 */
	public void addMenuOption(String command, GenericMenuOption MenuOption) {
		menuOptions.put(command, MenuOption);
	}

	/**
	 * Show menu and listen for input
	 * @param message (Optional) A message to show to the user above the menu
	 */
	public void show(String message) {
		// clear screen
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
		// show message
		if (message != "") {
			System.out.println("["+ message + "]");
		}
		// show menu and listen for input
		printSelf();
		executeStringCommand();
	}
	
	public void show() {
		show("");
	}
	

	/**
	 * print self
	 */
	public void printSelf() {
		System.out.println("****** " + title + " ******");
		for (HashMap.Entry<String, GenericMenuOption> entry : menuOptions.entrySet()) {
			String command = entry.getKey();
			GenericMenuOption menuOption = entry.getValue();
			System.out.println(" (" + command + ") " + menuOption.getTitle());
		}
	}

	/**
	 * listen and react to user input
	 */
	public void executeStringCommand() {
		Parser parser = Parser.getInstance();

		

		GenericMenuOption foundItem = null;
		while (foundItem == null) {
			String choice = parser.getStringInput(">>");
			foundItem = findMenuOption(choice);
			if (foundItem == null) {
				System.out.println("Please enter a valid command.");
			}
		}
		foundItem.run();
	}

	/**
	 * @param command the command a user inputs as a String
	 * @return the menu item
	 */
	private GenericMenuOption findMenuOption(String command) {
		return menuOptions.get(command);
	}
}
