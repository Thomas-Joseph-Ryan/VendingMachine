package CC12.Richard.Group1.Assign2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class ItemDao extends Dao{

    // giving number to each category
    String DRINKS = "0";
    String CHOCOLATES = "1";
    String CHIPS = "2";
    String CANDIES = "3";

    public ItemDao(String url) {
        super(url);
    }

    public void createProductsTable() {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS Products " +
                    " (Code  VARCHAR(20) PRIMARY KEY, " +
                    " Name VARCHAR(20), " +
                    " Price VARCHAR(20)," +
                    " Quantity VARCHAR(20)," +
                    " Category CHAR(1));");
            ps.executeUpdate();
            System.out.println("Product table created successfully");
            con.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            System.out.println("Table not created successfully");
        }

    }


//    public void createUpdateIndexerTable() {
//        Connection con = getCon();
//        try {
//            PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS Indexer " +
//                    " (Code  VARCHAR(20) PRIMARY KEY, " +
//                    " ProductName VARCHAR(20));");
//            ps.executeUpdate();
//            System.out.println("Indexer table created successfully");
//            PreparedStatement state = con.prepareStatement(
//                    """
//                    INSERT INTO Indexer (Code, ProductName)
//                    VALUES
//            ("1000", "Mineral Water"),
//            ("1001", "Sprite"),
//            ("1002", "Coca Cola"),
//            ("1003", "Pepsi"),
//            ("1004", "Juice"),
//            ("2000", "Mars"),
//            ("2001", "M&M"),
//            ("2002", "Bounty"),
//            ("2003", "Snickers"),
//            ("3000", "Smiths"),
//            ("3001", "Pringles"),
//            ("3002", "Kettle"),
//            ("3003", "Thins"),
//            ("4000", "Mentos"),
//            ("4001", "Sour Patch"),
//            ("4002", "Skittles");
//            """
//            );
//            state.executeUpdate();
//            System.out.println("Indexer table updated successfully");
//            con.close();
//
//        } catch (Exception e) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//            System.out.println("Table not created successfully");
//        }
//
//    }


    

    public static void insertToProductsTable(String code, String name, String price, String quantity, String category ){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Products VALUES (?, ?, ?, ?, ?);");
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, price);
            ps.setString(4, quantity);
            ps.setString(5,category);
            ps.executeUpdate();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    public void insertToProductsSoldTable (String code, String name, String quantity) {

    }


    public void insertDefaultProductsValues() {
        try {

            // Drinks
            insertToProductsTable("1000", "Mineral Water", "3.5", "7", DRINKS);
            insertToProductsTable("1001", "Sprite", "4", "7", DRINKS);
            insertToProductsTable("1002", "Coca Cola", "3.5", "7", DRINKS);
            insertToProductsTable("1003", "Pepsi", "3.5", "7", DRINKS);
            insertToProductsTable("1004", "Juice", "4.5", "7", DRINKS);

            // Chocolates
            insertToProductsTable("2000", "Mars", "2.5", "7", CHOCOLATES);
            insertToProductsTable("2001", "M&M", "2.5", "7", CHOCOLATES);
            insertToProductsTable("2002", "Bounty", "3", "7", CHOCOLATES);
            insertToProductsTable("2003", "Snickers", "3", "7", CHOCOLATES);

            // Chips
            insertToProductsTable("3000", "Smiths", "2", "7", CHIPS);
            insertToProductsTable("3001", "Pringles", "3", "7", CHIPS);
            insertToProductsTable("3002", "Kettle", "2.5", "7", CHIPS);
            insertToProductsTable("3003", "Thins", "2.5", "7", CHIPS);

            // Candies
            insertToProductsTable("4000", "Mentos", "2", "7", CANDIES);
            insertToProductsTable("4001", "Sour Patch", "2", "7", CANDIES);
            insertToProductsTable("4002", "Skittles", "2", "7", CANDIES);


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    

    public static List<Item> viewDrinks() {
        List<Item> items = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Products WHERE Category = 0");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("Name"));
                item.setPrice(rs.getDouble("Price"));
                item.setQty(rs.getInt("Quantity"));
                item.setCategory(0);
                item.setCode(rs.getInt("Code"));
                items.add(item);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static List<Item> viewChocolates() {
        List<Item> items = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Products WHERE Category = 1");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("Name"));
                item.setPrice(rs.getDouble("Price"));
                item.setQty(rs.getInt("Quantity"));
                item.setCategory(1);
                item.setCode(rs.getInt("Code"));
                items.add(item);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static List<Item> viewChips() {
        List<Item> items = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Products WHERE Category = 2");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("Name"));
                item.setPrice(rs.getDouble("Price"));
                item.setQty(rs.getInt("Quantity"));
                item.setCategory(2);
                item.setCode(rs.getInt("Code"));
                items.add(item);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static List<Item> viewCandies() {
        List<Item> items = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Products WHERE Category = 3");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("Name"));
                item.setPrice(rs.getDouble("Price"));
                item.setQty(rs.getInt("Quantity"));
                item.setCategory(3);
                item.setCode(rs.getInt("Code"));
                items.add(item);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public int getProductQuantity(String product){
        Connection con = getCon();
        int count = -1;
        try {
            PreparedStatement ps = con.prepareStatement(String.format("SELECT Quantity FROM Products WHERE Name='%s'",product));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
            con.close();
            return count;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return -1;
        }
    }
    
    public static void setProductQuantity(String product, String newQuantity){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE Products SET Quantity='%s' WHERE Name='%s'",newQuantity,product));
            ps.executeUpdate();
            con.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }

    public static void updateProduct(int code, Item updatedItem){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Products WHERE Code=?");
            ps.setInt(1, code);
            ps.executeUpdate();
            ps = con.prepareStatement("INSERT INTO Products VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, updatedItem.getCode());
            ps.setString(2, updatedItem.getName());
            ps.setDouble(3, updatedItem.getPrice());
            ps.setInt(4, updatedItem.getQty());
            ps.setInt(5, updatedItem.getCategory());
            ps.executeUpdate();

            con.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }

    public static void generateAvailableProductsReport(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/ProductsReport.csv";
        }
        String csvFilePath = path;

        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Products");
        
            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("Code,Name,Price,Quantity,Category");
             
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("quantity")) == 0) {
                    continue;
                }
                String code = rs.getString("Code");
                String productName = rs.getString("Name");
                String price = rs.getString("Price");
                String quantity = rs.getString("quantity");
                String category = rs.getString("category");
                 
                String line = String.format("%s,%s,%s,%s,%s",
                        code,productName,price,quantity,category);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
             
            con.close();
            fileWriter.close();
             
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
         
    }

    public static void generateCancelledTransactionSummary(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/CancellationSummary.csv";
        }
        String csvFilePath = path;

        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM CancelledTransactions");

            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("TimeStamper, Username, reason");

            while (rs.next()) {

                String TimeStamper = rs.getString("TimeStamper");
                String Username = rs.getString("Username");
                String reason = rs.getString("reason");

                String line = String.format("%s,%s,%s,",
                        TimeStamper,Username,reason);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            con.close();
            fileWriter.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }

    }

    public static void generateProductsSoldReport(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/ProductsSold.csv";
        }
        String csvFilePath = path;

        try{
            PreparedStatement ps= con.prepareStatement("SELECT Code, ProductName, Quantity FROM History");

            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("Code, ProductName, Quantity");

            while (rs.next()) {
                if (Integer.parseInt(rs.getString("quantity")) == 0) {
                    continue;
                }
                String code = rs.getString("Code");
                String productName = rs.getString("ProductName");
                String quantity = rs.getString("Quantity");

                String line = String.format("%s;%s;%s,",
                        code,productName,quantity);

                fileWriter.newLine();
                fileWriter.write(line);
            }

            con.close();
            fileWriter.close();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }

    }
}
