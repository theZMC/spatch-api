package app.spatch.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDBUtil {
  private static String connectionUrl = "jdbc:mariadb://localhost:3306/movies?user=root&password=password";

  public static Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      connection = DriverManager.getConnection(connectionUrl);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void main(String[] args) throws SQLException {
    Connection connection = getConnection();
    System.out.println(connection == null ? "NOT CONNECTED!" : "CONNECTED!");
    if (connection == null)
      return;

    DatabaseMetaData metaData = connection.getMetaData();
    ResultSet rs = metaData.getTables(null, null, "%", null);
    while (rs.next()) {
      System.out.println(rs.getString("table_name"));
    }
  }
}
