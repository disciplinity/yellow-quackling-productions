package database;

import java.sql.*;


public class DBConnect {



    public static void main(String[] args) {

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        String url = "jdbc:mysql://sql11.freemysqlhosting.net/sql11225282";
        String username = "sql11225282";
        String password = "VA8eNT8N2L";
        try {
            // create our mysql database connection
            Connection conn = DriverManager.getConnection(url, username, password);
            // our SQL SELECT query.
            // if you only need a few columns, specify them by name instead of using "*"

            // the mysql insert statement
            String query = "insert into test (first, second, third, fourth)"
                    + " values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, 5);
            preparedStmt.setString (2, "abc");
            preparedStmt.setInt(3, 6);
            preparedStmt.setInt(4, 7);

            // execute the preparedstatement
            preparedStmt.execute();

//            conn.close();

            String query1 = "SELECT * FROM test";


            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            // iterate through the java resultset
            while (rs.next())
            {
                int first = rs.getInt("first");
                String second = rs.getString("second");
                int third = rs.getInt("third");
                int fourth = rs.getInt("fourth");


                // print the results
                System.out.format("%d, %s, %d, %d\n", first, second, third, fourth);
            }
            st.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}