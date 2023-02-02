package CC12.Richard.Group1.Assign2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DenomDao extends Dao{

    public DenomDao(String url) {
        super(url);
    }

    public void createDenomTable() {
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("CREATE TABLE IF NOT EXISTS Denomination " +
                    " (Denomination  DOUBLE PRIMARY KEY, " +
                    "Quantity VARCHAR(20));");
            ps.executeUpdate();
            System.out.println("Denomination table created successfully");
            con.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            System.out.println("Table not created successfully");
        }

    }

    public static void insertToDenomTable(Double denom, String quantity ){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Denomination VALUES (?, ?);");
            ps.setDouble(1, denom);
            ps.setString(2, quantity);
            ps.executeUpdate();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    public void insertDefaultDenomValues() {
        try {
            // $100
            insertToDenomTable(100.0,"5");
            // $50
            insertToDenomTable(50.0,"5");
            
            // $20
            insertToDenomTable(20.0,"5");
            
            // $10
            insertToDenomTable(10.0,"5");

            // $5
            insertToDenomTable(5.0,"5");

            // $2
            insertToDenomTable(2.0,"5");

            // $1
            insertToDenomTable(1.0,"5");

            // 50c
            insertToDenomTable(0.5,"5");

            // 20c
            insertToDenomTable(0.2,"5");

            // 10c
            insertToDenomTable(0.1,"5");

            // 5c
            insertToDenomTable(0.05,"5");

            System.out.println("values added to Denomination Table");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            // System.exit(0);
            System.out.println("Table not created successfully");
        }
    }

    public static int getDenomQuantity(String denom){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("SELECT Quantity FROM Denomination WHERE denomination='%s'",denom));
            int ret = ps.executeQuery().getInt(1);
            con.close();
            return ret;

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return -1;
        }
    }
    public static void setDenomQuantity(String denom, String newQuantity){
        Connection con = getCon();
        try {
            PreparedStatement ps = con.prepareStatement(String.format("UPDATE Denomination SET quantity='%s' WHERE denomination='%s'",newQuantity,denom));
            ps.executeUpdate();
            con.close();

        } catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

        }
    }

    public static ArrayList<Denom> giveChange(List<Denom> denomsGiven, double amount){
        // to get rid of floating point errors
        amount = amount * 100;
        ArrayList<Denom> denoms = new ArrayList<>();
        ArrayList<Denom> ret = new ArrayList<>();
        
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Denomination ORDER BY Denomination DESC");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Denom denom = new Denom();
                Denom toAdd = new Denom();
                denom.setDenom(rs.getDouble("Denomination"));
//                System.out.println(denom.getDenom());
                toAdd.setDenom(rs.getDouble("Denomination"));
                denom.setQuantity(rs.getInt("Quantity"));
                toAdd.setQuantity(0);
                ret.add(toAdd);
                denoms.add(denom);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Adding the moneys given from customer
        for (Denom denomGiven : denomsGiven) {
            for (Denom denom : denoms) {
                if (Objects.equals(denom.getDenom(), denomGiven.getDenom())) {
                    denom.setQuantity(denom.getQuantity() + denomGiven.getQuantityInUse());
                }
            }
        }
        
        // while (amount > 0){
            int i = 0;

            // loops until we have used all the denoms or amount reaches 0
            while( (i < denoms.size()) && (amount > 0)){
                // starts from the highest denom
                Denom temp = denoms.get(i);

                // to use the highest denoms as long as their quantity is > 0 i.e we have not used them all
                if ((amount >= temp.getDenom() * 100) && (temp.getQuantity() > 0)){
                    amount -= temp.getDenom() * 100;
                    temp.setQuantity(temp.getQuantity()-1);
                    // increments quantity of the denom used in the array that will be returned
                    ret.get(i).setQuantity(ret.get(i).getQuantity()+1);
                }else{
                    i += 1;
                }
            }
        //testing 
//        for (Denom denom : ret) {
//            System.out.println(denom.getDenom() + " : " + denom.getQuantity());
//        }

        if (amount > 0){
            return null;
        }
        
        // update db
        int j = 0;
        while (j < denoms.size()){
            Denom current = denoms.get(j);
            DenomDao.setDenomQuantity(Double.toString(current.getDenom()),Integer.toString(current.getQuantity()));
            j += 1;
        }

        return ret;
    
        
    }

    public static List<Denom> viewDenoms() {
        List<Denom> denoms = new ArrayList<>();
        try {
            Connection con = getCon();
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Denomination ORDER BY Denomination DESC");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Denom denom = new Denom();
                denom.setDenom(rs.getDouble("Denomination"));
                denom.setQuantity(rs.getInt("Quantity"));
                denoms.add(denom);
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return denoms;
    }

//    public Denom getDenomName(String denom){
//        Connection con = getCon();
//        try {
//            PreparedStatement ps = con.prepareStatement(String.format("SELECT denomination FROM Denomination WHERE denomination='%s'",denom));
//            Denom ret = ps.executeQuery();
//            con.close();
//            return ret;
//
//        } catch ( Exception e ) {
//
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            return;
//        }
//    }
    public static void generateCashSummary(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/CashSummary.csv";
        }
        String csvFilePath = path;

        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Denomination");

            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("Denomination, quantity");

            while (rs.next()) {

                String Denomination = rs.getString("Denomination");
                String quantity = rs.getString("quantity");

                String line = String.format("%s,%s",
                        Denomination,quantity);

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

    public static void generateTransactionsSummary(String path){

        Connection con = getCon();
        if (path == null){
            path = "src/main/resources/reports/TransactionSummary.csv";
        }
        String csvFilePath = path;

        try{
            PreparedStatement ps= con.prepareStatement("SELECT * FROM Transactions");

            ResultSet rs = ps.executeQuery();
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

            // write header line containing column names
            fileWriter.write("Products, Paid, Change, PaymentMethod, TimeStamper");

            while (rs.next()) {

                String Products = rs.getString("Products");
                String Paid = rs.getString("Paid");
                String Change = rs.getString("Change");
                String PaymentMethod = rs.getString("PaymentMethod");
                String TimeStamper = rs.getString("TimeStamper");


                String line = String.format("%s,%s,%s,%s, %s",
                        Products, Paid, Change, PaymentMethod, TimeStamper);

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

