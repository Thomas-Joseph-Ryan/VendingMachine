package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends GUI {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI(Controller controller) {

        super(controller);
        setLayout(new GridLayout(5, 1));
        setMinimumSize(new Dimension(900, 700));
        getContentPane().setBackground(Color.darkGray);

        add(createFirstPanel());
        add(createSecondPanel());
        add(createThirdPanel());
        add(createFourthPanel());

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
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backFunction();
            }
        });
        panel.add(back);

        //Create title
        JLabel title = new JLabel("Login");
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

    private JPanel createSecondPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        //Create username label
        JLabel username = new JLabel("Username:   ");
        username.setForeground(Color.lightGray);
        username.setHorizontalAlignment(JLabel.TRAILING);
        username.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(username);

        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.darkGray);
        textPanel.add(new JLabel(""));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));
        textPanel.add(usernameField);
        textPanel.add(new JLabel(""));

        panel.add(textPanel);
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createThirdPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);

        //Create username label
        JLabel password = new JLabel("Password:   ");
        password.setForeground(Color.lightGray);
        password.setHorizontalAlignment(JLabel.TRAILING);
        password.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(password);

        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.darkGray);
        textPanel.add(new JLabel(""));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 30));
        textPanel.add(passwordField);
        textPanel.add(new JLabel(""));

        panel.add(textPanel);
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createFourthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);
        panel.add(new JLabel(""));

        //Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(""));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.darkGray);
        loginButton.setForeground(Color.lightGray);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = false;
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                //Method that checks username against database to see if it already exists
                if (UserDao.checkIfUsernameAlreadyExists(username)) {

                    //Method that checks if password matches username in database
                    if (UserDao.checkIfPasswordMatchesUsername(username, password)) {
                        valid = true;
                    }

                    else {
                        JOptionPane.showMessageDialog(null, "Your username or password is incorrect. Please try again.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                    }
                }

                else {
                    JOptionPane.showMessageDialog(null, "Your username or password is incorrect. Please try again.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                }


                if (valid) {
                    //TODO create function to log user into database
                    JOptionPane.showMessageDialog(null, "You are now logged in!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    controller.setUser(UserDao.viewUser(username));
                    System.out.println(controller.getUser().getCardName());
                    closeFrame();
                    if (UserDao.getUserRole(username).equals("owner")){
                        new OwnerGUI(controller);
                    } else if (UserDao.getUserRole(username).equals("seller")) {
                        new SellerGUI(controller);
                    } else if (UserDao.getUserRole(username).equals("cashier")) {
                        new CashierGUI(controller);
                    }else{
                        new SaleGUI(controller);
                    }
                    
                }
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(new JLabel(""));

        panel.add(buttonPanel);
        panel.add(new JLabel(""));

        return panel;
    }
}
