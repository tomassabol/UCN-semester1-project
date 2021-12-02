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
		Terminal terminal = Terminal.getInstance();
		terminal.clearScreen();
		// show message
		if (message != "") {
			System.out.println("["+ message + "]");
		}
		// show menu
		printMenu();
		// let the user choose a menu option until a valid one has been supplied.
		// Then, run the chosen option.
		getMenuOptionInput().run();
	}
	
	public void show() {
		show("");
	}
	

	/**
	 * print the menu
	 */
	private void printMenu() {
		System.out.println("****** " + title + " ******");
		for (HashMap.Entry<String, GenericMenuOption> entry : menuOptions.entrySet()) {
			String command = entry.getKey();
			GenericMenuOption menuOption = entry.getValue();
			System.out.println(" (" + command + ") " + menuOption.getTitle());
		}
	}

	/**
	 * Get
	 */
	public GenericMenuOption getMenuOptionInput() {
		Terminal parser = Terminal.getInstance();
		GenericMenuOption menuOption = null;
		while (true) {
			menuOption = this.menuOptions.get(parser.getStringInput(">>"));
			if (menuOption != null) {
				// return breaks out of the while loop!
				return menuOption;
			} else {
				System.out.println("Please choose one of the menu options");
				// implied continue
			}
		}
	}


}
