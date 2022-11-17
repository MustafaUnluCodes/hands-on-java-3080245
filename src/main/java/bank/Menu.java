package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exception.AmountException;

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

    int selection = 0;

    while ((selection != 4) && (customer.isAuthenticated())) {
      System.out.println("======================================================");
      System.out.println("Please select one of the options:");
      System.out.println("1.Deposit");
      System.out.println("2.Withdraw");
      System.out.println("3.Show balance");
      System.out.println("4.Exit");
      System.out.println("======================================================");

      selection = scanner.nextInt();

      double amount = 0.0d;

      switch (selection) {
        case 1:
          System.out.println("Please enter amount to deposit:");
          amount = scanner.nextDouble();
          try {
            account.deposit(amount);
          } catch (AmountException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Please try again.");
          }
          break;
        case 2:
          System.out.println("Please enter amount to withdraw:");
          amount = scanner.nextDouble();
          try {
            account.withdraw(amount);
          } catch (AmountException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Please try again.");
          }
          break;
        case 3:
          System.out.println("balance of account: " + account.getBalance());
          break;
        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for using our system.");
          break;
        default:
          System.out.println("Invalid selection.Please try again.");
          break;
      }

    }
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
