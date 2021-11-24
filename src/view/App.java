package view;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        MenuMain menuMain = MenuMain.getInstance();
        menuMain.show();
    }
}
