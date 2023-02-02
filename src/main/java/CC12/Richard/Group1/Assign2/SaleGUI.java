package CC12.Richard.Group1.Assign2;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.basic.BasicScrollBarUI;
 
public class SaleGUI extends GUI{

    private JLabel totalCostLabel;
    private JButton cash = new JButton();
    private JButton card = new JButton();

    public SaleGUI(Controller controller) {
        super(controller);
        controller.getItemsList().clear();
        setLayout(new GridLayout(6, 1, 0, 10));
        setMinimumSize(new Dimension(900, 700));
        getContentPane().setBackground(Color.darkGray);


        add(create1stPanel());
        add(create2ndPanel());
        add(create3rdPanel());
        add(create4thPanel());
        add(create5thPanel());
        add(create6thPanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
//        pack();
        setVisible(true);
    }

    private void setPanelDefaults(JPanel panel) {
        panel.setBackground(Color.darkGray);

    }

    private void setTitleDefault(JLabel label) {
        label.setForeground(Color.lightGray);
        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setBorder(new LineBorder(Color.lightGray));
        label.setFont(new Font("Calibri", Font.BOLD, 30));
        label.setVerticalAlignment(JLabel.BOTTOM);
    }

    private JScrollPane createOrderPanel(int type, JPanel panel) {

        panel.setBackground(Color.darkGray);

        List<Item> items = new ArrayList<>();

        if (type == 0) {
            items = ItemDao.viewDrinks();
        } else if (type == 1) {
            items = ItemDao.viewChocolates();
        } else if (type == 2) {
            items = ItemDao.viewChips();
        } else if (type == 3) {
            items = ItemDao.viewCandies();
        }

        for (Item item : items) {
            controller.getItemsList().add(item);
//            System.out.println(controller.getItemsList().size());
            JLabel itemName = new JLabel(item.getName());
//            itemName.setPreferredSize(new Dimension(70, 30));
            itemName.setForeground(Color.lightGray);
            itemName.setForeground(Color.lightGray);
            itemName.setFont(new Font("Calibri", Font.BOLD, 14));
            itemName.setVerticalAlignment(JLabel.CENTER);

            JLabel itemQty = new JLabel("Qty: " + item.getQty());
//            itemQty.setPreferredSize(new Dimension(40, 30));
            itemQty.setForeground(Color.lightGray);
            itemQty.setFont(new Font("Calibri", Font.PLAIN, 14));
            itemQty.setVerticalAlignment(JLabel.CENTER);

            JLabel itemPrice = new JLabel("$" + item.getPrice());
//            itemPrice.setPreferredSize(new Dimension(30, 30));
            itemPrice.setForeground(Color.lightGray);
            itemPrice.setForeground(Color.lightGray);
            itemPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
            itemPrice.setVerticalAlignment(JLabel.CENTER);

            JLabel qtyInBag = new JLabel("In bag: " + item.getQtyInBag());
            qtyInBag.setPreferredSize(new Dimension(90, 30));
            qtyInBag.setForeground(Color.lightGray);
            qtyInBag.setForeground(Color.lightGray);
            qtyInBag.setFont(new Font("Calibri", Font.PLAIN, 14));
            qtyInBag.setVerticalAlignment(JLabel.CENTER);

            JButton buttonUp = new JButton("^");
            buttonUp.setBackground(Color.darkGray);
            buttonUp.setForeground(Color.lightGray);
            buttonUp.setFocusable(false);
            buttonUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item.getQtyInBag() < item.getQty()) {
                        item.setQtyInBag(item.getQtyInBag() + 1);
                        qtyInBag.setText("In bag: " + item.getQtyInBag());
                        controller.getUser().setTotalCost(controller.getUser().getTotalCost() + item.getPrice());
                        totalCostLabel.setText(String.format("Total: $%.2f", controller.getUser().getTotalCost()));
                        if (controller.getUser().getTotalCost() > 0.0) {
                            cash.setEnabled(true);
                            card.setEnabled(true);
                        } else {
                            cash.setEnabled(false);
                            card.setEnabled(false);

                        }
                    }
                }
            });

            JButton buttonDown = new JButton("v");
            buttonDown.setBackground(Color.darkGray);
            buttonDown.setForeground(Color.lightGray);
            buttonDown.setFocusable(false);
            buttonDown.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (item.getQtyInBag() > 0) {
                        item.setQtyInBag(item.getQtyInBag() - 1);
                        qtyInBag.setText("In bag: " + item.getQtyInBag());
                        controller.getUser().setTotalCost(controller.getUser().getTotalCost() - item.getPrice());
                        totalCostLabel.setText(String.format("Total: $%.2f", controller.getUser().getTotalCost()));
                        if (controller.getUser().getTotalCost() > 0.0) {
                            cash.setEnabled(true);
                            card.setEnabled(true);
                        } else {
                            cash.setEnabled(false);
                            card.setEnabled(false);

                        }
                    }
                }
            });

            panel.add(buttonUp);
            panel.add(buttonDown);
            panel.add(itemName);
            panel.add(itemPrice);
            panel.add(itemQty);
            panel.add(qtyInBag);

        }

        UIManager.put("ScrollBar.width", 8);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getHorizontalScrollBar().setBackground(Color.darkGray);
