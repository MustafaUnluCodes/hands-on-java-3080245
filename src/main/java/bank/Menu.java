package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {

    System.out.println("Welcome to Global Bank International");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if (customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());

      menu.showMenu(customer, account);
    }

    menu.scanner.close();

  }

  private void showMenu(Customer customer, Account account) {
  }

  public Customer authenticateUser() {

    System.out.println("Please enter your username:");
    String userName = scanner.next();

    System.out.println("Please enter your password:");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(userName, password);
    } catch (LoginException ex) {
      System.out.println("User cannot be authenticated. " + ex.getMessage());
    }

    return customer;
  }

}
