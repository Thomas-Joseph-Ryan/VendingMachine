package CC12.Richard.Group1.Assign2;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CashPaymentGUI extends PurchaseGUI {

    private JButton pay;

    private JLabel amountBeingPaid;

    public CashPaymentGUI (Controller controller) {
        super(controller);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));
        getContentPane().setBackground(Color.darkGray);

        add(createFirstPanel(), BorderLayout.PAGE_START);
        add(createSecondPanel(), BorderLayout.CENTER);
        add(createThirdPanel(), BorderLayout.PAGE_END);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setVisible(true);
    }

    private JPanel createFirstPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 0, 30));
        panel.setBackground(Color.darkGray);

        //Create back button
        JButton back = new JButton("Back");
        back.setBackground(Color.darkGray);
        back.setForeground(Color.lightGray);
        back.setFocusable(false);
        back.addActionListener(e -> backFunction());
        panel.add(back);

        //Create title
        JLabel title = new JLabel("Payment");
        title.setForeground(Color.lightGray);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        panel.add(title);

        //Create Icon
        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.TRAILING);
        image.setIcon(new ImageIcon("src/main/resources/LiteSnacks.png"));
        panel.add(image);


        return panel;
    }


    private JLabel defaultTextSetter(JLabel label) {
        label.setForeground(Color.lightGray);
        return label;
    }
    private JPanel createSecondPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 30, 0));
        panel.setBackground(Color.darkGray);

        //LEFT RECEIPT PANEL