//        scrollPane.getHorizontalScrollBar().setForeground(Color.darkGray);

        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
            thumbColor = Color.gray;
            }
        });
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    private JPanel create1stPanel() {

        JPanel panel = new JPanel();

        setPanelDefaults(panel);
        panel.setLayout(new GridLayout(2, 1));

        //Create top label panel
        JPanel topPanel = new JPanel();
        setPanelDefaults(topPanel);
        topPanel.setLayout(new GridLayout(1, 3));

        String signInAsString = String.format("Signed in as: %s", controller.getUser().getName());
        JLabel signInText = new JLabel(signInAsString);
        signInText.setForeground(Color.lightGray);
        signInText.setHorizontalAlignment(JLabel.CENTER);

        JLabel drinksLabel = new JLabel("Drinks");
        setTitleDefault(drinksLabel);

        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.TRAILING);
        image.setIcon(new ImageIcon("src/main/resources/LiteSnacks.png"));

        topPanel.add(signInText);
        topPanel.add(drinksLabel);
        topPanel.add(image);
        panel.add(topPanel);
        //Top panel finished

        //Create bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        panel.add(createOrderPanel(0, bottomPanel));

        return panel;
    }

    private JPanel create2ndPanel() {

        JPanel panel = new JPanel();

        setPanelDefaults(panel);
        panel.setLayout(new GridLayout(2, 1));

        //Title panel

        JPanel topPanel = new JPanel(new GridLayout(1,3));
        setPanelDefaults(topPanel);

        topPanel.add(new JLabel());
        JLabel chocolateLabel = new JLabel("Chocolate");
        setTitleDefault(chocolateLabel);

        topPanel.add(chocolateLabel);
        topPanel.add(new JLabel());
        panel.add(topPanel);

        //Order panel
        JPanel orderPanel = new JPanel();
        panel.add(createOrderPanel(1, orderPanel));

        return panel;
    }

    private JPanel create3rdPanel() {

        JPanel panel = new JPanel();

        setPanelDefaults(panel);
        panel.setLayout(new GridLayout(2, 1));

        //Title panel

        JPanel topPanel = new JPanel(new GridLayout(1,3));
        setPanelDefaults(topPanel);

        topPanel.add(new JLabel());
        JLabel chocolateLabel = new JLabel("Chips");
        setTitleDefault(chocolateLabel);

        topPanel.add(chocolateLabel);
        topPanel.add(new JLabel());
        panel.add(topPanel);

        //Order panel
        JPanel orderPanel = new JPanel();
        panel.add(createOrderPanel(2, orderPanel));

        return panel;
    }

    private JPanel create4thPanel() {

        JPanel panel = new JPanel();

        setPanelDefaults(panel);
        panel.setLayout(new GridLayout(2, 1));

        //Title panel

        JPanel topPanel = new JPanel(new GridLayout(1,3));
        setPanelDefaults(topPanel);

        topPanel.add(new JLabel());
        JLabel chocolateLabel = new JLabel("Candies");
        setTitleDefault(chocolateLabel);

        topPanel.add(chocolateLabel);
        topPanel.add(new JLabel());
        panel.add(topPanel);

        //Order panel
        JPanel orderPanel = new JPanel();
        panel.add(createOrderPanel(3, orderPanel));

        return panel;
    }

    private JPanel create5thPanel() {
        JPanel panel = new JPanel();
        setPanelDefaults(panel);
        panel.setLayout(new GridLayout(1, 2));

        //History panel/
        JPanel historyPanel = new JPanel(new GridLayout(6, 1));
        setPanelDefaults(historyPanel);
        List<History> History = HistoryDao.viewHistoryByUserName(controller.getUser().getName());

        JLabel historyLabel = new JLabel("  Last 5 products: ");
        historyLabel.setHorizontalAlignment(JLabel.CENTER);
        historyLabel.setForeground(Color.lightGray);
        historyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        List<JLabel> historyLabels = new ArrayList<>();
        for (int i=0; i<History.size(); i++){
            
            historyLabels.add(new JLabel(String.format("      %s. ",i+1) + History.get(i).getProduct()));
        }
        setHistoryLabelDefaults(historyLabels);

        historyPanel.add(historyLabel);
        for (JLabel label : historyLabels) {
            label.setHorizontalAlignment(JLabel.CENTER);
            historyPanel.add(label);
        }

        panel.add(historyPanel);

        //Create right panel
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        setPanelDefaults(rightPanel);

        //Create Total Cost Counter
        totalCostLabel = new JLabel(String.format("Total: $%.2f", controller.getUser().getTotalCost()));
        totalCostLabel.setForeground(Color.lightGray);
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 20));
        totalCostLabel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        totalCostLabel.setHorizontalAlignment(JLabel.CENTER);

        rightPanel.add(totalCostLabel);

        //Create Complete Transaction panel
        JPanel completeTransactionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        setPanelDefaults(completeTransactionPanel);

        JLabel labelTransaction = new JLabel("Complete Transaction: ");
        labelTransaction.setFont(new Font("Arial", Font.BOLD, 20));
        labelTransaction.setForeground(Color.lightGray);
        labelTransaction.setVerticalAlignment(JLabel.CENTER);

        cash = new JButton("Cash");
        cash.setEnabled(false);
        cash.setFont(new Font("Arial", Font.BOLD, 14));
        cash.setBackground(Color.darkGray);
        cash.setForeground(Color.lightGray);


        cash.setFocusable(false);
        cash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
                new CashPaymentGUI(controller);
            }
        });

        card = new JButton("Card");
        card.setEnabled(false);
        card.setFont(new Font("Arial", Font.BOLD, 14));
        card.setBackground(Color.darkGray);
        card.setForeground(Color.lightGray);
        card.setFocusable(false);


        card.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
                new CardPaymentGUI(controller);
            }
        });

        completeTransactionPanel.add(labelTransaction);
        completeTransactionPanel.add(cash);
        completeTransactionPanel.add(card);

        rightPanel.add(completeTransactionPanel);

        panel.add(rightPanel);

        return panel;
    }

    private void setHistoryLabelDefaults(List<JLabel> labels) {
        for (JLabel label : labels) {
            label.setForeground(Color.lightGray);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
//            label.setHorizontalAlignment(JLabel.CENTER);
        }

    }

    private JPanel create6thPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setBackground(Color.darkGray);

        //Cancel transaction
        JButton cancel = new JButton("Cancel Transaction");
        cancel.setBackground(Color.darkGray);
        cancel.setForeground(Color.lightGray);
        cancel.setFocusable(false);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cancelTransactionFunction("User Cancelled");

            }
        });

        panel.add(cancel);

        if (controller.getUser().getName().equalsIgnoreCase("guest")) {
            JPanel loginSignUp = new JPanel(new GridLayout(2, 1));
            loginSignUp.setBackground(Color.darkGray);
            JButton signUp = new JButton("Sign Up");
            signUp.setBackground(Color.darkGray);
            signUp.setForeground(Color.lightGray);
            signUp.setFocusable(false);
            signUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.getUser().setTotalCost(0);
                    closeFrame();
                    new SignUpGUI(controller);
                }
            });

            JButton login = new JButton("Login");
            login.setBackground(Color.darkGray);
            login.setForeground(Color.lightGray);
            login.setFocusable(false);
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.getUser().setTotalCost(0);
                    closeFrame();
                    new LoginGUI(controller);
                }
            });

            loginSignUp.add(signUp);
            loginSignUp.add(login);

            panel.add(loginSignUp);
        } else {
            JButton signOut = new JButton("Sign Out");
            signOut.setBackground(Color.darkGray);
            signOut.setForeground(Color.lightGray);
            signOut.setFocusable(false);
            signOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.getUser().setName("guest");
                    closeFrame();
                    new SaleGUI(controller);
                }
            });
            panel.add(signOut);
        }



        return panel;
    }
}
