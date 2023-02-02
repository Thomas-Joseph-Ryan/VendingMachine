package CC12.Richard.Group1.Assign2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Dao {

    static String url = "jdbc:sqlite:src/main/resources/Vendor.db";
    static final String user = "";
    static final String password = "";

    public Dao(String url) {
        if (url != null) {
            this.url = url;
        }
    }

    public static Connection getCon(){
        Connection conn=null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url, user, password);
        }catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
            System.out.println("Connection Unsuccessful");
        }
        return conn;
    }

    public static int count(String column ,String database) {

        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("SELECT COUNT(%s) FROM %s", column, database));
            int ret = ps.executeQuery().getInt(1);
            con.close();
            return ret;
        } catch ( Exception e ) {
            return -1;
        }
    }

    public void dropTables() {
    
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("DROP TABLE Products");
            ps.executeUpdate();
            ps = con.prepareStatement("DROP TABLE History");
            ps.executeUpdate();
            ps = con.prepareStatement("DROP TABLE Denomination");
            ps.executeUpdate();
            ps = con.prepareStatement("DROP TABLE Users");
            ps.executeUpdate();
            ps = con.prepareStatement("DROP TABLE CancelledTransactions");
            ps.executeUpdate();
            ps = con.prepareStatement("DROP TABLE Transactions");
            ps.executeUpdate();
//            ps = con.prepareStatement("DROP TABLE soldProducts");
//            ps.executeUpdate();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

    }

    

}
