package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {

    String dbFile = "jdbc:sqlite:resources/bank.db";

    Connection connection = null;

    try {
      connection = DriverManager.getConnection(dbFile);

      System.out.println("db connection is established.");
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return connection;
  }

  public static Customer getCustomer(String userName) {

    String sql = "select * from customers where username= ?";

    Customer customer = null;

    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, userName);

      try (ResultSet resultset = statement.executeQuery()) {

        customer = new Customer(
            resultset.getInt("id"),
            resultset.getString("name"),
            resultset.getString("username"),
            resultset.getString("password"),
            resultset.getInt("account_id"));

      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return customer;
  }

  public static void main(String[] args) {

    // connect();
    Customer customer = getCustomer("twest8o@friendfeed.com");

    System.out.println(customer.getName());

  }

}
