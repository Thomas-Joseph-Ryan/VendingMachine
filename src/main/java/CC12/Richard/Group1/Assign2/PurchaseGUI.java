package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PurchaseGUI extends GUI{

    public PurchaseGUI(Controller controller) {
        super(controller);
    }

    protected void purchaseItemsCash() {

        /*
        1. Check change is available to complete transaction
        2. If not cancel transaction according to specs
        3. otherwise decrement change given and increment cash received
        4. Add information to transactions table
        5. Decrement items qty
         */
        //.giveChange function returns null if not possible, and updates the database if possible
        ArrayList<Denom> denomsGivenForChange = DenomDao.giveChange(controller.getDenomList(), controller.getAmountPaid() - controller.getUser().getTotalCost());

        if (denomsGivenForChange == null) {
            JOptionPane.showMessageDialog(null, "Sorry, Not enough change avaliable to complete transaction. Cancelling transaction", "Whoops!", JOptionPane.WARNING_MESSAGE);

            cancelTransactionFunction("Not enough change");

            return;
        }
        //Add information to transactions table
        StringBuilder productsBought = new StringBuilder();
        for (Item item : controller.getItemsList()) {
            if (item.getQtyInBag() > 0) {

                int qty = item.getQty();
                qty = qty - item.getQtyInBag();
                ItemDao.setProductQuantity(item.getName(), String.valueOf(qty));
                productsBought.append(item.getName() + "/");

                HistoryDao.insertToHistoryTable(String.valueOf(item.getCode()),item.getName(),String.valueOf(item.getQtyInBag()),controller.getUser().getName());
            }
        }

        int lastIndex = productsBought.length()-1;
        productsBought.deleteCharAt(lastIndex);

        double change = controller.getAmountPaid() - controller.getUser().getTotalCost();
        HistoryDao.insertToTransactionTable(productsBought.toString(),controller.getAmountPaid(),change,"cash");
        controller.setDenomChangeGiven(denomsGivenForChange);


        new PaymentProcessedGUI(controller);

    }

    protected void purchaseItemsCard() {

        StringBuilder productsBought = new StringBuilder();
        for (Item item : controller.getItemsList()) {
            if (item.getQtyInBag() > 0) {
                int qty = item.getQty();
                qty = qty - item.getQtyInBag();
                ItemDao.setProductQuantity(item.getName(), String.valueOf(qty));
                productsBought.append(item.getName() + "/");

                HistoryDao.insertToHistoryTable(String.valueOf(item.getCode()),item.getName(),String.valueOf(item.getQtyInBag()),controller.getUser().getName());
            }
        }

        int lastIndex = productsBought.length()-1;
        productsBought.deleteCharAt(lastIndex);

        HistoryDao.insertToTransactionTable(productsBought.toString(),controller.getUser().getTotalCost(),0.0,"card");
        new PaymentProcessedGUI(controller);
        /*
        1. Decrement items qty
        2. Add information to transactions table
        3. Log customer out
         */
        //Add information to transactions table
//        for (Item item : controller.getItemsList()) {
//            if (item.getQtyInBag() > 0) {
//                int qty = item.getQty();
//                qty = qty - item.getQtyInBag();
//                ItemDao.setProductQuantity(item.getName(), String.valueOf(qty));
//            }
//        }
//
//        new PaymentProcessedGUI(controller);
    }
}
