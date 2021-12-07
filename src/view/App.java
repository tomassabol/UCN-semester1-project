package view;

import model.Authentication;

public class App {
    public static void main(String[] args) {
    	
        MenuMain menuMain = MenuMain.getInstance();
        menuMain.show("Welcome, " + 
        Authentication.getInstance().getLoggedInUser().getFirstName());
    }
}
