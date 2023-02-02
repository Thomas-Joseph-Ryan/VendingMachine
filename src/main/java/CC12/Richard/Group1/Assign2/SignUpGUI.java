package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends GUI {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignUpGUI(Controller controller) {

        super(controller);
        setLayout(new GridLayout(5, 1));
        setMinimumSize(new Dimension(600, 500));
        getContentPane().setBackground(Color.darkGray);

        add(createFirstPanel());
        add(createSecondPanel());
        add(createThirdPanel());
        add(createFourthPanel());
        add(createFifthPanel());

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
        JLabel title = new JLabel("Sign Up");
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

        //Create username label
        JLabel password = new JLabel("Confirm Password:   ");
        password.setForeground(Color.lightGray);
        password.setHorizontalAlignment(JLabel.TRAILING);
        password.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(password);

        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.setBackground(Color.darkGray);
        textPanel.add(new JLabel(""));
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(300, 30));
        textPanel.add(confirmPasswordField);
        textPanel.add(new JLabel(""));

        panel.add(textPanel);
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createFifthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);
        panel.add(new JLabel(""));

        //Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(""));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(Color.darkGray);
        signUpButton.setForeground(Color.lightGray);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

                //TODO: Method that checks username against database to see if it already exists
                if (UserDao.checkIfUsernameAlreadyExists(username)) {
                    JOptionPane.showMessageDialog(null, "This username already exists", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                    valid = false;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords dont match", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    valid = false;
                }

                if (password.equals("")) {
                    JOptionPane.showMessageDialog(null, "You must enter a password", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                    valid = false;
                }

                if (username.equals("")) {
                    JOptionPane.showMessageDialog(null, "You must enter a username", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                    valid = false;
                }

                if (valid) {
                    //TODO create function to insert user into database
                    UserDao.insertNewUser(username, password);
                    controller.getUser().setName(username);
                    controller.getUser().setPassword(password);
                    JOptionPane.showMessageDialog(null, "Thanks for signing up!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    closeFrame();
                    new SaleGUI(controller);
                }
            }
        });

        buttonPanel.add(signUpButton);
        buttonPanel.add(new JLabel(""));

        panel.add(buttonPanel);
        panel.add(new JLabel(""));

        return panel;
    }
}
