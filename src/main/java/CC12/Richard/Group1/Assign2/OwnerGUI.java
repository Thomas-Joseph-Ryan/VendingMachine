package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class OwnerGUI extends GUI {


    public OwnerGUI(Controller controller) {

        super(controller);
        setLayout(new GridLayout(10, 1));
        setMinimumSize(new Dimension(900, 700));
        getContentPane().setBackground(Color.darkGray);

        
        add(createFirstPanel());
        add(createButtonPanel());
        add(createSecondPanel());
        add(createThirdPanel());
        add(createFourthPanel());
        add(createFifthPanel());
        add(createSixthPanel());
        add(createSeventhPanel());
        // add(createEighthPanel());
        add(new JLabel(""));
        add(createNinthPanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setVisible(true);

    }


    private void setPanelDefaults(JPanel panel) {
        panel.setBackground(Color.darkGray);

    }

    private void setTitleDefault(JLabel label) {
        label.setForeground(Color.lightGray);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(new LineBorder(Color.lightGray));
        label.setFont(new Font("Calibri", Font.BOLD, 20));
    }

    

    private JPanel createFirstPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 2, 30));
        panel.setBackground(Color.darkGray);

        panel.add(new JLabel(""));

    //    JPanel panel = new JPanel();

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

        JLabel pageLabel = new JLabel("Owner Home");
        setTitleDefault(pageLabel);

        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.TRAILING);
        image.setIcon(new ImageIcon("src/main/resources/LiteSnacks.png"));

        topPanel.add(signInText);
        topPanel.add(pageLabel);
        topPanel.add(image);
        panel.add(topPanel);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        //Create back button
        JButton owner = new JButton("Owner");
        owner.setBackground(Color.darkGray);
        owner.setForeground(Color.lightGray);
        owner.setFocusable(false);
        owner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
                new OwnerGUI(controller);
            }
        });
        JButton cashierButton = new JButton("Cashier");
        cashierButton.setBackground(Color.darkGray);
        cashierButton.setForeground(Color.lightGray);
        cashierButton.setFocusable(false);
         cashierButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 closeFrame();
                 new CashierGUI(controller);
             }
         });

        JButton sellerButton = new JButton("Seller");
        sellerButton.setBackground(Color.darkGray);
        sellerButton.setForeground(Color.lightGray);
        sellerButton.setFocusable(false);
         sellerButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 closeFrame();
                 new SellerGUI(controller);
             }
         });

        JPanel backPanel = new JPanel();
        setPanelDefaults(backPanel);
        backPanel.setLayout(new GridLayout(1, 3));
        backPanel.add(owner);
        backPanel.add(cashierButton);
        backPanel.add(sellerButton);
        panel.add(backPanel);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createSecondPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        //Create username label
        JLabel username = new JLabel("Owner");
        username.setForeground(Color.lightGray);
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setVerticalAlignment(JLabel.BOTTOM);
        username.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(username);
        
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createThirdPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        

        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.darkGray);
        
        textPanel.add(new JLabel(""));
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        textPanel.add(usernameField);
        textPanel.add(new JLabel(""));

        panel.add(textPanel);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.darkGray);
        // buttonPanel.add(new JLabel(""));
        // buttonPanel.add(new JLabel(""));
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.lightGray);
        addButton.setFocusable(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                if (username.length() > 3){
                    if (UserDao.checkIfUsernameAlreadyExists(username) && (UserDao.getUserRole(username).equals("customer"))){
                        UserDao.setUserRole(username,"owner");
                        JOptionPane.showMessageDialog(null, "Role Given Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (!(UserDao.checkIfUsernameAlreadyExists(username) )){
                        UserDao.insertNewUserByOwner(username,"owner");
                        JOptionPane.showMessageDialog(null, "Role Given Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Cannot give Role to User","Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "username must be atleast 4 characters","Failure", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.darkGray);
        removeButton.setForeground(Color.lightGray);
        removeButton.setFocusable(false);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                if (username.length() > 3){
                    if  (UserDao.getUserRole(username).equals("owner") && !(username.equals(controller.getUser().getName()))) {
                        UserDao.setUserRole(username,"customer");
                        JOptionPane.showMessageDialog(null, "Role Removed Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username does not exist or does not have Owner Role","Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "username must be atleast 4 characters","Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(removeButton); 
        buttonPanel.add(new JLabel(""));


        JButton reportButton = new JButton("Generate Users Report");
        reportButton.setBackground(Color.darkGray);
        reportButton.setForeground(Color.lightGray);
        reportButton.setFocusable(false);
        reportButton.setHorizontalAlignment(JLabel.TRAILING);
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Report Generated Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                UserDao userDao = new UserDao(null);
                userDao.generateUsersReport(null);

            }
        });

        panel.add(buttonPanel);
        panel.add(reportButton);

        return panel;
    }
        


    private JPanel createFourthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        //Create username label
        JLabel username = new JLabel("Seller");
        username.setForeground(Color.lightGray);
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setVerticalAlignment(JLabel.BOTTOM);
        username.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(username);
        
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createFifthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        

        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.darkGray);
        
        textPanel.add(new JLabel(""));
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        textPanel.add(usernameField);
        textPanel.add(new JLabel(""));

        panel.add(textPanel);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.darkGray);
        // buttonPanel.add(new JLabel(""));
        // buttonPanel.add(new JLabel(""));
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.lightGray);
        addButton.setFocusable(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                if (username.length() > 3){
                    if (UserDao.checkIfUsernameAlreadyExists(username) && (UserDao.getUserRole(username).equals("customer"))){
                        UserDao.setUserRole(username,"seller");
                        JOptionPane.showMessageDialog(null, "Role Given Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (!(UserDao.checkIfUsernameAlreadyExists(username) )){
                        UserDao.insertNewUserByOwner(username,"seller");
                        JOptionPane.showMessageDialog(null, "Role Given Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Cannot give Role to User","Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "username must be atleast 4 characters","Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.darkGray);
        removeButton.setForeground(Color.lightGray);
        removeButton.setFocusable(false);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                if (username.length() > 3){
                    if  (UserDao.getUserRole(username).equals("seller") && !(username.equals(controller.getUser().getName()))) {
                        UserDao.setUserRole(username,"customer");
                        JOptionPane.showMessageDialog(null, "Role Removed Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username does not exist or does not have Seller Role","Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "username must be atleast 4 characters","Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(removeButton); 
        buttonPanel.add(new JLabel(""));


        JButton reportButton = new JButton("Generate Cancellation Summary");
        reportButton.setBackground(Color.darkGray);
        reportButton.setForeground(Color.lightGray);
        reportButton.setFocusable(false);
        reportButton.setHorizontalAlignment(JLabel.TRAILING);
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Report Generated Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                ItemDao itemDao = new ItemDao(null);
                itemDao.generateCancelledTransactionSummary(null);

            }
        });

        panel.add(buttonPanel);
        panel.add(reportButton);

        return panel;
    }

    private JPanel createSixthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        //Create username label
        JLabel username = new JLabel("Cashier");
        username.setForeground(Color.lightGray);
        username.setHorizontalAlignment(JLabel.CENTER);
        username.setVerticalAlignment(JLabel.BOTTOM);
        username.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(username);
        
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createSeventhPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        

        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.darkGray);
        
        textPanel.add(new JLabel(""));
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        textPanel.add(usernameField);
        textPanel.add(new JLabel(""));

        panel.add(textPanel);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.setBackground(Color.darkGray);
        // buttonPanel.add(new JLabel(""));
        // buttonPanel.add(new JLabel(""));
        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.lightGray);
        addButton.setFocusable(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                if (username.length() > 3){
                    if (UserDao.checkIfUsernameAlreadyExists(username) && (UserDao.getUserRole(username).equals("customer"))){
                        UserDao.setUserRole(username,"cashier");
                        JOptionPane.showMessageDialog(null, "Role Given Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if (!(UserDao.checkIfUsernameAlreadyExists(username) )){
                        UserDao.insertNewUserByOwner(username,"cashier");
                        JOptionPane.showMessageDialog(null, "Role Given Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Cannot give Role to User","Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "username must be atleast 4 characters","Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setBackground(Color.darkGray);
        removeButton.setForeground(Color.lightGray);
        removeButton.setFocusable(false);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                if (username.length() > 3){
                    if  (UserDao.getUserRole(username).equals("cashier") && !(username.equals(controller.getUser().getName()))) {
                        UserDao.setUserRole(username,"customer");
                        JOptionPane.showMessageDialog(null, "Role Removed Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Username does not exist or does not have Cashier Role","Failure", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "username must be atleast 4 characters","Failure", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(removeButton); 
        buttonPanel.add(new JLabel(""));


        panel.add(buttonPanel);
        panel.add(new JLabel(""));

        return panel;
    }

    // private JPanel createEighthPanel() {
    //     JPanel panel = new JPanel(new GridLayout(1, 3));
    //     panel.setBackground(Color.darkGray);

    //     //Create username label
    //     JLabel username = new JLabel("User");
    //     username.setForeground(Color.lightGray);
    //     username.setHorizontalAlignment(JLabel.CENTER);
    //     username.setVerticalAlignment(JLabel.BOTTOM);
    //     username.setFont(new Font("Calibri", Font.BOLD, 20));
    //     panel.add(username);
        
    //     panel.add(new JLabel(""));
    //     panel.add(new JLabel(""));

    //     return panel;
    // }

    private JPanel createNinthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        

        //Create textField Panel
        // JPanel textPanel = new JPanel(new GridLayout(3, 1));
        // textPanel.setBackground(Color.darkGray);
        
        // textPanel.add(new JLabel(""));
        // usernameField = new JTextField();
        // usernameField.setPreferredSize(new Dimension(300, 30));
        // textPanel.add(usernameField);
        // textPanel.add(new JLabel(""));

        // panel.add(textPanel);
         panel.add(new JLabel(""));
        // JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        // buttonPanel.setBackground(Color.darkGray);
        // // buttonPanel.add(new JLabel(""));
        // // buttonPanel.add(new JLabel(""));
        // JButton addButton = new JButton("Add");
        // addButton.setBackground(Color.darkGray);
        // addButton.setForeground(Color.lightGray);
        // addButton.setFocusable(false);
        // addButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         boolean valid = false;
        //         String username = usernameField.getText();
        //         System.out.println("YAS");
        //     }
        // });

        // JButton removeButton = new JButton("Remove");
        // removeButton.setBackground(Color.darkGray);
        // removeButton.setForeground(Color.lightGray);
        // removeButton.setFocusable(false);
        // removeButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         boolean valid = false;
        //         String username = usernameField.getText();
        //         System.out.println("YAS");
        //     }
        // });

        // buttonPanel.add(addButton);
        // buttonPanel.add(new JLabel(""));
        // buttonPanel.add(removeButton); 
        // buttonPanel.add(new JLabel(""));

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
            

        // panel.add(buttonPanel);
        panel.add(new JLabel(""));
        panel.add(signOut);

        return panel;
    }
    
    
}