//        int receiptRowCount = 1;
//        for (Item item : controller.getItemsList()) {
//            if (item.getQtyInBag() > 0) {
//                receiptRowCount ++;
//            }
//        }

        JPanel receiptPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(receiptPanel);
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBackground(Color.darkGray);

        JPanel receiptLabelRow = new JPanel(new GridLayout(1, 3, 5, 0));
        receiptLabelRow.setBackground(Color.darkGray);


        JLabel temp;

        temp = new JLabel("Item");
        defaultTextSetter(temp);
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setHorizontalAlignment(JLabel.CENTER);
        receiptLabelRow.add(temp);

        temp = new JLabel("QTY");
        defaultTextSetter(temp);
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setHorizontalAlignment(JLabel.CENTER);
        receiptLabelRow.add(temp);

        temp = new JLabel("Total");
        defaultTextSetter(temp);
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setHorizontalAlignment(JLabel.CENTER);
        receiptLabelRow.add(temp);


        receiptPanel.add(receiptLabelRow);

        for (Item item : controller.getItemsList()) {
            if (item.getQtyInBag() > 0) {
                JPanel itemRow = new JPanel(new GridLayout(1, 3, 5, 0));
                itemRow.setBackground(Color.darkGray);

                temp = new JLabel(item.getName());
                defaultTextSetter(temp);
                temp.setVerticalAlignment(JLabel.TOP);
                temp.setHorizontalAlignment(JLabel.CENTER);
                itemRow.add(temp);

                temp = new JLabel(String.valueOf(item.getQtyInBag()));
                defaultTextSetter(temp);
                temp.setVerticalAlignment(JLabel.TOP);
                temp.setHorizontalAlignment(JLabel.CENTER);
                itemRow.add(temp);

                temp = new JLabel(String.valueOf(item.getQtyInBag() * item.getPrice()));
                defaultTextSetter(temp);
                temp.setVerticalAlignment(JLabel.TOP);
                temp.setHorizontalAlignment(JLabel.CENTER);
                itemRow.add(temp);

                receiptPanel.add(itemRow);
            }
        }
        receiptPanel.add(Box.createVerticalGlue());

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setBackground(Color.darkGray);
//        scrollPane.getHorizontalScrollBar().setForeground(Color.darkGray);

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = Color.gray;
            }
        });
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        panel.add(scrollPane);




        //RIGHT CASH PAYMENT PANEL
        List<Denom> denomList = DenomDao.viewDenoms();
        controller.setDenomList(denomList);

        JPanel cashButtonPanel = new JPanel(new GridLayout((int) Math.ceil(denomList.size() / 2.0), 2));
        cashButtonPanel.setBackground(Color.darkGray);
        for(Denom denom : denomList) {

            JPanel infoPanel = new JPanel(new GridLayout(2, 1));
            infoPanel.setBackground(Color.darkGray);
            JLabel denomLabel = new JLabel(String.valueOf(denom.getDenom()));
            denomLabel.setForeground(Color.lightGray);
            denomLabel.setHorizontalAlignment(JLabel.CENTER);
            denomLabel.setVerticalAlignment(JLabel.BOTTOM);
            JLabel qtyLabel = new JLabel("Qty: " + denom.getQuantityInUse());
            qtyLabel.setForeground(Color.lightGray);
            qtyLabel.setHorizontalAlignment(JLabel.CENTER);
            qtyLabel.setVerticalAlignment(JLabel.TOP);
            infoPanel.add(denomLabel);
            infoPanel.add(qtyLabel);

            JPanel buttonUpPanel = new JPanel(new GridLayout(3, 1));
            buttonUpPanel.setBackground(Color.darkGray);
            JButton buttonUp = new JButton("^");
            buttonUp.setBackground(Color.darkGray);
            buttonUp.setForeground(Color.lightGray);
            buttonUp.setFocusable(false);
            buttonUp.addActionListener(e -> {
                denom.incrementQtyInUse();
                qtyLabel.setText("Qty: " + denom.getQuantityInUse());
                controller.setAmountPaid(Math.round((controller.getAmountPaid() + denom.getDenom()) * 100.0) / 100.0);
                amountBeingPaid.setText("Being Paid: $" + controller.getAmountPaid());
                if (controller.getUser().getTotalCost() <= controller.getAmountPaid()) {
                    pay.setEnabled(true);
                    pay.setForeground(Color.lightGray);
                }
            });

            buttonUpPanel.add(new JLabel(""));
            buttonUpPanel.add(buttonUp);
            buttonUpPanel.add(new JLabel(""));

            JPanel buttonDownPanel = new JPanel(new GridLayout(3, 1));
            buttonDownPanel.setBackground(Color.darkGray);
            JButton buttonDown = new JButton("v");
            buttonDown.setBackground(Color.darkGray);
            buttonDown.setForeground(Color.lightGray);
            buttonDown.setFocusable(false);
            buttonDown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (denom.getQuantityInUse() > 0) {
                        denom.decrementQtyInUse();
                        qtyLabel.setText("Qty: " + denom.getQuantityInUse());
                        controller.setAmountPaid(Math.round((controller.getAmountPaid() - denom.getDenom()) * 100.0) / 100.0);
                        amountBeingPaid.setText("Being Paid: $" + controller.getAmountPaid());
                        if (controller.getUser().getTotalCost() > controller.getAmountPaid()) {
                            pay.setEnabled(false);
                            pay.setForeground(Color.lightGray);
                        }
                    }
                }
            });


            buttonDownPanel.add(new JLabel(""));
            buttonDownPanel.add(buttonDown);
            buttonDownPanel.add(new JLabel(""));

            cashButtonPanel.add(infoPanel);
            cashButtonPanel.add(buttonUpPanel);
            cashButtonPanel.add(buttonDownPanel);
        }

        panel.add(cashButtonPanel);



        return panel;
    }

    private JPanel createThirdPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 40, 0));
        panel.setBackground(Color.darkGray);



        //Create paid panel
        JPanel paidPanel = new JPanel(new GridLayout(1, 2));
        paidPanel.setBackground(Color.darkGray);

        JLabel totalLabel = new JLabel("Grand Total: $" + controller.getUser().getTotalCost());
        totalLabel.setForeground(Color.lightGray);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        totalLabel.setHorizontalAlignment(JLabel.TRAILING);
        paidPanel.add(totalLabel);

        amountBeingPaid = new JLabel("Being Paid: $" + controller.getAmountPaid());
        amountBeingPaid.setForeground(Color.lightGray);
        amountBeingPaid.setFont(new Font("Arial", Font.PLAIN, 20));
        amountBeingPaid.setHorizontalAlignment(JLabel.TRAILING);
        paidPanel.add(amountBeingPaid);

        panel.add(paidPanel);

        //Create transaction panel
        JPanel transacationPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        transacationPanel.setBackground(Color.darkGray);

        JButton cancelTransaction = new JButton("Cancel");
        cancelTransaction.setBackground(Color.darkGray);
        cancelTransaction.setForeground(Color.lightGray);
        cancelTransaction.setFocusable(false);
        cancelTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cancelTransactionFunction("User Cancelled");

            }
        });
        transacationPanel.add(cancelTransaction);

        pay = new JButton("Pay");
        pay.setBackground(Color.darkGray);
        pay.setForeground(Color.darkGray);
        pay.setFocusable(false);
        pay.setEnabled(false);
        transacationPanel.add(pay);
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                purchaseItemsCash();
                closeFrame();
            }
        });

        panel.add(transacationPanel);


        return panel;
    }
}
