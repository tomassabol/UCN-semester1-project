package view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;

import controller.AuthenticationController;

public class GenericMenuInterface {
	private Stack<GenericMenuInterface> breadcrumbs;
	private AuthenticationController auth;
	private String title;
	private LinkedHashMap<String, GenericMenuOption> menuOptions;

	/**
	 * @param title The title of the menu.
	 * @param MenuOptions the menu items
	 * 
	 * @exception IllegalArgumentException When previousInterface is null
	 */
	public GenericMenuInterface(GenericMenuInterface previousInterface) {
		if (previousInterface == null) {
			throw new IllegalArgumentException("Previous interface cannot be null!");
		}
		this.breadcrumbs = previousInterface.breadcrumbs;
		this.breadcrumbs.add(previousInterface);
		this.auth = previousInterface.auth;
		
		this.title = null;
		this.menuOptions = new LinkedHashMap<>();
	}
	
	// This constructor creates new breadcrumbs & auth!
	public GenericMenuInterface() {
		this.breadcrumbs = new Stack<>();
		this.auth = new AuthenticationController();
		this.title = null;
		this.menuOptions = new LinkedHashMap<>();
	}
	
	/**
	 * Show menu and listen for input
	 * @param message (Optional) A message to show to the user above the menu
	 */
	public void show(String message, boolean clearScreen) {
		Terminal terminal = Terminal.getInstance();
		if (clearScreen) {
			terminal.clearScreen();
		}
		// show message
		if (message != null) {
			System.out.println("["+ message + "]");
		}
		// show menu
		printMenu();
		// let the user choose a menu option until a valid one has been supplied.
		// Then, run the chosen option.
		getMenuOptionInput().run();
	}
	public void show() {
		show(null, true);
	}
	public void show(String message) {
		show(message, true);
	}
	public void show(boolean clearScreen) {
		show(null, clearScreen);
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
	 * print the menu
	 */
	private void printMenu() {
		if (title != null) {
			System.out.println("****** " + title + " ******");
		}
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
	
	public void goToPreviousMenu() {
		GenericMenuInterface previousInterface = breadcrumbs.pop();
		if (breadcrumbs == null) {
			System.out.println("Error: There are no more preivous menus");
		} else {
			previousInterface.show();
		}
	}
	
	public Stack<GenericMenuInterface> getBreadcrumbs() {
		return this.breadcrumbs;
	}
	
	public AuthenticationController getAuth() {
		return this.auth;
	}
	
	public void addMenuOptionGoBack(String key) {
		this.addMenuOption(key, new GenericMenuOption("<-- Go back", () -> this.goToPreviousMenu()));
	}


}
