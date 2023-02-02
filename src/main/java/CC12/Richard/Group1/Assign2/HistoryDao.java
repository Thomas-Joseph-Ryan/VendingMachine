package CC12.Richard.Group1.Assign2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDao extends Dao {

    public HistoryDao(String url) {
        super(url);
    }

    public void createHistoryTable() {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS History " +
                    " (Code VARCHAR(20), " +
                    " ProductName VARCHAR(20), " +
                    " Quantity VARCHAR(20), " +
                    "username VARCHAR(20));");
            ps.executeUpdate();
            System.out.println("History table created successfully");
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    public static void insertToHistoryTable(String code, String product, String quantity, String username) {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO History VALUES (?, ?, ?, ?);");
            ps.setString(1, code);
            ps.setString(2, product);
            ps.setString(3, quantity);
            ps.setString(4, username);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    public static ArrayList<History> viewHistoryByUserName(String username) {
        ArrayList<History> History = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement ps = con.prepareStatement(String.format("SELECT ProductName, Quantity, username FROM History WHERE username='%s' ORDER BY rowid DESC LIMIT 5", username));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                History entry = new History();
                entry.setProduct(rs.getString("ProductName"));
                entry.setQty(rs.getInt("Quantity"));
                entry.setUserName(rs.getString("username"));
                History.add(entry);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return History;
    }

    public void createTransactionTable() {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS Transactions " +
                    "(Products VARCHAR(2000)," +
                    " Paid DOUBLE, " +
                    " Change DOUBLE, " +
                    " PaymentMethod VARCHAR(20), " +
                    " TimeStamper datetime );");
            ps.executeUpdate();
            System.out.println("Transaction table created successfully");
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            System.out.println("Table not created successfully");

        }
    }

    public static void insertToTransactionTable(String product, Double paid, Double change, String paymentMethod  ){
        Connection con = getCon();
        try {
            // PreparedStatement ps = con.prepareStatement(String.format("SELECT code FROM Indexer WHERE Name = %s", product));
            // String ret = ps.executeQuery().getString(1);
            PreparedStatement sp = con.prepareStatement("INSERT INTO Transactions VALUES (?, ?, ?, ?, datetime('now','localtime'));");
            sp.setString(1, product);
            sp.setDouble(2, paid);
            sp.setDouble(3, change);
            sp.setString(4, paymentMethod);
            sp.executeUpdate();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not updated correctly");
        }
    }

    public void createCancelledTransactionTable() {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("" +
                    "CREATE TABLE IF NOT EXISTS CancelledTransactions " +
                    "(TimeStamper datetime," +
                    " Username VARCHAR(20), " +
                    " reason VARCHAR(200) );");
            ps.executeUpdate();
            System.out.println("Cancelled Transaction table created successfully");
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            System.out.println("Table not created successfully");

        }
    }

    public static void insertToCancelledTransactionTable(String username, String reason  ){

        Connection con = getCon();
        if (username.equalsIgnoreCase("guest")) {
            username = "anonymous";
        }
        try {
            // PreparedStatement ps = con.prepareStatement(String.format("SELECT code FROM Indexer WHERE Name = %s", product));
            // String ret = ps.executeQuery().getString(1);
            PreparedStatement sp = con.prepareStatement("INSERT INTO CancelledTransactions VALUES ( datetime('now','localtime'), ?, ?);");
            sp.setString(1, username);
            sp.setString(2, reason);
            sp.executeUpdate();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not updated correctly");
        }
    }
}
