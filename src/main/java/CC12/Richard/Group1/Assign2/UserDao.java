package CC12.Richard.Group1.Assign2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.util.*;
import processing.data.JSONObject;
import processing.data.JSONArray;

public class UserDao extends Dao{

    public UserDao(String url) {
        super(url);
    }

    public void createUsersTable() {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS Users " +
                    " (username VARCHAR(20), " +
                    " ROLE VARCHAR(20), " +
                    " password VARCHAR(20), " +
                    " CardHolderName VARCHAR(20), " +
                    "CardNumber VARCHAR(20));");
            ps.executeUpdate();
            System.out.println("User table created successfully");
            con.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            System.out.println("Table not created successfully");
        }

    }

    public static void insertToUsersTable(String username,String role, String password, String cardName, String cardNumber ){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Users VALUES (?, ?, ?, ?, ?);");
            ps.setString(1, username);
            ps.setString(2, role);
            ps.setString(3, password);
            ps.setString(4, cardName);
            ps.setString(5, cardNumber);
            ps.executeUpdate();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

        public void insertDefaultUsersValues() {
        try {

            // owner
            insertToUsersTable("owner","owner","owner","NULL","NULL");

            // seller
            insertToUsersTable("seller","seller","seller","NULL","NULL");

            //cashier
            insertToUsersTable("cashier","cashier","cashier","NULL","NULL");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    public static String getUserRole(String username){
        Connection con = getCon();
        String ret = "";
        try {
            PreparedStatement ps = con.prepareStatement(String.format("SELECT Role FROM Users WHERE username ='%s'",username));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ret = rs.getString(1);
            }
            con.close();
            return ret;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return null;
        }
    }

    public static void setUserRole(String username, String newRole){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE Users SET ROLE='%s' WHERE username='%s'",newRole,username));
            ps.executeUpdate();
            con.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }


    // public static void removeRole(String username){
    //     Connection con = getCon();
    //     try {
    //         PreparedStatement ps = con.prepareStatement(String.format("UPDATE Users SET ROLE='customer' WHERE username='%s'",username));
    //         ps.executeUpdate();
    //         con.close();

    //     } catch ( Exception e ) {

    //         System.err.println( e.getClass().getName() + ": " + e.getMessage() );

    //     }
    // }
    
    public static void saveCardDetails(String username, String cardHolderName, String cardNumber){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE Users SET CardHolderName='%s' WHERE username='%s'",cardHolderName,username));
            ps.executeUpdate();
            ps = con.prepareStatement(String.format("UPDATE Users SET CardNumber='%s' WHERE username='%s'",cardNumber,username));
            ps.executeUpdate();
            con.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }

    public static boolean checkIfUsernameAlreadyExists(String username) {
        Connection con = getCon();
        String ret = "";
        try {
            PreparedStatement ps = con.prepareStatement(String.format("SELECT * FROM Users WHERE username ='%s'",username));
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                ret = rs.getString(1);
            }
            con.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // return null;
        }

        if (ret == ""){
            return false;
        }else{
            return true;
        }
        //TODO just putting dummy values for now - Tom
        // if (username.equalsIgnoreCase("guest")) {
        //     return true;
        // }
        // return false;
    }

    public static User viewUser(String username) {
        User user = new User();
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            user.setName(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setRole(rs.getString("ROLE"));
            if (!rs.getString("CardHolderName").equals("NULL")) {
                user.setCardName(rs.getString("CardHolderName"));
                user.setCardNumber("CardNumber");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean checkIfPasswordMatchesUsername (String username, String password) {

        Connection con = getCon();

        try {
            String dbPassword = "";
            PreparedStatement ps = con.prepareStatement(String.format("SELECT password FROM Users WHERE username ='%s'",username));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                dbPassword = rs.getString(1);
            }

            con.close();

            if (dbPassword.equals(password)) {
                return true;
            }


        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // return null;
        }

        return false;

    }

    public static void insertNewUser(String username, String password) {
        //TODO
        UserDao.insertToUsersTable(username,"customer",password,"NULL","NULL");

    }

    public static void insertNewUserByOwner(String username, String role){
        UserDao.insertToUsersTable(username,role,role,"NULL","NULL");
    }


    public void generateAvailableChangeReport(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/AvailableChangeReport.csv";
        }
        
        String csvFilePath = path;

        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Denomination");
        
            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("Change,Quantity");
             
            while (rs.next()) {
                String denom = rs.getString("Denomination");
                String quantity = rs.getString("quantity");
                 
                 
                String line = String.format("%s,%s",
                        denom,quantity);
                 
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

    public void generateUsersReport(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/UsersReport.csv";
        }
        
        String csvFilePath = path;
        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Users");
        
            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("username,Role");
             
            while (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("ROLE");
                 
                 
                String line = String.format("%s,%s",
                        username,role);
                 
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

    public static int creditCardCheck(String name, String cardNumber){
        
         StringBuilder s = new StringBuilder();
        try
        {
            File jsonFile = new File("src/main/resources/credit_cards.json");
            Scanner jsonReader = new Scanner(jsonFile);
            int counter = 0;
            while(jsonReader.hasNextLine()){
                s.append(jsonReader.nextLine());
            }
            JSONArray array = JSONArray.parse(s.toString());
            // if(currentLevel == 0)
            //     lives = config.getInt("lives");
            // JSONArray array = config.getJSONArray("levels");
            // levels = array.size()-1;
            int valid = 0;
            int i = 0;
            while (i < 50){
                String nameCheck = array.getJSONObject(i).getString("name");
                if (nameCheck.equals(name)){

                    String cardNumberCheck = array.getJSONObject(i).getString("number");
                    System.out.println(cardNumberCheck);
                    if (cardNumberCheck.equals(cardNumber)){
                        valid = 1;
                    }
                }
                i += 1;
            }

            jsonReader.close();

            if (valid == 0){
                return 0;
            }else{
                return 1;
            }
            
            // System.out.println(levelTime);
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            return -1;
        }
    }
}
