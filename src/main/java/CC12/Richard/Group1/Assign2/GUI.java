package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GUI extends JFrame {

    protected Controller controller;

    Timer timer;

    public GUI(Controller controller) {
        this.controller = controller;
        //2 minutes in milliseconds

        int timerMs = 120000;
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelTransactionFunction("2 Minute Timeout");
            }
        };
        timer = new Timer(timerMs, taskPerformer);
        timer.start();

    }


    public void cancelTransactionFunction(String reason) {
//        for (Item item : controller.getItemsList()) {
//            item.setQtyInBag(0);
//        }

        closeFrame();
        String name = String.valueOf(controller.getUser().getName());
        if (name == "guest"){
            name = "anonymous";
        }
        HistoryDao.insertToCancelledTransactionTable(name, reason);
        controller.getUser().setTotalCost(0);
        controller.getItemsList().clear();
        controller.setAmountPaid(0);
        controller.setUser(new User());
        new SaleGUI(controller);
    }

    public void backFunction() {
        controller.getUser().setTotalCost(0);
        controller.getItemsList().clear();
        controller.setAmountPaid(0);
        closeFrame();
        new SaleGUI(controller);
    }

    public void closeFrame() {
        timer.stop();
        dispose();
    }

}
