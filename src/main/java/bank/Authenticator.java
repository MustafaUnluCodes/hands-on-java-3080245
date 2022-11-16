package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

  public static Customer login(String userName, String password) throws LoginException {

    Customer customer = DataSource.getCustomer(userName);

    if (customer == null) {
      throw new LoginException("Invalid user name!");
    }

    if (customer.getPassword().equals(password)) {
      customer.setAuthenticated(true);
      return customer;
    } else {
      throw new LoginException("Invalid password!");
    }

  }

  public static void logout(Customer customer) {

    customer.setAuthenticated(false);

  }

}
